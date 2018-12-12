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
public class CraftWorkStatus {
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
	
}
