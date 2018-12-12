package com.wl.testaction.orderManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.util.sql_data;

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;
import com.wl.tools.UUIDHexGenerator;

public class QuotationProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
		String fproductId = ChineseCode.toUTF8(request.getParameter("fproductId"));
		double ProducTotalPrice=0;
		double ProductGrossProfit = 0;
		String data = ChineseCode.toUTF8(request.getParameter("data").trim());
		String[] nameValues = data.split(",");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    double YCLTotalPrice=0;
	    double YCLGrossProfit=0;
	    int YCLTotalTime;
		for (int i = 0; i < nameValues.length; i++) {
			String name = (nameValues[i].split("#"))[0];
			String value = (nameValues[i].split("#"))[1];
			String unit = (nameValues[i].split("#"))[2];
			String totalTime = (nameValues[i].split("#"))[3];
			String totalPrice = (nameValues[i].split("#"))[4];
			String grossProfit = (nameValues[i].split("#"))[5];
	        if(name.equals("YCL")){
	        YCLTotalPrice=Double.parseDouble(totalPrice);
	        System.out.println("YCL");
	        System.out.println(YCLTotalPrice);
	        YCLGrossProfit=Double.parseDouble(grossProfit);
	        YCLTotalTime=Integer.parseInt(totalTime);
	        }
			ProducTotalPrice+=Double.parseDouble(totalPrice);
			ProductGrossProfit+=Double.parseDouble(grossProfit);
			String sql = "";
			String checkSql = "select count(*) from quotation where orderId='"+orderId+"' and PRODUCTID='"+productId+"' and fproductid='"+fproductId+"' and CRAFTID='"+name+"'";
			System.out.println(checkSql);
			int count=0;
			String[] params={};
			try{
				count=Sqlhelper.exeQueryCountNum(checkSql, params);
			}catch(Exception e){
				e.printStackTrace();
			}
	         if(count==0){
	        	 
					String uuid = UUIDHexGenerator.getInstance().generate();
					System.out.println(uuid);
					sql = "insert into quotation (mainId,ORDERID,PRODUCTID,fproductid,CRAFTID," +
					"PRICE,PRICEUNIT,CREATETIME,CHANGETIME,CREATEPERSON,CHANGEPERSON,totalTime,totalPrice,grossProfit)" +
					"values('"+uuid+"','"+orderId+"','"+productId+"','"+fproductId+"','"+name+"'," +
					"'"+value+"','"+unit+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss')," +
					"to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+createPerson+"','"+changePerson+"','"+totalTime+"','"+totalPrice+"','"+grossProfit+"')";
				}else {
					sql="update quotation set " +
						"PRICE='"+value+"',CHANGETIME=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),CHANGEPERSON='"+changePerson+"',totalTime='"+totalTime+"',totalPrice='"+totalPrice+"',grossProfit='"+grossProfit+"' " +
						"where  orderId='"+orderId+"' and PRODUCTID='"+productId+"' and fproductid='"+fproductId+"' and CRAFTID='"+name+"'";
				}
		   
			
			System.out.println("sql="+sql);
			
			try {
				Sqlhelper.executeUpdate(sql, params);
			} catch (SQLException e) {
				String json = "{\"result\":\"操作失败!\"}";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
				e.printStackTrace();
				return;
			}	
			
			String quotationTotalOrderDetailSql = "update order_detail set UNITPRICE='"+ProducTotalPrice+"', QUOTATIONTOTAL='"+ProducTotalPrice+"',planProfit='"+ProductGrossProfit+"',materialPrice='"+YCLTotalPrice+"' "+
					"where order_id='"+orderId+"' and product_id='"+productId+"' and fproduct_id='"+fproductId+"'";
			
			try {
				System.out.println(quotationTotalOrderDetailSql);
				Sqlhelper.executeUpdate(quotationTotalOrderDetailSql, params);
			} catch (SQLException e) {
				String json = "{\"result\":\"操作失败!\"}";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();

				e.printStackTrace();
				return;
			}	
		}
		String json = "{\"result\":\"操作成功!\"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
	}


}
