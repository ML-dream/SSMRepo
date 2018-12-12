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
import cfmes.bean.FoOperBean;
import cfmes.bom.dao.FoBeanDao;
import cfmes.bom.dao.MbomDao;
import cfmes.util.DealString;

public class FoOperSvlt extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	   throws ServletException, IOException {
		
		DealString ds = new DealString();
		FoOperBean operbean = new FoOperBean();
		FoBeanDao fobeandao = new FoBeanDao();
		MbomDao mbomdao = new MbomDao();
        
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
		operbean.setFo_no(fo_no);
		operbean.setFover(fover);
	    operbean.setFlight_type(flight_type);
	    operbean.setIssue_num(issue_num);
	    operbean.setItem_id(item_id);
	    operbean.setProduct_id(product_id);
	    
		String oper_id = ds.toString(ds.toGBK((String)request.getParameter("oper_id")));
		String oper_name = new String(request.getParameter("oper_name").getBytes("ISO8859-1"),"utf-8");
		//oper_name = request.getParameter("oper_name").trim();
		String oper_content = new String(request.getParameter("oper_content").trim().getBytes("ISO8859-1"),"utf-8");
		
		System.out.println(oper_name+"  "+oper_content);
	    String oper_time = ds.toString(ds.toGBK((String)request.getParameter("oper_time")));
		String rated_time = ds.toString(ds.toGBK((String)request.getParameter("rated_time")));
		String plan_time = ds.toString(ds.toGBK((String)request.getParameter("plan_time")));
		String oper_aidtime = ds.toString(ds.toGBK((String)request.getParameter("oper_aidtime")));
		String insp_time = ds.toString(ds.toGBK((String)request.getParameter("insp_time")));
		String is_keyop = request.getParameter("is_keyop").trim();
		String is_insp = ds.toString(ds.toGBK((String)request.getParameter("is_insp")));
		String is_arminsp = ds.toString(ds.toGBK((String)request.getParameter("is_arminsp")));
		String is_certop = ds.toString(ds.toGBK((String)request.getParameter("is_certop")));
		String is_co = ds.toString(ds.toGBK((String)request.getParameter("is_co")));
		String wcid = ds.toString(ds.toGBK((String)request.getParameter("wcid")));
		String equiptype = ds.toString(ds.toGBK((String)request.getParameter("equiptype")));
		String equipcode = ds.toString(ds.toGBK((String)request.getParameter("equipcode")));
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
		
		if(dotype.equals("add")){
			/**��߰����ϼ��ϵ�������1����MBOM����ͬ����ͬ�����µĹ������2����ʾMBOM������м������**/
			if(oper_id.length() == 1){
				oper_id="00"+oper_id;
				//oper_id= item_id+oper_id;
			}else if(oper_id.length() == 2){
				oper_id="0"+oper_id;
				//oper_id= item_id+oper_id;
			}
				 Hashtable ht = new Hashtable();
				 ht.put("OPER_ID",oper_id);
				 ht.put("OPER_NAME",oper_name);
				 ht.put("OPER_CONTENT",oper_content);
				 ht.put("OPER_TIME",oper_time);
				 ht.put("RATED_TIME",rated_time);
				 ht.put("PLAN_TIME",plan_time);
				 ht.put("OPER_AIDTIME",oper_aidtime);
				 ht.put("INSP_TIME",insp_time);
				 ht.put("IS_KEYOP",is_keyop);
				 ht.put("IS_INSP",is_insp);
				 ht.put("IS_ARMINSP",is_arminsp);
				 ht.put("IS_CERTOP",is_certop);
				 ht.put("IS_CO",is_co);
				 ht.put("WCID",wcid);
				 ht.put("EQUIPTYPE",equiptype);
				 ht.put("EQUIPCODE",equipcode);
				 
				 if(fobeandao.isinFoOp(product_id,issue_num,fo_no,fover,oper_id)){
				     operbean.modFoOper(ht);
				     if(mbomdao.HasMbom(product_id, issue_num))operbean.AddFoOperUpdateEc(ht);
				 }else{
				 operbean.addFoOper(ht);}
				 out.print("<script>window.location.href='bom_maitnjsp/fo/fo_oper.jsp';</script>");
		}
		
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
			 if(fobeandao.isin(product_id, issue_num, fo_no, fover, fomaterial, oper_id,"tool")){
				 out.print("<script>alert('��ԭ���ϱ���Ѵ����ڸ�FO�����У�δ����ӡ�');window.history.back();</script>");
				 }else{operbean.addFoCMT(oper_id, fomaterial,material_num, "material");}
			 if(fobeandao.isin(product_id, issue_num, fo_no, fover, foaccessory, oper_id,"tool")){
				 out.print("<script>alert('�˸��ϱ���Ѵ����ڸ�FO�����У�δ����ӡ�');window.history.back();</script>");
				 }else{operbean.addFoCMT(oper_id, foaccessory,accessory_num, "accessory");}
		}
		
		/**�޸�*/
		if(dotype.equals("mod")){
			operbean.delFoOper(oper_id);
			out.print("<script>window.location.href='bom_maitnjsp/fo/fo_oper.jsp';</script>");
		}
		/**ɾ��һ��*/
		if(dotype.equals("del")){
			operbean.delFoOper(oper_id);
			   out.print("<script>window.location.href='bom_maitnjsp/fo/fo_oper.jsp';</script>");
		    
		}
		/**ɾ�������Ϣ*/
		if(dotype.equals("delall")){
			String ao_opid4 =  ds.toGBK(request.getParameter("txt_oper_id"));
			operbean.delFoOper2(ao_opid4);
			flight_type = ds.toURL(flight_type);
			product_id = ds.toURL(product_id);
			issue_num = ds.toURL(issue_num);
			item_id = ds.toURL(item_id);
			
				   out.print("<script>window.location.href='Html/capp/fo/fo_oper.jsp';</script>");
			
		}
		/**�����Ϣ*/
		if(dotype.equals("clear")){
			operbean.clearRecord();
			flight_type = ds.toURL(flight_type);
			product_id = ds.toURL(product_id);
			issue_num = ds.toURL(issue_num);
			item_id = ds.toURL(item_id);
			
				   out.print("<script>window.location.href='Html/capp/fo/fo_oper.jsp';</script>");
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
