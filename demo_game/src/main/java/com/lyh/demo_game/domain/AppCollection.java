package com.lyh.demo_game.domain;

import java.util.Date;

public class AppCollection {
    private Long id;
    private Long userId;
    private Long appId;
    private Date createTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getAppId() { return appId; }
    public void setAppId(Long appId) { this.appId = appId; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
} 