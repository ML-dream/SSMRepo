package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

import net.sf.json.JSONArray;

import com.wl.tools.ChineseCode;
import com.wl.tools.JsonConvert;
import com.wl.tools.Sqlhelper0;
import com.wl.tools.StringUtil;

import PluSoft.Utils.JSON;

public class SaveRewards extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SaveRewards() {
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

		String json = request.getParameter("data");
		
		JSONArray jarr = JSONArray.fromObject(json);
	
//		System.out.println(jarr.get(0));
		String barcode = "";
		String fo_no = "";
		String workerid = "";
		String basetime = "";
		String rewardstime = "";
		String remark = "";
		
		String sqla = "";
		String temp1 = "";
		for (int i = 0,j=jarr.size();i<j;i++){
			
			Map<String, Object> map1 = JsonConvert.json2Map(jarr.get(i).toString());
			barcode = (String) (IsJsonNull.isJsonNull(map1.get("barcode"))?barcode:map1.get("barcode"));
			fo_no = (String) (IsJsonNull.isJsonNull(map1.get("foNo"))?fo_no:map1.get("foNo"));
			workerid =  (String) (IsJsonNull.isJsonNull(map1.get("workerid"))?workerid:map1.get("workerid"));
			basetime = (String) (IsJsonNull.isJsonNull(map1.get("basetime"))?basetime:map1.get("basetime"));
			
			rewardstime = (String) (IsJsonNull.isJsonNull(map1.get("rewardstime"))?barcode:map1.get("rewardstime"));
			remark = ChineseCode.toUTF8((String) (IsJsonNull.isJsonNull(map1.get("remark"))?remark:map1.get("remark")));
			
			sqla = "insert into rewardstime  (barcode, fo_no, workerid, basetime, rewardstime, remark) " +
					"values ('"+barcode+"','"+fo_no+"','"+workerid+"','"+basetime+"','"+rewardstime+"','"+remark+"')";
			
			sqla = "declare " +
					"total number; " +
					"begin " +
					"select count(1) into total from rewardstime where barcode ='"+barcode+"' and fo_no = "+fo_no+";" +
					"if total = 0 then " +
					 "insert into rewardstime  (barcode, fo_no, workerid, basetime, rewardstime, remark) " +
						"values ('"+barcode+"','"+fo_no+"','"+workerid+"','"+basetime+"','"+rewardstime+"','"+remark+"');"+
					"else" +
					" update rewardstime t set t.rewardstime = "+rewardstime+" ,t.remark ='"+remark+"' where t.barcode ='"+barcode+"' and t.fo_no = "+fo_no+";  " +
					"end if;" +
					"end;";
			
			try {
				System.out.println(sqla);
				Sqlhelper0.executeUpdate(sqla, null);
				System.out.println("ok "+sqla);
			} catch (Exception e) {
				// TODO: handle exception
				String result = "保存失败!";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(result).flush();
			}
		}
		
		String result = "保存成功!";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(result).flush();
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
