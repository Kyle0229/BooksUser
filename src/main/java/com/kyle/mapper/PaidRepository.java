package com.kyle.mapper;

import com.kyle.domain.Paid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaidRepository extends JpaRepository<Paid,Integer> {
}
