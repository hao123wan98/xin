package com.xin.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xin.common.BaseController;
import com.xin.system.dao.FileUploadDao;
import com.xin.webservice.COSClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhenhr.tools.ServletUtils;

@Controller
@RequestMapping("/file")
public class FileUploadController extends BaseController {

    /**
     * 生成文件上传签名
     *
     * @param req
     * @param res
     */
    @RequestMapping(value = "sign/create")
    public void createSign(HttpServletRequest req, HttpServletResponse res, String fileType) {
        FileUploadDao obj = COSClientService.createFileSign(fileType);
        ServletUtils.toJson(obj, req, res);
    }


}
