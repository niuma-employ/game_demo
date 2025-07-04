package com.lyh.demo_game.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName app_version
 */
@Data
public class AppVersion {
    private Long id;

    private Long appid;

    private String versionno;

    private String versioninfo;

    private Long publishstatus;

    private String downloadlink;

    private BigDecimal versionsize;

    private Long createdby;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationdate;

    private Long modifyby;

    private Date modifydate;

    private String apklocpath;

    private String apkfilename;


}