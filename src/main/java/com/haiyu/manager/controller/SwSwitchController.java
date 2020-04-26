package com.haiyu.manager.controller;

import com.haiyu.manager.dto.SwSwitchSearchDTO;
import com.haiyu.manager.pojo.SwSwitch;
import com.haiyu.manager.pojo.SwSwitchRules;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.SwSwitchService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/toggle")
public class SwSwitchController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SwSwitchService swSwitchService;

    @RequestMapping("/toggleManage")
    public String toPage() {
        logger.info("进入开关管理");
        return "toggleManage";
    }
    @RequestMapping("/rulesManage")
    public String toRules() {
        logger.info("进入开关规则管理");
        return "RulesManage";
    }

    //获取开关列表
    @RequestMapping(value = "/getSwSwitchList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getSwSwitchList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize,/*@Valid PageRequest page,*/ SwSwitchSearchDTO swSwitchSearchDTO) {
        logger.info("分页查询开关列表！搜索条件：Search：" + swSwitchSearchDTO + ",pageNum:" +pageNum
                + ",每页记录数量pageSize:" +pageSize);
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取用户列表
            pdr = swSwitchService.getSwSwitchList(swSwitchSearchDTO, pageNum ,pageSize);
            logger.info("开关列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("开关列表查询异常！", e);
        }
        return pdr;
    }


    //新增和更新开关
    @RequestMapping(value = "/setSwSwitch", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> setSwSwitch(SwSwitch swSwitch) {
        logger.info("设置开关[新增或更新]！开关信息:" + swSwitch);
        Map<String,Object> data = new HashMap();
        if(swSwitch.getId() == null){
            logger.info("调用新增服务");
            data = swSwitchService.addSwSwitch(swSwitch);
        }else{
            logger.info("调用更新服务");
            data = swSwitchService.updateSwSwitch(swSwitch);
        }
        return data;
    }

    /*
    * 新增和更新开关规则
    * */
    @RequestMapping(value = "/setSwSwitchRule", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> setSwSwitchRule(SwSwitchRules swSwitchRules) {
        logger.info("设置开关规则[新增或更新]！开关规则信息:" + swSwitchRules);
        Map<String,Object> data = new HashMap();
        if(swSwitchRules.getId() == null){
            logger.info("调用新增服务");
            data = swSwitchService.addSwSwitchRule(swSwitchRules);
        }else{
            logger.info("调用更新服务");
            data = swSwitchService.updateSwSwitchRule(swSwitchRules);
        }
        return data;
    }


    //删除
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> del(@RequestParam("id") Integer id,@RequestParam("name") String name) {
        logger.info("删除开关！ id:" + id +"  name:" + name);
        Map<String, Object> data = new HashMap<>();
        data = swSwitchService.del(id);
        return data;
    }

    /*
    * 删除开关规则
    * */
    @RequestMapping(value = "/delRule", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delRule(@RequestParam("id") Integer id) {
        logger.info("删除开关规则！ id:" + id);
        Map<String, Object> data = new HashMap<>();
        data = swSwitchService.delRule(id);
        return data;
    }


    /*
    * 开关规则查询
    * 默认查询开关id为0，此查询条件可查询所有列表
    * */
    @RequestMapping(value = "/getSwSwitchRulesList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getSwSwitchRulesList(@RequestParam("pageNum") Integer pageNum,
                                          @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "switchSearchId",defaultValue = "0") final Integer switchId) {
        logger.info("分页查询开关规则列表！搜索条件：Search开关序号：" + switchId + ",pageNum:" +pageNum
                + ",每页记录数量pageSize:" +pageSize);
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取用户列表
            pdr = swSwitchService.getSwSwitchRulesList(switchId, pageNum ,pageSize);
            logger.info("开关规则列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("开关规则列表查询异常！", e);
        }
        return pdr;
    }

}
