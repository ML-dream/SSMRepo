package com.wl.testaction.orderManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ItemType;
import com.wl.forms.Order;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class GetIssueNumServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
           doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
	    String sql= "select distinct rownum id ,issue_Num issueNum text from order_detail where product_id=? order by issueNum";
	    String[] params={productId};
	    List<Order> resultList = new ArrayList<Order>();
	    
	    try {
			resultList = Sqlhelper.exeQueryList(sql,params,Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    String json = PluSoft.Utils.JSON.Encode(resultList);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
		
	}

}
