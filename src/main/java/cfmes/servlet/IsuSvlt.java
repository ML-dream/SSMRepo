package cfmes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cfmes.util.DealString;
import cfmes.bom.dao.BomBeanDao;
public class IsuSvlt extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DealString ds = new DealString(); 
        BomBeanDao bombeandao = new BomBeanDao();
		
		response.setContentType("text/html");
		response.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
//		out.println("<script>window.history.back();</script>");
//		out.flush();
//		out.close();
//		.........................................可添加其他页面操作............................................//
	/**新增一个BOM*/
		String product_type = ds.toGBK(request.getParameter("product_type"));
		String product_id = ds.toGBK(request.getParameter("product_id"));
		String issue_num = ds.toGBK(request.getParameter("issue_num"));
		String lot = ds.toGBK(request.getParameter("lot"));
		String b_sortie = ds.toGBK(request.getParameter("b_sortie"));if(b_sortie.length()==1){b_sortie="0"+b_sortie;}
		String e_sortie = ds.toGBK(request.getParameter("e_sortie"));if(e_sortie.length()==1){e_sortie="0"+e_sortie;}
		String memo = ds.toGBK(request.getParameter("memo"));
		String fid= "#" ;
		
		try{
		   if(bombeandao.isin_Item(product_id)){
		      if(bombeandao.isin_Bom(product_id,issue_num)){
		         out.print("<script>alert('此部件的BOM结构已存在于数据库中，请重新输入。');window.history.back();</script>");
		      }else{
		          bombeandao.update("INSERT INTO work.ebom (product_id,issue_num,item_id,id,father_item_id,fid,level_id,item_num,scrap,memo)"
			              +" Values ('"+product_id+"','"+issue_num+"','"+product_id+"','"+1+"','"+fid+"','"+1+"','01',"+1+","+0+","+0+")");
		          bombeandao.update("INSERT INTO work.issuedeploy (product_type,product_id,lot,b_sortie,e_sortie,issue_num,memo)"
			               +" Values ('"+product_type+"','"+product_id+"','"+lot+"','"+b_sortie+"','"+e_sortie+"','"+issue_num+"','"+memo+"')");
		          out.print("<script>alert('添加成功,若要对此BOM进行维护,请进入BOM查询与维护页面');window.location.href='bom_maitn_in.jsp';</script>");
		     }
		   }else{
		     out.println("<script>alert('item表中没有此物料编号,请重新输入');window.history.back();</script>");
		   }
			
		}catch(Exception e){
			System.out.println(" 处理时出错；错误为："+e);
		}
		out.flush();
		out.close();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
        doPost(request,response);
    }

}
