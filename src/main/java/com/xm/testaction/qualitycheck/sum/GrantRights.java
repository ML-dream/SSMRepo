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
import com.wl.tools.Sqlhelper;
import com.wl.tools.Sqlhelper0;
import com.wl.tools.StringUtil;

public class GrantRights extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GrantRights() {
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
		String para = "";
		String staffcode = "";
		String cankao = "";
//		如果参数1
		para = StringUtil.isNullOrEmpty(request.getParameter("para"))?para:request.getParameter("para");
		staffcode = StringUtil.isNullOrEmpty(request.getParameter("staffcode"))?staffcode:request.getParameter("staffcode");
		cankao = StringUtil.isNullOrEmpty(request.getParameter("cankao"))?cankao:request.getParameter("cankao");
		
		if(!para.isEmpty()&&para.equals("1")){
			String sql = "merge into rightassign a using (select b.pageid from rightassign b where b.staffcode ='"+cankao+"') c " +
					" on (a.staffcode='"+staffcode+"' and a.pageid=c.pageid ) " +
					"when not matched then " +
					"insert  values ('"+staffcode+"',c.pageid,'','','') ";
			
//			String sql ="insert into rightassign t (staffcode, pageid )(select '"+staffcode+"',t.pageid from rightassign t where t.staffcode= '"+cankao+"')";
			try {
				Sqlhelper.executeUpdate(sql, null);
				System.out.println("ok  "+sql);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			String result = "保存成功!";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(result).flush();
			return;
		}
		
		String json = request.getParameter("data");
		
		JSONArray jarr = JSONArray.fromObject(json);
	
		
		String pageid = "";
		for (int i = 0,j=jarr.size();i<j;i++){
			
			 staffcode = "";
			 pageid = "";
			Map<String, Object> map1 = JsonConvert.json2Map(jarr.get(i).toString());
			staffcode = (String) (IsJsonNull.isJsonNull(map1.get("staffcode"))?staffcode:map1.get("staffcode"));
			pageid =  ChineseCode.toUTF8((String) (IsJsonNull.isJsonNull(map1.get("pageid"))?staffcode:map1.get("pageid")));
			String sqla = "declare " +
					"total number; " +
					"begin " +
					"select count(1) into total from rightassign b where b.staffcode ='"+staffcode+"' and b.pageid='"+pageid+"' ;" +
					"if total = 0 then " +
					"insert into rightassign (staffcode,pageid) " +
					"values ('"+staffcode+"','"+pageid+"');"+
//					"else " +
//					" update LISTTREE t set t.pageid = '"+id+"', t.text='"+text+"', t.ICONCLS='"+iconCls+"', t.pid='"+pid+"', t.pageurl='"+url+"',t.pagelevel='"+level+"'"
//							"where t.pageid ='"+id+"';  " +
					"end if;" +
					"end;";
			
			
			try {
				System.out.println(sqla);
				Sqlhelper0.executeUpdate(sqla, null);
				System.out.println("ok "+sqla);
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
