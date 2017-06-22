package com.xin.db.dao;

import com.xin.db.entity.TToken;
import com.xin.db.entity.TTokenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TTokenMapper {
    int countByExample(TTokenExample example);

    int deleteByExample(TTokenExample example);

    int deleteByPrimaryKey(String token);

    int insert(TToken record);

    int insertSelective(TToken record);

    List<TToken> selectByExample(TTokenExample example);

    TToken selectByPrimaryKey(String token);

    int updateByExampleSelective(@Param("record") TToken record, @Param("example") TTokenExample example);

    int updateByExample(@Param("record") TToken record, @Param("example") TTokenExample example);

    int updateByPrimaryKeySelective(TToken record);

    int updateByPrimaryKey(TToken record);
}