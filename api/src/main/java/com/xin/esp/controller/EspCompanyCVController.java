package com.xin.esp.controller;

import com.xin.common.BaseController;
import com.xin.company.dao.CompanyCVDao;
import com.xin.company.service.CompanyCVService;
import com.xin.user.dao.UserCVInfoVO;
import com.zhenhr.tools.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by guoyongshi on 17/5/26.
 * 企业收到的简历
 */

@Controller
@RequestMapping("mcompany/cv")
public class EspCompanyCVController extends BaseController {
    @Autowired
    CompanyCVService companyCVService;

    /**
     * 简历列表
     *
     * @param req
     * @param res
     * @param state
     */
    @RequestMapping(value = "list")
    public void list(HttpServletRequest req, HttpServletResponse res, Long companyId, String state) {
        List<CompanyCVDao> list = companyCVService.list(companyId, state);
        ServletUtils.toJson(list, req, res);
    }


    /**
     * 简历详情
     *
     * @param req
     * @param res
     * @param tid
     */
    @RequestMapping(value = "info")
    public void info(HttpServletRequest req, HttpServletResponse res, Long tid) {
        UserCVInfoVO vo = companyCVService.info(tid);
        ServletUtils.toJson(vo, req, res);
    }


    /**
     * 设置审核状态
     *
     * @param req
     * @param res
     * @param tid
     * @param state
     */
    @RequestMapping(value = "state/set")
    public void setState(HttpServletRequest req, HttpServletResponse res, Long tid, String state) {
        companyCVService.setState(tid, state);
        ServletUtils.toJson(req, res);
    }

}
