package cfmes.servlet;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import Bom.Bom_Bean;
import cfmes.bom.dao.BomBeanDao;
import cfmes.util.DealString;

public class BomdelServlet extends HttpServlet {
	public void doPost(HttpServletRequest request ,HttpServletResponse response)throws IOException, ServletException{
		Bom_Bean bom_bean = null;			
		try{
			DealString ds = new DealString();
			BomBeanDao bombeandao = new BomBeanDao();
			bom_bean = new Bom_Bean();
			//ȡ��session��Ĺ������
			HttpSession session = request.getSession(true);
			String product_id = (String)session.getAttribute("product_id");
			String issue_num = (String)session.getAttribute("issue_num");
			String father_item_id = (String)session.getAttribute("father_item_id");
			String level_id = (String)session.getAttribute("level_id");
			String id = (String)session.getAttribute("id");
			String fid = (String)session.getAttribute("fid");
			String item_id = (String)session.getAttribute("item_id");
			
				response.setContentType("text/html");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
//.........................................���������ҳ�����............................................//
//ɾ��BOM
				if(bombeandao.isin_fbom(item_id)){out.print("<script>alert('������������������ϴ��ڣ������ײ�����Ͽ�ʼɾ��');</script>");
				}else{
				bombeandao.update("DELETE FROM work.ebom where product_id="+"'"+product_id+"'"+"and item_id="+"'"+item_id+"'"+"and issue_num="+"'"+issue_num+"'");
				out.print("<script>alert('ɾ��ɹ���');</script>");
				}
			out.flush();
			out.close();  
		}catch (Exception e){System.out.println("BomServlet����ʱ���?����Ϊ��"+e);}
	}
	public void doGet(HttpServletRequest request ,HttpServletResponse response)throws IOException, ServletException
	{
		doPost(request,response);
	}
}

