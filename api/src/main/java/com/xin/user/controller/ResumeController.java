package com.xin.user.controller;

import com.xin.common.BaseController;
import com.xin.db.entity.TUserWorkHistory;
import com.xin.user.dao.UserCVInfoVO;
import com.xin.user.service.UserCVService;
import com.zhenhr.tools.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by guoyongshi on 17/6/2.
 */

@Controller
@RequestMapping(value = "user/cv")
public class ResumeController extends BaseController {
    @Autowired
    UserCVService userCVService;

    /**
     * 获取简历详情
     *
     * @param req
     * @param res
     */
    @RequestMapping(value = "info")
    public void info(HttpServletRequest req, HttpServletResponse res) {
        UserCVInfoVO vo = userCVService.getCVInfo(this.getUserId(req));
        ServletUtils.toJson(vo, req, res);
    }

    /**
     * 获取简历基本信息
     *
     * @param req
     * @param res
     */
    @RequestMapping(value = "basic/get")
    public void basicGet(HttpServletRequest req, HttpServletResponse res) {
        UserCVInfoVO vo = userCVService.getCVBsic(this.getUserId(req));
        ServletUtils.toJson(vo, req, res);
    }

    /**
     * 更新简历基本信息
     *
     * @param req
     * @param res
     * @param obj
     */
    @RequestMapping(value = "basic/set")
    public void basicSet(HttpServletRequest req, HttpServletResponse res, UserCVInfoVO obj) {
        userCVService.updateCVBase(this.getUserId(req), obj);
        ServletUtils.toJson(req, res);
    }

    /**
     * 简历工作经验
     *
     * @param req
     * @param res
     */
    @RequestMapping(value = "workhistory/list")
    public void workhistoryList(HttpServletRequest req, HttpServletResponse res) {
        ServletUtils.toJson(userCVService.getCVWorkHistoryList(this.getUserId(req)), req, res);
    }

    /**
     * 工作经历获取
     *
     * @param req
     * @param res
     * @param tid
     */
    @RequestMapping(value = "workhistory/get")
    public void workhistorySet(HttpServletRequest req, HttpServletResponse res, Long tid) {
        ServletUtils.toJson(userCVService.getWorkHistory(this.getUserId(req), tid), req, res);
    }

    /**
     * 工作经验设置
     *
     * @param req
     * @param res
     * @param obj
     */
    @RequestMapping(value = "workhistory/set")
    public void workhistorySet(HttpServletRequest req, HttpServletResponse res, TUserWorkHistory obj) {
        userCVService.setWorkHistory(this.getUserId(req), obj);
        ServletUtils.toJson(req, res);
    }

    /**
     * 删除工作经验
     *
     * @param req
     * @param res
     * @param tid
     */
    @RequestMapping(value = "workhistory/del")
    public void workhistoryDel(HttpServletRequest req, HttpServletResponse res, Long tid) {
        userCVService.delWorkHistory(this.getUserId(req), tid);
        ServletUtils.toJson(req, res);
    }


}
