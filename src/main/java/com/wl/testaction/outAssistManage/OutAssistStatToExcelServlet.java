package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ProcessesPlan;
import com.wl.tools.ExportExcelUtil;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class OutAssistStatToExcelServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	    
		
		String AllOutAssistStatSql="select D.* from (select distinct A.orderId,A.ProductId,A.issueNum,B.waiXieCom,C.ORDER_DATE deliverTime " +
                                    "from outAssistStat A " +
                                    "left join ProcessesPlan B on B.orderId=A.orderId and B.productId=A.productId and B.issueNum =A.issueNum and A.operId=B.operId " +
                                    "left join orders C on C.order_Id=A.orderId ";
				               
		if(bday.isEmpty()||eday.isEmpty()){
			AllOutAssistStatSql+=" where 1=1 ";
			
		}
		else
		{
			AllOutAssistStatSql+=" where C.order_date between to_date('"+bday+"','yyyy-mm-dd HH:MI:SS') and to_date('"+eday+"','yyyy-mm-dd HH:MI:SS') ";
		}
		if(!waixiecom.isEmpty()){
			AllOutAssistStatSql+=" and B.waixiecom ='"+waixiecom+"' ";
		}
		if(!orderId.isEmpty()){
			AllOutAssistStatSql+=" and A.orderId='"+orderId+"'";
		}
		if(!productId.isEmpty()){
			AllOutAssistStatSql+=" and A.productId='"+productId+"'";
		}
		AllOutAssistStatSql+=" order by C.order_date)D ";
		
		List<ProcessesPlan> AllOutAssistStatList=new ArrayList<ProcessesPlan>();
	    String[] OutAssistStatParams=null;
	    try{
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
        

        List<ProcessesPlan> AllOutAssistStatComb =new ArrayList<ProcessesPlan>();
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
        for(int i=0;i<AllOutAssistStatComb.size();i++){
          ProcessesPlan checkOpenTicket=AllOutAssistStatComb.get(i);
          if(checkOpenTicket.getIsOpenTicket()==null){
        	  checkOpenTicket.setIsOpenTicket("");}
          else if(checkOpenTicket.getIsOpenTicket().equals("0")){
        	  checkOpenTicket.setIsOpenTicket("否");
               }
              else{
        	       checkOpenTicket.setIsOpenTicket("是"); 
                   }
        }
        
        for(int j=0;j<AllOutAssistStatComb.size();j++){
            ProcessesPlan checkBusy=AllOutAssistStatComb.get(j);
            if(checkBusy.getIsBusy()==null){
            	checkBusy.setIsBusy("");
            }
            else if(checkBusy.getIsBusy().equals("0")){
            	checkBusy.setIsBusy("否");
            }
            else{
            	checkBusy.setIsBusy("是"); 
            }
          }
        
        for(int p=0;p<AllOutAssistStatComb.size();p++){
            ProcessesPlan checkPayTime=AllOutAssistStatComb.get(p);
            if(checkPayTime.getPayTime()==null){
            	checkPayTime.setPayTime("");
            }
          }
        
        for(int q=0;q<AllOutAssistStatComb.size();q++){
            ProcessesPlan checkMemo=AllOutAssistStatComb.get(q);
            if(checkMemo.getMemo()==null){
            	checkMemo.setMemo("");
            }
          }
        for(int s=0;s<AllOutAssistStatComb.size();s++){
            ProcessesPlan checkActuallyEndTime=AllOutAssistStatComb.get(s);
            if(checkActuallyEndTime.getActuallyEndTime()==null){
            	checkActuallyEndTime.setActuallyEndTime("");
            }
          }
 
        
		LinkedHashMap<String, String> liebiaoxiang = new LinkedHashMap<String, String>();
		liebiaoxiang.put("orderId", "订单号");
		liebiaoxiang.put("companyName", "单位名称");
		liebiaoxiang.put("productId", "图号");
		liebiaoxiang.put("productName", "产品名称");
		liebiaoxiang.put("deliverTime", "下达时间");
		liebiaoxiang.put("planEndTime", "计划完成时间");
		liebiaoxiang.put("actuallyEndTime", "实际完成时间");
		liebiaoxiang.put("operName", "工序名称"); 
		liebiaoxiang.put("unitPrice", "单价");
		liebiaoxiang.put("num", "计划数量");
		liebiaoxiang.put("passNum", "实际数量");
		liebiaoxiang.put("theoryTotalPrice", "计划总价");
		liebiaoxiang.put("totalPrice", "实际总价");
		liebiaoxiang.put("qualityFine", "质量罚款");
		liebiaoxiang.put("isOpenTicket", "是否开票");
		liebiaoxiang.put("payTime", "付款周期");  
		liebiaoxiang.put("isBusy", "是否急件");
		liebiaoxiang.put("memo", "备注"); 
		
		List<Integer> columnWidth = new ArrayList<Integer>();
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500); 
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);

		
		ExportExcelUtil.exportExcel(request, response, liebiaoxiang, columnWidth, AllOutAssistStatComb);
		
        
	}


}
