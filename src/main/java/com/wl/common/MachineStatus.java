/**
 * 项目名称: work
 * 创建日期：2016-6-27
 * 修改历史：
 *		1.[2016-6-27]创建文件 by Flair
 */
package com.wl.common;

/**
 * @author Flair
 *
 */
public class MachineStatus {
	/**
	 * 使用中
	 * */
	public static final int INUSE = 0;
	/**
	 * 迁出
	 * */
	public static final int GOOUT = 1;
	/**
	 * 停用
	 * */
	public static final int STOPUSE = 2;
	/**
	 * 报废
	 * */
	public static final int DISCARD = 3;
	/**
	 * 封存
	 * */
	public static final int INSTORE = 4;
	/**
	 * 租出
	 * */
	public static final int SENDOUT = 5;
	

}
