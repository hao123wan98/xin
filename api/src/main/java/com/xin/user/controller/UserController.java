/**
 * 2015-2016 龙果学院 (www.roncoo.com)
 */
package com.xin.user.controller;

import com.xin.db.entity.TLoginUser;
import com.xin.tools.DateUtil;
import com.xin.tools.ServletUtils;
import com.xin.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/user")
class UserController {
    @Autowired
    UserService userService;


    // @RequestParam 简单类型的绑定，可以出来get和post
    @RequestMapping(value = "/get")
    public void get(HttpServletRequest req, HttpServletResponse res, Long userId) {
        TLoginUser user = userService.get(userId);
        ServletUtils.toJson(user, req, res);
    }


    @RequestMapping(value = "set")
    public void set(HttpServletRequest req, HttpServletResponse res, TLoginUser user) {
        userService.set(user);
        ServletUtils.toJson(req, res);
    }


}
