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
 * 保存listtree
 *
 */
public class SaveListTree extends HttpServlet {
	/**
	 * Constructor of the object.
	 */
	public SaveListTree() {
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
	
		String id = "";
		String text = "";
		String iconCls = "";
		
		String pid = "";
		String url = "";
		String level = "2";
		for (int i = 0,j=jarr.size();i<j;i++){
			
			 id = "";
			 text = "";
			 iconCls = "";
			
			 pid = "";
			 url = "";
			 level = "2";
			Map<String, Object> map1 = JsonConvert.json2Map(jarr.get(i).toString());
			System.out.println(i +" "+map1);
			id = (String) (IsJsonNull.isJsonNull(map1.get("id"))?id:map1.get("id"));
			text =  ChineseCode.toUTF8((String) (IsJsonNull.isJsonNull(map1.get("text"))?text:map1.get("text")));
			iconCls =  (String) (IsJsonNull.isJsonNull(map1.get("iconCls"))?iconCls:map1.get("iconCls"));
			
			pid = (String) (IsJsonNull.isJsonNull(map1.get("pid"))?pid:map1.get("pid"));
			url = (String) (IsJsonNull.isJsonNull(map1.get("url"))?url:map1.get("url"));
			level = (String) (IsJsonNull.isJsonNull(map1.get("level"))?level:map1.get("level"));
			
			String sqla = "declare " +
					"total number; " +
					"begin " +
//					"select count(1) into total from LISTTREE where pageid ='"+id+"' ;" +
//					"if total = 0 then " +
					"insert into LISTTREE (PAGEID, TEXT, ICONCLS, PID, PAGEURL,pagelevel) " +
					"values ('"+id+"','"+text+"','"+iconCls+"','"+pid+"','"+url+"','"+level+"');"+
//					"else" +
//					" update LISTTREE t set t.pageid = '"+id+"', t.text='"+text+"', t.ICONCLS='"+iconCls+"', t.pid='"+pid+"', t.pageurl='"+url+"',t.pagelevel='"+level+"'"
//							"where t.pageid ='"+id+"';  " +
//					"end if;" +
					"end;";
			
//			没做工艺，导致fo_header表材料更新失败
			
			try {
//				System.out.println(sqla);
				Sqlhelper0.executeUpdate(sqla, null);
//				System.out.println("ok "+sqla);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(sqla);
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
