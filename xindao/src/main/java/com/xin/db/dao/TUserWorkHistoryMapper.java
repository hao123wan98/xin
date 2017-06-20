package com.xin.db.dao;

import com.xin.db.entity.TUserWorkHistory;
import com.xin.db.entity.TUserWorkHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserWorkHistoryMapper {
    int countByExample(TUserWorkHistoryExample example);

    int deleteByExample(TUserWorkHistoryExample example);

    int deleteByPrimaryKey(Long tid);

    int insert(TUserWorkHistory record);

    int insertSelective(TUserWorkHistory record);

    List<TUserWorkHistory> selectByExample(TUserWorkHistoryExample example);

    TUserWorkHistory selectByPrimaryKey(Long tid);

    int updateByExampleSelective(@Param("record") TUserWorkHistory record, @Param("example") TUserWorkHistoryExample example);

    int updateByExample(@Param("record") TUserWorkHistory record, @Param("example") TUserWorkHistoryExample example);

    int updateByPrimaryKeySelective(TUserWorkHistory record);

    int updateByPrimaryKey(TUserWorkHistory record);
}