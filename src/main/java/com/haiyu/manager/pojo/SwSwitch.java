package com.haiyu.manager.pojo;

import javax.persistence.*;
import java.util.Date;

public class SwSwitch {
    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    //应用主键
    @Column(name = "app_id")
    private Integer appId ;

    //开关名
    @Column(name = "name")
    private String name ;

    //开关唯一键，在应用的namespace 唯一
    @Column(name = "name_key")
    private String nameKey ;

    //开关描述

    @Column(name = "name_desc")
    private String nameDesc ;

    //负责人邮箱地址

    @Column(name = "owner_email")
    private String ownerEmail ;

    //-1: deleted, 1: normal

    @Column(name = "status")
    private Integer status ;

    //1:普通开关（true、false） ，2: 正则匹配 ，3：灰度类型，4：百分比类型

    @Column(name = "type")
    private Integer type ;

    //创建时间
    @Column(name = "createtime")
    private String createtime ;

    //更新时间

    @Column(name = "updatetime")
    private String updatetime ;
/*
    @Column(name = "creattime")
    private Date createtime ;

    //更新时间

    @Column(name = "updatetime")
    private Date updatetime ;*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public String getNameDesc() {
        return nameDesc;
    }

    public void setNameDesc(String nameDesc) {
        this.nameDesc = nameDesc;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
/*
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }*/

    @Override
    public String toString() {
        return "SwSwitch{" +
                "id=" + id +
                ", appId='" + appId + '\''+
                ", name='" + name + '\''+
                ", nameKey='" + nameKey + '\''+
                ", nameDesc='" + nameDesc + '\''+
                ", ownerEmail='" + ownerEmail + '\''+
                ", status='" + status + '\''+
                ", type='" + type + '\''+
                ", createtime='" + createtime + '\''+
                ", updatetime='" + updatetime + '\''+
                '}';
    }
}
