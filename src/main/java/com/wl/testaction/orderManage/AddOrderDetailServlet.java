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

import cfmes.util.sql_data;

import com.wl.forms.ItemType;
import com.wl.forms.User;
import com.wl.testaction.utils.UploadUtils;
import com.wl.tools.ChineseCode;
import com.wl.tools.StringUtil;
import com.xm.testaction.qualitycheck.sum.AddNewStuff;

public class AddOrderDetailServlet extends HttpServlet {

	private static final long serialVersionUID = -7390010185404923459L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		
		
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
//	    String productId = ChineseCode.toUTF8(requestValueMap.get("productId").trim());
//	    String productName = ChineseCode.toUTF8(requestValueMap.get("productName").trim());
	   
	    String FProductId = "";
	    if(StringUtil.isNullOrEmpty(requestValueMap.get("FProductId"))){	//空
	    	FProductId = orderId;
	    }else {
	    	FProductId = ChineseCode.toUTF8(requestValueMap.get("FProductId").trim());
		}
	    String spec = ChineseCode.toUTF8(requestValueMap.get("spec").trim());
	    String drawingId="";
	    
//	    String drawingId = ChineseCode.toUTF8(requestValueMap.get("drawingId").trim());
	    String issueNum = ChineseCode.toUTF8(requestValueMap.get("issueNum").trim());
	    String deptNo = ChineseCode.toUTF8(requestValueMap.get("deptNo").trim());
	    String itemTypeId = ChineseCode.toUTF8(requestValueMap.get("itemTypeId").trim());
	    String productNum = ChineseCode.toUTF8(requestValueMap.get("productNum").trim());
	    
	 //   String productStatus = ChineseCode.toUTF8(requestValueMap.get("productStatus").trim());
	    String unitPrice = ChineseCode.toUTF8(requestValueMap.get("unitPrice").trim());
	    String islailiao = ChineseCode.toUTF8(requestValueMap.get("islailiao").trim());
	    String BTime = ChineseCode.toUTF8(requestValueMap.get("BTime").trim());
	    String ETime = ChineseCode.toUTF8(requestValueMap.get("ETime").trim());
	    String lot = ChineseCode.toUTF8(requestValueMap.get("lot").trim());
	    String isWaiXie=ChineseCode.toUTF8(requestValueMap.get("iswaixie").trim());

	    
	//    String SBTime = ChineseCode.toUTF8(requestValueMap.get("SBTime").trim());
	//    String SETime = ChineseCode.toUTF8(requestValueMap.get("SETime").trim());
	    String memo = ChineseCode.toUTF8(requestValueMap.get("memo"));
	    String paper = requestValueMap.get("paper");
	    String otherPaper = requestValueMap.get("otherPaper");
	    int willpaynum = (int)((Integer.parseInt(productNum))*1.05+1);
	    
	    request.setCharacterEncoding("utf-8");
	    String productName = requestValueMap.get("productName").trim();
	    String productId = requestValueMap.get("productId").trim();
	    
	    String material=requestValueMap.get("material").trim();
	    
	    if(StringUtil.isNullOrEmpty(requestValueMap.get("drawingId"))){
	    	drawingId=productId;
	    }else{
	    	drawingId=ChineseCode.toUTF8(requestValueMap.get("drawingId"));
	    }
	    
//	   xiem  新材料维护
	    try {
			AddNewStuff.addNewStuff(material);
		} catch (Exception e) {
			// TODO: handle exception
		}
		response.setCharacterEncoding("UTF-8");
//		产品号里串上客户编号，xiem，防止简单编号重复
		String customerId = StringUtil.isNullOrEmpty(requestValueMap.get("customerId"))?"":requestValueMap.get("customerId").trim();
		if(customerId.isEmpty()){
//			如果前台的客户编号为空，则从订单号里摘取
			String temp1 [] = orderId.split("-");
			String temp2 = temp1[2];
			customerId =temp2;
		}
		productId= customerId+"-"+productId;
		String  addOrderSql = "insert into order_detail " +
				"(ORDER_ID,PRODUCT_ID,PRODUCT_NAME,DRAWINGID,PURDUCT_NUM," +
	//			"B_TIME,E_TIME,DEPT_ID,SB_TIME,SE_TIME," +
	//			"PRODUCT_STATUS,fproduct_id,SPEC,MEMO,createTime," +
	            "B_TIME,E_TIME,DEPT_ID," +
				"fproduct_id,SPEC,MEMO,createTime," +
				"changeTime,createPerson,changePerson,unitPrice,issue_Num,cengci," +
				"paper,otherPaper,isjiaohuo,PRODUCTTYPE,willpaynum,lot,islailiao,iswaixie,material) values ('"+
				orderId+"','"+productId+"','"+productName+"','"+drawingId+"','"+productNum+"',"+
				"to_date('"+BTime+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+ETime+"','yyyy-mm-dd,hh24:mi:ss'),'" +deptNo+"','"+
	//          "to_date('"+SBTime+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+SETime+"','yyyy-mm-dd,hh24:mi:ss'),'"
	//          +productStatus+"','"+FProductId+"','"+spec+"','"+memo+"'" +
	            FProductId+"','"+spec+"','"+memo+"'" +
				",to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+
				createPerson+"','"+changePerson+"','"+unitPrice+"','"+issueNum+"','2'," +
				"'"+paper+"','"+otherPaper+"','"+islailiao+"','"+itemTypeId+"','"+willpaynum+"','"+lot+"','"+islailiao+"','"+isWaiXie+"','"+material+"')";

		String addOrderItemSql = "insert into item " +
	//			"(ITEM_TYPEID,ITEM_ID,ITEM_NAME,LOT_SIZE,ITEM_STATUS,ITEM_DRAWING," +
	            "(ITEM_TYPEID,ITEM_ID,ITEM_NAME,LOT_SIZE,ITEM_DRAWING," +
				"ITEM_SPECIFICATION,ITEM_PRICE,EXTRA_A,FITEM_ID,MEMO," +
				"CREATE_TIME,UPDATE_TIME,CREATE_PERSON,UPDATE_PERSON,EXTRA_B) " +
	//			"values ('"+itemTypeId+"','"+productId+"','"+productName+"','"+productNum+"','"+productStatus+"','"+drawingId+"'," +
	            "values ('"+itemTypeId+"','"+productId+"','"+productName+"','"+productNum+"','"+drawingId+"'," +
						"'"+spec+"','"+unitPrice+"','"+issueNum+"','"+FProductId+"','"+memo+"'," +
						"to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+createPerson+"','"+changePerson+"','2')";
		System.out.println("addOrderSql=="+addOrderSql);
		System.out.println("addOrderItemSql=="+addOrderItemSql);
		
		sql_data sqlData = new sql_data();
		String result = "操作成功";
		String json = "";
		try {
			sqlData.exeUpdateThrowExcep(addOrderSql);
			
		} catch (SQLException e) {
			result = "操作失败";
		}
		try {
			sqlData.exeUpdateThrowExcep(addOrderItemSql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		json = "{\"result\":\""+result+"!\"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
	}


}
