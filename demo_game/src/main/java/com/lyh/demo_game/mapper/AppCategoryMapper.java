package com.lyh.demo_game.mapper;

import com.lyh.demo_game.domain.AppCategory;

import java.util.List;

/**
* @author Lang
* @description 针对表【app_category(手游类别)】的数据库操作Mapper
* @createDate 2025-05-18 11:26:11
* @Entity generator.domain.AppCategory
*/
public interface AppCategoryMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AppCategory record);

    int insertSelective(AppCategory record);

    AppCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppCategory record);

    int updateByPrimaryKey(AppCategory record);

    List<AppCategory> selectAll();
}
