package com.lyh.demo_game.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试用控制器，提供简单的测试接口
 */
@RestController
public class TestController {
    /**
     * 测试接口，返回 hello world
     * @return 字符串 hello world
     */
    @GetMapping("/test")
    public String test(){
        return "hello world";
    }
}
