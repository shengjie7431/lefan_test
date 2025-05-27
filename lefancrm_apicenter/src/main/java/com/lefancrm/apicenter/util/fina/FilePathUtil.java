package com.lefancrm.apicenter.util.fina;

import org.apache.commons.lang3.StringUtils;

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


    public static String convert(String sourcePath){
        String url = sourcePath;
        url = url.replace("\\","/");
        String[] split = url.split("/");
        StringBuffer path = new StringBuffer();
        for (String s : split) {
            if (StringUtils.isNotEmpty(s)){
                path.append("/" + s);
            }
        }
        return path.toString();
    }

    /**
     * 把HTTP路径转换成文件真实路径
     * @param path
     * @return
     */
    public static String convertToRealPath(String httpPath, String path){
        return path.replace(httpPath, realPath);
    }
}
