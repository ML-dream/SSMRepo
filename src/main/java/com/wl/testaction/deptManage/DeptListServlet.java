package com.wl.testaction.deptManage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.StringUtil;
import com.wl.forms.Customer;
import com.wl.forms.Dept;
import com.wl.tools.Sqlhelper;

public class DeptListServlet extends HttpServlet {
	private static final long serialVersionUID = 4071476502069730673L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

	    String deptId = StringUtil.isNullOrEmpty(request.getParameter("deptId"))?"zjls":(request.getParameter("deptId"));
	    System.out.println(deptId);

	    String CustomerSql= "select distinct deptId, deptName,FDeptId,deptLevel,iskey,headstaffId "+
				"from dept A "+
				"where isAlive='1' " +
				"start with deptId=? "+
				"connect by prior A.deptId=A.FDeptId " +
				"order by deptId";
	    String[] params = {deptId};
	    List<Dept> customerList = new ArrayList<Dept>();
	    try {
	    	customerList = Sqlhelper.exeQueryList(CustomerSql, params, Dept.class);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String json = PluSoft.Utils.JSON.Encode(customerList);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);

//	    System.out.println("CustomerSql=="+CustomerSql);
//	    
//	    ResultSet CustomerRs =null;
//		try{
//			CustomerRs = Sqlhelper.executeQuery(CustomerSql, null);
//			List<Dept> customerList = new ArrayList<Dept>();
//			try {
//				while (CustomerRs.next()) {
//					Dept result = new Dept();
//					result.setDeptId(CustomerRs.getString(1));
//					result.setDeptName(CustomerRs.getString(2));
//					result.setFDeptId(CustomerRs.getString(3));
//					result.setDeptLevel(CustomerRs.getInt(4));
//					result.setIsKey(CustomerRs.getString(5));
//					result.setHeadStaffId(CustomerRs.getString(6));
//					customerList.add(result);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String json = PluSoft.Utils.JSON.Encode(customerList);
////			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append(json).flush();
//			System.out.println(json);
//		}catch(Exception e){
//		}  finally{
//			try {
//				if(CustomerRs!=null){
//					CustomerRs.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













