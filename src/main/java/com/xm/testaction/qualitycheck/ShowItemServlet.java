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

public class ShowItemServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowItemServlet() {
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
		
		String bodysql="";		
		String json="";
//		待矫正， 加员工类型过滤
		bodysql = "select a.item_id,a.item_name from item a where a.item_typeid ='C'";
		ResultSet brs = null;
		List<ShowItemInfoBean> itemList = new ArrayList<ShowItemInfoBean>();
		
		try {
			
			brs =Sqlhelper0.executeQuery(bodysql, null);
			while(brs.next()){
			ShowItemInfoBean bean = new ShowItemInfoBean();
			bean.setId(brs.getString(1));
			bean.setText(brs.getString(2));
			itemList.add(bean);
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
		
		json = PluSoft.Utils.JSON.Encode(itemList);
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
