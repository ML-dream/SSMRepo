package com.wl.testaction.warehouse.payment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.EditPaystatistics;
import com.wl.tools.Sqlhelper;

public class seeEditDataMaintenanceServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public seeEditDataMaintenanceServlet() {
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
		int pageNow=0;
		int pageSize=0;
		int totalCount=0;
		pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		pageSize=Integer.parseInt(request.getParameter("pageSize"));
		
		String totalCountSql="select count(*) from editpaystatistics";
		try {
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		String editSql="select B.companyid,maintenancedate,editendpayment,initialendpayment,editperson,edittime,reason,C.companyname,D.staff_name editPersonName from " +
				"(select A.*,rownum rn from(select EM.* from editpaystatistics EM order by companyid asc,maintenancedate desc) A where rownum<="+pageSize*pageNow+" " +
						"order by companyid asc,maintenancedate desc) B " +
						"left join customer C on C.companyid=B.companyid " +
						"left join employee_info D on D.staff_code=B.editperson " +
						"where rn>="+(pageSize*(pageNow-1)+1)+"";
		List<EditPaystatistics> resultList =new ArrayList<EditPaystatistics>();
		try {
			resultList=Sqlhelper.exeQueryList(editSql, null, EditPaystatistics.class);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		System.out.println(json);
		response.getWriter().append(json).flush();
	}

}
