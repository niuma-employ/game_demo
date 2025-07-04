package com.lyh.demo_game.controller;

import com.lyh.demo_game.dto.LoginDto;
import com.lyh.demo_game.service.LoginService;
import com.lyh.demo_game.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器，提供登录接口
 */
@RestController
public class LoginController {
    // 注入登录服务
    @Autowired
    LoginService loginService;

    /**
     * 用户登录接口
     * @param loginDto 登录参数
     * @return 登录结果（token）
     */
    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginDto loginDto){
        String token = loginService.login(loginDto);
        return ResultVo.success("登录成功",token);
    }
}
