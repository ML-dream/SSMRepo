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

import com.wl.forms.WorkBranch;
import com.wl.tools.Sqlhelper;
public class GetWorkBranchServlet extends HttpServlet {

	private static final long serialVersionUID = -2010070723465074258L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
	    String Sql= "select typeId id,typeName text,sortedNum from workbranch order by sortedNum";
	    List<WorkBranch> resultList = new ArrayList<WorkBranch>();
	    try {
			resultList = Sqlhelper.exeQueryList(Sql, null, WorkBranch.class);
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
//			List<WorkBranch> resultList = new ArrayList<WorkBranch>();
//			try {
//				while (rs.next()) {
//					WorkBranch result = new WorkBranch();
//					result.setId(rs.getString(1));
//					result.setText(rs.getString(2));
//					result.setSortedNum(rs.getInt(3));
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













