package com.kyle.service.impl;

import com.kyle.domain.Paid;
import com.kyle.domain.User;
import com.kyle.mapper.BookstoreMapper;
import com.kyle.mapper.PaidMapper;
import com.kyle.mapper.PaidRepository;
import com.kyle.mapper.UsersRepository;
import com.kyle.domain.Book;
import com.kyle.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Resource
    private BookstoreMapper bookstoreMapper;
    @Resource
    private PaidMapper paidMapper;
    @Resource
    private PaidRepository paidRepository;

    @Override
    public User findByname(String uname) {
        User user = usersRepository.findByUname(uname);
        return user;
    }

    @Override
    public List<Book> findPayBook( Integer uid) {
        List<Book> payBooks = bookstoreMapper.findPayBook(uid);
        return payBooks;
    }

    @Override
    public List<Book> findConllectBook(Integer uid) {
        List<Book> collectBooks = paidMapper.findCollectBook(uid);
            return collectBooks;
    }

    @Override
    public Integer findCollectCount(Integer uid) {

        Integer collectCount = paidMapper.findCollectCount(uid);
        return collectCount;
    }

    @Override
    public void updateUsers(User user) {
        User user1 = usersRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findUserAll() {
        List<User> all = usersRepository.findAll();
        return all;
    }

    @Override
    public void saveCollect(Integer uid, Integer bid) {
        Paid paid=new Paid();
        paid.setAid(uid);
        paid.setBid(bid);
        paidRepository.save(paid);
    }
}
