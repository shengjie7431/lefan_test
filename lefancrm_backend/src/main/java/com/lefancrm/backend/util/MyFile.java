package com.lefancrm.backend.util;

import java.io.File;
import java.util.Comparator;

/**
 * 自定义排序类
 */
public class MyFile implements Comparator<File> {
    @Override
    public int compare(File o1, File o2) {
        int diff = o1.getName().charAt(0) - o2.getName().charAt(0);
        if (diff > 0){
            return 1;
        }else if (diff < 0){
            return -1;
        }
        return 0;
    }
}
