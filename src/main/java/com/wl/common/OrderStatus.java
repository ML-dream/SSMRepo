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
public class OrderStatus {
	/**
	 * 新建订单
	 * */
	public static final int NEWORDER = 1;
	/**
	 * 待审核
	 * */
	public final static int WILLCHECK = 2;
	/**
	 * 提交上级审核
	 * */
	public final static int UPCLASSCHECK = 3;
	/**
	 * 审核不通过
	 * */
	public final static int NOTPASS = 4;
	/**
	 * 审核通过
	 * */
	public final static int PASS = 5;
	/**
	 * 备料
	 * */
	public final static int PREPARE = 6;
	/**
	 * 代加工
	 * */
	public final static int WILLDO = 7;
	/**
	 * 加工中
	 * */
	public final static int DOING = 8;
	/**
	 * 加工完成
	 * */
	public final static int DONE = 9;
	/**
	 * 交付中
	 * */
	public final static int DELIVERING = 10;
	/**
	 * 交付完成
	 * */
	public final static int DELIVERED = 11;

}
