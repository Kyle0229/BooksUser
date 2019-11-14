package com.kyle.mapper;

import com.kyle.domain.Book;
import com.kyle.domain.Paid;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PaidMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Paid record);

    int insertSelective(Paid record);

    Paid selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Paid record);

    int updateByPrimaryKey(Paid record);

    List<Book>  findCollectBook(Integer uid);

    Integer findCollectCount(Integer uid);
}