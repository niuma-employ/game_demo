package com.lyh.demo_game.controller;

import com.github.pagehelper.PageInfo;
import com.lyh.demo_game.domain.AppInfo;
import com.lyh.demo_game.domain.AppCollection;
import com.lyh.demo_game.domain.AppVersion;
import com.lyh.demo_game.service.AppInfoService;
import com.lyh.demo_game.service.AppVersionService;
import com.lyh.demo_game.utils.JwtUtils;
import com.lyh.demo_game.utils.RedisUtils;
import com.lyh.demo_game.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * AppInfoController 负责处理与应用信息相关的所有接口请求
 */
@RestController
@RequestMapping("/appInfo")
public class AppInfoController {
    // 注入AppInfoService服务
    @Autowired
    AppInfoService appInfoService;

    // 注入AppVersionService服务
    @Autowired
    AppVersionService appVersionService;

    // 注入Redis工具类，用于直接在Controller层操作缓存（如测试接口）
    @Autowired
    RedisUtils redisUtils;

    // 上传文件路径
    @Value("${upload.path}")
    String uploadPath;

    /**
     * 删除指定ID的应用信息
     * @param id 应用ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResultVo del(@PathVariable Long id){
        appInfoService.del(id, uploadPath);
        return ResultVo.success("删除成功",null);
    }

    /**
     * 查询应用基本信息及其所有版本信息
     * @param appid 应用ID
     * @return 应用及版本信息
     */
    @GetMapping("/appWithVersion/{appid}")
    public ResultVo appWithVersion(@PathVariable Long appid){
        AppInfo appInfo = appInfoService.getAppWithVersion(appid);
        return ResultVo.success("",appInfo);
    }

    /**
     * 上传logo图片
     * @param id 应用ID
     * @param logo logo文件
     * @return 上传结果
     */
    @PostMapping("/upLogo/{id}")
    public ResultVo upLogo(@PathVariable Long id, MultipartFile logo){
        if(logo!=null && !logo.isEmpty()){
            //说明前端传了文件
            //获取上传文件的原始名称
            String originalFilename = logo.getOriginalFilename();
            //提取文件的扩展名。
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            //不超过1M
            if(logo.getSize() > 1024*1024){
                return ResultVo.error("文件过大，请上传不要超过1M");
            } else if(
                    suffix.equalsIgnoreCase(".jpg")||
                    suffix.equalsIgnoreCase(".jpeg")||
                    suffix.equalsIgnoreCase(".gif")||
                    suffix.equalsIgnoreCase(".png")
            ){
                //上传文件
                File savePath = new File(uploadPath);
                if(!savePath.exists()){
                    savePath.mkdirs();
                }
                //文件起新名字
                String newName = UUID.randomUUID().toString().replace("-","");
                File saveFile = new File(uploadPath+newName+suffix);
                try {
                    logo.transferTo(saveFile);
                    //文件已经上传成功
                    //保存到数据库
                    AppInfo appInfo = appInfoService.getById(id);
                    appInfo.setLogopicpath(newName+suffix);
                    appInfoService.saveOrUpdate(appInfo);
                    return  ResultVo.success("上传logo成功",null);
                } catch (IOException e) {
                    throw new RuntimeException("文件上传异常");
                }
            }else{
                return ResultVo.error("文件格式不对，必须是图片");
            }
        }else {
            return ResultVo.error("文件必需传");
        }
    }

    /**
     * 校验apkname不能重复
     * @param apkname 包名
     * @param id 应用ID（可选）
     * @return 是否可用
     */
    @GetMapping("/validateApkName")
    public ResultVo validateApkName(@RequestParam(required = true)String apkname,@RequestParam(required = false) Long id){
        Boolean flag = appInfoService.validateApkName(apkname,id);
        return ResultVo.success("",flag);
    }

