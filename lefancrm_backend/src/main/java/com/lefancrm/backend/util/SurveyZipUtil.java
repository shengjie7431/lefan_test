package com.lefancrm.backend.util;

import com.lefancrm.backend.dto.SurveyUploadFileDto;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SurveyZipUtil {

    public static Map sftpZip(String surveyCno,String confSurveySouce,String surveyCaseNo,String downType,String pdfType) {
        Map<String,Object> map =  new HashMap<>();
        if ("dev".equals(confSurveySouce)){
            map.put("path", "E:\\templete\\email-test.zip");
            map.put("surveyCno", surveyCno);
            return map;
        }
        try {
            String sourcePath = "/mnt/sftp/files/" + confSurveySouce + "/ddr/cno/" + surveyCno + "/direction/";
            String reportPath = "/mnt/sftp/files/" + confSurveySouce + "/ddr/cno/" + surveyCno + "/report/";
            String zipPath = "/mnt/sftp/temp/zip/" + surveyCaseNo + ".zip";

            String fPath = "";
            File file = new File(reportPath);
            if (file.exists() && file.isDirectory()){
                if (file.listFiles().length == 1) {//如果只有一个文件 则就取当前文件
                    fPath = file.listFiles()[0].getPath();
                }
            }
            if ("pdf".equals(downType)){//将word转换为pdf
                List<String> pdfFiles = new ArrayList<>();
                String pdfPath = "/mnt/sftp/temp/pdf/" + surveyCaseNo + ".pdf";
                WordToPDF.doc2pdf(fPath,pdfPath);//将word转换为pdf
                fPath = pdfPath;
                //获取所有的非图片文件
                List<String> attrs = new ArrayList<>();
                //所有的图片文件压缩成一个pdf
                String imgPdfPath = "/mnt/sftp/temp/pdf/"+surveyCaseNo+"(附件).pdf";
                Map<String,List<String>> tempMap = new HashMap<>();//目录对应的图片集合
                File temp = new File(sourcePath);//方向目录
                if (temp.exists())
                {
                    MyFile myFile = new MyFile();
                    File[] names = temp.listFiles();//所有的方向名称
                    Arrays.sort(names,myFile);
                    for (File name : names) {
                        if (name.isDirectory()){
                            File[] sonFoder = name.listFiles();//所有方向名称对应的子文件夹/老案件也可能没有文件夹直接对应的文件
                            Arrays.sort(sonFoder,myFile);
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
                                    if (file1.getName().indexOf("-不下载") > -1){
                                        continue;
                                    }
                                    imgs = new ArrayList<>();
                                    File[] files = file1.listFiles();//所有方向名称子文件夹的文件
                                    Arrays.sort(files,myFile);
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
                            if ("2".equals(pdfType)){//每个方向单独一个pdf
                                String pdfFile = "/mnt/sftp/temp/pdf/" + System.currentTimeMillis() + "/" + name.getName() + "（附件）.pdf";
                                pdfFile = PDFUtil.imgToPdf(tempMap,pdfFile,name.getName());
                                pdfFiles.add(pdfFile);
                            }
                        }
                    }
                }
                List<String> files = new ArrayList<String>();
                if ("1".equals(pdfType)){
                    imgPdfPath = PDFUtil.imgToPdf(tempMap,imgPdfPath);
                    files.add(imgPdfPath);//图片附件的pdf
                }else if ("2".equals(pdfType)){
                    files.addAll(pdfFiles);
                }
                files.add(fPath);//报告
                files.addAll(attrs);//所有非图片的附件
                FileZipUtil.createZipEntrust("/a/b/c/d/", zipPath,files);//soucePath无意义
            }else {
                FileZipUtil.createZipEntrust(sourcePath, zipPath,fPath);
            }

//            FileZipUtil.createZipEmail(sourcePath, zipPath,reportPath);
            map.put("path", zipPath);
            map.put("surveyCno", surveyCno);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("抛出异常");
            return null;
        }
    }

    /**
     * 判断文件是否是图片
     * @param file
     * @return
     */
    public static boolean checkImg(File file){
        try {
            Image image = ImageIO.read(file);
            return image != null;
        } catch(IOException ex) {
            return false;
        }
    }

    public static void main(String[] args) {
        MyFile myFile = new MyFile();
        File file = new File("E:\\");
        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println(file1.getName());
        }
        System.out.println("*****************************************");
        Arrays.sort(files,myFile);
        for (File file1 : files) {
            System.out.println(file1.getName());
        }



//        try {
//            //nio压缩文件
//            zipOutputStream=new ZipOutputStream(new FileOutputStream(new File("F:\\新建文件夹\\ceshi.zip")));
//            System.out.println("start:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//            toZip(new File("F:\\新建文件夹\\相关材料\\"));
//            System.out.println("end:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//            zipOutputStream.flush();
//            zipOutputStream.close();
//
//            //io压缩文件
//            System.out.println("start:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//            FileZipUtil.createZipEmail("F:\\新建文件夹\\相关材料\\", "F:\\新建文件夹\\ceshi2.zip","");
//            System.out.println("end:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//
//            //切割文件
//            System.out.println("start:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//            List<Map<String,Object>> zipFiles = ZipPartUtil.zip("F:\\新建文件夹\\ceshi.zip", "F:\\新建文件夹\\切割\\qiege.zip", null, 20);
//            System.out.println("end:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private static  ZipOutputStream zipOutputStream=null;
    private static  ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public static void toZip(File file){
        if (file.isFile()){
            try {
                zipOutputStream.putNextEntry(new ZipEntry(file.getAbsolutePath().substring(3,file.getAbsolutePath().length())));
                FileChannel channel = new FileInputStream(file).getChannel();
                while (true){
                    byteBuffer.clear();
                    int read = channel.read(byteBuffer);
                    if (read==-1)break;;
                    zipOutputStream.write(byteBuffer.array());
                }
                channel.close();
                zipOutputStream.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            File[] files = file.listFiles();
            if (files==null||files.length==0){
                try {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getAbsolutePath().substring(3,file.getAbsolutePath().length())+"/"));
                    zipOutputStream.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                for (File file2:files) {
                    toZip(file2);
                }
            }
        }
    }



}
