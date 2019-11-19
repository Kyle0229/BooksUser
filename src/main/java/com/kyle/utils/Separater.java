package com.kyle.utils;

import com.kyle.mapper.ChapterRespository;
import com.kyle.domain.Chapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Separater {
    @Resource
      ChapterRespository chapterRespository;

    /**
     * 生成小说文件夹
     * @param novel
     * @return 成功生成返回文件夹名，已存在返回null
     */
    public  String genarateFolder(File novel) {
        if(!novel.isFile() || !novel.getAbsolutePath().endsWith(".txt")) {
            return null;
        }
        String novelName = novel.getAbsolutePath();
        String folderName = novelName.substring(0, novelName.indexOf(".txt"));
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdirs();
            return folderName;
        }
        return null;
    }
 /**
      * 输出html文件
      * @param bodyContent
      * @param currentFileName
      * @param currentPageIndex
      * @throws Exception
      */



/**
      * 获取章节列表
      * @param novel
      * @throws Exception
      */
         public  List<String> getChapterList(File novel) throws Exception {
	List<String> chapterList = new ArrayList<String>();
	FileInputStream fileInputStream = new FileInputStream(novel);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,getCharsetOfNovel(novel));
        BufferedReader novelbr = new BufferedReader(inputStreamReader);
        int currentIndex = 1;
        String line = novelbr.readLine();
        while(line != null) {
            line=line.replaceAll(" ","");
            if(line.indexOf("第") == 0 && line.indexOf("节") != -1) {
                chapterList.add("第" + currentIndex + "章" + line.substring(line.indexOf("节")+1));
                currentIndex ++;
            } else if (line.indexOf("第") == 0 && line.indexOf("章") != -1) {
                chapterList.add("第" + currentIndex + "章" + line.substring(line.indexOf("章")+1));
                currentIndex ++;
            }
            line = novelbr.readLine();
        }
        novelbr.close();
        fileInputStream.close();
        return chapterList;
 }

public   void generateChapterMenuHtmlFile(String folderName,List<String> chapterList) throws Exception {
	String menuPath = folderName+"\\章节目录.html";
	StringBuilder pageContent = new StringBuilder();
	pageContent.append("<html><head>"
            +"<meta http-equiv='content-type' content='text/html;charset=utf-8'>"
		+"<title>"+folderName.substring(folderName.lastIndexOf("\\")+1)+"章节目录</title>"
		+"<head>"
		+"<body bgcolor='#e6f3ff' id='body'>"
		+ "<h1 align='center'>章节目录</h1><br>"
		+ "<table style='margin :auto;'  cellpadding='10px' cellspacing='0' align='center' border='1'>");
	for (int i = 0; i < chapterList.size(); i++) {
		if(i == 0) {
			pageContent.append("<tr>");
		} else if(i % 5 == 0) {
			pageContent.append("<td><a href='"+chapterList.get(i)+".html'>"+chapterList.get(i)+"</a></td>");
			pageContent.append("</tr>");
 		}
            pageContent.append("<td><a href='"+chapterList.get(i)+".html'>"+chapterList.get(i)+"</a></td>");
        }
	pageContent.append("</table></body></html>");
	PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(menuPath)));

 out.print(pageContent.toString());
out.flush();
 out.close();
 }
/**
          * 判断TXT文件编码方式
          * @param fileName
          * @return
          * @throws IOException
          */
    public  String getCharsetOfNovel(File novel) throws IOException {
	 BufferedInputStream bin = new BufferedInputStream(new FileInputStream(novel));
        byte[] head = new byte[3];
        bin.read(head, 0, head.length);
        String encoding = "gb2312";
if (head[0] == -1 && head[1] == -2 )
	 encoding = "UTF-16";
if (head[0] == -2 && head[1] == -1 )
	 encoding = "Unicode";
if(head[0]==-17 && head[1]==-69 && head[2] ==-65)
	 encoding = "UTF-8";
return encoding;
}

public    void generate(File novel) throws Exception {
	String folderName = genarateFolder(novel);
	if(folderName == null) {
		return ;
	}
 	List<String> chapterList = getChapterList(novel);
//	System.out.println(chapterList);
	generateChapterMenuHtmlFile(folderName, chapterList);
	FileInputStream fileInputStream = new FileInputStream(novel);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,getCharsetOfNovel(novel));
        BufferedReader novelbr = new BufferedReader(inputStreamReader);
        int currentPageIndex = -1;
        StringBuilder content = new StringBuilder();
        String line = novelbr.readLine();
        while(line != null) {
            line=line.replaceAll(" ","");
            if(line.indexOf("第") == 0 && (line.indexOf("节") != -1 || line.indexOf("章") != -1)) {
                if(currentPageIndex > -1) {
//                    System.out.println(currentPageIndex);
//                    System.out.println(content.toString());
//                    System.out.println(chapterList);
                    generateChapterHtmlFile(currentPageIndex, content.toString(),chapterList,folderName);
                    content.delete(0, content.length());
                }
                currentPageIndex ++;
            } else if(currentPageIndex > -1) {
                content.append(line+"<br><br>");
            }
            line = novelbr.readLine();
            line=line.replaceAll(" ","");
        }
        novelbr.close();
        fileInputStream.close();
 }
