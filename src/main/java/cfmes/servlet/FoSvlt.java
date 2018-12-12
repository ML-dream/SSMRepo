package cfmes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import Bom.Tree_session;
import cfmes.bean.FoBean;
import cfmes.bom.dao.FoBeanDao;
import cfmes.util.DealString;

public class FoSvlt extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	   throws ServletException, IOException {
		
		DealString ds = new DealString();
		FoBean fobean = new FoBean();
		FoBeanDao fobeandao = new FoBeanDao();
		
		//ȡ��session��Ĺ������
		HttpSession session = request.getSession(true);
		String flight_type = (String)session.getAttribute("flight_type");
		String product_id = (String)session.getAttribute("product_id");
		String issue_num = (String)session.getAttribute("issue_num");
		String item_id = (String)session.getAttribute("item_id");
		
		String fo_no = ds.toGBK(request.getParameter("fo_no"));
		String fover = ds.toGBK(request.getParameter("fover"));
		
		String dotype = ds.toGBK(request.getParameter("dotype"));
		String dept_id = ds.toGBK(request.getParameter("dept_id"));
		
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		try{
			fobean.setFlight_type(flight_type);
		    fobean.setIssue_num(issue_num); 
		    fobean.setItem_id(item_id);
		    fobean.setProduct_id(product_id);
//		.........................................���������ҳ�����............................................//
		/**����һ��BOM*/
		if(dotype.equals("add")){
			 if(fobeandao.isinFo(product_id,issue_num,fo_no,fover)){
			     out.print("<script>alert('��FO�ĸð汾�Ѵ��ڣ����������롣');window.history.back();</script>");
			 }else{
				 Hashtable ht = new Hashtable();
				 ht.put("FO_NO",fo_no);
				 ht.put("FO_VER",fover);
				 ht.put("DEPT_ID",dept_id);
				 fobean.addFo(ht);

			 }
		}
		
		/**�޸�*/
		if(dotype.equals("mod")){
			 String fo_no_old =  ds.toGBK(request.getParameter("txt_fo_no"));
			 String fover_old =  ds.toGBK(request.getParameter("txt_fover"));
		     if(!fo_no_old.equals(fo_no) && fobeandao.isinFo(product_id,issue_num,fo_no,fover)){
			       out.print("<script>alert('��FO�ĸð汾�Ѵ��ڣ����������롣');window.history.back();</script>");
			 }else{
				 Hashtable ht = new Hashtable();
				 ht.put("FO_NO_OLD",fo_no_old);
				 ht.put("FOVER_OLD",fover_old);
				 ht.put("FO_NO",fo_no);
				 ht.put("FOVER",fover);
				 ht.put("DEPT_ID",dept_id);
				 fobean.modFo(ht);
			 }
		        
		}
		/**ɾ��һ��*/
		if(dotype.equals("del")){
			
			fobean.delFo(fo_no,fover);

				
			   out.print("<script>window.location.href='Html/capp/fo/fo.jsp;</script>");
		}
	
		out.flush();
		out.close();
		}catch(Exception e){
			System.out.println("FoSvlt ����ʱ���?����Ϊ��"+e);
		}
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
          doPost(request,response);
    }
}
