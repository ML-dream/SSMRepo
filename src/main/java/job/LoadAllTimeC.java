package job;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper0;

public class LoadAllTimeC extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadAllTimeC() {
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
		String orderid = request.getParameter("orderid");
		String productid = request.getParameter("productid");
		
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
//		(nvl(t.price,0)*(t.basetime+nvl(c.rewardstime,0))) timem   返工的工序没有单价，造成结果错误
		String sqlc = "create or replace view focdetail " +
				"as " +
				"select order_id,drawingid,fo_no,sum(timec) timec,sum(timem) timem from ( select t.order_id,t.drawingid,t.fo_no,t.fo_opname,t.typename,t.price,(t.basetime+nvl(c.rewardstime,0)) timec,(nvl(t.price,0)*(t.basetime+nvl(c.rewardstime,0))) timem,rownum rn " +
				"from rewardstemp t " +
				"left join rewardstime c on c.barcode = t.barcode and c.fo_no = t.fo_no  " +
				"where t.order_id = '"+orderid+"' and t.drawingid = '"+productid+"' )" +
				"group by order_id,drawingid,fo_no ";
		try {
			System.out.println(sqlc);
			Sqlhelper0.executeUpdate(sqlc, null);
			System.out.println("ok " + sqlc);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String sqld = "create or replace view focdetail2 " +
				"as " +
				"select t.fo_no,t.fo_opname,t.workbranch,a.typename,a.price  " +
				"from fo_detail t" +
				" left join workbranch a on a.typeid = t.workbranch " +
				"where t.product_id = '"+productid+"' and t.isinuse='1' and t.issue_num in " +
				" (select max(issue_num )from fo_detail where product_id = '"+productid+"')";
		try {
			System.out.println(sqld);
			Sqlhelper0.executeUpdate(sqld, null);
			System.out.println("ok " + sqld);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String sqla = "select fo_no,fo_opname,typename,price,timec,timem from ("+
				"  select b.fo_no,b.fo_opname,b.typename,b.price,a.timec,a.timem,rownum rn from focdetail2 b  " +
				" left join focdetail a on b.fo_no = a.fo_no "+
				" order by fo_no asc)"+
				" where  rn>"+min+" and rn <="+max ;
		String sqlb = "select count(1) from (" +
						" select b.fo_no from focdetail2 b  " +
						" left join focdetail a on b.fo_no = a.fo_no "+
					")";
		
		int total = 0 ;
		ResultSet totalRs = null;
		try {
			totalRs = Sqlhelper0.executeQuery(sqlb, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
					
			totalRs.next();
			total = totalRs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if(totalRs!=null){
					totalRs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		ResultSet rsa = null;
		ArrayList<LoadAllTimeCBean> waitList = new ArrayList<LoadAllTimeCBean>();
		try {
			rsa = Sqlhelper0.executeQuery(sqla, null);		//详细数据
			
			while (rsa.next()){
				LoadAllTimeCBean bean = new LoadAllTimeCBean();
				bean.setFono(rsa.getString(1));
				bean.setFoname(rsa.getString(2));
				bean.setFotype(rsa.getString(3));
				bean.setPrice(rsa.getString(4));
				bean.setTimec(rsa.getString(5));
				bean.setTimem(rsa.getString(6));
				waitList.add(bean);	
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if(rsa!=null){
					rsa.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		String json = PluSoft.Utils.JSON.Encode(waitList);
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