// public  void main(String[] args) throws Exception {
//        File folder = new File("D:\\小说");
//        File[] files = folder.listFiles();
//
//        for (int i = 0; i < files.length; i++) {
//            Separater.generate(files[i]);
//        }
//    }

    public  void generateChapterHtmlFile(int currentPageIndex, String content, List<String> chapterList, String folderName)throws Exception {
String pageContent="<html><head>"
+"<meta http-equiv='content-type' content='text/html;charset=utf-8'>"
+"<title>"+chapterList.get(currentPageIndex)+"</title>"
+"<script type=\"text/javascript\">"
+"window.onload = function(){"
+"//去掉默认的contextmenu事件，否则会和右键事件同时出现。\r\n"
+"document.oncontextmenu = function(e){"
+"e.preventDefault();"
+"};"
+" document.getElementById(\"body\").onmousedown = function(e){"
+" if(e.button ==2){"
+"window.open('"+chapterList.get(currentPageIndex==chapterList.size()-1?chapterList.size()-1:currentPageIndex+1)+".html','_self')"
+"}else if(e.button ==0){"
+"window.open('"+chapterList.get(currentPageIndex==0?0:currentPageIndex-1)+".html','_self')"
+"}else if(e.button ==1){"
+"window.open('章节目录.html','_self');}}}"
+"</script>"
+"</head><body bgcolor='#e6f3ff' id='body'>"
+"<h1 align='center'>"+chapterList.get(currentPageIndex)+"</h1>"
+"<div style='line-height:40px;font-size:24px;width: 50%;margin :auto'>"+content+"</div>"
+"</br>"
+"<table align='center'>"
//+"<tr>"
//+"<td><a href='"+chapterList.get(currentPageIndex==0?0:currentPageIndex-1)+".html'>上一页</a></td>"
//+"<td><a href='contents.html'>目录</a></td>"
//+"<td><a href='"+chapterList.get(currentPageIndex==chapterList.size()-1?chapterList.size()-1:currentPageIndex+1)+".html'>下一页</a></td>"
//+"</tr>"
+"</table>"
+"</body></html>";
//System.out.println(chapterList.get(currentPageIndex));

//        //计算每个章节的汉字字数
//        char[] t1 = null;
//        t1 = content.toCharArray();
//        int t0 = t1.length;
//        int count = 0;
//        for (int i = 0; i < t0; i++) {
//            if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
//                count ++;
//
//            }
//        }
//        //按照章名保存章节
//        Chapter chapter=new Chapter();
//        int i=0;
//        i++;
//        chapter.setChid(i);
//        chapter.setChnane(chapterList.get(currentPageIndex));
//        chapter.setContent(folderName.substring(folderName.lastIndexOf("\\")+1));
//
//        chapter.setContent(content);
//        chapter.setBid(1);
////     System.out.println(content1);
//        chapter.setNumber(count);
//        chapterRespository.save(chapter);
        String filePath=folderName+"\\"+chapterList.get(currentPageIndex)+".html";
        //计算每个章节的汉字字数
        char[] t1 = null;
        t1 = content.toCharArray();
        int t0 = t1.length;
        int count = 0;
        for (int i = 0; i < t0; i++) {
            if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                count ++;

            }
        }
        //按照章名保存章节
        Chapter chapter=new Chapter();
        int i=0;
        i++;
//        chapter.setChid(i);
        chapter.setChnane(chapterList.get(currentPageIndex));
        chapter.setContent(folderName.substring(folderName.lastIndexOf("\\")+1));

        chapter.setContent(filePath);
        chapter.setBid(3);
//     System.out.println(content1);
        chapter.setNumber(count);
//        System.out.println(chapter);
        chapterRespository.save(chapter);
PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
out.print(pageContent);
out.flush();
out.close();
    }
    @Test
    public void test() throws Exception {
        File folder = new File("D:\\小说");
        File[] files = folder.listFiles();

        for (int i = 0; i < files.length; i++) {
            generate(files[i]);

        }
    }


    public void save() throws Exception {
        File folder = new File("D:\\小说");
        File[] files = folder.listFiles();

        for (int i = 0; i < files.length; i++) {
            generate(files[i]);

        }
    }
//    public  void saveBook(Integer bid) throws Exception {
//        File folder = new File("http://q04351w6a.bkt.clouddn.com/%E5%9C%A3%E5%A2%9F.txt");
////        File[] files = folder.listFiles();
////        File file=new URL("http://q04351w6a.bkt.clouddn.com/%E6%88%91%E6%98%AF%E8%87%B3%E5%B0%8A.txt").getFile();
//       Chapter chapter=new Chapter();
////        for (int i = 0; i < files.length; i++) {
//            chapter.setBid(bid);
//            generate(folder);
//            chapterRespository.saveAndFlush(chapter);
////        }
//    }
//
//public static void main(String[] args) throws Exception {
//    Separater separater=new Separater();
//    separater.saveBook(3);
//}

}
