package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.JsonConvert;
import com.wl.tools.Sqlhelper0;

import net.sf.json.JSONArray;

public class SaveStuffManage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SaveStuffManage() {
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
		String stuffid = "";
		String stuffname = "";
		String price = "";
		String density = "";
		
		String sqla = "";
		
		for (int i = 0,j=jarr.size();i<j;i++){
			stuffid = "";
			stuffname = "";
			price = "";
			density = "0";
			
			Map<String, Object> map1 = JsonConvert.json2Map(jarr.get(i).toString());
			stuffid =ChineseCode.toUTF8( (String) (IsJsonNull.isJsonNull(map1.get("stuffid"))?stuffid:map1.get("stuffid")));
			stuffname = ChineseCode.toUTF8((String) (IsJsonNull.isJsonNull(map1.get("stuffname"))?stuffid:map1.get("stuffname")));
			price = (String) (IsJsonNull.isJsonNull(map1.get("price"))?price:map1.get("price"));
			density = (String) (IsJsonNull.isJsonNull(map1.get("density"))?density:map1.get("density"));
			
			sqla = "insert into  STUFFPRICE  (STUFFID, STUFFNAME, PRICE, DENSITY) values ('"+stuffid+"','"+stuffname+"','"+price+"','"+density+"')";
			sqla = "declare " +
			"total number; " +
			"begin " +
			"select count(1) into total from STUFFPRICE where stuffid ='"+stuffid+"';" +
			"if total = 0 then " +
			"insert into  STUFFPRICE  (STUFFID, STUFFNAME, PRICE, DENSITY) values ('"+stuffid+"','"+stuffname+"',"+price+",'"+density+"');"+
			"else" +
			" update stuffprice t set t.stuffname = '"+stuffname+"',t.price= '"+price+"',t.density ='"+density +
					"' where t.stuffid ='"+stuffid+"' ;  " +
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
