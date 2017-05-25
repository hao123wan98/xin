package com.xin.db.dao;

import com.xin.db.entity.TLoginUser;
import com.xin.db.entity.TLoginUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;


public interface TLoginUserMapper {
    int countByExample(TLoginUserExample example);

    int deleteByExample(TLoginUserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(TLoginUser record);

    int insertSelective(TLoginUser record);

    List<TLoginUser> selectByExample(TLoginUserExample example);

    TLoginUser selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") TLoginUser record, @Param("example") TLoginUserExample example);

    int updateByExample(@Param("record") TLoginUser record, @Param("example") TLoginUserExample example);

    int updateByPrimaryKeySelective(TLoginUser record);

    int updateByPrimaryKey(TLoginUser record);
}