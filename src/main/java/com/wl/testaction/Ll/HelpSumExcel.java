package com.wl.testaction.Ll;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.common.OrderStatus;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class HelpSumExcel {
	public static String conditonSql(HttpServletRequest request, HttpServletResponse response){
		String condition = "";
		
		
	    
	    String bday = "";
	    String eday = "";
	    bday = StringUtil.isNullOrEmpty(request.getParameter("bdate"))?bday:request.getParameter("bdate");
	    eday = StringUtil.isNullOrEmpty(request.getParameter("edate"))?eday:request.getParameter("edate");
	    
	    String itemType ="";
	    itemType = StringUtil.isNullOrEmpty(request.getParameter("itemType"))?itemType:request.getParameter("itemType").trim();
	    
	    HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getStaffCode();
	    
		if(bday.isEmpty()||eday.isEmpty()){
			condition = " where 1=1 ";
		}else{
			condition = " where a.lldate between to_date('"+bday+"','yyyy-MM-ddHH:mi:ss') and to_date('"+eday+"','yyyy-MM-ddHH:mi:ss')";
		}
		if(!itemType.isEmpty()){
			condition += " and t.item_type='"+itemType+"'";
		}
		
	    
		return condition;
	}
/*
 * 领料统计查询
 */
	public static List<BeanLingLiaoSum> lingLiaoOut (HttpServletRequest request, HttpServletResponse response){
		List<BeanLingLiaoSum> lingLiaoList = new ArrayList<BeanLingLiaoSum>();
		
		String conditon = conditonSql(request, response);
	    
		int pageNo=0;
	    int countPerPage=10;
//	    
	    String orderStr = "lldate";
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
	    
	    String LingLiaoSql="";
	    
	    LingLiaoSql ="select b.product_name productName,b.product_id productId,d.companyname customer,c.customer customerId,to_char(b.purduct_num) productNum," +
	    		"bc.order_id orderId,to_char(bc.lldate,'yyyy-MM-dd') liDate,bc.item_name stuff,bc.spec,to_char(bc.LL_num) liNum,to_char(bc.unitPrice) unitPrice,to_char(bc.price) sumPrice," +
	    		"bc.unit,e.item_typedesc itemType,bc.LL_sheetid sheetId,bc.item_id,bc.llStaff,f.staff_name llStaffName from (select ab.*,rownum rn from (" +
	    		"select a.lldate,a.emp_id llStaff,t.* from lingliao_detl t " +
	    		"       left join lingliao a on a.ll_sheetid=t.ll_sheetid" +
	    		conditon+
	    		"order by a.lldate desc,t.item_id desc )ab order by lldate desc,item_id desc )bc " +
	    		"left join order_detail b on b.order_id= bc.order_id and b.product_id=bc.productId " +
	    		"left join orders c on c.order_id = bc.order_id " +
	    		"left join customer d on d.companyid=c.customer " +
	    		"left join itemtype e on e.item_typeid=bc.item_type " +
	    		"left join employee_info f on f.staff_code= bc.llStaff " +
	    		pageCut +" order by liDate desc,item_id desc ";
	    
	    try {
	    	lingLiaoList = Sqlhelper.exeQueryList(LingLiaoSql, null, BeanLingLiaoSum.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lingLiaoList;
	}
}
