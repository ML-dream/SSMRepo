/**
 * 项目名称: javaTest
 * 创建日期：2016-7-13
 * 修改历史：
 *		1.[2016-7-13]创建文件 by Flair
 */
package com.wl.tools;

/**
 * @author Flair
 *
 */
public class PrintArray {
	public static void print(String[] strs){
		System.out.print("[");
		if (null!=strs) {
			for (int i = 0; i < strs.length; i++) {
				if (i==strs.length-1) {
					System.out.print(strs[i]);
				}else {
					System.out.print(strs[i]+", ");
				}
			}
		}
		System.out.println("]");
	}
}
