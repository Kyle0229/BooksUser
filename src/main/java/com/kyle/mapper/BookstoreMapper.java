package com.kyle.mapper;

import com.kyle.pojo.Book;
import com.kyle.pojo.Bookstore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BookstoreMapper {
    int deleteByPrimaryKey(Integer bsid);

    int insert(Bookstore record);

    int insertSelective(Bookstore record);

    Bookstore selectByPrimaryKey(Integer bsid);

    int updateByPrimaryKeySelective(Bookstore record);

    int updateByPrimaryKey(Bookstore record);

    List<Book> findPayBook(Integer uid);
}