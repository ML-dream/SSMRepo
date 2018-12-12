package com.xm.testaction.qualitycheck.statejudge;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Sqlhelper0;
import com.xm.testaction.qualitycheck.ToBarcode;
import com.xm.testaction.qualitycheck.ToSum;

public class DiscardTable extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DiscardTable() {
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
//		保存废品单
		String runnum = "";	//报废单号
//		从废品表、审理单表 取数据    这个是报废单  保存 
		String data = request.getParameter("data");
//		这里的data不能为空，否则会报错 
//		废品残值，待矫正 	
		String recvalue="0";
		String dutyparter ="";	//责任部门负责人
		String dutygrouper="";	//责任班组长
		String coster="";	//财务
		String superviser = "";
		String checker = "";
		String checkdate = "";
		int fo_no =0;
		String recinput = "";
		
		String reject_num = "";
		
		ArrayList datalist =null;
		if(data==null){}else{
		 datalist = (ArrayList)Test.JSON.Decode(data);
		 for(int i=0,l=datalist.size(); i<l; i++){
		    	HashMap datamap = (HashMap)datalist.get(i);
		    	runnum = datamap.get("runnum") != null ? datamap.get("runnum").toString() : "";
		    	recvalue = ChineseCode.toUTF8(datamap.get("recvalue") != null ? datamap.get("recvalue").toString() : recvalue);
		    	dutyparter = ChineseCode.toUTF8(datamap.get("dutyparter") != null ? datamap.get("dutyparter").toString() : dutyparter);
		    	dutygrouper = ChineseCode.toUTF8(datamap.get("dutygrouper") != null ? datamap.get("dutygrouper").toString() : dutygrouper);
		    	coster = ChineseCode.toUTF8(datamap.get("coster") != null ? datamap.get("coster").toString() : coster);
		    	superviser = ChineseCode.toUTF8(datamap.get("superviser") != null ? datamap.get("superviser").toString() : superviser);
		    	checker = ChineseCode.toUTF8(datamap.get("checker") != null ? datamap.get("checker").toString() : checker);
		    	checkdate = ChineseCode.toUTF8(datamap.get("checkdate") != null ? datamap.get("checkdate").toString() : "default");
		    	recinput = ChineseCode.toUTF8(datamap.get("recinput") != null ? datamap.get("recinput").toString() : "");
		    	
		    	String sfo_no = datamap.get("fo_no") != null ? datamap.get("fo_no").toString() : "0";
		    	if(sfo_no.equals("")){
		    		
		    	}else{
		    		fo_no =Integer.parseInt(sfo_no);
		    	}
		    	
		    	reject_num = datamap.get("reject_num") != null ? datamap.get("reject_num").toString() : "0";
			} 
		}

//		9-6
		String disSql="update disdetail set recvalue='"+recvalue+"',dutyparter='"+dutyparter+"',dutygrouper='"+dutygrouper+"'," +
				"coster='"+coster+"', superviser='"+superviser+"',checker='"+checker +"',checkdate= to_date('"+checkdate+"','yyyy-MM-ddHH:mi:ss'),recinput= '"+recinput+"' "+
				"where runnum='"+runnum+"'";
		try {
			System.out.println(disSql);
			Sqlhelper0.executeUpdate(disSql, null);
			System.out.println("ok "+disSql);
		} catch (Exception e) {
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
