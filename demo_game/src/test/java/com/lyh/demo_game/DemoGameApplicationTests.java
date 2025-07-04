package com.lyh.demo_game;


import com.lyh.demo_game.domain.AppCategory;
import com.lyh.demo_game.mapper.AppCategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoGameApplicationTests {

    @Autowired
    AppCategoryMapper appCategoryMapper;


    @Test
    void contextLoads() {
        AppCategory appCategory = appCategoryMapper.selectByPrimaryKey(2L);
        System.out.println(appCategory);
    }

}
