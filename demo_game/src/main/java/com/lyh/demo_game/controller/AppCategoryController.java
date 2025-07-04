package com.lyh.demo_game.controller;

import com.lyh.demo_game.domain.AppCategory;
import com.lyh.demo_game.service.AppCategoryService;
import com.lyh.demo_game.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用分类相关接口控制器
 */
@RestController
@RequestMapping("/appCateory")
public class AppCategoryController {
    // 注入应用分类服务
    @Autowired
    AppCategoryService appCategoryService;

    /**
     * 获取应用分类树结构
     * @return 分类树
     */
    @GetMapping("/tree")
    public ResultVo getTree(){
        AppCategory tree = appCategoryService.getTree();
        return ResultVo.success("查询成功",tree);
    }
}
