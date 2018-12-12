package com.xm.testaction.qualitycheck.sum;

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

public class LoadRewardsForm extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadRewardsForm() {
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
		String fo_no = request.getParameter("fo_no");
		
		String sqla = "select t.runnum,t.dutyman,t.rejectnum,t.opinion,a.staff_name,c.staff_name fixer,t.timeloss from reject_state t " +
				"left join employee_info a on a.staff_code = t.dutyman " +
				"left join fixdetail b on b.staterunnum = t.runnum " +
				" left join employee_info c on c.staff_code = b.fixer "+
				"where t.barcode = '"+barcode+"' and t.fo_no ="+fo_no;
		System.out.println(sqla);
		ResultSet rsa = null;
		RewardsFormBean bean = new RewardsFormBean();
		String rejtype = "";
		try {
			rsa = Sqlhelper0.executeQuery(sqla, null);
			
			rsa.next();
			bean.setBarcode(barcode);
			bean.setFo_no(fo_no);
			bean.setRun1(rsa.getString(1));
			bean.setDuty1(rsa.getString(5));
			bean.setReject1(rsa.getString(3));
			rejtype = RejTypeCh.rejTypeCh(rsa.getString(4));
			bean.setRejtype1(rejtype);
			bean.setFixer1(rsa.getString(6));
			bean.setTimeLoss1(rsa.getString(7));
			try {
				rsa.next();
				bean.setRun2(rsa.getString(1));
				bean.setDuty2(rsa.getString(5));
				bean.setReject2(rsa.getString(3));
				rejtype = RejTypeCh.rejTypeCh(rsa.getString(4));
				bean.setRejtype2(rejtype);
				bean.setFixer2(rsa.getString(6));
				bean.setTimeLoss2(rsa.getString(7));
				try {
					rsa.next();
					bean.setRun3(rsa.getString(1));
					bean.setDuty3(rsa.getString(5));
					bean.setReject3(rsa.getString(3));
					rejtype = RejTypeCh.rejTypeCh(rsa.getString(4));
					bean.setRejtype3(rejtype);
					bean.setFixer3(rsa.getString(6));
					bean.setTimeLoss3(rsa.getString(7));
				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(rsa != null){try {
				rsa.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
		String json = "";
		Object t = null;
		try {
			t = JsonConvert.beanToMap(bean);
		} catch (Exception e) {
			// TODO: handle exception
		}
		json=JSON.Encode(t);
		System.out.println(json);
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
