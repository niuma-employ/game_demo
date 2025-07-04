package com.lyh.demo_game.service;

import com.lyh.demo_game.domain.AppCategory;
import com.lyh.demo_game.domain.AppInfo;
import com.lyh.demo_game.mapper.AppCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用分类相关业务逻辑服务类
 */
@Service
public class AppCategoryService {
    // 注入应用分类数据访问对象
    @Autowired
    AppCategoryMapper appCategoryMapper;

    /**
     * 获取应用分类树结构
     * @return 分类树根节点
     */
    public AppCategory getTree() {
        List<AppCategory> list = appCategoryMapper.selectAll();
        AppCategory tree = new AppCategory();
        for(AppCategory appCategory: list){
            if (appCategory.getParentid() == null) {
                tree = findChildren(appCategory,list);
            }
        }
        return tree;
    }

    /**
     * 递归查找并设置子分类
     * @param appCategory 当前分类节点
     * @param list 所有分类列表
     * @return 当前节点（含子节点）
     */
    private AppCategory findChildren(AppCategory appCategory, List<AppCategory> list) {
        appCategory.setChildren(new ArrayList<>());
        for (AppCategory category : list){
            if(category.getParentid() == appCategory.getId()){
                appCategory.getChildren().add(category);
                findChildren(category,list);
            }
        }
        return appCategory;
    }
}
