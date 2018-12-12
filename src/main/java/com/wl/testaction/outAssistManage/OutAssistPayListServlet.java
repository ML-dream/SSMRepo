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
import com.wl.tools.StringUtil;

public class OutAssistPayListServlet extends HttpServlet {

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
	    String sql="select count(*) from outAssistMenu ";
	    String[] params=null;
	    try{
	     totalCount=Sqlhelper.exeQueryCountNum(sql, params);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    String Sql="select menuId,deliverTime,waixiecom,payTime,companyName,cast(totalPrice as number(18,2)) totalPrice,cast(theorytotalPrice as number(18,2)) theorytotalPrice,cast(qualityFine as number(18,2)) qualityFine from " +
	    		 "(select menuId,deliverTime,waixiecom,payTime,companyName,cast(theorytotalPrice as number(18,2)) theorytotalPrice,cast(totalPrice as number(18,2)) totalPrice,cast(qualityFine as number(18,2)) qualityFine,rownum rn " +
	    		 "from (select E.*,rowNum row_num from (select A.menuId,to_char(A.deliverTime,'yyyy-mm-dd') deliverTime,A.waixiecom,B.companyName," +
	    		 "to_char(D.payTime,'yyyy-mm-dd') payTime,cast(sum(D.totalPrice) as number(18,2)) theorytotalPrice,cast(sum(D.unitPrice*C.passnum) as number(18,2)) totalPrice,cast(sum(D.qualityFine) as number(18,2)) qualityFine " +
	    		 "from outassistmenu A " +
	    		 "left join outAssistCom B on B.companyId=A.waixiecom " +
	    		 "left join processesPlan C on C.menuId=A.menuId " +
	    		 "left join outAssistStat D on C.orderId=D.orderId and C.productId=D.productId and C.issueNum=D.issueNum and C.operId=D.operId " +
	    		 "group by A.menuId,A.deliverTime,A.waixiecom,B.companyName,D.payTime " +
	    		 "order by A.deliverTime desc)E ) ) "+
	             "where rn<="+(countPerPage*pageNo)+" and rn>="+(((pageNo-1)*countPerPage+1));
//	    String AllSql="select menuId,deliverTime,waixiecom,payTime,companyName,cast(theorytotalPrice as number(18,2)) theorytotalPrice,cast(totalPrice as number(18,2)) totalPrice,cast(qualityFine as number(18,2)) qualityFine,row_num " +
//	    		 "from (select E.*,rowNum row_num from (select A.menuId,to_char(A.deliverTime,'yyyy-mm-dd') deliverTime,A.waixiecom,B.companyName," +
//	    		 "to_char(D.payTime,'yyyy-mm-dd') payTime,cast(sum(D.totalPrice) as number(18,2)) theorytotalPrice,cast(sum(D.unitPrice*C.passnum) as number(18,2)) totalPrice,cast(sum(D.qualityFine) as number(18,2)) qualityFine " +
//	    		 "from outassistmenu A " +
//	    		 "left join outAssistCom B on B.companyId=A.waixiecom " +
//	    		 "left join processesPlan C on C.menuId=A.menuId " +
//	    		 "left join outAssistStat D on C.orderId=D.orderId and C.productId=D.productId and C.issueNum=D.issueNum and C.operId=D.operId " +
//	    		 "group by A.menuId,A.deliverTime,A.waixiecom,B.companyName,D.payTime " +
//	    		 "order by A.deliverTime desc)E ) ";
	            
	    List<ProcessesPlan> outAssistMenu =new ArrayList<ProcessesPlan>();
//	    List<ProcessesPlan> allOutAssistMenu=new ArrayList<ProcessesPlan>();
	    String[] Params=null;
	    try{
	    	outAssistMenu=Sqlhelper.exeQueryList(Sql, Params, ProcessesPlan.class);
//	    	allOutAssistMenu=Sqlhelper.exeQueryList(AllSql, Params,ProcessesPlan.class);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    double totalPrice=0;
	    double qualityFine=0;
	    double shouldPay=0;
//	    for(int i=0;i<outAssistMenu.size();i++){
//	    	totalPrice+=allOutAssistMenu.get(i).getTotalPrice();
//	    	qualityFine+=allOutAssistMenu.get(i).getQualityFine();
//	    }
//	    shouldPay=totalPrice-qualityFine;
	    
	    String json = PluSoft.Utils.JSON.Encode(outAssistMenu);
   	    json = "{\"total\":"+totalCount+",\"data\":"+json+",\"totalPrice\":"+totalPrice+",\"qualityFine\":"+qualityFine+",\"shouldPay\":"+shouldPay+"}";
   		response.setCharacterEncoding("UTF-8");
   		response.getWriter().append(json).flush();
   		System.out.println(json);		 
	    		   

	}



}
