package com.xin.company.service;

import com.xin.common.BaseService;
import com.xin.db.dao.TCompanyMapper;
import com.xin.db.entity.TCompany;
import com.xin.db.entity.TCompanyExample;
import com.zhenhr.common.ToUserException;
import com.zhenhr.tools.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by guoyongshi on 17/5/26.
 */

@Service
public class CompanyService extends BaseService {
    @Autowired
    TCompanyMapper companyMapper;


    /**
     * 设置企业信息
     *
     * @param userId
     * @param company
     */
    public void setCompany(Long userId, TCompany company) {

        TCompany old = this.getCompany(userId);
        if (old == null) {
            if (this.isEmptyValue(company.getName())) {
                throw new ToUserException("企业名称不能为空", null);
            }

            company.setUserId(userId);
            company.setState("1");
            company.setCreateTime(new Date());
            company.setReviewState("0");
            companyMapper.insertSelective(company);
        } else {
            ObjectUtils.mergeSameClassValue(company, old);
            companyMapper.updateByPrimaryKey(old);
        }
    }


    public TCompany getCompany(Long userId) {
        TCompanyExample exam = new TCompanyExample();
        exam.createCriteria().andUserIdEqualTo(userId).andStateEqualTo("1");

        List<TCompany> list = companyMapper.selectByExample(exam);
        if (this.isEmptyList(list)) {
            return null;
        }

        return list.get(0);
    }


}
