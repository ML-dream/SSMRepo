package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Order;
import com.wl.forms.ProcessesPlan;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class ProcessOutAssistServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    String orderId=ChineseCode.toUTF8(request.getParameter("orderId"));
	    String productId=ChineseCode.toUTF8(request.getParameter("productId"));
	    String issueNum=ChineseCode.toUTF8(request.getParameter("issueNum"));
	    String[] params={orderId,productId,issueNum};
	    String Sql="select count(*) from Processesplan where orderId=? and productId=? and issueNum=? and isco='1'";
	    String sql="select A.orderId,A.productId,A.operId,E.FO_OPNAME OPERNAME,A.issueNum,A.isco,A.waixieCom,B.companyName " +
	    		"from processesplan A " +
	    		"left join outAssistCom B ON A.waixieCom=B.companyId " +
	    		"left join foheader D on D.ORDERID=A.ORDERID AND D.PRODUCTID=A.PRODUCTID AND D.ISSUENUM=A.ISSUENUM "+
	    		"left join fo_detail E on D.productID=E.PRODUCT_ID AND D.FOID=E.FOID and E.fo_no =A.OPERID "+
	    		"where A.orderId=? and A.productId=? and A.issuenum=? and A.isCo='1' and E.isinuse='1' " +
	    		"order by to_number(A.operId)";
	   int totalCount=0;
	   List<ProcessesPlan> orderList=new ArrayList<ProcessesPlan>();
	   try{
		   totalCount=Sqlhelper.exeQueryCountNum(Sql, params);
		   orderList=Sqlhelper.exeQueryList(sql, params,ProcessesPlan.class);
		   
	      }catch (Exception e) {
			e.printStackTrace();
		}
	    
	    String json = PluSoft.Utils.JSON.Encode(orderList);
		json = "{\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	}


}
