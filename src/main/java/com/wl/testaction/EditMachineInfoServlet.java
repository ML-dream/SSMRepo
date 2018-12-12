package com.wl.testaction;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

import cfmes.util.DealString;
import cfmes.util.sql_data;

public class EditMachineInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
	    response.setContentType("text/xml"); 
	    response.setHeader("Charset","utf-8");    
	    PrintWriter out = response.getWriter();
	    response.setHeader("Cache-Control","no-cache");
	    out.println("<?xml version='1.0' encoding='utf-8'?>");
	    System.out.println("进入EditMachineInfoServlet函数体了已经！！");
	    HttpSession httpSession = ((HttpServletRequest)request).getSession(true);
	    String MachineSql =null;
	    
	    int pageNow = 1;
	    int pageLenth = 4;
	    int pageWill =1;
	    
	    
	    if (request.getParameter("pageLength")!=null) {
	    	pageLenth = Integer.parseInt(request.getParameter("pageLength"));
	    	httpSession.setAttribute("pageLenth", pageLenth);
		}
	    httpSession.setAttribute("pageLenth", pageLenth);
	    MachineSql = "select machineid,machinename,power,worker from  (select A.*,ROWNUM row_num " +
	    		"from (select * from machinfo) A where ROWNUM<="+(pageLenth*pageWill)+
	    		" order by machineid) where row_num>="+((pageWill-1)*pageLenth+1);
	    if (request.getParameter("will")!=null) {
			pageNow = Integer.parseInt((httpSession.getAttribute("pageNow")).toString()) ;
			pageLenth = Integer.parseInt((httpSession.getAttribute("pageLenth")).toString());
			int will = Integer.parseInt(request.getParameter("will"));
			
			if (will!=-3) {
				if (will==-1) {
					pageWill = pageNow -1;
				}
				if (will==-2) {
					pageWill =pageNow+1;
				}
				
				if (will==-4) {
					pageWill = 1;
				}
				MachineSql =MachineSql = "select machineid,machinename,power,worker " +
					"from  (select A.*,ROWNUM row_num from (select * from machinfo) A where ROWNUM<="
				+(pageLenth*pageWill)+" order by machineid) where row_num>="+((pageWill-1)*pageLenth+1);
			}else {
				MachineSql = 
			    	"select machineid,machinename,power,worker from (select A.*,ROWNUM row_num " +
			    	"from (select * from machinfo) A order by machineid desc) where row_num<="
			    	+pageLenth+" order by machineid ";
			
			}
			System.out.println(pageNow+"  "+will+"  "+pageLenth);
		}
	    
	    
	    
	    
//	    MachineSql = "select machineid,machinename,power,worker from machinfo";
	    System.out.println("MachineSql=="+MachineSql);
	    
	    String machineworkerSql = "select staff_code,staff_name from employee_info";
	    System.out.println("machineworkerSql=="+machineworkerSql);
	    
	    ResultSet machineworkerrs = null;
	    ResultSet machiners =null;
		try{
			machiners = Sqlhelper.executeQuery(MachineSql, null);
			machineworkerrs =Sqlhelper.executeQuery(machineworkerSql, null);
			out.println("<machine>");
			
			while (machiners.next()) {
				out.println("<MachineInfo>");
				out.println("<machineid>"+machiners.getString(1)+"</machineid>");
				out.println("<machinename>"+machiners.getString(2)+"</machinename>");
				out.println("<machinepower>"+machiners.getString(3)+"</machinepower>");
				out.println("<machineworker>"+machiners.getString(4)+"</machineworker>");
				
				out.println("</MachineInfo>");
			}
			
			while (machineworkerrs.next()) {
				out.println("<workerInfo>");
				out.println("<machineworkers>"+machineworkerrs.getString(2)
						+"("+machineworkerrs.getString(1)+")</machineworkers>");
				out.println("<machineworkerid>"+machineworkerrs.getString(1)+"</machineworkerid>");
				out.println("</workerInfo>");
				System.out.println(machineworkerrs.getString(1)+"  "+machineworkerrs.getString(2));
			}
			out.println("</machine>");
			out.flush();
			out.close();
			
			httpSession.setAttribute("pageNow", pageWill);
			
		}catch(Exception e){
		}  finally{
			try {
				if(machiners!=null){
					machiners.close();
				}
				if(machineworkerrs!=null){
					machineworkerrs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













