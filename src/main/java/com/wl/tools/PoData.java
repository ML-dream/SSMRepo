package com.wl.tools;

import java.util.HashMap;

public class PoData {
	public static void PoSheetData(HashMap datamap,String po_sheetid,String item_id1,String item_name1,String spec1,String unit1,String kind1,
			String usage1,String po_num1,String unitprice1,String price1,String order_id1,String lot1,String memo1,String createPerson,String createTime,
			String changePerson,String changeTime){
		String item_id=item_id1;
		String item_name=(String)datamap.get(item_name1);
		String spec=(String)datamap.get(spec1);
		String unit=(String)datamap.get(unit1);
		String kind=(String)datamap.get(kind1);
		String usage=(String)datamap.get(usage1);
		String num=datamap.get(po_num1).toString().trim();
		String uprice=(String)datamap.get(unitprice1).toString().trim();
		String tprice=(String)datamap.get(price1).toString().trim();
		String order_id=(String)datamap.get(order_id1);
		String lot=(String)datamap.get(lot1);
		String memo=(String)datamap.get(memo1);
		int po_num=num.equals("")?0:Integer.parseInt(num);
		float unitprice=uprice.equals("")?0:Float.parseFloat(uprice);
		float price=tprice.equals("")?0:Float.parseFloat(tprice);
		String posql="insert into poplan_detl (po_sheetid,item_id,item_name,spec,unit,po_num,unitprice,price,memo,kind,usage,createperson,createtime," +
				"changeperson,changetime) values('"+po_sheetid+"','"+item_id+"','"+item_name+"','"+spec+"','"+unit+"',"+po_num+","+unitprice+","+price+"," +
				"'"+memo+"','"+kind+"','"+usage+"','"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'))";
		try{
			Sqlhelper.executeUpdate(posql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//采购付款
	public static void PaySheetData(HashMap datamap,String paySheetid,String PrSheetId,String Prdate1,String itemId1,String itemName1,String spec1,
			String price1,String haspaid1,String nopay1,String thispay1,String memo1,String createPerson,String createTime,String changePerson,String changeTime){
		
	String Prdate=(String) datamap.get(Prdate1);
	String itemId=(String) datamap.get(itemId1);
	String itemName=(String) datamap.get(itemName1);
	String spec=(String) datamap.get(spec1);
	String price=(String) datamap.get(price1);
	String haspaid=(String) datamap.get(haspaid1);
	String nopay=(String) datamap.get(nopay1);
	String thispay=(String) datamap.get(thispay1);
	String memo=(String) datamap.get(memo1);
	
	String Sql="insert into popaydetl values('"+paySheetid+"','"+PrSheetId+"','"+Prdate+"','"+itemId+"','"+itemName+"','"+spec+"'," +
			"'"+price+"','"+haspaid+"','"+nopay+"','"+thispay+"','"+memo+"','"+createPerson+"',to_date('"+createTime+"','yyyy-MM-dd,hh24:mi:ss')," +
					"'"+changePerson+"',to_date('"+changeTime+"','yyyy-MM-dd,hh24:mi:ss'))";
	
	try{
		Sqlhelper.executeUpdate(Sql, null);
	}catch(Exception e){
		e.printStackTrace();
	}
	}
}
