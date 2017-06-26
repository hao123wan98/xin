package com.xin.user.service;

import com.xin.common.BaseService;
import com.xin.db.dao.TLoginUserMapper;
import com.xin.db.dao.TTokenMapper;
import com.xin.db.entity.TLoginUser;
import com.xin.db.entity.TLoginUserExample;
import com.xin.db.entity.TToken;
import com.xin.db.entity.TTokenExample;
import com.zhenhr.common.TokenException;
import com.zhenhr.tools.HmacshaUtils;
import com.zhenhr.tools.PropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TokenService extends BaseService {
    private static int expireSecond = 0;

    @Autowired
    TTokenMapper tokenMapper;

    /**
     * 生成Token
     *
     * @param userId
     * @return
     */
    public String generateToken(Long userId) {
        String tmp = UUID.randomUUID().toString().replaceAll("-", "");
        String token = HmacshaUtils.getToken("xin", tmp, 1);

        this.insertToDB(token, userId);
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

        TToken o = tokenMapper.selectByPrimaryKey(token);
        if (o == null) {
            return null;
        }
        return o.getValue();
    }


    public void deleteToken(String token) {
        //
        tokenMapper.deleteByPrimaryKey(token);
    }


    private int getExpireSecond() {
        String value = PropertiesUtils.getZhenPinProperties().get("token_expire_time")
                .toString();
        value = value.trim();

        int token_expire_time = Integer.valueOf(value);
        return token_expire_time;

    }

    private void insertToDB(String token, Long userId) {
        TTokenExample exam = new TTokenExample();
        exam.createCriteria().andValueEqualTo(String.valueOf(userId));
        tokenMapper.deleteByExample(exam);


        TToken record = new TToken();
        record.setToken(token);
        record.setValue(String.valueOf(userId));
        record.setCreateTime(new Date());
        tokenMapper.insert(record);
    }


    public static void main(String[] args) {
        TokenService service = new TokenService();
        String token = service.generateToken((long) 1);
        System.out.print(token);
    }
}
