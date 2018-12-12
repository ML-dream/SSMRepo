package com.xm.testaction.qualitycheck.sum;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper0;
import com.wl.tools.StringUtil;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;
import com.xm.testaction.qualitycheck.BarcodeLength;
import com.xm.testaction.qualitycheck.PoFlowBean;

public class ProductSumDetail extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println(this.getClass().getName());
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		int totalCount=0;
		
		 String state = "";  	//工序状态
		 
		
		String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());	
		String issueNum = request.getParameter("issueNum".trim());
//		计划数量，应从零件计划里取，包括报废的
		String partStr = "create or replace view partnumView as " +
				"   select a.orderid,a.productid,a.issuenum,sum(a.partnum) partNUm from partsplan a " +
				"  group by a.orderid,a.productid,a.issuenum ";
		try {
			System.out.println(partStr );
			Sqlhelper.executeUpdate(partStr, null);
			System.out.println("ok "+  partStr );
		} catch (Exception e) {
			// TODO: handle exception
		}
//		创建质量损失视图
		RejectView.proFoLoss();
		
		String sqla = "select * from ("+
			"select t.operid fono,a.fo_opname foopname,c.partnum partnum,t.passnum acceptnum,t.failurenum rejectnum,t.operstatus state,t.isco,b.companyname,to_char(d.qualityLoss) qualityLoss,rownum rn" +
			" from processesplan t " +
			"left join partnumview c on c.orderid= t.orderid and c.productid= t.productid and c.issuenum= t.issuenum " +
				" left join fo_detail a on a.product_id= t.productid and a.issue_num = t.issuenum and a.fo_no = t.operid and a.isinuse='1' " +
				" left join outassistcom b on b.companyid= t.waixiecom " +
				" left join profoLoss d on d.order_id=t.orderid and d.drawingid =t.productid and t.issuenum=d.issuenum   and t.operid= d.fo_no "+
				"where  t.orderid = '"+orderId+"' and t.productid= '"+productId+"' and t.issuenum= '"+issueNum+"' and t.isdiscard='0'" +
						" order by to_number(fono))  where  rn>"+min+" and rn <="+max ;	
		String sqlb = "select count(*) from (" +
				"select t.operid fono from processesplan t where  t.orderid = '"+orderId+"' and t.productid= '"+productId+"' and t.issuenum= '"+issueNum+"' and t.isdiscard='0')";
		List <ProductSumDetailBean> list = new ArrayList<ProductSumDetailBean>();
		try {
			list = Sqlhelper.exeQueryList(sqla, null, ProductSumDetailBean.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		List <ProductSumDetailBean> jsonList = new ArrayList<ProductSumDetailBean>();
		for(int m=0,n=list.size();m<n;m++){
//			判断工序状态
			ProductSumDetailBean bean = new ProductSumDetailBean();
			bean = list.get(m);
			state = bean.getState();
			int temp =Integer.parseInt(state);
			switch (temp) {
			case 0:
				bean.setStateName("未开工");
				break;
			case 1:
				bean.setStateName("加工中");
				break;
			case 2:
				bean.setStateName("完成");
				break;

			default:
				break;
			}
//			判断是否外协
			String isco = bean.getIsco();
			if(isco.equals("1")){
				bean.setRemark("外协-"+bean.getCompanyname());
			}
			jsonList.add(bean);
		}
		try {
			totalCount = Sqlhelper.exeQueryCountNum(sqlb, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = PluSoft.Utils.JSON.Encode(jsonList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		System.out.println(json);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













