package com.xin.user.service;

import com.xin.common.BaseService;
import com.xin.db.dao.TLoginUserMapper;
import com.xin.db.entity.TLoginUser;
import com.xin.db.entity.TLoginUserExample;
import com.zhenhr.tools.HmacshaUtils;
import com.zhenhr.tools.PropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TokenService extends BaseService {
    private static int expireSecond = 0;

    @Autowired
    TLoginUserMapper userMapper;

    /**
     * 生成Token
     *
     * @param userId
     * @return
     */
    public String generateToken(Long userId) {
        String tmp = UUID.randomUUID().toString().replaceAll("-", "");
        String token = HmacshaUtils.getToken("xin", tmp, 1);

        return token;
    }

    public String generateToken(String value) {
        String tmp = UUID.randomUUID().toString().replaceAll("-", "");
        String token = HmacshaUtils.getToken("xin", tmp, 1);

        return token;
    }


    /**
     * 获取Token
     *
     * @return
     */
    public String getUserId(String token) {
        if (token == null) {
            return null;
        }

        TLoginUserExample exam = new TLoginUserExample();
        exam.createCriteria().andTokenEqualTo(token);

        List<TLoginUser> list = userMapper.selectByExample(exam);
        if (this.isEmptyList(list)) {
            return null;
        }

        TLoginUser user = list.get(0);
        return String.valueOf(user.getUserId());
    }


    public void deleteToken(String token) {
        //
    }

    private int getExpireSecond() {
        String value = PropertiesUtils.getZhenPinProperties().get("token_expire_time")
                .toString();
        value = value.trim();

        int token_expire_time = Integer.valueOf(value);
        return token_expire_time;

    }

    public static void main(String[] args) {
        TokenService service = new TokenService();
        String token = service.generateToken((long) 1);
        System.out.print(token);
    }
}
