package com.wl.testaction.warehouse.apply;

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
import com.wl.tools.Sqlhelper;

public class doEditApplyHeaderServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doEditApplyHeaderServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		doPost(request,response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String data=request.getParameter("submitData");
		HashMap<String,String> datamap= (HashMap<String, String>) Test.JSON.Decode(data);
		String Sstatus=datamap.get("isPass").toString();
		int status=Integer.parseInt(Sstatus);
		if(status==1){
			String json="{\"result\":\"已审核通过，不能修改！\"}";
			out.append(json).flush();
		}else{
			String applySheetid=datamap.get("applySheetid");
			String applyDate=datamap.get("applyDate");
			String applicantId=datamap.get("applicantId");
			String deptId=datamap.get("deptId");
			String orderId=datamap.get("orderId");
			String warehouseId=datamap.get("warehouse_id");
			
			HttpSession session=request.getSession();
			String changePerson=((User)session.getAttribute("user")).getStaffCode();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String changeTime = df.format(new Date());
//			xiem 	ispass字段的0值未使用，默认值为3  ispass=0
			String applySql="update apply set ispass=3,applydate=to_date('"+applyDate+"','yyyy-mm-dd,hh24:mi:ss'),applicantid='"+applicantId+"',deptid='"+deptId+"',orderid='"+orderId+"'," +
				"changePerson='"+changePerson+"',changeTime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),warehouseid='"+warehouseId+"' where applysheetid='"+applySheetid+"'";
			try{
				Sqlhelper.executeUpdate(applySql, null);
				String json="{\"result\":\"操作成功！\"}";
				out.append(json).flush();
			}catch(Exception e){
				String json="{\"result\":\"操作失败！\"}";
				out.append(json).flush();
				e.printStackTrace();
			}
		}
	
	}

}
