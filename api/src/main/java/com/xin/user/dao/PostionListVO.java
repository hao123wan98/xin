package com.xin.user.dao;

import com.xin.db.entity.TCompanyPostion;

/**
 * Created by guoyongshi on 17/6/2.
 */
public class PostionListVO extends TCompanyPostion {
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
