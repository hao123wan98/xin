package com.xin.user.controller;

import com.xin.common.BaseController;
import com.xin.db.entity.TUserWorkHistory;
import com.xin.user.dao.UserCVInfoVO;
import com.xin.user.service.UserCVOuterService;
import com.zhenhr.tools.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by guoyongshi on 17/6/2.
 * 外部完善简历
 */

@Controller
@RequestMapping(value = "user/cv/outer")
public class ResumeOuterController extends BaseController {
    @Autowired
    UserCVOuterService cvOuterService;

    /**
     * 获取简历详情
     *
     * @param req
     * @param res
     * @param code
     */
    @RequestMapping(value = "info")
    public void info(HttpServletRequest req, HttpServletResponse res, String code) {
        UserCVInfoVO vo = cvOuterService.getCVInfo(code);
        ServletUtils.toJson(vo, req, res);
    }

    /**
     * 获取简历基本信息
     *
     * @param req
     * @param res
     * @param code
     */
    @RequestMapping(value = "basic/get")
    public void basicGet(HttpServletRequest req, HttpServletResponse res, String code) {
        UserCVInfoVO vo = cvOuterService.getCVBsic(code);
        ServletUtils.toJson(vo, req, res);
    }

    /**
     * 更新简历基本信息
     *
     * @param req
     * @param res
     * @param code
     * @param obj
     */
    @RequestMapping(value = "basic/set")
    public void basicSet(HttpServletRequest req, HttpServletResponse res, String code, UserCVInfoVO obj) {
        cvOuterService.updateCVBase(code, obj);
        ServletUtils.toJson(req, res);
    }

    /**
     * 简历工作经验
     *
     * @param req
     * @param res
     * @param code
     */
    @RequestMapping(value = "workhistory/list")
    public void workhistoryList(HttpServletRequest req, HttpServletResponse res, String code) {
        ServletUtils.toJson(cvOuterService.getCVWorkHistoryList(code), req, res);
    }

    /**
     * 工作经历获取
     *
     * @param req
     * @param res
     * @param code
     * @param tid
     */
    @RequestMapping(value = "workhistory/get")
    public void workhistorySet(HttpServletRequest req, HttpServletResponse res, String code, Long tid) {
        ServletUtils.toJson(cvOuterService.getWorkHistory(code, tid), req, res);
    }

    /**
     * 工作经验设置
     *
     * @param req
     * @param res
     * @param code
     * @param obj
     */
    @RequestMapping(value = "workhistory/set")
    public void workhistorySet(HttpServletRequest req, HttpServletResponse res, String code, TUserWorkHistory obj) {
        cvOuterService.setWorkHistory(code, obj);
        ServletUtils.toJson(req, res);
    }

    /**
     * 删除工作经验
     *
     * @param req
     * @param res
     * @param code
     * @param tid
     */
    @RequestMapping(value = "workhistory/del")
    public void workhistoryDel(HttpServletRequest req, HttpServletResponse res, String code, Long tid) {
        cvOuterService.delWorkHistory(code, tid);
        ServletUtils.toJson(req, res);
    }


    /**
     * 简历上传
     *
     * @param req
     * @param res
     */
    @RequestMapping(value = "file/upload")
    public void fileUpload(HttpServletRequest req, HttpServletResponse res, String code, String cvPath) {
        Long userId = this.getUserId(req);
        cvOuterService.cvUpload(code, cvPath);
    }


}
