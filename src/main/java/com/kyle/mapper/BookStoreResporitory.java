package com.kyle.mapper;

import com.kyle.pojo.Bookstore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStoreResporitory extends JpaRepository<Bookstore,Integer> {
}
