package com.wl.testaction.orderManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.common.OrderStatus;
import com.wl.forms.Order;
import com.wl.forms.OrderTree;
import com.wl.forms.ProcessesPlan;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class GetOrderTreeServlet2 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String productName=null;
//		String isWaiXie=null;
		String orderStatusFrom = StringUtil.isNullOrEmpty(request.getParameter("orderStatusFrom"))?OrderStatus.NEWORDER+"":request.getParameter("orderStatusFrom");
		String orderStatusTo = StringUtil.isNullOrEmpty(request.getParameter("orderStatusTo"))?OrderStatus.DELIVERED+"":request.getParameter("orderStatusTo");
//		productName=request.getParameter("productName");
//		isWaiXie=request.getParameter("isWaiXie");
		String productName=StringUtil.isNullOrEmpty(request.getParameter("productName"))?null:ChineseCode.toUTF8(request.getParameter("productName"));
		String isWaiXie=StringUtil.isNullOrEmpty(request.getParameter("isWaiXie"))?"0":ChineseCode.toUTF8(request.getParameter("isWaiXie"));
				
	    String orderSql = "select distinct orderId from (select  A.orderID,B.order_date from processesplan A " +
	    		   "left join orders B on B.order_Id=A.orderId " +
				   "left join order_detail C on C.order_Id=A.orderId " +
				   "where B.order_status<=to_number(?) and B.order_status>=to_number(?) and A.isCo='1'  and A.isdiscard='0' and B.order_date>=to_date('2017-03-10','yyyy-mm-dd') ";
      
	     String itemSql =  "select distinct A.orderId,A.productId,A.issueNum,B.PRODUCT_NAME PRODUCTNAME " +
		    		"from processesplan A " +
		    		"left join order_detail B on A.orderID=B.order_Id AND A.productId=B.product_id AND A.ISSUENum=B.issue_NUM "+
		    		"where A.orderId=? and A.isCo='1' and A.isdiscard='0' ";	

		 String sql= "select A.orderId,A.productId,A.issueNum,A.operId,A.isCo,A.num,C.Fo_opName operName,A.ismenu "+
					 "from processesPlan A " +
					"left join foheader B on B.ORDERID=A.ORDERID AND B.PRODUCTID=A.PRODUCTID AND B.ISSUENUM=A.ISSUENUM "+
		    		"left join fo_detail C on C.product_ID=A.PRODUCTID AND C.FOID=B.FOID and C.fo_no =A.OPERID " +
					"left join order_detail D ON A.orderId=D.order_Id and A.productId=D.product_Id and A.issueNum=D.issue_num "+
					"where A.orderId=? and A.productId=? and A.issueNum=? and A.isCo='1' and A.isdiscard='0' and C.isinuse='1' " ;

		if((productName==null)&&(isWaiXie==null))
		{
	     orderSql = orderSql;   
	     itemSql =	itemSql;
		 sql=sql ;
		}
		
		if(productName!=null){
		 orderSql +=" and C.product_name like'%"+productName+"%' ";
		 itemSql  +=" and B.product_name like'%"+productName+"%' ";
		 sql      +=" and D.product_Name like'%"+productName+"%' ";
		}
		if(isWaiXie!=null){ 
		 orderSql +=" and A.isMenu='"+isWaiXie+"' ";
		 itemSql  +=" and A.isMenu='"+isWaiXie+"' ";
		 sql      +=" and A.isMenu='"+isWaiXie+"' ";	
			
		}
	    
	    
		orderSql+=" order by B.order_date,A.orderid )";
		itemSql+=" order by A.productId";
		sql+=" order by A.operId";
		
 
	    String[] params = {orderStatusTo,orderStatusFrom};
	    List<ProcessesPlan> orderTree = new ArrayList<ProcessesPlan>();
	 
	    System.out.println(orderSql);
	    try {
	    	orderTree= Sqlhelper.exeQueryList(orderSql, params, ProcessesPlan.class);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	    
	    StringBuffer jsonBuffer = new StringBuffer(8192);
	    jsonBuffer.append("[");
	    
	    for (int i = 0,len=orderTree.size(); i < len; i++) {
	    	ProcessesPlan tree = orderTree.get(i);
			jsonBuffer.append("{");
			jsonBuffer.append("\"id\":"+"\""+tree.getOrderId()+"\",");
			jsonBuffer.append("\"pid\":"+"\"0000\",");
			jsonBuffer.append("\"level\":"+"\"1\",");		//1：订单层；2：零件层；3：物料层
			jsonBuffer.append("\"orderId\":"+"\""+tree.getOrderId()+"\",");
			jsonBuffer.append("\"text\":"+"\""+tree.getOrderId()+"\"");
			jsonBuffer.append("},");

	    String[] params2 = {tree.getOrderId()};
	    List<ProcessesPlan> productTree =new ArrayList<ProcessesPlan>();
	    try {
			productTree = Sqlhelper.exeQueryList(itemSql, params2, ProcessesPlan.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   
		for (int j = 0;j<productTree.size();j++) {
			ProcessesPlan product = productTree.get(j);
			jsonBuffer.append("{");
			jsonBuffer.append("\"id\":"+"\""+product.getOrderId()+product.getProductId()+"\",");
			jsonBuffer.append("\"pid\":"+"\""+product.getOrderId()+"\",");
			jsonBuffer.append("\"level\":"+"\""+"2"+"\",");
			jsonBuffer.append("\"issueNum\":"+"\""+product.getIssueNum()+"\",");
			jsonBuffer.append("\"productId\":"+"\""+product.getProductId()+"\",");
			jsonBuffer.append("\"orderId\":"+"\""+product.getOrderId()+"\",");
//			jsonBuffer.append("\"isWaiXie\":"+"\""+product.getIsWaiXie()+"\",");
//			jsonBuffer.append("\"isCaiGou\":"+"\""+product.getIsCaiGou()+"\",");
//			jsonBuffer.append("\"isGongYi\":"+"\""+product.getIsGongYi()+"\",");
//			jsonBuffer.append("\"productStatus\":"+"\""+product.getProductStatus()+"\",");
			jsonBuffer.append("\"text\":"+"\""+product.getProductName()+"\"");
			jsonBuffer.append("},");
		

		List<ProcessesPlan> processTree =new ArrayList<ProcessesPlan>();
		String[] params3={product.getOrderId(),product.getProductId(),product.getIssueNum()};
		try{
			processTree=Sqlhelper.exeQueryList(sql, params3, ProcessesPlan.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		for (int k = 0;k<processTree.size();k++) {
			ProcessesPlan process = processTree.get(k);
			jsonBuffer.append("{");
			jsonBuffer.append("\"id\":"+"\""+process.getOperId()+"\",");
			jsonBuffer.append("\"pid\":"+"\""+process.getOrderId()+process.getProductId()+"\",");
			jsonBuffer.append("\"level\":"+"\""+"3"+"\",");
			jsonBuffer.append("\"issueNum\":"+"\""+process.getIssueNum()+"\",");
			jsonBuffer.append("\"operId\":"+"\""+process.getOperId()+"\",");
			jsonBuffer.append("\"orderId\":"+"\""+process.getOrderId()+"\",");
			jsonBuffer.append("\"operName\":"+"\""+process.getOperName()+"\",");
			jsonBuffer.append("\"isMenu\":"+"\""+process.getIsMenu()+"\",");
			jsonBuffer.append("\"productId\":"+"\""+product.getProductId()+"\",");
    //		jsonBuffer.append("\"isGongYi\":"+"\""+product.getIsGongYi()+"\",");
	//		jsonBuffer.append("\"productStatus\":"+"\""+product.getProductStatus()+"\",");
	//		jsonBuffer.append("\"ProductPrice\":"+"\""+tree.getProductPrice()+"\",");
			jsonBuffer.append("\"text\":"+"\""+process.getOperName()+"\"");
			jsonBuffer.append("},");
		                  }
	             }		
	    }		
			
			
		String jsonString  = jsonBuffer.substring(0, jsonBuffer.length()-1)+"]";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(jsonString).flush();
		System.out.println(jsonString);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       doGet(request,response);
	}


}
