package com.xin.company.controller;

import com.xin.common.BaseController;
import com.xin.company.service.CompanyPosService;
import com.xin.db.entity.TCompany;
import com.xin.db.entity.TCompanyPostion;
import com.zhenhr.tools.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by guoyongshi on 17/5/26.
 */

@Controller
@RequestMapping("/company/postion")
public class CompanyPosController extends BaseController {
    @Autowired
    CompanyPosService companyPosService;


    /**
     * 获取职位列表
     * @param req
     * @param res
     */
    @RequestMapping(value = "list")
    public void list(HttpServletRequest req, HttpServletResponse res) {
        Long userId = this.getUserId(req);
        List<TCompanyPostion> list = companyPosService.list(userId);
        ServletUtils.toJson(list, req, res);
    }

    /**
     * 新加或者编辑职位
     * @param req
     * @param res
     * @param postion
     */
    @RequestMapping(value = "set")
    public void set(HttpServletRequest req, HttpServletResponse res, TCompanyPostion postion) {
        Long userId = this.getUserId(req);
        companyPosService.setPostion(userId, postion);
        ServletUtils.toJson(req, res);
    }

    /**
     * 获取职位详情
     * @param req
     * @param res
     * @param postionId
     */
    @RequestMapping(value = "get")
    public void get(HttpServletRequest req, HttpServletResponse res, Long postionId) {
        Long userId = this.getUserId(req);
        TCompanyPostion pos = companyPosService.getPostion(postionId);
        ServletUtils.toJson(pos, req, res);
    }

    /**
     * 关闭职位
     * @param req
     * @param res
     * @param postionId
     */
    @RequestMapping(value = "close")
    public void closePostion(HttpServletRequest req, HttpServletResponse res, Long postionId) {
        Long userId = this.getUserId(req);
        companyPosService.closePostion(userId, postionId);
        ServletUtils.toJson(req, res);
    }

    /**
     * 重启职位
     * @param req
     * @param res
     * @param postionId
     */
    @RequestMapping(value = "reopen")
    public void reopenPostion(HttpServletRequest req, HttpServletResponse res, Long postionId) {
        Long userId = this.getUserId(req);
        companyPosService.reOpenPostion(userId, postionId);
        ServletUtils.toJson(req, res);
    }


}
