package com.xin.webservice;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SMSClient {
    public static String sms_http_url = "http://gw.api.taobao.com/router/rest";
    public static String sms_app_key = "23409246";
    public static String sms_app_secret = "ccd7a9f7a656d5e0853c6cc4d5f33fed";

    /**
     * 发送短信注册码
     *
     * @param mobile
     * @param code
     * @throws ApiException
     */
    public static String sendSMSCode(String mobile, String code) {
        TaobaoClient client = new DefaultTaobaoClient(sms_http_url,
                sms_app_key, sms_app_secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsType("normal");
        req.setSmsFreeSignName("爱催收");
        req.setSmsParamString("{\"code\":\"" + code + "\"}");
        req.setRecNum(mobile);
        req.setSmsTemplateCode("SMS_12490180");
        AlibabaAliqinFcSmsNumSendResponse rsp;
        try {
            rsp = client.execute(req);
            if (rsp.getErrorCode() == null || "0".equals(rsp.getErrorCode())) {
                return null;
            } else {
                return errCodeToStr(rsp.getErrorCode());
            }
        } catch (ApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "验证码发送失败";
        }
    }

    private static String errCodeToStr(String code) {
        if ("15".equals(code)) {
            return "短信发送太频繁，请稍后再试";
        }
        return "验证码发送失败";
    }

    public static void main(String[] args) {
        String sms = SMSClient.sendSMSCode("13699274927", "1456");
        if (sms != null) {
            System.out.println(sms);
        }
    }

}
