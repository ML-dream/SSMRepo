/**
 * 项目名称: work
 * 创建日期：2016-6-21
 * 修改历史：
 *		1.[2016-6-21]创建文件 by Flair
 */
package com.wl.testaction.fileManage;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Flair
 * 
 */
public class GetAllFiles {
	private static ArrayList<String> filelist = new ArrayList<String>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		getFiles("E:/feiqiu");
	}

	static void getFiles(String filePath) {
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				/*
				 * 递归调用
				 */
				getFiles(file.getAbsolutePath());
				filelist.add(file.getAbsolutePath());
				System.out.println("显示" + filePath + "下所有子目录及其文件"+ file.getAbsolutePath());
			} else {
				System.out.println("显示" + filePath + "下所有子目录"+ file.getAbsolutePath());
			}
		}
	}

}
