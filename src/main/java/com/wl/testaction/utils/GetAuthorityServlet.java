package com.wl.testaction.utils;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Authority;
import com.wl.tools.Sqlhelper;
public class GetAuthorityServlet extends HttpServlet {

	private static final long serialVersionUID = -4769779064943941970L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
	    String sql= "select authorityId,authorityName from authority";
	    
	    List<Authority> resultList = new ArrayList<Authority>();
	    
	    try {
			resultList = Sqlhelper.exeQueryList(sql, null, Authority.class);
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
//			List<Authority> resultList = new ArrayList<Authority>();
//			try {
//				while (rs.next()) {
//					Authority result = new Authority();
//					result.setAuthorityId(rs.getInt(1));
//					result.setAuthorityName(rs.getString(2));
//					
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













