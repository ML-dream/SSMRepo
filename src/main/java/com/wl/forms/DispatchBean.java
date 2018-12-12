package com.wl.forms;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cfmes.util.sql_data;

public class DispatchBean {
	
	private String orderid;
	private String equipment_drawid;
	private String issue_num;
	private String product_id;
	private String item_id;
	private String oper_id;
	
	private String machine_id;
	private Date start_time;
    private Date end_time;
    
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getEquipment_drawid() {
		return equipment_drawid;
	}
	public void setEquipment_drawid(String equipmentDrawid) {
		equipment_drawid = equipmentDrawid;
	}
	public String getIssue_num() {
		return issue_num;
	}
	public void setIssue_num(String issueNum) {
		issue_num = issueNum;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String productId) {
		product_id = productId;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String itemId) {
		item_id = itemId;
	}
	public String getOper_id() {
		return oper_id;
	}
	public void setOper_id(String operId) {
		oper_id = operId;
	}
	public String getMachine_id() {
		return machine_id;
	}
	public void setMachine_id(String machineId) {
		machine_id = machineId;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date startTime) {
		start_time = startTime;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date endTime) {
		end_time = endTime;
	}
	ResultSet rs=null;
	//获取订单号下拉列表项
	public  ArrayList getOrder(String type){
		
		sql_data sqlbean=new sql_data();
		ArrayList arraylist = new ArrayList();
		String sql = "SELECT distinct order_id From work."+type+" order by order_id"; 
		System.out.println("DispatchBean中的sql语句为："+sql);
		rs=sqlbean.executeQuery(sql);
		
		 try {
			while(rs.next()){
			 	System.out.println("==="+rs.getString(1));
				arraylist.add(rs.getString(1));//把rs中的第一列的值添加到arraylist列表的尾部。——fy
			 }rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sqlbean.closeConn(); 
		}		
		return arraylist;		
	}
}
