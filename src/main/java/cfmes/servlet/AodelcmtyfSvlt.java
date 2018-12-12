package cfmes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.bean.AoBean;
import cfmes.bom.dao.FoBeanDao;
import cfmes.util.DealString;

public class AodelcmtyfSvlt extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	   throws ServletException, IOException {
		
		DealString ds = new DealString();
		AoBean aobean = new AoBean();
		FoBeanDao fobeandao = new FoBeanDao();
     
		HttpSession session = request.getSession(true);
		String flight_type = (String)session.getAttribute("flight_type");
		String product_id = (String)session.getAttribute("product_id");
		String issue_num = (String)session.getAttribute("issue_num");
		String item_id = (String)session.getAttribute("item_id");
		
	
		 
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
	    aobean.setFlight_type(flight_type);
	    aobean.setIssue_num(issue_num);
	    aobean.setItem_id(item_id);
	    aobean.setProduct_id(product_id);
	    
		String aono = ds.toString(ds.toGBK((String)request.getParameter("aono2")));
		String aover = ds.toString(ds.toGBK((String)request.getParameter("ao_ver")));
		String aocut = ds.toString(ds.toGBK((String)request.getParameter("ao_cut")));
		String aomeasure = ds.toString(ds.toGBK((String)request.getParameter("ao_measure")));
		String aotool = ds.toString(ds.toGBK((String)request.getParameter("ao_tool")));
		String aomaterial = ds.toString(ds.toGBK((String)request.getParameter("ao_material")));
		String aoaccessory = ds.toString(ds.toGBK((String)request.getParameter("ao_accessory")));
		try{
//		.........................................���������ҳ�����............................................//
		
		/**ɾ�������С�ԭ����**/
		
			 if(aocut.equals("")||aocut==null){}else{aobean.delCMT(aono,aover,"cut",aocut);}
			 if(aomeasure.equals("")||aomeasure==null){}else{aobean.delCMT(aono,aover,"measure",aomeasure);}
			 if(aotool.equals("")||aotool==null){}else{aobean.delCMT(aono,aover,"tool",aotool);}
			 if(aomaterial.equals("")||aomaterial==null){}else{aobean.delCMT(aono,aover,"material",aomaterial);}
			 if(aoaccessory.equals("")||aoaccessory==null){}else{aobean.delCMT(aono,aover,"accessory",aoaccessory);}
		
		

		
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
