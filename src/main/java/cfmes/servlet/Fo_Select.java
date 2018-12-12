package cfmes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import cfmes.util.DealString;
import cfmes.util.sql_data;

public class Fo_Select extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
sql_data sqlbean=new sql_data();
DealString ds = new DealString();
String type="";
String sql="";
try{
	request.setCharacterEncoding("utf-8"); 
    
	HttpSession session = request.getSession(true);
	String flight_type = (String)session.getAttribute("flight_type");
	String product_id = (String)session.getAttribute("product_id");
	String issue_num = (String)session.getAttribute("issue_num");
	String item_id = (String)session.getAttribute("item_id");
    String oper_id = ds.toString(ds.toGBK(request.getParameter("value")));
    String fo_no = ds.toString(ds.toGBK(request.getParameter("fo_no")));
    String fover  = ds.toString(ds.toGBK(request.getParameter("fover")));
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/xml"); 
    response.setHeader("Charset","utf-8");
	    
    PrintWriter out = response.getWriter();

    response.setHeader("Cache-Control","no-cache");
    out.println("<?xml version='1.0' encoding='utf-8'?>");
    out.println("<Item>");
    for(int i=1;i<6;i++){	
    	if(i==1){type="cut";}else if(i==2){type="measure";}else if(i==3){type="tool";}
    	else if(i==4){type="material";}else if(i==5){type="accessory";}
    	sql= "Select distinct i."+type+"_id,i2.item_name"
		 +" FROM work.fo_"+type+" i,work.item i2 WHERE i.product_id='"+product_id+"' and i.issue_num='"+issue_num
		  +"' and i.item_id='"+item_id+"' and i.fo_no='"+fo_no+"' and i.fo_operid='"+oper_id+"' and i2.item_id=i."+type+"_id";
    	 ResultSet rs =sqlbean.executeQuery(sql);
    	 out.println("<"+type+">");
    	 //System.out.println("<"+type+">");
    	 while(rs.next()){
    	    out.println("<Name_id>"+rs.getString(type+"_id")+"��"+rs.getString("item_name")+"��</Name_id>");
    	    out.println("<ID>"+rs.getString(type+"_id")+ "</ID>"); 
    	   // System.out.println(rs.getString(type+"_id"));
    	  }rs.close();
    	  out.println("</"+type+">");
    	  sqlbean.closeConn();
    }	
    
out.println("</Item>");
out.flush();
out.close();

}catch(Exception e){
	System.out.println("Fo_Select����ʱ���?����Ϊ��"+e);
}
}
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
 doPost(request,response);
}
}
