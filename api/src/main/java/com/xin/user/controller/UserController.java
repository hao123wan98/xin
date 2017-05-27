/**
 * 2015-2016 龙果学院 (www.roncoo.com)
 */
package com.xin.user.controller;

import com.xin.common.BaseController;
import com.xin.db.entity.TLoginUser;
import com.xin.system.SystemService;
import com.xin.user.dao.LoginOKVO;
import com.xin.user.service.UserService;
import com.zhenhr.tools.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    SystemService systemService;


    /**
     * 发送手机验证码
     *
     * @param req
     * @param res
     * @param mobile
     */
    @RequestMapping(value = "smscode/send")
    public void smsSend(HttpServletRequest req, HttpServletResponse res,
                        String mobile) {
        String code = userService.sendCode(mobile);
        if (code == null) {
            ServletUtils.toJson(req, res);
        } else {
            ServletUtils.toJson("code", code, req, res);
        }
    }


    /**
     * 用户注册
     *
     * @param req
     * @param res
     * @param mobile
     */
    @RequestMapping(value = "register")
    public void register(HttpServletRequest req, HttpServletResponse res, String mobile,
                         String smscode, String pwd) {
        String ip = req.getRemoteHost();
        LoginOKVO vo = userService.mobileRegister(mobile, smscode, pwd, ip);
        ServletUtils.toJson(vo, req, res);
    }

    /**
     * 登录
     *
     * @param req
     * @param res
     * @param mobile
     */
    @RequestMapping(value = "login")
    public void login(HttpServletRequest req, HttpServletResponse res, String mobile,
                      String pwd) {
        String ip = req.getRemoteHost();
        LoginOKVO vo = userService.mobileLogin(mobile, pwd, ip);
        ServletUtils.toJson(vo, req, res);
    }


    /**
     * 重设密码
     *
     * @param req
     * @param res
     * @param mobile
     * @param smscode
     * @param pwd
     */
    @RequestMapping(value = "pwd/reset")
    public void resetpwd(HttpServletRequest req, HttpServletResponse res, String mobile,
                         String smscode, String pwd) {
        String ip = req.getRemoteHost();
        LoginOKVO vo = userService.pwdReset(mobile, smscode, pwd, ip);
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

    // @RequestParam 简单类型的绑定，可以出来get和post
    @RequestMapping(value = "/get")
    public void get(HttpServletRequest req, HttpServletResponse res) {
        Long userId = this.getUserId(req);
        TLoginUser user = userService.get(userId);
        ServletUtils.toJson(user, req, res);
    }


    /**
     * 更新个人信息
     *
     * @param req
     * @param res
     * @param user
     */
    @RequestMapping(value = "set")
    public void set(HttpServletRequest req, HttpServletResponse res, TLoginUser user) {
        Long userId = this.getUserId(req);
        user.setUserId(userId);

        userService.set(user);
        ServletUtils.toJson(req, res);
    }


}
