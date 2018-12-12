package com.wl.tools;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.common.OrderStatus;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.xm.testaction.qualitycheck.statejudge.WaitJudgeBean;

/**
 * 导出Excel的sql语句
 * @author xiem
 *
 */
public class HelpExcelOut extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String conditonSql(HttpServletRequest request, HttpServletResponse response){
		String condition = "";
		
		
	    
	    String bday = "";
	    String eday = "";
	    bday = StringUtil.isNullOrEmpty(request.getParameter("bday"))?bday:request.getParameter("bday");
	    eday = StringUtil.isNullOrEmpty(request.getParameter("eday"))?eday:request.getParameter("eday");
	    String isExceed="1";
	    isExceed = StringUtil.isNullOrEmpty(request.getParameter("isExceed"))?isExceed:request.getParameter("isExceed");
	    
	    String creater = "";	//接收人
	    String status = "";  //订单状态
	    creater = StringUtil.isNullOrEmpty(request.getParameter("creater"))?creater:request.getParameter("creater");
	    status = StringUtil.isNullOrEmpty(request.getParameter("status"))?status:request.getParameter("status");
	    
	    
	    String customer ="";
	    customer = StringUtil.isNullOrEmpty(request.getParameter("customer"))?customer:request.getParameter("customer").trim();
	    
	    HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getStaffCode();
	    String workerType = ((User)session.getAttribute("user")).getWorkerType();
	    
//	    如果是销售经理,可以查看所有人的订单
	    boolean authority = false;
	    if(workerType.equals("saleManager")){
	    	authority = true;
	    }
	    
		String orderStatus = OrderStatus.PASS+"";
		condition = " where t.order_date between to_date('"+bday+"','yyyy-MM-ddHH:mi:ss') and to_date('"+eday+"','yyyy-MM-ddHH:mi:ss') and to_number(t.order_status) >= "+orderStatus;
		
		
		if(bday.isEmpty()||eday.isEmpty()){
			condition = " where 1=1 and to_number(t.order_status) >= "+orderStatus ;
		}
		
		String createrPara = userId;
//		String remind="0";
	    if(!creater.isEmpty()){
//	    	如果不是销售经理,而且不是订单创建人
	    	if(!authority && !userId.equals(creater)){
//	    		
//	    		String totalPrice = "0";
//	    		String json="[]";
//	    		json = "{\"mark\":1,\"remind\":\""+remind+"\",\"total\":"+totalCount+",\"orderPrice\":"+totalPrice+",\"data\":"+json+"}";
//	    		System.out.println(json);
	    		condition ="noEntry";
	    		return condition;
	    	}
	    	createrPara = creater;
	    	
	    }
	    condition += " and t.createperson ='"+createrPara+"' ";
	    if(!status.isEmpty()){
	    	condition += " and to_number(t.order_status)="+status;
	    }
	    if(!customer.isEmpty()){
	    	condition += " and t.customer='"+customer+"'";
	    }
	    
		return condition;
	}
	
	public static List<Order> productSaleOut (HttpServletRequest request, HttpServletResponse response){
		List<Order> orderList = new ArrayList<Order>();
		
		String conditon = conditonSql(request, response);
	    
		if(conditon.equals("noEntry")){
//			没有当前数据访问权限
			if(orderList ==null){
				String temp = "1";
				System.out.println("temp="+temp);
			}
			return null;
		}
		
		int pageNo=0;
	    int countPerPage=10;
//	    
	    String orderStr = "ORDER_DATE";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(StringUtil.isNullOrEmpty(request.getParameter("pageIndex"))?"0":request.getParameter("pageIndex"))+1;      
	    
	    String para = StringUtil.isNullOrEmpty(request.getParameter("para"))?"":request.getParameter("para");
	 
	    countPerPage = Integer.parseInt(StringUtil.isNullOrEmpty(request.getParameter("pageSize"))?"10":request.getParameter("pageSize"));    
	    
	    int max = pageNo*countPerPage;
	    int min = (pageNo-1)*countPerPage;
	    
	    String pageCut = "where rn<="+max+" and rn >="+min;
	    if(!para.isEmpty()){
//	    	导出execl
	    	pageCut = "";
	    }
//	    产品号与图号调换了位置
	    String OrderSql="select order_date,c.order_id orderId,c.drawingid productId,c.product_name productName,c.batch,c.unitprice,c.purduct_num productNum," +
	    		"to_char((c.unitprice*c.purduct_num)) productPrice,c.Productremark,to_char(c.backNum) backNum,to_char(c.backPrice) backPrice,product_id drawingid,backRemark,rn from " +
	    		"(select a.*,rownum rn from " +
	    		"        (select t.order_date,p.* from orders t" +
	    		"                left join order_detail p on  p.order_id=t.order_id " +conditon+
	    		"         order by t.order_date desc,t.order_id desc,p.drawingid desc ) a)c " +
	    			pageCut +" order by order_date desc,orderId desc,productId desc ";
	    
	    
	    try {
			orderList = Sqlhelper.exeQueryList(OrderSql, null, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return orderList;
	}
}
