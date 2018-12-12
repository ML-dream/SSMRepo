package com.wl.testaction.itemManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.util.sql_data;

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class AddItemServlet extends HttpServlet {
	private static final long serialVersionUID = 8211416997978746631L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String orderId= ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String itemId= ChineseCode.toUTF8(request.getParameter("itemId").trim());
		String itemTypeId= ChineseCode.toUTF8(request.getParameter("itemTypeId").trim());
		String itemName= ChineseCode.toUTF8(request.getParameter("itemName").trim());
		String itemDrawing= ChineseCode.toUTF8(request.getParameter("itemDrawing").trim());
		String FItemId= ChineseCode.toUTF8(request.getParameter("FItemId").trim());
		
		String itemSpecification= ChineseCode.toUTF8(request.getParameter("itemSpecification").trim());
		String purchaseUnite= ChineseCode.toUTF8(request.getParameter("purchaseUnite").trim());
		String itemPrice= ChineseCode.toUTF8(request.getParameter("itemPrice").trim());
		String priceUnit= ChineseCode.toUTF8(request.getParameter("priceUnit").trim());
		String materialMark= ChineseCode.toUTF8(request.getParameter("materialMark"));
		
		String itemAttri= ChineseCode.toUTF8(request.getParameter("itemAttri"));
		String lotSize= ChineseCode.toUTF8(request.getParameter("lotSize"));
		String leadTime= ChineseCode.toUTF8(request.getParameter("leadTime"));
		String safeStock= ChineseCode.toUTF8(request.getParameter("safeStock"));
		String stockLow= ChineseCode.toUTF8(request.getParameter("stockLow"));
		
		String stockHigh= ChineseCode.toUTF8(request.getParameter("stockHigh"));
		String itemWeight= ChineseCode.toUTF8(request.getParameter("itemWeight"));
		String weightUnit= ChineseCode.toUTF8(request.getParameter("weightUnit"));
		
	    String memo = ChineseCode.toUTF8(request.getParameter("memo"));
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
		String  sql = "insert into item " +
				"(item_Id,item_TypeId,item_Name,item_Drawing,FItem_Id," +
				"item_Specification,purchase_Unite,item_Price,price_Unit,material_Mark," +
				"item_Attri,lot_Size,lead_Time,safe_Stock,stock_Low," +
				"stock_High,item_Weight,weight_Unit,create_Time,UPDATE_TIME," +
				"create_Person,UPDATE_PERSON,memo,EXTRA_B) values ('"+
				itemId+"','"+itemTypeId+"','"+itemName+"','"+itemDrawing+"','"+FItemId+"','"+
				itemSpecification+"','"+purchaseUnite+"','"+itemPrice+"','"+priceUnit+"','"+materialMark+"','"+
				itemAttri+"','"+lotSize+"','"+leadTime+"','"+safeStock+"','"+stockLow+"','"+
				stockHigh+"','"+itemWeight+"','"+weightUnit+"',"+
				"to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'" +
				createPerson+"','"+changePerson+"','"+memo+"','3')";
		System.out.println("sql=="+sql);
		
//		String addOrderSql = "insert into order_detail" +
//				"(PRODUCT_ID,PRODUCT_NAME,drawingId,FPRODUCT_ID,SPEC," +
//				"APPENDA,unitprice,PRICEUNIT,PURDUCT_NUM,MEMO," +
//				"CREATETIME,CHANGETIME,CREATEPERSON,CHANGEPERSON,order_id," +
//				"cengci)" +
//				"values('"+itemId+"','"+itemName+"','"+itemDrawing+"','"+FItemId+"','"+itemSpecification+"'," +
//				"'"+purchaseUnite+"','"+itemPrice+"','"+priceUnit+"','"+lotSize+"','"+memo+"'," +
//				"to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+createPerson+"','"+changePerson+"','"+orderId+"'," +
//				"'3')";
//		System.out.println("addOrderSql="+addOrderSql);
		sql_data sqlData = new sql_data();
		try {
//			sqlData.exeUpdateThrowExcep(addOrderSql);
			sqlData.exeUpdateThrowExcep(sql);
			
			
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			//request.getRequestDispatcher("customerManage/showCustomer.jsp").forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("result", "failure");
			e.printStackTrace();
		}
	}


}
