package com.lyh.demo_game.mapper;

import com.lyh.demo_game.domain.AppVersion;

import java.util.List;

/**
* @author Lang
* @description 针对表【app_version】的数据库操作Mapper
* @createDate 2025-05-18 11:26:11
* @Entity generator.domain.AppVersion
*/
public interface AppVersionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AppVersion record);

    int insertSelective(AppVersion record);

    AppVersion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateByPrimaryKey(AppVersion record);

    List<AppVersion> selectByAppId(Long appid);
}
