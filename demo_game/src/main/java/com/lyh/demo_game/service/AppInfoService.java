package com.lyh.demo_game.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.demo_game.domain.AppInfo;
import com.lyh.demo_game.domain.AppVersion;
import com.lyh.demo_game.domain.AppCollection;
import com.lyh.demo_game.mapper.AppInfoMapper;
import com.lyh.demo_game.mapper.AppVersionMapper;
import com.lyh.demo_game.mapper.AppCollectionMapper;
import com.lyh.demo_game.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * AppInfoService 负责应用信息的业务逻辑，包括增删改查、缓存、收藏等功能
 */
@Service
public class AppInfoService {
    // 注入AppInfo数据访问对象
    @Autowired
    AppInfoMapper appInfoMapper;

    // 注入AppVersion数据访问对象
    @Autowired
    AppVersionMapper appVersionMapper;

    // 注入AppCollection数据访问对象
    @Autowired
    AppCollectionMapper appCollectionMapper;

    // 注入Redis工具类，用于操作Redis缓存
    @Autowired
    RedisUtils redisUtils;

    // Redis缓存key前缀
    private static final String GAME_DETAIL_KEY_PREFIX = "game:detail:";
    // 缓存过期时间（小时）
    private static final long GAME_DETAIL_EXPIRE_TIME = 1;

    /**
     * 分页查询应用信息
     * @param appInfo 查询条件
     * @param pageNum 页码
     * @param userType 用户类型
     * @param userId 用户ID
     * @return 分页结果
     */
    public PageInfo getPage(AppInfo appInfo, Integer pageNum, String userType, Long userId) {
        PageHelper.startPage(pageNum,5,"id desc");
        List<AppInfo> list;
        if ("dev".equals(userType)) {
            list = appInfoMapper.selectByDevId(userId);
        } else if ("user".equals(userType)) {
            list = appInfoMapper.selectOnShelf();
        } else if ("admin".equals(userType)) {
            list = appInfoMapper.selectAllWithDevName();
        } else {
            list = appInfoMapper.seletBy(appInfo);
        }
        if(pageNum<=0){
            throw new RuntimeException("当前页不能小于等于0");
        }
        return new PageInfo(list);
    }

