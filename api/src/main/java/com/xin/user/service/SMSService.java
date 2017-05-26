package com.xin.user.service;

import com.xin.common.BaseService;
import com.xin.db.common.Page;
import com.xin.db.dao.TSMSLogMapper;
import com.xin.db.entity.TSMSLog;
import com.xin.db.entity.TSMSLogExample;
import com.zhenhr.common.ParameterException;
import com.zhenhr.common.ToUserException;
import com.zhenhr.tools.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

;

/**
 * 短信 服务
 *
 * @author sgy
 */
@Service
public class SMSService extends BaseService {
    @Autowired
    private TSMSLogMapper shortMsgMapper;

    /**
     * 获取已经发送的验证码，10分钟之内不变
     *
     * @param mobile
     * @return
     */
    public String getRedisCode(String mobile) {

//        String code = smsCacheService.getCacheSms(mobile);
        TSMSLogExample exam = new TSMSLogExample();
        exam.createCriteria().andMobileLike(mobile);
        exam.setOrderByClause("create_time DESC");
        exam.setPage(new Page(1, 1));

        List<TSMSLog> list = shortMsgMapper.selectByExample(exam);
        if (this.isEmptyList(list)) {
            return null;
        }

        TSMSLog log = list.get(0);
        Long min = (new Date().getTime() - log.getCreateTime().getTime()) / 1000 / 60;
        if (min < 10) {
            return log.getCode();
        }

        return null;
    }

    public int getErrorCount(String mobile) {
//        String count = smsCacheService.getErrorCodeCount(mobile);
//        if (count == null) {
//            return 0;
//        }
//
//        return Integer.valueOf(count);
        return 0;
    }

    public void setErrorCount(String mobile, int count) {
//        smsCacheService.setErrorCodeCount(mobile, count);
    }

    /**
     * 生成验证码
     */
    public String generalCode() {
        String code;
        code = RandomUtils.generateNumber(4);
        return code;
    }

    /**
     * 写redis
     *
     * @param mobile
     * @param code
     */
    public void writeRedis(String mobile, String code) {
//        smsCacheService.setCacheSms(mobile, code);
    }

    /**
     * 发验证码
     *
     * @param mobile
     * @param code
     */
    public void writeDB(String mobile, String code) {

        TSMSLog msg = new TSMSLog();
        msg.setMobile(mobile);
        msg.setCode(code);
        msg.setSource("wechat");
        Date date = new Date();
        msg.setCreateTime(date);
        shortMsgMapper.insert(msg);
    }

    /**
     * 检查验证码
     */
    public boolean checkCode(String mobile, String code) {
        String dbCode = this.getRedisCode(mobile);
        if (dbCode == null) {
            return false;
        }

        if (dbCode.equals(code)) {
            return true;
        }

        return false;
    }

    public boolean verify(String smsCode, String mobile) {
        if (smsCode == null) {
            throw new ParameterException("短信验证码不能为空");
        }

        // 检测验证码
        String cacheCode = this.getRedisCode(mobile);
        if (cacheCode == null) {
            throw new ToUserException("请先获取短信验证码", "smscode");
        }

        smsCode = smsCode.trim();
        if (!smsCode.equals(cacheCode)) {
            throw new ToUserException("您输入的短信验证码不正确", "smscode");
        }

        return true;

    }

}
