package com.xin.db.dao;

import com.xin.db.entity.TUserEducateHistory;
import com.xin.db.entity.TUserEducateHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserEducateHistoryMapper {
    int countByExample(TUserEducateHistoryExample example);

    int deleteByExample(TUserEducateHistoryExample example);

    int deleteByPrimaryKey(Long tid);

    int insert(TUserEducateHistory record);

    int insertSelective(TUserEducateHistory record);

    List<TUserEducateHistory> selectByExample(TUserEducateHistoryExample example);

    TUserEducateHistory selectByPrimaryKey(Long tid);

    int updateByExampleSelective(@Param("record") TUserEducateHistory record, @Param("example") TUserEducateHistoryExample example);

    int updateByExample(@Param("record") TUserEducateHistory record, @Param("example") TUserEducateHistoryExample example);

    int updateByPrimaryKeySelective(TUserEducateHistory record);

    int updateByPrimaryKey(TUserEducateHistory record);
}