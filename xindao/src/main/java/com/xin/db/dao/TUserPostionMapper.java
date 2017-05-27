package com.xin.db.dao;

import com.xin.db.entity.TUserPostion;
import com.xin.db.entity.TUserPostionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserPostionMapper {
    int countByExample(TUserPostionExample example);

    int deleteByExample(TUserPostionExample example);

    int deleteByPrimaryKey(Long tid);

    int insert(TUserPostion record);

    int insertSelective(TUserPostion record);

    List<TUserPostion> selectByExample(TUserPostionExample example);

    TUserPostion selectByPrimaryKey(Long tid);

    int updateByExampleSelective(@Param("record") TUserPostion record, @Param("example") TUserPostionExample example);

    int updateByExample(@Param("record") TUserPostion record, @Param("example") TUserPostionExample example);

    int updateByPrimaryKeySelective(TUserPostion record);

    int updateByPrimaryKey(TUserPostion record);
}