package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.OutAssistPayTotal;
import com.wl.forms.OutAssistPrimary;
import com.wl.forms.ProcessesPlan;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class SelectedOutAssistPayListServlet extends HttpServlet {


	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = null;
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    String companyId=ChineseCode.toUTF8(request.getParameter("companyId"));
	    String startDate=ChineseCode.toUTF8(request.getParameter("startDate"));
	    String endDate=ChineseCode.toUTF8(request.getParameter("endDate"));
	    String countSql="select count(*) from  Outassistprimary where  companyId=?";
	    String primaryDataSql="select payNum,to_char(primaryTime,'yyyy-mm-dd') primaryTime,max(modifyTime) modifyTime from Outassistprimary where  companyId=? group by paynum,primaryTime";
	    OutAssistPrimary outassistprimary =new OutAssistPrimary();
	    String sql=null;
	    String Sql=null;
	    String allSql=null;
	    String[] Params={companyId};
        int count=0;
	     try{
	    	 count=Sqlhelper.exeQueryCountNum(countSql, Params);
	     }catch(Exception e){
	    	 e.printStackTrace();
	     }
          
	     if(count==0){
	    	 outassistprimary.setPayNum("0");
	    	 outassistprimary.setPrimaryTime("2017-04-01");
	     }
	     else{
	    try{
           outassistprimary=Sqlhelper.exeQueryBean(primaryDataSql, Params, OutAssistPrimary.class);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
    	 }
	     

	     List<ProcessesPlan> outAssistMenu =new ArrayList<ProcessesPlan>();
		 List<ProcessesPlan> allOutAssistMenu=new ArrayList<ProcessesPlan>();
		 double totalPrice=0;
		 double qualityFine=0;
		 double shouldPay=0;
		 double alreadyPay=0;

	    
	    if(startDate.equals("")&&endDate.equals("")){
	    	System.out.println("1");
		     OutAssistPayTotal total=new OutAssistPayTotal();
		     String[] params={companyId,outassistprimary.getPrimaryTime()};
	    	
	      sql="select count(*) from outAssistMenu where waixieCom=?";
		  Sql="select menuId,deliverTime,waixiecom,companyName,cast(totalPrice as number(18,2)) totalPrice,cast(theorytotalPrice as number(18,2)) theorytotalPrice,cast(qualityFine as number(18,2)) qualityFine from " +
		   		"(select menuId,deliverTime,waixiecom,companyName,cast(totalPrice as number(18,2)) totalPrice,cast(theorytotalPrice as number(18,2)) theorytotalPrice,cast(qualityFine as number(18,2)) qualityFine,rownum rn " +
		    		 "from (select E.*,rowNum row_num from (select A.menuId,to_char(A.deliverTime,'yyyy-mm-dd') deliverTime,A.waixiecom,B.companyName,to_char(D.payTime,'yyyy-mm-dd') payTime,cast(sum(D.unitPrice*C.passnum) as number(18,2)) totalPrice,cast(sum(D.totalPrice) as number(18,2)) theorytotalPrice,cast(sum(D.qualityFine) as number(18,2)) qualityFine " +
		    		 "from outassistmenu A " +
		    		 "left join outAssistCom B on B.companyId=A.waixiecom " +
		    		 "left join processesPlan C on C.menuId=A.menuId " +
		    		 "left join outAssistStat D on C.orderId=D.orderId and C.productId=D.productId and C.issueNum=D.issueNum and C.operId=D.operId " +
		    		 "where A.waiXieCom=? " +
		    		 "group by A.menuId,A.deliverTime,A.waixiecom,B.companyName,D.payTime " +
		    		 "order by A.deliverTime desc)E ) ) "+
		             "where rn<="+(countPerPage*pageNo)+" and rn>="+(((pageNo-1)*countPerPage+1));
		  
//		   allSql="select menuId,deliverTime,waixiecom,companyName,cast(totalPrice as number(18,2)) totalPrice,cast(qualityFine as number(18,2)) qualityFine,row_num " +
//		    		 "from (select E.*,rowNum row_num from (select A.menuId,to_char(A.deliverTime,'yyyy-mm-dd') deliverTime,A.waixiecom,B.companyName,to_char(D.payTime,'yyyy-mm-dd') payTime,cast(sum(D.unitPrice*C.passnum) as number(18,2)) totalPrice,cast(sum(D.qualityFine) as number(18,2)) qualityFine " +
//		    		 "from outassistmenu A " +
//		    		 "left join outAssistCom B on B.companyId=A.waixiecom " +
//		    		 "left join processesPlan C on C.menuId=A.menuId " +
//		    		 "left join outAssistStat D on C.orderId=D.orderId and C.productId=D.productId and C.issueNum=D.issueNum and C.operId=D.operId " +
//		    		 "where A.waiXieCom=? " +
//		    		 "group by A.menuId,A.deliverTime,A.waixiecom,B.companyName,D.payTime " +
//		    		 "order by A.deliverTime desc)E ) ";
		   
		   allSql="select menuId,deliverTime,waixiecom,companyName,cast(totalPrice as number(18,2)) totalPrice,cast(qualityFine as number(18,2)) qualityFine,row_num " +
		    		 "from (select E.*,rowNum row_num from (select A.menuId,to_char(A.deliverTime,'yyyy-mm-dd') deliverTime,A.waixiecom,B.companyName,to_char(D.payTime,'yyyy-mm-dd') payTime,cast(sum(D.unitPrice*C.passnum) as number(18,2)) totalPrice,cast(sum(D.qualityFine) as number(18,2)) qualityFine " +
		    		 "from outassistmenu A " +
		    		 "left join outAssistCom B on B.companyId=A.waixiecom " +
		    		 "left join processesPlan C on C.menuId=A.menuId " +
		    		 "left join outAssistStat D on C.orderId=D.orderId and C.productId=D.productId and C.issueNum=D.issueNum and C.operId=D.operId " +
		    		 "where A.waiXieCom=? and A.deliverTime >to_date('"+outassistprimary.getPrimaryTime()+"','yyyy-mm-dd,hh24:mi:ss')  "+
		    		 "group by A.menuId,A.deliverTime,A.waixiecom,B.companyName,D.payTime " +
		    		 "order by A.deliverTime desc)E ) "; 
		  
	 	String alreadyPaySql="select cast(sum(thispay) as number(18,2)) totalPay,cast(sum(qualityFine) as number(18,2)) totalQualityFine from outAssistPay where companyId=? and paydate>to_date(?,'yyyy-mm-dd') ";
		   

		    
		    try{
		    	 totalCount=Sqlhelper.exeQueryCountNum(sql, Params);
		    	outAssistMenu=Sqlhelper.exeQueryList(Sql, Params, ProcessesPlan.class);
		    	allOutAssistMenu=Sqlhelper.exeQueryList(allSql, Params, ProcessesPlan.class);
		    	total=Sqlhelper.exeQueryBean(alreadyPaySql, params, OutAssistPayTotal.class);
		    }catch(Exception e){
		    	e.printStackTrace();
		    }

		    for(int i=0;i<allOutAssistMenu.size();i++){
		    	totalPrice+=allOutAssistMenu.get(i).getTotalPrice();
		    	qualityFine+=allOutAssistMenu.get(i).getQualityFine();
		    }
		    totalPrice+=Double.parseDouble(outassistprimary.getPayNum());
		    alreadyPay=total.getTotalPay();
		    shouldPay=totalPrice-alreadyPay-qualityFine;
	   
	  }else{
		    System.out.println("2");
		     String[] params1={companyId,startDate,endDate};
		     String[] params2={companyId,outassistprimary.getPrimaryTime(),endDate};

		     OutAssistPayTotal total1=new OutAssistPayTotal();
		     OutAssistPayTotal total2=new OutAssistPayTotal();
			
		     List<ProcessesPlan> primaryOutAssistMenu=new ArrayList<ProcessesPlan>();
	    	
	    	 sql="select count(*) from outAssistMenu where waixieCom=? and deliverTime between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') " +
					"and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss')";	 	
			 Sql="select menuId,deliverTime,waixiecom,companyName,cast(totalPrice as number(18,2)) totalPrice,cast(theorytotalPrice as number(18,2)) theorytotalPrice,cast(qualityFine as number(18,2)) qualityFine from " +
			   		"(select menuId,deliverTime,waixiecom,companyName,cast(totalPrice as number(18,2)) totalPrice,cast(theorytotalPrice as number(18,2)) theorytotalPrice,cast(qualityFine as number(18,2)) qualityFine,rownum rn " +
			    		 "from (select E.*,rowNum row_num from (select A.menuId,to_char(A.deliverTime,'yyyy-mm-dd') deliverTime,A.waixiecom,B.companyName,to_char(D.payTime,'yyyy-mm-dd') payTime,cast(sum(D.unitPrice*C.passnum) as number(18,2)) totalPrice,cast(sum(D.totalPrice) as number(18,2)) theorytotalPrice,cast(sum(D.qualityFine) as number(18,2)) qualityFine " +
			    		 "from outassistmenu A " +
			    		 "left join outAssistCom B on B.companyId=A.waixiecom " +
			    		 "left join processesPlan C on C.menuId=A.menuId " +
			    		 "left join outAssistStat D on C.orderId=D.orderId and C.productId=D.productId and C.issueNum=D.issueNum and C.operId=D.operId " +
			    		 "where A.waiXieCom=? and A.deliverTime between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss') "+
			    		 "group by A.menuId,A.deliverTime,A.waixiecom,B.companyName,D.payTime " +
			    		 "order by A.deliverTime desc)E ) ) "+
			             "where rn<="+(countPerPage*pageNo)+" and rn>="+(((pageNo-1)*countPerPage+1));
 
			 allSql="select menuId,deliverTime,waixiecom,companyName,cast(totalPrice as number(18,2)) totalPrice,cast(qualityFine as number(18,2)) qualityFine,row_num " +
			    		 "from (select E.*,rowNum row_num from (select A.menuId,to_char(A.deliverTime,'yyyy-mm-dd') deliverTime,A.waixiecom,B.companyName,to_char(D.payTime,'yyyy-mm-dd') payTime,cast(sum(D.unitPrice*C.passnum) as number(18,2)) totalPrice,cast(sum(D.qualityFine) as number(18,2)) qualityFine " +
			    		 "from outassistmenu A " +
			    		 "left join outAssistCom B on B.companyId=A.waixiecom " +
			    		 "left join processesPlan C on C.menuId=A.menuId " +
			    		 "left join outAssistStat D on C.orderId=D.orderId and C.productId=D.productId and C.issueNum=D.issueNum and C.operId=D.operId " +
			    		 "where A.waiXieCom=? and A.deliverTime between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss') "+
			    		 "group by A.menuId,A.deliverTime,A.waixiecom,B.companyName,D.payTime " +
			    		 "order by A.deliverTime desc)E ) "; 
	
			 String primarySql="select menuId,deliverTime,waixiecom,companyName,cast(totalPrice as number(18,2)) totalPrice,cast(theorytotalPrice as number(18,2)) theorytotalPrice,cast(qualityFine as number(18,2)) qualityFine from " +
				   		"(select menuId,deliverTime,waixiecom,companyName,cast(totalPrice as number(18,2)) totalPrice,cast(theorytotalPrice as number(18,2)) theorytotalPrice,cast(qualityFine as number(18,2)) qualityFine,rownum rn " +
				    		 "from (select E.*,rowNum row_num from (select A.menuId,to_char(A.deliverTime,'yyyy-mm-dd') deliverTime,A.waixiecom,B.companyName,to_char(D.payTime,'yyyy-mm-dd') payTime,cast(sum(D.unitPrice*C.passnum) as number(18,2)) totalPrice,cast(sum(D.totalPrice) as number(18,2)) theorytotalPrice,cast(sum(D.qualityFine) as number(18,2)) qualityFine " +
				    		 "from outassistmenu A " +
				    		 "left join outAssistCom B on B.companyId=A.waixiecom " +
				    		 "left join processesPlan C on C.menuId=A.menuId " +
				    		 "left join outAssistStat D on C.orderId=D.orderId and C.productId=D.productId and C.issueNum=D.issueNum and C.operId=D.operId " +
				    		 "where A.waiXieCom=? and A.deliverTime between to_date('"+outassistprimary.getPrimaryTime()+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss') "+
				    		 "group by A.menuId,A.deliverTime,A.waixiecom,B.companyName,D.payTime " +
				    		 "order by A.deliverTime desc)E ) ) "+
				             "where rn<="+(countPerPage*pageNo)+" and rn>="+(((pageNo-1)*countPerPage+1)); 
			   
			   
			 String alreadyPaySql1="select cast(sum(thispay) as number(18,2)) totalPay,cast(sum(qualityFine) as number(18,2)) totalQualityFine from outAssistPay where companyId=? and paydate>to_date(?,'yyyy-mm-dd hh24:mi:ss') and paydate<to_date(?,'yyyy-mm-dd hh24:mi:ss') ";
			 String alreadyPaySql2="select cast(sum(thispay) as number(18,2)) totalPay,cast(sum(qualityFine) as number(18,2)) totalQualityFine from outAssistPay where companyId=? and paydate>to_date(?,'yyyy-mm-dd hh24:mi:ss') and paydate<to_date(?,'yyyy-mm-dd hh24:mi:ss') ";
			 
			  
			try{
			    	totalCount=Sqlhelper.exeQueryCountNum(sql, Params);
			    	outAssistMenu=Sqlhelper.exeQueryList(Sql, Params, ProcessesPlan.class);	
			    	allOutAssistMenu=Sqlhelper.exeQueryList(allSql, Params, ProcessesPlan.class);
			    	primaryOutAssistMenu=Sqlhelper.exeQueryList(primarySql, Params, ProcessesPlan.class);
			    	total1=Sqlhelper.exeQueryBean(alreadyPaySql1, params1, OutAssistPayTotal.class);
			    	total2=Sqlhelper.exeQueryBean(alreadyPaySql2, params1, OutAssistPayTotal.class);
			    }catch(Exception e){
			    	e.printStackTrace();
			    }

			    for(int j=0;j<allOutAssistMenu.size();j++){
			    	totalPrice+=allOutAssistMenu.get(j).getTotalPrice();
			    	qualityFine+=allOutAssistMenu.get(j).getQualityFine();
			    }
			    
			    double primaryTotalPrice=0;
			    for(int k=0;k<primaryOutAssistMenu.size();k++){
			        primaryTotalPrice=primaryOutAssistMenu.get(k).getTotalPrice();
			    }  
			    alreadyPay=total1.getTotalPay();
			    shouldPay=Double.parseDouble(outassistprimary.getPayNum())+primaryTotalPrice-total2.getTotalPay()-qualityFine;
			   
			   

	    }

	    
	
	    
	    String json = PluSoft.Utils.JSON.Encode(outAssistMenu);
   	    json = "{\"total\":"+totalCount+",\"data\":"+json+",\"totalPrice\":"+totalPrice+",\"qualityFine\":"+qualityFine+",\"alreadyPay\":"+alreadyPay+",\"shouldPay\":"+shouldPay+"}";
   		response.setCharacterEncoding("UTF-8");
   		response.getWriter().append(json).flush();
   		System.out.println(json);		 
	    		   

	}


}
