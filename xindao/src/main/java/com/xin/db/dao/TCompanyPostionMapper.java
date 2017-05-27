package com.xin.db.dao;

import com.xin.db.entity.TCompanyPostion;
import com.xin.db.entity.TCompanyPostionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCompanyPostionMapper {
    int countByExample(TCompanyPostionExample example);

    int deleteByExample(TCompanyPostionExample example);

    int deleteByPrimaryKey(Long tid);

    int insert(TCompanyPostion record);

    int insertSelective(TCompanyPostion record);

    List<TCompanyPostion> selectByExample(TCompanyPostionExample example);

    TCompanyPostion selectByPrimaryKey(Long tid);

    int updateByExampleSelective(@Param("record") TCompanyPostion record, @Param("example") TCompanyPostionExample example);

    int updateByExample(@Param("record") TCompanyPostion record, @Param("example") TCompanyPostionExample example);

    int updateByPrimaryKeySelective(TCompanyPostion record);

    int updateByPrimaryKey(TCompanyPostion record);
}