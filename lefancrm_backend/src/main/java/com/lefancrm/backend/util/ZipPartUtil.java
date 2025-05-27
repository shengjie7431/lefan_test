package com.lefancrm.backend.util;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.*;

public class ZipPartUtil {
    public static void main(String[] args) {
        FileUtils.deleteDir("F:\\ddr\\no1\\direction");



//        try {
//            System.out.println(ZipPartUtil.zip("F:\\新建文件夹\\相关材料.zip", "F:\\新建文件夹\\zip\\test.zip", null, 1));
//        } catch (ZipException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 分卷压缩
     * @param srcFiles 要压缩的文件绝对路径列表（支持多个文件的合并压缩）
     * @param destFile 压缩后的zip文件名
     * @param password   压缩密码
     * @param mb 分卷大小()
     * @return 压缩文件路径（如分卷会返回以 "," 分隔的文件路径列表）
     * @throws ZipException
     */
    public static List<Map<String,Object>> zip(String srcFiles, String destFile, String password, long mb) throws ZipException {
        List<String> list = new ArrayList<String>();
        list.add(srcFiles);
        return ZipPartUtil.zip(list,destFile,password,mb);
    }

    /**
     * 分卷压缩
     * @param srcFiles 要压缩的文件绝对路径列表（支持多个文件的合并压缩）
     * @param destFile 压缩后的zip文件名
     * @param password   压缩密码
     * @param mb 分卷大小()
     * @return 压缩文件路径（如分卷会返回以 "," 分隔的文件路径列表）
     * @throws ZipException
     */
    public static List<Map<String,Object>> zip(List<String> srcFiles, String destFile, String password, long mb) throws ZipException {
        File tmpFile = new File(destFile);
        if (tmpFile.getParentFile().exists()){
            ZipPartUtil.removeDir(tmpFile.getParentFile());
        }
        if (!tmpFile.getParentFile().exists()) {
            tmpFile.getParentFile().mkdirs();
        }
        try {
            if (tmpFile.isFile()) {
                tmpFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        net.lingala.zip4j.core.ZipFile zipFile = new net.lingala.zip4j.core.ZipFile(destFile);

        ArrayList<File> filesToAdd = new ArrayList<File>();
        if(srcFiles!=null&&srcFiles.size()>0){

            int fileCount = srcFiles.size();
            for(int i=0;i<fileCount;i++){
                filesToAdd.add(new File(srcFiles.get(i)));
            }

            ZipParameters parameters = new ZipParameters();
            if(!StringUtils.isBlank(password)) {
                parameters.setEncryptFiles(true);
                parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
                parameters.setPassword(password.toCharArray());
            }
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);

            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

            zipFile.createZipFile(filesToAdd, parameters, true, 1048576 * mb);

            List<Map<String,Object>> zipFiles = new ArrayList<Map<String,Object>>();
            @SuppressWarnings("unchecked")
            ArrayList<String> zipList = zipFile.getSplitZipFiles();
            for (String item : zipList) {
                Double size = new Double(new File(item).length()) / new Double(1048576);
                String str = ZipPartUtil.doubleTrans(size);
                String value = item + "(" + str + "MB)";
                Map<String,Object> map =  new HashMap<>();
                map.put(item,value);
                zipFiles.add(map);
            }
            return zipFiles;
        }
        return null;
    }

    public static String doubleTrans(double num) {
        String number1 = String.format("%.2f", num);//只保留小数点后6位
        double number2 = Double.parseDouble(number1);//類型轉換
        if (Math.round(number2) - number2 == 0) {
            return String.valueOf((long) number2);
        }
        return String.valueOf(number2);
    }

    private static void removeDir(File dir) {
        File[] files=dir.listFiles();
        for(File file:files){
            if(file.isDirectory()){
                removeDir(file);
            }else{
                file.delete();
            }
        }
    }
}