package com.lyh.demo_game.mapper;

import com.lyh.demo_game.domain.DataDictionary;

/**
* @author Lang
* @description 针对表【data_dictionary】的数据库操作Mapper
* @createDate 2025-05-18 11:26:11
* @Entity generator.domain.DataDictionary
*/
public interface DataDictionaryMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DataDictionary record);

    int insertSelective(DataDictionary record);

    DataDictionary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataDictionary record);

    int updateByPrimaryKey(DataDictionary record);

}
