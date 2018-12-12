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

public class doeditProductCheckinServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doeditProductCheckinServlet() {
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
		String Sstatus=datamap.get("status").toString();
		int status=Integer.parseInt(Sstatus);
		if(status==1){
			String json="{\"result\":\"已审核通过，不能修改！\"}";
			out.append(json).flush();
		}else{
			
			String checksheetId=(String) datamap.get("checksheetId");
			String checkindate=(String) datamap.get("checkindate");
			String orderId=(String) datamap.get("orderId");
			String productId=(String) datamap.get("productId");
			String productName=(String) datamap.get("productName");
			String drawingId=(String) datamap.get("drawingId");
			String issueNum=(String) datamap.get("issueNum");
			String spec=(String) datamap.get("spec");
			String unitPrice=(String) datamap.get("unitPrice");
			String checkinNum=(String) datamap.get("checkinNum");
			String unit=(String) datamap.get("unit");
			String lot=(String) datamap.get("lot");
			String qualityId=(String) datamap.get("qualityId");
			String memo=(String) datamap.get("memo");
			
			
			HttpSession session=request.getSession();
			String changePerson=((User)session.getAttribute("user")).getStaffCode();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String changeTime=df.format(new Date());
			
			String sql="update productcheckin set checkindate=to_date('"+checkindate+"','yyyy-MM-dd,hh24:mi:ss'),orderid='"+orderId+"',productid='"+productId+"'," +
					"productname='"+productName+"',drawingid='"+drawingId+"',issuenum='"+issueNum+"',spec='"+spec+"',unitprice='"+unitPrice+"',checkinnum='"+checkinNum+"',unit='"+unit+"'," +
					"lot='"+lot+"',qualityid='"+qualityId+"',memo='"+memo+"',changeperson='"+changePerson+"',changetime=to_date('"+changeTime+"','yyyy-MM-dd,hh24:mi:ss')," +
					"status=0 where checksheetid='"+checksheetId+"'";
			try{
				Sqlhelper.executeUpdate(sql, null);
				String json="{\"result\":\"修改保存成功！\"}";
				out.append(json).flush();
			}catch(Exception e){
				String json="{\"result\":\"修改保存失败！\"}";
				out.append(json).flush();
				e.printStackTrace();
			}
			
		}
		
		
		
	}

}
