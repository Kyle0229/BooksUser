package com.kyle.mapper;

import com.kyle.pojo.Paid;

public interface PaidMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Paid record);

    int insertSelective(Paid record);

    Paid selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Paid record);

    int updateByPrimaryKey(Paid record);
}