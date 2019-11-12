package com.kyle.mapper;

import com.kyle.pojo.Catalog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CatalogMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Catalog record);

    int insertSelective(Catalog record);

    Catalog selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Catalog record);

    int updateByPrimaryKey(Catalog record);
}