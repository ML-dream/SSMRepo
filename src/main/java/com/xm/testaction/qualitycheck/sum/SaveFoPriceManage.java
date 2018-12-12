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

public class SaveFoPriceManage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SaveFoPriceManage() {
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
		String foid = "";
		String foname = "";
		String price = "";
		
		String sqla = "";
		String result= "保存成功!";
		for (int i = 0,j=jarr.size();i<j;i++){
			foid = "";
			foname = "";
			price = "";
			
			Map<String, Object> map1 = JsonConvert.json2Map(jarr.get(i).toString());
			foid = (String) (IsJsonNull.isJsonNull(map1.get("foid"))?foid:map1.get("foid"));
			foname = ChineseCode.toUTF8((String) (IsJsonNull.isJsonNull(map1.get("foname"))?foname:map1.get("foname")));
			price = (String) (IsJsonNull.isJsonNull(map1.get("price"))?price:map1.get("price"));
			
//			sqla = "insert into  costpara t (t.paid,t.paname,t.pavalue) values ('"+foid+"','"+foname+"',"+price+")";
			sqla = "declare " +
			"total number; " +
			"begin " +
			"select count(1) into total from WORKBRANCH where typeid ='"+foid+"';" +
			"if total = 0 then " +
			"insert into  WORKBRANCH t (t.typeid,t.typename,t.price) values ('"+foid+"','"+foname+"',"+price+");"+
			"else" +
			" update WORKBRANCH t set t.typeid = '"+foid+"',t.typename= '"+foname+"',t.price ="+price +
					" where t.typeid ='"+foid+"' ;  " +
			"end if;" +
			"end;";
			
			try {
				System.out.println(sqla);
				Sqlhelper0.executeUpdate(sqla, null);
				System.out.println("ok "+sqla);
			} catch (Exception e) {
				// TODO: handle exception
				result = "保存失败!";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(result).flush();
			}
		}
		
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
