package com.lefancrm.apicenter.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.util.StringUtils;
import sh.zj100.common.util.ImageUtil;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by lixianfeng on 2019/3/19.
 */
public class FileZipUtil {
    @Autowired
    private TaskExecutor myExecutor;

    public static void main(String[] args) {
        File file = new File("e:\\wdog.sql");
        if (file.isDirectory()) {
            System.out.println("yes");
        }else{
            System.out.println("no");
        }
//       FileZipUtil.createZip("E:\\data\\cwt888\\", "F:\\sftp\\" + new Date().getTime() + ".zip");
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
                        //如果方向是在12号之后上传的文件。 则直接压缩。 否则说明是以前的案子（再进入一层压缩）
                        Calendar cal = Calendar.getInstance();
                        long time = file1.lastModified();
                        cal.setTimeInMillis(time);
                        if (cal.getTime().compareTo(new SimpleDateFormat("yyyy-MM-dd").parse("2019-11-12")) >= 0) {
                            writeZipEntrust(new File(file1.getPath()), "", zos);
                        }else{
                            File[] files1 = file1.listFiles();
                            for (File file2 : files1) {
                                if (file2.isDirectory()){
                                    writeZipEntrust(new File(file2.getPath()), "", zos);
                                }
                            }
                        }

                        //。。修改日期：2019年11月4日  14点56分  ， 之后部署上传的文件都不存在 任务类型文件夹
//                        Boolean folderTask = false;// true 表示当前在方向名称文件夹下。  false 表示当前在任务类型文件夹下
//                        File[] files1 = file1.listFiles();
//                        if (files1.length > 0){
//                            for (File file2 : files1) {
//                                //如果文件的创建日期是 11月12号之后。则说明 当前在方向文件夹下。
//                                if (file2.isFile()){//如果有文件
//                                    folderTask = true;
//                                    break;
//                                }
//                            }
//                        }
//                        if (folderTask){
//                            //2019年11月4日  14点56分  。方向附件 无 任务类型 。 直接将方向名称下的附件压缩
//                            writeZipEntrust(new File(file1.getPath()), "", zos);
//                        }else{
//                            for (File file2 : files1) {
//                                if (file2.isDirectory()){
//                                    writeZipEntrust(new File(file2.getPath()), "", zos);
//                                }
//                            }
//                        }
                    }
                }
            }
//            writeZipEntrust(new File(sourcePath), "", zos);
            writeZipEntrust(new File(reportPath), "", zos);
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

    public static void createZipEntrust(String sourcePath, String zipPath,List<String> filePaths) {
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
                        //如果方向是在12号之后上传的文件。 则直接压缩。 否则说明是以前的案子（再进入一层压缩）
                        Calendar cal = Calendar.getInstance();
                        long time = file1.lastModified();
                        cal.setTimeInMillis(time);
                        if (cal.getTime().compareTo(new SimpleDateFormat("yyyy-MM-dd").parse("2019-11-12")) >= 0) {
                            writeZipEntrust(new File(file1.getPath()), "", zos);
                        }else{
                            File[] files1 = file1.listFiles();
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

    private static void writeZipEntrust(File file, String parentPath, ZipOutputStream zos) {
        if(file.exists()){
            if(file.isDirectory()){//处理文件夹
                if ("1200x1200".equals(file.getName())){
                    return;
                }
                if (file.getName().indexOf("-不下载") > -1){
                    return;
                }
                parentPath+=file.getName()+File.separator;
                File [] files=file.listFiles();
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
                //如果是文件 并且是图片 大于250KB  则将当前文件压缩后放入zip中  2019年10月18日09点41分
                String fileName = file.getName();
                String filePath = file.getPath();
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                Long size = file.length() / 1024;
                String yasuoFilePath = "";
                if (false){// 如果文件大于 250KB , 则将图片压缩 为 1200 X 1200
                    if ("jpg".equals(suffix) || "jpeg".equals(suffix) || "gif".equals(suffix) || "png".equals(suffix)){
                        File sourceFile = new File(filePath);
                        yasuoFilePath = sourceFile.getParent() + "/1200x1200/";
                        File yasuoFile = new File(yasuoFilePath);
                        if (!yasuoFile.exists()){
                            yasuoFile.mkdir();
                        }
                        yasuoFile = new File(yasuoFilePath + "/" + file.getName());
                        try {
                            ImageUtil.drawImageScale(sourceFile,yasuoFile,1200,1200);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        file = yasuoFile;
                    }
                }


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

                    //2019年11月4日  14点56分  。方向附件 无 任务类型 。 直接将方向名称下的附件压缩
                    //删除压缩文件夹
                    if (!"".equals(yasuoFilePath)){
                        deleteDir(yasuoFilePath);
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
     * 删除目录
     * @param fileDir
     * @return
     */
    public static boolean deleteDir(String fileDir){
        File file=new File(fileDir);
        if(file.exists()) {
            //delete()方法不能删除非空文件夹，所以得用递归方式将file下所有包含内容删除掉，然后再删除file
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    deleteDir(f.getPath());
                }
            }
            file.delete();
        }
        return true;
    }

}
