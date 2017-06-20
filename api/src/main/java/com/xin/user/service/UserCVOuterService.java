package com.xin.user.service;

import com.xin.common.BaseService;
import com.xin.db.dao.TUserWorkHistoryMapper;
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
public class UserCVOuterService extends BaseService {
    @Autowired
    UserService userService;
    @Autowired
    UserCVService userCVService;

    /**
     * 获取简历详情
     *
     * @param code
     * @return
     */
    public UserCVInfoVO getCVInfo(String code) {
        Long userId = userService.codeToUserId(code);
        return userCVService.getCVInfo(userId);
    }

    /**
     * 获取简历基本信息
     *
     * @param code
     * @return
     */
    public UserCVInfoVO getCVBsic(String code) {
        Long userId = userService.codeToUserId(code);
        return userCVService.getCVBsic(userId);
    }


    /**
     * 更新简历基本信息
     *
     * @param obj
     */
    public void updateCVBase(String code, UserCVInfoVO obj) {
        Long userId = userService.codeToUserId(code);
        userCVService.updateCVBase(userId, obj);
    }

    /**
     * 获取用户的工作简历列表
     *
     * @param code
     * @return
     */
    public List<TUserWorkHistory> getCVWorkHistoryList(String code) {
        Long userId = userService.codeToUserId(code);
        return userCVService.getCVWorkHistoryList(userId);
    }


    public TUserWorkHistory getWorkHistory(String code, Long tid) {
        Long userId = userService.codeToUserId(code);
        return userCVService.getWorkHistory(userId, tid);
    }

    /**
     * 设置用户的简历设置
     *
     * @param code
     * @param obj
     */
    public void setWorkHistory(String code, TUserWorkHistory obj) {
        Long userId = userService.codeToUserId(code);
        userCVService.setWorkHistory(userId, obj);
    }


    /**
     * 删除工作经历
     * d
     *
     * @param code
     * @param tid
     */
    public void delWorkHistory(String code, Long tid) {
        Long userId = userService.codeToUserId(code);
        userCVService.delWorkHistory(userId, tid);
    }


    /**
     * 上传简历附件
     *
     * @param code
     * @param cvPath
     */
    public void cvUpload(String code, String cvPath) {
        Long userId = userService.codeToUserId(code);
        userCVService.cvUpload(userId, cvPath);
    }

}
