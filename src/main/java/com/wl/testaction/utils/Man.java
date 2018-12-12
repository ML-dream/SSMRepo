/**
 * 项目名称: work
 * 创建日期：2016-6-22
 * 修改历史：
 *		1.[2016-6-22]创建文件 by Flair
 */
package com.wl.testaction.utils;

public class Man {
	private String name;
	private int sex;
	private String idCard;
	private float salary;
	public Man(String name, int sex, String idCard, float salary) {
		super();
		this.name = name;
		this.sex = sex;
		this.idCard = idCard;
		this.salary = salary;
	}
	
	public Man() {
		super();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	

}