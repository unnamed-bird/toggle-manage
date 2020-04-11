package com.haiyu.manager.controller;

import com.haiyu.manager.dto.SwSwitchSearchDTO;
import com.haiyu.manager.pojo.SwSwitch;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.SwSwitchService;
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


    //删除
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> del(@RequestParam("id") Integer id,@RequestParam("name") String name) {
        logger.info("删除开关！ id:" + id +"  name:" + name);
        Map<String, Object> data = new HashMap<>();
        data = swSwitchService.del(id);
        return data;
    }

}
