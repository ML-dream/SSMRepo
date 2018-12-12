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

public class FodelcmtyfSvlt extends HttpServlet {
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
		
	
		 
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
	    operbean.setFlight_type(flight_type);
	    operbean.setIssue_num(issue_num);
	    operbean.setItem_id(item_id);
	    operbean.setProduct_id(product_id);
	    //.......
		String oper_id = ds.toString(ds.toGBK((String)request.getParameter("oper_id2")));
		String fo_no = ds.toString(ds.toGBK((String)request.getParameter("fo_no")));
		String fo_ver = ds.toString(ds.toGBK((String)request.getParameter("fover")));
		String focut = ds.toString(ds.toGBK((String)request.getParameter("fo_cut")));
		String fomeasure = ds.toString(ds.toGBK((String)request.getParameter("fo_measure")));
		String fotool = ds.toString(ds.toGBK((String)request.getParameter("fo_tool")));
		String fomaterial = ds.toString(ds.toGBK((String)request.getParameter("fo_material")));
		String foaccessory = ds.toString(ds.toGBK((String)request.getParameter("fo_accessory")));
		
		operbean.setFo_no(fo_no);
		operbean.setFover(fo_ver);
		try{
//		.........................................���������ҳ�����............................................//
		
		/**ɾ�������С�ԭ����**/
	    
			 if(focut.equals("")||focut==null){}else{operbean.delCMT(oper_id,"cut",focut);}
			 if(fomeasure.equals("")||fomeasure==null){}else{operbean.delCMT(oper_id,"measure",fomeasure);}
			 if(fotool.equals("")||fotool==null){}else{operbean.delCMT(oper_id,"tool",fotool);}
			 if(fomaterial.equals("")||fomaterial==null){}else{operbean.delCMT(oper_id,"material",fomaterial);}
			 if(foaccessory.equals("")||foaccessory==null){}else{operbean.delCMT(oper_id,"accessory",foaccessory);}
		
		

		
		out.flush();
		out.close();
		}catch(Exception e){
			System.out.println("AoOperSvlt ����ʱ���?����Ϊ��"+e);
		}
}
public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
      doPost(request,response);
}
}
