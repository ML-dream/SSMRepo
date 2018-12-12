package com.xm.testaction.qualitycheck.sum;

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

public class DeleteRights extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteRights() {
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
	
		String staffcode = "";
		String pageid = "";
		for (int i = 0,j=jarr.size();i<j;i++){
			
			 staffcode = "";
			 pageid = "";
			Map<String, Object> map1 = JsonConvert.json2Map(jarr.get(i).toString());
			staffcode = ChineseCode.toUTF8((String) (IsJsonNull.isJsonNull(map1.get("staffcode"))?staffcode:map1.get("staffcode")));
			pageid =  ChineseCode.toUTF8((String) (IsJsonNull.isJsonNull(map1.get("pageid"))?staffcode:map1.get("pageid")));
			String sqla = "delete from rightassign t where t.staffcode = '"+staffcode+"' and t.pageid ='"+pageid+"'";
			
			
			try {
				System.out.println(sqla);
				Sqlhelper0.executeUpdate(sqla, null);
				System.out.println("ok "+sqla);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(sqla);
				String result = "操作失败!";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(result).flush();
			}
		}
		
		String result = "操作成功!";
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
