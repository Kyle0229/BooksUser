package com.kyle.service.impl;

import com.kyle.domain.*;
import com.kyle.mapper.*;
import com.kyle.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;
    @Autowired
    private BookRepository bookRepository;
    @Resource
    private AuthorMapper authorMapper;
    @Resource
    private RedisTemplate<String ,Object> redisTemplate;
    @Resource
    private PaidMapper paidMapper;
    @Resource
    private BookstoreMapper bookstoreMapper;
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
        Book bookId = (Book)redisTemplate.opsForValue().get("BookId");
        if (bookId!=null){
            return bookId;
        }
        Optional<Book> byId = bookRepository.findById(bid);
        if (byId!=null){
            redisTemplate.opsForValue().set("BookId",bookId,10000,TimeUnit.MILLISECONDS);
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
        Object scount1 = redisTemplate.opsForValue().get("Scount");
        if (scount1!=null){
            System.out.println("redis查询");
            return ( List<Book>)scount1;
        }
        List<Book> scount = bookMapper.findScount();
        redisTemplate.opsForValue().set("Scount",scount,5,TimeUnit.MINUTES);
        return scount;
    }

    @Override
    public List<Book> findNumMoney() {
        Object NumMoney = redisTemplate.opsForValue().get("NumMoney");
        if (NumMoney!=null){
            System.out.println("redis查询NumMoney");
            return ( List<Book>)NumMoney;
        }
        List<Book> numMOney = bookMapper.findNumMoney();
        redisTemplate.opsForValue().set("NumMoney",numMOney,5,TimeUnit.SECONDS);
        return numMOney;
    }

    @Override
    public List<Book> findAscMoney() {
        List<Book> ascMoney = bookMapper.findAscMoney();
        return ascMoney;
    }

    @Override
    public List findBookChapter(Integer bid) {
        Object findBookChapter = redisTemplate.opsForValue().get("findBookChapter");
        if (findBookChapter!=null){
            System.out.println("redis查询findBookChapter");
            return (List)findBookChapter;
        }
        List<Chapter> bookChapter = bookMapper.findBookChapter(bid);
            redisTemplate.opsForValue().set("findBookChapter",bookChapter,10,TimeUnit.MINUTES);
        return bookChapter;
    }

    @Override
    public String findBookContent(Integer chid) {

        String bookContent = bookMapper.findBookContent(chid);
        return bookContent;
    }

    @Override
    public List<Book> findCatalog(Integer cid) {
        Object findCatalog = redisTemplate.opsForValue().get("findCatalog");
        if (findCatalog!=null){
            return (List<Book>)findCatalog;
        }
        List<Book> catalog = bookMapper.findCatalog(cid);
        redisTemplate.opsForValue().set("findCatalog",catalog,28,TimeUnit.HOURS);
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

    @Override
    public List<Book> findBookAll() {
        Object findBookAll = redisTemplate.opsForValue().get("findBookAll");
        if (findBookAll!=null){
            return (List<Book>)findBookAll;
        }
        List<Book> all = bookRepository.findAll();
        redisTemplate.opsForValue().set("findBookAll",all,3,TimeUnit.HOURS);
        return all;
    }

    @Override
    public List<Book> findNewBook() {
        Object findNewBook = redisTemplate.opsForValue().get("findNewBook");
        if (findNewBook!=null){
            return (List<Book>)findNewBook;
        }
        List<Book> newBook = bookMapper.findNewBook();
        redisTemplate.opsForValue().set("findNewBook",newBook,30,TimeUnit.MINUTES);
        return newBook;
    }

    @Override
    public List<Book> findRomantic() {
        Object findRomantic = redisTemplate.opsForValue().get("findRomantic");
        if (findRomantic!=null){
            return (List<Book>)findRomantic;
        }
        List<Book> romantic = bookMapper.findRomantic();
        redisTemplate.opsForValue().set("findRomantic",romantic,30,TimeUnit.MINUTES);
        return romantic;
    }

    @Override
    public List<Book> findMan() {
        Object findMan = redisTemplate.opsForValue().get("findMan");
        if (findMan!=null){
            return (List<Book>)findMan;
        }
        List<Book> man = bookMapper.findMan();
        redisTemplate.opsForValue().set("findMan",man,30,TimeUnit.MINUTES);
        return man;
    }

    @Override
    public Paid findCollect(Integer uid, Integer bid) {
        Paid collect = paidMapper.findCollect(uid, bid);
        return collect;

    }

    @Override
    public BookStore findBookStore(Integer uid, Integer bid) {
        BookStore bookStore = bookstoreMapper.findBookStore(uid, bid);
        return bookStore;
    }
}
