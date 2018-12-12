package com.wl.testaction.itemManage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wl.forms.ItemType;
import com.wl.tools.Sqlhelper;

public class GetItemTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 3011225105830773877L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
	    String sql= "select item_typeId id, item_typedesc text from itemtype";
	    List<ItemType> resultList = new ArrayList<ItemType>();
	    
	    try {
			resultList = Sqlhelper.exeQueryList(sql, null, ItemType.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    String json = PluSoft.Utils.JSON.Encode(resultList);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
		
		
//	    ResultSet rs =null;
//		try{
//			rs = Sqlhelper.executeQuery(Sql, null);
//			List<ItemType> resultList = new ArrayList<ItemType>();
//			try {
//				while (rs.next()) {
//					ItemType result = new ItemType();
//					result.setId(rs.getString(1));
//					result.setText(rs.getString(2));
//					resultList.add(result);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String json = PluSoft.Utils.JSON.Encode(resultList);
//			//json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
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













