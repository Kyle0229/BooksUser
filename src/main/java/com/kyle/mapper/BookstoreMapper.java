package com.kyle.mapper;

import com.kyle.pojo.Bookstore;

public interface BookstoreMapper {
    int deleteByPrimaryKey(Integer bsid);

    int insert(Bookstore record);

    int insertSelective(Bookstore record);

    Bookstore selectByPrimaryKey(Integer bsid);

    int updateByPrimaryKeySelective(Bookstore record);

    int updateByPrimaryKey(Bookstore record);
}