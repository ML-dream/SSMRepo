package com.wl.tools;

import java.util.ArrayList;
import java.util.List;

import com.wl.forms.ItemSelect;

public class StockinItem {
	@SuppressWarnings("unchecked")
	public static void ItemStockin(String item_id,String item_name,String item_typeid,String spec,double item_price,String memo){
		
		String Sql="select item_id from item";
		List<ItemSelect> resultList=new ArrayList<ItemSelect>();
		boolean b=true;
		try{
			resultList=Sqlhelper.exeQueryList(Sql, null, ItemSelect.class);
	
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int i=0,len=resultList.size();i<len;i++){
			ItemSelect item=new ItemSelect();
			item=resultList.get(i);
			String itemId=item.getItemid();
			if(item_id.equals(itemId)){
				return;
			}
			
		}
		b=false;
		if(!b){
			
		String itemSql="insert into ITEM (ITEM_ID,ITEM_NAME,ITEM_TYPEID,ITEM_SPECIFICATION,ITEM_PRICE,MEMO) values ('"+item_id+"','"+item_name+"','"+item_typeid+"',"+
		"'"+spec+"',"+item_price+",'"+memo+"')";	
		try{
			Sqlhelper.executeUpdate(itemSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		}
	}
}
