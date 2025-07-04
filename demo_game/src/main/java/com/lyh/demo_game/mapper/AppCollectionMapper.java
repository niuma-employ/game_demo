package com.lyh.demo_game.mapper;

import com.lyh.demo_game.domain.AppCollection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppCollectionMapper {
    int insert(AppCollection record);
    int deleteByUserIdAndAppId(@Param("userId") Long userId, @Param("appId") Long appId);
    List<AppCollection> selectByUserId(@Param("userId") Long userId);
    AppCollection selectByUserIdAndAppId(@Param("userId") Long userId, @Param("appId") Long appId);
} 