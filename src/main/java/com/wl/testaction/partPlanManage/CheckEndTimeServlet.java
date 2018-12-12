package com.wl.testaction.partPlanManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.wl.forms.Order;
import com.wl.forms.PartsPlan;
import com.wl.tools.Sqlhelper;

public class CheckEndTimeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object data=request.getSession().getAttribute("willSeeParts");
	    JSONArray jsonArray = JSONArray.fromObject(data);
		List<Order> orders = JSONArray.toList(jsonArray, Order.class);
	    Order order = orders.get(0);
	    String sql="select B_Time planbtime,E_Time planEtime from order_detail where order_Id=? and product_Id=? and issue_Num=?";
	    String[] params={order.getOrderId(),order.getProductId(),order.getIssueNum()};	
	    PartsPlan partsplan=new PartsPlan();
	    try
	    {  
	     partsplan=Sqlhelper.exeQueryBean(sql, params, PartsPlan.class);
	     String json = PluSoft.Utils.JSON.Encode(partsplan);
		 response.setCharacterEncoding("UTF-8");
		 response.getWriter().append(json).flush();
	    }catch(Exception e)
	      {
	    	e.printStackTrace();
	      }
	    }
	}



