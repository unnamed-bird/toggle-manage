package com.haiyu.manager.controller;

import com.haiyu.manager.pojo.SwApp;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.SwAppService;
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
//@RequestMapping("/toggle")
public class SwAppController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SwAppService swAppService;

    @RequestMapping("/appManage")
    public String toPage() {
        logger.info("进入用用管理");
        return "appManage";
    }

    /*
     * 应用查询
     *appName参数默认值为空，查询列表所有数据
     * */
    @RequestMapping(value = "/getSwAppList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getSwAppList(@RequestParam("pageNum") Integer pageNum,
                                               @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "appName",defaultValue = "") final String appName) {
        logger.info("分页查询应用列表！搜索条件：app名字：" + appName + ",pageNum:" +pageNum
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
            pdr = swAppService.getSwAppList(appName, pageNum ,pageSize);
            logger.info("应用列表查询=pdr:" + pdr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("开关规则列表查询异常！", e);
        }
        return pdr;
    }

    /**
     *
     * @param swApp
     * @return
     */
    @RequestMapping(value = "/setSwApp", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> setSwApp(SwApp swApp) {
        logger.info("设置应用[新增或更新]！应用信息:" + swApp);
        Map<String,Object> data = new HashMap();
        if(swApp.getId() == null){
            logger.info("调用新增服务");
            data = swAppService.addApp(swApp);
        }else{
            logger.info("调用更新服务");
            data = swAppService.updateSwApp(swApp);
        }
        return data;
    }

    /**
     *
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value = "/delApp", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delApp(@RequestParam("id") Integer id,@RequestParam("appName") String name) {
        logger.info("删除应用！ id:" + id +"  name:" + name);
        Map<String, Object> data = new HashMap<>();
        data = swAppService.del(id);
        return data;
    }
}
