package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Item;
import com.wl.forms.ItemCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Stockcl;
import com.wl.tools.StockinItem;
import com.wl.tools.StringUtil;

public class addWarehouseItemServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public addWarehouseItemServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String warehouseId=(String) datamap.get("warehouse_Id");
		String itemId=StringUtil.isNullOrEmpty((String) datamap.get("itemId"))?"":(String) datamap.get("itemId");
		String itemName=(String) datamap.get("itemName");
		String spec=(String) datamap.get("spec");
		String itemType=(String) datamap.get("productType");
		String stockNum=(String) datamap.get("stockNum");
		String unit=(String) datamap.get("unit");
		String unitPrice=StringUtil.isNullOrEmpty((String) datamap.get("unitPrice"))?"":(String) datamap.get("unitPrice");
		String stockId=(String) datamap.get("stockId");
		
		double unitprice=StringUtil.isNullOrEmpty(unitPrice)?0:Double.parseDouble(unitPrice);
//		boolean b=true;
//		String itemSql="select item_id from item";
//		List<Item> resultList=new ArrayList<Item>();
//		
//		try{
//			resultList=Sqlhelper.exeQueryList(itemSql, null, Item.class);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		for(int i=0,l=resultList.size();i<l;i++){
//			Item item=new Item();
//			item=resultList.get(i);
//			String item_id=item.getItemid();
//			if(itemId.equals(item_id)){
//				b=false;
//				break;
//			}
//		}
//		if(b==false){
//			String json = "{\"result\":\"物料编号已存在，请重新编码!\",\"status\":0}";
//			response.getWriter().append(json).flush();
//		}else{
//			String sql="insert into stock values('"+itemId+"','"+itemName+"','"+warehouseId+"','"+stockId+"'," +
//			"'"+stockNum+"','"+unit+"','"+itemType+"','"+spec+"','"+unitPrice+"')";
//			try{
//					Sqlhelper.executeUpdate(sql, null);
//					StockinItem.ItemStockin(itemId,itemName,itemType,spec,unitprice,"");
//					String json = "{\"result\":\"操作成功!\",\"status\":1}";
//					response.getWriter().append(json).flush();
//			}catch(Exception e){
//				String json = "{\"result\":\"操作失败!\",\"status\":0}";
//				response.getWriter().append(json).flush();
//				e.printStackTrace();
//			}
//		}
		
		
		 int count=0;
		 if(itemId.equals("")){
			 String itemCodeSql="select max(seq) seq from itemcode where itemtype='"+itemType+"'";
			 ItemCode itemcode=new ItemCode();
			 try{
				 itemcode=Sqlhelper.exeQueryBean(itemCodeSql, null, ItemCode.class);
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 count=StringUtil.isNullOrEmpty(itemcode.getSeq())?0:itemcode.getSeq(); 
//			 xiem	 如果count 为0 ，为了保险，查询该类型的数据总数
			 if(count ==0){
				 System.out.println("itemCodeSql  "+itemCodeSql);
				 String sumSql = "select count(1) from " +
				 		"(select t.seq,rownum rn from itemcode t where itemtype='"+itemType+"' ) a " +
				 		"where a.rn =1";
				 int temp = 0;
				 try {
					temp = Sqlhelper.exeQueryCountNum(sumSql, null);
					System.out.println("sql "+sumSql);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
//			如果结果不为0，则提示报错。
				if(temp!=0){
					String json="{\"result\":\"操作失败！\",\"status\":0}";
					response.getWriter().append(json).flush();
					return;
				}
			
			 }
			 count++;
			 String stringcount=Integer.toString(count);
			 String result="";
			 for(int i=0,n=6-stringcount.length();i<n;i++){
				
					result += "0";
			 }
			 itemId=itemType+result+stringcount;
			 //存
			 String itemSql="insert into itemcode(seq,itemid,itemname,itemtype) values('"+count+"','"+itemId+"','"+itemName+"','"+itemType+"')";
			 try{
				 Sqlhelper.executeUpdate(itemSql, null);
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 
			 String sql="insert into stock values('"+itemId+"','"+itemName+"','"+warehouseId+"','"+stockId+"'," +
				"'"+stockNum+"','"+unit+"','"+itemType+"','"+spec+"','"+unitPrice+"')";
				try{
						Sqlhelper.executeUpdate(sql, null);
						StockinItem.ItemStockin(itemId,itemName,itemType,spec,unitprice,"");
						String json = "{\"result\":\"操作成功!\",\"status\":1}";
						response.getWriter().append(json).flush();
				}catch(Exception e){
					String json = "{\"result\":\"操作失败!\",\"status\":0}";
					response.getWriter().append(json).flush();
					e.printStackTrace();
				}
//			 Stockcl.Stockin(itemId,itemName,spec,itemType,warehouseId,"",0,0,unit);
		 }
		
	}

}
