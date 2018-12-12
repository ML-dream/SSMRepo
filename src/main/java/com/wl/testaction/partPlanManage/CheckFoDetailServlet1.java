package com.wl.testaction.partPlanManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.FoDetail;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class CheckFoDetailServlet1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "fo_no";
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    String issueNum=ChineseCode.toUTF8(request.getParameter("issueNum"));
	    String productId=ChineseCode.toUTF8(request.getParameter("ProductId"));
	    String sql="select count(*) from fo_detail where productId=? and issueNUM=? and isinuse='1'";
	    String[] params={productId,issueNum};
	    try{
	    	totalCount=Sqlhelper.exeQueryCountNum(sql, params);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    String Sql= "select foId,PRODUCT_TYPE productType,PRODUCT_ID productId,ISSUE_NUM issueNum,ITEM_ID itemId,FO_NO foNo," +
				"FO_OPNAME foOpName,FO_OPCONTENT foOpcontent,RATED_TIME ratedTime,PLAN_TIME planTime,OPER_AIDTIME operAidTime," +
				"INSP_TIME inspTime,OPER_TIME operTime,IS_KEY isKey,IS_INSP isInsp,IS_ARMINSP isArmInsp," +
				"IS_CERTOP isCerTop,IS_CO isCo,WCID WCID,EQUIPTYPE equipType,EQUIPCODE equipCode," +
				"CUT,ACCESSORY,MATERIAL,MEASURE,TOOL," +
				"CUTNUM,ACCESSORYNUM,MATERIALNUM,MEASURENUM,TOOLNUM," +
				"CHANGEPERSON,CHANGETIME,workBranch workBranchId,confirmAdvice," +
				"WB.typename workBranchName  " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from fo_detail EM where product_id=? and issue_num=? and isInUse='1' order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
	    	"left join workbranch WB on WB.typeid=B.workBranch "+
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" and product_id=? and issue_num=?  and isInUse='1' order by "+orderStr;
	    	String[] params2 = {productId,issueNum,productId,issueNum};
	    	List<FoDetail> foDetails = null;
	    	    try {
	    			foDetails = Sqlhelper.exeQueryList(Sql, params2, FoDetail.class);
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
	    		
	    	String json = PluSoft.Utils.JSON.Encode(foDetails);
	    	json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
	    	response.setCharacterEncoding("UTF-8");
	    	response.getWriter().append(json).flush();
	    	System.out.println(json);
	}



}
