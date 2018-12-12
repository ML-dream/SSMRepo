package com.wl.testaction.sysManage;


import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.wl.tools.StringUtil;

import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class ShowSysUsersServlet extends HttpServlet {

	private static final long serialVersionUID = 4248531700551337155L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=20;
	    int totalCount = 0;
	    request.setCharacterEncoding("utf-8");
//	    String orderStr = "USER_ID";
//	    String username = "N";
//	    username= StringUtil.isNullOrEmpty(request.getParameter("username"))?username :request.getParameter("username");
//	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    String username = request.getParameter("username");
	   
	
	  
	 /* if (username=="1"){
		  response.sendRedirect("showSysUsers.jsp?flag=cuowu");
	  }*/
//	    String username="DWG";
	    String totalCountSql="";
	    String sql="";
	    
    if (username.equals("")){
		 
		   
	    totalCountSql = "select count(*) from sysUsers";
//	    totalCountSql = "select count(*) from sysUsers where USERNAME like '%"+username+"%'";
	     sql = "select USER_ID userId,STAFF_CODE staffCode,USER_NAME staffName,AUTHORITY,AA.authorityName " +
		    	"from (select A.*,ROWNUM row_num from (select EM.* from sysUsers EM  order by USER_ID asc) A where ROWNUM<="+(countPerPage*pageNo) +" order by USER_ID asc)" +
		    			" B  "+"left join authority AA on AA.authorityId=B.authority "+
		    	"where row_num>="+((pageNo-1)*countPerPage+1)+" order by USER_ID asc" ;
	    
	 }else{ 
		    
	        totalCountSql = "select count(*) from sysUsers where USER_ID='"+username+"'";
//		    totalCountSql = "select count(*) from sysUsers where username like '%"+username+"%'";
	         sql= "select USER_ID userId,STAFF_CODE staffCode,USER_NAME staffName,AUTHORITY,AA.authorityName " +
	    	    	"from (select A.*,ROWNUM row_num from (select EM.* from sysUsers EM where USER_ID like '%"+username+"%' order by USER_ID asc) A where ROWNUM<="+(countPerPage*pageNo) +" order by USER_ID) B " +
	    	    	"left join authority AA on AA.authorityId=B.authority "+
	    	    	"where row_num>="+((pageNo-1)*countPerPage+1) ;
	 }
		      
	        try {
				totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		   
		
	    List<User> resultList = new ArrayList<User>();
	    try {
	    	
			resultList = Sqlhelper.exeQueryList(sql, null, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   
	       String json = PluSoft.Utils.JSON.Encode(resultList);
	 	   json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
	        response.setCharacterEncoding("UTF-8");
		    response.getWriter().append(json).flush();
		    System.out.println(json);//这句话根本没什么用 只是用来调试的像控制台打印东西的
		    
		
   
		
		
	    
	
//	    System.out.println("OrderSql=="+OrderSql);
//	    
//	    ResultSet priceManHourRs =null;
//		try{
//			priceManHourRs = Sqlhelper.executeQuery(OrderSql, null);
//			List<User> resultList = new ArrayList<User>();
//			try {
//				while (priceManHourRs.next()) {
//					User result = new User();
//					result.setUserId(priceManHourRs.getString(1));
//					result.setStaffCode(priceManHourRs.getString(2));
//					result.setStaffName(priceManHourRs.getString(3));
//					result.setAuthority(priceManHourRs.getString(4));
//					result.setAuthorityName(priceManHourRs.getString(5));
//					
//					resultList.add(result);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String json = PluSoft.Utils.JSON.Encode(resultList);
//			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append(json).flush();
//			System.out.println(json);
//		}catch(Exception e){
//		}  finally{
//			try {
//				if(priceManHourRs!=null){
//					priceManHourRs.close();
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













