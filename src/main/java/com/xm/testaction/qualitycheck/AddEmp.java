package com.xm.testaction.qualitycheck;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper0;

public class AddEmp extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddEmp() {
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
//流程卡，补充未填写加工者、设备
		String json = request.getParameter("data");
	    ArrayList datalist = (ArrayList)Test.JSON.Decode(json);
	    String barcode ="";
	    String fo_no = "";
	    String machineid = "";
	    String workerid = "";
	    
	    for(int i=0,l=datalist.size(); i<l; i++){
	    	HashMap datamap = (HashMap)datalist.get(i);
//	  		待矫正，工时定额未取   
			 barcode = datamap.get("barcode") != null ? datamap.get("barcode").toString() : "";
			 fo_no = datamap.get("fo_no") != null ? datamap.get("fo_no").toString() : "";
			 machineid = datamap.get("machine") != null ? datamap.get("machine").toString() : "";
			 workerid = ChineseCode.toUTF8(datamap.get("workerid") != null ? datamap.get("workerid").toString() : "");
	    }
	    String sqla = "update po_routing2 t set t.workerid = '"+workerid+"' ,t.machineid = '"+machineid+"' where t.barcode = '"+barcode+"' and t.fo_no = '"+fo_no+"'";
	    try {
			System.out.println(sqla);
			Sqlhelper0.executeUpdate(sqla, null);
			System.out.println ("ok  "+sqla);
			String result = "success";
 			response.setCharacterEncoding("UTF-8");
 			response.getWriter().append(result).flush();
		} catch (Exception e) {
			// TODO: handle exception
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
