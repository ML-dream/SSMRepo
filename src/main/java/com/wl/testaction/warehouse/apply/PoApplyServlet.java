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

public class PoApplyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PoApplyServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String data=request.getParameter("submitData");
		HashMap<String,String> datamap=(HashMap<String,String>) Test.JSON.Decode(data);
		String applyDate=datamap.get("applyDate");
		String applySheetid=datamap.get("applySheetid");
		String deptId=datamap.get("deptId");
		String applicantId=datamap.get("applicantId");
		String orderId=datamap.get("order_id");
		String warehouseId=datamap.get("warehouse_id");
		String id=datamap.get("id");
		String seq=datamap.get("seq");
		String isPass=datamap.get("isPass");
		
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		 String createTime = df.format(new Date());
		 String changeTime = df.format(new Date());
		    
		 HttpSession session = request.getSession();
		 String createPerson = ((User)session.getAttribute("user")).getStaffCode();
		 String changePerson = ((User)session.getAttribute("user")).getStaffCode();
		
		 String applySql="insert into apply (APPLYSHEETID,APPLYDATE,APPLICANTID,DEPTID,ISPASS,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME,ORDERID,WAREHOUSEID) " +
			"values('"+applySheetid+"',to_date('"+applyDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+applicantId+"','"+deptId+"','"+isPass+"','"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss')," +
					"'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+orderId+"','"+warehouseId+"')";
		
		 String sheetSql="insert into applysheetid values("+seq+",'"+id+"','"+applySheetid+"')";
			try{
			Sqlhelper.executeUpdate(applySql, null);
			Sqlhelper.executeUpdate(sheetSql, null);
			String json="{\"result\":\"操作成功！\",\"status\":1,\"count\":1}";
			System.out.println(json);
			out.append(json).flush();
			}catch(Exception e){
				String json="{\"result\":\"操作失败！\",\"status\":0}";
				System.out.println(json);
				out.append(json).flush();
				e.printStackTrace();
			}
			
			
	}

}
