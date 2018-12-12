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
/**
 * 
 *保存焊件子材料
 */
public class SaveSubStuff extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SaveSubStuff() {
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
		String json = request.getParameter("data");
		
		JSONArray jarr = JSONArray.fromObject(json);
	
		String orderid = request.getParameter("orderid");
		String draid = request.getParameter("draid");
		String stuff = "";
		
		String dia = "";
		String len = "1";
		String wid = "1";
		String hei = "1";
		
		String islailiao = "N";
		String stufflevel= "2";	//焊接件，多个材料数据
		String helpkey = "";
		String sqla = "";
		for (int i = 0,j=jarr.size();i<j;i++){
			
			 stuff = "";
			
			 dia = "";
			 len = "1";
			 wid = "1";
			 hei = "1";
			
			 islailiao = "N";
			 helpkey = "";
			 
			Map<String, Object> map1 = JsonConvert.json2Map(jarr.get(i).toString());
			stuff =  (String) (IsJsonNull.isJsonNull(map1.get("stuff"))?stuff:map1.get("stuff"));
			
			dia = (String) (IsJsonNull.isJsonNull(map1.get("dia"))?dia:map1.get("dia"));
			len = (String) (IsJsonNull.isJsonNull(map1.get("len"))?len:map1.get("len"));
			wid = (String) (IsJsonNull.isJsonNull(map1.get("wid"))?wid:map1.get("wid"));
			hei = (String) (IsJsonNull.isJsonNull(map1.get("hei"))?hei:map1.get("hei"));
			
			islailiao = (String) (IsJsonNull.isJsonNull(map1.get("issup"))?islailiao:map1.get("issup"));
			helpkey = (String) (IsJsonNull.isJsonNull(map1.get("helpkey"))?islailiao:map1.get("helpkey"));
			
			sqla = "declare " +
					"total number; " +
					"begin " +
					"select count(1) into total from costinput where orderid ='"+orderid+"' and draid = '"+draid+"' and stufflevel='2' and helpkey='"+helpkey+"';" +
					"if total = 0 then " +
					"insert into costinput (orderid, draid, stuff, dia, len, wid, hei, islailiao, stufflevel,helpkey) " +
					"values ('"+orderid+"','"+draid+"','"+stuff+"','"+dia+"','"+len+"','"+wid+"','"+hei+"','"+islailiao+"','1','"+helpkey+"');"+
					"else" +
					" update costinput t set t.stuff = '"+stuff+"', t.dia='"+dia+"', t.len='"+len+"', t.wid='"+wid+"', t.hei='"+hei+"', " +
							"t.islailiao='"+islailiao+"' " +
							"where t.orderid ='"+orderid+"' and t.draid = '"+draid+"' and t.stufflevel='2' and t.helpkey='"+helpkey+"';" +
					"end if;" +
//					" update order_detail m set m.islailiao = '"+para+"' where m.order_id = '"+orderid+"' and m.product_id = '"+draid+"' ;"+
//					" update foheader n set n.matirial = '"+stuff+"' where n.orderid = '"+orderid+"' and n.productid = '"+draid+"' ;"+
					"end;";
			
//			没做工艺，导致fo_header表材料更新失败
			
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

		doGet(request, response);
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
