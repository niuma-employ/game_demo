package com.lyh.demo_game.service;

import com.lyh.demo_game.domain.NormalUser;
import com.lyh.demo_game.mapper.NormalUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 普通用户相关业务逻辑服务类
 */
@Service
public class NormalUserService {
    // 注入普通用户数据访问对象
    @Autowired
    private NormalUserMapper normalUserMapper;

    /**
     * 普通用户注册
     * @param user 用户信息
     * @return 注册是否成功（true=成功，false=用户已存在）
     */
    public boolean register(NormalUser user) {
        if (normalUserMapper.selectByUserCode(user.getUserCode()) != null) {
            return false; // 用户已存在
        }
        normalUserMapper.insertSelective(user);
        return true;
    }

    /**
     * 普通用户登录
     * @param userCode 用户账号
     * @param password 密码
     * @return 登录成功返回用户对象，否则返回null
     */
    public NormalUser login(String userCode, String password) {
        NormalUser user = normalUserMapper.selectByUserCode(userCode);
        if (user != null && user.getUserPassword().equals(password)) {
            return user;
        }
        return null;
    }
} 