    /**
     * 新增或修改应用信息
     * @param appInfo 应用信息
     * @param token 用户token
     * @param userType 用户类型
     * @return 操作结果
     */
    @PostMapping("/add")
    public ResultVo add(@RequestBody AppInfo appInfo, @RequestHeader(value = "token", required = false) String token, @RequestHeader(value = "userType", required = false) String userType){
        if ("dev".equals(userType) && appInfo.getDevid() == null) {
            Long devId = JwtUtils.getUserId(token, userType);
            appInfo.setDevid(devId);
        }
        appInfoService.saveOrUpdate(appInfo);
        return ResultVo.success("操作成功",null);
    }

    /**
     * 分页查询应用信息
     * @param appInfo 查询条件
     * @param pageNum 页码
     * @param token 用户token
     * @param userType 用户类型
     * @return 分页结果
     */
    @PostMapping("/page")
    public ResultVo page(@RequestBody AppInfo appInfo, @RequestParam(defaultValue = "1" )Integer pageNum, @RequestHeader("token") String token, @RequestHeader("userType") String userType){
        Long userId = null;
        if ("dev".equals(userType)) {
            // 只有开发者需要 userId，用于查询自己创建的应用
            userId = JwtUtils.getUserId(token, userType);
        }
        // admin 和 user 都不需要 userId，admin 查询所有应用，user 查询已上架应用
        PageInfo pageInfo = appInfoService.getPage(appInfo,pageNum, userType, userId);
        return ResultVo.success("查询成功",pageInfo);
    }

    /**
     * 收藏应用
     * @param token 用户token
     * @param appId 应用ID
     * @return 操作结果
     */
    @PostMapping("/collect/{appId}")
    public ResultVo collect(@RequestHeader("token") String token, @PathVariable Long appId) {
        Long userId = JwtUtils.getUserId(token, "normal");
        appInfoService.collect(userId, appId);
        return ResultVo.success("收藏成功", null);
    }

    /**
     * 取消收藏
     * @param token 用户token
     * @param appId 应用ID
     * @return 操作结果
     */
    @PostMapping("/uncollect/{appId}")
    public ResultVo uncollect(@RequestHeader("token") String token, @PathVariable Long appId) {
        Long userId = JwtUtils.getUserId(token, "normal");
        appInfoService.uncollect(userId, appId);
        return ResultVo.success("取消收藏成功", null);
    }

    /**
     * 获取我的收藏列表
     * @param token 用户token
     * @return 收藏的应用列表
     */
    @GetMapping("/myCollections")
    public ResultVo myCollections(@RequestHeader("token") String token) {
        Long userId = JwtUtils.getUserId(token, "normal");
        List<AppInfo> list = appInfoService.myCollections(userId);
        System.out.println("[myCollections] userId=" + userId + ", 返回数量=" + list.size());
        for (AppInfo app : list) {
            System.out.println(app);
        }
        return ResultVo.success("查询成功", list);
    }

    /**
     * 上架应用
     * @param token 用户token
     * @param appId 应用ID
     * @return 操作结果
     */
    @PostMapping("/publish/{appId}")
    public ResultVo publish(@RequestHeader("token") String token, @PathVariable Long appId) {
        appInfoService.publish(appId);
        return ResultVo.success("上架成功", null);
    }

    /**
     * 审核应用
     * @param token 用户token
     * @param appId 应用ID
     * @param status 审核状态
     * @return 操作结果
     */
    @PostMapping("/audit/{appId}")
    public ResultVo audit(@RequestHeader("token") String token, @PathVariable Long appId, @RequestParam Long status) {
        appInfoService.audit(appId, status);
        return ResultVo.success("审核成功", null);
    }

    /**
     * 获取应用详情（含版本列表）
     * @param appId 应用ID
     * @return 应用详情和版本列表
     */
    @GetMapping("/detail/{appId}")
    public ResultVo getAppDetail(@PathVariable Long appId) {
        AppInfo appInfo = appInfoService.getById(appId);
        List<AppVersion> versionList = appVersionService.selectByAppId(appId);
        HashMap<String, Object> data = new HashMap<>();
        data.put("appInfo", appInfo);
        data.put("versionList", versionList);
        return ResultVo.success("查询成功", data);
    }

