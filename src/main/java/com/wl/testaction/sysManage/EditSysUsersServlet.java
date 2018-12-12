package com.wl.testaction.sysManage;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;
public class EditSysUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 7647489919592523210L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    
	    String userId = "";
	    
	    HttpSession session = request.getSession();
	    String temp = ((User)(session.getAttribute("user"))).getUserId();
	    userId= StringUtil.isNullOrEmpty(request.getParameter("userId"))?temp:request.getParameter("userId");
	    
	    String toUrl = "sysUserManage/editSysUsers.jsp";
	    
//	    xiem，判断是否是删除用户 参数1表示删除用户
	    String para = "";
	    para = StringUtil.isNullOrEmpty(request.getParameter("para"))?para:request.getParameter("para");
	    if(!para.isEmpty()&&para.equals("1")){
	    	String staffCode = StringUtil.isNullOrEmpty(request.getParameter("staffCode"))?"":request.getParameter("staffCode");
	    	String deleteSql = "begin  " +
	    			"delete from rightassign a where a.staffcode='"+staffCode+"';" +
	    			"delete from sysusers t where t.user_id='"+userId+"';" +
	    			"end;";
	    	System.out.println(deleteSql);
	    	String result= "操作成功";
	    	try {
				Sqlhelper.executeUpdate(deleteSql, null);
				System.out.println("ok  "+deleteSql);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result= "操作失败";
			}
			String json = "{\"result\":\""+result+"\"}";
			System.out.println(json);
			response.setCharacterEncoding("utf-8");
			response.getWriter().append(json).flush();
			return;
	    }else if(!para.isEmpty()&&para.equals("0")){
//	    	0表示当前用户修改密码
	    	toUrl= "sysUserManage/editPassword.jsp";
	    }
	    
		String	priceManHourSql = 
		    	"select USER_ID,STAFF_CODE,USER_NAME,AUTHORITY,AA.authorityName from sysUsers B " +
		    	"left join authority AA on AA.authorityId=B.authority " +
		    	"where b.user_id = '"+userId+"'";
		User result = new User();
		
		try {
			result = Sqlhelper.exeQueryBean(priceManHourSql, null, User.class);
//			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("result", result);
		
		request.getRequestDispatcher(toUrl).forward(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













