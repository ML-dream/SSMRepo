package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ProcessesPlan;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class ProcessOutAssistStatServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    String orderId=ChineseCode.toUTF8(request.getParameter("orderId"));
    String productId=ChineseCode.toUTF8(request.getParameter("productId"));
    String issueNum=ChineseCode.toUTF8(request.getParameter("issueNum"));
    String operId=ChineseCode.toUTF8(request.getParameter("operId"));
//    String Sql="select count(*) from outAssistStat where orderId=? and productId=? and issueNum=? and operId=?";
      String[] params={orderId,productId,issueNum,operId};
//    int count=0;
//    String sql;
//    try{ 
//    	 count=Sqlhelper.exeQueryCountNum(Sql, params);
//    }catch(Exception e){
//    	e.printStackTrace();
//    }
//    if(count==1)
       String sql="select distinct to_char(G.deliverTime,'yyyy-mm-dd') deliverTime,A.orderId,A.productId,A.operId,A.issueNum,A.num," +
    		"to_char(G.planEndTime,'yyyy-mm-dd') planEndTime,A.waiXieCom,B.companyName,B.Connector,B.connectorTel,B.Address," +
    		"C.product_name productName,E.FO_OPNAME OPERNAME,E.workbranch,F.typeName,G.unitPrice,G.totalprice," +
    		"G.quality,G.Qualityfine,G.alreadyPay,G.waitpay,G.isopenticket,G.paytime,G.isBusy,G.memo,to_char(G.ACTUALLYEndTime,'yyyy-mm-dd') actuallyEndTime " +
    		"from processesplan A " +
    		"left join outAssistCom B ON A.waixieCom=B.companyId " +
    		"left join order_detail C on A.orderId=C.order_Id and A.productId=C.product_Id and A.issueNum=C.issue_Num "+
    		"left join foheader D on D.ORDERID=A.ORDERID AND D.PRODUCTID=A.PRODUCTID AND D.ISSUENUM=A.ISSUENUM "+
    		"left join fo_detail E on D.productID=E.PRODUCT_ID AND D.FOID=E.FOID and E.fo_no =A.OPERID " +
    		"left join workbranch F on F.typeId=E.workbranch " +
    		"left join outassiststat G ON A.orderId=G.orderID AND A.productId=G.PRODUCTID AND A.ISSUENum=G.issueNum and A.OPERID=G.operID "+
    		"left join outAssistmenu H ON H.menuId=A.MENUID "+
    		"where A.orderId=? and A.productId=? and A.issueNum=? and A.operId=? and A.isdiscard='0' and E.isinuse='1' ";
	 
    ProcessesPlan process=new ProcessesPlan();
    try{
    	process=Sqlhelper.exeQueryBean(sql, params, ProcessesPlan.class);
    	
    }catch(Exception e){
    	e.printStackTrace();
    }
    request.setAttribute("process", process);
    request.getRequestDispatcher("outAssistManage/processOutAssistStat2.jsp").forward(request,response);
	}

}
