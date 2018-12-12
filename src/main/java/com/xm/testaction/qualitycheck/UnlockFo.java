package com.xm.testaction.qualitycheck;

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
import com.xm.testaction.qualitycheck.sum.IsJsonNull;

public class UnlockFo extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UnlockFo() {
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
//		当前流程卡的当前工序
//		修改流程卡中的数据
//		插入或更新 reject_info 表里数据
		System.out.println(this.getClass().getName());
		request.setCharacterEncoding("utf-8");
		
		String barcode = request.getParameter("barcode");
		
		
		String data = request.getParameter("data");
		
		JSONArray jarr = JSONArray.fromObject(data);
		
		String foNo = "";
		String backNum = "";
		String accept = "0";
		String reject = "0";
		
		String result = "";
		String foName = "";
		
		HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
		
		for (int i = 0,j=jarr.size();i<j;i++){
			foNo="";
			backNum = "";
			accept = "0";
			reject = "0";
			foName = "";
			
			Map<String, Object> map1 = JsonConvert.json2Map(jarr.get(i).toString());
			foNo = (String) (IsJsonNull.isJsonNull(map1.get("fo_no"))?foNo:map1.get("fo_no"));
			backNum = (String) (IsJsonNull.isJsonNull(map1.get("backNum"))?backNum:map1.get("backNum"));
			
			accept = (String) (IsJsonNull.isJsonNull(map1.get("accept_num"))?accept:map1.get("accept_num"));
			reject = (String) (IsJsonNull.isJsonNull(map1.get("reject_num"))?reject:map1.get("reject_num"));
			
			foName = (String) (IsJsonNull.isJsonNull(map1.get("fo_opname"))?foName:map1.get("fo_opname"));
			
			if(backNum == null || backNum.equals("")){
				continue;	//如果没有退货数量，则不办理
			}
			int back = Integer.parseInt(backNum);	//退货数量
			int accNum = Integer.parseInt(accept);	//退货前的合格数
			if(back >accNum){
				result = "退货数量填写有误";
				continue;
			}
			int rejNum = Integer.parseInt(reject);
			rejNum  += back;
			accNum = accNum -back;
			int conNum = accNum + rejNum;	//送检数
//			更新流程卡数据
			String sqla = "update po_routing2 t set t.accept_num= "+accNum+",t.reject_num="+rejNum+" ,t.confirm_num="+conNum+" where t.barcode= '"+barcode+"' and t.fo_no="+foNo;
//	更新不合格信息表
			String sqlb = "declare " +
				"total number; " +
				"begin " +
				"select count(1) into total from reject_info where barcode= '"+barcode+"' and fo_no= "+foNo+";" +
				"if total = 0 then " +
				"insert into reject_info t (barcode, accept_num, reject_num, fo_no, confirm_num,FO_OPNAME ) values ('"+barcode+"',"+accNum+","+rejNum+","+foNo+","+conNum+",'"+foName+"' );" +
				"else " +
				"update reject_info t set t.confirm_num="+conNum+" ,t.accept_num= "+accNum+",t.reject_num= "+rejNum+" where t.barcode= '"+barcode+"' and t.fo_no= "+foNo+";"+
				"end if;" +
				"end;";
			try {
				System.out.println(sqla);
				Sqlhelper.executeUpdate(sqla, null);
				System.out.println("ok  "+sqla);
				result = "操作成功";
			} catch (Exception e) {
				// TODO: handle exception
				result = "操作失败";
			}
			try {
				Sqlhelper.executeUpdate(sqlb, null);
				System.out.println("ok  "+ sqlb);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
//		修改产品的已交付数量
		String orderId = request.getParameter("orderId");
		String productId = request.getParameter("productId");
//		查找已交付数量，并判断
		String sqlc="select t.alreadypaynum from order_detail t where t.order_id='"+orderId+"' and t.product_id='"+productId+"'";
		double alreadyNum = 0;
		try {
			String temp = Sqlhelper.exeQueryString(sqlc, null);
			alreadyNum = Double.parseDouble(temp);
			double willBack = Double.parseDouble(backNum);
			if(alreadyNum >= willBack){
				double nowPayNum = alreadyNum - willBack;
				String sqld = "update order_detail t set t.alreadypaynum = '"+nowPayNum+"' where t.order_id='"+orderId+"' and t.product_id='"+productId+"'";
				try {
					Sqlhelper.executeUpdate(sqld, null);
					System.out.println("ok  "+sqld );
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		response.setCharacterEncoding("utf-8");
		String json = "{\"result\":\""+result+"\"}";
		response.getWriter().append(json).flush();
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
