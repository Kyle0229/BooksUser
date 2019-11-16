package com.kyle.mapper;

import com.kyle.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
public interface BookMapper {
    int deleteByPrimaryKey(Integer bid);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer bid);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> findScount();

    List<Book> findNumMoney();
    List<Book> findAscMoney();
    List<Book> findBtickets();

    List findBookChapter(Integer bid);

    String findBookContent(Integer chid);

    String findBookCatalog(Integer cid);

    List<Book> findCatalog(Integer cid);

    List<Book> findNewBook();

    List<Book> findRomantic();
    List<Book> findMan();
}