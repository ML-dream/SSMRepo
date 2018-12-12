package com.wl.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wl.forms.Item;
import com.wl.tools.StockinItem;

public class PrData {
	public static void getPrData(HashMap datamap,String prSheetid,String itemId,String poSheetId1,String itemName1,
			String itemType1,String spec1,String unit1,String usage1,String prNum1,String unitPrice1,String price1,
			String taxrate1,String tax1,String stockId1,String memo1,String warehouseId,String createPerson,String createTime,
			String changePerson,String changeTime){
		String itemName=(String) datamap.get(itemName1);
		String itemType=(String) datamap.get(itemType1);
		String spec=(String)datamap.get(spec1);
		String unit=(String)datamap.get(unit1);
		String usage=(String) datamap.get(usage1);
		String num=datamap.get(prNum1).toString().trim();
		String uprice=(String)datamap.get(unitPrice1).toString().trim();
		String tprice=(String)datamap.get(price1).toString().trim();
		String taxrate=(String) datamap.get(taxrate1);
		String tax=(String) datamap.get(tax1);
		String stockId=(String) datamap.get(stockId1);
		String memo=(String) datamap.get(memo1);
		String poSheetId=(String) datamap.get(poSheetId1);
		double prNum=num.equals("")?0:Double.parseDouble(num);
		double unitPrice=uprice.equals("")?0:Double.parseDouble(uprice);
		double price=tprice.equals("")?0:Double.parseDouble(tprice);
		String prsql="insert into PRDETAIL (PRSHEETID,ITEMID,ITEMNAME,ITEMTYPE,SPEC,UNIT," +
				"USSAGE,PRNUM,UNITPRICE,PRICE,TAXRATE,TAX,STOCKID,MEMO,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME,POSHEETID) values ('"+prSheetid+"'," +
				"'"+itemId+"','"+itemName+"','"+itemType+"','"+spec+"','"+unit+"','"+usage+"',"+prNum+","+unitPrice+","+price+",'"+taxrate+"','"+tax+"'," +
				"'"+stockId+"','"+memo+"','"+createPerson+"',to_date('"+createTime+"','yyyy-MM-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-MM-dd,hh24:mi:ss'),'"+poSheetId+"')";
		
		System.out.println("prsql="+prsql);
		try{
			
			Sqlhelper.executeUpdate(prsql, null);
			Stockcl.Stockin(itemId,itemName,spec,itemType,warehouseId,stockId,prNum,unitPrice,unit);
			StockinItem.ItemStockin(itemId,itemName,itemType,spec,unitPrice,memo);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	//采购退货
	public static void getReData(HashMap datamap,String reSheetid,String itemId,String itemName1,
			String itemType1,String spec1,String unit1,String reNum1,String unitPrice1,String price1,
			String stockId1,String memo1,String warehouseId,String createPerson,String createTime,
			String changePerson,String changeTime){
		
		String itemName=(String) datamap.get(itemName1);
		String itemType=(String) datamap.get(itemType1);
		String spec=(String)datamap.get(spec1);
		String unit=(String)datamap.get(unit1);
		String num=datamap.get(reNum1).toString().trim();
		String uprice=datamap.get(unitPrice1).toString().trim();
		String tprice=datamap.get(price1).toString().trim();
		String stockId=(String)datamap.get(stockId1);
		String memo=(String) datamap.get(memo1);
		
		double reNum=num.equals("")?0:Double.parseDouble(num);
		double unitPrice=uprice.equals("")?0:Double.parseDouble(uprice);
		double price=tprice.equals("")?0:Double.parseDouble(tprice);
		String resql="insert into REDETAIL (RESHEETID,ITEMID,ITEMNAME,ITEMTYPE,SPEC,UNIT," +
		"RENUM,UNITPRICE,PRICE,STOCKID,MEMO,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME) values ('"+reSheetid+"'," +
		"'"+itemId+"','"+itemName+"','"+itemType+"','"+spec+"','"+unit+"'," +
		""+reNum+","+unitPrice+","+price+",'"+stockId+"','"+memo+"','"+createPerson+"'," +
		"to_date('"+createTime+"','yyyy-MM-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-MM-dd,hh24:mi:ss'))";
		System.out.println("resql="+resql);
		try{
			Sqlhelper.executeUpdate(resql, null);
			Stockcl.Stockout(itemId,reNum);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
