package com.lyh.demo_game.mapper;

import com.lyh.demo_game.domain.DevUser;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
* @author Lang
* @description 针对表【dev_user】的数据库操作Mapper
* @createDate 2025-05-18 11:26:11
* @Entity generator.domain.DevUser
*/
@Mapper
public interface DevUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DevUser record);

    int insertSelective(DevUser record);

    DevUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DevUser record);

    int updateByPrimaryKey(DevUser record);

    DevUser selectByUserName(String username);

    List<DevUser> selectAll();
}
