package com.wl.testaction.orderManage;


import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;

import com.wl.common.OrderStatus;
import com.wl.forms.User;
import com.wl.testaction.utils.UploadUtils;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.xm.testaction.qualitycheck.sum.AddNewStuff;

import cfmes.util.sql_data;

public class EditOrderDetailSpecServlet extends HttpServlet {

	private static final long serialVersionUID = -6636466172419300443L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		
		
		System.out.println(this.getClass().getName());
		response.setCharacterEncoding("UTF-8");
		Map<String, String> requestValueMap = new HashMap<String, String>();	//用来封装request请求中的参数
		
		HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
		try {
			requestValueMap = UploadUtils.UploadLoadFile(request, response, createPerson, requestValueMap);	//保存文件+得到request属性值
		} catch (FileUploadException e1) {
			String json = "{\"result\":\"文件太大，请检查文件上大小！！\"}";
			response.getWriter().append(json).flush();
			e1.printStackTrace();
		}
		

		
		
		
		//ChineseCode.toUTF8(requestValueMap.get("customer").trim());

		String orderId = ChineseCode.toUTF8(requestValueMap.get("orderId").trim());
	    String productId = ChineseCode.toUTF8(requestValueMap.get("productId").trim());
	    String productName =requestValueMap.get("productName").trim();
	    
	    String FProductId = ChineseCode.toUTF8(requestValueMap.get("FProductId").trim());
	    String spec = ChineseCode.toUTF8(requestValueMap.get("spec").trim());
	    String issueNum = ChineseCode.toUTF8(requestValueMap.get("issueNum").trim());
	    
	    String productNum = ChineseCode.toUTF8(requestValueMap.get("productNum").trim());
	    String BTime = ChineseCode.toUTF8(requestValueMap.get("BTime").trim());
	    String ETime = ChineseCode.toUTF8(requestValueMap.get("ETime").trim());
	    
	    String deptNo = ChineseCode.toUTF8(requestValueMap.get("deptNo").trim());
	    String unitPrice = ChineseCode.toUTF8(requestValueMap.get("unitPrice").trim());
	//    String SBTime = ChineseCode.toUTF8(requestValueMap.get("SBTime").trim());
	//    String SETime = ChineseCode.toUTF8(requestValueMap.get("SETime").trim());
	    
    //    String productStatus = ChineseCode.toUTF8(requestValueMap.get("productStatus").trim());
	    String memo = ChineseCode.toUTF8(requestValueMap.get("memo").trim());
        String islailiao=ChineseCode.toUTF8(requestValueMap.get("islailiao").trim());
        String iswaixie=ChineseCode.toUTF8(requestValueMap.get("iswaixie").trim());
	    String paper = requestValueMap.get("paper");
	    String detailOtherPaper = requestValueMap.get("detailOtherPaper");
//	    xiem 材料
	    request.setCharacterEncoding("utf-8");
	    String material=requestValueMap.get("material").trim();
	    try {
			AddNewStuff.addNewStuff(material);
		} catch (Exception e) {
			// TODO: handle exception
		}
//	    11-24 xiexiaoming 修改权限
	    String sqla = "select to_number(t.order_status) orderStatus from ORDERS t where t.order_id=?";	
	    String [] params = {orderId};
		int status = 0;
		try {
			status = Sqlhelper.exeQueryCountNum(sqla, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(status>= OrderStatus.PASS){
	    	String json = "{\"result\":\"订单当前状态,不允许修改.\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			return;
		}
	    
		String updateOrderSql="update order_detail set " +
				"PRODUCT_NAME='"+productName+"',spec='"+spec+"',ISSUE_NUM='"+issueNum+"',PURDUCT_NUM='"+productNum+"'," +
				"B_Time=to_date('"+BTime+"','yyyy-mm-dd,hh24:mi:ss'),E_Time=to_date('"+ETime+"','yyyy-mm-dd,hh24:mi:ss'),DEPT_ID='"+deptNo+"'," +
				"memo='"+memo+"',CHANGETIME=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),CHANGEPERSON='"+changePerson+"', " +
				"islailiao='"+islailiao+"',iswaixie='"+iswaixie+"', "+
				"paper='"+paper+"',OtherPaper='"+detailOtherPaper+"',unitprice=' "+unitPrice+"',material='"+material+"' "+
				"where ORDER_ID='"+orderId+"' and PRODUCT_ID='"+productId+"' and FPRODUCT_ID='"+FProductId+"'";
		System.out.println("updateOrderSql="+updateOrderSql);
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(updateOrderSql);
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (SQLException e) {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













