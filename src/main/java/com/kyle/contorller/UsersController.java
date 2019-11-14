package com.kyle.contorller;

import com.kyle.Request.BookMoney;
import com.kyle.domain.*;
import com.kyle.mapper.AuthorRepository;
import com.kyle.mapper.BookRepository;
import com.kyle.mapper.BookStoreRepository;
import com.kyle.mapper.UsersRepository;
import com.kyle.domain.BookStore;
import com.kyle.service.BookService;
import com.kyle.service.BookStoreService;
import com.kyle.service.UsersService;
import com.kyle.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {
    @Resource
    private AuthorRepository authorRepository;
    @Resource
    private BookService bookService;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private BookStoreRepository bookStoreRepository;
    @Resource
    private UsersService usersService;
    @Resource
    private UploadUtils uploadUtils;
    @Resource
    private BookStoreService bookStoreService;
    @Resource
    private BookRepository bookRepository;
    //根据用户id查询用户信息
    //需要用户信息
    @RequestMapping("/findUserId")
    public User findUserId(HttpSession session){
        System.out.println(session.getId());
        User user = (User)session.getAttribute("user");
        System.out.println(user.getUname());
        Integer uid = user.getUid();
        Optional<User> byId = usersRepository.findById(uid);
        if (byId!=null){
            return byId.get();
        }
        return null;
    }
    @RequestMapping("/findUserAll")
    public List<User> findUserAll(){
        List<User> userAll = usersService.findUserAll();
        return userAll;
    }
    //需要从登录信息里拿到uid添加进已购买表里去
    @RequestMapping(value = "/payBook",method = RequestMethod.POST)
    public String saveBookStore(@RequestBody Book book,HttpSession session){
        User user = (User)session.getAttribute("user");
        Integer uid = user.getUid();
        Integer bid = book.getBid();
       bookStoreService.saveBook(uid,bid);

        return "购买成功";
    }
    //需要用户登录信息
    //根据uid查出已购买的书
    @RequestMapping(value = "/findPayBook")
    public List<Book> findPayBook(HttpSession session){
        User user = (User)session.getAttribute("user");
        Integer uid = user.getUid();
        List<Book> payBook = usersService.findPayBook(uid);

        return payBook;
    }
    //收藏书
    @RequestMapping(value = "/collectBook",method = RequestMethod.POST)
    public String savePaid(@RequestBody Book book,HttpSession session){
        Integer bid = book.getBid();
        User user = (User)session.getAttribute("user");
        Integer uid = user.getUid();
        usersService.saveCollect(uid,bid);

        return "已加入书架";
    }
    //根据uid查出加入书架的书，需要登录信息
    @RequestMapping(value = "/findCollect")
    public List<Book> findCollectBook(HttpSession session){
        User user = (User)session.getAttribute("user");
        Integer uid = user.getUid();
        List<Book> conllectBook = usersService.findConllectBook(uid);
        return conllectBook;
    }

    //根据id查出书架的数量
    @RequestMapping(value = "/findCollectCount")
    public Integer findCollectCount(HttpSession session){
        User user = (User)session.getAttribute("user");
        Integer uid = user.getUid();
        Integer collectCount = usersService.findCollectCount(uid);
        return collectCount;
    }
    //打赏
    //需要登录信息，用户的钱包减去money
    @RequestMapping(value = "/giveMoney",method = RequestMethod.POST)
    public String giveMoney(@RequestBody BookMoney bookMoney, HttpSession session){
        User user = (User)session.getAttribute("user");
        BigDecimal money = bookMoney.getMoney();
        Book book = bookMoney.getBook();
        BigDecimal ucoin = user.getUcoin();
        //用户减钱并保存
        BigDecimal subtract = ucoin.subtract(money);
        user.setUcoin(subtract);
        usersRepository.save(user);
        //书字段加钱并保存
        BigDecimal nummoney = book.getNummoney();
        BigDecimal money1 = nummoney.add(money);
        book.setNummoney(money1);
        bookRepository.save(book);
        //根据书id查出作者id
        Integer aid = book.getAid();
        Author bookAuthor = bookService.findBookAuthor(aid);
        BigDecimal awallet = bookAuthor.getAwallet();
        //给作者加钱
        BigDecimal add = awallet.add(money);
        bookAuthor.setAwallet(add);
        authorRepository.save(bookAuthor);

        return "成功打赏";
    }
    //投票  需要用户信息
    @RequestMapping("/giveUticket")
    public String giveUticket(@RequestBody Book book,HttpSession session){
        Integer btickets = book.getBtickets();
        btickets++;
        book.setBtickets(btickets);
        bookRepository.save(book);
        System.out.println(btickets);
        User user = (User)session.getAttribute("user");
        Integer uticket = user.getUticket();
        Integer integer = uticket--;
        user.setUticket(integer);
        //需要用户信息,用户的ticket字段减一
        usersRepository.save(user);
        //用户模块没写
        return "成功";
    }

    //充值会员
    //vip状态：0 无会员 1 一个月     2 三个月   3 六个月 4 12个月
    @RequestMapping("/Vip")
    public User setVid(HttpSession session){
        User user = (User)session.getAttribute("user");
        Integer uvip = user.getUvip();
        int d=0;
        if(uvip==0) {
            user.setUvip(uvip);
            BigDecimal bigDecimal1 = new BigDecimal("9");
            BigDecimal bigDecimal2 = new BigDecimal("25");
            BigDecimal bigDecimal3 = new BigDecimal("45");
            BigDecimal bigDecimal4 = new BigDecimal("80");
            if (uvip == 1) {
                BigDecimal subtract = user.getUcoin().subtract(bigDecimal1);
                user.setUcoin(subtract);
                d=1;
            }
            if (uvip == 2) {
                BigDecimal subtract = user.getUcoin().subtract(bigDecimal2);
                user.setUcoin(subtract);
                d=3;
            }
            if (uvip == 3) {
                BigDecimal subtract = user.getUcoin().subtract(bigDecimal3);
                user.setUcoin(subtract);
                d=6;
            }
            if (uvip == 4) {
                BigDecimal subtract = user.getUcoin().subtract(bigDecimal4);
                user.setUcoin(subtract);
                d=12;
            }
            //vid创建时间，以及到期时间
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Calendar rightNow = Calendar.getInstance();
            user.setUvipdate(new Date());
            user.setUticket(5);
            Date uvipdate = user.getUvipdate();
            rightNow.setTime(uvipdate);
            rightNow.add(Calendar.MONTH,d);
            Date dt1=rightNow.getTime();
            user.setUexptime(dt1);
        } else {
            if (uvip> user.getUvip()){
                user.setUvip(uvip);
            }
            BigDecimal bigDecimal1 = new BigDecimal("9");
            BigDecimal bigDecimal2 = new BigDecimal("25");
            BigDecimal bigDecimal3 = new BigDecimal("45");
            BigDecimal bigDecimal4 = new BigDecimal("80");
            if (uvip == 1) {
                BigDecimal subtract = user.getUcoin().subtract(bigDecimal1);
                user.setUcoin(subtract);
                d=1;
            }
            if (uvip == 2) {
                BigDecimal subtract = user.getUcoin().subtract(bigDecimal2);
                user.setUcoin(subtract);
                d=3;
            }
            if (uvip == 3) {
                BigDecimal subtract = user.getUcoin().subtract(bigDecimal3);
                user.setUcoin(subtract);
                d=6;
            }
            if (uvip == 4) {
                BigDecimal subtract = user.getUcoin().subtract(bigDecimal4);
                user.setUcoin(subtract);
                d=12;
            }

    }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar rightNow = Calendar.getInstance();
        Date uvipdate = user.getUexptime();
        rightNow.setTime(uvipdate);
        rightNow.add(Calendar.MONTH,d);
        Date dt1=rightNow.getTime();
        String reStr = sdf.format(dt1);
        System.out.println(reStr);
        user.setUexptime(dt1);
        usersRepository.saveAndFlush(user);
        return user;
    }

    @RequestMapping("/viptime")
    public String viptime(@RequestBody User user){
//        System.out.println(user);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date uvipdate = user.getUexptime();
        Calendar rightNow = Calendar.getInstance();
        System.out.println(uvipdate);
        rightNow.setTime(uvipdate);
        Integer uvip = user.getUvip();
        int d=0;
        if (uvip==1){
            d=1;
        }else if (uvip==2){
            d=3;
        }else if (uvip==3){
            d=6;
        }else if (uvip==4){
            d=12;
        }
        rightNow.add(Calendar.MONTH,d);
        Date dt1=rightNow.getTime();
        String reStr = sdf.format(dt1);
//        System.out.println(dt1);
        System.out.println(reStr);

        return reStr;

    }
    //个人修改信息
    @RequestMapping("/updateUser")
    public String updateUser(@RequestBody User user){
        usersService.updateUsers(user);

        return "OK";
    }
    //上传头像
    @RequestMapping(value = "/updatePic",method = RequestMethod.POST)
    public String updatePic(MultipartFile file){
        if (file.isEmpty()){
            return null;
        }
        return uploadUtils.upload(file);
    }
}
