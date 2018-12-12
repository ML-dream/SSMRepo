package com.wl.tools;

import java.util.HashMap;

public class LlData {
	public static void getLlData(HashMap datamap,String ll_sheetid,String item_id1,String item_name1,
			String item_type1,String spec1,String unit1,String ll_num1,String unitprice1,String price1,
			String stock_id1,String order_id1,String issue_num1,String memo1,String productId1,String productName1,String createPerson,
			String createTime,String changePerson,String changeTime){
		String item_id=item_id1;
		String item_name=(String)datamap.get(item_name1);
		String item_type=(String) datamap.get(item_type1);
		String spec=(String)datamap.get(spec1);
		String unit=(String)datamap.get(unit1);
		String num=datamap.get(ll_num1).toString().trim();
		String uprice=(String)datamap.get(unitprice1).toString().trim();
		String tprice=(String)datamap.get(price1).toString().trim();
		String stock_id=(String)datamap.get(stock_id1);
		String order_id=(String)datamap.get(order_id1);
		String issue_num=(String)datamap.get(issue_num1);
		String productId=(String)datamap.get(productId1);
		String productName=(String)datamap.get(productName1);
		String memo=(String)datamap.get(memo1);
		double ll_num=StringUtil.isNullOrEmpty(num)?0:Double.parseDouble(num);
		double unitprice=StringUtil.isNullOrEmpty(uprice)?0:Double.parseDouble(uprice);
		double price=StringUtil.isNullOrEmpty(tprice)?ll_num*unitprice:Double.parseDouble(tprice);
		
		String llsql="insert into lingliao_detl values('"+ll_sheetid+"','"+item_id+"','"+item_name+"','"+order_id+"'," +
				"'"+issue_num+"','"+item_type+"','"+spec+"','"+unit+"',"+ll_num+","+unitprice+","+price+"," +
				"'"+stock_id+"','"+memo+"','"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss')," +
				"'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+productId+"','"+productName+"')";
		System.out.println("llsql="+llsql);
		try{
			Sqlhelper.executeUpdate(llsql, null);
			Stockcl.Stockout(item_id,ll_num);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
