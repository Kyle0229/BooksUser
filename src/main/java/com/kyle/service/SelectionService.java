package com.kyle.service;

import com.kyle.domain.Selection;

import java.util.List;

public interface SelectionService {

    List<Selection> findSelectionId();

    Selection findSid(Integer bid);
}
