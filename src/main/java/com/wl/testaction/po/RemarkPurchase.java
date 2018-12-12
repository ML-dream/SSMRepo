package com.wl.testaction.po;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.wl.forms.User;
import com.wl.tools.JsonConvert;
import com.wl.tools.Sqlhelper;
import com.xm.testaction.qualitycheck.statejudge.TypeResult;
import com.xm.testaction.qualitycheck.sum.IsJsonNull;

public class RemarkPurchase extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RemarkPurchase() {
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
//  	标记收货已对账
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String result = "操作成功";
		
		String gridjson =request.getParameter("gridjson");
				
		JSONArray jarr = JSONArray.fromObject(gridjson);
		
		String sheetId = "";
		String itemId = "";		//onOperatePower 触发时，会取消当前行的选中状态 
		String condition1 = "";
		String sqla = "";
		
		for (int i = 0,j=jarr.size();i<j;i++){
			
			sheetId = "";
			itemId = "";
			
			Map<String, Object> map1 = JsonConvert.json2Map(jarr.get(i).toString());
			System.out.println(map1);
				
			sheetId = (String) (IsJsonNull.isJsonNull(map1.get("prSheetid"))?sheetId:map1.get("prSheetid"));
			itemId =(String) (IsJsonNull.isJsonNull(map1.get("itemId"))?itemId:map1.get("itemId"));
			if(i==0){
				condition1 += "'"+itemId+"'";
			}else{
				condition1 += ",'"+itemId+"'";
			}
			
		}
		sqla = "update prdetail t set t.ischecked=1 where t.prsheetid= ? and t.itemid in ("+condition1+") ";
		
		String para [] = {sheetId};
		try {
			System.out.println(sqla);
			Sqlhelper.executeUpdate(sqla, para);
			System.out.println("ok "+sqla);
			result="操作成功";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result="操作失败";
		}
//		如果一个单子下的全部物料都标记过了，则修改单子的状态
		String sqlb = "declare " +
				"  itemNum number;" +
				"begin " +
				"  select count(1) into itemNum from prdetail a where a.prsheetid='"+sheetId+"' and a.status='1' and a.ischecked=0; " +
				"  if itemNum=0 then " +
				"     update pr t set t.ischecked =1 where t.prsheetid='"+sheetId+"';" +
				"  end if;" +
				"end ;";
		try {
			Sqlhelper.executeUpdate(sqlb, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String json = "{\"result\":\""+result+"\"}";
		out.append(json).flush();
		
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
