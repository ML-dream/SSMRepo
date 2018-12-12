package com.wl.testaction;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

import cfmes.util.DealString;

public class EditUserInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");    
	    PrintWriter out = response.getWriter();
	    response.setHeader("Cache-Control","no-cache");
	    out.println("<?xml version='1.0' encoding='utf-8'?>");
	    System.out.println("进入EditUserInfoServlet函数体了已经！！");
	    
	    
	    HttpSession httpSession = ((HttpServletRequest)request).getSession(true);
	    String userSql =null;
	    
	    int pageNow = 1;
	    int pageLenth = 4;
	    int pageWill =1;
	    
	    
	    if (request.getParameter("pageLength")!=null) {
	    	pageLenth = Integer.parseInt(request.getParameter("pageLength"));
	    	httpSession.setAttribute("pageLenth", pageLenth);
		}
	    httpSession.setAttribute("pageLenth", pageLenth);
	    userSql = "select STAFF_CODE,USER_NAME,PASSWORD,AUTHORITY from  (select A.*,ROWNUM row_num " +
	    		"from (select * from sysusers) A where ROWNUM<="+(pageLenth*pageWill)+
	    		" order by STAFF_CODE) where row_num>="+((pageWill-1)*pageLenth+1);
	    System.out.println(userSql);
	    if (request.getParameter("will")!=null) {
			pageNow = Integer.parseInt((httpSession.getAttribute("pageNow")).toString()) ;
			pageLenth = Integer.parseInt((httpSession.getAttribute("pageLenth")).toString());
			int will = Integer.parseInt(request.getParameter("will"));
			
			if (will!=-3) {
				if (will==-1) {
					pageWill = pageNow -1;
				}
				if (will==-2) {
					pageWill =pageNow+1;
				}
				
				if (will==-4) {
					pageWill = 1;
				}
				userSql = "select STAFF_CODE,USER_NAME,PASSWORD,AUTHORITY " +
					"from  (select A.*,ROWNUM row_num from (select * from sysusers) A where ROWNUM<="
				+(pageLenth*pageWill)+" order by STAFF_CODE) where row_num>="+((pageWill-1)*pageLenth+1);
			}else {
				userSql = 
			    	"select STAFF_CODE,USER_NAME,PASSWORD,AUTHORITY from (select A.*,ROWNUM row_num " +
			    	"from (select * from sysusers) A order by STAFF_CODE desc) where row_num<="
			    	+pageLenth+" order by STAFF_CODE ";
			
			}
			System.out.println(pageNow+"  "+will+"  "+pageLenth);
		}
	    
	    

	    
	    //userSql = "select STAFF_CODE,USER_NAME,PASSWORD,AUTHORITY from sysusers";

	    ResultSet userrs =null;
		try{
			userrs = Sqlhelper.executeQuery(userSql, null);
			out.println("<userInfo>");
			while (userrs.next()) {
				out.println("<staffcode>"+userrs.getString(1)+"</staffcode>");
				out.println("<userName>"+userrs.getString(2)+"</userName>");
				out.println("<password>"+userrs.getString(3)+"</password>");
				out.println("<authority>"+userrs.getString(4)+"</authority>");
				System.out.println("userrs.getString(1)=="+userrs.getString(1));
				System.out.println("userrs.getString(2)=="+userrs.getString(2));
				System.out.println("userrs.getString(3)=="+userrs.getString(3));
				System.out.println("userrs.getString(4)=="+userrs.getString(4));
			}
			out.println("</userInfo>");
			out.flush();
			out.close();
			
			httpSession.setAttribute("pageNow", pageWill);
			
		}catch(Exception e){
			System.out.println("增加用户时出错！错误为："+e);
			System.out.println("用户已存在！！！");
		}  finally{
			try {
				if(userrs!=null){
					userrs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













