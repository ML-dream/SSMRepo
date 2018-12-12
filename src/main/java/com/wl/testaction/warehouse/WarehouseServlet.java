package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Warehouse;
import com.wl.tools.Sqlhelper;

public class WarehouseServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	this.doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int pageNo=0;
	    int countPerPage=20;
	    int totalCount = 0;
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    String totalCountSql = "select count(*) from warehouse";
	    try {
	    	totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		try{

			String sql="select warehouse_id,warehouse_name,shelf_num,shelf_storey,shelf_column,wh_addr,wh_phone,emp_id,C.staff_name empName from " +
					"(select A.*,rownum row_num from (select EM.* from warehouse EM order by warehouse_id asc ) A where rownum<="+(countPerPage*pageNo)+" order by warehouse_id) B " +
							"left join EMPLOYEE_INFO C on C.staff_code=emp_id " +
							"where row_num >="+(countPerPage*(pageNo-1)+1)+" order by warehouse_id";
			System.out.println("sql="+sql);
			
			List<Warehouse> resultList=new ArrayList<Warehouse>();
			//ResultSet rs=null;
			//rs=Sqlhelper.executeQuery(sql, null);
			try{
				resultList=Sqlhelper.exeQueryList(sql, null, Warehouse.class);
				/*while(rs.next()){
					Warehouse warehouse=new Warehouse();
					warehouse.setWarehouse_id(rs.getString(1));
					warehouse.setWarehouse_name(rs.getString(2));
					warehouse.setShelf_num(rs.getInt(3));
					warehouse.setShelf_storey(rs.getInt(4));
					warehouse.setShelf_column(rs.getInt(5));
					
					warehouse.setWh_addr(rs.getString(6));
					warehouse.setWh_phone(rs.getString(7));
					warehouse.setEmp_id(rs.getString(8));
					resultList.add(warehouse);
				}*/
				
			
			}catch(Exception e1){
			e1.printStackTrace();
			}
			String json=PluSoft.Utils.JSON.Encode(resultList);
			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
			response.getWriter().append(json).flush();
			System.out.println(json);
		}catch(Exception e2){
			e2.printStackTrace();
		}
		
		
	}
}
