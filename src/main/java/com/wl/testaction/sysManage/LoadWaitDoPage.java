package com.wl.testaction.sysManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class LoadWaitDoPage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadWaitDoPage() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	//		待处理事项列表,配合权限配置,通过list读取权限，确定事项与数量对应顺序
		 HttpSession session = request.getSession();
		 String createPerson = ((User)session.getAttribute("user")).getStaffCode();
//		 String changePerson = ((User)session.getAttribute("user")).getStaffCode();
		 
		
		 int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		String json = "";
		int total = 0;
		List <WaitDoPageBean> lista = new ArrayList<WaitDoPageBean>();
		String sqla ="";
		 if(createPerson.equals("NLJS02001")){		//2001
			sqla = "select waitname,waitnum,link from(" +
			 		"select s.waitname,em.waitnum,t.pageurl link,rownum rn from( " +
			 		"select waitname,waitnum from(" +
			 		" select 'orders' waitname,count(1) waitnum from orders a where to_number(a.order_status) >=1 and to_number(a.order_status) <=4 " +
			 		" union all" +
	//		 		" select 'fos',count(1) waitdo from order_detail b where b.product_status >=10 and b.product_status<=13" +
	//		 		" union all" +
			 		" select 'po',count(1) waitdo from po_plan c where c.status =1 or c.status =3" +
			 		" union all"+
			 		" select 'email',count(1) waitdo from NOTICE d where d.receiver='"+createPerson+"' and  d.isreaded='0'"+
			 		" ) where waitnum >0 )em " +
			 		"  left join waitdopage s on s.waitid = em.waitname " +
			 		" left join listtree t on t.pageid= s.link "+
			 		")" +
			 		"where rn>"+min+" and rn <= "+max+""+
			 		" order by waitnum desc ";
			total =3;	// total 应等于权限的数量，不需要从后台查询
			 
		}else{
			sqla ="select waitname,waitnum,link from(" +
		 		"select s.waitname,em.waitnum,t.pageurl link,rownum rn from( " +
		 		"select waitname,waitnum from(" +
		 		" select 'email' waitname,count(1) waitnum from NOTICE d where d.receiver='"+createPerson+"' and d.isreaded='0'"+
		 		" ) where waitnum >0 )em " +
		 		"  left join waitdopage s on s.waitid = em.waitname" +
		 		" left join listtree t on t.pageid= s.link "+
		 		")" +
		 		"where rn>"+min+" and rn <= "+max+""+
		 		" order by waitnum desc ";
			total =1;	// total 应等于权限的数量，不需要从后台查询
		}
		 try {
			lista = Sqlhelper.exeQueryList(sqla, null, WaitDoPageBean.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		json = PluSoft.Utils.JSON.Encode(lista);
		json = "{\"total\":"+total+",\"data\":"+json+"}";
		System.out.println(json);
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(json).flush();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
