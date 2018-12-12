package com.wl.testaction;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wl.forms.Employee;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;


public class EditEmployeeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 4697195515623837591L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    
	    String staffCode = request.getParameter("staffCode");
	
		String	EmployeeSql = 
		    	"select STAFF_CODE staffCode,STAFF_NAME staffName,SECTION_CODE sectionCode,GENDER,SCHOOL_FROM schoolFrom,ADDRESS,TECHNICAL_GRADE technicalGrade,BIRTHDAY," +
		    	"EDUCATION_LEVEL educationLevel,SPECIALITY,WORK_TYPE workType,OFFICE_PHNE officePhne,MOBILE_PHONE mobilePhone,HOME_PHONE homePhone," +
		    	"POSITION,WORKTIME,QQ,EMAIL,RFID_CODE RFIDCode,JOINTIME,FEE,LEAVETIME,IDCARD,B.deptname sectionname " +
		    	"from employee_info A " +
		    	"left join dept B on A.section_CODE=B.deptId "+
		    	"where staff_Code=? ";
		String[] params = {staffCode};
		Employee employee = new Employee();
		
		try {
			employee = Sqlhelper.exeQueryBean(EmployeeSql, params, Employee.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("employee", employee);
		
//	    System.out.println("EmployeeSql=="+EmployeeSql);
//	    
//	    ResultSet Employeers =null;
//		try{
//			Employeers = Sqlhelper.executeQuery(EmployeeSql, null);
//			
//			Employeers.next();
//			Employee employee = new Employee();
//			employee.setStaffCode(Employeers.getString(1));
//			employee.setStaffName(Employeers.getString(2));
//			employee.setSectionCode(Employeers.getString(3));
//			employee.setGender(Employeers.getString(4));
//			employee.setSchoolFrom(Employeers.getString(5));
//			employee.setAddress(Employeers.getString(6));
//			employee.setTechnicalGrade(Employeers.getString(7));
//			employee.setBirthday(Employeers.getDate(8));
//			employee.setEducationLevel(Employeers.getString(9));
//			employee.setSpeciality(Employeers.getString(10));
//			employee.setWorkType(Employeers.getString(11));
//			employee.setOfficePhne(Employeers.getString(12));
//			employee.setMobilePhone(Employeers.getString(13));
//			employee.setHomePhone(Employeers.getString(14));
//			employee.setPosition(Employeers.getString(15));
//			employee.setWorkTime(Employeers.getString(16));
//			employee.setQQ(Employeers.getString(17));
//			employee.setEmail(Employeers.getString(18));
//			employee.setRFIDCode(Employeers.getString(19));
//			employee.setJoinTime(Employeers.getDate(20));
//			employee.setFee(Employeers.getString(21));
//			employee.setLeaveTime(Employeers.getDate(22));
//			employee.setIDCard(Employeers.getString(23));
//			
//			//request.setAttribute("employee", employee);
//			request.setAttribute("employee", employee);
			
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
//					employeeList.add(employee);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String json = PluSoft.Utils.JSON.Encode(employeeList);
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append(json).flush();
//			System.out.println(json);
//			this.getServletConfig().getServletContext().getRequestDispatcher("employeeManage/editEmployee.jsp").forward(request, response);
			
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
//		xiem   para用来判断请求来源  seeEmp则是查看员工信息
		String para = "";
		String toUrl ="employeeManage/editEmployee.jsp";
		try {
			para = StringUtil.isNullOrEmpty(request.getParameter("para"))?para:request.getParameter("para");
			if(para.equals("seeEmp")){
				toUrl="employeeManage/seeEmployee.jsp";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.getRequestDispatcher(toUrl).forward(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













