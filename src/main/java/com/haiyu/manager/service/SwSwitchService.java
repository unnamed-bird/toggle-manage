package com.haiyu.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haiyu.manager.dao.SwSwitchMapper;
import com.haiyu.manager.dao.SwSwitchRulesMapper;
import com.haiyu.manager.dto.SwSwitchSearchDTO;
import com.haiyu.manager.pojo.SwSwitchRules;
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
public class SwSwitchService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SwSwitchMapper swSwitchMapper;
    @Autowired
    private SwSwitchRulesMapper swSwitchRulesMapper;

    public PageDataResult getSwSwitchList(SwSwitchSearchDTO swSwitchSearchDTO,Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<SwSwitch> swSwitches = swSwitchMapper.getSwSwitchList(swSwitchSearchDTO);

        logger.info("Service层开关查询结果： "+swSwitches);
        PageHelper.startPage(pageNum, pageSize);

        if(swSwitches.size() != 0){
            PageInfo<SwSwitch> pageInfo = new PageInfo<>(swSwitches);
            pageDataResult.setList(swSwitches);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }

        return pageDataResult;
    }

    public PageDataResult getSwSwitchRulesList(Integer switchId,Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        //List<SwSwitchRules> swSwitcheRules = swSwitchRulesMapper.selectByExample(switchId);
        List<SwSwitchRules> swSwitcheRules = swSwitchRulesMapper.getSwSwitchRulesList(switchId);

        logger.info("Service层开关规则查询结果： "+swSwitcheRules);
        PageHelper.startPage(pageNum, pageSize);

        if(swSwitcheRules.size() != 0){
            PageInfo<SwSwitchRules> pageInfo = new PageInfo<>(swSwitcheRules);
            pageDataResult.setList(swSwitcheRules);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }

        return pageDataResult;
    }


    public Map<String,Object> addSwSwitch(SwSwitch swSwitch) {
        Map<String,Object> data = new HashMap();
        try {
            int result = swSwitchMapper.insert(swSwitch);
            if (result == 0) {
                data.put("code", 0);
                data.put("msg", "新增失败！");
                logger.error("开关[新增]，结果=新增失败！");
                return data;
            }
            data.put("code",1);
            data.put("msg","新增成功！");
            logger.info("用户[新增]，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("用户[新增]异常！", e);
            return data;
        }
        return data;
    }

    public Map<String,Object> addSwSwitchRule(SwSwitchRules swSwitchRules) {
        Map<String,Object> data = new HashMap();
        try {
            int result = swSwitchRulesMapper.insert(swSwitchRules);
            if (result == 0) {
                data.put("code", 0);
                data.put("msg", "新增失败！");
                logger.error("开关规则[新增]，结果=新增失败！");
                return data;
            }
            data.put("code",1);
            data.put("msg","新增成功！");
            logger.info("开关规则[新增]，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("开关规则[新增]异常！", e);
            return data;
        }
        return data;
    }

    public Map <String, Object> updateSwSwitch(SwSwitch swSwitch) {
        Map<String,Object> data = new HashMap();
        //此处要有开关名字重复判定
       /* Integer id = swSwitch.getId();
        BaseAdminUser old = swSwitchMapper.getUserByUserName(user.getSysUserName(),id);
        if(old != null){
            data.put("code",0);
            data.put("msg","用户名已存在！");
            logger.error("用户[更新]，结果=用户名已存在！");
            return data;
        }
        String username = swSwitch.getSysUserName();
        if(swSwitch.getSysUserPwd() != null){
            String password = DigestUtils.Md5(username,swSwitch.getSysUserPwd());
            swSwitch.setSysUserPwd(password);
        }*/

        logger.info("调用Mapper层");
        int result =  swSwitchMapper.updateByPrimaryKey(swSwitch);
        //自定义的查询由于sql语句有问题，放弃使用。自定义方法（mapper层）暂留，sql语句问题参考xml配置文件
       // int result = swSwitchMapper.updateSwSwitch(swSwitch);
        if(result == 0){
            data.put("code",0);
            data.put("msg","更新失败！");
            logger.error("开关[更新]，结果=更新失败！");
            return data;
        }
        data.put("code",1);
        data.put("msg","更新成功！");
        logger.info("开关[更新]，结果=更新成功！");
        return data;
    }

    public Map <String, Object> updateSwSwitchRule(SwSwitchRules swSwitchRules) {
        Map<String,Object> data = new HashMap();
        //此处要有开关名字重复判定
       /* Integer id = swSwitch.getId();
        BaseAdminUser old = swSwitchMapper.getUserByUserName(user.getSysUserName(),id);
        if(old != null){
            data.put("code",0);
            data.put("msg","用户名已存在！");
            logger.error("用户[更新]，结果=用户名已存在！");
            return data;
        }
        String username = swSwitch.getSysUserName();
        if(swSwitch.getSysUserPwd() != null){
            String password = DigestUtils.Md5(username,swSwitch.getSysUserPwd());
            swSwitch.setSysUserPwd(password);
        }*/

        logger.info("调用Mapper层");
        int result =  swSwitchRulesMapper.updateByPrimaryKey(swSwitchRules);
        //自定义的查询由于sql语句有问题，放弃使用。自定义方法（mapper层）暂留，sql语句问题参考xml配置文件
        // int result = swSwitchMapper.updateSwSwitch(swSwitch);
        if(result == 0){
            data.put("code",0);
            data.put("msg","更新失败！");
            logger.error("开关规则[更新]，结果=更新失败！");
            return data;
        }
        data.put("code",1);
        data.put("msg","更新成功！");
        logger.info("开关规则[更新]，结果=更新成功！");
        return data;
    }

    public Map <String, Object> del(Integer id) {
        Map<String, Object> data = new HashMap<>();
        try {
            // 删除用户
            int result = swSwitchMapper.deleteByPrimaryKey(id);
            if(result == 0){
                data.put("code",0);
                data.put("msg","删除开关失败");
                logger.error("删除开关失败");
                return data;
            }
            data.put("code",1);
            data.put("msg","删除开关成功");
            logger.info("删除开关成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除开关异常！", e);
        }
        return data;
    }

    public Map <String, Object> delRule(Integer id) {
        Map<String, Object> data = new HashMap<>();
        try {
            // 删除开关规则
            int result = swSwitchRulesMapper.deleteByPrimaryKey(id);
            if(result == 0){
                data.put("code",0);
                data.put("msg","删除开关规则失败");
                logger.error("删除开关规则失败");
                return data;
            }
            data.put("code",1);
            data.put("msg","删除开关规则成功");
            logger.info("删除开关规则成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除开关规则异常！", e);
        }
        return data;
    }
}
