package com.wl.testaction.warehouse.applyexamine;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.common.OrderStatus;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class doExamineApplyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doExamineApplyServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String isPass=request.getParameter("isPass");
		String data=request.getParameter("submitData");
		HashMap<String,String> datamap=(HashMap<String,String>)Test.JSON.Decode(data);
		String opinion=datamap.get("opinion");
		String confirmedAdvice=datamap.get("confirmedAdvice");
//		String orderId=datamap.get("order_id");
		String poSheetid=datamap.get("po_sheetid");
		
		HttpSession session=request.getSession();
		String examineId=((User)session.getAttribute("user")).getStaffCode();
		
		int status=Integer.parseInt(isPass);
		if(opinion.equals("")){
			
			switch (status) {
			case 3:
				opinion += "不通过";
				break;
			case 2:
				opinion+= "通过";
				break;
			default:
				break;
			}
		}
		
		
		
		String examineSql="update po_plan set status='"+isPass+"',opinion='"+confirmedAdvice+opinion+"("+examineId+")',examineid='"+examineId+"' where po_sheetid='"+poSheetid+"'";
		try{
			Sqlhelper.executeUpdate(examineSql, null);
			String json="{\"result\":\"操作成功！\",\"status\":1}";
			out.append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\",\"status\":0}";
			out.append(json).flush();
			e.printStackTrace();
		}
		
		
	}

}
