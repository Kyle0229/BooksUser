package com.kyle.service.impl;

import com.kyle.domain.BookStore;
import com.kyle.mapper.BookStoreRepository;
import com.kyle.service.BookStoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class BookStoreServiceImpl implements BookStoreService {
    @Resource
    private BookStoreRepository bookStoreRepository;

    @Override
    public void saveBook(Integer uid, Integer bid) {
        BookStore bookStore=new BookStore();
        bookStore.setUid(uid);
        bookStore.setBid(bid);
        bookStoreRepository.save(bookStore);
    }
}
