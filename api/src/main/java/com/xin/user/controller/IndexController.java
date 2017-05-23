/**
 * 2015-2016 龙果学院 (www.roncoo.com)
 */
package com.xin.user.controller;

import com.xin.tools.DateUtil;
import com.xin.tools.ServletUtils;
import com.xin.tools.common.ParameterException;
import com.xin.user.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/index")
public class IndexController {
    @Autowired
    IndexService indexService;

    @RequestMapping
    public void index(HttpServletRequest req, HttpServletResponse res) {
        String tmp = DateUtil.date2FullStr(new Date());
        //String tmp = "hello world";
        ServletUtils.strToJson(tmp, req, res);
    }

    // @RequestParam 简单类型的绑定，可以出来get和post
    @RequestMapping(value = "/get")
    public void get(HttpServletRequest req, HttpServletResponse res) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("title", "hello world");
        map.put("name", "aaa");
        ServletUtils.toJson(map, req, res);
    }

    @RequestMapping(value = "set")
    public void set(HttpServletRequest req, HttpServletResponse res) {
        ServletUtils.toJson(req, res);
    }


}
