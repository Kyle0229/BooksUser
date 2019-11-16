package com.kyle.service.impl;

import com.kyle.domain.Selection;
import com.kyle.mapper.SelectionMapper;
import com.kyle.service.SelectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class SelectionServiceImpl implements SelectionService {
    @Resource
    private SelectionMapper selectionMapper;
    @Override
    public List<Selection> findSelectionId() {
        List<Selection> selectionId = selectionMapper.findSelectionId();
        return selectionId;
    }

    @Override
    public Selection findSid(Integer bid) {
        Selection sid = selectionMapper.findSid(bid);
        return sid;
    }
}
