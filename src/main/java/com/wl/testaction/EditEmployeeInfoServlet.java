package com.wl.testaction;


import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.wl.tools.StringUtil;

import com.wl.forms.Employee;

import com.wl.tools.Sqlhelper;


public class EditEmployeeInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "STAFF_CODE";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	    String section = "";
	    String workName = "";
	    String leave = "";
	    section = StringUtil.isNullOrEmpty(request.getParameter("section"))?section :request.getParameter("section");
	    workName = StringUtil.isNullOrEmpty(request.getParameter("workName"))?workName :request.getParameter("workName");
	   leave = StringUtil.isNullOrEmpty(request.getParameter("leave"))?leave :request.getParameter("leave");
	    String totalCountSql = "select count(*) from employee_info where staff_name like '%"+leave+"%' and section_code like '%"+section+"%' ";
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    String EmployeeSql= null;
	    HttpSession httpSession = ((HttpServletRequest)request).getSession(true);
	    EmployeeSql = 
	    	"select STAFF_CODE staffCode,STAFF_NAME staffName,SECTION_CODE sectionCode,GENDER,SCHOOL_FROM schoolFrom,ADDRESS,TECHNICAL_GRADE technicalGrade,BIRTHDAY," +
	    	"EDUCATION_LEVEL educationLevel,SPECIALITY,WORK_TYPE workType,OFFICE_PHNE officePhne,MOBILE_PHONE mobilePhone," +
	    	"HOME_PHONE homePhone,POSITION,WORKTIME,QQ,EMAIL,RFID_CODE RFIDCode,JOINTIME,FEE,LEAVETIME,IDCARD,C.DeptName sectionName " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from employee_info EM where staff_Name like '%"+leave+"%'and em.section_code like '%"+section+
	    	"'  order by STAFF_CODE asc) A where ROWNUM<="+(countPerPage*pageNo) +
	    			"order by STAFF_CODE) B " +
	    	"left join dept C on B.Section_Code=C.DeptId where row_num>="+((pageNo-1)*countPerPage+1)+" order by STAFF_CODE";
	    List<Employee> employeeList = new ArrayList<Employee>();
	    try {
			employeeList = Sqlhelper.exeQueryList(EmployeeSql, null, Employee.class);   
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    String json = PluSoft.Utils.JSON.Encode(employeeList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	
//	    System.out.println("EmployeeSql2=="+EmployeeSql);
//	    
//	    ResultSet Employeers =null;
//		try{
//			Employeers = Sqlhelper.executeQuery(EmployeeSql, null);
//			List<Employee> employeeList = new ArrayList<Employee>();
//			try {
//				while (Employeers.next()) {
//					Employee employee = new Employee();
//					employee.setStaffCode(Employeers.getString(1));
//					employee.setStaffName(Employeers.getString(2));
//					employee.setSectionCode(Employeers.getString(3));
//					employee.setGender(Employeers.getString(4));
//					employee.setSchoolFrom(Employeers.getString(5));
//					employee.setAddress(Employeers.getString(6));
//					employee.setTechnicalGrade(Employeers.getString(7));
//					employee.setBirthday(Employeers.getDate(8));
//					employee.setEducationLevel(Employeers.getString(9));
//					employee.setSpeciality(Employeers.getString(10));
//					employee.setWorkType(Employeers.getString(11));
//					employee.setOfficePhne(Employeers.getString(12));
//					employee.setMobilePhone(Employeers.getString(13));
//					employee.setHomePhone(Employeers.getString(14));
//					employee.setPosition(Employeers.getString(15));
//					employee.setWorkTime(Employeers.getString(16));
//					employee.setQQ(Employeers.getString(17));
//					employee.setEmail(Employeers.getString(18));
//					employee.setRFIDCode(Employeers.getString(19));
//					employee.setJoinTime(Employeers.getDate(20));
//					employee.setFee(Employeers.getString(21));
//					employee.setLeaveTime(Employeers.getDate(22));
//					employee.setIDCard(Employeers.getString(23));
//					employee.setSectionName(Employeers.getString("DEPTNAME"));
//					employeeList.add(employee);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String json = PluSoft.Utils.JSON.Encode(employeeList);
//			
//			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append(json).flush();
//			System.out.println(json);
//			StringBuffer employeeInfo = new StringBuffer(8192);
//			employeeInfo.append("[");
//			while (Employeers.next()) {
//				employeeInfo.append("{");
//				employeeInfo.append("staffCode:").append("\"").append(Employeers.getString(1)).append("\"").append(",");
//				employeeInfo.append("staffName:").append("\"").append(Employeers.getString(2)).append("\"").append(",");
//				employeeInfo.append("sectionCode:").append("\"").append(Employeers.getString(3)).append("\"").append(",");
//				employeeInfo.append("gender:").append("\"").append(Employeers.getString(4)).append("\"").append(",");
//				employeeInfo.append("schoolFrom:").append("\"").append(Employeers.getString(5)).append("\"").append(",");
//				employeeInfo.append("address:").append("\"").append(Employeers.getString(6)).append("\"").append(",");
//				employeeInfo.append("technicalGrade:").append("\"").append(Employeers.getString(7)).append("\"").append(",");
//				employeeInfo.append("birthday:").append("\"").append(Employeers.getString(8)).append("\"").append(",");
//				employeeInfo.append("educationLevel:").append("\"").append(Employeers.getString(9)).append("\"").append(",");
//				employeeInfo.append("speciality:").append("\"").append(Employeers.getString(10)).append("\"").append(",");
//				employeeInfo.append("workType:").append("\"").append(Employeers.getString(11)).append("\"").append(",");
//				employeeInfo.append("officePhne:").append("\"").append(Employeers.getString(12)).append("\"").append(",");
//				employeeInfo.append("mobilePhone:").append("\"").append(Employeers.getString(13)).append("\"").append(",");
//				employeeInfo.append("homePhone:").append("\"").append(Employeers.getString(14)).append("\"").append(",");
//				employeeInfo.append("position:").append("\"").append(Employeers.getString(15)).append("\"").append(",");
//				employeeInfo.append("workTime:").append("\"").append(Employeers.getString(16)).append("\"").append(",");
//				employeeInfo.append("QQ:").append("\"").append(Employeers.getString(17)).append("\"").append(",");
//				employeeInfo.append("email:").append("\"").append(Employeers.getString(18)).append("\"").append(",");
//				employeeInfo.append("RFIDCode:").append("\"").append(Employeers.getString(19)).append("\"").append(",");
//				employeeInfo.append("joinTime:").append("\"").append(Employeers.getString(20)).append("\"").append(",");
//				employeeInfo.append("fee:").append("\"").append(Employeers.getString(21)).append("\"").append(",");
//				employeeInfo.append("leaveTime:").append("\"").append(Employeers.getString(22)).append("\"").append(",");
//				employeeInfo.append("IDCard:").append("\"").append(Employeers.getString(23)).append("\"");
//				employeeInfo.append("},");
//			}
//			String employeeInfoString = employeeInfo.substring(0, employeeInfo.length()-1)+"]";
//			
//			System.out.println(employeeInfoString);
			
			
			
//			out.println("<Employee>");
//			
//			while (Employeers.next()) {
//				out.println("<EmployeeInfo>");
//				out.println("<EmployeeID>"+Employeers.getString(1)+"</EmployeeID>");
//				out.println("<EmployeeName>"+Employeers.getString(2)+"</EmployeeName>");
//				out.println("<deptid>"+Employeers.getString(3)+"</deptid>");
//				out.println("<gender>"+Employeers.getString(4)+"</gender>");
//				out.println("<schoolFrom>"+Employeers.getString(5)+"</schoolFrom>");
//				out.println("<ADDRESS>"+Employeers.getString(6)+"</ADDRESS>");
//				out.println("<techGrade>"+Employeers.getString(7)+"</techGrade>");
//				out.println("<BIRTHDAY>"+Employeers.getString(8)+"</BIRTHDAY>");
//				out.println("<EDUCATION_LEVEL>"+Employeers.getString(9)+"</EDUCATION_LEVEL>");
//				out.println("<SPECIALITY>"+Employeers.getString(10)+"</SPECIALITY>");
//				out.println("<WORK_TYPE>"+Employeers.getString(11)+"</WORK_TYPE>");
//				out.println("<OFFICE_PHNE>"+Employeers.getString(12)+"</OFFICE_PHNE>");
//				out.println("<MOBILE_PHONE>"+Employeers.getString(13)+"</MOBILE_PHONE>");
//				out.println("<HOME_PHONE>"+Employeers.getString(14)+"</HOME_PHONE>");
//				out.println("<POSITION>"+Employeers.getString(15)+"</POSITION>");
//				out.println("<WORKTIME>"+Employeers.getString(16)+"</WORKTIME>");
//				out.println("<QQ>"+Employeers.getString(17)+"</QQ>");
//				out.println("<EMAIL>"+Employeers.getString(18)+"</EMAIL>");
//				out.println("<RFID_CODE>"+Employeers.getString(19)+"</RFID_CODE>");
//				
//				out.println("</EmployeeInfo>");
//			}
//			out.println("</Employee>");
			//out.write(employeeInfoString);
			//response.getWriter().append(employeeInfoString).flush();
//			out.flush();
//			out.close();
//		}catch(Exception e){
//		}  finally{
//			try {
//				if(Employeers!=null){
//					Employeers.close();
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













