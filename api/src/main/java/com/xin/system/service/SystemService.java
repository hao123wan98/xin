package com.xin.system.service;

import com.xin.common.BaseService;
import com.xin.webservice.EmailSendAccountVO;
import com.zhenhr.tools.PropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class SystemService extends BaseService {
    @Value("${test_mode}")
    private String test_mode;

    @Autowired
    Environment env;


    public boolean isTestMode() {
        if (test_mode.equals("true")) {
            return true;
        }

        return false;
    }

    public EmailSendAccountVO getSysEmailAccount() {
        EmailSendAccountVO vo = new EmailSendAccountVO();
        vo.setShowName(env.getProperty("email.showName"));
        vo.setPwd(env.getProperty("email.pwd"));
        vo.setEmail(env.getProperty("email.account"));
        vo.setSmtp(env.getProperty("email.smtp"));
        return vo;
    }


}
