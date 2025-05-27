package com.lefancrm.apicenter.util;
import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.util.JSONDTO.His;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.List;

public class FileTxtUtil {
    public static String fff = "C:\\mq\\read\\from.xml";

    public static void main1(String[] args) throws Exception {

        final int BUFFER_SIZE = 0x300000;// 缓冲区大小为3M

        File f = new File(fff);

        /**
         *
         * map(FileChannel.MapMode mode,long position, long size)
         *
         * mode - 根据是按只读、读取/写入或专用（写入时拷贝）来映射文件，分别为 FileChannel.MapMode 类中所定义的
         * READ_ONLY、READ_WRITE 或 PRIVATE 之一
         *
         * position - 文件中的位置，映射区域从此位置开始；必须为非负数
         *
         * size - 要映射的区域大小；必须为非负数且不大于 Integer.MAX_VALUE
         *
         * 所以若想读取文件后半部分内容，如例子所写；若想读取文本后1/8内容，需要这样写map(FileChannel.MapMode.READ_ONLY,
         * f.length()*7/8,f.length()/8)
         *
         * 想读取文件所有内容，需要这样写map(FileChannel.MapMode.READ_ONLY, 0,f.length())
         *
         */

        MappedByteBuffer inputBuffer = new RandomAccessFile(f, "r")
                .getChannel().map(FileChannel.MapMode.READ_ONLY,
                        f.length() / 2, f.length() / 2);

        byte[] dst = new byte[BUFFER_SIZE];// 每次读出3M的内容

        long start = System.currentTimeMillis();

        for (int offset = 0; offset < inputBuffer.capacity(); offset += BUFFER_SIZE) {

            if (inputBuffer.capacity() - offset >= BUFFER_SIZE) {

                for (int i = 0; i < BUFFER_SIZE; i++)

                    dst[i] = inputBuffer.get(offset + i);

            } else {

                for (int i = 0; i < inputBuffer.capacity() - offset; i++)

                    dst[i] = inputBuffer.get(offset + i);

            }

            int length = (inputBuffer.capacity() % BUFFER_SIZE == 0) ? BUFFER_SIZE
                    : inputBuffer.capacity() % BUFFER_SIZE;

            System.out.println(new String(dst, 0, length));// new
            // String(dst,0,length)这样可以取出缓存保存的字符串，可以对其进行操作

        }

        long end = System.currentTimeMillis();

        System.out.println("读取文件文件一半内容花费：" + (end - start) + "毫秒");

    }

    public static void main2(String[] args) throws Exception {
        int bufSize = 1024;
        byte[] bs = new byte[bufSize];
        ByteBuffer byteBuf = ByteBuffer.allocate(1024);
        FileChannel channel = new RandomAccessFile(fff, "r").getChannel();
        while (channel.read(byteBuf) != -1) {
            int size = byteBuf.position();
            byteBuf.rewind();
            byteBuf.get(bs); // 把文件当字符串处理，直接打印做为一个例子。
            byteBuf.clear();
        }

    }


    public static void main3(String[] args) throws Exception {
        FileTxtUtil fileTxtUtil = new FileTxtUtil();
        String folder = "F:\\新建文件夹\\相互宝医院数据";
        List<His> data = fileTxtUtil.getHisData(folder);
        System.out.println(data.size());

//        int bufSize = 1024;
//        byte[] bs = new byte[bufSize];
//        ByteBuffer byteBuf = ByteBuffer.allocate(1024);
//        FileChannel channel = new RandomAccessFile("F:\\新建文件夹\\医院.txt","r").getChannel();
//        StringBuffer stringBuffer = new StringBuffer();
//        while(channel.read(byteBuf) != -1) {
//            int size = byteBuf.position();
//            byteBuf.rewind();
//            byteBuf.get(bs);
//            // 把文件当字符串处理，直接打印做为一个例子。
//            stringBuffer.append(new String(bs, 0, size));
//            byteBuf.clear();
//        }
//        String json = stringBuffer.toString();
    }

