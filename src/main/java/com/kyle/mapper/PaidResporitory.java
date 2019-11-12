package com.kyle.mapper;

import com.kyle.pojo.Paid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaidResporitory extends JpaRepository<Paid,Integer> {
}