    /**
     * 审核接口，支持POST JSON参数
     * @param params 包含appId和status的参数
     * @return 操作结果
     */
    @PostMapping("/audit")
    public ResultVo auditApp(@RequestBody HashMap<String, Object> params) {
        Long appId = Long.valueOf(params.get("appId").toString());
        Long status = Long.valueOf(params.get("status").toString());
        appInfoService.audit(appId, status);
        return ResultVo.success("审核成功", null);
    }

    /**
     * 测试缓存效果的接口
     * @param appId 应用ID
     * @return 查询时间、缓存状态等信息
     */
    @GetMapping("/testCache/{appId}")
    public ResultVo testCache(@PathVariable Long appId) {
        HashMap<String, Object> result = new HashMap<>();
        // 第一次查询（应该从数据库获取）
        long startTime1 = System.currentTimeMillis();
        AppInfo appInfo1 = appInfoService.getById(appId);
        long endTime1 = System.currentTimeMillis();
        // 第二次查询（应该从缓存获取）
        long startTime2 = System.currentTimeMillis();
        AppInfo appInfo2 = appInfoService.getById(appId);
        long endTime2 = System.currentTimeMillis();
        // 通过 redisUtils.hasKey 检查缓存是否存在，验证缓存命中情况
        String cacheKey = "game:detail:" + appId;
        boolean hasCache = redisUtils.hasKey(cacheKey);
        result.put("firstQueryTime", endTime1 - startTime1);
        result.put("secondQueryTime", endTime2 - startTime2);
        result.put("hasCache", hasCache);
        result.put("appInfo", appInfo2);
        return ResultVo.success("测试成功", result);
    }

    /**
     * 测试带版本信息的缓存效果
     * @param appId 应用ID
     * @return 查询时间、缓存状态等信息
     */
    @GetMapping("/testCacheWithVersion/{appId}")
    public ResultVo testCacheWithVersion(@PathVariable Long appId) {
        HashMap<String, Object> result = new HashMap<>();
        // 第一次查询（应该从数据库获取）
        long startTime1 = System.currentTimeMillis();
        AppInfo appInfo1 = appInfoService.getAppWithVersion(appId);
        long endTime1 = System.currentTimeMillis();
        // 第二次查询（应该从缓存获取）
        long startTime2 = System.currentTimeMillis();
        AppInfo appInfo2 = appInfoService.getAppWithVersion(appId);
        long endTime2 = System.currentTimeMillis();
        // 通过 redisUtils.hasKey 检查缓存是否存在，验证缓存命中情况
        String cacheKey = "game:detail:" + appId + ":with_version";
        boolean hasCache = redisUtils.hasKey(cacheKey);
        result.put("firstQueryTime", endTime1 - startTime1);
        result.put("secondQueryTime", endTime2 - startTime2);
        result.put("hasCache", hasCache);
        result.put("appInfo", appInfo2);
        return ResultVo.success("测试成功", result);
    }

    /**
     * 测试缓存更新接口
     * @param appId 应用ID
     * @param appInfo 应用信息
     * @return 缓存状态
     */
    @PostMapping("/testCacheUpdate/{appId}")
    public ResultVo testCacheUpdate(@PathVariable Long appId, @RequestBody AppInfo appInfo) {
        appInfo.setId(appId);
        appInfoService.saveOrUpdate(appInfo);
        // 验证缓存是否被删除
        String cacheKey = "game:detail:" + appId;
        String cacheKeyWithVersion = "game:detail:" + appId + ":with_version";
        boolean hasCache = redisUtils.hasKey(cacheKey);
        boolean hasCacheWithVersion = redisUtils.hasKey(cacheKeyWithVersion);
        HashMap<String, Object> result = new HashMap<>();
        result.put("hasCache", hasCache);
        result.put("hasCacheWithVersion", hasCacheWithVersion);
        return ResultVo.success("更新成功", result);
    }
}
