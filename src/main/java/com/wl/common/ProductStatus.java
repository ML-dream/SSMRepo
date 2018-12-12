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
public class ProductStatus {
	/**
	 * 新建订单
	 * */
	public static final int ADDED = 0;
	/**
	 * 做工艺
	 * */
	public static final int FODOING = 10;
	/**
	 * 工艺提交
	 * */
	public static final int FOSUBMIT = 11;
	/**
	 * 工艺检查
	 * */
	public static final int FOCHECKING = 12;
	/**
	 * 工艺提交上级
	 * */
	public static final int FOUP = 13;
	/**
	 * 工艺没通过
	 * */
	public static final int FONOTPASS = 14;
	/**
	 * 工艺通过
	 * */
	public static final int FOPASS = 16;	
	/**
	 * 做零件计划
	 * */
	public static final int PARTPLANING = 20;
	/**
	 * 零件计划提交
	 * */
	public static final int PARTPLANSBUMIT = 21;
	/**
	 * 零件计划检查
	 * */
	public static final int PARTPLANCHECKING = 22;
	/**
	 * 零件计划提交上级
	 * */
	public static final int PARTPLANUP = 23;
	/**
	 * 零件计划没通过
	 * */
	public static final int PARTPLANNOTPASS = 24;
	/**
	 * 零件 计划通过
	 * */
	public static final int PARTPLANPASS =26;
	/**
	 * 做工序计划
	 * */
	public static final int PROPLANING = 30;
	/**
	 * 工序计划提交
	 * */
	public static final int PROPLANSUBMIT = 31;
	/**
	 * 工序计划检查
	 * */
	public static final int PROPLANCHECKING = 32;
	/**
	 * 工序计划提交上级
	 * */
	public static final int PROPLANUP = 33;
	/**
	 * 工序计划没通过
	 * */
	public static final int PROPLANNOTPASS = 34;
	/**
	 * 工序计划通过
	 * */
	public static final int PROPLANPASS = 36;
	/**
	 * 派工中
	 * */
	public static final int PAIGONGING = 40;
	/**
	 * 代加工
	 * */
	public static final int WILLDO = 50;
	/**
	 * 正在加工
	 * */
	public static final int DOING = 60;
	/**
	 * 加工完成
	 * */
	public static final int DONE = 70;
	/**
	 * 正在交付
	 * */
	public static final int DELEVERING = 80;
	/**
	 * 交付完成
	 * */
	public static final int DELEVERED = 90;

}
