import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.wl.forms.User;
import com.wl.tools.JsonConvert;
import com.wl.tools.Sqlhelper;
import com.xm.testaction.qualitycheck.sum.IsJsonNull;


public class editOrderStatusServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public editOrderStatusServlet() {
		super();
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

		doPost(request,response);
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

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String result="";
		String gridjson =request.getParameter("gridjson");
		System.out.println(gridjson);
		
		
		HttpSession session=request.getSession();
	    String changePerson=((User)session.getAttribute("user")).getStaffCode();
	    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String changeTime=df.format(new Date());
	    
	    if(!changePerson.equals("admin")){
	    	result="操作失败，您没有相应的权限！";	
	    }else{
	    	JSONArray jarr = JSONArray.fromObject(gridjson);
	    	String orderId="";
	    	String orderStatus="";
	    	for (int i = 0,j=jarr.size();i<j;i++){
	    		Map<String, Object> map = JsonConvert.json2Map(jarr.get(i).toString());
				System.out.println(map);
				
		    	orderId=(String) (IsJsonNull.isJsonNull(map.get("orderId").toString())?orderId:map.get("orderId").toString());
		    	orderStatus=(String)(IsJsonNull.isJsonNull(map.get("orderStatus").toString())?orderStatus:map.get("orderStatus").toString());
		    	
		    	String[] params={orderStatus,orderId};
		    	
		    	String sql="update orders t set t.order_status=? where t.order_id=?";
		    	
		    	try {
					Sqlhelper.executeUpdate(sql, params);
					result="操作成功！";
				} catch (Exception e) {
					result="操作失败！";
					e.printStackTrace();
					// TODO: handle exception
				}
	    	}
	    }
	    
	    String json="{\"result\":\""+result+"\"}";
	    response.getWriter().append(json).flush();
	    
	}

}
