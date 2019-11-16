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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private RedisTemplate<String ,Object> redisTemplate;

    @Override
    public User findByname(String uname) {
        User user = usersRepository.findByUname(uname);
        return user;
    }

    @Override
    public List<Book> findPayBook( Integer uid) {
        Object findPayBook = redisTemplate.opsForValue().get("findPayBook");
        if (findPayBook!=null){
            return (List<Book>)findPayBook;
        }
        List<Book> payBooks = bookstoreMapper.findPayBook(uid);
        redisTemplate.opsForValue().set("findPayBook",payBooks,1,TimeUnit.MINUTES);
        return payBooks;
    }

    @Override
    public List<Book> findConllectBook(Integer uid) {
        Object findConllectBook = redisTemplate.opsForValue().get("findConllectBook");
        if (findConllectBook!=null){
            return (List<Book>)findConllectBook;
        }
        List<Book> collectBooks = paidMapper.findCollectBook(uid);
        redisTemplate.opsForValue().set("findConllectBook",collectBooks,1,TimeUnit.MINUTES);
            return collectBooks;
    }

    @Override
    public Integer findCollectCount(Integer uid) {
        Object findCollectCount = redisTemplate.opsForValue().get("findCollectCount");
        if (findCollectCount!=null){
            return (Integer) findCollectCount;
        }
        Integer collectCount = paidMapper.findCollectCount(uid);
        redisTemplate.opsForValue().set("findCollectCount",collectCount,10,TimeUnit.SECONDS);
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
