package cfmes.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.bean.AoBean;
import cfmes.bom.dao.AoBeanDao;
import cfmes.util.DealString;

public class AocmtyfSvlt extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	   throws ServletException, IOException {
		
		DealString ds = new DealString();
		AoBeanDao aobeandao = new AoBeanDao();
        
		HttpSession session = request.getSession(true);
		String flight_type = (String)session.getAttribute("flight_type");
		String product_id = (String)session.getAttribute("product_id");
		String issue_num = (String)session.getAttribute("issue_num");
		String item_id = (String)session.getAttribute("item_id");
		
		String dotype = ds.toGBK(request.getParameter("dotype"));
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
        
		AoBean aobean = new AoBean();
	    
		String aono = ds.toString(ds.toGBK((String)request.getParameter("aono")));
		String aover = ds.toString(ds.toGBK((String)request.getParameter("aover")));
		String aocut = ds.toString(ds.toGBK((String)request.getParameter("aocut")));
		String cut_num = ds.toString(ds.toGBK((String)request.getParameter("cut_num")));
		String aomeasure = ds.toString(ds.toGBK((String)request.getParameter("aomeasure")));
		String measure_num = ds.toString(ds.toGBK((String)request.getParameter("measure_num")));
		String aotool = ds.toString(ds.toGBK((String)request.getParameter("aotool")));
		String tool_num = ds.toString(ds.toGBK((String)request.getParameter("tool_num")));
		String aomaterial = ds.toString(ds.toGBK((String)request.getParameter("aomaterial")));
		String material_num = ds.toString(ds.toGBK((String)request.getParameter("material_num")));
		String aoaccessory = ds.toString(ds.toGBK((String)request.getParameter("aoaccessory")));
		String accessory_num = ds.toString(ds.toGBK((String)request.getParameter("accessory_num")));
		try{
//		.........................................���������ҳ�����............................................//
		
		/**��ӵ��������С�ԭ����**/
		if(dotype.equals("addcmtyf")){
			 if(aobeandao.isin(product_id, issue_num, aocut, aono,aover,"cut")){
				 out.print("<script>alert('�˵��߱���Ѵ����ڸ�AO�У�δ����ӡ�');window.history.back();</script>");
				 }else{aobean.addAoCMT(flight_type,product_id,issue_num,item_id,aono,aover, aocut,cut_num, "cut");}
			 if(aobeandao.isin(product_id, issue_num,  aomeasure, aono,aover,"measure")){
				 out.print("<script>alert('�����߱���Ѵ����ڸ�AO�У�δ����ӡ�');window.history.back();</script>");
				 }else{aobean.addAoCMT(flight_type,product_id,issue_num,item_id,aono,aover, aomeasure,measure_num, "measure");}
			 if(aobeandao.isin(product_id, issue_num, aotool,aono,aover,"tool")){
				 out.print("<script>alert('�˼о߱���Ѵ����ڸ�AO�У�δ����ӡ�');window.history.back();</script>");
				 }else{aobean.addAoCMT(flight_type,product_id,issue_num,item_id,aono,aover, aotool,tool_num, "tool");}
			 if(aobeandao.isin(product_id, issue_num, aomaterial,aono,aover,"material")){
				 out.print("<script>alert('��ԭ���ϱ���Ѵ����ڸ�AO�У�δ����ӡ�');window.history.back();</script>");
				 }else{aobean.addAoCMT(flight_type,product_id,issue_num,item_id,aono,aover, aomaterial,material_num, "material");}
			 if(aobeandao.isin(product_id, issue_num, aoaccessory,aono,aover,"accessory")){
				 out.print("<script>alert('�˸��ϱ���Ѵ����ڸ�AO�У�δ����ӡ�');window.history.back();</script>");
				 }else{aobean.addAoCMT(flight_type,product_id,issue_num,item_id,aono,aover, aoaccessory,accessory_num, "accessory");}
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
