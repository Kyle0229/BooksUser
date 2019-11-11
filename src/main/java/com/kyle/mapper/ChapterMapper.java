package com.kyle.mapper;

import com.kyle.pojo.Chapter;

public interface ChapterMapper {
    int deleteByPrimaryKey(Integer chid);

    int insert(Chapter record);

    int insertSelective(Chapter record);

    Chapter selectByPrimaryKey(Integer chid);

    int updateByPrimaryKeySelective(Chapter record);

    int updateByPrimaryKeyWithBLOBs(Chapter record);

    int updateByPrimaryKey(Chapter record);
}