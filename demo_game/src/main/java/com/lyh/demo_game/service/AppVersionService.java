package com.lyh.demo_game.service;

import com.lyh.demo_game.domain.AppVersion;
import com.lyh.demo_game.mapper.AppVersionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppVersionService {
    @Autowired
    AppVersionMapper appVersionMapper;
    public void save(AppVersion appVersion) {
        appVersionMapper.insert(appVersion);
    }
    public java.util.List<AppVersion> selectByAppId(Long appId) {
        return appVersionMapper.selectByAppId(appId);
    }
}
