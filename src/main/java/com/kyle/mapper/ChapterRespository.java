package com.kyle.mapper;

import com.kyle.domain.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRespository extends JpaRepository<Chapter,Integer> {
}
