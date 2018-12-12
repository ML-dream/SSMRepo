package com.xm.testaction.qualitycheck.cardhandle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper0;

public class SubmitSave extends HttpServlet {
// 保存各审核意见。
	/**
	 * Constructor of the object.
	 */
	public SubmitSave() {
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
// 有的项目可能为空。		
		String para = request.getParameter("para");
		String paratext = para + "in";
		String opiniontext = ChineseCode.toUTF8(request.getParameter("opiniontext"));
		String opinion = ChineseCode.toUTF8(request.getParameter("opinion"));
//		String barcode = request.getParameter("fbarcode");
//		int fo_no =Integer.parseInt(request.getParameter("fo_no"));
		String runnum = request.getParameter("runnum");
		String result = request.getParameter("result");		//主管审理意见 
		HttpSession session = request.getSession();
		String user= ((User)session.getAttribute("user")).getStaffName();
//		9-2
		String savesql ="";
//		质检复检后，待处理审理单清空   
//		9-6
		if(para.equals("rechecker")){
			savesql = "update reject_state set " +para+"='"+user+"',"+paratext+"='"+opiniontext+"',opinionerstate=1 where runnum='"+runnum+"' ";
		}else{
			savesql = "update reject_state set " +para+"='"+user+"',"+paratext+"='"+opiniontext+"' where runnum='"+runnum+"' ";
		}
		System.out.println(savesql);
		try{
			Sqlhelper0.executeUpdate(savesql, null);
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
//			Sqlhelper0.close();
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
