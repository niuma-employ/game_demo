package com.lyh.demo_game.domain;

import java.util.Date;
import lombok.Data;

/**
 * @TableName data_dictionary
 */
@Data
public class DataDictionary {
    private Long id;

    private String typecode;

    private String typename;

    private Long valueid;

    private String valuename;

    private Long createdby;

    private Date creationdate;

    private Long modifyby;

    private Date modifydate;

    private String typeflag;
}