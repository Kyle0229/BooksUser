package com.kyle.contorller;

import com.kyle.Request.BookDown;
import com.kyle.domain.*;
import com.kyle.mapper.BookRepository;
import com.kyle.mapper.CatalogRepository;
import com.kyle.mapper.ChapterRespository;
import com.kyle.mapper.SelectionRepository;
import com.kyle.service.BookService;
import com.kyle.service.SelectionService;
import com.kyle.utils.Separater;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Resource
    private BookRepository bookRepository;
    @Resource
    private ChapterRespository chapterRespository;
    @Resource
    private BookService bookService;
    @Resource
    private SelectionService selectionService;
    @Resource
    private CatalogRepository catalogRepository;
    @Resource
    private SelectionRepository selectionRepository;
//    @RequestMapping("/savebook")
//    public String savebook(@RequestBody Book book){
//        String s = bookService.saveBook(book);
//        if (s.equals("成功")){
//            return "上传成功";
//        }
//        return "上传失败";
//    }
    //查询所有书
    @RequestMapping("/findBookAll")
    public List<Book> findBookAll(){
        List<Book> bookAll = bookService.findBookAll();
        return bookAll;
    }
    //根据书的id查出书的详情
    @RequestMapping(value ="/findBookId",method = RequestMethod.POST)
    public Book findBook(@RequestBody Book book){
        Integer bid = book.getBid();
        Book book1 = bookService.findBookId(bid);
        return book1;
    }
    //根据书的id查出作者的信息
    @RequestMapping(value ="/findBookAuthor",method = RequestMethod.POST)
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
    @RequestMapping(value = "/findBookChapter",method = RequestMethod.POST)
    public List<Chapter> findBookChapter(@RequestBody Book book){
        Integer bid = book.getBid();
        System.out.println(bid);
        List<Chapter> bookChapter = bookService.findBookChapter(bid);
        System.out.println(bookChapter);
        return bookChapter;
    }


    //根据章节的id查出章节内容
    @RequestMapping("/findBookContent")
    public String findBookContent(@RequestBody Chapter chapter) throws IOException {
        Integer chid = chapter.getChid();
        System.out.println(chid);
        String bookContent = bookService.findBookContent(chid);
        File fin = new File(bookContent);
        StringBuilder strline=null;
        try (RandomAccessFile accessFile = new RandomAccessFile(fin, "r");
             FileChannel fcin = accessFile.getChannel();
        ){
            Charset charset = Charset.forName("UTF-8");
            int bufSize = 100000;
            ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);
            String enterStr = "\n";
            byte[] bs = new byte[bufSize];
            strline = new StringBuilder("");
            StringBuilder strBuf = new StringBuilder("");
            while (fcin.read(rBuffer) != -1) {
                int rSize = rBuffer.position();
                rBuffer.rewind();
                rBuffer.get(bs);
                rBuffer.clear();
                String tempString = new String(bs, 0, rSize,charset);
                tempString = tempString.replaceAll("\r", "");

                int fromIndex = 0;
                int endIndex = 0;
                while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1) {
                    String line = tempString.substring(fromIndex, endIndex);
                    line = strBuf.toString() + line;
                    strline.append(line.trim());

                    strBuf.delete(0, strBuf.length());
                    fromIndex = endIndex + 1;
                }
                if (rSize > tempString.length()) {
                    strline.append(tempString.substring(fromIndex, tempString.length()));
                    strBuf.append(tempString.substring(fromIndex, tempString.length()));
                } else {
                    strline.append(tempString.substring(fromIndex, rSize));
                    strBuf.append(tempString.substring(fromIndex, rSize));
                }
            }
