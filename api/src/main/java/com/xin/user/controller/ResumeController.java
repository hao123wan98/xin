package com.xin.user.controller;

import com.xin.common.BaseController;
import com.zhenhr.tools.ServletUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by guoyongshi on 17/6/2.
 */

@Controller
@RequestMapping(value = "resume")
public class ResumeController extends BaseController {

    /**
     * 简历上传
     *
     * @param req
     * @param res
     */
    @RequestMapping(value = "upload")
    public void upload(HttpServletRequest req, HttpServletResponse res, String code, String pathUrl) {
        Long userId = this.getUserId(req);
    }

}
