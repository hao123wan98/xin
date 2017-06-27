package com.xin.company.service;

import com.xin.XinGeneralCode;
import com.xin.common.BaseService;
import com.xin.db.dao.TCompanyPostionMapper;
import com.xin.db.entity.TCompany;
import com.xin.db.entity.TCompanyPostion;
import com.xin.db.entity.TCompanyPostionExample;
import com.zhenhr.common.ParameterException;
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
public class CompanyPosService extends BaseService {
    @Autowired
    TCompanyPostionMapper companyPostionMapper;


    /**
     * 获取企业职位列表
     *
     * @param companyId
     */
    public List<TCompanyPostion> list(Long companyId, String posState) {

        TCompanyPostionExample exam = new TCompanyPostionExample();
        if (posState != null) {
            exam.createCriteria().andCompanyIdEqualTo(companyId).andStateEqualTo("1").andPosStateEqualTo(posState);
        } else {
            exam.createCriteria().andCompanyIdEqualTo(companyId).andStateEqualTo("1");
        }
        exam.setOrderByClause("create_time desc");
        List<TCompanyPostion> list = companyPostionMapper.selectByExample(exam);
        if (this.isEmptyList(list)) {
            return null;
        }

        return list;
    }

    /**
     * 发布职位
     *
     * @param companyId
     * @param postion
     */
    public void setPostion(Long companyId, TCompanyPostion postion) {

        if (postion.getTid() == null) {
            if (this.isEmptyValue(postion.getName())) {
                throw new ToUserException("职位名称不能为空", null);
            }

            if (this.isEmptyValue(postion.getSalary())) {
                throw new ToUserException("薪资不能为空", null);
            }

            if (this.isEmptyValue(postion.getWorkPlace())) {
                throw new ToUserException("工作地点不能为空", null);
            }
            postion.setCompanyId(companyId);
            postion.setPosState(XinGeneralCode.postion_state_open);
            postion.setCreateTime(new Date());
            postion.setState("1");
            postion.setPublishTime(new Date());
            companyPostionMapper.insert(postion);

        } else {

            TCompanyPostion old = companyPostionMapper.selectByPrimaryKey(postion.getTid());
            ObjectUtils.mergeSameClassValue(postion, old);
            if (old.getPosState().equals(XinGeneralCode.postion_state_open)) {
                old.setPublishTime(new Date());
            }
            companyPostionMapper.updateByPrimaryKey(postion);
        }
    }

    public TCompanyPostion getPostion(Long postionId) {
        TCompanyPostion old = companyPostionMapper.selectByPrimaryKey(postionId);
        if (old == null) {
            throw new ParameterException("无效的参数Id");
        }
        return old;
    }


    /**
     * 关闭职位
     */
    public void closePostion(Long postionId) {
        TCompanyPostion old = companyPostionMapper.selectByPrimaryKey(postionId);
        if (old == null) {
            throw new ParameterException("无效的参数Id");
        }
        old.setPosState(XinGeneralCode.postion_state_close);
        companyPostionMapper.updateByPrimaryKey(old);

    }


    /**
     * 重新打开职位
     *
     * @param postionId
     */
    public void reOpenPostion(Long postionId) {
        TCompanyPostion old = companyPostionMapper.selectByPrimaryKey(postionId);
        if (old == null) {
            throw new ParameterException("无效的参数Id");
        }
        old.setPosState(XinGeneralCode.postion_state_open);
        companyPostionMapper.updateByPrimaryKey(old);
    }

}
