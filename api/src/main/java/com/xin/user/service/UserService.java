package com.xin.user.service;

import com.xin.db.dao.TLoginUserMapper;
import com.xin.db.entity.TLoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by guoyongshi on 17/5/22.
 */
@Service
public class UserService {
    @Autowired
    TLoginUserMapper loginUserMapper;


    public void set(TLoginUser user) {

    }

    public TLoginUser get(Long userId) {

        return null;
    }

}
