package com.haiyu.manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @author:
 * @version: 1.0
 */
@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping({"home","/"})
    public String home(){
        logger.info("定向主页");
        return "home";
    }

}
