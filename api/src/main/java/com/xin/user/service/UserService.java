package com.xin.user.service;

import com.xin.common.BaseService;
import com.xin.db.dao.TLoginUserMapper;
import com.xin.db.entity.TLoginUser;
import com.xin.db.entity.TLoginUserExample;
import com.xin.system.SystemService;
import com.xin.tools.ObjectUtils;
import com.xin.tools.common.ParameterException;
import com.xin.tools.common.TPErrorCodeGeneral;
import com.xin.tools.common.ToUserException;
import com.xin.user.dao.LoginOKVO;
import com.xin.webservice.SMSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by guoyongshi on 17/5/22.
 */
@Service
public class UserService extends BaseService {
    @Autowired
    TLoginUserMapper userMapper;

    //
    @Autowired
    private SMSService smsService;
    @Autowired
    SystemService systemService;
    @Autowired
    TokenService tokenService;

    /**
     * 发送验证码
     *
     * @param mobile
     */
    public String sendCode(String mobile) {

        this.isValidMobie(mobile);
        mobile = mobile.trim();
        int errorCount = smsService.getErrorCount(mobile);
        if (errorCount >= 10) {
            throw new ToUserException("由于您输入的验证码错误次数超限，请等待5分钟后再试", null);
        }

        // 1 获取上次发送的码
        boolean bFromDb = true;
        String code = smsService.getRedisCode(mobile);
        if (code == null) {
            bFromDb = false;
            code = smsService.generalCode();
        }

        if (!systemService.isTestMode()) {
            String smsResult = SMSClient.sendSMSCode(mobile, code);
            if (smsResult != null) {
                throw new ToUserException(smsResult, null);
            }
        }

        if (!bFromDb) {
            smsService.writeRedis(mobile, code);
        }

        smsService.writeDB(mobile, code);
        if (systemService.isTestMode()) {
            return code;
        } else {
            return null;
        }
    }

    public LoginOKVO mobileRegister(String mobile, String smscode, String pwd,
                                    String ip) {
        this.isValidMobie(mobile);

        if (smscode == null) {
            throw new ParameterException("短信验证码不能为空");
        }

        // 检测验证码
        String cacheCode = smsService.getRedisCode(mobile);
        if (cacheCode == null) {
            throw new ToUserException("请先获取短信验证码", "smscode");
        }

        mobile = mobile.trim();
        smscode = smscode.trim();
        if (!smscode.equals(cacheCode)) {
            throw new ToUserException("您输入的短信验证码不正确", "smscode");
        }

        TLoginUser user = null;
        // 检测手机号
        TLoginUserExample example = new TLoginUserExample();
        example.createCriteria().andMobileEqualTo(mobile);
        List<TLoginUser> list = userMapper.selectByExample(example);
        boolean firstFlag = false;
        if (list == null || list.size() == 0) {
            // 注册
            firstFlag = true;
            user = new TLoginUser();
            user.setName(mobile);
            user.setMobile(mobile);
            user.setPwd(pwd);
            user.setCreateTime(new Date());
            user.setState("1");
        } else { // login
            user = list.get(0);
            if (user.getState().equals("0")) {
                throw new ToUserException("您的帐户已被禁用", null);
            } else if (user.getState().equals("2")) {
                throw new ToUserException("您的帐户已被冻结", null);
            }
        }

        if (firstFlag) {
            userMapper.insertSelective(user);
//            this.runRegisterTask(user.getUserId());
        } else {
            user.setPwd(pwd);
            userMapper.updateByPrimaryKey(user);
        }

        return this.loginSucceed(user, mobile, ip);

    }

    /**
     * 登录
     *
     * @param
     * @param pwd
     * @return
     */
    public LoginOKVO mobileLogin(String mobile, String pwd, String ip) {
        this.isValidMobie(mobile);
        mobile = mobile.trim();

        if (pwd == null) {
            throw new ToUserException("密码不能为空",
                    TPErrorCodeGeneral.Error_ToUser_Invalid_PWD);
        }


        TLoginUser user = null;
        // 检测手机号
        TLoginUserExample example = new TLoginUserExample();
        example.createCriteria().andMobileEqualTo(mobile);
        List<TLoginUser> list = userMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            throw new ToUserException("手机号未注册",
                    TPErrorCodeGeneral.Error_ToUser_Invalid_Mobile);
        } else { // login
            user = list.get(0);
            if (user.getState().equals("0")) {
                throw new ToUserException("您的帐户已被禁用", null);
            } else if (user.getState().equals("2")) {
                throw new ToUserException("您的帐户已被冻结", null);
            }
            if (!user.getPwd().equals(pwd)) {
                throw new ToUserException("您输入的密码不正确",
                        TPErrorCodeGeneral.Error_ToUser_Invalid_PWD);
            }
        }

        return this.loginSucceed(user, mobile, ip);
    }

    /**
     * 重设密码
     *
     * @param mobile
     * @param smscode
     * @param pwd
     * @param ip
     * @return
     */
    public LoginOKVO pwdReset(String mobile, String smscode, String pwd, String ip) {
        this.isValidMobie(mobile);

        if (smscode == null) {
            throw new ParameterException("短信验证码不能为空");
        }

        // 检测验证码
        String cacheCode = smsService.getRedisCode(mobile);
        if (cacheCode == null) {
            throw new ToUserException("请先获取短信验证码", "smscode");
        }

        if (!smscode.equals(cacheCode)) {
            throw new ToUserException("您输入的短信验证码不正确", "smscode");
        }

        TLoginUser user = null;
        // 检测手机号
        TLoginUserExample example = new TLoginUserExample();
        example.createCriteria().andMobileEqualTo(mobile);
        List<TLoginUser> list = userMapper.selectByExample(example);
        boolean firstFlag = false;
        if (list == null || list.size() == 0) {
            // 注册
            firstFlag = true;
            user = new TLoginUser();
            user.setName(mobile);
            user.setMobile(mobile);
            user.setPwd(pwd);
            user.setCreateTime(new Date());
            user.setState("1");
        } else { // login
            user = list.get(0);
            if (user.getState().equals("0")) {
                throw new ToUserException("您的帐户已被禁用", null);
            } else if (user.getState().equals("2")) {
                throw new ToUserException("您的帐户已被冻结", null);
            }
        }

        if (firstFlag) {
            userMapper.insertSelective(user);
        } else {
            user.setPwd(pwd);
            userMapper.updateByPrimaryKey(user);
        }

        return this.loginSucceed(user, mobile, ip);
    }

    private LoginOKVO loginSucceed(TLoginUser user, String mobile, String ip) {
        if (user.getToken() != null) { // 旧token失效
            tokenService.deleteToken(user.getToken());
        }

        String token = tokenService.generateToken(user.getUserId());
        user.setToken(token);
        userMapper.updateByPrimaryKey(user);

        LoginOKVO vo = new LoginOKVO();
        vo.setToken(token);

        // 发送注册邮件
        return vo;
    }


    /**
     * 登出
     *
     * @param
     */
    public void logout(String token) {
        String userId = tokenService.getUserId(token);
        if (this.isEmptyValue(userId)) {
            return;
        }

        TLoginUser user = this.get(Long.valueOf(userId));
        if (user != null) {
            user.setToken(null);
            userMapper.updateByPrimaryKey(user);
        }

        tokenService.deleteToken(token);
    }

    public void set(TLoginUser user) {
        TLoginUser old = userMapper.selectByPrimaryKey(user.getUserId());
        ObjectUtils.mergeSameClassValue(user, old);
        userMapper.updateByPrimaryKey(old);
    }


    public TLoginUser get(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

}
