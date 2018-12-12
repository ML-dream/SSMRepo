package com.wl.testaction.warehouse.productcheckin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Stockcl;
import com.wl.tools.StockinItem;

public class ProductServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ProductServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String status=request.getParameter("status");
		String checksheetId=(String) datamap.get("checksheetId");
		String checkindate=(String) datamap.get("checkindate");
		String orderId=(String) datamap.get("orderId");
		String productId=(String) datamap.get("productId");
		String productName=(String) datamap.get("productName");
		String drawingId=(String) datamap.get("drawingId");
		String issueNum=(String) datamap.get("issueNum");
		String spec=(String) datamap.get("spec");
		String unitPrice=(String) datamap.get("unitPrice");
	
		String checkinNum=(String) datamap.get("checkinnum");
		String lot=(String) datamap.get("lot");
		String qualityId=(String) datamap.get("qualityId");
		String memo=(String) datamap.get("memo");
		String id=(String) datamap.get("id");
		String seq=datamap.get("seq").toString();
		String unit=(String) datamap.get("unit");
		String productType=(String) datamap.get("productType");
		
		HttpSession session=request.getSession();
		String createPerson=((User)session.getAttribute("user")).getStaffCode();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime=df.format(new Date());
		String changeTime=df.format(new Date());
		String sql="insert into productcheckin (checksheetid,checkindate,orderid,productid,productname,drawingid," +
				"issuenum,spec,unitprice,checkinnum,lot,qualityid,memo,status,createperson,createtime,changeperson,changetime,unit,producttype) " +
				"values('"+checksheetId+"',to_date('"+checkindate+"','yyyy-MM-dd,hh24:mi:ss'),'"+orderId+"','"+productId+"','"+productName+"','"+drawingId+"'," +
					"'"+issueNum+"','"+spec+"','"+unitPrice+"','"+checkinNum+"','"+lot+"','"+qualityId+"','"+memo+"',"+status+"," +
					"'"+createPerson+"',to_date('"+createTime+"','yyyy-MM-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-MM-dd,hh24:mi:ss')," +
					"'"+unit+"','"+productType+"')";
		String sqlsheet="insert into checkin_sheetid values("+seq+",'"+id+"','"+checksheetId+"')";
		
		try{
			Sqlhelper.executeUpdate(sql, null);
			Sqlhelper.executeUpdate(sqlsheet, null);
			
			String json="{\"result\":\"操作成功！\",\"status\":1}";
			out.append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\",\"status\":0}";
			out.append(json).flush();
			e.printStackTrace();
		}
		
	}

}
