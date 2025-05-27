package com.lefancrm.backend.util;

import org.springframework.util.StringUtils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by lixianfeng on 2019/3/19.
 */
public class FileZipUtil {
    public static void main(String[] args) {
//        File file = new File("e:\\wdog.sql");
//        if (file.isDirectory()) {
//            System.out.println("yes");
//        }else{
//            System.out.println("no");
//        }
//       FileZipUtil.createZip("E:\\zip", "F:\\sftp\\" + new Date().getTime() + ".zip");
    }
    /**
      * 创建ZIP文件
      * @param sourcePath 文件或文件夹路径
      * @param zipPath 生成的zip文件存在路径（包括文件名）
      */ 
    public static void createZip(String sourcePath, String zipPath) {
    FileOutputStream fos = null;
    ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipPath);
            zos = new ZipOutputStream(fos);
            writeZip(new File(sourcePath), "", zos);
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         } finally {
             try {
                 if (zos != null) {
                    zos.close();
                 }
             } catch (IOException e) {
                e.printStackTrace();
             }
         }
     }
    private static void writeZip(File file, String parentPath, ZipOutputStream zos) {
         if(file.exists()){ 
             if(file.isDirectory()){//处理文件夹 
                 parentPath+=file.getName()+File.separator; 
                 File [] files=file.listFiles(); 
                 if(files.length != 0){ 
                     for(File f:files){ 
                         writeZip(f, parentPath, zos); 
                         } 
                     }else{ //空目录则创建当前目录 
                     try { 
                         zos.putNextEntry(new ZipEntry(parentPath));
                         } catch (IOException e) { 
                         e.printStackTrace(); 
                         } 
                     } 
                 }else{ 
                 FileInputStream fis=null;
                 try { 
                     fis=new FileInputStream(file); 
                     ZipEntry ze = new ZipEntry(parentPath + file.getName()); 
                     zos.putNextEntry(ze); 
                     byte [] content=new byte[1024]; 
                     int len; 
                     while((len=fis.read(content))!=-1){ 
                         zos.write(content,0,len); 
                         zos.flush(); 
                         } 
                    
                     } catch (FileNotFoundException e) { 
                         e.printStackTrace();
                     } catch (IOException e) {
                        e.printStackTrace();
                     }finally{ 
                     try { 
                         if(fis!=null){ 
                             fis.close(); 
                             } 
                         }catch(IOException e){
                            e.printStackTrace();
                     }
                     } 
                 } 
             } 
         }



    /**
     * 创建ZIP文件
     * @param sourcePath 文件或文件夹路径
     * @param zipPath 生成的zip文件存在路径（包括文件名）
     */
    public static void createZipEmail(String sourcePath, String zipPath,String reportPath) {
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipPath);
            zos = new ZipOutputStream(fos);
            File file = new File(sourcePath);//方向目录
            if (file.exists()){
                File[] files = file.listFiles();//方向名称
                for (File file1 : files) {
//                    if (file1.isDirectory()) {
//                        File[] files1 = file1.listFiles();
//                        for (File file2 : files1) {
//                            if (file2.isDirectory()){
//                                writeZipEntrust(new File(file2.getPath()), "", zos);
//                            }
//                        }
//                    }
                    writeZipEntrust(new File(file1.getPath()), "", zos);
                }
            }
            writeZipEntrust(new File(reportPath), "", zos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void writeZipEntrust(File file, String parentPath, ZipOutputStream zos) {
        if(file.exists()){
            MyFile myFile = new MyFile();
            if(file.isDirectory()){//处理文件夹
                parentPath+=file.getName()+File.separator;
                File [] files=file.listFiles();
                Arrays.sort(files,myFile);
                if(files.length != 0){
                    for(File f:files){
                        writeZipEntrust(f, parentPath, zos);
                    }
                }else{ //空目录则创建当前目录
                    try {
                        zos.putNextEntry(new ZipEntry(parentPath));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                FileInputStream fis=null;
                try {
                    fis=new FileInputStream(file);
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());
                    zos.putNextEntry(ze);
                    byte [] content=new byte[1024];
                    int len;
                    while((len=fis.read(content))!=-1){
                        zos.write(content,0,len);
                        zos.flush();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    try {
                        if(fis!=null){
                            fis.close();
                        }
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void createZipEntrust(String sourcePath, String zipPath,String reportPath) {
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipPath);
            zos = new ZipOutputStream(fos);
            File file = new File(sourcePath);
            if (file.exists()){
                File[] files = file.listFiles();//方向名称列表
                for (File file1 : files) {
                    if (file1.isDirectory()) {
                        writeZipEntrust(new File(file1.getPath()), "", zos);
                    }
                }
            }
            writeZipEntrust(new File(reportPath), "", zos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  finally {
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createZipEntrust(String sourcePath, String zipPath, List<String> filePaths) {
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipPath);
            zos = new ZipOutputStream(fos);
            File file = new File(sourcePath);
            if (file.exists()){
                File[] files = file.listFiles();//方向名称列表
                Arrays.sort(files);
                for (File file1 : files) {
                    if (file1.isDirectory()) {
                        //如果方向是在12号之后上传的文件。 则直接压缩。 否则说明是以前的案子（再进入一层压缩）
                        Calendar cal = Calendar.getInstance();
                        long time = file1.lastModified();
                        cal.setTimeInMillis(time);
                        if (cal.getTime().compareTo(new SimpleDateFormat("yyyy-MM-dd").parse("2019-11-12")) >= 0) {
                            writeZipEntrust(new File(file1.getPath()), "", zos);
                        }else{
                            File[] files1 = file1.listFiles();
                            Arrays.sort(files1);
                            for (File file2 : files1) {
                                if (file2.isDirectory()){
                                    writeZipEntrust(new File(file2.getPath()), "", zos);
                                }
                            }
                        }
                    }
                }
            }
            for (String filePath : filePaths) {
                if (StringUtils.isEmpty(filePath)){
                    continue;
                }
                writeZipEntrust(new File(filePath), "", zos);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
