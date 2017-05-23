package com.xin.db.dao;

import com.xin.db.entity.TPicCode;
import com.xin.db.entity.TPicCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPicCodeMapper {
    int countByExample(TPicCodeExample example);

    int deleteByExample(TPicCodeExample example);

    int deleteByPrimaryKey(Long tid);

    int insert(TPicCode record);

    int insertSelective(TPicCode record);

    List<TPicCode> selectByExample(TPicCodeExample example);

    TPicCode selectByPrimaryKey(Long tid);

    int updateByExampleSelective(@Param("record") TPicCode record, @Param("example") TPicCodeExample example);

    int updateByExample(@Param("record") TPicCode record, @Param("example") TPicCodeExample example);

    int updateByPrimaryKeySelective(TPicCode record);

    int updateByPrimaryKey(TPicCode record);
}