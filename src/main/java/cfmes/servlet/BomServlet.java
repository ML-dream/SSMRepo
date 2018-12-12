package cfmes.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import cfmes.bom.dao.BomBeanDao;
import cfmes.util.*;
import Bom.Bom_Bean;

//import Bom.Tree_session;

public class BomServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Bom_Bean bom_bean = null;
		try {
			DealString ds = new DealString();
			BomBeanDao bombeandao = new BomBeanDao();
			bom_bean = new Bom_Bean();
			// ȡ��session��Ĺ������
			HttpSession session = request.getSession(true);
			String product_id = (String) session.getAttribute("product_id");
			String issue_num = (String) session.getAttribute("issue_num");
			String father_item_id = (String) session
					.getAttribute("father_item_id");
			String level_id = (String) session.getAttribute("level_id");
			System.out.println("level_id=="+level_id);
			String id = (String) session.getAttribute("id");
			String fid = (String) session.getAttribute("fid");
			String item_id = (String) session.getAttribute("item_id");
			// ȡ�ñ?�ύ�����
			String additem_id = ds.toGBK(request.getParameter("additem_id"));
			String additem_num = ds.toGBK(request.getParameter("additem_num"));// ����
			String addscrap = ds.toGBK(request.getParameter("addscrap"));// �����
			String addmemo = ds.toGBK(request.getParameter("addmemo"));// ��ע

			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			// .........................................课添加其他页面操作............................................//
			// 新增BOM
			int level = Integer.parseInt(level_id) + 1;
			System.out.println("level=="+level);
			String level_id2 = level + "";
			if (level_id2.length() == 1) {
				level_id2 = "0" + level_id2;
			}
			boolean in_num = bombeandao.isin_Father(product_id, additem_id,level_id2, item_id, fid, issue_num);
			if (additem_id.equals(item_id)) {
				out.print("<script language=javascript>alert('此物料与父物料重复！请重新输入。。。');window.history.back();</script>");
			} else if (in_num) {
				out.print("<script language=javascript>alert('" + item_id
						+ "下已有物料:" + additem_id
						+ "勿重新输入');window.history.back();</script>");
			} else if (!bombeandao.isin_item(item_id)) {
				out.print("<script language=javascript>alert('此物料在item表中不存在！请重新输入。。。');window.history.back();</script>");
			} else {
				Hashtable ht = new Hashtable();
				ht.put("PRODUCT_ID", product_id);
				ht.put("ISSUE_NUM", issue_num);
				ht.put("FATHER_ITEM_ID", item_id);
				ht.put("ITEM_ID", additem_id);
				ht.put("FID", id);
				ht.put("LEVEL_ID", level_id2);
				ht.put("ITEM_NUM", additem_num);
				ht.put("SCRAP", addscrap);
				ht.put("MEMO", addmemo);
				//返回到查看列表的界面
				bom_bean.addBom(ht);
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("BomServlet处理时出错：错误为：" + e);
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doPost(request, response);
	}
}
