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

public class SaveParaManage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SaveParaManage() {
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
		String paraid = "";
		String paraname = "";
		String paraval = "";
		
		String sqla = "";
		
		for (int i = 0,j=jarr.size();i<j;i++){
			paraid = "";
			paraname = "";
			paraval = "";
			
			Map<String, Object> map1 = JsonConvert.json2Map(jarr.get(i).toString());
			paraid = (String) (IsJsonNull.isJsonNull(map1.get("paraid"))?paraid:map1.get("paraid"));
			paraname = ChineseCode.toUTF8((String) (IsJsonNull.isJsonNull(map1.get("paraname"))?paraname:map1.get("paraname")));
			paraval = (String) (IsJsonNull.isJsonNull(map1.get("paraval"))?paraval:map1.get("paraval"));
			
			sqla = "insert into  costpara t (t.paid,t.paname,t.pavalue) values ('"+paraid+"','"+paraname+"',"+paraval+")";
			sqla = "declare " +
			"total number; " +
			"begin " +
			"select count(1) into total from costpara where paid ='"+paraid+"';" +
			"if total = 0 then " +
			"insert into  costpara t (t.paid,t.paname,t.pavalue) values ('"+paraid+"','"+paraname+"',"+paraval+");"+
			"else" +
			" update costpara t set t.paid = '"+paraid+"',t.paname= '"+paraname+"',t.pavalue ="+paraval +
					" where t.paid ='"+paraid+"' ;  " +
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
