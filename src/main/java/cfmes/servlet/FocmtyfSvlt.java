package cfmes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.bean.FoOperBean;
import cfmes.bom.dao.FoBeanDao;
import cfmes.util.DealString;

public class FocmtyfSvlt extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	   throws ServletException, IOException {
		
		DealString ds = new DealString();
		FoOperBean operbean = new FoOperBean();
		FoBeanDao fobeandao = new FoBeanDao();
     
		HttpSession session = request.getSession(true);
		String flight_type = (String)session.getAttribute("flight_type");
		String product_id = (String)session.getAttribute("product_id");
		String issue_num = (String)session.getAttribute("issue_num");
		String item_id = (String)session.getAttribute("item_id");
		
		String fo_no = ds.toGBK(request.getParameter("fo_no"));
		String fover = ds.toGBK(request.getParameter("fover"));
		String dotype = ds.toGBK(request.getParameter("dotype"));
		 
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		operbean.setFo_no(fo_no);
		operbean.setFover(fover);
	    operbean.setFlight_type(flight_type);
	    operbean.setIssue_num(issue_num);
	    operbean.setItem_id(item_id);
	    operbean.setProduct_id(product_id);
	    
		String oper_id = ds.toString(ds.toGBK((String)request.getParameter("oper_id")));
		String focut = ds.toString(ds.toGBK((String)request.getParameter("focut")));
		String cut_num = ds.toString(ds.toGBK((String)request.getParameter("cut_num")));
		String fomeasure = ds.toString(ds.toGBK((String)request.getParameter("fomeasure")));
		String measure_num = ds.toString(ds.toGBK((String)request.getParameter("measure_num")));
		String fotool = ds.toString(ds.toGBK((String)request.getParameter("fotool")));
		String tool_num = ds.toString(ds.toGBK((String)request.getParameter("tool_num")));
		String fomaterial = ds.toString(ds.toGBK((String)request.getParameter("fomaterial")));
		String material_num = ds.toString(ds.toGBK((String)request.getParameter("material_num")));
		String foaccessory = ds.toString(ds.toGBK((String)request.getParameter("foaccessory")));
		String accessory_num = ds.toString(ds.toGBK((String)request.getParameter("accessory_num")));
		try{
//		.........................................���������ҳ�����............................................//
		
		/**��ӵ��������С�ԭ����**/
		if(dotype.equals("addcmtyf")){
			 if(fobeandao.isin(product_id, issue_num, fo_no, fover, focut, oper_id,"cut")){
				 out.print("<script>alert('�˵��߱���Ѵ����ڸ�FO�����У�δ����ӡ�');window.history.back();</script>");
				 }else{operbean.addFoCMT(oper_id, focut,cut_num, "cut");}
			 if(fobeandao.isin(product_id, issue_num, fo_no, fover, fomeasure, oper_id,"measure")){
				 out.print("<script>alert('�����߱���Ѵ����ڸ�FO�����У�δ����ӡ�');window.history.back();</script>");
				 }else{operbean.addFoCMT(oper_id, fomeasure,measure_num, "measure");}
			 if(fobeandao.isin(product_id, issue_num, fo_no, fover, fotool, oper_id,"tool")){
				 out.print("<script>alert('�˼о߱���Ѵ����ڸ�FO�����У�δ����ӡ�');window.history.back();</script>");
				 }else{operbean.addFoCMT(oper_id, fotool,tool_num, "tool");}
			 if(fobeandao.isin(product_id, issue_num, fo_no, fover, fomaterial, oper_id,"material")){
				 out.print("<script>alert('��ԭ���ϱ���Ѵ����ڸ�FO�����У�δ����ӡ�');window.history.back();</script>");
				 }else{operbean.addFoCMT(oper_id, fomaterial,material_num, "material");}
			 if(fobeandao.isin(product_id, issue_num, fo_no, fover, foaccessory, oper_id,"accessory")){
				 out.print("<script>alert('�˸��ϱ���Ѵ����ڸ�FO�����У�δ����ӡ�');window.history.back();</script>");
				 }else{operbean.addFoCMT(oper_id, foaccessory,accessory_num, "accessory");}
		}
		

		
		out.flush();
		out.close();
		}catch(Exception e){
			System.out.println("FoOperSvlt ����ʱ���?����Ϊ��"+e);
		}
}
public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
      doPost(request,response);
}
}
