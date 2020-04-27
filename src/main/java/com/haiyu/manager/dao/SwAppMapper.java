package com.haiyu.manager.dao;

import com.haiyu.manager.pojo.SwApp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface SwAppMapper extends MyMapper<SwApp> {
    List<SwApp> getSwAppList(@Param("appName") String appName);
}
