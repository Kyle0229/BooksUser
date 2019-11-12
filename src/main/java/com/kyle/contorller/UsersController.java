package com.kyle.contorller;

import com.kyle.mapper.BookStoreResporitory;
import com.kyle.mapper.UsersRespority;
import com.kyle.pojo.Book;
import com.kyle.pojo.Bookstore;
import com.kyle.pojo.Paid;
import com.kyle.pojo.Users;
import com.kyle.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {
    @Autowired
    private UsersRespority usersRespority;
    @Autowired
    private BookStoreResporitory bookStoreResporitory;
    @Resource
    private UsersService usersService;

    //根据用户id查询用户信息
    @RequestMapping("/findUserId")
    public Users findUserId(){
        Optional<Users> byId = usersRespority.findById(1);
        if (byId!=null){
            return byId.get();
        }
        return null;
    }
    //需要从登录信息里拿到uid添加进已购买表里去
    @RequestMapping("/payBook")
    public String saveBookStore(@RequestBody Book book){
        Integer bid = book.getBid();
        Bookstore bookstore=new Bookstore();
        bookstore.setBid(bid);

//        bookstore.setUid(uid);
        bookStoreResporitory.save(bookstore);

        return "购买成功";
    }
    //需要用户登录信息
    //根据uid查出已购买的书
    @RequestMapping("/findPayBook")
    public List<Book> findPayBook(){
        List<Book> payBook = usersService.findPayBook(1);

        return payBook;
    }
//需要登录信息
    @RequestMapping("/collectBook")
    public String savePaid(@RequestBody Book book){
        Integer bid = book.getBid();

        Paid paid=new Paid();
        paid.setBid(bid);
//        paid.setUid(uid);

        return "已加入书架";
    }
    //根据uid查出加入书架的书，需要登录信息
    @RequestMapping("/findCollect")
    public List<Book> findCollectBook(){
        List<Book> conllectBook = usersService.findConllectBook(1);
        return conllectBook;
    }
    //打赏
    //需要登录信息，用户的钱包减去money
    @RequestMapping("/giveMoney")
    public String giveMoney(@RequestBody Book book,BigDecimal money){
        BigDecimal nummoney = book.getNummoney();
        BigDecimal money1 = nummoney.add(money);
        book.setNummoney(money1);
        return "成功";
    }
    //投票  需要用户信息
    @RequestMapping("/giveUticket")
    public String giveUticket(@RequestBody Book book){
        Integer btickets = book.getBtickets();
        btickets++;
        System.out.println(btickets);
        //需要用户信息,用户的ticket字段减一
        //用户模块没写
        return "成功";
    }

    //充值会员
    //需要用户信息
    //vip状态：0 无会员 1 30天       2 90天   3 180天  4 365天
    @RequestMapping("/Vip")
    public Users setVid(@RequestBody Users users){

        users.setUvip(users.getUvip());
        //vid创建时间，在页面用当前日期减去创建日期计算天数，
        users.setUvipdate(new Date());
        users.setUticket(5);
        return null;
    }

}
