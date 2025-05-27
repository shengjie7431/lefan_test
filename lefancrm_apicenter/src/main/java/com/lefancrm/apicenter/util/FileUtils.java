package com.lefancrm.apicenter.util;

import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by fanshuai on 16/10/28.
 */
public class FileUtils {

    private static final String baseDir = "/data/img";
    public static String saveBytesToFile(String busket, byte[] fileDataStream)  {
        FileOutputStream out =null;
        try {
            File dir = new File("/"+busket+"/");
            File file = new File(dir,System.currentTimeMillis()+".jpg");
            if (!dir.exists()){
                dir.mkdirs();
            }
            if (!file.exists()){
                file.createNewFile();
            }
            out = new FileOutputStream(file);
            out.write(fileDataStream);
            out.flush();
            return "/"+busket+"/"+file.getName();
        }catch (Exception e){
            return null;
        }finally {
            if (out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    out=null;
                }
            }
        }
    }

    public static String saveBytesToFileXls(String busket, byte[] fileDataStream)  {
        FileOutputStream out =null;
        try {
            File dir = new File("/"+busket+"/");
            File file = new File(dir,System.currentTimeMillis()+".xls");
            if (!dir.exists()){
                dir.mkdirs();
            }
            if (!file.exists()){
                file.createNewFile();
            }
            out = new FileOutputStream(file);
            out.write(fileDataStream);
            out.flush();
            return "/"+busket+"/"+file.getName();
        }catch (Exception e){
            return null;
        }finally {
            if (out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    out=null;
                }
            }
        }
    }

    public static String saveBytesToFile(String busket,String base64FileValue)  {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            return saveBytesToFile(busket, base64Decoder.decodeBuffer(base64FileValue));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String saveBytesToFileXls(String busket,String base64FileValue)  {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            return saveBytesToFileXls(busket, base64Decoder.decodeBuffer(base64FileValue));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void downloadFile(String url,String fileName, HttpServletResponse response) {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            File downLoadFile = new File(url);
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO8859_1"));
//            response.setContentType(fileInfo.getContentType() + ";charset=UTF-8");
//            response.setHeader("Content-Length", String.valueOf(fileInfo.getFileSize()));
            in = new BufferedInputStream(new FileInputStream(downLoadFile));
            out = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int length = 0;
            while ((length = in.read(buff)) > 0) {
                out.write(buff, 0, length);
                out.flush();
            }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
        }
    }
}
