package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper0;

public class EmpSumClose extends HttpServlet {
//   按人员统计页面关闭时，删除临时表数据 
	/**
	 * Constructor of the object.
	 */
	public EmpSumClose() {
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
			
		
		String sql = "truncate table empsumtem";
		String sqlb = "truncate table empsumtem2";
		String sqla = "select count(*) from empsumtem a " ;
		String sqlba =  "select count(*) from empsumtem2 b " ;
		try {
			Sqlhelper0.executeUpdate(sql, null);
			System.out.println("ok  "+sql);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			ResultSet rsa = null;
			try {
				
				rsa= Sqlhelper0.executeQuery(sqla, null);
				int con = 0;
				rsa.next();
				con =rsa.getInt(1);
				if(con != 0){
					try {
						Sqlhelper0.executeUpdate(sql, null);
						System.out.println("ok  "+sql);
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}finally{
				if(rsa != null){
					try {
						rsa.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		try {
			Sqlhelper0.executeUpdate(sqlb, null);
			System.out.println("ok  "+sqlb);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			ResultSet rsa = null;
			try {
				
				rsa= Sqlhelper0.executeQuery(sqlba, null);
				int con = 0;
				rsa.next();
				con =rsa.getInt(1);
				if(con != 0){
					try {
						Sqlhelper0.executeUpdate(sqlb, null);
						System.out.println("ok  "+sqlb);
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}finally{
				if(rsa != null){
					try {
						rsa.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().append("ok").flush();
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
