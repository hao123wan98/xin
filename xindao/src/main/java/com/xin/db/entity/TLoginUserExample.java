package com.xin.db.entity;

import com.xin.db.common.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TLoginUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public TLoginUserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setPage(Page page) {
        this.page=page;
    }

    public Page getPage() {
        return page;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andPwdIsNull() {
            addCriterion("pwd is null");
            return (Criteria) this;
        }

        public Criteria andPwdIsNotNull() {
            addCriterion("pwd is not null");
            return (Criteria) this;
        }

        public Criteria andPwdEqualTo(String value) {
            addCriterion("pwd =", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotEqualTo(String value) {
            addCriterion("pwd <>", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThan(String value) {
            addCriterion("pwd >", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThanOrEqualTo(String value) {
            addCriterion("pwd >=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThan(String value) {
            addCriterion("pwd <", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThanOrEqualTo(String value) {
            addCriterion("pwd <=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLike(String value) {
            addCriterion("pwd like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotLike(String value) {
            addCriterion("pwd not like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdIn(List<String> values) {
            addCriterion("pwd in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotIn(List<String> values) {
            addCriterion("pwd not in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdBetween(String value1, String value2) {
            addCriterion("pwd between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotBetween(String value1, String value2) {
            addCriterion("pwd not between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(String value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(String value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(String value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(String value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(String value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(String value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLike(String value) {
            addCriterion("gender like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotLike(String value) {
            addCriterion("gender not like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<String> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<String> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(String value1, String value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(String value1, String value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(String value) {
            addCriterion("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(String value) {
            addCriterion("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(String value) {
            addCriterion("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(String value) {
            addCriterion("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(String value) {
            addCriterion("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(String value) {
            addCriterion("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLike(String value) {
            addCriterion("birthday like", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotLike(String value) {
            addCriterion("birthday not like", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<String> values) {
            addCriterion("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<String> values) {
            addCriterion("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(String value1, String value2) {
            addCriterion("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(String value1, String value2) {
            addCriterion("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andOrigoIsNull() {
            addCriterion("origo is null");
            return (Criteria) this;
        }

        public Criteria andOrigoIsNotNull() {
            addCriterion("origo is not null");
            return (Criteria) this;
        }

        public Criteria andOrigoEqualTo(String value) {
            addCriterion("origo =", value, "origo");
            return (Criteria) this;
        }

        public Criteria andOrigoNotEqualTo(String value) {
            addCriterion("origo <>", value, "origo");
            return (Criteria) this;
        }

        public Criteria andOrigoGreaterThan(String value) {
            addCriterion("origo >", value, "origo");
            return (Criteria) this;
        }

        public Criteria andOrigoGreaterThanOrEqualTo(String value) {
            addCriterion("origo >=", value, "origo");
            return (Criteria) this;
        }

        public Criteria andOrigoLessThan(String value) {
            addCriterion("origo <", value, "origo");
            return (Criteria) this;
        }

        public Criteria andOrigoLessThanOrEqualTo(String value) {
            addCriterion("origo <=", value, "origo");
            return (Criteria) this;
        }

        public Criteria andOrigoLike(String value) {
            addCriterion("origo like", value, "origo");
            return (Criteria) this;
        }

        public Criteria andOrigoNotLike(String value) {
            addCriterion("origo not like", value, "origo");
            return (Criteria) this;
        }

        public Criteria andOrigoIn(List<String> values) {
            addCriterion("origo in", values, "origo");
            return (Criteria) this;
        }

        public Criteria andOrigoNotIn(List<String> values) {
            addCriterion("origo not in", values, "origo");
            return (Criteria) this;
        }

        public Criteria andOrigoBetween(String value1, String value2) {
            addCriterion("origo between", value1, value2, "origo");
            return (Criteria) this;
        }

        public Criteria andOrigoNotBetween(String value1, String value2) {
            addCriterion("origo not between", value1, value2, "origo");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andWechatIsNull() {
            addCriterion("wechat is null");
            return (Criteria) this;
        }

        public Criteria andWechatIsNotNull() {
            addCriterion("wechat is not null");
            return (Criteria) this;
        }

        public Criteria andWechatEqualTo(String value) {
            addCriterion("wechat =", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotEqualTo(String value) {
            addCriterion("wechat <>", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatGreaterThan(String value) {
            addCriterion("wechat >", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatGreaterThanOrEqualTo(String value) {
            addCriterion("wechat >=", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatLessThan(String value) {
            addCriterion("wechat <", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatLessThanOrEqualTo(String value) {
            addCriterion("wechat <=", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatLike(String value) {
            addCriterion("wechat like", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotLike(String value) {
            addCriterion("wechat not like", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatIn(List<String> values) {
            addCriterion("wechat in", values, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotIn(List<String> values) {
            addCriterion("wechat not in", values, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatBetween(String value1, String value2) {
            addCriterion("wechat between", value1, value2, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotBetween(String value1, String value2) {
            addCriterion("wechat not between", value1, value2, "wechat");
            return (Criteria) this;
        }

        public Criteria andWorkStartDateIsNull() {
            addCriterion("work_start_date is null");
            return (Criteria) this;
        }

        public Criteria andWorkStartDateIsNotNull() {
            addCriterion("work_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andWorkStartDateEqualTo(String value) {
            addCriterion("work_start_date =", value, "workStartDate");
            return (Criteria) this;
        }

        public Criteria andWorkStartDateNotEqualTo(String value) {
            addCriterion("work_start_date <>", value, "workStartDate");
            return (Criteria) this;
        }

        public Criteria andWorkStartDateGreaterThan(String value) {
            addCriterion("work_start_date >", value, "workStartDate");
            return (Criteria) this;
        }

        public Criteria andWorkStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("work_start_date >=", value, "workStartDate");
            return (Criteria) this;
        }

        public Criteria andWorkStartDateLessThan(String value) {
            addCriterion("work_start_date <", value, "workStartDate");
            return (Criteria) this;
        }

        public Criteria andWorkStartDateLessThanOrEqualTo(String value) {
            addCriterion("work_start_date <=", value, "workStartDate");
            return (Criteria) this;
        }

        public Criteria andWorkStartDateLike(String value) {
            addCriterion("work_start_date like", value, "workStartDate");
            return (Criteria) this;
        }

        public Criteria andWorkStartDateNotLike(String value) {
            addCriterion("work_start_date not like", value, "workStartDate");
            return (Criteria) this;
        }

        public Criteria andWorkStartDateIn(List<String> values) {
            addCriterion("work_start_date in", values, "workStartDate");
            return (Criteria) this;
        }

        public Criteria andWorkStartDateNotIn(List<String> values) {
            addCriterion("work_start_date not in", values, "workStartDate");
            return (Criteria) this;
        }

        public Criteria andWorkStartDateBetween(String value1, String value2) {
            addCriterion("work_start_date between", value1, value2, "workStartDate");
            return (Criteria) this;
        }

        public Criteria andWorkStartDateNotBetween(String value1, String value2) {
            addCriterion("work_start_date not between", value1, value2, "workStartDate");
            return (Criteria) this;
        }

        public Criteria andLastCompanyNameIsNull() {
            addCriterion("last_company_name is null");
            return (Criteria) this;
        }

        public Criteria andLastCompanyNameIsNotNull() {
            addCriterion("last_company_name is not null");
            return (Criteria) this;
        }

        public Criteria andLastCompanyNameEqualTo(String value) {
            addCriterion("last_company_name =", value, "lastCompanyName");
            return (Criteria) this;
        }

        public Criteria andLastCompanyNameNotEqualTo(String value) {
            addCriterion("last_company_name <>", value, "lastCompanyName");
            return (Criteria) this;
        }

        public Criteria andLastCompanyNameGreaterThan(String value) {
            addCriterion("last_company_name >", value, "lastCompanyName");
            return (Criteria) this;
        }

        public Criteria andLastCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("last_company_name >=", value, "lastCompanyName");
            return (Criteria) this;
        }

        public Criteria andLastCompanyNameLessThan(String value) {
            addCriterion("last_company_name <", value, "lastCompanyName");
            return (Criteria) this;
        }

        public Criteria andLastCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("last_company_name <=", value, "lastCompanyName");
            return (Criteria) this;
        }

        public Criteria andLastCompanyNameLike(String value) {
            addCriterion("last_company_name like", value, "lastCompanyName");
            return (Criteria) this;
        }

        public Criteria andLastCompanyNameNotLike(String value) {
            addCriterion("last_company_name not like", value, "lastCompanyName");
            return (Criteria) this;
        }

        public Criteria andLastCompanyNameIn(List<String> values) {
            addCriterion("last_company_name in", values, "lastCompanyName");
            return (Criteria) this;
        }

        public Criteria andLastCompanyNameNotIn(List<String> values) {
            addCriterion("last_company_name not in", values, "lastCompanyName");
            return (Criteria) this;
        }

        public Criteria andLastCompanyNameBetween(String value1, String value2) {
            addCriterion("last_company_name between", value1, value2, "lastCompanyName");
            return (Criteria) this;
        }

        public Criteria andLastCompanyNameNotBetween(String value1, String value2) {
            addCriterion("last_company_name not between", value1, value2, "lastCompanyName");
            return (Criteria) this;
        }

        public Criteria andLastCompanyPosIsNull() {
            addCriterion("last_company_pos is null");
            return (Criteria) this;
        }

        public Criteria andLastCompanyPosIsNotNull() {
            addCriterion("last_company_pos is not null");
            return (Criteria) this;
        }

        public Criteria andLastCompanyPosEqualTo(String value) {
            addCriterion("last_company_pos =", value, "lastCompanyPos");
            return (Criteria) this;
        }

        public Criteria andLastCompanyPosNotEqualTo(String value) {
            addCriterion("last_company_pos <>", value, "lastCompanyPos");
            return (Criteria) this;
        }

        public Criteria andLastCompanyPosGreaterThan(String value) {
            addCriterion("last_company_pos >", value, "lastCompanyPos");
            return (Criteria) this;
        }

        public Criteria andLastCompanyPosGreaterThanOrEqualTo(String value) {
            addCriterion("last_company_pos >=", value, "lastCompanyPos");
            return (Criteria) this;
        }

        public Criteria andLastCompanyPosLessThan(String value) {
            addCriterion("last_company_pos <", value, "lastCompanyPos");
            return (Criteria) this;
        }

        public Criteria andLastCompanyPosLessThanOrEqualTo(String value) {
            addCriterion("last_company_pos <=", value, "lastCompanyPos");
            return (Criteria) this;
        }

        public Criteria andLastCompanyPosLike(String value) {
            addCriterion("last_company_pos like", value, "lastCompanyPos");
            return (Criteria) this;
        }

        public Criteria andLastCompanyPosNotLike(String value) {
            addCriterion("last_company_pos not like", value, "lastCompanyPos");
            return (Criteria) this;
        }

        public Criteria andLastCompanyPosIn(List<String> values) {
            addCriterion("last_company_pos in", values, "lastCompanyPos");
            return (Criteria) this;
        }

        public Criteria andLastCompanyPosNotIn(List<String> values) {
            addCriterion("last_company_pos not in", values, "lastCompanyPos");
            return (Criteria) this;
        }

        public Criteria andLastCompanyPosBetween(String value1, String value2) {
            addCriterion("last_company_pos between", value1, value2, "lastCompanyPos");
            return (Criteria) this;
        }

        public Criteria andLastCompanyPosNotBetween(String value1, String value2) {
            addCriterion("last_company_pos not between", value1, value2, "lastCompanyPos");
            return (Criteria) this;
        }

        public Criteria andLastSalaryIsNull() {
            addCriterion("last_salary is null");
            return (Criteria) this;
        }

        public Criteria andLastSalaryIsNotNull() {
            addCriterion("last_salary is not null");
            return (Criteria) this;
        }

        public Criteria andLastSalaryEqualTo(String value) {
            addCriterion("last_salary =", value, "lastSalary");
            return (Criteria) this;
        }

        public Criteria andLastSalaryNotEqualTo(String value) {
            addCriterion("last_salary <>", value, "lastSalary");
            return (Criteria) this;
        }

        public Criteria andLastSalaryGreaterThan(String value) {
            addCriterion("last_salary >", value, "lastSalary");
            return (Criteria) this;
        }

        public Criteria andLastSalaryGreaterThanOrEqualTo(String value) {
            addCriterion("last_salary >=", value, "lastSalary");
            return (Criteria) this;
        }

        public Criteria andLastSalaryLessThan(String value) {
            addCriterion("last_salary <", value, "lastSalary");
            return (Criteria) this;
        }

        public Criteria andLastSalaryLessThanOrEqualTo(String value) {
            addCriterion("last_salary <=", value, "lastSalary");
            return (Criteria) this;
        }

        public Criteria andLastSalaryLike(String value) {
            addCriterion("last_salary like", value, "lastSalary");
            return (Criteria) this;
        }

        public Criteria andLastSalaryNotLike(String value) {
            addCriterion("last_salary not like", value, "lastSalary");
            return (Criteria) this;
        }

        public Criteria andLastSalaryIn(List<String> values) {
            addCriterion("last_salary in", values, "lastSalary");
            return (Criteria) this;
        }

        public Criteria andLastSalaryNotIn(List<String> values) {
            addCriterion("last_salary not in", values, "lastSalary");
            return (Criteria) this;
        }

        public Criteria andLastSalaryBetween(String value1, String value2) {
            addCriterion("last_salary between", value1, value2, "lastSalary");
            return (Criteria) this;
        }

        public Criteria andLastSalaryNotBetween(String value1, String value2) {
            addCriterion("last_salary not between", value1, value2, "lastSalary");
            return (Criteria) this;
        }

        public Criteria andCvPathIsNull() {
            addCriterion("cv_path is null");
            return (Criteria) this;
        }

        public Criteria andCvPathIsNotNull() {
            addCriterion("cv_path is not null");
            return (Criteria) this;
        }

        public Criteria andCvPathEqualTo(String value) {
            addCriterion("cv_path =", value, "cvPath");
            return (Criteria) this;
        }

        public Criteria andCvPathNotEqualTo(String value) {
            addCriterion("cv_path <>", value, "cvPath");
            return (Criteria) this;
        }

        public Criteria andCvPathGreaterThan(String value) {
            addCriterion("cv_path >", value, "cvPath");
            return (Criteria) this;
        }

        public Criteria andCvPathGreaterThanOrEqualTo(String value) {
            addCriterion("cv_path >=", value, "cvPath");
            return (Criteria) this;
        }

        public Criteria andCvPathLessThan(String value) {
            addCriterion("cv_path <", value, "cvPath");
            return (Criteria) this;
        }

        public Criteria andCvPathLessThanOrEqualTo(String value) {
            addCriterion("cv_path <=", value, "cvPath");
            return (Criteria) this;
        }

        public Criteria andCvPathLike(String value) {
            addCriterion("cv_path like", value, "cvPath");
            return (Criteria) this;
        }

        public Criteria andCvPathNotLike(String value) {
            addCriterion("cv_path not like", value, "cvPath");
            return (Criteria) this;
        }

        public Criteria andCvPathIn(List<String> values) {
            addCriterion("cv_path in", values, "cvPath");
            return (Criteria) this;
        }

        public Criteria andCvPathNotIn(List<String> values) {
            addCriterion("cv_path not in", values, "cvPath");
            return (Criteria) this;
        }

        public Criteria andCvPathBetween(String value1, String value2) {
            addCriterion("cv_path between", value1, value2, "cvPath");
            return (Criteria) this;
        }

        public Criteria andCvPathNotBetween(String value1, String value2) {
            addCriterion("cv_path not between", value1, value2, "cvPath");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNull() {
            addCriterion("openid is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openid =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openid <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openid >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openid >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openid <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openid <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openid like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openid not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openid in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openid not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openid between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openid not between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }

        public Criteria andPwdLikeInsensitive(String value) {
            addCriterion("upper(pwd) like", value.toUpperCase(), "pwd");
            return this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return this;
        }

        public Criteria andGenderLikeInsensitive(String value) {
            addCriterion("upper(gender) like", value.toUpperCase(), "gender");
            return this;
        }

        public Criteria andBirthdayLikeInsensitive(String value) {
            addCriterion("upper(birthday) like", value.toUpperCase(), "birthday");
            return this;
        }

        public Criteria andOrigoLikeInsensitive(String value) {
            addCriterion("upper(origo) like", value.toUpperCase(), "origo");
            return this;
        }

        public Criteria andMobileLikeInsensitive(String value) {
            addCriterion("upper(mobile) like", value.toUpperCase(), "mobile");
            return this;
        }

        public Criteria andEmailLikeInsensitive(String value) {
            addCriterion("upper(email) like", value.toUpperCase(), "email");
            return this;
        }

        public Criteria andWechatLikeInsensitive(String value) {
            addCriterion("upper(wechat) like", value.toUpperCase(), "wechat");
            return this;
        }

        public Criteria andWorkStartDateLikeInsensitive(String value) {
            addCriterion("upper(work_start_date) like", value.toUpperCase(), "workStartDate");
            return this;
        }

        public Criteria andLastCompanyNameLikeInsensitive(String value) {
            addCriterion("upper(last_company_name) like", value.toUpperCase(), "lastCompanyName");
            return this;
        }

        public Criteria andLastCompanyPosLikeInsensitive(String value) {
            addCriterion("upper(last_company_pos) like", value.toUpperCase(), "lastCompanyPos");
            return this;
        }

        public Criteria andLastSalaryLikeInsensitive(String value) {
            addCriterion("upper(last_salary) like", value.toUpperCase(), "lastSalary");
            return this;
        }

        public Criteria andCvPathLikeInsensitive(String value) {
            addCriterion("upper(cv_path) like", value.toUpperCase(), "cvPath");
            return this;
        }

        public Criteria andOpenidLikeInsensitive(String value) {
            addCriterion("upper(openid) like", value.toUpperCase(), "openid");
            return this;
        }

        public Criteria andStateLikeInsensitive(String value) {
            addCriterion("upper(state) like", value.toUpperCase(), "state");
            return this;
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}