package com.lyh.demo_game.domain;

import java.util.Date;

public class NormalUser {
    private Long id;
    private String userCode;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userInfo;
    private Long createdBy;
    private Date creationDate;
    private Long modifyBy;
    private Date modifyDate;

    // getter 和 setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUserCode() { return userCode; }
    public void setUserCode(String userCode) { this.userCode = userCode; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getUserPassword() { return userPassword; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public String getUserInfo() { return userInfo; }
    public void setUserInfo(String userInfo) { this.userInfo = userInfo; }
    public Long getCreatedBy() { return createdBy; }
    public void setCreatedBy(Long createdBy) { this.createdBy = createdBy; }
    public Date getCreationDate() { return creationDate; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }
    public Long getModifyBy() { return modifyBy; }
    public void setModifyBy(Long modifyBy) { this.modifyBy = modifyBy; }
    public Date getModifyDate() { return modifyDate; }
    public void setModifyDate(Date modifyDate) { this.modifyDate = modifyDate; }
} 