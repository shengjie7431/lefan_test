package com.lefancrm.apicenter.util;

import org.springframework.core.task.TaskExecutor;
import sh.zj100.common.util.ImageUtil;

import java.io.File;

public class ChineseUtil {
    public static String numberToChinese1(String numberStr)
    {
        String numStr = "0123456789";
        String chineseStr = "零一二三四五六七八九";
        char[] c = numberStr.toCharArray();
        String tempstr = "";
        for (int i = 0; i < c.length; i++)
        {
            int index = numStr.indexOf(c[i]);
            if (index != -1)
            {
                if (c[i] != '0' || i != c.length - 1)
                {
                    tempstr += chineseStr.toCharArray()[index] + "";
                }
                if (c.length - i == 2)
                {
                    tempstr += "十";
                }
                tempstr = tempstr.replace("一十", "十");
            }
        }
        numStr = null;
        chineseStr = null;
        return tempstr;
    }


    public static String numberToChinese(String numberStr)
    {
        if (numberStr == "")
        {
            return "0";
        }
        String numStr = "0123456789";
        String chineseStr = "零一二三四五六七八九";
        char[] c = numberStr.toCharArray();
        String tempstr = "";
        for (int i = 0; i < c.length; i++)
        {
            int index = numStr.indexOf(c[i]);
            if (index != -1)
            {
                if (c[i] != '0' || i != c.length - 1)
                {
                    tempstr += chineseStr.toCharArray()[index] + "";
                }
                if ((c.length - i) % 4 == 1)
                {
                    if ((c.length - i) / 4 == 1)
                    {
                        tempstr += "万";
                    }
                }
                if ((c.length - i) % 4 == 0)
                {
                    tempstr += "千";
                }
                if ((c.length - i) % 4 == 3)
                {
                    tempstr += "百";
                }
                if ((c.length - i) % 4 == 2)
                {
                    tempstr += "十";
                }
                tempstr = tempstr.replace("零万","万").replace("零千","零").replace("零百","零").replace("零十","零").replace("零零","零");
            }
        }
        if (c.length % 4 == 2)
        {
            tempstr = tempstr.replace("一十", "十");
        }
//        System.out.println(tempstr);
//        if (tempstr.substring(tempstr.length() - 1, 1) == "零")
//        {
//            tempstr = tempstr.substring(0, tempstr.length() - 1);
//        }
        numStr = null;
        chineseStr = null;
        return tempstr;
    }

    public static void main(String[] args) {
        System.out.println(DecimalUtil.twoDecimalTOFourFromFive(((float) 4 / 0) * 1200));

//        String idstr = "1,2,3,,,45,67,";
//        String[] ids = idstr.split(",");
//        System.out.println("length:" + ids.length);
//        for (String id : ids) {
//            if (id == null){
//                System.out.println("null");
//            }
//            if ("".equals(id)){
//                System.out.println("双引号");
//            }
//            if (id == ""){
//                System.out.println("value 双引号");
//            }
//            if (!"".equals(id)){
//                System.out.println(id);
//            }
////            System.out.println(id);
//        }
        System.out.println(numberToChinese("2"));

//
//        File sourceFile = new File("F:\\generate\\test.jpg");
//
//        System.out.println("source:" + sourceFile.length() / 1024);
//        File yasuoFile = new File(sourceFile.getParent() + "\\100x100\\" + sourceFile.getName());
//        try {
////            if (!yasuoFile.exists()) {
////                yasuoFile.createNewFile();
////            }
//            ImageUtil.drawImageScale(sourceFile,yasuoFile,1200,1200);
////            ImageUtil.drawImageFix(sourceFile,yasuoFile,100,100);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }
}
