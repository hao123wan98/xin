package com.xin.system;

import com.xin.common.BaseService;
import com.zhenhr.tools.PropertiesUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class SystemService extends BaseService {
    private static String static_test_mode = null;


    public boolean isTestMode() {
        if (static_test_mode == null) {
            Properties prop = PropertiesUtils.getProperties("app.properties");
            static_test_mode = prop.get("test_mode").toString();
        }

        if (static_test_mode.equals("true")) {
            return true;
        }

        return false;
    }
}
