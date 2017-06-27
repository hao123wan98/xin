package com.xin.company.service;

import com.xin.XinGeneralCode;
import com.xin.common.BaseService;
import com.xin.common.ListPageVO;
import com.xin.db.common.Page;
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
            if (!old.getReviewState().equals(XinGeneralCode.review_company_access)) {
                old.setReviewState(XinGeneralCode.review_company_none);
            }

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


    public Long getCompanyId(Long userId) {
        TCompany com = this.getCompany(userId);
        if (com == null) {
            throw new ToUserException("请先完善企业信息", null);
        }

        if (com.getReviewState().equals(XinGeneralCode.review_company_none)) {
            throw new ToUserException("企业还未审核", null);
        }

        if (com.getReviewState().equals(XinGeneralCode.review_company_failed)) {
            throw new ToUserException("企业审核失败", null);
        }

        return com.getCompanyId();
    }




}
