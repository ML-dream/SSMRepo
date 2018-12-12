package com.wl.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wl.forms.ItemCode;
import com.wl.forms.StockInfo;
public class Stockcl {

	public static String toItemCode(String itemType,String itemname){
		String itemId="";
		
		int count=0;
    	String itemCodeSql="select max(seq) seq from itemcode where itemtype='"+itemType+"'";
		 ItemCode itemcode=new ItemCode();
		 try{
			 itemcode=Sqlhelper.exeQueryBean(itemCodeSql, null, ItemCode.class);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 count=StringUtil.isNullOrEmpty(itemcode.getSeq())?0:itemcode.getSeq();
		 count++;
		 String stringcount=Integer.toString(count);
		 String result="";
		 for(int i=0,n=6-stringcount.length();i<n;i++){
			
				result += "0";
		 }
		 itemId=itemType+result+stringcount;
		 //存
		 String itemSql="insert into itemcode(seq,itemid,itemname,itemtype) values('"+count+"','"+itemId+"','"+itemname+"','"+itemType+"')";
		 try{
			 Sqlhelper.executeUpdate(itemSql, null);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		return itemId;
	}
	
	public static void Stockin(String item_id,String item_name,String spec,String item_type,String warehouse_id,String stock_id,double num1,double unitPrice,String unit){
		
			String sql="select ITEM_ID,STOCK_NUM from Stock where item_id='"+item_id+"'";
			
			List<StockInfo> stockList=new ArrayList<StockInfo>();
		try {
			stockList=Sqlhelper.exeQueryList(sql, null, StockInfo.class);	
			boolean b=false;
			for(int i=0,l=stockList.size();i<l;i++){
				StockInfo stock=new StockInfo();
				stock=stockList.get(i);
				if(item_id.equals(stock.getItemId())){
					double num2=stock.getStockNum();
					double num=num1+num2;
					String sql1="update STOCK set STOCK_NUM='"+num+"',unitprice='"+unitPrice+"',warehouse_id='"+warehouse_id+"' where ITEM_ID='"+item_id+"'";
					System.out.println("sql1="+sql1);
					Sqlhelper.executeUpdate(sql1, null);
					b=true;
				}
				
			}
			if(!b&&item_id.length()>0){
				String sql2="insert into stock(item_id,item_name,warehouse_id,stock_id,stock_num,unit,item_type,spec,unitprice) values('"+item_id+"','"+item_name+"','"+warehouse_id+"','"+stock_id+"',"+num1+",'"+unit+"','"+item_type+"','"+spec+"',"+unitPrice+")";
				System.out.println(sql2);
				Sqlhelper.executeUpdate(sql2, null);
				}
		
		}catch(Exception e){
			e.printStackTrace();
			
		}

}
	public static void Stockout(String item_id,double num1){
		String sql="select ITEM_ID,STOCK_NUM from stock where item_id='"+item_id+"'";
		List<StockInfo> stockList=new ArrayList<StockInfo>();
		try {
			System.out.println(sql);
			stockList=Sqlhelper.exeQueryList(sql, null, StockInfo.class);	
			//boolean b=false;
			for(int i=0,l=stockList.size();i<l;i++){
				StockInfo stock=new StockInfo();
				stock=stockList.get(i);
				if(item_id.equals(stock.getItemId())){
					double num2=stock.getStockNum();
					double num=(num2-num1);
					String sql1="update STOCK set STOCK_NUM="+num+" where ITEM_ID='"+item_id+"'";
					System.out.println("sql1="+sql1);
					Sqlhelper.executeUpdate(sql1, null);
					
				}
				
			}
			
			/*ResultSet rs=null;
			rs=Sqlhelper.executeQuery(sq3, null);
			while(rs.next()&&s1.equals(rs.getString(1))){
				
					double num2=rs.getInt(2);
					double num=(num2-num1);
					String sql4="update STOCK set STOCK_NUM="+num+" where ITEM_ID='"+s1+"'";
					System.out.println(sql4);
					Sqlhelper.executeUpdate(sql4, null);
				
				
			}*/
		} catch (Exception e) {
	
			e.printStackTrace();
		}finally{
			
		}
	}
}
