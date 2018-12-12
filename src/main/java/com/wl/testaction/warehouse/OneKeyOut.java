package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.wl.forms.OrderItem;
import com.wl.forms.User;
import com.wl.tools.JsonConvert;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Stockcl;
import com.wl.tools.StringUtil;
import com.xm.testaction.qualitycheck.statejudge.TypeResult;
import com.xm.testaction.qualitycheck.sum.IsJsonNull;

public class OneKeyOut extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OneKeyOut() {
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

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String result = "已响应";
		
		String gridjson =request.getParameter("gridjson");
		
		System.out.println(gridjson);
		
		JSONArray jarr = JSONArray.fromObject(gridjson);
		
		
		for (int i = 0,j=jarr.size();i<j;i++){
			
			String checksheet_id=request.getParameter("checksheet_id");
			String orderId=request.getParameter("orderId");
			String checkoutType=request.getParameter("checkoutType");
//			String data=request.getParameter("submitData");
//			String warehouseId=request.getParameter("warehouseId");
			
			Map<String, Object> datamap = JsonConvert.json2Map(jarr.get(i).toString());
			  System.out.println(datamap);
			  
			String productId=(String) datamap.get("productId");
			String productName=(String) datamap.get("productName");
			String issueNum=(String) datamap.get("issueNum");
			String drawingId=(String) datamap.get("drawingId");
			String productType=(String) datamap.get("productType");
			String spec=(String) datamap.get("spec");
			
			String stockNum= datamap.get("stockNum").toString();
//			出库数量为0，直接跳过
			double temp = Double.parseDouble(stockNum);
			if(temp <=0){
				continue;
			}
			String purductNum=datamap.get("purductNum").toString();
			String alreadyPayNum=datamap.get("alreadyPayNum").toString();
			String noPayNum= datamap.get("noPayNum").toString();
			
			String checkoutNum= datamap.get("stockNum").toString();	//默认库存
			String unitPrice= datamap.get("unitPrice").toString();
			String price=datamap.get("price").toString();
			String stockId=(String) (IsJsonNull.isJsonNull(datamap.get("stockId"))?"":datamap.get("stockId"));
			//String qualityId=(String) datamap.get("qualityId");
			String memo=(String) (IsJsonNull.isJsonNull(datamap.get("stockId"))?"":datamap.get("stockId"));
			double totalPrice=0;
//			如果未交付数量小于库存，不予处理
			double dPayed = Double.parseDouble(alreadyPayNum);
			double dProductNum = Double.parseDouble(purductNum);
			double dStoctNum = Double.parseDouble(stockNum);
			double dNopay = dProductNum -dPayed;
			if(dStoctNum >dNopay){
				continue;
			}
			
			HttpSession session = request.getSession();
			String createPerson = ((User)session.getAttribute("user")).getStaffCode();
			String changePerson = ((User)session.getAttribute("user")).getStaffCode();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String createTime = df.format(new Date());
			String changeTime = df.format(new Date());
			String orderSql="";
			
			double unitprice=Double.parseDouble(unitPrice);
			double outNum=Double.parseDouble(checkoutNum);
			double payNum=Double.parseDouble(alreadyPayNum);
			double alreadyNum=outNum+payNum;
			double productNum=0;
			
			String[] params={orderId,productId,issueNum};
			totalPrice=StringUtil.isNullOrEmpty(price)?outNum*unitprice:Double.parseDouble(price);
//			if(price.equals("")){
//				double num=Double.parseDouble(checkoutNum);
//				double unitprice=Double.parseDouble(unitPrice);
//				totalPrice=num*unitprice;
//				
//			}
			
			String checkoutSql="insert into mycheckout_detl (CHECKSHEET_ID,ITEM_ID,ITEM_NAME," +
					"SPEC,STOCKNUM,CHECKOUT_NUM,UNITPRICE,PRICE,STOCK_ID,MEMO,ISSUE_NUM,ITEM_TYPE," +
					"ALREADYPAYNUM,NOPAYNUM,PURDUCTNUM,ORDER_ID,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME,DRAWINGID) values('"+checksheet_id+"','"+productId+"','"+productName+"'," +
					"'"+spec+"','"+stockNum+"',"+checkoutNum+","+unitPrice+","+totalPrice+",'"+stockId+"'," +
					"'"+memo+"','"+issueNum+"','"+productType+"',"+alreadyPayNum+","+noPayNum+","+purductNum+",'"+orderId+"'," +
					"'"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+drawingId+"')";
			int re=0;
			try{
				re=Sqlhelper.executeUpdate(checkoutSql, null);
				result="已响应";
			}catch(Exception e){
				e.printStackTrace();
				result="已响应";
			}
			
				
			Stockcl.Stockout(productId,outNum);
			
			if(checkoutType.equals("0")){
				String productNumSql="select purduct_num from order_detail where order_id='"+orderId+"' and product_id='"+productId+"' and issue_num='"+issueNum+"'";
				OrderItem orderitem=new OrderItem();
				
				try{
					orderitem=Sqlhelper.exeQueryBean(productNumSql, null, OrderItem.class);
					productNum=orderitem.getPurductNum();
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(alreadyNum<productNum){
					orderSql="update ORDER_DETAIL set alreadypaynum='"+alreadyNum+"',product_status=80 where order_id=? and product_id=? and issue_num=?";
				}else{
					orderSql="update ORDER_DETAIL set alreadypaynum='"+alreadyNum+"',product_status=90 where order_id=? and product_id=? and issue_num=?";
				}
				
				try{
					Sqlhelper.executeUpdate(orderSql, params);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}
		String json = "{\"result\":\""+result+"\"}";
		System.out.println(json);
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
