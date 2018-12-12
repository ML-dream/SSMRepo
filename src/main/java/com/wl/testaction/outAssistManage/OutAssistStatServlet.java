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
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class OutAssistStatServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        String orderId=ChineseCode.toUTF8(request.getParameter("orderId"));
        String waiXieCom=ChineseCode.toUTF8(request.getParameter("waiXieCom"));
        String sql="select distinct to_char(sysdate,'yyyy-mm-dd') currentTime,A.orderId,A.productId,A.operId,A.issueNum,A.num,to_char(A.planStartTime,'yyyy-mm-dd') planStartTime," +
        		"to_char(A.planEndTime,'yyyy-mm-dd') planEndTime,A.waiXieCom,B.companyName,B.Connector,B.connectorTel,B.Address," +
        		"C.product_name productName,E.FO_OPNAME OPERNAME,E.workbranch,F.typeName,A.unitPrice,A.unitPrice*A.num totalprice " +
        		"from processesplan A " +
        		"left join outAssistCom B ON A.waixieCom=B.companyId " +
        		"left join order_detail C on A.orderId=C.order_Id and A.productId=C.product_Id and A.issueNum=C.issue_Num "+
	    		"left join foheader D on D.ORDERID=A.ORDERID AND D.PRODUCTID=A.PRODUCTID AND D.ISSUENUM=A.ISSUENUM "+
	    		"left join fo_detail E on D.productID=E.PRODUCT_ID AND D.FOID=E.FOID and E.fo_no =A.OPERID " +
	    		"left join workbranch F on F.typeId=E.workbranch " +
	    		"where A.orderId=? and A.waiXieCom=? and E.isinuse='1' " +
	    		"order by A.productId";
        String[] params={orderId,waiXieCom};
        List <ProcessesPlan>  processesplanlist=new ArrayList<ProcessesPlan>();
        try{
        	processesplanlist=Sqlhelper.exeQueryList(sql, params, ProcessesPlan.class);
        }catch(Exception e){
         e.printStackTrace();
        }
        
	    String json = PluSoft.Utils.JSON.Encode(processesplanlist);
		json = "{\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	}


}
