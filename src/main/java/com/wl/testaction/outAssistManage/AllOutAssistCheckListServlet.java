package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ProcessesPlan;
import com.wl.tools.Sqlhelper;

public class AllOutAssistCheckListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
		   String sql="select count(*) from outAssistMenu";
		   String[] Params={};
		   try{
			  totalCount=Sqlhelper.exeQueryCountNum(sql, Params);
		   }catch(Exception e){
			   e.printStackTrace();
		   } 
	       String Sql="select C.* from (select A.menuId,A.waixiecom,to_char(A.deliverTime,'yyyy-mm-dd') deliverTime,A.totalNum,A.outassiststatus,B.companyName,rownum row_num from outassistmenu A " +
	          		"left join outAssistCom B ON A.waixiecom=B.companyId " +
	          		"order by A.deliverTime desc) C where ROW_NUM<="+(countPerPage*pageNo)+" and Row_num>="+((pageNo-1)*countPerPage+1);
	          List<ProcessesPlan> outAssistMenuList=new ArrayList<ProcessesPlan>();
	          String[] params={};
	          try{
	       	   outAssistMenuList=Sqlhelper.exeQueryList(Sql, params, ProcessesPlan.class);
	          }catch(Exception e){
	       	   e.printStackTrace();
	          }
	   	    String json = PluSoft.Utils.JSON.Encode(outAssistMenuList);
	   	    json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
	   		response.setCharacterEncoding("UTF-8");
	   		response.getWriter().append(json).flush();
	   		System.out.println(json);  
	}



}
