package com.xm.testaction.qualitycheck.cardhandle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.tools.Sqlhelper0;

public class EditControl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public EditControl() {
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
//		editform表默认从第一道工序开始，后续都是对表的更新，而不是插入
		
			String barcode = request.getParameter("barcode");
			String fo_no = request.getParameter("fo_no");
			String reject_nums =request.getParameter("reject_num");
			int reject_num = 0;
			reject_num= Integer.parseInt( reject_nums.equals("null") ? "0" : reject_nums);
			
			String nullsql = "select fo_no from edit_control where barcode='"+barcode+"'";
			ResultSet nullRst = null;
			try{
				nullRst = Sqlhelper0.executeQuery(nullsql, null);
				if(!nullRst.next()){
					String editsql = "insert into edit_control (barcode,fo_no,reject_num) values ('"+barcode+"','"+fo_no+"',"+reject_num+")";
					try {
			 			Sqlhelper0.executeUpdate(editsql,null);
			 			String result ="success";
			 			response.setCharacterEncoding("UTF-8");
			 			response.getWriter().append(result).flush();
			 		} catch (Exception e) {
			 			String result = "{\"result\":\"操作失败!\"}";
			 			response.setCharacterEncoding("UTF-8");
			 			response.getWriter().append(result).flush();
			 			e.printStackTrace();
			 		}
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(nullRst!= null){
					try {
						nullRst.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
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