    /**
     * 新增或修改应用信息，自动处理缓存
     * @param appInfo 应用信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(AppInfo appInfo) {
        if(appInfo.getId()==null){
            appInfo.setCreationdate(new Date());
            appInfo.setDownloads(0L);
            appInfo.setStatus(1L);//待审核状态
            appInfoMapper.insert(appInfo);
        } else {
            appInfo.setModifydate(new Date());
            appInfoMapper.updateByPrimaryKeySelective(appInfo);
            // 更新或删除数据时，需同步删除Redis缓存，保证缓存与数据库一致
            String cacheKey = GAME_DETAIL_KEY_PREFIX + appInfo.getId();
            String cacheKeyWithVersion = GAME_DETAIL_KEY_PREFIX + appInfo.getId() + ":with_version";
            redisUtils.delete(cacheKey);
            redisUtils.delete(cacheKeyWithVersion);
        }
    }

    /**
     * 校验apkName是否可用
     * @param apkname 包名
     * @param id 应用ID（可选）
     * @return 是否可用
     */
    public Boolean validateApkName(String apkname, Long id) {
        //排除特殊情况 修改的时候用户没修改apk名称
        if(id !=null){
            AppInfo appInfo1 = appInfoMapper.selectByPrimaryKey(id);
            if(appInfo1.getApkname().equals(apkname)){
                return true;
            }
        }
        //普通情况
        AppInfo appInfo = new AppInfo();
        appInfo.setApkname(apkname);
        List<AppInfo> list = appInfoMapper.seletBy(appInfo);
        if(list == null || list.size()==0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 根据ID获取应用信息，带缓存
     * @param id 应用ID
     * @return 应用信息
     */
    public AppInfo getById(Long id) {
        // 先从Redis缓存中获取，提升查询效率，减轻数据库压力
        String cacheKey = GAME_DETAIL_KEY_PREFIX + id;
        Object cachedApp = redisUtils.get(cacheKey);
        if (cachedApp != null) {
            return (AppInfo) cachedApp;
        }
        // 缓存未命中，从数据库查询
        AppInfo appInfo = appInfoMapper.selectByPrimaryKey(id);
        // 将查询结果存入Redis缓存，设置过期时间
        if (appInfo != null) {
            redisUtils.set(cacheKey, appInfo, GAME_DETAIL_EXPIRE_TIME, TimeUnit.HOURS);
        }
        return appInfo;
    }

    /**
     * 获取应用及其所有版本信息，带缓存
     * @param appid 应用ID
     * @return 应用信息（含版本）
     */
    public AppInfo getAppWithVersion(Long appid) {
        // 先从Redis缓存中获取，提升查询效率，减轻数据库压力
        String cacheKey = GAME_DETAIL_KEY_PREFIX + appid + ":with_version";
        Object cachedApp = redisUtils.get(cacheKey);
        if (cachedApp != null)  {
            return (AppInfo) cachedApp;
        }
        // 缓存未命中，从数据库查询
        AppInfo appInfo = appInfoMapper.selectByPrimaryKey(appid);
        if (appInfo != null) {
            appInfo.setVersions(appVersionMapper.selectByAppId(appid));
            // 将查询结果存入Redis缓存，设置过期时间
            redisUtils.set(cacheKey, appInfo, GAME_DETAIL_EXPIRE_TIME, TimeUnit.HOURS);
        }
        return appInfo;
    }

    /**
     * 删除应用及相关资源，自动清理缓存
     * @param id 应用ID
     * @param uploadPath 上传路径
     */
    @Transactional(rollbackFor = Exception.class)
    public void del(Long id, String uploadPath) {
        // 删除缓存，保证缓存与数据库一致
        String cacheKey = GAME_DETAIL_KEY_PREFIX + id;
        String cacheKeyWithVersion = GAME_DETAIL_KEY_PREFIX + id + ":with_version";
        redisUtils.delete(cacheKey);
        redisUtils.delete(cacheKeyWithVersion);

        List<AppVersion> appVersions = appVersionMapper.selectByAppId(id);
        for (AppVersion version : appVersions) {
            if(version.getDownloadlink()!= null){
                File file = new File(uploadPath+version.getDownloadlink());
                if(file.exists()){
                    file.delete();
                }
                appVersionMapper.deleteByPrimaryKey(version.getId());
            }
        }

        AppInfo appInfo = appInfoMapper.selectByPrimaryKey(id);
        if(appInfo.getLogopicpath() != null){
            File logo= new File(uploadPath+appInfo.getLogopicpath());
            logo.delete();
        }
        appInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 收藏应用
     * @param userId 用户ID
     * @param appId 应用ID
     */
    public void collect(Long userId, Long appId) {
        if (appCollectionMapper.selectByUserIdAndAppId(userId, appId) == null) {
            AppCollection c = new AppCollection();
            c.setUserId(userId);
            c.setAppId(appId);
            appCollectionMapper.insert(c);
        }
    }

    /**
     * 取消收藏
     * @param userId 用户ID
     * @param appId 应用ID
     */
    public void uncollect(Long userId, Long appId) {
        appCollectionMapper.deleteByUserIdAndAppId(userId, appId);
    }

    /**
     * 获取我的收藏列表
     * @param userId 用户ID
     * @return 收藏的应用列表
     */
    public List<AppInfo> myCollections(Long userId) {
        List<AppCollection> collections = appCollectionMapper.selectByUserId(userId);
        List<AppInfo> result = new ArrayList<>();
        for (AppCollection c : collections) {
            AppInfo app = appInfoMapper.selectByPrimaryKey(c.getAppId());
            System.out.println("查appId=" + c.getAppId() + " 结果=" + app);
            if (app != null) {
                result.add(app);
            }
        }
        return result;
    }

    /**
     * 上架应用
     * @param appId 应用ID
     */
    public void publish(Long appId) {
        AppInfo app = appInfoMapper.selectByPrimaryKey(appId);
        app.setStatus(4L); // 4=已上架
        appInfoMapper.updateByPrimaryKeySelective(app);
    }

    /**
     * 审核应用
     * @param appId 应用ID
     * @param status 审核状态
     */
    public void audit(Long appId, Long status) {
        AppInfo app = appInfoMapper.selectByPrimaryKey(appId);
        app.setStatus(status); // 2=审核通过, 3=审核不通过
        appInfoMapper.updateByPrimaryKeySelective(app);
    }
}
