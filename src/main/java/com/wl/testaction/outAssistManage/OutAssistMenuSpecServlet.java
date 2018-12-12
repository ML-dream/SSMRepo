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

public class OutAssistMenuSpecServlet extends HttpServlet {
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
        String Sql="select count(*) from processesPlan where menuId=?";
        String[] Params={menuId};
        try{
        	totalCount=Sqlhelper.exeQueryCountNum(Sql, Params);
        }catch(Exception e){
        	e.printStackTrace();
        }
        List<ProcessesPlan> outassistmenu=new ArrayList<ProcessesPlan>();
        String sql="select H.*,cast(totalpric as number(18,2)) totalPrice from  (select G.*,cast(totalpri as number(18,2)) totalPric, rownum rn from (select A.orderId,A.productId,A.issueNum,A.operId,A.num,A.passnum,to_char(F.deliverTime,'yyyy-mm-dd') planStartTime," +
      		    "to_char(F.planEndTime,'yyyy-mm-dd') planEndTime,F.UNITPRICE,F.TOTALPRICE theoryTotalPrice,cast((A.passNum*F.unitPrice) as number(18,2)) totalPri,F.MEMO,F.qualityFine," +
      		    "B.companyName,C.product_name productName,E.FO_OPNAME OPERNAME,A.waiXieCom,C.unitprice productPrice,rownum row_num " +
      		    "from processesplan A " +
      		    "left join outAssistCom B ON A.waixieCom=B.companyId " +
      		    "left join order_detail C on A.orderId=C.order_Id and A.productId=C.product_Id and A.issueNum=C.issue_Num "+
	    		"left join foheader D on D.ORDERID=A.ORDERID AND D.PRODUCTID=A.PRODUCTID AND D.ISSUENUM=A.ISSUENUM "+
	    		"left join fo_detail E on D.productID=E.PRODUCT_ID AND D.FOID=E.FOID and E.fo_no =A.OPERID " +
	    		"left join outAssistStat F ON A.orderId=F.orderId and A.productId=F.productId and A.issueNum=F.issueNum and A.operId=F.operId "+
	    		"where A.menuId=? and E.isinuse='1' " +
	    		"order by A.ORDERID,A.productId,A.operId desc) G  )H " +
	    		"where rn<="+(countPerPage*pageNo)+" and rn>="+(((pageNo-1)*countPerPage+1));
        	String[] params={menuId}; 
              try{
            	  
            	 outassistmenu=Sqlhelper.exeQueryList(sql, params, ProcessesPlan.class);
                }catch(Exception e){
                       e.printStackTrace();
                       }
              
         
   
	    String json = PluSoft.Utils.JSON.Encode(outassistmenu);
	    json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	}

}
