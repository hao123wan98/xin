package com.xin.user.dao;

public class LoginOKVO {
    private String token;
    private String role;
    private boolean changePwdFlag = false;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isChangePwdFlag() {
        return changePwdFlag;
    }

    public void setChangePwdFlag(boolean changePwdFlag) {
        this.changePwdFlag = changePwdFlag;
    }
}
