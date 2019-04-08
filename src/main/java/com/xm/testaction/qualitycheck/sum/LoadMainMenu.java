package com.xm.testaction.qualitycheck.sum;

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

public class LoadMainMenu extends HttpServlet {
//登陆根据权限加载主页面目录
	/**
	 * Constructor of the object.
	 */
	public LoadMainMenu() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
	    String userId = ((User)session.getAttribute("user")).getUserId();
	    String para =  ((User)session.getAttribute("user")).getStaffCode();	//当前用户
	    String sqla ="select c.pageid id,c.text,c.pid,c.pagelevel,c.iconcls iconCls,c.pageurl url from listtree c where c.pagelevel ='1' order by c.pageid" ; 
	    		
	    	/*"select t.pageid id,t.text ,t.iconcls iconCls,t.pid,t.pagelevel,t.pageurl url from LISTTREE t " +
	    		"left join rightassign a on a.pageid = t.pageid " +
	    		"where a.staffcode = '"+para+"' "+
	    		"union all " +
	    		"select c.pageid,c.text,c.iconcls,c.pid,c.pagelevel,c.pageurl url from listtree c where c.pagelevel ='1'" +
	    		"order by id" ;*/
	    List<LoadAllMenuBean> fu = null ;

	    ArrayList<LoadAllMenuBean> list = new ArrayList<LoadAllMenuBean>();
	    
	    System.out.println(sqla);
	    try {
	    	fu = Sqlhelper.exeQueryList(sqla, null, LoadAllMenuBean.class);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	    
	    for (int i = 0,len=fu.size(); i < len; i++) {
	    	
	    	LoadAllMenuBean tree = fu.get(i);
			
	    	String pid = tree.getId();
			String sql2 ="select t.pageid id,t.text ,t.iconcls iconCls,t.pid,t.pagelevel,t.pageurl url "
					+ "from LISTTREE t left join rightassign a on a.pageid = t.pageid where a.staffcode = '70203718'  "
					+ "and t.pid='"+pid+"'"+" order by t.pageId";
			List<LoadAllMenuBean> children = null;
			try {
				children = Sqlhelper.exeQueryList(sql2, null, LoadAllMenuBean.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			tree.setChildren(children);
			list.add(tree);
			
		}
	    String json = PluSoft.Utils.JSON.Encode(list);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
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
