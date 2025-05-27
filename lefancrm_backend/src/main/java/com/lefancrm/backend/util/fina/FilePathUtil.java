package com.lefancrm.backend.util.fina;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class FilePathUtil {
    private static String realPath = "/mnt/sftp/files/";

    /**
     * 把文件真实路径转换成HTTP路径
     * @param path
     * @return
     */
    public static String convertToHttpPath(String httpPath,String path){
        return path.replace(realPath, httpPath);
    }

    /**
     * 把HTTP路径转换成文件真实路径
     * @param path
     * @return
     */
    public static String convertToRealPath(String httpPath, String path){
        return path.replace(httpPath, realPath);
    }

    public static void main(String[] args) {
//        System.out.println(convertToHttpPath("/mnt/sftp/files/test/1.jpg"));
//        System.out.println(convertToRealPath("https://ddrapi.shlefan.com/sftp/files/test/1.jpg"));
    }
}
