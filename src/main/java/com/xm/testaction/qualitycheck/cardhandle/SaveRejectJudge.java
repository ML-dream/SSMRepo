package com.xm.testaction.qualitycheck.cardhandle;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/*import javax.jms.Session;*/
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Sqlhelper0;
import com.xm.testaction.qualitycheck.sum.FixToSum;

public class SaveRejectJudge extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SaveRejectJudge() {
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
		String barcode = "";
		int rejectnum = 0;
		String occurplace = "";
		int 	fo_no =0;
		String runnum = "";
		String rejectfo ="";
//		9-9  质检状态 
		int opinionstate = 0;
		double timeloss= 0.0;
		String duty_man ="";
//		9-24
		int rejecttype = 1;
		String cardType = "";
		String opinion = "empty";
		HttpSession session = request.getSession();
	    user = ((User)session.getAttribute("user")).getStaffName();
		
		int bnum = 0 ;	//剩余不合格数
		String reback = ""; //回调结果
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
//		    	user = ChineseCode.toUTF8(datamap.get("user") != null ? datamap.get("user").toString() : "");
		    	barcode = datamap.get("barcode") != null ? datamap.get("barcode").toString() : "" ;
//		    	String fo_nos = datamap.get("rejectfo") != null ? datamap.get("rejectfo").toString() : "0";
//		    	rejectfo = datamap.get("rejectfo") != null ? datamap.get("rejectfo").toString() : "";
		    	String sfo_no =datamap.get("fo_no") != null ? datamap.get("fo_no").toString() : "0";
		    	if(sfo_no.equals("")){}else{
		    		fo_no=Integer.parseInt(sfo_no);
		    	}
		    	runnum = datamap.get("runnum") != null ? datamap.get("runnum").toString() : "";
		    	
		    	rejectnum = Integer.parseInt(datamap.get("reject_num") != null ? datamap.get("reject_num").toString() : "0");
//		    	9-9
		    	opinionstate= Integer.parseInt(datamap.get("opinionstate") != null ? datamap.get("opinionstate").toString() : "0");
//		    	9-19   工时损耗
		    	timeloss = Double.parseDouble(datamap.get("time_loss") != null ? datamap.get("time_loss").toString() : "0");
		    	
		    	rejecttype = Integer.parseInt(datamap.get("rejecttype") != null ? datamap.get("rejecttype").toString() : "1");	//不合格原因
		    	cardType = datamap.get("cardType") != null ? datamap.get("cardType").toString() : "submit";
		    	if(cardType.equals("fix")){
		    		opinion= "toFix";
		    	}
		  }
//		  9-25  新增 rejecttype
		  String savesql = "insert into reject_state (checkrank,dutypart,operatecard,prosource,materialloss,describle,checker,barcode,fo_no,runnum,rejectnum,opinionerstate,timeloss,dutyman,rejecttype,occurplace,opinion) " +
		  		"values ('"+checkrank+"','"+duty_part+"','"+operate_card+"','"+prosource+"',"+materialloss+",'"+imgurl+"','"+user+"','"+barcode+"',"+fo_no+",'"+runnum+"',"+rejectnum+","+opinionstate+","+timeloss+",'"+duty_man+"',"+rejecttype+",'"+occurplace+"','"+opinion+"')";
		  System.out.println(savesql);
		  try {
			Sqlhelper0.executeUpdate(savesql, null);
			System.out.println("ok " + savesql);
			
			bnum = RejectStateHelper.rejectStaticHelper(barcode, fo_no);
				reback += bnum ;
			
//			9-6 审理单处理时间     9-20新增不合格品发生原因
			String datesql ="insert into opiniondate (runnum) values('"+runnum+"')";
			try {
				Sqlhelper0.executeUpdate(datesql, null);
				System.out.println(datesql +"ok");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			}
			
			response.setCharacterEncoding("utf-8");
			response.getWriter().append(reback).flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
//			Sqlhelper0.close();
		}
		
//		9-12  返修产品 统计数据
//		if(opinionstate ==1){		// 为“1” 的状态，则是从返修审理单中提交。
//			FixToSum.fixToSum(runnum, rejectnum, fo_no, barcode);
//		}
		

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
