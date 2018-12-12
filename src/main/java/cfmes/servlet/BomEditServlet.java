package cfmes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import cfmes.bean.MultiRecordBean;
import cfmes.util.DealString;
import cfmes.util.sql_data;

public class BomEditServlet extends HttpServlet {

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

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//ȡ��session��Ĺ������
		HttpSession session = request.getSession(true);
		String product_id = (String)session.getAttribute("product_id");
		String issue_num = (String)session.getAttribute("issue_num");
		String father_item_id = (String)session.getAttribute("father_item_id");
		String level_id = (String)session.getAttribute("level_id");
		String id = (String)session.getAttribute("id");
		String fid = (String)session.getAttribute("fid");
		String item_id = (String)session.getAttribute("item_id");
		
		DealString ds = new DealString();
		String item_num = ds.toString(ds.toGBK(request.getParameter("item_numbt")));
		
		if(false){//issue_num.indexOf("-")!=-1){
			//��ʾ���������´������䣬��ʱ�Ĳ���Ϊ���̱��Ĳ���
			MultiRecordBean mrbean = new MultiRecordBean();
			Vector vect = new Vector();
			vect.add("work.ebom");
			vect.add(mrbean.addVector("product_id", product_id, "CHAR"));
			vect.add(mrbean.addVector("issue_num", issue_num, "CHAR"));
			vect.add(mrbean.addVector("father_item_id", father_item_id, "CHAR"));
			vect.add(mrbean.addVector("level_id", level_id, "CHAR"));
			vect.add(mrbean.addVector("id", id, "CHAR"));
			vect.add(mrbean.addVector("fid", fid, "CHAR"));
			vect.add(mrbean.addVector("item_id", item_id, "CHAR"));
			vect.add(mrbean.addVector("item_num", item_num, "CHAR"));
			
			mrbean.updateRecord(mrbean.EcVector(vect, "03", 2));
			
		}else{	
		sql_data sqlbean = new sql_data();
		String sql = "update work.ebom set item_num = '"+item_num+"' where product_id ='"+product_id
		+"' and issue_num = '"+issue_num+"' and father_item_id = '"+father_item_id+"' and level_id= '"
		+level_id+"' and id= '"+id+"' and fid ='"+fid+"' and item_id = '"+item_id+"'";
		try{
			sqlbean.executeUpdate(sql);
	    out.println("<script>alert('����ɹ���');</script>");
		out.flush();
		out.close();
	}catch(Exception e){
		System.out.println("BomEditServlet����ʱ���?����Ϊ��"+e);
	}finally{
		sqlbean.closeConn();
	}
		
		
	}

}
}