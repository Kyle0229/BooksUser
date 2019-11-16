package com.kyle.mapper;

import com.kyle.domain.Selection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SelectionMapper {

    List<Selection> findSelectionId();

    Selection findSid(Integer bid);
}
