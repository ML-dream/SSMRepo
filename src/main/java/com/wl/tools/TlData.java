package com.wl.tools;

import java.util.HashMap;

public class TlData {
	public static void getTlData(HashMap datamap,String rm_sheetid,String item_id1,String item_name1,
			String item_type1,String spec1,String unit1,String rm_num1,String unitprice1,String price1,
			String stock_id1,String order_id1,String issue_num1,String memo1,String warehouse_id,String createPerson,
			String createTime,String changePerson,String changeTime){
		String item_id=item_id1;
		String item_name=(String)datamap.get(item_name1);
		String item_type=(String) datamap.get(item_type1);
		String spec=(String)datamap.get(spec1);
		String unit=(String)datamap.get(unit1);
		String num=StringUtil.isNullOrEmpty((String)datamap.get(rm_num1))?"":(String)datamap.get(rm_num1);
		String uprice=StringUtil.isNullOrEmpty((String)datamap.get(unitprice1))?"":(String)datamap.get(unitprice1);
		String tprice=StringUtil.isNullOrEmpty((String)datamap.get(price1))?"":(String)datamap.get(price1);
		String stock_id=(String)datamap.get(stock_id1);
		String order_id=(String)datamap.get(order_id1);
		String issue_num=(String)datamap.get(issue_num1);
		String memo=(String)datamap.get(memo1);
		double rm_num=num.equals("")?0:Double.parseDouble(num);
		double unitprice=uprice.equals("")?0:Double.parseDouble(uprice);
		double price=tprice.equals("")?rm_num*unitprice:Double.parseDouble(tprice);	
		String llsql="insert into tuiliao_detl values('"+rm_sheetid+"','"+item_id+"','"+item_name+"','"+order_id+"'," +
				"'"+issue_num+"','"+item_type+"','"+spec+"','"+unit+"',"+rm_num+","+unitprice+","+price+"," +
				"'"+stock_id+"','"+memo+"','"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss')," +
				"'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'))";
		System.out.println("llsql="+llsql);
		try{
			Sqlhelper.executeUpdate(llsql, null);
			Stockcl.Stockin(item_id,item_name,spec,item_type,warehouse_id,stock_id,rm_num,unitprice,unit);
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
