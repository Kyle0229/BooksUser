package com.kyle.service.impl;

import com.kyle.mapper.AuthorMapper;
import com.kyle.mapper.BookMapper;
import com.kyle.mapper.BookRepository;
import com.kyle.domain.Author;
import com.kyle.domain.Book;
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
    private BookRepository bookRepository;
    @Resource
    private AuthorMapper authorMapper;

    @Override
    public String saveBook(Book book) {
        Book save = bookRepository.save(book);
        if (save!=null) {
            return "成功";
        }
        return "失败";
    }

    @Override
    public Book findBookId(Integer bid) {
        Optional<Book> byId = bookRepository.findById(bid);
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

    @Override
    public String findBookCatalog(Integer cid) {
        String bookCatalog = bookMapper.findBookCatalog(cid);
        return bookCatalog;
    }

    @Override
    public List<Book> findBtickets() {
        List<Book> btickets = bookMapper.findBtickets();
        return btickets;
    }
}
