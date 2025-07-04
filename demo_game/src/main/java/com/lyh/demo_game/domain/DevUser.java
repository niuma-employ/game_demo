package com.lyh.demo_game.domain;

import java.util.Date;
import lombok.Data;

/**
 * @TableName dev_user
 */
@Data
public class DevUser {
    private Long id;

    private String devcode;

    private String devname;

    private String devpassword;

    private String devemail;

    private String devinfo;

    private Long createdby;

    private Date creationdate;

    private Long modifyby;

    private Date modifydate;
}