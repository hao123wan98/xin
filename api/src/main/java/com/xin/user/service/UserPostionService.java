package com.xin.user.service;

import com.xin.common.ListPageVO;
import com.xin.db.common.Page;
import com.xin.db.dao.TCompanyPostionMapper;
import com.xin.db.entity.TCompanyPostionExample;
import com.xin.self.db.dao.SelfMapper;
import com.xin.user.dao.PostionListVO;
import com.xin.user.dao.SearchPosObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by guoyongshi on 17/6/2.
 */

@Service
public class UserPostionService {
    @Autowired
    TCompanyPostionMapper companyPostionMapper;
    @Autowired
    SelfMapper selfMapper;

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
        // 先判断个人信息是否完善

    }


}
