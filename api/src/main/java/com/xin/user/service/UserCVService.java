package com.xin.user.service;

import com.xin.common.BaseService;
import com.xin.db.entity.TLoginUser;
import com.xin.db.entity.TUserWorkHistory;
import com.xin.user.dao.UserCVInfoVO;
import com.xin.webservice.COSClientService;
import com.zhenhr.tools.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by guoyongshi on 17/6/12.
 * 用户完善个人简历信息
 */

@Service
public class UserCVService extends BaseService {
    @Autowired
    UserService userService;
    @Autowired
    UserWorkHistoryService workHistoryService;

    /**
     * 获取简历详情
     *
     * @return
     */
    public UserCVInfoVO getCVInfo(Long userId) {
        TLoginUser user = userService.get(userId);

        UserCVInfoVO vo = new UserCVInfoVO();
        ObjectUtils.mergeDifferClassValue(user, vo);

        List<TUserWorkHistory> list = workHistoryService.userWorkHistory(userId);
        vo.setWorkHistoryList(list);

        return vo;
    }

    /**
     * 获取简历基本信息
     *
     * @return
     */
    public UserCVInfoVO getCVBsic(Long userId) {
        TLoginUser user = userService.get(userId);

        UserCVInfoVO vo = new UserCVInfoVO();
        ObjectUtils.mergeDifferClassValue(user, vo);
        return vo;
    }


    /**
     * 更新简历基本信息
     *
     * @param obj
     */
    public void updateCVBase(Long userId, UserCVInfoVO obj) {
        TLoginUser user = userService.get(userId);
        ObjectUtils.mergeDifferClassValue(obj, user);

        userService.update(user);
    }

    /**
     * 获取用户的工作简历列表
     *
     * @return
     */
    public List<TUserWorkHistory> getCVWorkHistoryList(Long userId) {
        List<TUserWorkHistory> list = workHistoryService.userWorkHistory(userId);
        return list;
    }


    public TUserWorkHistory getWorkHistory(Long userId, Long tid) {
        return workHistoryService.get(userId, tid);
    }

    /**
     * 设置用户的简历设置
     *
     * @param obj
     */
    public void setWorkHistory(Long userId, TUserWorkHistory obj) {
        workHistoryService.set(userId, obj);
    }


    /**
     * 删除工作经历
     * d
     *
     * @param userId
     * @param tid
     */
    public void delWorkHistory(Long userId, Long tid) {
        workHistoryService.del(userId, tid);
    }


    /**
     * 上传简历附件
     *
     * @param userId
     * @param cvPath
     */
    public void cvUpload(Long userId, String cvPath) {
        TLoginUser user = userService.get(userId);
        if (!this.isEmptyValue(user.getCvPath())) {
            COSClientService.deleFile(user.getCvPath());
        }

        user.setCvPath(cvPath);
        userService.update(user);
    }

}
