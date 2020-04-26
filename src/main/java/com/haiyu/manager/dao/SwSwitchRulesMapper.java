package com.haiyu.manager.dao;

import com.haiyu.manager.pojo.SwSwitchRules;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface SwSwitchRulesMapper extends MyMapper<SwSwitchRules> {
    List<SwSwitchRules> getSwSwitchRulesList(@Param("switchId") Integer switchId);
}
