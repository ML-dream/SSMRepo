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
import com.wl.forms.ProcessesPlan;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;
import com.wl.tools.UUIDHexGenerator;

public class OutAssistMenuServlet extends HttpServlet {
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
        String menuId=ChineseCode.toUTF8(request.getParameter("menuId"));
        List<ProcessesPlan> outassistmenu=new ArrayList<ProcessesPlan>();

        String sql="select A.orderId,A.productId,A.issueNum,A.operId,A.num,A.menuId,to_char(F.deliverTime,'yyyy-mm-dd') planStartTime," +
      		"to_char(F.planEndTime,'yyyy-mm-dd') planEndTime,F.UNITPRICE,F.TOTALPRICE,F.MEMO,F.qualityFine," +
      		"B.companyName,C.product_name productName,E.FO_OPNAME OPERNAME,A.waiXieCom,C.unitprice productPrice " +
      		"from processesplan A " +
      		"left join outAssistCom B ON A.waixieCom=B.companyId " +
      		"left join order_detail C on A.orderId=C.order_Id and A.productId=C.product_Id and A.issueNum=C.issue_Num "+
	    	"left join foheader D on D.ORDERID=A.ORDERID AND D.PRODUCTID=A.PRODUCTID AND D.ISSUENUM=A.ISSUENUM "+
	    	"left join fo_detail E on D.productID=E.PRODUCT_ID AND D.FOID=E.FOID and E.fo_no =A.OPERID " +
	    	"left join outAssistStat F ON A.orderId=F.orderId and A.productId=F.productId and A.issueNum=F.issueNum and A.operId=F.operId "+
	    	"where A.menuId=? and E.isinuse='1' " +
	    	"order by A.operId";
        	String[] params={menuId}; 
              try{
            	  
            	 outassistmenu=Sqlhelper.exeQueryList(sql, params, ProcessesPlan.class);
                }catch(Exception e){
                       e.printStackTrace();
                       }
              
         
   
	    String json = PluSoft.Utils.JSON.Encode(outassistmenu);
		json = "{\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	}

}
