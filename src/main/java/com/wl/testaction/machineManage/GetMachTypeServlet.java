package com.wl.testaction.machineManage;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wl.forms.MachType;
import com.wl.tools.Sqlhelper;

public class GetMachTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 6665788848645500372L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
	    String sql= "select machineType , typeName from machineType";
	    List<MachType> resultList = new ArrayList<MachType>();
	    
	    try {
			resultList = Sqlhelper.exeQueryList(sql, null, MachType.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    String json = PluSoft.Utils.JSON.Encode(resultList);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	    
//	    System.out.println("Sql=="+Sql);
//	    ResultSet rs =null;
//		try{
//			rs = Sqlhelper.executeQuery(Sql, null);
//			List<MachType> resultList = new ArrayList<MachType>();
//			try {
//				while (rs.next()) {
//					MachType result = new MachType();
//					result.setMachineType(rs.getString(1));
//					result.setTypeName(rs.getString(2));
//					resultList.add(result);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String json = PluSoft.Utils.JSON.Encode(resultList);
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append(json).flush();
//			System.out.println(json);
//		}catch(Exception e){
//			e.printStackTrace();
//		}  finally{
//			try {
//				if(rs!=null){
//					rs.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













