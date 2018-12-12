package com.xm.testaction.qualitycheck.cardhandle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Test.JSON;

import com.wl.tools.JsonConvert;
import com.wl.tools.Sqlhelper0;
import com.xm.testaction.qualitycheck.PoFlowBean;
import com.xm.testaction.qualitycheck.StateJudge;

public class FindNbarcode extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FindNbarcode() {
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
//		搜索attach_info表中最新的一条记录，查找新建流程卡的条码号
		String fbarcode = request.getParameter("fbarcode");
		String newestsql="select barcode,fbarcode,attach_no,info_index,status from attach_info" +
				" where fbarcode='"+fbarcode+"' order by info_index desc";
		System.out.println("newestsql="+newestsql);
		ResultSet attachRst=null;
		try{	
			attachRst=Sqlhelper0.executeQuery(newestsql, null);
			attachRst.next();
			String grade = StateJudge.stateJudge(attachRst.getInt(5));
			
			AttachCardBean attachbean = new AttachCardBean();
			attachbean.setBarcode(attachRst.getString(1));
			attachbean.setFbarcode(attachRst.getString(2));
			attachbean.setAttach_no(attachRst.getInt(3));
			attachbean.setStatus(grade);
			
			
//			String json = JSON.Encode(JsonConvert.beanToMap(attachbean));
			String json = PluSoft.Utils.JSON.Encode(attachbean);
			System.out.println(json);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if(attachRst!=null){
					attachRst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
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
