package com.haiyu.manager.pojo;

import javax.persistence.*;


public class SwSwitchRules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "app_id")
    private Integer appId;
    @Column(name = "switch_id")
    private Integer switchId;
    private String rule;
    private Integer sort;
    private Integer status;
    private String createtime ;
    private String updatetime ;

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

    public Integer getSwitchId() {
        return switchId;
    }

    public void setSwitchId(Integer switchId) {
        this.switchId = switchId;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
        return "SwSwitch{" +
                "id=" + id +
                ", appId='" + appId + '\''+
                ", switchId='" + switchId + '\''+
                ", rule='" + rule + '\''+
                ", sort='" + sort + '\''+
                ", status='" + status + '\''+
                ", createtime='" + createtime + '\''+
                ", updatetime='" + updatetime + '\''+
                '}';
    }
}
