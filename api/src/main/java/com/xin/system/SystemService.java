package com.xin.system;

import com.xin.common.BaseService;
import com.zhenhr.tools.PropertiesUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class SystemService extends BaseService {
    @Value("${test_mode}")
    private String test_mode;


    public boolean isTestMode() {
        if (test_mode.equals("true")) {
            return true;
        }

        return false;
    }
}
