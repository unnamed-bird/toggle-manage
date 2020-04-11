package com.haiyu.manager.dao;

import com.haiyu.manager.dto.SwSwitchSearchDTO;
import com.haiyu.manager.pojo.SwSwitch;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
//此处继承有何作用
public interface SwSwitchMapper extends MyMapper<SwSwitch> {

    List<SwSwitch> getSwSwitchList(SwSwitchSearchDTO swSwitchSearchDTO);
    int updateSwSwitch(SwSwitch swSwitch);
}
