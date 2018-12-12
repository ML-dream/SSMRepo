package com.xm.testaction.qualitycheck;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Test.JSON;

import com.wl.tools.ChineseCode;
import com.wl.tools.JsonConvert;

public class SaveSession extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SaveSession() {
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
//	作废	
		String json = request.getParameter("data");
	    ArrayList datalist = (ArrayList)Test.JSON.Decode(json);
//		ArrayList datalist = (ArrayList)PluSoft.Utils.JSON.Decode(json);
	    String barcode = "";
	    String fo_no = "";
	    String fo_opname ="";
	    String num ="";
	    String workername ="";
	    String accept_num ="";
	    String reject_num ="";
	    String confirm_num ="";
	    String remark ="";
	    String checkdate="";
	    String checker ="";
	    
	    for(int i=0,l=datalist.size(); i<l; i++){
	    	HashMap datamap = (HashMap)datalist.get(i);
	  		  
			  barcode = datamap.get("barcode") != null ? datamap.get("barcode").toString() : "";
			  fo_no =  ChineseCode.toUTF8(datamap.get("fo_no") != null ? datamap.get("fo_no").toString() : "");
			  fo_opname = ChineseCode.toUTF8(datamap.get("fo_opname") != null ? datamap.get("fo_opname").toString() : "");
		      num = datamap.get("num") != null ? datamap.get("num").toString() : "";
		      workername =ChineseCode.toUTF8(datamap.get("workername") != null ? datamap.get("workername").toString() : "");
		      checkdate =ChineseCode.toUTF8(datamap.get("checkdate") != null ? datamap.get("checkdate").toString() : "");
		      
		      accept_num = datamap.get("accept_num") != null ? datamap.get("accept_num").toString() : "";
			  reject_num = datamap.get("reject_num") != null ? datamap.get("reject_num").toString() : "";
			  confirm_num = datamap.get("confirm_num") != null ? datamap.get("confirm_num").toString() : "";
			   remark = ChineseCode.toUTF8(datamap.get("remark") != null ? datamap.get("remark").toString() : "");
				fo_no = datamap.get("fo_no") != null ? datamap.get("fo_no").toString() : "";
				checker =ChineseCode.toUTF8(datamap.get("checker") != null ? datamap.get("checker").toString() : "");
			       
	    }
		 PoFlowBean sessionBean = new PoFlowBean();
		 sessionBean.setBarcode(barcode);
		 
		 sessionBean.setFo_no(fo_no);
		 sessionBean.setFo_opname(fo_opname);
		 sessionBean.setNum(num);
		 sessionBean.setWorkername(workername);
		sessionBean.setCheckdate(checkdate);
		 sessionBean.setAccept_num(accept_num);
		 sessionBean.setReject_num(reject_num);
		 sessionBean.setChecker(checker);
		 
		 
//		 为什么要把这个数据放到session中？？？
		 HttpSession session = request.getSession();
		 session.setAttribute("editPo", sessionBean);
		 
		 String result = "success";
		 response.setCharacterEncoding("UTF-8");
		 response.getWriter().append(result).flush();
//		 String createPerson = ((User)session.getAttribute("user")).getStaffCode();
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
