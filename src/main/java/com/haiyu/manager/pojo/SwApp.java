package com.haiyu.manager.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SwApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(name = "app_name")
    private String appName ;

    @Column(name = "app_namespace")
    private String appNameSpace ;

    @Column(name = "app_desc")
    private String appDesc ;

    @Column(name = "owner_name")
    private String ownerName ;

    //负责人邮箱地址

    @Column(name = "owner_email")
    private String ownerEmail ;

    //-1: deleted, 1: normal

    @Column(name = "status")
    private Integer status ;

    //创建时间
    @Column(name = "createtime")
    private String createtime ;

    //更新时间

    @Column(name = "updatetime")
    private String updatetime ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppNameSpace() {
        return appNameSpace;
    }

    public void setAppNameSpace(String appNameSpace) {
        this.appNameSpace = appNameSpace;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    @Override
    public String toString() {
        return "SwApp{" +
                "id=" + id +
                ", appName='" + appName + '\''+
                ", appNameSpace='" + appNameSpace + '\''+
                ", appDesc='" + appDesc + '\''+
                ", ownerName='" + ownerName + '\''+
                ", ownerEmail='" + ownerEmail + '\''+
                ", status='" + status + '\''+
                ", createtime='" + createtime + '\''+
                ", updatetime='" + updatetime + '\''+
                '}';
    }
}
