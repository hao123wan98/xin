package com.xin.user.dao;

import com.xin.db.entity.TLoginUser;
import com.xin.db.entity.TUserWorkHistory;

import java.util.Date;
import java.util.List;

/**
 * Created by guoyongshi on 17/6/13.
 */
public class UserCVInfoVO extends TLoginUser {

    private List<TUserWorkHistory> workHistoryList;

    public List<TUserWorkHistory> getWorkHistoryList() {
        return workHistoryList;
    }

    public void setWorkHistoryList(List<TUserWorkHistory> workHistoryList) {
        this.workHistoryList = workHistoryList;
    }

    public void addWorkHistory(TUserWorkHistory obj) {

    }

}