//            System.out.println(strline.toString().replaceAll("\"", "'"));
        } catch (Exception e) {

        }


        return strline.toString().replaceAll("\"", "'");
    }

    //查询所有分类
    @RequestMapping("/findAllCatalaog")
    public List<Catalog> findAllCatalaog(){
        List<Catalog> all = catalogRepository.findAll();
        return all;
    }

    //根据分类id查出分类下所有书籍
    @RequestMapping(value ="/findCatalog",method = RequestMethod.POST)
    public List<Book> findCatalog(@RequestBody Catalog catalog){
        Integer cid = catalog.getCid();
        List<Book> catalog1 = bookService.findCatalog(cid);
        return catalog1;
    }
    //根据id倒序查询
    @RequestMapping("/findNewBook")
    public List<Book> findNewBook(){
        List<Book> newBook = bookService.findNewBook();
        return newBook;
    }
    //查询推荐
    @RequestMapping("/findSelection")
    public List<Book> findSelection(){
        List<Book> bookList=new ArrayList<>();
        List<Selection> selectionId = selectionService.findSelectionId();
//        System.out.println(selectionId);
        for (Selection s:selectionId){
            Integer bid = s.getBid();
            Book bookId = bookService.findBookId(bid);
            bookList.add(bookId);
        }
        return bookList;
    }
    //添加推荐
    @RequestMapping(value = "/addSelection",method = RequestMethod.POST)
    public String addSelection(@RequestBody Book book){
        Integer bid = book.getBid();
        System.out.println(bid);
        Optional<Selection> byId = selectionRepository.findByBid(bid);

    System.out.println(byId.isPresent());
        if (byId.isPresent()){
            return "error";
        }
        Selection selection=new Selection();
        selection.setBid(bid);
        selectionRepository.save(selection);
        return "success";
    }
    //查询言情
    @RequestMapping("/findRomantic")
    public List<Book> findRomantic(){
        List<Book> romantic = bookService.findRomantic();
        return romantic;
    }
    //查询玄幻
    @RequestMapping("/findMan")
    public List<Book> findMan(){
        List<Book> man = bookService.findMan();
        return man;
    }
    //后台查询
    @RequestMapping("/findBookDown")
    public List<BookDown> findBookDown(){
        List<BookDown> bookDownList=new ArrayList<>();
        List<Book> all = bookService.findAscMoney();
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
            Book book=new Book();
            Author author=new Author();
            Catalog catalog1=new Catalog();
            book.setBid(bid);
            book.setBprice(bprice);
            book.setNummoney(nummoney);
            book.setBname(bname);
            book.setBtickets(btickets);
            bookDown.setBook(book);
//            bookDown.getBook().setBname(bname);
//
//            bookDown.getBook().setNummoney(nummoney);
//            bookDown.getBook().setBtickets(btickets);
//            bookDown.getBook().setBprice(bprice);
            author.setAname(aname);
            catalog1.setCname(catalog);

//            bookDown.getAuthor().setAname(aname);
//            bookDown.getCatalog().setCname(catalog);
            bookDown.setAuthor(author);
            bookDown.setCatalog(catalog1);
            bookDownList.add(bookDown);

        }
        return bookDownList;
    }
    //取消推荐书籍
    @RequestMapping("/deleteSelection")
    public String deleteSelection(@RequestBody Book book){
        Integer bid = book.getBid();
        Selection sid = selectionService.findSid(bid);
        Integer sid1 = sid.getSid();
        selectionRepository.deleteById(sid1);
        return "成功取消";
    }
    //下架书籍
    @RequestMapping("/deleteBook")
    public String deleteBook(@RequestBody Book book){
        Integer bid = book.getBid();
        bookRepository.deleteById(bid);
        return "下架成功";
    }


    @RequestMapping("/save")
    public void save() throws Exception {
        Separater separater=new Separater();
        separater.save();
    }


//    // 在线打开方式 下载
//    @RequestMapping("/down")
//    public void downLoad(HttpServletResponse response) throws Exception {
//        System.out.println(1111111);
//        File f = new File("http://q04351w6a.bkt.clouddn.com/%E5%9C%A3%E5%A2%9F.txt");
//
//        OutputStream out = response.getOutputStream();
//        if (!f.exists()) {
//            response.setCharacterEncoding("UTF-8");
////            String notFileHtml=getNotFileHtml(filePath,"文件找不到");
////            out.write(notFileHtml.getBytes("UTF-8"));
////            out.flush();
//            return;
//        }
//        System.out.println(f);
//        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
//        byte[] buf = new byte[1024];
//        int len = 0;
//        response.reset(); // 非常重要
//        response.setContentType("application/x-msdownload");
//        response.setHeader("Content-Disposition", "attachment; filename=" + "圣墟.txt");
//
//        while ((len = br.read(buf)) > 0)
//            out.write(buf, 0, len);
//        br.close();
//        out.close();
//    }
}
