package com.wl.testaction.machineManage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

/**
 * Servlet implementation class checkMachineTime
 */
public class checkMachineTime extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String year = request.getParameter("year");
		String month01 = request.getParameter("month");
		String date01 = request.getParameter("date");
		String equipCode = request.getParameter("equipCode");
		String machineTime0101 =  ChineseCode.toUTF8(request.getParameter("machineTime0101"));
		//int i = Integer.parseInt([String]);
		String month = "";
		String date = "";
		if( Integer.parseInt(month01)<10) {
			month="0"+month01;
		}else {
			month=month01;
		}
		if(Integer.parseInt(date01)<10) {
			date ="0"+date01;
		}else {
			date = date01;
		}
		String machineIdMachineTime = year+"-"+month+"-"+date;
		
		/*String [] para = {};*/
		String sqla = "select count(*) from machineinfo_time where MACHINEID_MACHINETIME='"+equipCode+machineIdMachineTime+machineTime0101+"'";
		int temp = 0; //最大的工序号
		try {
			temp = Sqlhelper.exeQueryCountNum(sqla, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String result = temp+"";
		String json = "";
		
		json = "{\"result\":\""+result+"\"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
	}


}
