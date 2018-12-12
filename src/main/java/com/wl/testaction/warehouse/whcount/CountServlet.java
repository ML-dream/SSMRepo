package com.wl.testaction.warehouse.whcount;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.CheckoutId;
import com.wl.forms.CountSheetId;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class CountServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CountServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String data=request.getParameter("submitData");
		HashMap<String,String> datamap=(HashMap<String, String>) Test.JSON.Decode(data);
		String CountSheetId=datamap.get("CountSheetId");
		boolean b=false;
		String idSql="select sheetid from countsheetid";
		List<CountSheetId> resultList=new ArrayList<CountSheetId>();
		
		try{
			resultList=Sqlhelper.exeQueryList(idSql, null, CountSheetId.class);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for(int i=0,len=resultList.size();i<len;i++){
			CountSheetId countSheetId=new CountSheetId();
			countSheetId=resultList.get(i);
			String sheetid=countSheetId.getSheetId();
			if(sheetid.equals(CountSheetId)){
				b=true;
			}
		}
		
		if(!b){
		String id=datamap.get("id");
		String seq=datamap.get("seq");
		String CountDate=datamap.get("CountDate");
		String warehouseId=datamap.get("warehouseId");
		String operatorId=datamap.get("operatorId");
		String deptid=datamap.get("deptid");
		String empId=datamap.get("empId");
		
		HttpSession session=request.getSession();
		String createPerson=((User)session.getAttribute("user")).getStaffCode();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		 String createTime = df.format(new Date());
		 String changeTime = df.format(new Date());
		 
		String countSql="insert into whcount (COUNTSHEETID,COUNTDATE,WAREHOUSEID,OPERATORID,DEPTID,EMPID,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME) " +
				"values('"+CountSheetId+"',to_date('"+CountDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+warehouseId+"','"+operatorId+"','"+deptid+"','"+empId+"','"+createPerson+"'," +
				"to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'))";
		String sheetSql="insert into countsheetid values("+seq+",'"+id+"','"+CountSheetId+"')";
		try{
			Sqlhelper.executeUpdate(countSql, null);
			Sqlhelper.executeUpdate(sheetSql, null);
			String json="{\"result\":\"操作成功！\"}";
			out.append(json).flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		}
		
	}
		
}
