package com.xm.testaction.qualitycheck.statejudge;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Test.JSON;

import com.wl.tools.JsonConvert;
import com.wl.tools.Sqlhelper0;

public class ToJudgeText extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ToJudgeText() {
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
		String barcode = request.getParameter("barcode");
		int fo_no = Integer.parseInt(request.getParameter("fo_no"));
		
		String sql ="select checker,describle,securityman,reason,designer,usability,markter,marketing,superviser,supervise,opinioner,opinion,opinionText," +
				"repairer,repair from reject_state where barcode='"+barcode+"' and fo_no="+fo_no;
		System.out.println(sql);
		
		String json= "";
		ResultSet Rs = null;
		try {
			Rs=Sqlhelper0.executeQuery(sql, null);
			Rs.next();
			ToJudgeTextBean bean = new ToJudgeTextBean();
			bean.setChecker(Rs.getString(1));
			bean.setImg(Rs.getString(2));
			bean.setSecurity(Rs.getString(3));
			bean.setSecurityin(Rs.getString(4));
			bean.setDesigner(Rs.getString(5));
			bean.setDesignerin(Rs.getString(6));
			bean.setMarketer(Rs.getString(7));
			bean.setMarketerin(Rs.getString(8));
			bean.setSuperviser(Rs.getString(9));
			bean.setSuperviserin(Rs.getString(10));
			bean.setOpinioner(Rs.getString(11));
			bean.setOpinion(Rs.getString(12));
			bean.setOpinionText(Rs.getString(13));
			bean.setRechecker(Rs.getString(14));
			bean.setRecheckerin(Rs.getString(15));
			
			json=JSON.Encode(JsonConvert.beanToMap(bean));
			System.out.println(json);
			response.setCharacterEncoding("utf-8");
			response.getWriter().append(json).flush();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(Rs!=null){
				try {
					Rs.close();
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
