package com.kyle.contorller;

import com.kyle.Request.BookDown;
import com.kyle.mapper.BookRepository;
import com.kyle.mapper.CatalogRepository;
import com.kyle.mapper.ChapterRespository;
import com.kyle.domain.Author;
import com.kyle.domain.Book;
import com.kyle.domain.Catalog;
import com.kyle.service.BookService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    @Resource
    private BookRepository bookRepository;
    @Resource
    private ChapterRespository chapterRespository;
    @Resource
    private BookService bookService;
    @Resource
    private CatalogRepository catalogRepository;
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
    //降序查询所有的书的投票数
    @RequestMapping("/findBtickets")
    public List<Book> findBtickets(){
        List<Book> btickets = bookService.findBtickets();
        return btickets;
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
        List<Catalog> all = catalogRepository.findAll();
        return all;
    }

    //根据分类id查出分类下所有书籍
    @RequestMapping("/findCatalog")
    public List<Book> findCatalog(@RequestBody Catalog catalog){
        Integer cid = catalog.getCid();
        List<Book> catalog1 = bookService.findCatalog(cid);
        return catalog1;
    }


    public List<BookDown> findBookDown(){
        List<BookDown> bookDownList=new ArrayList<>();
        List<Book> all = bookRepository.findAll();
        for (Book list:all){
            Integer bid = list.getBid();
            Integer aid = list.getAid();
            Integer cid = list.getCid();
            String bname = list.getBname();
            Integer btickets = list.getBtickets();
            BigDecimal nummoney = list.getNummoney();
            BigDecimal bprice = list.getBprice();

            Author bookAuthor = bookService.findBookAuthor(aid);
            String aname = bookAuthor.getAname();
            String catalog = bookService.findBookCatalog(cid);
            BookDown bookDown = new BookDown();
            bookDown.getBook().setBid(bid);
            bookDown.getBook().setBname(bname);
            bookDown.getBook().setNummoney(nummoney);
            bookDown.getBook().setBtickets(btickets);
            bookDown.getBook().setBprice(bprice);
            bookDown.getAuthor().setAname(aname);
            bookDown.getCatalog().setCname(catalog);
            bookDownList.add(bookDown);

        }
        return bookDownList;
    }


}
