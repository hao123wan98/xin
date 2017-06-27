package com.xin.company.controller;

import com.xin.common.BaseController;
import com.xin.common.ListPageVO;
import com.xin.company.service.CompanyService;
import com.xin.db.common.Page;
import com.xin.db.entity.TCompany;
import com.zhenhr.tools.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by guoyongshi on 17/5/26.
 */

@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {
    @Autowired
    CompanyService companyService;

    /**
     * 设置公司信息
     *
     * @param req
     * @param res
     * @param company
     */
    @RequestMapping(value = "info/set")
    public void setInfo(HttpServletRequest req, HttpServletResponse res,
                        TCompany company) {
        Long userId = this.getUserId(req);
        companyService.setCompany(userId, company);
        ServletUtils.toJson(req, res);
    }

    /**
     * 获取公司信息
     *
     * @param req
     * @param res
     */
    @RequestMapping(value = "info/get")
    public void getInfo(HttpServletRequest req, HttpServletResponse res) {
        Long userId = this.getUserId(req);
        TCompany company = companyService.getCompany(userId);
        ServletUtils.toJson(company, req, res);
    }



//    /**
//     * 提交审核
//     *
//     * @param req
//     * @param res
//     */
//    @RequestMapping(value = "commit/review")
//    public void review(HttpServletRequest req, HttpServletResponse res) {
//        Long userId = this.getUserId(req);
//        TCompany company = companyService.getCompany(userId);
//        ServletUtils.toJson(company, req, res);
//    }


}
