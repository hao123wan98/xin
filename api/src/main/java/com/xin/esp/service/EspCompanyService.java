package com.xin.esp.service;

import com.xin.common.BaseService;
import com.xin.db.dao.TCompanyMapper;
import com.xin.db.dao.TLoginUserMapper;
import com.xin.db.entity.TCompany;
import com.xin.db.entity.TLoginUser;
import com.zhenhr.common.ToUserException;
import com.zhenhr.tools.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by guoyongshi on 17/6/26.
 */

@Service
public class EspCompanyService extends BaseService {
    @Autowired
    TCompanyMapper companyMapper;
    @Autowired
    TLoginUserMapper userMapper;


    /**
     * 设置企业信息
     *
     * @param company
     */
    public TCompany setCompany(TCompany company) {
        if (company.getCompanyId() == null) { //新的企业
            if (this.isEmptyValue(company.getName())) {
                throw new ToUserException("企业名称不能为空", null);
            }

            company.setUserId(-1l);
            company.setState("1");
            company.setCreateTime(new Date());
            company.setReviewState("1");
            companyMapper.insertSelective(company);
            return company;
        } else {
            TCompany old = this.getCompany(company.getCompanyId());
            ObjectUtils.mergeSameClassValue(company, old);
            companyMapper.updateByPrimaryKey(old);
            return old;
        }
    }

    /**
     * 关联登录账户
     *
     * @param companyId
     * @param user
     */
    public void linkUser(Long companyId, TLoginUser user) {
        this.isValidCompanyId(companyId);
        if (user.getUserId() == null) { //新增
            if (this.isEmptyValue(user.getMobile())) {
                throw new ToUserException("登录手机号不能为空", "mobile");
            }

            if (this.isEmptyValue(user.getPwd())) {
                throw new ToUserException("登录密码不能为空", "pwd");
            }
            if (user.getGender() == null) {
                user.setGender("0");
            }

            user.setCreateTime(new Date());
            user.setState("1");
            userMapper.insertSelective(user);

            TCompany oldCompany = this.getCompany(companyId);
            oldCompany.setUserId(user.getUserId());
            companyMapper.updateByPrimaryKey(oldCompany);

        } else { //修改
            TLoginUser old = userMapper.selectByPrimaryKey(user.getUserId());
            ObjectUtils.mergeSameClassValue(user, old);
            userMapper.updateByPrimaryKey(old);
        }

    }


    public TCompany getCompany(Long companyId) {
        TCompany company = companyMapper.selectByPrimaryKey(companyId);
        return company;
    }

}
