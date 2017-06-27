package com.xin.esp.service;

import com.xin.common.BaseService;
import com.xin.db.dao.TLoginUserMapper;
import com.xin.db.dao.mUsersMapper;
import com.xin.db.entity.TLoginUser;
import com.xin.db.entity.TLoginUserExample;
import com.xin.db.entity.mUsers;
import com.xin.db.entity.mUsersExample;
import com.xin.system.service.SystemService;
import com.xin.user.dao.LoginOKVO;
import com.xin.user.service.TokenService;
import com.xin.webservice.EmailClient;
import com.xin.webservice.EmailSendAccountVO;
import com.xin.webservice.SMSClient;
import com.zhenhr.common.ParameterException;
import com.zhenhr.common.TPErrorCodeGeneral;
import com.zhenhr.common.ToUserException;
import com.zhenhr.tools.Base64;
import com.zhenhr.tools.ObjectUtils;
import com.zhenhr.tools.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by guoyongshi on 17/5/22.
 */
@Service
public class EspLoginUserService extends BaseService {

    @Autowired
    mUsersMapper userMapper;

    //
    @Autowired
    SystemService systemService;
    @Autowired
    TokenService tokenService;


    /**
     * 登录
     *
     * @param email
     * @param pwd
     * @return
     */
    public LoginOKVO login(String email, String pwd) {
        email = email.trim();

        mUsersExample exam = new mUsersExample();
        exam.createCriteria().andEmailEqualTo(email).andPwdEqualTo(pwd);
        List<mUsers> list = userMapper.selectByExample(exam);
        if (this.isEmptyList(list)) {
            throw new ToUserException("登录失败，无效的账户或者密码", null);
        }

        mUsers user = list.get(0);
        if (user.getState().equals("2")) {
            throw new ToUserException("您的账户已经被停用！", null);
        }

        String token = tokenService.generateToken(Long.valueOf(user.getTid()));

        LoginOKVO vo = new LoginOKVO();
        vo.setToken(token);
        vo.setChangePwdFlag(user.getFirstLoginFlag());


        return vo;
    }

    /**
     * 修改密码
     *
     * @param userId
     * @param oldPwd
     * @param newPwd
     * @return
     */
    public LoginOKVO changePwd(Long userId, String oldPwd, String newPwd) {
        if (this.isEmptyValue(oldPwd)) {
            throw new ToUserException("旧密码不能为空", "oldPwd");
        }

        if (this.isEmptyValue(newPwd)) {
            throw new ToUserException("新密码不能为空", "newPwd");
        }

        mUsers user = userMapper.selectByPrimaryKey(userId.intValue());
        if (!user.getPwd().equals(oldPwd)) {
            throw new ToUserException("原密码不正确", "oldPwd");
        }

        user.setPwd(newPwd);
        user.setFirstLoginFlag(false);
        userMapper.updateByPrimaryKey(user);

        // 重新生成token
        String token = tokenService.generateToken(Long.valueOf(user.getTid()));

        LoginOKVO vo = new LoginOKVO();
        vo.setToken(token);

        return vo;
    }

    /**
     * 登出
     *
     * @param
     */
    public void logout(String token) {
        tokenService.deleteToken(token);
    }


}
