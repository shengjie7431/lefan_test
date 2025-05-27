package com.lefancrm.apicenter.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.nlp.AipNlp;
import com.lefancrm.apicenter.util.JSONDTO.His;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChineseWordProofreadUtil {
    public String readTxt2Json(String fileName){
        File file = new File(fileName);
        String jsonStr = "";
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader =  null;
        BufferedReader bufferedReader = null;

        try {
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream,"UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                jsonStr += line;
            }
            return jsonStr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String json = null;
        String fileName = "F:\\新建文件夹\\医院.txt";
        ChineseWordProofreadUtil util = new ChineseWordProofreadUtil();
        try {
            json = util.getLogAllContent(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String json = util.readTxt2Json(fileName);
        json = json.replaceAll("\r\n","");
        List<His> hisList = JSONArray.parseArray(json, His.class);
        System.out.println(hisList.size());





        Long a = 2189L;
        Long b = 2189L;
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a.equals(b.toString()));
        System.out.println(a.equals(b.longValue()));
        System.out.println(a.equals(b.intValue()));

        a = 1L;
        b = 1L;
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a.equals(b.toString()));
        System.out.println(a.equals(b.longValue()));
        System.out.println(a.equals(b.intValue()));


//        AipNlp client = new AipNlp("","","");
//        JSONObject json = client.ecnet("我是", new HashMap<>());
//        System.out.println(json);
    }



    private final int BUFFER_SIZE = 0x300000;// 缓冲区大小为3M
    public String getLogAllContent(String filename) throws IOException {
        String FlagString = "";
        File file = new File(filename);
        MappedByteBuffer inputBuffer = new RandomAccessFile(file, "r")
                .getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length()); //读取全部内容
        byte[] logByte = new byte[BUFFER_SIZE];//每次读取3M

        for (int offset = 0; offset < inputBuffer.capacity(); offset += BUFFER_SIZE) {
            if(inputBuffer.capacity() - offset >= BUFFER_SIZE){
                for(int i = 0; i < BUFFER_SIZE; i++)
                    logByte[i] = inputBuffer.get(offset + i);
            }else{
                for (int i = 0; i < inputBuffer.capacity() - offset; i++)
                    logByte[i] = inputBuffer.get(offset + i);
            }

            int length = (inputBuffer.capacity() % BUFFER_SIZE == 0) ? BUFFER_SIZE
                    :inputBuffer.capacity() % BUFFER_SIZE;
            FlagString = new String(logByte, 0, length);
        }
        return FlagString;
    }
}
