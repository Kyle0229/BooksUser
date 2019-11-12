package com.kyle.service;

import com.kyle.pojo.Book;
import com.kyle.pojo.Users;

import java.util.List;

public interface UsersService {
    Users findByname(String uname);


    List<Book> findPayBook(Integer uid);

    List<Book> findConllectBook(Integer uid);

}
