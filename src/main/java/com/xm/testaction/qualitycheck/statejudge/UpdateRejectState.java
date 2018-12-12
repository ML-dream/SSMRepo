package com.xm.testaction.qualitycheck.statejudge;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper0;

public class UpdateRejectState extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateRejectState() {
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

		String data = request.getParameter("data");
		ArrayList datalist = (ArrayList)Test.JSON.Decode(data);
		String checkrank ="";
		String duty_part ="";
		String operate_card	="";
		String prosource ="";
		double materialloss = 0;
		String imgurl ="";
		String user = "";
		int rejectnum = 0;
		String occurplace = "";
		String runnum = "";
		double timeloss= 0.0;
		String duty_man ="";
		HttpSession session = request.getSession();
	    user = ((User)session.getAttribute("user")).getStaffName();
		String rejecttype= "";
		
		  for(int i=0,l=datalist.size(); i<l; i++){
		    	HashMap datamap = (HashMap)datalist.get(i);
//		  		待矫正，工时定额未取   
		    	checkrank = datamap.get("checkrank") != null ? datamap.get("checkrank").toString() : "";
		    	duty_part = ChineseCode.toUTF8(datamap.get("duty_part") != null ? datamap.get("duty_part").toString() : "");
		    	duty_man = ChineseCode.toUTF8(datamap.get("duty_man") != null ? datamap.get("duty_man").toString() : "");	//9-20
		    	
		    	operate_card =ChineseCode.toUTF8(datamap.get("operate_card") != null ? datamap.get("operate_card").toString() : "");
				
		    	prosource = ChineseCode.toUTF8(datamap.get("prosource") != null ? datamap.get("prosource").toString() : "");
		    	String material = (datamap.get("materialloss") != null ? datamap.get("materialloss").toString() : "0");
		    	materialloss = Double.parseDouble(material);
		    	imgurl = datamap.get("imgurl") != null ? datamap.get("imgurl").toString() : "" ;
		    	runnum = datamap.get("runnum") != null ? datamap.get("runnum").toString() : "";
		    	
		    	rejectnum = Integer.parseInt(datamap.get("reject_num") != null ? datamap.get("reject_num").toString() : "0");
//		    	9-19   工时损耗
		    	timeloss = Double.parseDouble(datamap.get("time_loss") != null ? datamap.get("time_loss").toString() : "0");
		    	rejecttype = datamap.get("rejecttype") != null ? datamap.get("rejecttype").toString() : "1";
		    	occurplace = datamap.get("rejectad") != null ? datamap.get("rejectad").toString() : "";
		  }
//		  9-25  新增 rejecttype
		  String savesql = "update reject_state t set t.checkrank= '"+checkrank+"',t.occurplace='"+occurplace+"',t.operatecard='"+operate_card+"',t.prosource='"+prosource+"',t.dutypart='"+duty_part+"',t.dutyman='"+duty_man+"'," +
		  		"t.timeloss= "+timeloss+",t.materialloss= "+materialloss+",t.checker='"+user+"',t.rejectnum="+rejectnum+",t.rejecttype = '"+rejecttype+"' where t.runnum = '"+runnum+"'";
		  System.out.println(savesql);
		  try {
			Sqlhelper0.executeUpdate(savesql, null);
			System.out.println("ok" + savesql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
