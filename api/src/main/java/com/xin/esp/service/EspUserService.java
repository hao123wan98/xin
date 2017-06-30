package com.xin.esp.service;

import com.xin.XinGeneralCode;
import com.xin.common.BaseService;
import com.xin.common.ListPageVO;
import com.xin.db.common.Page;
import com.xin.db.dao.TLoginUserMapper;
import com.xin.db.entity.TCompany;
import com.xin.db.entity.TLoginUser;
import com.xin.db.entity.TLoginUserExample;
import com.zhenhr.common.ParameterException;
import com.zhenhr.common.ToUserException;
import com.zhenhr.tools.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by guoyongshi on 17/6/27.
 * 注册用户列表
 */

@Service
public class EspUserService extends BaseService {
    @Autowired
    TLoginUserMapper userMapper;

    /**
     * 用户列表
     *
     * @param page
     * @return
     */
    public ListPageVO list(Page page) {
        TLoginUserExample exam = new TLoginUserExample();
        exam.createCriteria().andStateEqualTo("1");
        Integer count = userMapper.countByExample(exam);

        exam.setOrderByClause("create_time desc");
        exam.setPage(page);

        List<TLoginUser> list = userMapper.selectByExample(exam);
        ListPageVO vo = new ListPageVO();
        vo.setList(list);
        vo.setPage(page.getPageNo(), page.getSize(), count);
        return vo;
    }


    public TLoginUser get(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public void delete(Long userId) {
        TLoginUser user = this.get(userId);
        user.setState("2");
        userMapper.updateByPrimaryKey(user);
    }


}
