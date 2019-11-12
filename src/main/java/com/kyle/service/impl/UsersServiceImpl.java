package com.kyle.service.impl;

import com.kyle.mapper.BookstoreMapper;
import com.kyle.mapper.PaidMapper;
import com.kyle.mapper.UsersRespority;
import com.kyle.pojo.Book;
import com.kyle.pojo.Users;
import com.kyle.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRespority usersRespority;
    @Autowired
    private BookstoreMapper bookstoreMapper;
    @Autowired
    private PaidMapper paidMapper;

    @Override
    public Users findByname(String uname) {
        Users users = usersRespority.findByUname(uname);
        return users;
    }

    @Override
    public List<Book> findPayBook(Integer uid) {
        List<Book> payBooks = bookstoreMapper.findPayBook(uid);
        return payBooks;
    }

    @Override
    public List<Book> findConllectBook(Integer uid) {
        List<Book> collectBooks = paidMapper.findCollectBook(uid);
            return collectBooks;
    }
}
