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

public class OutAssistCompleteServlet extends HttpServlet {

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
	    String menuId=ChineseCode.toUTF8(request.getParameter("menuId"));
		String sql="select count(*) from ProcessesPlan where menuId=? and isCo='1' ";
		String[] params={menuId};
		   try{
			  totalCount=Sqlhelper.exeQueryCountNum(sql, params);
		   }catch(Exception e){
			   e.printStackTrace();
		   } 
      String Sql="select B.*,rowNum from (select A.orderId,A.productId,A.issueNum,A.operId,E.fo_opName operName,A.num,A.passnum from ProcessesPlan A " +
    		  "left join foheader D on D.ORDERID=A.ORDERID AND D.PRODUCTID=A.PRODUCTID AND D.ISSUENUM=A.ISSUENUM "+
	    	"left join fo_detail E on D.productID=E.PRODUCT_ID AND D.FOID=E.FOID and E.fo_no =A.OPERID " +
    		 "where A.menuId=? and E.isinuse='1' order by productId,operId)B where ROWNUM<="+(countPerPage*pageNo)+" and Rownum>="+((pageNo-1)*countPerPage+1);;
      String[] Params={menuId};
      List<ProcessesPlan> outAssist=new ArrayList<ProcessesPlan>();
      try{
    	  outAssist=Sqlhelper.exeQueryList(Sql, Params, ProcessesPlan.class);
      }catch(Exception e){
    	  e.printStackTrace();
      }
	    String json = PluSoft.Utils.JSON.Encode(outAssist);
	    json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	}

}
