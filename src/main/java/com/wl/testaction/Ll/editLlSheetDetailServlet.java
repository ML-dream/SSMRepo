package com.wl.testaction.Ll;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.LlSheetDetail;
import com.wl.tools.Sqlhelper;

public class editLlSheetDetailServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String llSheetid=request.getParameter("llSheetid");
		String itemId=request.getParameter("itemId");
	//	String issueNum=request.getParameter("issueNum");
		String warehouse_id=request.getParameter("warehouse_id");
		String sql="select A.ll_sheetid,A.item_id,A.item_name,order_id,A.issue_num,A.item_type,A.spec,A.unit,ll_num,A.unitprice," +
				"A.price,A.stock_id,A.memo,A.productid,A.productname,B.stock_num from lingliao_detl A " +
				"left join stock B on B.item_id=A.item_id and B.item_type=A.item_type " +
				"where ll_sheetid=? and A.item_id=?";
		String[] params={llSheetid,itemId}; 
		LlSheetDetail lldetail=new LlSheetDetail();
		try{
			lldetail=Sqlhelper.exeQueryBean(sql, params, LlSheetDetail.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(lldetail);
		request.setAttribute("lldetail", lldetail);
		request.setAttribute("warehouse_id", warehouse_id);
		request.getRequestDispatcher("/Lingliao/editLlSheetDetail.jsp").forward(request, response);
	}

	 
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		doGet(request,response);
	}

}
