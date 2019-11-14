package com.kyle.mapper;

import com.kyle.domain.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStoreRepository extends JpaRepository<BookStore,Integer> {
}
