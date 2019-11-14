package com.kyle.service;

import com.kyle.domain.Book;
import com.kyle.domain.User;

import java.util.List;

public interface UsersService {
    User findByname(String uname);


    List<Book> findPayBook(Integer uid);

    List<Book> findConllectBook(Integer uid);

    Integer findCollectCount(Integer uid);

    void updateUsers(User user);

    List<User> findUserAll();

    void saveCollect(Integer uid, Integer bid);
}
