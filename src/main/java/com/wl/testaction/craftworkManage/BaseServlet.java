package com.wl.testaction.craftworkManage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.StringUtil;

public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 559264262439401995L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String functionName = request.getParameter("meth");
		String pathTo = StringUtil.isNullOrEmpty(request.getParameter("pathTo"))?"":request.getParameter("pathTo");
		if("ProductInfoServlet".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.ProductInfoServlet(request, response, pathTo+".jsp");
		}
		if("Addcraftwork".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.Addcraftwork(request, response, pathTo+".jsp");
		}
		if("Addcraftwork1".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.Addcraftwork1(request, response, pathTo+".jsp");
		}
		if("goaofoselect".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.goaofoselect(request, response, pathTo+".jsp");
		}
		if("CraftworkList".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.CraftworkList(request, response, pathTo+".jsp");
		}
		if("CraftworkList1".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.CraftworkList1(request, response, pathTo+".jsp");
		}
		if("FodetailData".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.FodetailData(request, response, pathTo+".jsp");
		}
		if("Updatacraftwork".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.Updatacraftwork(request, response, pathTo+".jsp");
			String json = "{\"result\":\"操作成功!\"}";
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append(json).flush();
		}
		if("Gofodetail".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();	
		actionServlet.Gofodetail(request, response, pathTo+".jsp");
		}
		if("Goaodetail".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.Goaodetail(request, response, pathTo+".jsp");
		}
		if("AddCMTYF".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.AddCMTYF(request, response, pathTo+".jsp");
		}
		if("Gofocmtyf".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.Gofocmtyf(request, response, pathTo+".jsp");
		}
		if("CMTYFList".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.CMTYFList(request, response, pathTo+".jsp");
		}
		if("focmtyfEdit".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.focmtyfEdit(request, response, pathTo+".jsp");
		}
		if("addAo".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.addAo(request, response, pathTo+".jsp");
		}
		if("AOList".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.AOList(request, response, pathTo+".jsp");
		}
		if("getLazyTree".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.getLazyTree(request, response, pathTo+".jsp");
		}
		if("craftProAllServlet".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.craftProAllServlet(request, response, pathTo+".jsp");
		}
		if("gofoAlldetail".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.gofoAlldetail(request, response, pathTo+".jsp");
		}
		if("updatecraftwork".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.updatecraftwork(request, response, pathTo+".jsp");
		}
		if("AddFoHeader".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.AddFoHeader(request, response, pathTo+".jsp");
		}
		if("AddFoHeader1".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.AddFoHeader1(request, response, pathTo+".jsp");
		}
		if("GoCraftMainBody".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.GoCraftMainBody(request, response, pathTo+".jsp");
		}
		if("GoCraftMainBody1".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.GoCraftMainBody1(request, response, pathTo+".jsp");
		}
		if("updateFoHeader".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.updateFoHeader(request, response, pathTo+".jsp");
		}
		if("GetFoHeader".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.GetFoHeader(request, response, pathTo+".jsp");
		}
		if("GetFoHeader1".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.GetFoHeader1(request, response, pathTo+".jsp");
		}
		if("GetFoHeader2".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.GetFoHeader2(request, response, pathTo+".jsp");
		}
		if("GetAoHeader".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.GetAoHeader(request, response, pathTo+".jsp");
		}
		if("AddAoHeader".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.AddAoHeader(request, response, pathTo+".jsp");
		}
		if("updateAoHeader".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.updateAoHeader(request, response, pathTo+".jsp");
		}
		if("addAoDetail".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.addAoDetail(request, response, pathTo+".jsp");
		}
		if("AodetailData".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.AodetailData(request, response, pathTo+".jsp");
		}
		if("UpdateAodetail".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.UpdateAodetail(request, response, pathTo+".jsp");
		}
		if("GoAocmtyf".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.GoAocmtyf(request, response, pathTo+".jsp");
		}
		if("AddAoCMTYF".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.AddAoCMTYF(request, response, pathTo+".jsp");
		}
		if("AoCMTYFList".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.AoCMTYFList(request, response, pathTo+".jsp");
		}
		if("aocmtyfEdit".equals(functionName)){
			ActionServlet actionServlet = new ActionServlet();
			actionServlet.aocmtyfEdit(request, response, pathTo+".jsp");
		}
	
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
