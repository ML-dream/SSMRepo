package com.wl.testaction.outAssistManage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ProcessesPlan;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class OutAssistMenuPrint extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     String menuId=ChineseCode.toUTF8(request.getParameter("menuId"));
     String Sql="select A.*,B.companyName from outAssistMenu A " +
     		    "left join outAssistCom B ON A.waixiecom=B.companyId " +
     		    "where menuId=?";
     String[] Params={menuId};
     ProcessesPlan process=new ProcessesPlan();
     
     try{
        process=Sqlhelper.exeQueryBean(Sql, Params, ProcessesPlan.class);
     }catch(Exception e){
    	 e.printStackTrace();
     }
     request.setAttribute("process", process);
     request.getRequestDispatcher("outAssistManage/outAssistMenuPrint.jsp").forward(request,response);
	}

}
