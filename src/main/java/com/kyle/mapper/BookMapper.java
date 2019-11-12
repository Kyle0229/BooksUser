package com.kyle.mapper;

import com.kyle.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface BookMapper {
    int deleteByPrimaryKey(Integer bid);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer bid);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> findScount();

    List<Book> findNumMoney();

    List findBookChapter(Integer bid);

    String findBookContent(Integer chid);

    List<Book> findCatalog(Integer cid);
}