package com.kyle.contorller;

import com.kyle.mapper.BookResporitory;
import com.kyle.mapper.CatalogResporitory;
import com.kyle.mapper.ChapterResporitory;
import com.kyle.pojo.Author;
import com.kyle.pojo.Book;
import com.kyle.pojo.Catalog;
import com.kyle.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BookController {
    @Resource
    private BookResporitory bookResporitory;
    @Resource
    private ChapterResporitory chapterResporitory;
    @Resource
    private BookService bookService;
    @Resource
    private CatalogResporitory catalogResporitory;
//    @RequestMapping("/savebook")
//    public String savebook(@RequestBody Book book){
//        String s = bookService.saveBook(book);
//        if (s.equals("成功")){
//            return "上传成功";
//        }
//        return "上传失败";
//    }

    //根据书的id查出书的详情
    @RequestMapping("/findBookId")
    public Book findBook(@RequestBody Book book){
        Integer bid = book.getBid();
        Book book1 = bookService.findBookId(bid);
        return book1;
    }
    //根据书的id查出作者的信息
    @RequestMapping("/findBookAuthor")
    public Author findBookAuthor(@RequestBody Book book){
        Integer aid = book.getAid();
        Author bookAuthor = bookService.findBookAuthor(aid);
        return bookAuthor;
    }

    //降序查询所有的书的scount收藏数
    @RequestMapping("/findScount")
    public List<Book> findScount(){
        List<Book> scount = bookService.findScount();
        return scount;
    }


    //降序查询所有书的收入金额nummoney
    @RequestMapping("/findNumMoney")
    public  List<Book> findNumMoney(){
        List<Book> numMOney = bookService.findNumMoney();
        return numMOney;
    }
//根据书的id查出书的所有章节名
    @RequestMapping("/findBookChapter")
    public List findBookChapter(@RequestBody Book book){
        Integer bid = book.getBid();
        List bookChapter = bookService.findBookChapter(bid);
        return bookChapter;
    }


    //根据章节的id查出章节内容
    @RequestMapping("/findBookContent")
    public String findBookContent(){
        String bookContent = bookService.findBookContent(6888);
        return bookContent;
    }

    //查询所有分类
    @RequestMapping("/findAllCatalaog")
    public List<Catalog> findAllCatalaog(){
        List<Catalog> all = catalogResporitory.findAll();
        return all;
    }

    //根据分类id查出分类下所有书籍
    @RequestMapping("/findCatalog")
    public List<Book> findCatalog(@RequestBody Catalog catalog){
        Integer cid = catalog.getCid();
        List<Book> catalog1 = bookService.findCatalog(cid);
        return catalog1;
    }





}
