package com.wl.testaction.warehouse.apply;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.wl.tools.ChineseCode;
import com.wl.tools.JsonConvert;
import com.wl.tools.Sqlhelper0;
import com.xm.testaction.qualitycheck.sum.IsJsonNull;

public class UpdateApplyState extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateApplyState() {
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
//更新申请单状态
		String json = request.getParameter("data");
		
		JSONArray jarr = JSONArray.fromObject(json);
	
		String sheetId = "";
		
		String state = "3";	
		String sqla="";
		for (int i = 0,j=jarr.size();i<j;i++){
			
			 sheetId = "";
			 state = "3";	
			 
			Map<String, Object> map1 = JsonConvert.json2Map(jarr.get(i).toString());
			sheetId = (String) (IsJsonNull.isJsonNull(map1.get("applySheetid"))?sheetId:map1.get("applySheetid"));
			
			state= (String) (IsJsonNull.isJsonNull(map1.get("isPass"))?state:map1.get("isPass"));
			
			sqla ="update apply t set t.ispass='"+state+"' where t.applysheetid='"+sheetId+"'";
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
