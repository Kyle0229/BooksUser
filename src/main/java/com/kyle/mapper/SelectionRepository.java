package com.kyle.mapper;

import com.kyle.domain.Selection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SelectionRepository extends JpaRepository<Selection,Integer> {

    Optional<Selection> findByBid(Integer bid);
}
