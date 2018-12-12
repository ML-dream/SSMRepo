package com.xm.testaction.qualitycheck.statejudge;

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

public class FixcardTable extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FixcardTable() {
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

		String runnum = "";		//返修单 单号
//		从废品表、审理单表 取数据 
		String data = request.getParameter("data");
//		这里的data不能为空，否则会报错 
//		废品残值，待矫正 	
		String designerin="";		//修正方法
		String designer ="";	//工艺员
		String returndate ="";	//返回日期
		String checkdate = ""; 	// 发生日期
		String dutyer="";		//责任代表
		String checker ="";		//检验员
		
		String fixer="";	//返修者
		String checkheader="";	//检验室主任
		int fo_no =0;
		
		ArrayList datalist =null;
		if(data==null){}else{
		 datalist = (ArrayList)Test.JSON.Decode(data);
		 for(int i=0,l=datalist.size(); i<l; i++){
		    	HashMap datamap = (HashMap)datalist.get(i);
		    	runnum = datamap.get("runnum") != null ? datamap.get("runnum").toString() : "";
		    	designerin = ChineseCode.toUTF8(datamap.get("designerin") != null ? datamap.get("designerin").toString() : "default");
		    	designer = ChineseCode.toUTF8(datamap.get("designer") != null ? datamap.get("designer").toString() : "default");
		    	returndate = ChineseCode.toUTF8(datamap.get("returndate") != null ? datamap.get("returndate").toString() : "default");
		    	checkdate = ChineseCode.toUTF8(datamap.get("checkdate") != null ? datamap.get("checkdate").toString() : "default");
		    	dutyer = ChineseCode.toUTF8(datamap.get("dutyer") != null ? datamap.get("dutyer").toString() : "default");
		    	checker = ChineseCode.toUTF8(datamap.get("checker") != null ? datamap.get("checker").toString() : "default");
		    	fixer = ChineseCode.toUTF8(datamap.get("fixer") != null ? datamap.get("fixer").toString() : "default");
		    	checkheader = ChineseCode.toUTF8(datamap.get("checkheader") != null ? datamap.get("checkheader").toString() : "default");
		    	
		    	String sfo_no = datamap.get("fo_no") != null ? datamap.get("fo_no").toString() : "0";
		    	if(sfo_no.equals("")){
		    		
		    	}else{
		    		fo_no =Integer.parseInt(sfo_no);
		    	}
			} 
		}

		
		
//		String disSql = "insert into disdetail (runnum,recvalue,dutyparter,dutygrouper,coster) values " +
//				"('"+runnum+"','"+recvalue+"','"+dutyparter+"','"+dutygrouper+"','"+coster+"')";
//		9-6
		String disSql="update fixdetail set designerin='"+designerin+"',designer='"+designer+"',returndate=to_date('"+returndate+"','yyyy-mm-dd hh24:mi:ss'),checkdate=to_date('"+checkdate+"','yyyy-mm-dd hh24:mi:ss'),dutyer='"+dutyer+"', " +
				" checker='"+checker+"',fixer='"+fixer+"',checkheader='"+checkheader+"' where runnum='"+runnum+"'";
		try {
			
			Sqlhelper0.executeUpdate(disSql, null);
			System.out.println(disSql);
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
