package com.xin.esp.controller;

import com.xin.common.BaseController;
import com.xin.common.ListPageVO;
import com.xin.db.common.Page;
import com.xin.db.entity.TCompany;
import com.xin.db.entity.TLoginUser;
import com.xin.esp.service.EspCompanyService;
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
    EspCompanyService espCompanyService;

    /**
     * 获取注册用户列表
     *
     * @param req
     * @param res
     * @param page
     * @param reviewState
     */
    @RequestMapping(value = "list")
    public void list(HttpServletRequest req, HttpServletResponse res, Page page, String reviewState) {
        ListPageVO vo = espCompanyService.list(page, reviewState);
        ServletUtils.toJson(vo, req, res);
    }

    /**
     * 企业详情
     *
     * @param req
     * @param res
     * @param companyId
     */
    @RequestMapping(value = "info")
    public void info(HttpServletRequest req, HttpServletResponse res, Long companyId) {
        TCompany vo = espCompanyService.getCompany(companyId);
        ServletUtils.toJson(vo, req, res);
    }


    /**
     * 删除企业
     *
     * @param req
     * @param res
     * @param companyId
     */
    @RequestMapping(value = "delete")
    public void delete(HttpServletRequest req, HttpServletResponse res, Long companyId) {
        espCompanyService.delete(companyId);
        ServletUtils.toJson(req, res);
    }


    /**
     * 企业信息
     *
     * @param req
     * @param res
     * @param company
     */
    @RequestMapping(value = "set")
    public void set(HttpServletRequest req, HttpServletResponse res, TCompany company) {
        TCompany vo = espCompanyService.setCompany(company);
        ServletUtils.toJson(vo, req, res);
    }


}
