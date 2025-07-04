package com.lyh.demo_game.controller;

import com.lyh.demo_game.domain.AppInfo;
import com.lyh.demo_game.domain.AppVersion;
import com.lyh.demo_game.service.AppVersionService;
import com.lyh.demo_game.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * AppVersionController 负责处理应用版本相关的上传和保存接口
 */
@RestController
@RequestMapping("/version")
public class AppVersionController {
    // 上传文件路径
    @Value("${upload.path}")
    String uploadPath;

    // 注入AppVersionService服务
    @Autowired
    AppVersionService appVersionServiec;

    /**
     * 上传并保存应用版本信息
     * @param appVersion 版本信息
     * @param apkFile apk安装包文件
     * @return 操作结果
     */
    @PostMapping
    public ResultVo add(AppVersion appVersion, MultipartFile apkFile){
        if(apkFile!=null && !apkFile.isEmpty()){
            //说明前端传了文件
            //获取上传文件的原始名称
            String originalFilename = apkFile.getOriginalFilename();
            //提取文件的扩展名。
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            //不超过500M
            if(apkFile.getSize() > 1024*1024*500){
                return ResultVo.error("文件过大, 请上传不要超过500MB");
            } else if(
                suffix.equalsIgnoreCase(".apk")
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
                    apkFile.transferTo(saveFile);
                    //文件已经上传成功
                    appVersion.setDownloadlink(newName+suffix);
                    //保存到数据库
                    appVersion.setCreationdate(new Date());
                    appVersion.setPublishstatus(3L);
                    appVersionServiec.save(appVersion);
                    return ResultVo.success("保存成功",null);
                } catch (IOException e) {
                    throw new RuntimeException("文件上传异常");
                }
            }else{
                return ResultVo.error("文件格式不对，必须是 apk");
            }
        }else {
            return ResultVo.error("apk文件必须上传");
        }
    }
}
