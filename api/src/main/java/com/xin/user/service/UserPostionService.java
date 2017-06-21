package com.xin.user.service;

import com.xin.XinGeneralCode;
import com.xin.common.BaseService;
import com.xin.common.ListPageVO;
import com.xin.company.service.CompanyPosService;
import com.xin.db.common.Page;
import com.xin.db.dao.TCompanyPostionMapper;
import com.xin.db.dao.TUserPostionMapper;
import com.xin.db.entity.*;
import com.xin.self.db.dao.SelfMapper;
import com.xin.user.dao.PostionListVO;
import com.xin.user.dao.SearchPosObj;
import com.zhenhr.common.ToUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by guoyongshi on 17/6/2.
 */

@Service
public class UserPostionService extends BaseService {
    @Autowired
    TCompanyPostionMapper companyPostionMapper;
    @Autowired
    SelfMapper selfMapper;
    @Autowired
    TUserPostionMapper userPostionMapper;

    @Autowired
    UserService userService;
    @Autowired
    CompanyPosService companyPosService;

    /**
     * 获取首页职位列表
     *
     * @param page
     * @return
     */
    public ListPageVO getHomePostion(Page page) {
        TCompanyPostionExample exam = new TCompanyPostionExample();
        exam.createCriteria().andStateEqualTo("1").andStateEqualTo("1");
        int count = companyPostionMapper.countByExample(exam);


        int pageBegin = (page.getPageNo() - 1) * page.getSize();

        String sql = "select t2.name as company_name,t1.* from t_company_postion t1 " +
                "left join t_company t2 on t1.company_id = t2.company_id " +
                "where t1.state='1' and t1.pos_state = '1' " +
                "order by t1.publish_time desc LIMIT " + pageBegin + "," + page.getSize();
        List<Map<String, Object>> list = selfMapper.execSqlSelect(sql);

        ListPageVO vo = new ListPageVO();
        vo.setList(list);
        vo.setPage(page.getPageNo(), page.getSize(), count);

        return vo;
    }


    /**
     * 查找职位
     *
     * @param param
     * @param page
     * @return
     */
    public List<PostionListVO> searchPostion(SearchPosObj param, Page page) {
        return null;
    }


    /**
     * 用户申请职位
     *
     * @param userId
     * @param posId
     */
    public void requestPostion(Long userId, Long posId) {
        if (posId == null) {
            throw new ToUserException("职位id不能为空", "posId");
        }

        // 先判断个人信息是否完善
        TLoginUser user = userService.get(userId);

        if (this.isEmptyValue(user.getName())) {
            throw new ToUserException("请先完善个人信息", null);
        }

        if (this.isEmptyValue(user.getEmail())) {
            throw new ToUserException("请先完善个人信息", null);
        }

        TUserPostionExample exam = new TUserPostionExample();
        exam.createCriteria().andUserIdEqualTo(userId).andPostionIdEqualTo(posId);
        int count = userPostionMapper.countByExample(exam);
        if (count > 0) {
            throw new ToUserException("您已经申请过该职位", null);
        }

        TCompanyPostion postion = companyPosService.getPostion(posId);

        TUserPostion up = new TUserPostion();
        up.setCompanyId(postion.getCompanyId());
        up.setPostionId(posId);
        up.setReviewState(XinGeneralCode.postion_state_none);
        up.setUserId(userId);

        up.setCreateTime(new Date());
        up.setState("1");

        userPostionMapper.insert(up);
    }


    /**
     * 我的投递记录
     *
     * @param userId
     */
    public List<TUserPostion> history(Long userId) {
        TUserPostionExample exam = new TUserPostionExample();
        exam.createCriteria().andUserIdEqualTo(userId);
        exam.setOrderByClause("create_time desc");
        List<TUserPostion> list = userPostionMapper.selectByExample(exam);
        return list;
    }


}
