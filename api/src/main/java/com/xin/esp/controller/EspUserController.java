package com.xin.esp.controller;

import com.xin.common.BaseController;
import com.xin.common.ListPageVO;
import com.xin.db.common.Page;
import com.xin.db.entity.TCompany;
import com.xin.db.entity.TLoginUser;
import com.xin.db.entity.TUserWorkHistory;
import com.xin.esp.service.EspCompanyService;
import com.xin.esp.service.EspUserService;
import com.xin.user.dao.UserCVInfoVO;
import com.xin.user.service.UserCVService;
import com.xin.user.service.UserService;
import com.zhenhr.tools.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by guoyongshi on 17/6/26.
 */

@Controller
@RequestMapping(value = "muser")
public class EspUserController extends BaseController {
    @Autowired
    EspUserService espUserService;
    @Autowired
    UserService userService;
    @Autowired
    UserCVService userCVService;

    /**
     * 获取注册用户列表
     *
     * @param req
     * @param res
     * @param page
     */
    @RequestMapping(value = "list")
    public void list(HttpServletRequest req, HttpServletResponse res, Page page) {
        ListPageVO vo = espUserService.list(page);
        ServletUtils.toJson(vo, req, res);
    }

    /**
     * 详情
     *
     * @param req
     * @param res
     * @param userId
     */
    @RequestMapping(value = "info")
    public void info(HttpServletRequest req, HttpServletResponse res, Long userId) {
        TLoginUser vo = espUserService.get(userId);
        ServletUtils.toJson(vo, req, res);
    }


    /**
     * 删除
     *
     * @param req
     * @param res
     * @param userId
     */
    @RequestMapping(value = "delete")
    public void delete(HttpServletRequest req, HttpServletResponse res, Long userId) {
        espUserService.delete(userId);
        ServletUtils.toJson(req, res);
    }


    /**
     * 设置信息
     *
     * @param req
     * @param res
     * @param user
     */
    @RequestMapping(value = "set")
    public void set(HttpServletRequest req, HttpServletResponse res, TLoginUser user) {
        userService.set(user);
        ServletUtils.toJson(req, res);
    }

    /**
     * 切换角色
     *
     * @param req
     * @param res
     * @param userId
     * @param role
     */
    @RequestMapping(value = "role/switch")
    public void roleSwitch(HttpServletRequest req, HttpServletResponse res, Long userId, String role) {
        userService.roleSwitch(userId, role);
        ServletUtils.toJson(req, res);
    }


    /**
     * 获取简历详情
     *
     * @param req
     * @param res
     */
    @RequestMapping(value = "cv/info")
    public void cvInfo(HttpServletRequest req, HttpServletResponse res, Long userId) {
        UserCVInfoVO vo = userCVService.getCVInfo(userId);
        ServletUtils.toJson(vo, req, res);
    }

    /**
     * 获取简历基本信息
     *
     * @param req
     * @param res
     * @param userId
     */
    @RequestMapping(value = "cv/basic/get")
    public void basicGet(HttpServletRequest req, HttpServletResponse res, Long userId) {
        UserCVInfoVO vo = userCVService.getCVBsic(userId);
        ServletUtils.toJson(vo, req, res);
    }

    /**
     * 更新简历基本信息
     *
     * @param req
     * @param res
     * @param userId
     * @param obj
     */
    @RequestMapping(value = "cv/basic/set")
    public void basicSet(HttpServletRequest req, HttpServletResponse res, Long userId, UserCVInfoVO obj) {
        userCVService.updateCVBase(userId, obj);
        ServletUtils.toJson(req, res);
    }

    /**
     * 简历工作经验
     *
     * @param req
     * @param res
     * @param userId
     */
    @RequestMapping(value = "cv/workhistory/list")
    public void workhistoryList(HttpServletRequest req, HttpServletResponse res, Long userId) {
        ServletUtils.toJson(userCVService.getCVWorkHistoryList(userId), req, res);
    }

    /**
     * 工作经历获取
     *
     * @param req
     * @param res
     * @param userId
     * @param tid
     */
    @RequestMapping(value = "cv/workhistory/get")
    public void workhistorySet(HttpServletRequest req, HttpServletResponse res, Long userId, Long tid) {
        ServletUtils.toJson(userCVService.getWorkHistory(userId, tid), req, res);
    }

    /**
     * 工作经验设置
     *
     * @param req
     * @param res
     * @param userId
     * @param obj
     */
    @RequestMapping(value = "cv/workhistory/set")
    public void workhistorySet(HttpServletRequest req, HttpServletResponse res, Long userId, TUserWorkHistory obj) {
        userCVService.setWorkHistory(userId, obj);
        ServletUtils.toJson(req, res);
    }

    /**
     * 删除工作经验
     *
     * @param req
     * @param res
     * @param userId
     * @param tid
     */
    @RequestMapping(value = "cv/workhistory/del")
    public void workhistoryDel(HttpServletRequest req, HttpServletResponse res, Long userId, Long tid) {
        userCVService.delWorkHistory(userId, tid);
        ServletUtils.toJson(req, res);
    }

}
