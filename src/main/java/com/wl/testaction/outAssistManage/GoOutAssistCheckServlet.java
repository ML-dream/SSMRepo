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

public class GoOutAssistCheckServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        String menuId=ChineseCode.toUTF8(request.getParameter("menuId"));
        String Sql="select A.menuId,A.waixiecom,to_char(A.deliverTime,'yyyy-mm-dd') deliverTime,A.totalNum,A.outAssistStatus,A.checkedadvice,B.companyName,B.connector,B.connectorTel,B.address from outassistmenu A " +
           		"left join outAssistCom B ON A.waixiecom=B.companyId " +
           		"where menuId=?";
        String[] params={menuId};
        ProcessesPlan outassistmenu=new ProcessesPlan();
        try{
        	outassistmenu=Sqlhelper.exeQueryBean(Sql, params, ProcessesPlan.class);
        }catch(Exception e){
        	e.printStackTrace();
        }
        request.setAttribute("outassistmenu", outassistmenu);
        request.getRequestDispatcher("outAssistManage/OutAssistCheck.jsp").forward(request, response);
	}

}
