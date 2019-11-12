package com.kyle.service.impl;

import com.kyle.mapper.AuthorMapper;
import com.kyle.mapper.BookMapper;
import com.kyle.mapper.BookResporitory;
import com.kyle.pojo.Author;
import com.kyle.pojo.Book;
import com.kyle.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;
    @Autowired
    private BookResporitory bookResporitory;
    @Resource
    private AuthorMapper authorMapper;

    @Override
    public String saveBook(Book book) {
        Book save = bookResporitory.save(book);
        if (save!=null) {
            return "成功";
        }
        return "失败";
    }

    @Override
    public Book findBookId(Integer bid) {
        Optional<Book> byId = bookResporitory.findById(bid);
        if (byId!=null){
            return byId.get();
        }
        return null;
    }

    @Override
    public Author findBookAuthor(Integer aid) {

        Author bookAuthor = authorMapper.findBookAuthor(aid);
        return bookAuthor;
    }

    @Override
    public List<Book> findScount() {
        List<Book> scount = bookMapper.findScount();
        return scount;
    }

    @Override
    public List<Book> findNumMoney() {
        List<Book> numMOney = bookMapper.findNumMoney();
        return numMOney;
    }

    @Override
    public List findBookChapter(Integer bid) {
        List bookChapter = bookMapper.findBookChapter(bid);

        return bookChapter;
    }

    @Override
    public String findBookContent(Integer chid) {
        String bookContent = bookMapper.findBookContent(chid);
        return bookContent;
    }

    @Override
    public List<Book> findCatalog(Integer cid) {
        List<Book> catalog = bookMapper.findCatalog(cid);
        return catalog;
    }
}