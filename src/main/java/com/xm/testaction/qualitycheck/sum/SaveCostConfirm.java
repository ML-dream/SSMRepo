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

public class SaveCostConfirm extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SaveCostConfirm() {
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
	
		String orderid = "";
		String draid = "";
		String stuff = "";
		
		String dia = "";
		String len = "";
		String wid = "";
		String hei = "";
		
		String islailiao = "Y";
		String pack = "0";
		String trans = "0";
		String realc = "0";
		String remark = "";
		String outasist = "";
		String stufflevel= "1";	//焊接件，多个材料数据
		String helpkey  = "1";
		
		String stucost="0";		//材料费用
		
		String sqla = "";
		String para = "0";	//是否来料
		for (int i = 0,j=jarr.size();i<j;i++){
			
			 orderid = "";
			 draid = "";
			 stuff = "";
			
			 dia = "";
			 len = "";
			 wid = "";
			 hei = "";
			
			 islailiao = "Y";
			 pack = "0";
			 trans = "0";
			 realc = "0";
			 remark = "";
			 outasist = "0";
			 stucost="0";
			 
			Map<String, Object> map1 = JsonConvert.json2Map(jarr.get(i).toString());
			orderid = ChineseCode.toUTF8((String) (IsJsonNull.isJsonNull(map1.get("orderid"))?orderid:map1.get("orderid")));
			draid = ChineseCode.toUTF8((String) (IsJsonNull.isJsonNull(map1.get("draid"))?draid:map1.get("draid")));
			stuff =  ChineseCode.toUTF8((String) (IsJsonNull.isJsonNull(map1.get("stuff"))?stuff:map1.get("stuff")));
			
			dia = (String) (IsJsonNull.isJsonNull(map1.get("dia"))?dia:map1.get("dia"));
			len = (String) (IsJsonNull.isJsonNull(map1.get("len"))?len:map1.get("len"));
			wid = (String) (IsJsonNull.isJsonNull(map1.get("wid"))?wid:map1.get("wid"));
			hei = (String) (IsJsonNull.isJsonNull(map1.get("hei"))?hei:map1.get("hei"));
			
			islailiao = (String) (IsJsonNull.isJsonNull(map1.get("issup"))?islailiao:map1.get("issup"));
//			if(islailiao.equals("Y")){
//				para = "1";}else{
//					para = "0";
//				}
			pack = (String) (IsJsonNull.isJsonNull(map1.get("pack"))?pack:map1.get("pack"));
			trans = (String) (IsJsonNull.isJsonNull(map1.get("trans"))?trans:map1.get("trans"));
			realc = (String) (IsJsonNull.isJsonNull(map1.get("realc"))?realc:map1.get("realc"));
			
			remark = ChineseCode.toUTF8((String) (IsJsonNull.isJsonNull(map1.get("remark"))?remark:map1.get("remark")));
			outasist = (String) (IsJsonNull.isJsonNull(map1.get("outasist"))?outasist:map1.get("outasist"));
			
			stucost= (String) (IsJsonNull.isJsonNull(map1.get("stucost"))?stucost:map1.get("stucost"));
			
			sqla = "declare " +
					"total number; " +
					"begin " +
					"select count(1) into total from costinput where orderid ='"+orderid+"' and draid = '"+draid+"' and stufflevel='1';" +
					"if total = 0 then " +
					"insert into costinput (orderid, draid, stuff, dia, len, wid, hei, islailiao, pack, trans, realc, remark,outasist,stufflevel,helpkey,stucost) " +
					"values ('"+orderid+"','"+draid+"','"+stuff+"','"+dia+"','"+len+"','"+wid+"','"+hei+"','"+islailiao+"','"+pack+"','"+trans+"','"+realc+"','"+remark+"','"+outasist+"','1','1','"+stucost+"');"+
					"else" +
					" update costinput t set t.stuff = '"+stuff+"', t.dia='"+dia+"', t.len='"+len+"', t.wid='"+wid+"', t.hei='"+hei+"', " +
							"t.islailiao='"+islailiao+"', t.pack='"+pack+"', t.trans='"+trans+"', t.realc='"+realc+"' ,t.remark ='"+remark+"',t.stucost='"+stucost+"',t.outasist= "+outasist +
							"where t.orderid ='"+orderid+"' and t.draid = '"+draid+"';  " +
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
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
