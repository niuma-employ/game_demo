package com.lyh.demo_game.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @TableName app_category
 */
@Data
public class AppCategory {
    private Long id;

    private String categorycode;

    private String categoryname;

    private Long parentid;

    private Long createdby;

    private Date creationtime;

    private Long modifyby;

    private Date modifydate;

    private static final long serialVersionUID = 1L;

    private List<AppCategory> children;
}