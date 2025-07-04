package com.lyh.demo_game.mapper;

import com.lyh.demo_game.domain.BackendUser;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
* @author Lang
* @description 针对表【backend_user】的数据库操作Mapper
* @createDate 2025-05-18 11:26:11
* @Entity generator.domain.BackendUser
*/
@Mapper
public interface BackendUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(BackendUser record);

    int insertSelective(BackendUser record);

    BackendUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BackendUser record);

    int updateByPrimaryKey(BackendUser record);

    BackendUser selectByUserName(String username);

    List<BackendUser> selectAll();
}
