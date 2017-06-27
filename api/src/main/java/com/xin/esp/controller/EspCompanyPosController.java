package com.xin.esp.controller;

import com.xin.common.BaseController;
import com.xin.company.service.CompanyPosService;
import com.xin.db.entity.TCompanyPostion;
import com.zhenhr.tools.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by guoyongshi on 17/6/26.
 * 企业职位信息
 */

@Controller
@RequestMapping(value = "mcompany/pos")
public class EspCompanyPosController extends BaseController {
    @Autowired
    CompanyPosService companyPosService;

    /**
     * 获取企业职位列表
     *
     * @param req
     * @param res
     * @param posState
     */
    @RequestMapping(value = "list")
    public void list(HttpServletRequest req, HttpServletResponse res, Long companyId, String posState) {
        List<TCompanyPostion> vo = companyPosService.list(companyId, posState);
        ServletUtils.toJson(vo, req, res);
    }

    /**
     * 添加编辑职位
     *
     * @param req
     * @param res
     * @param postion
     */
    @RequestMapping(value = "set")
    public void set(HttpServletRequest req, HttpServletResponse res, TCompanyPostion postion) {
        companyPosService.setPostion(postion.getCompanyId(), postion);
        ServletUtils.toJson(req, res);
    }

    /**
     * 设置职位状态
     *
     * @param req
     * @param res
     * @param state
     * @param tid
     */
    @RequestMapping(value = "state")
    public void setState(HttpServletRequest req, HttpServletResponse res, Long tid, String state) {

        if ("1".equals(state)) {
            companyPosService.reOpenPostion(tid);
        } else {
            companyPosService.closePostion(tid);
        }

        ServletUtils.toJson(req, res);
    }

}
