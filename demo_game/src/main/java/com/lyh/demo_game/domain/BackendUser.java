package com.lyh.demo_game.domain;

import java.util.Date;
import lombok.Data;

/**
 * @TableName backend_user
 */
@Data
public class BackendUser {
    private Long id;

    private String usercode;

    private String username;

    private Long usertype;

    private Long createdby;

    private Date creationdate;

    private Long modifyby;

    private Date modifydate;

    private String userpassword;
}