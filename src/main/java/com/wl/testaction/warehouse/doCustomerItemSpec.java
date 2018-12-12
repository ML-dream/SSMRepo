package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Stockcl;

public class doCustomerItemSpec extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
//		request.setCharacterEncoding("utf-8");
		
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String checkindate=ChineseCode.toUTF8((String) datamap.get("checkin_date"));
		String checksheetId=ChineseCode.toUTF8((String) datamap.get("checksheet_id"));
		String id=ChineseCode.toUTF8((String) datamap.get("id"));
		String seq=ChineseCode.toUTF8((String) datamap.get("seq"));
		String customerId=ChineseCode.toUTF8((String) datamap.get("company_id"));
		String customerName=ChineseCode.toUTF8((String) datamap.get("company_name"));
		String warehouseId=ChineseCode.toUTF8((String) datamap.get("warehouse_id"));
		String itemId=ChineseCode.toUTF8((String) datamap.get("itemId"));
		String itemName=ChineseCode.toUTF8((String) datamap.get("itemName"));
		String itemTypeId=ChineseCode.toUTF8((String) datamap.get("itemType"));
		String spec=ChineseCode.toUTF8((String) datamap.get("itemspec"));
		String num=ChineseCode.toUTF8((String) datamap.get("itemNum"));
		String price=ChineseCode.toUTF8((String) datamap.get("itemPrice"));
		String stockId=ChineseCode.toUTF8((String) datamap.get("stock_id"));
		String orderId=ChineseCode.toUTF8((String) datamap.get("orderId"));
		String lot=ChineseCode.toUTF8((String) datamap.get("lot"));
		String qualityId=ChineseCode.toUTF8((String) datamap.get("quality_id"));
		String memo=ChineseCode.toUTF8((String) datamap.get("memo"));
		String examineId=ChineseCode.toUTF8((String) datamap.get("examineId"));
		String whstaffId="";
//		String whstaffId=ChineseCode.toUTF8((String) datamap.get("whstaffId"));
		String deliveryId=ChineseCode.toUTF8((String) datamap.get("deliveryId"));
		String status=ChineseCode.toUTF8(request.getParameter("status"));
		int checkinNum=num.equals("")?0:Integer.parseInt(num);
		double Price=Double.parseDouble(price);
		
		HttpSession session=request.getSession();
		String createPerson=((User)session.getAttribute("user")).getStaffCode();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime=df.format(new Date());
		String changeTime=df.format(new Date());
		
		String checkinSql="insert into customercheckin (checkindate,checksheetid,orderid,customerid,customername,itemid,itemname," +
				"itemtypeid,spec,checkinnum,price,warehouseid,stockid,lot,qualityid,memo,deliveryid,examineid,whstaffid,createperson," +
				"createtime,changeperson,changetime) values (to_date('"+checkindate+"','yyyy-MM-dd,hh24:mi:ss'),'"+checksheetId+"','"+orderId+"'," +
				"'"+customerId+"','"+customerName+"','"+itemId+"','"+itemName+"','"+itemTypeId+"','"+spec+"',"+checkinNum+","+Price+"," +
					"'"+warehouseId+"','"+stockId+"','"+lot+"','"+qualityId+"','"+memo+"','"+deliveryId+"','"+examineId+"','"+whstaffId+"'," +
					"'"+createPerson+"',to_date('"+createTime+"','yyyy-MM-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-MM-dd,hh24:mi:ss'))";
		String sqlsheet="insert into checkin_sheetid values("+seq+",'"+id+"','"+checksheetId+"')";
//		String itemSql="insert into item ()";
		
		
		String statusSql="update CUSTOMEASSET set checkinstatus='1'";
		try{
		Sqlhelper.executeUpdate(checkinSql, null);
		Sqlhelper.executeUpdate(sqlsheet, null);
		Sqlhelper.executeUpdate(statusSql, null);
		Stockcl.Stockin(itemId,itemName,spec,itemTypeId,warehouseId,stockId,checkinNum,Price,"");
		String json = "{\"result\":\"操作成功!\",\"status\":1}";
		response.getWriter().append(json).flush();
		
		}catch(Exception e){
			e.printStackTrace();
			String json = "{\"result\":\"操作失败!\",\"status\":0}";
			response.getWriter().append(json).flush();
		}
	}

}
