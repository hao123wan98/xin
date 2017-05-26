package com.xin.common;

import com.zhenhr.common.ParameterException;
import com.zhenhr.common.TPErrorCodeGeneral;
import com.zhenhr.common.ToUserException;

import java.util.List;

/**
 * @author guoyongshi
 */
public class BaseService {

    public void judgeTid(Object tid) {
        if (tid == null) {
            throw new ParameterException("无效的tid");
        }
    }

    public void judgeZhEid(String zhEid) {
        if (zhEid == null) {
            throw new ParameterException("无效的zhEid");
        }
    }

    public void judgeCompanyId(Long companyId, Long dbCompanyId) {
        if (!companyId.equals(dbCompanyId)) {
            throw new ToUserException("您无权操作该纪录", null);
        }
    }

    public <T> boolean isEmptyList(List<T> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    public boolean isEmptyValue(String obj) {
        if (obj == null || obj.isEmpty()) {
            return true;
        }
        return false;
    }

    public void isValidMobie(String mobile) {
        if (mobile == null) {
            throw new ToUserException("手机号不能为空！",
                    TPErrorCodeGeneral.Error_ToUser_Invalid_Mobile);
        }

        String regex = "^[1][3,4,5,7,8]\\d{9}$";
        Boolean isMobile = mobile.matches(regex);
        if (!isMobile) {
            throw new ToUserException("无效的手机号",
                    TPErrorCodeGeneral.Error_ToUser_Invalid_Mobile);
        }
    }

    public void isValidCompanyId(Long companyId) {
        if (companyId == null || companyId <= 0) {
            throw this.ToUserNoCompanyInfo();
        }
    }

    public ToUserException ToUserNoCompanyInfo() {
        return new ToUserException("企业Id不存在", TPErrorCodeGeneral.Error_ToUser_No_Company,
                null);
    }

    /**
     * 获取值
     *
     * @param value
     * @return
     */
    protected Float getValue(Float value) {
        if (value == null) {
            return 0.0f;
        }
        return value;
    }

    protected int getValue(Integer value) {
        if (value == null) {
            return 0;
        }
        return value;
    }

    protected long getValue(Long value) {
        if (value == null) {
            return 0;
        }
        return value;
    }

    /**
     * 获取工资月份
     *
     * @param salaryDate
     * @return
     */
    protected String getSalaryDate(String salaryDate) {
        salaryDate = salaryDate.replaceAll("/", "-");
        String[] list = salaryDate.split("-");
        if (list.length < 2) {
            throw new ParameterException("无效的月时间");
        }

        if (list[1].length() == 1) {
            return list[0] + "-0" + list[1];
        } else {
            return list[0] + "-" + list[1];
        }

    }

    public float convertFloat(Float value) {
        if (value == null) {
            return 0f;
        } else {
            long l1 = Math.round(value * 100); // 四舍五入
            return l1 / 100.0f;
        }
    }

    public boolean isTrue(Boolean value) {
        if (value == null || value == false) {
            return false;
        }

        return true;
    }

    public boolean isNumber(String value) {
        if (this.isEmptyValue(value)) {
            return false;
        }

        try {
            Integer.valueOf(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isValidDateStr(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        if (str.equals("0")) {
            return false;
        }

        if (str.contains("null")) {
            return false;
        }

        if (str.contains("undefined")) {
            return false;
        }

        if (str.contains("0-0")) {
            return false;
        }

        return true;
    }

}
