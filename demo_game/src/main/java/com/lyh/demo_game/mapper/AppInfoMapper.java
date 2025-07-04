package com.lyh.demo_game.mapper;

import com.lyh.demo_game.domain.AppInfo;

import java.util.List;

/**
* @author Lang
* @description 针对表【app_info】的数据库操作Mapper
* @createDate 2025-05-18 11:26:11
* @Entity generator.domain.AppInfo
*/
public interface AppInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AppInfo record);

    int insertSelective(AppInfo record);

    AppInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppInfo record);

    int updateByPrimaryKey(AppInfo record);

    List<AppInfo> seletBy(AppInfo appInfo);

    List<AppInfo> selectByDevId(Long devId);

    List<AppInfo> selectOnShelf();

    List<AppInfo> selectAllWithDevName();
}
