package com.xm.testaction.qualitycheck;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper0;
import com.wl.tools.StringUtil;

public class ShowEmployeeInfoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowEmployeeInfoServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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
		doPost(request, response);
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
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		int totalCount=0;
		
		String section = "";
	    String workType = "";
	    section = StringUtil.isNullOrEmpty(request.getParameter("section"))?section :request.getParameter("section");
	    workType = StringUtil.isNullOrEmpty(request.getParameter("workName"))?workType :request.getParameter("workName");
	    
		String bodysql="";		
		String totalsql="";
		String json="";
//		待矫正， 加员工类型过滤
		bodysql = "select b.* from (select a.staff_code,a.staff_name,c.deptname,a.gender,a.mobile_phone,rownum rn from employee_info a " +
				"left join dept c on c.deptid = a.section_code " +
				"where leave='N' and section_code like '%"+section+"%' and staff_name like '%"+workType+"%' order by a.staff_code asc  ) b " +
				"where "+min+ "<rn and rn <=" +max;
		ResultSet brs = null;
		List<EmployeeInfoBean> empList = new ArrayList<EmployeeInfoBean>();
		
		try {
			
			brs =Sqlhelper0.executeQuery(bodysql, null);
			while(brs.next()){
			EmployeeInfoBean bean = new EmployeeInfoBean();
			bean.setStaff_code(brs.getString(1));
			bean.setStaff_name(brs.getString(2));
			bean.setDept_id(brs.getString(3));
			bean.setGender(brs.getString(4));
			bean.setMobile_phone(brs.getString(5));
			empList.add(bean);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			Sqlhelper0.close();
			if(brs != null){
		    	try {
				brs.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		
		totalsql= "select count(*) from (select a.staff_code,a.staff_name from " +
			"employee_info a where leave='N' and section_code like '%"+section+"%' and staff_name like '%"+workType+"%') " ;
		ResultSet trs = null;
		try {
			trs=Sqlhelper0.executeQuery(totalsql, null);
			trs.next();
			totalCount = trs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			Sqlhelper0.close();
			if(trs != null){
		    	try {
				trs.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		json = PluSoft.Utils.JSON.Encode(empList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
