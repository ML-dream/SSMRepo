package com.wl.testaction.po;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.PoPayStatistics;
import com.wl.tools.Sqlhelper;

public class PoPayStatisticsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PoPayStatisticsServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String Year=request.getParameter("year");
		int year = 0;
		int month =0;
		String sql = "";
		String paySql="";
		String bdate= "";
		String edate= "";
		String time = "";
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		if(Year.equals(""))
		{
			year = c.get(Calendar.YEAR); 
			month = c.get(Calendar.MONTH)+1;	 
		}else{
			year=Integer.parseInt(Year);
			month=12;
		}
			//+1  是当前月 ,如现在是 10月，这个值是 9
	//	int date = c.get(Calendar.DATE);
		List<PoPayStatistics> resultList=new ArrayList<PoPayStatistics>();
		for (int n= 1 ,m=month;n< m ;n++){
			bdate= "" +year +"-"+n+"-"+1;
			edate= "" +year +"-"+n+"-"+30;
			time =  "" +year +"-"+n;
			sql="select sum(price) from prdetail where createtime between to_date('"+bdate+"','yyyy-mm-dd,hh24:mi:ss') " +
					"and to_date('"+edate+"','yyyy-mm-dd,hh24:mi:ss')";
			paySql="select sum(thispay) from popaydetl where createtime between to_date('"+bdate+"','yyyy-mm-dd,hh24:mi:ss') " +
			"and to_date('"+edate+"','yyyy-mm-dd,hh24:mi:ss')";
			PoPayStatistics bean=new PoPayStatistics();
			double payMent=0;
			double hasPaid=0;
			try{
				
				String payment=Sqlhelper.exeQueryString(sql, null);
				payMent=(payment==null)?0:Double.parseDouble(payment);
				bean.setPayMent(payMent);
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				String haspaid=Sqlhelper.exeQueryString(paySql, null);
				hasPaid=(haspaid==null)?0:Double.parseDouble(haspaid);
				bean.setHasPaid(hasPaid);
			}catch(Exception e){
				e.printStackTrace();
			}
			bean.setNoPaid((payMent-hasPaid));
			bean.setMonth(time);
			resultList.add(bean);
		}
		
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+month+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
		
	}

}
