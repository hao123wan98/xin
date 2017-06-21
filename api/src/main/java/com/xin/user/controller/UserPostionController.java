package com.xin.user.controller;

import com.xin.common.BaseController;
import com.xin.common.ListPageVO;
import com.xin.db.common.Page;
import com.xin.db.entity.TUserPostion;
import com.xin.user.dao.SearchPosObj;
import com.xin.user.service.UserPostionService;
import com.zhenhr.tools.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by guoyongshi on 17/6/2.
 * 用户职位
 */

@Controller
@RequestMapping(value = "user/postions")
public class UserPostionController extends BaseController {
    @Autowired
    UserPostionService userPostionService;

    /**
     * 职位首页
     *
     * @param req
     * @param res
     */
    @RequestMapping(value = "home")
    public void home(HttpServletRequest req, HttpServletResponse res, Page page) {
        Long userId = this.getUserId(req);

        ListPageVO vo = userPostionService.getHomePostion(page);
        ServletUtils.toJson(vo, req, res);
    }


    /**
     * 查找职位
     *
     * @param req
     * @param res
     * @param paramObj
     */
    @RequestMapping(value = "search")
    public void search(HttpServletRequest req, HttpServletResponse res, SearchPosObj paramObj) {
        Long userId = this.getUserId(req);
        ServletUtils.toJson(req, res);
    }


    /**
     * 申请职位
     *
     * @param req
     * @param res
     */
    @RequestMapping(value = "request")
    public void request(HttpServletRequest req, HttpServletResponse res, Long postionId) {
        Long userId = this.getUserId(req);
        userPostionService.requestPostion(userId, postionId);

        ServletUtils.toJson(req, res);
    }

    /**
     * 应聘历史记录
     *
     * @param req
     * @param res
     */
    @RequestMapping(value = "history")
    public void history(HttpServletRequest req, HttpServletResponse res) {
        Long userId = this.getUserId(req);
        List<TUserPostion> list = userPostionService.history(userId);
        ServletUtils.toJson(list, req, res);
    }


}
