package com.xin.common;

import com.xin.company.service.CompanyService;
import com.xin.user.service.TokenService;
import com.zhenhr.common.TokenException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @Autowired
    TokenService tokenService;
    @Autowired
    CompanyService companyService;


    /**
     * 用户Id
     *
     * @param req
     * @return
     */
    public Long getUserId(HttpServletRequest req) {
        String token = req.getHeader("token");
        if (token == null) {
            throw new TokenException("token不能为空");
        }

        String userId = tokenService.getUserId(token);
        if (userId == null) {
            throw new TokenException("无效的用户Token");
        }

        return Long.valueOf(userId);
    }

    public Long getCompanyId(Long userId) {
        return companyService.getCompanyId(userId);
    }

    public Long getCompanyId(HttpServletRequest req) {
        Long userId = this.getUserId(req);
        return this.getCompanyId(userId);
    }

}
