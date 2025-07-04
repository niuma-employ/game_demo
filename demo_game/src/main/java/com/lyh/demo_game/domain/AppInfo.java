package com.lyh.demo_game.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @TableName app_info
 */
@Data
public class AppInfo {
    private Long id;

    private String softwarename;

    private String apkname;

    private String supportrom;

    private String interfacelanguage;

    private BigDecimal softwaresize;

    private Date updatedate;

    private Long devid;

    private String appinfo;

    private Long status;

    private Date onsaledate;

    private Date offsaledate;

    private Long flatformid;

    private Long categorylevel3;

    private Long downloads;

    private Long createdby;

    private Date creationdate;

    private Long modifyby;

    private Date modifydate;

    private Long categorylevel1;
    private String level1Name;
    private String level2Name;
    private String level3Name;


    private Long categorylevel2;

    private String logopicpath;

    private String logolocpath;

    private Long versionid;

    private List<AppVersion> versions; //app所有版本
    private static final long serialVersionUID = 1L;
}