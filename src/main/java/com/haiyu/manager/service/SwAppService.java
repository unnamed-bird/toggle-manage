package com.haiyu.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haiyu.manager.dao.SwAppMapper;
import com.haiyu.manager.dto.SwSwitchSearchDTO;
import com.haiyu.manager.pojo.SwApp;
import com.haiyu.manager.pojo.SwSwitch;
import com.haiyu.manager.response.PageDataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SwAppService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SwAppMapper swAppMapper;

    public PageDataResult getSwAppList(String appName, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<SwApp> swApps = swAppMapper.getSwAppList(appName);

        logger.info("Service层开关查询结果： "+swApps);
        PageHelper.startPage(pageNum, pageSize);

        if(swApps.size() != 0){
            PageInfo<SwApp> pageInfo = new PageInfo<>(swApps);
            pageDataResult.setList(swApps);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }

        return pageDataResult;
    }

    public Map<String,Object> addApp(SwApp swApp) {
        Map<String,Object> data = new HashMap();
        try {
            int result = swAppMapper.insert(swApp);
            if (result == 0) {
                data.put("code", 0);
                data.put("msg", "新增失败！");
                logger.error("应用[新增]，结果=新增失败！");
                return data;
            }
            data.put("code",1);
            data.put("msg","新增成功！");
            logger.info("应用[新增]，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("应用[新增]异常！", e);
            return data;
        }
        return data;
    }

    public Map <String, Object> updateSwApp(SwApp swApp) {
        Map<String,Object> data = new HashMap();
        int result =  swAppMapper.updateByPrimaryKey(swApp);
        if(result == 0){
            data.put("code",0);
            data.put("msg","更新失败！");
            logger.error("应用[更新]，结果=更新失败！");
            return data;
        }
        data.put("code",1);
        data.put("msg","更新成功！");
        logger.info("应用[更新]，结果=更新成功！");
        return data;
    }

    public Map <String, Object> del(Integer id) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = swAppMapper.deleteByPrimaryKey(id);
            if(result == 0){
                data.put("code",0);
                data.put("msg","删除应用失败");
                logger.error("删除应用失败");
                return data;
            }
            data.put("code",1);
            data.put("msg","删除应用成功");
            logger.info("删除应用成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除应用异常！", e);
        }
        return data;
    }
}
