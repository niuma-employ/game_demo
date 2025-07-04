package com.lyh.demo_game.controller;

import com.lyh.demo_game.domain.NormalUser;
import com.lyh.demo_game.service.NormalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 普通用户相关接口控制器，提供注册和登录功能
 */
@RestController
@RequestMapping("/normalUser")
public class NormalUserController {
    // 注入普通用户服务
    @Autowired
    private NormalUserService normalUserService;

    /**
     * 普通用户注册接口
     * @param user 用户信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public String register(@RequestBody NormalUser user) {
        boolean success = normalUserService.register(user);
        return success ? "success" : "用户已存在";
    }

    /**
     * 普通用户登录接口
     * @param userCode 用户账号
     * @param password 密码
     * @return 登录结果（用户信息）
     */
    @PostMapping("/login")
    public NormalUser login(@RequestParam String userCode, @RequestParam String password) {
        return normalUserService.login(userCode, password);
    }
} 