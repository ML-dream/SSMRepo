package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.hql.ast.tree.IsNullLogicOperatorNode;

import com.wl.forms.CustomerItem;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class SearchAssetServlet extends HttpServlet {

	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String data=request.getParameter("submitData");
//		HashMap datamap=(HashMap) Test.JSON.Decode(data);
 		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String para =  StringUtil.isNullOrEmpty(request.getParameter("para"))?"":request.getParameter("para").trim();
		String orderid = StringUtil.isNullOrEmpty(request.getParameter("orderid"))?"":request.getParameter("orderid").trim();
		String customerId=StringUtil.isNullOrEmpty(request.getParameter("customerId"))?"":request.getParameter("customerId").trim();
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		String sql = "";
		List<CustomerItem> orderList = new ArrayList<CustomerItem>();
		try{
			if(para.equals("all")){	//查看所有待入库
				sql = "select A.mainid,A.orderid,A.customeId,A.itemId,A.itemname," +
					"itemspec,A.starttime,A.endtime," +
					"person,orderDetailId,itemType,itemNum," +
					"itemPrice,isCheckIn,D.text unit,customeName,B.connector,C.item_typeDesc itemTypeName,f.staff_name personName " +
					"from(select E.*,rownum row_num from(select EM.* from customeasset EM where checkinstatus='0' order by orderid) E where rownum<="+(pageSize*pageNow)+" order by orderid) A " +
					"left join orders B on B.order_id=A.orderId "+
					"left join itemtype C on C.item_typeId=A.itemType "+
					"left join priceunit D on D.id=A.unit "+
					" left join employee_info f on f.staff_code= a.person "+
					"where row_num>="+(pageSize*(pageNow-1))+"";
				orderList = Sqlhelper.exeQueryList(sql, null,CustomerItem.class);
				
			}else if(!orderid.equals("")){
			sql = "select A.mainid,A.orderid,A.customeId,A.itemId,A.itemname," +
				"itemspec,A.starttime,A.endtime," +
				"person,orderDetailId,itemType,itemNum," +
				"itemPrice,isCheckIn,D.text unit,customeName,B.connector,C.item_typeDesc itemTypeName,f.staff_name personName " +
				"from(select E.*,rownum row_num from(select EM.* from customeasset EM where orderid='"+orderid+"' and ischeckin='1' and checkinstatus='0' order by orderid) E where rownum<="+(pageSize*pageNow)+" order by orderid) A " +
				"left join orders B on B.order_id=A.orderId "+
				"left join itemtype C on C.item_typeId=A.itemType "+
				"left join priceunit D on D.id=A.unit "+
				" left join employee_info f on f.staff_code= a.person "+
				"where row_num>="+(pageSize*(pageNow-1))+"";
			orderList = Sqlhelper.exeQueryList(sql, null,CustomerItem.class);
		}else if(!customerId.equals("")){
			 sql = "select A.mainId,A.orderid,A.customeId,A.itemId,A.itemname," +
			 	"itemspec,A.starttime,A.endtime," +
			 	"person,orderDetailId,itemType,itemNum," +
			 	"itemPrice,isCheckIn,D.text unit,customeName,B.connector,C.item_typeDesc itemTypeName,f.staff_name personName " +
			 	"from(select E.*,rownum row_num from(select EM.* from customeasset EM where customeid='"+customerId+"' and ischeckin='1' and checkinstatus='0' order by orderid) E where rownum<="+(pageSize*pageNow)+" order by orderid) A " +
			 	"left join orders B on B.order_id=A.orderId "+
			 	"left join itemtype C on C.item_typeId=A.itemType "+
			 	"left join priceunit D on D.id=A.unit "+
			 	" left join employee_info f on f.staff_code= a.person "+
			 	"where row_num>="+(pageSize*(pageNow-1))+"";
			 orderList = Sqlhelper.exeQueryList(sql, null,CustomerItem.class);
			
		}
	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		String json = PluSoft.Utils.JSON.Encode(orderList);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	}

}
