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

import cfmes.bean.AoBean;
import cfmes.bom.dao.BomBeanDao;
import cfmes.bom.dao.AoBeanDao;
import cfmes.util.DealString;

public class AoSvlt extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	   throws ServletException, IOException {
		
		DealString ds = new DealString();
		AoBean aobean = new AoBean();
		AoBeanDao aobeandao = new AoBeanDao();
		
		PrintWriter out = response.getWriter();
		
		//取得session里的共享数据
		HttpSession session = request.getSession(true);
		String flight_type = (String)session.getAttribute("flight_type");
		String product_id = (String)session.getAttribute("product_id");
		String issue_num = (String)session.getAttribute("issue_num");
		String item_id = (String)session.getAttribute("item_id");
		String ao_no = ds.toGBK(request.getParameter("ao_no"));
		String aover = ds.toGBK(request.getParameter("aover"));
		
		String dotype = ds.toGBK(request.getParameter("dotype"));
		String aoname = ds.toGBK(request.getParameter("aoname"));
		String ao_time = ds.toString(ds.toGBK((String)request.getParameter("ao_time")));
		String workplacecode = ds.toString(ds.toGBK((String)request.getParameter("workplacecode")));
		String workplacename = ds.toString(ds.toGBK((String)request.getParameter("workplacename")));
		String partno = ds.toString(ds.toGBK((String)request.getParameter("partno")));

		/**删除一个*/
		try{
			if(dotype.equals("del")){
			aobean.delAo(flight_type,product_id,issue_num,item_id,ao_no,aover);
			   out.print("<script>window.location.href='bom_maitnjsp/ao/ao.jsp;'</script>");
			}else if(dotype.equals("save")){
				 if(aobeandao.isinAover(product_id,issue_num,ao_no,aover)){//aover没用
				     out.print("<script>alert('此AO的该版本已存在，请重新输入。');window.history.back();</script>");
				 }else{
					 Hashtable ht = new Hashtable();
					 ht.put("FLIGHT_TYPE",flight_type);
					 ht.put("PRODUCT_ID",product_id);
					 ht.put("ISSUE_NUM",issue_num);
					 ht.put("ITEM_ID",item_id);
					 ht.put("AO_NO",ao_no);
					 ht.put("AONAME",aoname);
					 ht.put("AOVER",aover);
					 ht.put("AO_TIME",ao_time);
					 ht.put("WORKPLACECODE",workplacecode);
					 ht.put("WORKPLACENAME",workplacename);
					 ht.put("PARTNO",partno);
					 aobean.addAo(ht);
					 
				 }
			}
		out.flush();
		out.close();
		}catch(Exception e){
			System.out.println("AoSvlt 处理时出错；错误为："+e);
		}
   }
   public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       doPost(request,response);
   }
}
