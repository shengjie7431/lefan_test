package com.lefancrm.backend.util;

public class Test20220429 {
    public static void main(String[] args) {
        String a = "abc";
        String b = new String("abc");
        String c = "ab" + "c";

        System.out.println(a == b);//f
        System.out.println(a == c);//t
        System.out.println(a.equals(b));//t
        System.out.println(a.equals(c));//t
        System.out.println(a.intern() == b.intern());//t
        System.out.println(a.intern() == c.intern());//t
        System.out.println(a.intern());

        char h = '啊';
    }
}
