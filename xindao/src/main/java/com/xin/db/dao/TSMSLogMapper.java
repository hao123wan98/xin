package com.xin.db.dao;

import com.xin.db.entity.TSMSLog;
import com.xin.db.entity.TSMSLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSMSLogMapper {
    int countByExample(TSMSLogExample example);

    int deleteByExample(TSMSLogExample example);

    int deleteByPrimaryKey(Long tid);

    int insert(TSMSLog record);

    int insertSelective(TSMSLog record);

    List<TSMSLog> selectByExample(TSMSLogExample example);

    TSMSLog selectByPrimaryKey(Long tid);

    int updateByExampleSelective(@Param("record") TSMSLog record, @Param("example") TSMSLogExample example);

    int updateByExample(@Param("record") TSMSLog record, @Param("example") TSMSLogExample example);

    int updateByPrimaryKeySelective(TSMSLog record);

    int updateByPrimaryKey(TSMSLog record);
}