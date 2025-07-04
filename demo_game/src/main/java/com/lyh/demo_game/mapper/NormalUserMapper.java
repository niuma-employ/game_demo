package com.lyh.demo_game.mapper;

import com.lyh.demo_game.domain.NormalUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface NormalUserMapper {
    int insert(NormalUser record);
    int insertSelective(NormalUser record);
    NormalUser selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(NormalUser record);
    int updateByPrimaryKey(NormalUser record);
    NormalUser selectByUserCode(@Param("userCode") String userCode);
    List<NormalUser> selectAll();
} 