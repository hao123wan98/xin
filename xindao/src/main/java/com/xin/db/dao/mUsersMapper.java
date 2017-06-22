package com.xin.db.dao;

import com.xin.db.entity.mUsers;
import com.xin.db.entity.mUsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface mUsersMapper {
    int countByExample(mUsersExample example);

    int deleteByExample(mUsersExample example);

    int deleteByPrimaryKey(Integer tid);

    int insert(mUsers record);

    int insertSelective(mUsers record);

    List<mUsers> selectByExample(mUsersExample example);

    mUsers selectByPrimaryKey(Integer tid);

    int updateByExampleSelective(@Param("record") mUsers record, @Param("example") mUsersExample example);

    int updateByExample(@Param("record") mUsers record, @Param("example") mUsersExample example);

    int updateByPrimaryKeySelective(mUsers record);

    int updateByPrimaryKey(mUsers record);
}