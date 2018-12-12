package com.wl.testaction.noticeManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.util.sql_data;

import com.wl.forms.Notice;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;
import com.wl.tools.UUIDHexGenerator;

public class NoticeHaveSendServlet extends HttpServlet {
	private static final long serialVersionUID = 8211416997978746631L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "sendTime";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();	
	    
	    String totalCountSql = "select count(*) from notice where sender=?  ";
	    String[] paramss = {createPerson};
		try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, paramss);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	    
	    String noticeWillSql = "select A.id,B.staff_name sender,C.staff_name receiver,sendTime,readTime," +
	    		"contentTitle,content,attachment,isReaded,D.text grade,A.grade noticegrade " +
	    		"from (select Z.*,ROWNUM row_num from (select EM.* from notice EM where sender=? order by to_number(grade) desc, "+orderStr+" desc) Z where ROWNUM<="+(countPerPage*pageNo)+"  order by to_number(grade) desc,  "+orderStr+"  desc) A " +
	    		"left join employee_info B on A.sender=B.staff_code " +
	    		"left join employee_info C on A.receiver=C.staff_code  " +
	    		"left join noticeGrade D on A.grade=D.id "+
	    		"where row_num>="+((pageNo-1)*countPerPage+1)+" order by to_number(noticegrade) desc,  "+orderStr+" desc";
	    String[] params = {createPerson};
	    List<Notice> notices = new ArrayList<Notice>();
		try {
			notices = Sqlhelper.exeQueryList(noticeWillSql, params, Notice.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String json = PluSoft.Utils.JSON.Encode(notices);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	}


}
