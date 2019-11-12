package com.kyle.service;

import com.kyle.pojo.Author;
import com.kyle.pojo.Book;

import java.util.List;

public interface BookService {

    public String saveBook(Book book);
    //根据Bid查询书籍信息
    Book findBookId(Integer bid);
    //根据aid查询作者信息
    Author findBookAuthor(Integer aid);
    //降序查询所有的书的scount收藏数
    List<Book> findScount();
    //降序查询所有的书的收入
    List<Book> findNumMoney();
    //根据书的id查询所有的章节
    List findBookChapter(Integer bid);
    //根据章节id查出章节内容
    String findBookContent(Integer chid);
    //根据分类id查出所有书
    List<Book> findCatalog(Integer cid);
}
