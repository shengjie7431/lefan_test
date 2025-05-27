package com.lefancrm.backend.util;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.*;

/**
 * Created by DELL on 2017/9/12.
 */
public class PDFUtil {

    private static Font headfont ;// 设置字体大小
    private static Font keyfont;// 设置字体大小
    private static Font textfont;// 设置字体大小

    private static BaseFont bfChinese;
    static{
        try {
            bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            headfont = new Font(bfChinese, 15, Font.BOLD);// 设置字体大小
            keyfont = new Font(bfChinese, 18, Font.BOLD);// 设置字体大小
            textfont = new Font(bfChinese, 15, Font.NORMAL);// 设置字体大小
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public PDFUtil() {

    }

    public static String imgToPdf(Map<String,List<String>> files,String pdfPath){
        if (files.size() == 0) {
            return "";
        }
        try {
            // 输入流
            FileOutputStream fos = new FileOutputStream(pdfPath);
            Font font1  = new Font(bfChinese, 16, Font.BOLD);//
            // 创建文档
            Document document = new Document();
            document.setPageSize(new Rectangle(PageSize.A4.getWidth() + 20,PageSize.A4.getHeight()));
            document.addTitle("Title@sample");
            document.addAuthor("Author@rensanning");
            document.addSubject("Subject@iText sample");
            document.addKeywords("Keywords@iText");
            document.addCreator("Creator@iText");
            document.setMargins(10, 20, 10, 10);
            // 写入PDF文档
            PdfWriter writer = null;
            try {
                writer = PdfWriter.getInstance(document,fos);
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
            document.open();

            Iterator<String> iterator = files.keySet().iterator();
            while (iterator.hasNext()) {
                document.newPage();
//                System.out.println("是否新页：" + document.newPage());
                String key = iterator.next();
                Paragraph paragraph = new Paragraph(key,font1);
                paragraph.setAlignment(Paragraph.ALIGN_LEFT);
                paragraph.setSpacingAfter(5f);// 下留白
                document.add(paragraph);
                // 读取图片流
                BufferedImage img = null;
                // 实例化图片
                Image image = null;
                List<String> imgs = files.get(key);
                for (String imgPath : imgs) {
                    // 读取图片流
                    img = ImageIO.read(new File(imgPath));
                    image = Image.getInstance(imgPath);
                    image.scaleToFit(PageSize.A4.getWidth(), img.getHeight() > PageSize.A4.getHeight() ? PageSize.A4.getHeight() - 30: img.getHeight());
                    // 添加图片到文档
                    document.add(image);
                }
            }
            // 关闭文档
            document.close();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return pdfPath;
    }
    public static String imgToPdf(Map<String,List<String>> files,String pdfPath,String name){
        if (files.size() == 0) {
            return "";
        }
        try {
            // 输入流
            FileOutputStream fos = new FileOutputStream(pdfPath);
            Font font1  = new Font(bfChinese, 16, Font.BOLD);//
            // 创建文档
            Document document = new Document();
            document.setPageSize(new Rectangle(PageSize.A4.getWidth() + 20,PageSize.A4.getHeight()));
            document.addTitle("Title@sample");
            document.addAuthor("Author@rensanning");
            document.addSubject("Subject@iText sample");
            document.addKeywords("Keywords@iText");
            document.addCreator("Creator@iText");
            document.setMargins(10, 20, 10, 10);
            // 写入PDF文档
            PdfWriter writer = null;
            try {
                writer = PdfWriter.getInstance(document,fos);
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
            document.open();

            Iterator<String> iterator = files.keySet().iterator();
            while (iterator.hasNext()) {
                document.newPage();
//                System.out.println("是否新页：" + document.newPage());
                String key = iterator.next();
                if (key.indexOf(name) < 0) {
                    continue;
                }
                Paragraph paragraph = new Paragraph(key,font1);
                paragraph.setAlignment(Paragraph.ALIGN_LEFT);
                paragraph.setSpacingAfter(5f);// 下留白
                document.add(paragraph);
                // 读取图片流
                BufferedImage img = null;
                // 实例化图片
                Image image = null;
                List<String> imgs = files.get(key);
                for (String imgPath : imgs) {
                    // 读取图片流
                    img = ImageIO.read(new File(imgPath));
                    image = Image.getInstance(imgPath);
                    image.scaleToFit(PageSize.A4.getWidth(), img.getHeight() > PageSize.A4.getHeight() ? PageSize.A4.getHeight() - 30: img.getHeight());
                    // 添加图片到文档
                    document.add(image);
                }
            }
            // 关闭文档
            document.close();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return pdfPath;
    }

    public static void main(String[] args) {
        //获取所有的非图片文件
        List<String> attrs = new ArrayList<>();
        //所有的图片文件压缩成一个pdf
        String imgPdfPath = "E:\\mnt\\案件编号\\direction\\"+"测试"+"(附件).pdf";
        Map<String,List<String>> tempMap = new HashMap<>();//目录对应的图片集合
        File temp = new File("E:\\mnt\\案件编号\\direction");//方向目录
        if (temp.exists())
        {
            File[] names = temp.listFiles();//所有的方向名称
            for (File name : names) {
                if (name.isDirectory()){
                    File[] sonFoder = name.listFiles();//所有方向名称对应的子文件夹/老案件也可能没有文件夹直接对应的文件
                    List<String> imgs = new ArrayList<>();
                    for (File file1 : sonFoder) {
                        if (file1.isFile() && !checkImg(file1)) {//是文件并且不是图片
                            attrs.add(file1.getPath());
                        }
                        else if (file1.isFile() && checkImg(file1)){//是文件并且是图片
                            imgs.add(file1.getPath());
                            tempMap.put(name.getName(),imgs);//方向名称对于的图片集合
                        }
                        else if (file1.isDirectory()) {//是文件夹
                            imgs = new ArrayList<>();
                            File[] files = file1.listFiles();//所有方向名称子文件夹的文件
                            for (File file2 : files) {
                                if (file2.isFile() && !checkImg(file2)) {//是文件不是图片
                                    attrs.add(file2.getPath());
                                }
                                else if (file2.isFile() && checkImg(file2)){//是文件是图片
                                    imgs.add(file2.getPath());
                                    tempMap.put(name.getName() + "-" + file1.getName(),imgs);//方向名称对于的图片集合
                                }
                            }
                        }
                    }
                    PDFUtil.imgToPdf(tempMap, "E:\\mnt\\案件编号\\direction\\"+name.getName()+".pdf",name.getName());
                }
            }
        }
//        imgPdfPath = PDFUtil.imgToPdf(tempMap,imgPdfPath);
    }

    /**
     * 判断文件是否是图片
     * @param file
     * @return
     */
    public static boolean checkImg(File file){
        try {
            java.awt.Image image = ImageIO.read(file);
            return image != null;
        } catch(IOException ex) {
            return false;
        }
    }
}
