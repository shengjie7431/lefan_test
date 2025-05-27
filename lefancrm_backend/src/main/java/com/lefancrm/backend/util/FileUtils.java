package com.lefancrm.backend.util;

import com.lefancrm.base.utils.DateTimeUtil;
import org.springframework.util.FileCopyUtils;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    public static String saveBytesToFile(String busket,String base64FileValue)  {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            return saveBytesToFile(busket, base64Decoder.decodeBuffer(base64FileValue));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 压缩文件
     *
     * @param filePath 待压缩的文件路径
     * @return 压缩后的文件
     */
    public static File zip(String filePath,List<File> files) {
        String zipName = DateTimeUtil.formatDateTime(new Date(),"yyyyMMddHHmmss");
        File target = new File(filePath+ zipName + ".rar");
        if (target.exists()) {
            target.delete();//删除旧的压缩包
        }
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(target);
            zos = new ZipOutputStream(new BufferedOutputStream(fos));
            for (File file : files) {
                addEntry( file, zos);  //添加对应的文件Entry
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtil.closeQuietly(zos, fos);
        }
        return target;
    }

    /**
     * 压缩文件
     *
     * @param filePath 待压缩的文件路径
     * @return 压缩后的文件
     */
    public static File zipTwo(String filePath,List<File> files) {
        File target = new File(filePath);
        if (target.exists()) {
            target.delete();//删除旧的压缩包
        }
        File fileParent = target.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(target);
            zos = new ZipOutputStream(new BufferedOutputStream(fos));
            for (File file : files) {
                addEntry( file, zos);  //添加对应的文件Entry
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtil.closeQuietly(zos, fos);
        }
        return target;
    }

    /**
     * 扫描添加文件Entry
     *
     * @param source 源文件
     * @param zos    Zip文件输出流
     * @throws IOException
     */
    private static void addEntry(File source, ZipOutputStream zos) throws IOException {
        String entry =  source.getName(); //按目录分级，形如：aaa/bbb.txt
        if (source.isDirectory()) {
            File[] files = source.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    addEntry( file, zos);// 递归列出目录下的所有文件，添加文件 Entry
                }
            }
        } else {
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                byte[] buffer = new byte[1024 * 10];
                fis = new FileInputStream(source);
                bis = new BufferedInputStream(fis, buffer.length);
                int read;
                zos.putNextEntry(new ZipEntry(entry)); //如果只是想将文件夹下的所有文件压缩，不需名要压缩父目录,约定文件名长度 entry.substring(length)
                while ((read = bis.read(buffer, 0, buffer.length)) != -1) {
                    zos.write(buffer, 0, read);
                }
                zos.closeEntry();
            } finally {
                IOUtil.closeQuietly(bis, fis);
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

    public static void copyFolder(String oldPath, String newPath, Boolean del) {
        try {
            // 读取整个文件夹的内容到file字符串数组，下面设置一个游标i，不停地向下移开始读这个数组
            File filelist = new File(oldPath);
            if (!filelist.exists()){
                return;
            }
            String[] file = filelist.list();
            // 如果文件夹不存在，则建立新文件夹
            (new File(newPath)).mkdirs();
            // 要注意，这个temp仅仅是一个临时文件指针
            // 整个程序并没有创建临时文件
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                // 如果oldPath以路径分隔符/或者\结尾，那么则oldPath/文件名就可以了
                // 否则要自己oldPath后面补个路径分隔符再加文件名
                // 谁知道你传递过来的参数是f:/a还是f:/a/啊？
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                // 如果游标遇到文件
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    // 复制并且改名
                    FileOutputStream output = new FileOutputStream(newPath
                            + "/" + (temp.getName()).toString());
                    byte[] bufferarray = new byte[1024 * 64];
                    int prereadlength;
                    while ((prereadlength = input.read(bufferarray)) != -1) {
                        output.write(bufferarray, 0, prereadlength);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                // 如果游标遇到文件夹
                if (temp.isDirectory()) {
                    if (del){
                        copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                    }else{
                        copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i],false);
                    }
                }
            }

            //复制完之后删除原文件目录
            if(del){
                deleteDir(oldPath);
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
        }
    }
    /**
     * 复制某个目录及目录下的所有子目录和文件到新文件夹
     * 同时删除旧目录及文件
     * @param oldPath
     * @param newPath
     */
    public static void copyFolder(String oldPath, String newPath) {
        copyFolder(oldPath, newPath, true);
    }

    public static void copy(File orgFile,File descFile) throws Exception{
        if (!descFile.exists()){
            descFile.getParentFile().mkdirs();
        }
        if (!descFile.exists()){
            descFile.createNewFile();
        }
        FileCopyUtils.copy(orgFile,descFile);
    }

    public static void main(String[] args) {
        File file = new File("E:\\mnt\\打卡-不下载");
//        file.setLastModified(1L);
        System.out.println(file.getName());
//        try {
//            List<File> files = new ArrayList<>();
//            files.add(new File("E:\\mnt\\sftp\\files\\staff\\20210427\\1619503555063\\员工管理 (2).xls"));
//            files.add(new File("E:\\tools\\list.html"));
//            zipTwo("E:\\mnt\\sftp\\files\\staff\\20210427\\1619503555063\\abc\\test\\123.rar",files);
//
//
////            copy(new File("C:\\Users\\liuting\\Desktop\\新建文件夹\\ClaimsVisitInfoMapper.xml"),new File("C:\\Users\\liuting\\Desktop\\abc\\新建文件夹\\ClaimsVisitInfoMapper.xml"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }s
    }

}
