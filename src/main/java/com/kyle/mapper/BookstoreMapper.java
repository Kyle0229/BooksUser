package com.kyle.mapper;

import com.kyle.domain.Book;

import com.kyle.domain.BookStore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface BookstoreMapper {
    int deleteByPrimaryKey(Integer bsid);

    int insert(BookStore record);

    int insertSelective(BookStore record);

    BookStore selectByPrimaryKey(Integer bsid);

    int updateByPrimaryKeySelective(BookStore record);

    int updateByPrimaryKey(BookStore record);

    List<Book> findPayBook(Integer uid);

    BookStore findBookStore(@Param("uid") Integer uid,@Param("bid") Integer bid);
}