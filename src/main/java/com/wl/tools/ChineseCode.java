package com.wl.tools;

import java.io.UnsupportedEncodingException;
/*
 * 将给定的字符串转化为中文
 * @date 2015.12.30
 * */
public class ChineseCode {
	
	/**
	 * 将给定的字符串转化为UTF-8 编码
	 * @param str 待转化的字符串
	 * @return String UTF-8 编码的中文字符
	 * */
	public static String toUTF8(String str) throws UnsupportedEncodingException{
		return new String(str.getBytes("ISO-8859-1"),"utf-8");
	} 
}
