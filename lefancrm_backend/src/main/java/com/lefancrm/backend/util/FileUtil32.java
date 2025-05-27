package com.lefancrm.backend.util;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class FileUtil32 {
    private static FileUtil32 testFileUtil = null;
    private FileUtil32(){

    }
    private static FileUtil32 getInstance(){
        if (testFileUtil == null){
            return new FileUtil32();
        }
        return testFileUtil;
    }

    public static void main1(String[] args) {
        String filePath = "F:\\新建文件夹\\";
        String httpUrl = "https://oss.esign.cn/1111563786/d265468e-0c49-430b-a493-9e261fb980e2/%E5%9E%AB%E4%BB%98%E5%8D%8F%E8%AE%AE.pdf?Expires=1610349178&OSSAccessKeyId=LTAI4FzYNb4RgSiqd2tKnaPH&Signature=fgJ15JddHtSFqhMFeOyOL6KnUJM%3D";
        testFileUtil = FileUtil32.getInstance();
        List<String> urls = new ArrayList<String>();
        urls.add("F:\\generate\\2.jpg");
        urls.add("F:\\generate\\3.jpg");
        urls.add("F:\\generate\\100x100\\33.jpg");
        testFileUtil.createZip("F:\\generate\\222.zip",urls);











//        String fileName = testFileUtil.getFileName(httpUrl);
//        String downPath = testFileUtil.downloadFromUrl("12345.pdf", filePath, httpUrl);
//        System.out.println(downPath);
    }

    public String getFileName(String httpPath){
        httpPath = httpPath.replace("\\\\","/");
        return httpPath.substring(httpPath.lastIndexOf("/") + 1);
    }


    /**
     * 下载文件到指定目录下
     * @param fileName 本地文件url
     * @param filePath 存放文件目录
     * @param httpPath 文件远程url
     */
    public String downloadFromUrl(String fileName, String filePath, String httpPath) {
        String path = "";
        try {
            URL httpUrl = new URL(httpPath);
            File f = new File(filePath + "/" + fileName);
            FileUtils.copyURLToFile(httpUrl, f);
            path = f.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 压缩文件
     * @param zipName 压缩后文件名
     * @param fileCacheUrlSet 被压缩文件url
     */
    public void createZip(String zipName,List<String> fileCacheUrlSet) {
        try {
            //文件输出流
            OutputStream os = new FileOutputStream(zipName);
            //创建文件压缩流（处理流|过滤流）
            ZipOutputStream zip = new ZipOutputStream(os);
            //将文件输出缓冲流
            BufferedOutputStream bos = new BufferedOutputStream(zip);
            fileCacheUrlSet.forEach(url -> {
                //添加实体
                try {
                    zip.putNextEntry(new ZipEntry(url));
                    InputStream in = new FileInputStream(url);
                    byte[] buffer = new byte[4096];
                    int len;
                    while((len = in.read(buffer)) != -1){
                        bos.write(buffer, 0, len);
                    }
                    //带缓冲的流需要清空缓冲区，强行把缓冲区的数据写到数据流中
                    bos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            zip.closeEntry();
            zip.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        File[] srcFiles = { new File("F:\\generate\\3.jpg"), new File("F:\\generate\\2.jpg"), new File("F:\\generate\\100x100\\33.jpg") };
        File zipFile = new File("F:\\generate\\ZipFile.zip");
        // 调用压缩方法
        zipFiles(srcFiles, zipFile);
    }

    public static String zipFiles(File[] srcFiles, File zipFile) {
        // 判断压缩后的文件存在不，不存在则创建
        if (!zipFile.exists()) {
            try {
                zipFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 创建 FileOutputStream 对象
        FileOutputStream fileOutputStream = null;
        // 创建 ZipOutputStream
        ZipOutputStream zipOutputStream = null;
        // 创建 FileInputStream 对象
        FileInputStream fileInputStream = null;

        try {
            // 实例化 FileOutputStream 对象
            fileOutputStream = new FileOutputStream(zipFile);
            // 实例化 ZipOutputStream 对象
            zipOutputStream = new ZipOutputStream(fileOutputStream);
            // 创建 ZipEntry 对象
            ZipEntry zipEntry = null;
            // 遍历源文件数组
            for (int i = 0; i < srcFiles.length; i++) {
                // 将源文件数组中的当前文件读入 FileInputStream 流中
                if (!srcFiles[i].exists()) {
                    continue;
                }
                fileInputStream = new FileInputStream(srcFiles[i]);
                // 实例化 ZipEntry 对象，源文件数组中的当前文件
                zipEntry = new ZipEntry(System.currentTimeMillis() + "-"+ srcFiles[i].getName());
                zipOutputStream.putNextEntry(zipEntry);
                // 该变量记录每次真正读的字节个数
                int len;
                // 定义每次读取的字节数组
                byte[] buffer = new byte[1024];
                while ((len = fileInputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zipFile.getPath();
    }
}
