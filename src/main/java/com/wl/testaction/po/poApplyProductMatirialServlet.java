package com.wl.testaction.po;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.FoDetail;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class poApplyProductMatirialServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public poApplyProductMatirialServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String productId=request.getParameter("productId");
		String issueNum=request.getParameter("issueNum");
		String orderStr = "PRODUCT_ID";
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int totalCount=0;
		
		String sql= "select B.foId,B.PRODUCT_TYPE productType,B.PRODUCT_ID productId,B.ISSUE_NUM issueNum,B.ITEM_ID itemId,B.FO_NO foNo," +
		"B.FO_OPNAME foOpName,B.FO_OPCONTENT foOpcontent,B.RATED_TIME ratedTime,B.PLAN_TIME planTime,B.OPER_AIDTIME operAidTime," +
		"B.INSP_TIME inspTime,B.OPER_TIME operTime,B.IS_KEY isKey,B.IS_INSP isInsp,B.IS_ARMINSP isArmInsp," +
		"B.IS_CERTOP isCerTop,B.IS_CO isCo,B.WCID,B.EQUIPTYPE,B.EQUIPCODE," +
		"B.CUT,B.ACCESSORY,B.MATERIAL,B.MEASURE,B.TOOL," +
		"B.CUTNUM,B.MEASURENUM,B.TOOLNUM,B.MATERIALNUM,B.ACCESSORYNUM  " +
		"from (select A.*,ROWNUM row_num from (select EM.* from fo_detail EM where product_id=? and issue_num=? and isInUse='1' order by "+orderStr+" asc) A where ROWNUM<="+(pageNow*pageSize)+" order by "+orderStr+") B " +
		"where row_num>="+((pageNow-1)*pageSize+1)+" and product_id=? and issue_num=? and isInUse='1' order by "+orderStr;

		String [] params2 = {productId,issueNum,productId,issueNum};
	    
	    List<FoDetail> foDetails = null;
	    try {
			foDetails = Sqlhelper.exeQueryList(sql, params2, FoDetail.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0,len=foDetails.size();i<len;i++){
			FoDetail foDetail = new FoDetail();
			foDetail = foDetails.get(i);
			if (!StringUtil.isNullOrEmpty(foDetail.getCut())) {
				String[] strArray = foDetail.getCut().split(",");
				String cut = "";
				for (int j = 0; j < strArray.length; j++) {
					String cutSql = "select item_Name from item where item_Id=?";
					String [] paramsCut = {strArray[j]};
					try {
						cut+=Sqlhelper.exeQueryString(cutSql, paramsCut);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				foDetail.setCut(cut);
			}
		}
		
		for(int i=0,len=foDetails.size();i<len;i++){
			FoDetail foDetail = new FoDetail();
			foDetail = foDetails.get(i);
			if (!StringUtil.isNullOrEmpty(foDetail.getAccessory())) {
				String[] strArray = foDetail.getAccessory().split(",");
				String Accessory = "";
				for (int j = 0; j < strArray.length; j++) {
					String cutSql = "select item_Name from item where item_Id=?";
					String [] paramsCut = {strArray[j]};
					try {
						Accessory+=Sqlhelper.exeQueryString(cutSql, paramsCut);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				foDetail.setAccessory(Accessory);
			}
		}
		
		for(int i=0,len=foDetails.size();i<len;i++){
			FoDetail foDetail = new FoDetail();
			foDetail = foDetails.get(i);
			if (!StringUtil.isNullOrEmpty(foDetail.getMaterial())) {
				String[] strArray = foDetail.getMaterial().split(",");
				String Material = "";
				for (int j = 0; j < strArray.length; j++) {
					String cutSql = "select item_Name from item where item_Id=?";
					String [] paramsCut = {strArray[j]};
					try {
						Material+=Sqlhelper.exeQueryString(cutSql, paramsCut);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				foDetail.setMaterial(Material);
			}
		}
		
		for(int i=0,len=foDetails.size();i<len;i++){
			FoDetail foDetail = new FoDetail();
			foDetail = foDetails.get(i);
			if (!StringUtil.isNullOrEmpty(foDetail.getMeasure())) {
				String[] strArray = foDetail.getMeasure().split(",");
				String Measure = "";
				for (int j = 0; j < strArray.length; j++) {
					String cutSql = "select item_Name from item where item_Id=?";
					String [] paramsCut = {strArray[j]};
					try {
						Measure+=Sqlhelper.exeQueryString(cutSql, paramsCut);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				foDetail.setMeasure(Measure);
			}
		}
		
		for(int i=0,len=foDetails.size();i<len;i++){
			FoDetail foDetail = new FoDetail();
			foDetail = foDetails.get(i);
			if (!StringUtil.isNullOrEmpty(foDetail.getTool())) {
				String[] strArray = foDetail.getTool().split(",");
				String Tool = "";
				for (int j = 0; j < strArray.length; j++) {
					String cutSql = "select item_Name from item where item_Id=?";
					String [] paramsCut = {strArray[j]};
					try {
						Tool+=Sqlhelper.exeQueryString(cutSql, paramsCut);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				foDetail.setTool(Tool);
			}
		}
	    
		String json = PluSoft.Utils.JSON.Encode(foDetails);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	}

}
