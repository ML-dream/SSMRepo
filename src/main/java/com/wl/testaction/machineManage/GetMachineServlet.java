package com.wl.testaction.machineManage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.common.MachineStatus;
import com.wl.forms.Machine;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;
public class GetMachineServlet extends HttpServlet {

	private static final long serialVersionUID = 615004024768731311L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String statusFrom = StringUtil.isNullOrEmpty(request.getParameter("statusFrom"))?MachineStatus.INUSE+"":request.getParameter("statusFrom");
		String statusTo = StringUtil.isNullOrEmpty(request.getParameter("statusTo"))?MachineStatus.INUSE+"":request.getParameter("statusTo");
	    String sql= "select MACHINEID,MACHINENAME,MACHINESPEC,STATUS,POWER,WORKRANGE,MACHTYPE,MACHMODEL from machinfo " +
	    		"where status=? and status=?";
	    String[] params = {statusFrom,statusTo};
	    List<Machine> resultList = new ArrayList<Machine>();
	    try {
			resultList = Sqlhelper.exeQueryList(sql, params, Machine.class);
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
//			List<Machine> resultList = new ArrayList<Machine>();
//			try {
//				while (rs.next()) {
//					Machine result = new Machine();
//					result.setMachineId(rs.getString(1));
//					result.setMachineName(rs.getString(2));
//					result.setMachineSpec(rs.getString(3));
//					result.setStatus(rs.getString(4));
//					result.setPower(rs.getDouble(5));
//					result.setWorkRange(rs.getString(6));
//					result.setMachType(rs.getString(7));
//					result.setMachModel(rs.getString(8));
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













