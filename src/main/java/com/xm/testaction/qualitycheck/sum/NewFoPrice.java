package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Sqlhelper0;

public class NewFoPrice extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public NewFoPrice() {
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
		String foid = ChineseCode.toUTF8(request.getParameter("id").trim());
	    String foname = ChineseCode.toUTF8(request.getParameter("name").trim());
	    String price = ChineseCode.toUTF8(request.getParameter("price").trim());
	    String result = "保存成功";
	    String sqla = "";
	    int count = 0;
	    String sqlb ="select count(1)  from WORKBRANCH where typeid ='"+foid+"'";
	    try {
			count = Sqlhelper.exeQueryCountNum(sqlb, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
	    if(count ==0){
		    sqla = "declare " +
				"total number; " +
				"begin " +
				"select count(1) into total from WORKBRANCH where typeid ='"+foid+"';" +
				"if total = 0 then " +
				"insert into  WORKBRANCH t (t.typeid,t.typename,t.price) values ('"+foid+"','"+foname+"',"+price+");"+
				"end if;" +
				"end;";
			
			try {
				System.out.println(sqla);
				Sqlhelper0.executeUpdate(sqla, null);
				System.out.println("ok "+sqla);
			} catch (Exception e) {
				// TODO: handle exception
				result = "保存失败!";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(result).flush();
			}
	}else{
		result="工种编号已存在，请修改编号。";
	}
	
	response.setCharacterEncoding("UTF-8");
	response.getWriter().append(result).flush();
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
