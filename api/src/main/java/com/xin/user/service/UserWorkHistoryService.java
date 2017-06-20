package com.xin.user.service;

import com.xin.common.BaseService;
import com.xin.db.dao.TUserWorkHistoryMapper;
import com.xin.db.entity.TUserWorkHistory;
import com.xin.db.entity.TUserWorkHistoryExample;
import com.zhenhr.common.ToUserException;
import com.zhenhr.tools.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by guoyongshi on 17/6/12.
 * 用户工作经验
 */

@Service
public class UserWorkHistoryService extends BaseService {
    @Autowired
    TUserWorkHistoryMapper userWorkHistoryMapper;

    /**
     * 获取用户工作经历
     *
     * @param userId
     * @return
     */
    public List<TUserWorkHistory> userWorkHistory(Long userId) {
        TUserWorkHistoryExample exam = new TUserWorkHistoryExample();
        exam.createCriteria().andUserIdEqualTo(String.valueOf(userId)).andStateEqualTo("1");
        exam.setOrderByClause("start_date DESC");

        List<TUserWorkHistory> list = userWorkHistoryMapper.selectByExample(exam);
        if (this.isEmptyList(list)) {
            return null;
        }

        return list;
    }


    /**
     * 设置工作经验
     *
     * @param userId
     * @param obj
     */
    public void set(Long userId, TUserWorkHistory obj) {
        if (obj.getTid() == null) { //insert
            if (this.isEmptyValue(obj.getCompanyName())) {
                throw new ToUserException("企业名称不能为空", "companyName");
            }
            if (this.isEmptyValue(obj.getStartDate())) {
                throw new ToUserException("开始日期不能为空", "startDate");
            }

            obj.setUserId(String.valueOf(userId));
            obj.setState("1");
            obj.setCreateTime(new Date());
            userWorkHistoryMapper.insert(obj);
        } else { //update
            TUserWorkHistory old = this.get(userId, obj.getTid());
            ObjectUtils.mergeSameClassValue(obj, old);
            userWorkHistoryMapper.updateByPrimaryKey(old);
        }
    }


    /**
     * 获取工作经验
     *
     * @param userId
     * @param tid
     */
    public TUserWorkHistory get(Long userId, Long tid) {
        this.judgeTid(tid);

        TUserWorkHistory obj = userWorkHistoryMapper.selectByPrimaryKey(tid);
        if (obj == null || !obj.getUserId().equals(userId)) {
            throw new ToUserException("tid错误", null);
        }

        return obj;
    }


    /**
     * 删除工作经验
     *
     * @param userId
     * @param tid
     */
    public void del(Long userId, Long tid) {
        TUserWorkHistory obj = this.get(userId, tid);
        obj.setState("2");
        userWorkHistoryMapper.updateByPrimaryKey(obj);
    }

}
