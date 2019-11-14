package com.kyle.mapper;

import com.kyle.domain.Author;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(Author record);

    int insertSelective(Author record);

    Author selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(Author record);

    int updateByPrimaryKey(Author record);

    Author findBookAuthor(Integer aid);
}