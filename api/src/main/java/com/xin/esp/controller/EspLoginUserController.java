/**
 * 2015-2016 龙果学院 (www.roncoo.com)
 */
package com.xin.esp.controller;

import com.xin.common.BaseController;
import com.xin.esp.service.EspLoginUserService;
import com.xin.user.dao.LoginOKVO;
import com.zhenhr.tools.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理后台用户登录
 */
@Controller
@RequestMapping(value = "/muser")
public class EspLoginUserController extends BaseController {
    @Autowired
    EspLoginUserService userService;


    /**
     * 登录
     *
     * @param req
     * @param res
     * @param email
     */
    @RequestMapping(value = "login")
    public void login(HttpServletRequest req, HttpServletResponse res, String email,
                      String pwd) {
        LoginOKVO vo = userService.login(email, pwd);
        ServletUtils.toJson(vo, req, res);
    }


    /**
     * 修改密码
     *
     * @param req
     * @param res
     * @param oldPwd
     * @param newPwd
     */
    @RequestMapping(value = "pwd/change")
    public void changepwd(HttpServletRequest req, HttpServletResponse res, String oldPwd,
                          String newPwd) {
        Long userId = this.getUserId(req);
        LoginOKVO vo = userService.changePwd(userId, oldPwd, newPwd);
        ServletUtils.toJson(vo, req, res);
    }

    /**
     * 登出
     *
     * @param req
     * @param res
     * @param
     */
    @RequestMapping(value = "logout")
    public void logout(HttpServletRequest req, HttpServletResponse res,
                       @RequestHeader("token") String token) {
        userService.logout(token);
        ServletUtils.toJson(req, res);
    }


}
