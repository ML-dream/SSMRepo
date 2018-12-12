package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.wl.forms.Order;
import com.wl.forms.OrderTree;
import com.wl.forms.ProcessesPlan;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class OutAssistStatServlet1 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     doPost(request,response);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	    String bday = "";
	    String eday = "";
	    String waixiecom="";
	    String orderId="";
	    String productId="";
	    bday = StringUtil.isNullOrEmpty(request.getParameter("bday"))?bday:request.getParameter("bday");
	    eday = StringUtil.isNullOrEmpty(request.getParameter("eday"))?eday:request.getParameter("eday");
	    waixiecom = StringUtil.isNullOrEmpty(request.getParameter("waixiecom"))?waixiecom:request.getParameter("waixiecom");
	    orderId = StringUtil.isNullOrEmpty(request.getParameter("orderId"))?orderId:request.getParameter("orderId");
	    productId = StringUtil.isNullOrEmpty(request.getParameter("productId"))?productId:request.getParameter("productId");

	    String TotalCountSql="select count(*) from (select distinct A.orderId,A.ProductId,A.issueNum,B.waiXieCom,C.ORDER_DATE deliverTime " +
                             "from outAssistStat A " +
                             "left join ProcessesPlan B on B.orderId=A.orderId and B.productId=A.productId and B.issueNum =A.issueNum and A.operId=B.operId " +
                             "left join orders C on C.order_Id=A.orderId ";

	    
	    
		String OutAssistStatSql="select F.* from (select E.*,rownum rn  from (select D.*,rownum rw from (select distinct A.orderId,A.ProductId,A.issueNum,B.waiXieCom,C.ORDER_DATE deliverTime " +
				                "from outAssistStat A " +
				                "left join ProcessesPlan B on B.orderId=A.orderId and B.productId=A.productId and B.issueNum =A.issueNum and A.operId=B.operId " +
				                "left join orders C on C.order_Id=A.orderId ";
		
		String AllOutAssistStatSql="select D.* from (select distinct A.orderId,A.ProductId,A.issueNum,B.waiXieCom,C.ORDER_DATE deliverTime " +
                                    "from outAssistStat A " +
                                    "left join ProcessesPlan B on B.orderId=A.orderId and B.productId=A.productId and B.issueNum =A.issueNum and A.operId=B.operId " +
                                    "left join orders C on C.order_Id=A.orderId ";
				               
		if(bday.isEmpty()||eday.isEmpty()){
			OutAssistStatSql+= " where 1=1 ";
			TotalCountSql+=" where 1=1 ";
			AllOutAssistStatSql+=" where 1=1 ";
			
		}
		else
		{
			OutAssistStatSql+=" where C.order_date between to_date('"+bday+"','yyyy-mm-dd HH:MI:SS') and to_date('"+eday+"','yyyy-mm-dd HH:MI:SS') ";
			TotalCountSql+=" where C.order_date between to_date('"+bday+"','yyyy-mm-dd HH:MI:SS') and to_date('"+eday+"','yyyy-mm-dd HH:MI:SS') ";
			AllOutAssistStatSql+=" where C.order_date between to_date('"+bday+"','yyyy-mm-dd HH:MI:SS') and to_date('"+eday+"','yyyy-mm-dd HH:MI:SS') ";
		}
		if(!waixiecom.isEmpty()){
			OutAssistStatSql += " and B.waixiecom ='"+waixiecom+"' ";
			TotalCountSql += " and B.waixiecom ='"+waixiecom+"' ";
			AllOutAssistStatSql+=" and B.waixiecom ='"+waixiecom+"' ";
		}
		if(!orderId.isEmpty()){
			OutAssistStatSql += " and A.orderId='"+orderId+"'";
			TotalCountSql += " and A.orderId='"+orderId+"'";
			AllOutAssistStatSql+=" and A.orderId='"+orderId+"'";
		}
		if(!productId.isEmpty()){
			OutAssistStatSql += " and A.productId='"+productId+"'";
			TotalCountSql += " and A.productId='"+productId+"'";
			AllOutAssistStatSql+=" and A.productId='"+productId+"'";
		}
		TotalCountSql+= " order by C.order_date) ";
		OutAssistStatSql+= " order by C.order_date) D) E )F " +" where rn<="+(countPerPage*pageNo)+" and rn>="+(((pageNo-1)*countPerPage+1));
		AllOutAssistStatSql+=" order by C.order_date)D ";
		
		List<ProcessesPlan> OutAssistStatList= new ArrayList<ProcessesPlan>();
		List<ProcessesPlan> AllOutAssistStatList=new ArrayList<ProcessesPlan>();
	    String[] OutAssistStatParams=null;
	    try{
	        OutAssistStatList=Sqlhelper.exeQueryList(OutAssistStatSql, OutAssistStatParams, ProcessesPlan.class);
	        totalCount=Sqlhelper.exeQueryCountNum(TotalCountSql, OutAssistStatParams);
	        AllOutAssistStatList=Sqlhelper.exeQueryList(AllOutAssistStatSql, OutAssistStatParams,ProcessesPlan.class);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        String sql="select A.orderId,A.productId,A.issueNum,A.operId,to_char(deliverTime,'yyyy-mm-dd') deliverTime, " +
        		"to_char(A.planEndTime,'yyyy-mm-dd') planEndTime,to_char(A.ActuallyEndTime,'yyyy-mm-dd') ActuallyEndTime," +
        		"A.unitPrice,A.quality,A.qualityFine,A.isopenTicket,A.payTime,A.isBusy,A.memo,A.totalPrice theoryTotalPrice,B.num,B.passNum,cast((A.unitPrice*B.passNUM) as number(18,2)) totalPrice,C.companyName,C.connector,C.telephone fax, " +
        		"c.connectorTel,c.Address,D.product_name productName,F.FO_OPNAME OPERNAME,F.workbranch,G.typeName "+
        		"from outAssistStat A " +
        		"left join ProcessesPlan B ON B.orderId=A.orderId and B.productId=A.productId and B.issueNum=A.issueNum and B.operId=A.operId "+
        		"left join outAssistCom C on B.waixiecom=C.companyId "+
        		"left join order_detail D on A.orderId=D.order_Id and A.productId=D.product_Id and A.issueNum=D.issue_Num "+
        		"left join foheader E on E.ORDERID=A.ORDERID AND E.PRODUCTID=A.PRODUCTID AND E.ISSUENUM=A.ISSUENUM "+
        		"left join fo_detail F on E.productID=F.PRODUCT_ID AND F.FOID=E.FOID and F.fo_no =A.OPERID " +
        		"left join workbranch G on G.typeId=F.workbranch " +
	    		"where A.orderId=? and A.PRODUCTId=? and A.issueNum=? and B.waiXieCom=? and F.isinuse='1' " +
	    		"order by A.orderId,A.productId,A.issueNum,A.operId";
        
        List<ProcessesPlan> OutAssistStatComb =new ArrayList<ProcessesPlan>();
        List<ProcessesPlan> AllOutAssistStatComb =new ArrayList<ProcessesPlan>();
        for(int i=0;i<OutAssistStatList.size();i++)
        {
          String[] params={OutAssistStatList.get(i).getOrderId(),OutAssistStatList.get(i).getProductId(),OutAssistStatList.get(i).getIssueNum(),OutAssistStatList.get(i).getWaiXieCom()}; 
          List <ProcessesPlan>  processlist=new ArrayList<ProcessesPlan>();
          try{
        	processlist=Sqlhelper.exeQueryList(sql, params, ProcessesPlan.class);
            }catch(Exception e){
            	e.printStackTrace();
            }
          ProcessesPlan OutAssistStat=processlist.get(0);
          for(int j=1;j<processlist.size();j++){
            ProcessesPlan Process=processlist.get(j);
            OutAssistStat.setOperName(OutAssistStat.getOperName()+"、"+Process.getOperName());
            OutAssistStat.setUnitPrice(OutAssistStat.getUnitPrice()+Process.getUnitPrice());
            OutAssistStat.setTotalPrice(OutAssistStat.getTotalPrice()+Process.getTotalPrice());
            OutAssistStat.setQualityFine(OutAssistStat.getQualityFine()+Process.getQualityFine());
           } 
          
          OutAssistStatComb.add(OutAssistStat); 
        }
        
        for(int k=0;k<AllOutAssistStatList.size();k++)
        {
          String[] params={AllOutAssistStatList.get(k).getOrderId(),AllOutAssistStatList.get(k).getProductId(),AllOutAssistStatList.get(k).getIssueNum(),AllOutAssistStatList.get(k).getWaiXieCom()}; 
          List <ProcessesPlan>  allprocesslist=new ArrayList<ProcessesPlan>();
          try{
        	allprocesslist=Sqlhelper.exeQueryList(sql, params, ProcessesPlan.class);
            }catch(Exception e){
            	e.printStackTrace();
            }
          ProcessesPlan OutAssistStat=allprocesslist.get(0);
          for(int l=1;l<allprocesslist.size();l++){
            ProcessesPlan Process=allprocesslist.get(l);
            OutAssistStat.setOperName(OutAssistStat.getOperName()+"、"+Process.getOperName());
            OutAssistStat.setUnitPrice(OutAssistStat.getUnitPrice()+Process.getUnitPrice());
            OutAssistStat.setTotalPrice(OutAssistStat.getTotalPrice()+Process.getTotalPrice());
            OutAssistStat.setQualityFine(OutAssistStat.getQualityFine()+Process.getQualityFine());
           } 
          
          AllOutAssistStatComb.add(OutAssistStat);  
        }
        
        
        double totalPay=0;
        double qualityFine=0;
        int number=0;;

         for(int m=0;m<AllOutAssistStatComb.size();m++){
          totalPay+=AllOutAssistStatComb.get(m).getTotalPrice();
          qualityFine+=AllOutAssistStatComb.get(m).getQualityFine();
          number+=AllOutAssistStatComb.get(m).getPassNum();
         }

        String json = PluSoft.Utils.JSON.Encode(OutAssistStatComb);
        json = "{\"total\":"+totalCount+",\"data\":"+json+",\"totalPay\":"+totalPay+",\"qualityFine\":"+qualityFine+",\"number\":"+number+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	}


}
