package com.wl.tools;

import java.util.HashMap;

public class CheckData {
	//入库
	public static void Checkin(HashMap datalist,String checksheet_id,String checkindetl_id,String item_id,String item_name,
		String item_type,String spec,String unit,String checkin_num,String unitprice,String price,
		String warehouse_id,String stock_id,String order_id,String lot,String quality_id,String memo,String issue_num,
		String createPerson,String createTime, String changePerson,String changeTime){
		
		String checkindetl_id1=(String) datalist.get(checkindetl_id);
		String item_name1=(String) (datalist.get(item_name));
		String item_type1=(String) datalist.get(item_type);
		String spec1=(String) datalist.get(spec);
		String unit1=(String) datalist.get(unit);
		String num1=(String) datalist.get(checkin_num);
		String uprice1=(String) datalist.get(unitprice);
		String tprice1=(String) datalist.get(price);
		//String warehouse_id1=(String) datalist.get(warehouse_id);
		String stock_id1=(String) datalist.get(stock_id);
		String order_id1=(String) datalist.get(order_id);
		String lot1=(String) datalist.get(lot);
		String quality_id1=(String) datalist.get(quality_id);
		String memo1=(String) datalist.get(memo); 
		String issue_num1=(String) datalist.get(issue_num);
		double checkin_num1=0;
		double unitprice1=0;
		double price1=0;
		if(num1.equals("")){
			checkin_num1=0;
		}else{
			checkin_num1=Double.parseDouble(num1);
		}
		if(uprice1.equals("")){
			unitprice1=0;
		}else{
			unitprice1=Double.parseDouble(uprice1);
		}
		if(tprice1.equals("")){
			price1=0;
		}else{
			price1=Double.parseDouble(tprice1);
		}
		
		String sql1="insert into mycheckin_detl (checksheet_id,checkindetl_id,item_id,item_name,spec,unit,checkin_num,unitprice,price," +
				"stock_id,order_id,lot,quality_id,memo,item_type,issue_num,createperson,createtime,changeperson,changetime) values" +
				"('"+checksheet_id+"','"+checkindetl_id1+"','"+item_id+"','"+item_name1+"','"+spec1+"','"+unit1+"',"+checkin_num1+"," +
				""+unitprice1+","+price1+",'"+stock_id1+"','"+order_id1+"','"+lot1+"','"+quality_id1+"','"+memo1+"','"+item_type1+"'," +
				"'"+issue_num1+"','"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'))";
		System.out.println("sqla="+sql1);
	
		try{
			Sqlhelper.executeUpdate(sql1, null);
			
			Stockcl.Stockin(item_id,item_name1,spec1,item_type1,warehouse_id,stock_id1,checkin_num1,unitprice1,unit1);
			StockinItem.ItemStockin(item_id,item_name1,item_type1,spec1,unitprice1,memo);
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	//出库
//	public static void Checkout(HashMap datalist,String checksheet_id,String checkoutdetl_id,String item_id,String item_name,
//			String issue_num,String property,String item_type,String spec,String unit,String checkout_num,String unitprice,String price,
//			String stock_id,String quality_id,String memo){
//	
//		
//		String item_name1=(String) datalist.get(item_name);
//		String issue_num1=(String) datalist.get(issue_num);
//		String property1=(String) datalist.get(property);
//		String item_type1=(String) datalist.get(item_type);
//		String spec1=(String) datalist.get(spec);
//		String unit1=(String) datalist.get(unit);
//		String num1=(String) datalist.get(checkout_num);
//		String uprice1=(String) datalist.get(unitprice);
//		String tprice1=(String) datalist.get(price);
//		//String warehouse_id1=(String) datalist.get(warehouse_id);
//		//String order_id1=(String) datalist.get(order_id);
//		//String lot1=(String) datalist.get(lot);
//		String stock_id1=(String) datalist.get(stock_id);
//		String quality_id1=(String) datalist.get(quality_id);
//		String memo1=(String) datalist.get(memo); 
//		int checkout_num1=0;
//		float unitprice1=0;
//		float price1=0;
//		if(num1.equals("")){
//			checkout_num1=0;
//		}else{
//			checkout_num1=Integer.parseInt(num1);
//		}
//		if(uprice1.equals("")){
//			unitprice1=0;
//		}else{
//			unitprice1=Float.parseFloat(uprice1);
//		}
//		if(tprice1.equals("")){
//			price1=0;
//		}else{
//			price1=Float.parseFloat(tprice1);
//		}
//		
//		String sql1="insert into mycheckout_detl (checksheet_id,checkoutdetl_id,item_id,item_name,spec,unit," +
//				"checkout_num,unitprice,price,stock_id,quality_id,memo,property,issue_num,item_type) values" +
//				"('"+checksheet_id+"','"+checkoutdetl_id+"','"+item_id+"','"+item_name1+"','"+spec1+"','"+unit1+"'," +
//						""+checkout_num1+","+unitprice1+","+price1+",'"+stock_id1+"','"+quality_id1+"','"+memo1+"'," +
//						"'"+property1+"','"+issue_num1+"','"+item_type1+"')";
//		System.out.println("sqla="+sql1);
//		try{
//			Sqlhelper.executeUpdate(sql1, null);
//			
//			Stockcl.Stockout(item_id,checkout_num1);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		
//		
//		
//		
//	}
}
