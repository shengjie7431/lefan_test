package com.lefancrm.backend.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	/**
	 * 判断字符串是否是乱码
	 * 
	 * @param strName
	 *            字符串
	 * @return 是否是乱码
	 */
	public static boolean isMessyCode(String strName) {
		Pattern p = Pattern.compile("\\s*|t*|r*|n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		float count = 0;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {
				if (!isChinese(c)) {
					count = count + 1;
				}
			}
		}
		float result = count / chLength;
		if (result > 0.1) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * 判断字符是否是中文
	 * 
	 * @param c 字符
	 * 
	 * @return 是否是中文
	 */
	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

    public static String decode(String param) throws Exception{
        if(StringUtils.isEmpty(param)){
            return param;
        }
        if(Util.isMessyCode(param)){
            String newOrgName = new String(param.getBytes("iso-8859-1"), "UTF-8");
            param = newOrgName;
            if(Util.isMessyCode(newOrgName)){
                String newOrgNameTo = new String(param.getBytes("iso-8859-1"), "GBK");
                param = newOrgNameTo;
            }
        }
        return param;
    }

	public static void main(String[] args) {
		//å·²æ¿æ´»
		//哈哈
		System.err.println(isMessyCode("å·²æ¿æ´»"));
		System.err.println(isMessyCode("哈哈²æ¿"));
	}
}
