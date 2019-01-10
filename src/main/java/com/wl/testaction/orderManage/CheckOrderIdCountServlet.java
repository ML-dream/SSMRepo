package com.wl.testaction.orderManage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Order;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class CheckOrderIdCountServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
             doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     String companyId=ChineseCode.toUTF8(request.getParameter("companyId"));
     String sql="select A.* from (select B.* from orders B where customer=? order by order_id  desc) A where rownum=1";
     String[] params={companyId};
    Order order = null;
     try{
    	 order=Sqlhelper.exeQueryBean(sql, params,Order.class );
     }catch(Exception e){
             e.printStackTrace();
     }
     String count;
     String json;
     if (order==null) {
    	 count="00000000011";
    	 json = "{\"total\":"+count+"}";
     }else {
    	   json = "{\"total\":"+order.getOrderId().substring(order.getOrderId().length()-8,order.getOrderId().length())+"}";
    	
     }
//     String count=order==null?"00000000000000000":order.getOrderId();
  
 	 response.setCharacterEncoding("UTF-8");
 	 response.getWriter().append(json).flush();
 	 System.out.println(json); 
     
     
	}


}
