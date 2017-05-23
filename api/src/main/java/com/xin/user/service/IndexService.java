package com.xin.user.service;

import com.xin.tools.common.ParameterException;
import org.springframework.stereotype.Service;

/**
 * Created by guoyongshi on 17/5/22.
 */
@Service
public class IndexService {

    public void test() {
        throw new ParameterException("aaaaa");
    }
}
