package com.xin.company.service;

import com.xin.common.BaseService;
import com.xin.company.dao.CompanyCVDao;
import com.xin.db.dao.TUserPostionMapper;
import com.xin.db.entity.TUserPostion;
import com.xin.self.db.dao.SelfMapper;
import com.xin.user.dao.UserCVInfoVO;
import com.xin.user.service.UserCVService;
import com.zhenhr.common.ParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by guoyongshi on 17/6/21.
 * 企业收到的简历
 */
@Service
public class CompanyCVService extends BaseService {
    @Autowired
    TUserPostionMapper userPostionMapper;
    @Autowired
    SelfMapper selfMapper;

    @Autowired
    UserCVService userCVService;

    /**
     * 获取投递的简历列表
     *
     * @param companyId
     * @return
     */
    public List<CompanyCVDao> list(Long companyId, String state) {

        String sql = "select t1.tid, t1.postion_id, t3.name as postion_name, t2.* from t_user_postion t1 " +
                " left join t_user t2 on t1.user_id = t2.user_id " +
                " left join t_company_postion t3 on t1.postion_id = t3.tid" +
                " where t1.company_id =  " + companyId;
        if (!this.isEmptyValue(state)) {
            sql += " and t1.review_state='" + state + "' order by t1.create_time desc";
        }
        List<Map<String, Object>> list = selfMapper.execSqlSelect(sql);
        if (this.isEmptyList(list)) {
            return null;
        }

        List<CompanyCVDao> result = new ArrayList<>();
        for (Map<String, Object> map : list) {
            CompanyCVDao dao = CompanyCVDao.fromObject(map);
            result.add(dao);
        }
        return result;


    }

    /**
     * 简历详情
     *
     * @param tid
     */
    public UserCVInfoVO info(Long tid) {
        this.judgeTid(tid);
        TUserPostion up = userPostionMapper.selectByPrimaryKey(tid);
        UserCVInfoVO info = userCVService.getCVInfo(up.getUserId());
        return info;
    }


    /**
     * 设置简历状态
     *
     * @param tid
     * @param state
     */
    public void setState(Long tid, String state) {
        this.judgeTid(tid);

        if (this.isEmptyValue(state)) {
            throw new ParameterException("state不能为空");
        }

        TUserPostion up = userPostionMapper.selectByPrimaryKey(tid);
        up.setReviewState(state);
        up.setReviewTime(new Date());
        userPostionMapper.updateByPrimaryKey(up);
    }

}
