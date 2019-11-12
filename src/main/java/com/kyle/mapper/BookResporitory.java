package com.kyle.mapper;

import com.kyle.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookResporitory  extends JpaRepository<Book,Integer> {

}
