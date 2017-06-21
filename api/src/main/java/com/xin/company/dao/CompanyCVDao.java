package com.xin.company.dao;

import com.zhenhr.tools.DateUtil;
import com.zhenhr.tools.ObjectMapConvert;
import com.zhenhr.tools.ObjectUtils;

import java.util.Map;

/**
 * Created by guoyongshi on 17/6/21.
 */
public class CompanyCVDao {
    private Long tid;
    private Long userId;
    private String name;
    private String gender;
    private String avatar;
    private String lastCompanyName; //最后工作单位
    private String lastCompanyPos;//最后工作职位


    private String workStartDate;
    private Integer workYears; //工作年限
    private String degreeTop; //最高学历

    private Long postionId; //职位编号
    private String postionName;//职位名称

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLastCompanyName() {
        return lastCompanyName;
    }

    public void setLastCompanyName(String lastCompanyName) {
        this.lastCompanyName = lastCompanyName;
    }

    public String getLastCompanyPos() {
        return lastCompanyPos;
    }

    public void setLastCompanyPos(String lastCompanyPos) {
        this.lastCompanyPos = lastCompanyPos;
    }

    public Integer getWorkYears() {
        return workYears;
    }

    public void setWorkYears(Integer workYears) {
        this.workYears = workYears;
    }

    public String getDegreeTop() {
        return degreeTop;
    }

    public void setDegreeTop(String degreeTop) {
        this.degreeTop = degreeTop;
    }

    public String getWorkStartDate() {
        return workStartDate;
    }

    public void setWorkStartDate(String workStartDate) {
        this.workStartDate = workStartDate;
    }

    public Long getPostionId() {
        return postionId;
    }

    public void setPostionId(Long postionId) {
        this.postionId = postionId;
    }

    public String getPostionName() {
        return postionName;
    }

    public void setPostionName(String postionName) {
        this.postionName = postionName;
    }

    public static CompanyCVDao fromObject(Map<String, Object> map) {
        CompanyCVDao dao = ObjectMapConvert.mapToObject(map, CompanyCVDao.class);
        if (dao.getWorkYears() != null) {
            dao.setWorkYears(DateUtil.yearsToNow(dao.getWorkStartDate()));
        }
        return dao;
    }
}
