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
import com.wl.tools.StringUtil;

import cfmes.util.sql_data;

public class EditOrderSpecServlet extends HttpServlet {

	private static final long serialVersionUID = 1945853112761631406L;
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

		String orderId = ChineseCode.toUTF8(requestValueMap.get("orderId").trim());
	    String customer = ChineseCode.toUTF8(requestValueMap.get("customer").trim());
	    String deptUser = ChineseCode.toUTF8(requestValueMap.get("deptUser").trim());
	    
	    String endTime = ChineseCode.toUTF8(requestValueMap.get("endTime").trim());
	    String orderDate = ChineseCode.toUTF8(requestValueMap.get("orderDate").trim());
	    String orderStatus = ChineseCode.toUTF8(requestValueMap.get("orderStatus").trim());
	    String memo = ChineseCode.toUTF8(requestValueMap.get("memo"));
	    
//	    System.out.println(orderStatus);
	    String orderPaper = requestValueMap.get("orderPaper");
	    String duizhanghan = requestValueMap.get("duizhanghan");
	    String otherPaper = requestValueMap.get("otherPaper");
	    
//	    xiexiaoming 判断订单状态，是否允许修改
	    int status = 0;
	    try {
	    	status = Integer.parseInt(orderStatus);
		} catch (Exception e) {
			// TODO: handle exception
		}
	    if(status>= OrderStatus.PASS){
	    	String json = "{\"result\":\"订单当前状态,不允许修改.\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			return;
	    }
	    
		String updateOrderSql="update orders set " +
				"ORDER_ID='"+orderId+"',DEPT_USER='"+deptUser+"',MEMO='"+memo+"',endTime=to_date('"+endTime+"','yyyy-mm-dd,hh24:mi:ss'),ORDER_DATE=to_date('"+orderDate+"','yyyy-mm-dd,hh24:mi:ss')," +
				"ORDER_STATUS='"+orderStatus+"',CUSTOMER='"+customer+"',CHANGEPERSON='"+changePerson+"',CHANGETIME=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss')" ;
				
		
		if (!StringUtil.isNullOrEmpty(orderPaper)) {
			updateOrderSql+=",orderPaper='"+orderPaper+"'";
		}
		if (!StringUtil.isNullOrEmpty(duizhanghan)) {
			updateOrderSql+=",duizhanghan='"+duizhanghan+"'";
		}
		if (!StringUtil.isNullOrEmpty(otherPaper)) {
			updateOrderSql+=",otherPaper='"+otherPaper+"'";
		}
		
		updateOrderSql +="where ORDER_ID='"+orderId+"'";
		
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













