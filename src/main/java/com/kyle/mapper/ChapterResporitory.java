package com.kyle.mapper;

import com.kyle.pojo.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterResporitory extends JpaRepository<Chapter,Integer> {
}
