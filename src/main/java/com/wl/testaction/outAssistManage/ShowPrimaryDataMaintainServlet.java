package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.OutAssistPrimary;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class ShowPrimaryDataMaintainServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      doPost(request,response);
      
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
		String companyId=ChineseCode.toUTF8(request.getParameter("companyId"));
		String sql="select count(*) from outAssistPrimary where companyId=? ";
		String[] params={companyId};
		try{
		 totalCount=Sqlhelper.exeQueryCountNum(sql, params);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String Sql="select * from " +
				"(select B.*,rownum rn from " +
				"(select A.companyId,to_char(A.primaryTime,'yyyy-mm-dd hh24:mi:ss') primaryTime,A.payNum,A.reason,A.modifyPerson,to_char(A.modifyTime,'yyyy-mm-dd hh24:mi:ss') modifyTime,rownum row_num from outAssistPrimary A where companyId=? order by modifyTime desc)B order by modifyTime desc) " +
				"where rn<="+(countPerPage*pageNo)+" and rn>="+((pageNo-1)*countPerPage+1)+" order by modifyTime desc";
		List<OutAssistPrimary> outassistprimary =new ArrayList<OutAssistPrimary>();
		
		try{
		      outassistprimary=Sqlhelper.exeQueryList(Sql, params, OutAssistPrimary.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	    String json = PluSoft.Utils.JSON.Encode(outassistprimary);
	    json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
		
		
		
	}

}