    public static List<His> getHisData(String folder) throws Exception{
        FileTxtUtil fileTxtUtil = new FileTxtUtil();
        File folderFile = new File(folder);
        List<His> list = new ArrayList<His>();
        if (folderFile.isDirectory()){
            File[] files = folderFile.listFiles();
            for (File file : files) {
                System.out.println(file.getPath());
                String json = fileTxtUtil.readFile(file.getPath());
                List<His> hisList = JSONArray.parseArray(json, His.class);
                for (His his : hisList) {
                    if (!StringUtils.isEmpty(his.getInvestMethods())) {
                        his.setInvestMethodsStr(String.join(",",his.getInvestMethods()));
                    }
                    if (!StringUtils.isEmpty(his.getEvidenceFormats())){
                        his.setEvidenceFormatsStr(String.join(",",his.getEvidenceFormats()));
                    }
                }
                list.addAll(hisList);
            }
        }
        return list;
    }



    public String readFile(String filePath) throws IOException {
        StringBuffer context = new StringBuffer();
        // 文件编码是utf8,需要用utf8解码
        Charset charset = Charset.forName("utf-8");
        CharsetDecoder decoder = charset.newDecoder();

        File file = new File(filePath);
        RandomAccessFile raFile = new RandomAccessFile(file, "rw");
        FileChannel fChannel = raFile.getChannel();

        ByteBuffer bBuf = ByteBuffer.allocate(1024 * 5); // 缓存大小设置为个字节。仅仅是测试用。
        CharBuffer cBuf = CharBuffer.allocate(1024 * 5);

        int bytesRead = fChannel.read(bBuf); // 从文件通道读取字节到buffer.
        char[] tmp = null; // 临时存放转码后的字符
        byte[] remainByte = null;// 存放decode操作后未处理完的字节。decode仅仅转码尽可能多的字节，此次转码不了的字节需要缓存，下次再转
        int leftNum = 0; // 未转码的字节数
        while (bytesRead != -1) {

            bBuf.flip(); // 切换buffer从写模式到读模式
            decoder.decode(bBuf, cBuf, true); // 以utf8编码转换ByteBuffer到CharBuffer
            cBuf.flip(); // 切换buffer从写模式到读模式
            remainByte = null;
            leftNum = bBuf.limit() - bBuf.position();
            if (leftNum > 0) { // 记录未转换完的字节
                remainByte = new byte[leftNum];
                bBuf.get(remainByte, 0, leftNum);
            }

            // 输出已转换的字符
            tmp = new char[cBuf.length()];
            while (cBuf.hasRemaining()) {
                cBuf.get(tmp);
//                System.out.print(new String(tmp));
                context.append(new String(tmp));
            }

            bBuf.clear(); // 切换buffer从读模式到写模式
            cBuf.clear(); // 切换buffer从读模式到写模式
            if (remainByte != null) {
                bBuf.put(remainByte); // 将未转换完的字节写入bBuf，与下次读取的byte一起转换
            }
            bytesRead = fChannel.read(bBuf);
        }
        raFile.close();
        return context.toString();
    }




    public static  void  main(String[] args) throws IOException {
        //定义输出目录
        String FileOut="E:\\1.sql";
        BufferedWriter bw=new BufferedWriter(new FileWriter(FileOut));

        //读取目录下的每个文件或者文件夹，并读取文件的内容写到目标文字中去
        File[] list = new File("E:\\sql\\1\\").listFiles();
        int fileCount = 0;
        int folderConut= 0;
        for(File file : list)
        {
            if(file.isFile())
            {
                fileCount++;
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while((line=br.readLine())!=null) {
                    bw.write(line);
                    bw.newLine();
                }
                br.close();
            }else {
                folderConut++;
            }
        }
        bw.close();
    }

}
