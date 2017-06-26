package com.xin.esp.controller;

import com.xin.common.BaseController;
import com.xin.db.entity.TCompany;
import com.xin.db.entity.TLoginUser;
import com.xin.esp.service.EspCompanyService;
import com.xin.user.dao.LoginOKVO;
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
@RequestMapping(value = "mcompany")
public class EspCompanyController extends BaseController {
    @Autowired
    EspCompanyService espCompanyService;

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

    /**
     * 关联登录账户
     *
     * @param req
     * @param res
     * @param companyId
     * @param user
     */
    @RequestMapping(value = "linkuser")
    public void linkUser(HttpServletRequest req, HttpServletResponse res, Long companyId, TLoginUser user) {
        espCompanyService.linkUser(companyId, user);
        ServletUtils.toJson(req, res);
    }


}
