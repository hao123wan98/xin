package com.xin.system.dao;

/**
 * Created by guoyongshi on 17/5/26.
 */
public class FileUploadDao {
    private String key;
    private String sign;
    private String url = "";
    private String uploadUrl = ""; // 上传的url

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }
}
