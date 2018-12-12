package com.xm.testaction.qualitycheck.cardhandle;

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
import com.xm.testaction.qualitycheck.PoFlowBean;

public class LoadRejectForm extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadRejectForm() {
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

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			String barcode = request.getParameter("barcode");
			int fo_no = Integer.parseInt(request.getParameter("fo_no"));
			
			String sql = "select fo_no,fo_opname,fnum,workername,checkdate,accept_num,reject_num,checker from" +
					" po_routing2 where barcode='"+barcode+"' and fo_no="+fo_no;
			ResultSet rejectRst = null;
			try {
				rejectRst = Sqlhelper0.executeQuery(sql, null);
				rejectRst.next();
				PoFlowBean rejectBean = new PoFlowBean();
				
				rejectBean.setFo_no(rejectRst.getString(1));
				rejectBean.setFo_opname(rejectRst.getString(2));
				rejectBean.setNum(rejectRst.getString(3));
				rejectBean.setWorkername(rejectRst.getString(4));
				
				rejectBean.setCheckdate(rejectRst.getString(5));
				rejectBean.setAccept_num(rejectRst.getString(6));
				rejectBean.setReject_num(rejectRst.getString(7));
				rejectBean.setChecker(rejectRst.getString(8));
				
				String json=JSON.Encode(JsonConvert.beanToMap(rejectBean));
				System.out.println(json);
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				try {
					rejectRst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
