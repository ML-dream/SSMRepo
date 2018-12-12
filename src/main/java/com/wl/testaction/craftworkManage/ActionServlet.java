/**
 * 项目名称: work
 * 创建日期：2016-6-27
 * 修改历史：
 *		1.[2016-6-27]创建文件 by Flair
 */
package com.wl.testaction.craftworkManage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;

import cfmes.util.sql_data;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.wl.common.ProductStatus;
import com.wl.forms.AoDetail;
import com.wl.forms.AoHeader;
import com.wl.forms.FoDetail;
import com.wl.forms.FoHeader;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.testaction.utils.DocStyleUtils;
import com.wl.testaction.utils.UploadUtils;
import com.wl.tools.ChineseCode;
import com.wl.tools.Mapping;
import com.wl.tools.MyBeanUtil;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;
import com.wl.tools.UUIDHexGenerator;
import com.xm.testaction.qualitycheck.sum.AddNewStuff;

/**
 * @author Flair
 *
 */
public class ActionServlet {
	public void ProductInfoServlet(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		System.out.println(this.getClass().getName());
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
		String FProductId = ChineseCode.toUTF8(request.getParameter("FProductId").trim());
		String issueNum = request.getParameter("issueNum");
		String foType = request.getParameter("foType");		//正常件 or 报废件
		String barcode = request.getParameter("barcode");	
		
//		String orderSql = "select ORDER_ID orderId,PRODUCT_ID productId,ISSUE_NUM issueNum,LOT lot,PURDUCT_NUM productNum," +
//			    	"PRODUCT_STATUS productStatus,FPRODUCT_ID fproductId,PRODUCT_NAME productName,SPEC spec,drawingId," +
//			    	"B.ITEM_TYPEID itemTypeId,C.ITEM_TYPEDESC itemTypeName " +
//			    	"from order_detail A " +
//			    	"left join item B on B.ITEM_ID=A.PRODUCT_ID and B.FITEM_ID=A.FPRODUCT_ID " +
//			    	"left join itemtype C on C.item_typeid=B.item_typeid  " +
//
//			    	"where ORDER_ID=? and PRODUCT_ID=? and FPRODUCT_ID=?";
		String orderSql = "select distinct ORDER_ID orderId,A.PRODUCT_ID productId,A.ISSUE_NUM issueNum,LOT lot, purduct_num productNum," +
    	     "PRODUCT_STATUS productStatus,FPRODUCT_ID fproductId,PRODUCT_NAME productName,SPEC spec,drawingId," +
    	     "B.ITEM_TYPEID itemTypeId,C.ITEM_TYPEDESC itemTypeName,D.CONFIRMADVICE confirmedAdvice " +
    	     "from order_detail A " +
    	     "left join item B on B.ITEM_ID=A.PRODUCT_ID "+
//    	     "left join item B on B.ITEM_ID=A.PRODUCT_ID and B.FITEM_ID=A.FPRODUCT_ID " +
    	     "left join itemtype C on C.item_typeid=B.item_typeid  " +
             "left join fo_detail D on D.product_id=A.product_id and D.issue_num=A.issue_num "+
    	     "where ORDER_ID=? and A.PRODUCT_ID=? and FPRODUCT_ID=?  ";//and D.isinuse='1' and a.issue_num="+issueNum
		String [] params = {orderId,productId,FProductId};
		
		if(foType.equals("1")){
//			orderSql= "select distinct a.ORDER_ID orderId,A.PRODUCT_ID productId,A.ISSUE_NUM issueNum,LOT lot, purduct_num productNum,PRODUCT_STATUS productStatus, " +
//					"FPRODUCT_ID fproductId,PRODUCT_NAME productName,SPEC spec,a.drawingId,B.ITEM_TYPEID itemTypeId,C.ITEM_TYPEDESC itemTypeName, " +
//					"e.confirmadvice confirmedAdvice from order_detail A " +
//					"left join item B on B.ITEM_ID=A.PRODUCT_ID " +
//					" left join itemtype C on C.item_typeid=B.item_typeid " +
//					" left join fo_detail D on D.product_id=A.product_id and D.issue_num=A.issue_num " +
//					" left join todiscard e on e.order_id=a.order_id and e.product_id=A.product_id and e.issue_num=A.issue_num " +
//					"where e.barcode='"+barcode+"'";
		orderSql= "select distinct a.ORDER_ID orderId,A.PRODUCT_ID productId,A.ISSUE_NUM issueNum,LOT lot, e.rejectNum productNum,PRODUCT_STATUS productStatus, " +
					"FPRODUCT_ID fproductId,PRODUCT_NAME productName,SPEC spec,a.drawingId,B.ITEM_TYPEID itemTypeId,C.ITEM_TYPEDESC itemTypeName, " +
					"e.confirmadvice confirmedAdvice from order_detail A " +
					"left join item B on B.ITEM_ID=A.PRODUCT_ID " +
					" left join itemtype C on C.item_typeid=B.item_typeid " +
					" left join fo_detail D on D.product_id=A.product_id and D.issue_num=A.issue_num  " +
					" left join todiscard e on e.order_id=a.order_id and e.product_id=A.product_id and e.issue_num=A.issue_num " +
					"where e.barcode='"+barcode+"' and D.isinuse='0' ";
			params = null;
		}
		System.out.println(orderSql);
		
		Order order = new Order();
		try {
			order = Sqlhelper.exeQueryBean(orderSql, params, Order.class);
			System.out.println(order.getProductNum());
			order.setFoType(foType);	//xiem   正常件、报废件
			if(foType.equals("1")){
//				order.setConfirmedAdvice("");	//如果是报废件，就不加载原来的审核意见
				order.setBarcode(barcode);
			}
			request.setAttribute("result", order);
		}  catch (SQLException e) {
			e.printStackTrace();
		}  catch (Exception e1) {
			e1.printStackTrace();
		}
		if (!"".equals(pathTo)) {
			request.getRequestDispatcher("craftworkManage/"+pathTo).forward(request, response);
		}
	}
	
	public void ProductInfoServlet1(HttpServletRequest request, HttpServletResponse response,String pathTo)
			throws ServletException, IOException {
				String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
				String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
				String FProductId = ChineseCode.toUTF8(request.getParameter("FProductId").trim());
				String barcode = request.getParameter("barcode");
				String issueNum = request.getParameter("issueNum");
				String orderSql = "select distinct A.ORDER_ID orderId,A.PRODUCT_ID productId,A.ISSUE_NUM issueNum,LOT lot,e.rejectnum productNum," +
		    	     "PRODUCT_STATUS productStatus,FPRODUCT_ID fproductId,PRODUCT_NAME productName,SPEC spec,A.drawingId," +
		    	     "B.ITEM_TYPEID itemTypeId,C.ITEM_TYPEDESC itemTypeName,D.CONFIRMADVICE confirmedAdvice " +
		    	     "from order_detail A " +
		    	     "left join item B on B.ITEM_ID=A.PRODUCT_ID "+
//		    	     "left join item B on B.ITEM_ID=A.PRODUCT_ID and B.FITEM_ID=A.FPRODUCT_ID " +
		    	     "left join itemtype C on C.item_typeid=B.item_typeid  " +
		             "left join fo_detail D on D.product_id=A.product_id and D.issue_num=A.issue_num "+
		    	     "left join todiscard E on E.ORDER_ID=A.order_id and E.product_id=A.product_id "+
		    	     "where a.ORDER_ID=? and a.PRODUCT_ID=? and FPRODUCT_ID=? and D.isinuse='1' and a.ISSUE_NUM="+issueNum;
				System.out.println(orderSql);
				String [] params = {orderId,productId,FProductId};
				Order order = new Order();
				try {
					order = Sqlhelper.exeQueryBean(orderSql, params, Order.class);
					order.setBarcode(barcode);
					request.setAttribute("result", order);
				}  catch (SQLException e) {
					e.printStackTrace();
				}  catch (Exception e1) {
					e1.printStackTrace();
				}
				if (!"".equals(pathTo)) {
					request.getRequestDispatcher("craftworkManage/"+pathTo).forward(request, response);
				}
			}
	
	public void ProductInfoServlet2(HttpServletRequest request, HttpServletResponse response,String pathTo)
			throws ServletException, IOException {
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
		String FProductId = ChineseCode.toUTF8(request.getParameter("FProductId").trim());
		
//		String orderSql = "select ORDER_ID orderId,PRODUCT_ID productId,ISSUE_NUM issueNum,LOT lot,PURDUCT_NUM productNum," +
//			    	"PRODUCT_STATUS productStatus,FPRODUCT_ID fproductId,PRODUCT_NAME productName,SPEC spec,drawingId," +
//			    	"B.ITEM_TYPEID itemTypeId,C.ITEM_TYPEDESC itemTypeName " +
//			    	"from order_detail A " +
//			    	"left join item B on B.ITEM_ID=A.PRODUCT_ID and B.FITEM_ID=A.FPRODUCT_ID " +
//			    	"left join itemtype C on C.item_typeid=B.item_typeid  " +
//
//			    	"where ORDER_ID=? and PRODUCT_ID=? and FPRODUCT_ID=?";
		String orderSql = "select distinct ORDER_ID orderId,A.PRODUCT_ID productId,A.ISSUE_NUM issueNum,LOT lot, purduct_num productNum," +
    	     "PRODUCT_STATUS productStatus,FPRODUCT_ID fproductId,PRODUCT_NAME productName,SPEC spec,drawingId," +
    	     "B.ITEM_TYPEID itemTypeId,C.ITEM_TYPEDESC itemTypeName,D.CONFIRMADVICE confirmedAdvice,to_char(a.e_time,'yyyy-MM-dd') eTime " +
    	     "from order_detail A " +
    	     "left join item B on B.ITEM_ID=A.PRODUCT_ID "+
//    	     "left join item B on B.ITEM_ID=A.PRODUCT_ID and B.FITEM_ID=A.FPRODUCT_ID " +
    	     "left join itemtype C on C.item_typeid=B.item_typeid  " +
             "left join fo_detail D on D.product_id=A.product_id and D.issue_num=A.issue_num "+
    	     "where ORDER_ID=? and A.PRODUCT_ID=? and FPRODUCT_ID=?  ";//and D.isinuse='1'
		System.out.println(orderSql);
		String [] params = {orderId,productId,FProductId};
		Order order = new Order();
		try {
			order = Sqlhelper.exeQueryBean(orderSql, params, Order.class);
			request.setAttribute("result", order);
		}  catch (SQLException e) {
			e.printStackTrace();
		}  catch (Exception e1) {
			e1.printStackTrace();
		}
		if (!"".equals(pathTo)) {
			request.getRequestDispatcher("craftworkManage/"+pathTo).forward(request, response);
		}
			}
			
	public void Addcraftwork(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		Map<String, String> requestValueMap = new HashMap<String, String>();	//用来封装request请求中的参数
		
		HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	   
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
//	    RequestCloneable reqCloneable = new  RequestCloneable(request);
//	    HttpServletRequest req = null;
//		try {
//			req = reqCloneable.clone();
//		} catch (CloneNotSupportedException e2) {
//			e2.printStackTrace();
//		}
//		HttpServletRequest reqCloned = new MultipartRequestWrapper(req);
//	    String orderId = reqCloned.getParameter("orderId");
	    
		try {
			requestValueMap = UploadUtils.UploadLoadFile(request, response, createPerson, requestValueMap);	//保存文件+得到request属性值
		} catch (FileUploadException e1) {
			String json = "{\"result\":\"文件太大，请检查文件上大小！！\"}";
			response.getWriter().append(json).flush();
			e1.printStackTrace();
		}
		
		FoDetail foDetail = Mapping.convertObjectFromMap(FoDetail.class, requestValueMap);
		
		foDetail.getFoNo();
		
//		去掉fono里开头的0
		int fono=11012;
		try {
			fono= Integer.parseInt(foDetail.getFoNo());
		} catch (Exception e) {
			// TODO: handle exception
		}
		String fo ="";
		if(fono==11012){
			fo= foDetail.getFoNo();
		}else{
			fo= fono+"";
		}

		String sql = "insert into fo_detail " +
				"(foId,FO_NO,FO_OPNAME,OPER_TIME,RATED_TIME,PLAN_TIME," +
				"OPER_AIDTIME,FO_OPCONTENT,INSP_TIME,WCID,EQUIPTYPE," +
				"EQUIPCODE,IS_KEY,IS_INSP,IS_ARMINSP,IS_CERTOP," +
				"IS_CO,product_type,product_id,issue_num,workBranch," +
				"createPerson,createTime,changePerson,changeTime)" +
				"values" +
				"(?,?,?,?,?,?," +
				"?,?,?,?,?," +
				"?,?,?,?,?," +
				"?,?,?,?,?," +
				"?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'),?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'))";
		String[] params = {foDetail.getFoId(),fo,foDetail.getFoOpName(),foDetail.getOperTime()+"",foDetail.getRatedTime()+"",foDetail.getPlanTime()+"",
				foDetail.getOperAidTime()+"",foDetail.getFoOpcontent(),foDetail.getInspTime()+"",foDetail.getWCID(),foDetail.getEquipType(),
				foDetail.getEquipCode(),foDetail.getIsKey(),foDetail.getIsInsp(),foDetail.getIsArmInsp(),foDetail.getIsCerTop(),
				foDetail.getIsCo(),foDetail.getProductType(),foDetail.getProductId(),foDetail.getIssueNum(),foDetail.getWorkBranchId(),
				createPerson,createTime,changePerson,changeTime};
		System.out.println("sql="+sql);
		
//		String ProductStatusSql = "update order_detail set product_status=? " +
//				"where order_id=? and product_id=? and issue_num=? and drawingId=? ";
//		String[] params2 = {ProductStatus.FODOING+"",foDetail.getOrderId(),foDetail.getProductId(),foDetail.getIssueNum(),foDetail.getDrawingId()};
		
		try {
			Sqlhelper.executeUpdate(sql, params);
//			Sqlhelper.executeUpdate(ProductStatusSql, params2);
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (Exception e) {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}
	
	public void Addcraftwork1(HttpServletRequest request, HttpServletResponse response,String pathTo)
			throws ServletException, IOException {
				response.setCharacterEncoding("UTF-8");
				Map<String, String> requestValueMap = new HashMap<String, String>();	//用来封装request请求中的参数
				HttpSession session = request.getSession();
			    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
			    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	
			    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			    String createTime = df.format(new Date());
			    String changeTime = df.format(new Date());
			    
			    //String machineTime01 =ChineseCode.toUTF8(requestValueMap.get("machineTime01").trim())+ requestValueMap.get("machineTime0101").trim();
			    
//			    RequestCloneable reqCloneable = new  RequestCloneable(request);
//			    HttpServletRequest req = null;
//				try {
//					req = reqCloneable.clone();
//				} catch (CloneNotSupportedException e2) {
//					e2.printStackTrace();
//				}
//				HttpServletRequest reqCloned = new MultipartRequestWrapper(req);
//			    String orderId = reqCloned.getParameter("orderId");
			    
				try {
					requestValueMap = UploadUtils.UploadLoadFile(request, response, createPerson, requestValueMap);	//保存文件+得到request属性值
				} catch (FileUploadException e1) {
					String json = "{\"result\":\"文件太大，请检查文件上大小！！\"}";
					response.getWriter().append(json).flush();
					e1.printStackTrace();
				}
				
				FoDetail foDetail = Mapping.convertObjectFromMap(FoDetail.class, requestValueMap);
				
				foDetail.getFoNo();
				int totalCount=0;
			    String totalCountSql = "select count(*) from foheader where orderId=? and productId=? and issuenum=?";
			    String[] params1 = {foDetail.getOrderId(),foDetail.getProductId(),foDetail.getIssueNum()}; 
			    try {
			    	totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, params1);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    String processissuenum=totalCount+"";
			    
			    System.out.println(foDetail.getCraftPaper());
				String sql = "insert into fo_detail " +
						"(fo_id,foId,FO_NO,FO_OPNAME,OPER_TIME,RATED_TIME,PLAN_TIME," +
						"OPER_AIDTIME,FO_OPCONTENT,INSP_TIME,WCID,EQUIPTYPE," +
						"EQUIPCODE,IS_KEY,IS_INSP,IS_ARMINSP,IS_CERTOP," +
						"IS_CO,product_type,product_id,issue_num,workBranch," +
						"createPerson,createTime,changePerson,changeTime,processissue_num,craftpaper)" +
						"values" +
						"(?,?,?,?,?,?,?," +
						"?,?,?,?,?," +
						"?,?,?,?,?," +
						"?,?,?,?,?," +
						"?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'),?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'),?,?)";
//				去掉fono里开头的0
				int fono=11012;
				try {
					fono= Integer.parseInt(foDetail.getFoNo());
				} catch (Exception e) {
					// TODO: handle exception
				}
				String fo ="";
				if(fono==11012){
					fo= foDetail.getFoNo();
				}else{
					fo= fono+"";
				}
				String foIdId=foDetail.getProductId()+fo;//这个代表工序唯一的id，对应表中的FO_id,注意不要和里面的foid混淆，插入的原因是为了和machineinfo_time表格形成对应！
				
				String[] params = {foIdId,foDetail.getFoId(),fo,foDetail.getFoOpName(),foDetail.getOperTime()+"",foDetail.getRatedTime()+"",foDetail.getPlanTime()+"",
						foDetail.getOperAidTime()+"",foDetail.getFoOpcontent(),foDetail.getInspTime()+"",foDetail.getWCID(),foDetail.getEquipType(),
						foDetail.getEquipCode(),foDetail.getIsKey(),foDetail.getIsInsp(),foDetail.getIsArmInsp(),foDetail.getIsCerTop(),
						foDetail.getIsCo(),foDetail.getProductType(),foDetail.getProductId(),foDetail.getIssueNum(),foDetail.getWorkBranchId(),
						createPerson,createTime,changePerson,changeTime,processissuenum,foDetail.getCraftPaper()};
	
	/**************************************************************开始插入设备时间等具体信息**************************************************************************/
				String[] machineParams= {foDetail.getOrderId(),foIdId,foDetail.getEquipCode(),foDetail.getMachineName(),foDetail.getMachineTime01()+foDetail.getMachineTime0101(),foDetail.getEquipCode()+foDetail.getMachineTime01()+foDetail.getMachineTime0101()};
				
				String machineInfoTime_Sql = "insert into machineinfo_time " +
						"(orderId,fo_id,machineId,machineName,machineTime,MACHINEID_MACHINETIME)" +
						"values" +
						"(?,?," +
						"?,?," +
						"?,?)";
				
				
				
				System.out.println("sql="+sql);
				System.out.println("machineInfoTime_Sql="+machineInfoTime_Sql);
//				String ProductStatusSql = "update order_detail set product_status=? " +
//						"where order_id=? and product_id=? and issue_num=? and drawingId=? ";
//				String[] params2 = {ProductStatus.FODOING+"",foDetail.getOrderId(),foDetail.getProductId(),foDetail.getIssueNum(),foDetail.getDrawingId()};
				
				String result = "操作成功";
				String json = "";
				
				
				try {
					Sqlhelper.executeUpdate(sql, params);
//					Sqlhelper.executeUpdate(ProductStatusSql, params2);
					
				} catch (Exception e) {
					result = "操作失败";
					e.printStackTrace();
				}
				try {
					Sqlhelper.executeUpdate(machineInfoTime_Sql, machineParams);
					
				} catch (Exception e) {
					result = "操作失败";
					e.printStackTrace();
				}
				
				 json = "{\"result\":\"操作成功!\"}";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
			}
			    
	public void goaofoselect(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String productType = request.getParameter("productType");
		String productId = request.getParameter("productId");
		String issueNum = request.getParameter("issueNum");
		System.out.println(productType);
		request.setAttribute("productType", productType);
		request.setAttribute("productId", productId);
		request.setAttribute("issueNum", issueNum);
		request.getRequestDispatcher("craftworkManage/"+pathTo).forward(request, response);
	}
	
	public void CraftworkList(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String productId = request.getParameter("productId");
		String issueNum = request.getParameter("issueNum");
		
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "fo_no";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	    String totalCountSql = "select count(*) from fo_detail where product_id=? and issue_num=? and isInUse='1'";
	    String[] params = {productId,issueNum}; 
	    try {
	    	totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, params);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
//		2016-12-18   修改工艺内容为 工艺文件   修改foOpcontent 为 craftpaper
	    String sql= "select foId,PRODUCT_TYPE productType,PRODUCT_ID productId,ISSUE_NUM issueNum,ITEM_ID itemId,FO_NO foNo," +
				"FO_OPNAME foOpName,craftpaper foOpcontent,RATED_TIME ratedTime,PLAN_TIME planTime,OPER_AIDTIME operAidTime," +
				"INSP_TIME inspTime,OPER_TIME operTime,IS_KEY isKey,IS_INSP isInsp,IS_ARMINSP isArmInsp," +
				"IS_CERTOP isCerTop,IS_CO isCo,WCID WCID,EQUIPTYPE equipType,EQUIPCODE equipCode," +
				"CUT,ACCESSORY,MATERIAL,MEASURE,TOOL," +
				"CUTNUM,ACCESSORYNUM,MATERIALNUM,MEASURENUM,TOOLNUM," +
				"CHANGEPERSON,CHANGETIME,workBranch workBranchId,confirmAdvice," +
				"WB.typename workBranchName  " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from fo_detail EM where product_id=? and issue_num=? and isInUse='1' order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
	    	"left join workbranch WB on WB.typeid=B.workBranch "+
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" and product_id=? and issue_num=? and isInUse='1' order by to_number("+orderStr+")";
	
	    String[] params2 = {productId,issueNum,productId,issueNum};
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
						cut+=Sqlhelper.exeQueryString(cutSql, paramsCut)+",";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				cut = cut.substring(0,cut.length()-1);
				foDetail.setCutName(cut);
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
						Accessory+=Sqlhelper.exeQueryString(cutSql, paramsCut)+",";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Accessory = Accessory.substring(0,Accessory.length()-1);
				foDetail.setAccessoryName(Accessory);
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
						Material+=Sqlhelper.exeQueryString(cutSql, paramsCut)+",";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Material = Material.substring(0,Material.length()-1);
				foDetail.setMaterialName(Material);
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
						Measure+=Sqlhelper.exeQueryString(cutSql, paramsCut)+",";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Measure = Measure.substring(0,Measure.length()-1);
				foDetail.setMeasureName(Measure);
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
						Tool+=Sqlhelper.exeQueryString(cutSql, paramsCut)+",";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Tool = Tool.substring(0,Tool.length()-1);
				foDetail.setToolName(Tool);
			}
		}
		
		String json = PluSoft.Utils.JSON.Encode(foDetails);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
		
	}
	
	public void CraftworkList1(HttpServletRequest request, HttpServletResponse response,String pathTo)
			throws ServletException, IOException {
				String productId = request.getParameter("productId");
				String issueNum = request.getParameter("issueNum");
//				String processIssueNum=request.getParameter("processissuenum");
				int pageNo=0;
			    int countPerPage=10;
			    int totalCount = 0;
			    String orderStr = "fo_no";
			    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
			    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
			    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
			    
			    String totalCountSql = "select count(*) from fo_detail where product_id=? and issue_num=?  and isInUse='1'";
			    String[] params = {productId,issueNum}; 
			    try {
			    	totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, params);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    String sql= "select foId,PRODUCT_TYPE productType,PRODUCT_ID productId,ISSUE_NUM issueNum,ITEM_ID itemId,FO_NO foNo," +
						"FO_OPNAME foOpName,FO_OPCONTENT foOpcontent,RATED_TIME ratedTime,PLAN_TIME planTime,OPER_AIDTIME operAidTime," +
						"INSP_TIME inspTime,OPER_TIME operTime,IS_KEY isKey,IS_INSP isInsp,IS_ARMINSP isArmInsp," +
						"IS_CERTOP isCerTop,IS_CO isCo,WCID WCID,EQUIPTYPE equipType,EQUIPCODE equipCode," +
						"CUT,ACCESSORY,MATERIAL,MEASURE,TOOL," +
						"CUTNUM,ACCESSORYNUM,MATERIALNUM,MEASURENUM,TOOLNUM," +
						"CHANGEPERSON,CHANGETIME,workBranch workBranchId,confirmAdvice," +
						"WB.typename workBranchName  " +
			    	"from (select A.*,ROWNUM row_num from (select EM.* from fo_detail EM where product_id=? and issue_num=?  and isInUse='1' order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
			    	"left join workbranch WB on WB.typeid=B.workBranch "+
			    	"where row_num>="+((pageNo-1)*countPerPage+1)+" and product_id=? and issue_num=?  and isInUse='1' order by to_number("+orderStr+")";
			
			    String[] params2 = {productId,issueNum,productId,issueNum};
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
								cut+=Sqlhelper.exeQueryString(cutSql, paramsCut)+",";
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						cut = cut.substring(0,cut.length()-1);
						foDetail.setCutName(cut);
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
								Accessory+=Sqlhelper.exeQueryString(cutSql, paramsCut)+",";
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						Accessory = Accessory.substring(0,Accessory.length()-1);
						foDetail.setAccessoryName(Accessory);
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
								Material+=Sqlhelper.exeQueryString(cutSql, paramsCut)+",";
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						Material = Material.substring(0,Material.length()-1);
						foDetail.setMaterialName(Material);
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
								Measure+=Sqlhelper.exeQueryString(cutSql, paramsCut)+",";
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						Measure = Measure.substring(0,Measure.length()-1);
						foDetail.setMeasureName(Measure);
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
								Tool+=Sqlhelper.exeQueryString(cutSql, paramsCut)+",";
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						Tool = Tool.substring(0,Tool.length()-1);
						foDetail.setToolName(Tool);
					}
				}
				
				String json = PluSoft.Utils.JSON.Encode(foDetails);
				json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
				System.out.println(json);
				
			}
	public void FodetailData(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String productId = request.getParameter("productId");
		String issueNum = request.getParameter("issueNum");
		String foNo	= request.getParameter("foNo");
		String foId	= request.getParameter("foId");
		
		String sql = "select foId,PRODUCT_TYPE productType,PRODUCT_ID productId,ISSUE_NUM issueNum,ITEM_ID itemId,FO_NO foNo," +
				"FO_OPNAME foOpName,FO_OPCONTENT foOpcontent,RATED_TIME ratedTime,PLAN_TIME planTime,OPER_AIDTIME operAidTime," +
				"INSP_TIME inspTime,OPER_TIME operTime,IS_KEY isKey,IS_INSP isInsp,IS_ARMINSP isArmInsp," +
				"IS_CERTOP isCerTop,IS_CO isCo,WCID,EQUIPTYPE,EQUIPCODE," +
				"DEPARTMENT,ISFIRST,ISASSEMBE,workBranch workBranchId,B.typeName workBranchName," +
				"A.craftPaper  " +
				"from fo_detail A  " +
				"left join workbranch B on B.typeId=A.workBranch "+
				"where product_id=? and issue_num=? and fo_no=? and foId=? and a.isinuse='1' ";
		String[] params = {productId,issueNum,foNo,foId};
		FoDetail foDetail = new FoDetail();
		try {
			foDetail = Sqlhelper.exeQueryBean(sql, params, FoDetail.class);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		request.setAttribute("result", foDetail);

		request.getRequestDispatcher("craftworkManage/"+pathTo).forward(request, response);
	}
	
	public void Updatacraftwork(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		Map<String, String> requestValueMap = new HashMap<String, String>();	//用来封装request请求中的参数
		
		HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    
		try {
			requestValueMap = UploadUtils.UploadLoadFile(request, response, createPerson, requestValueMap);	//保存文件+得到request属性值
		} catch (FileUploadException e1) {
			String json = "{\"result\":\"文件太大，请检查文件上大小！！\"}";
			response.getWriter().append(json).flush();
			e1.printStackTrace();
		}
		String isInUse = request.getParameter("isInUse");
		requestValueMap.put("isInUse", isInUse);
		FoDetail foDetail = Mapping.convertObjectFromMap(FoDetail.class, requestValueMap);
		
		if(isInUse.equals("0")){
			String sql = "update fo_detail set " +
			"FO_OPNAME=?,RATED_TIME=?,PLAN_TIME=?,OPER_AIDTIME=?," +
			"INSP_TIME=?,OPER_TIME=?,IS_KEY=?,IS_INSP=?,IS_ARMINSP=?," +
			"IS_CERTOP=?,IS_CO=?,WCID=?,EQUIPTYPE=?,EQUIPCODE=?," +
			"changeperson=?,changeTime=to_date(?,'yyyy-mm-dd,hh24:mi:ss'),isInUse=? "+
			"where  product_id=? and issue_num=? and fo_no=? and isinuse=1 ";	//如果有删除的工序，会出现违反主键约束报错。
			String []params = new String[]{foDetail.getFoOpName(),foDetail.getRatedTime()+"",foDetail.getPlanTime()+"",foDetail.getOperAidTime()+"",
				foDetail.getInspTime()+"",foDetail.getOperTime()+"",foDetail.getIsKey(),foDetail.getIsInsp(),foDetail.getIsArmInsp(),
				foDetail.getIsCerTop(),foDetail.getIsCo(),foDetail.getWCID(),foDetail.getEquipType(),foDetail.getEquipCode(),
				changePerson,changeTime,"0",
				foDetail.getProductId(),foDetail.getIssueNum(),foDetail.getFoNo()};
			try {
				Sqlhelper.executeUpdate(sql, params);
				String json = "{\"result\":\"操作成功!\"}";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
			} catch (Exception e) {
				String json = "{\"result\":\"操作失败!\"}";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
				e.printStackTrace();
			}
			return;
		}
		
		String[] params ;
		
		String sql = "";
		if (StringUtil.isNullOrEmpty(foDetail.getFoOpcontent())&&StringUtil.isNullOrEmpty(foDetail.getCraftPaper())) {
			sql = "update fo_detail set " +
				"FO_OPNAME=?,RATED_TIME=?,PLAN_TIME=?,OPER_AIDTIME=?," +
				"INSP_TIME=?,OPER_TIME=?,IS_KEY=?,IS_INSP=?,IS_ARMINSP=?," +
				"IS_CERTOP=?,IS_CO=?,WCID=?,EQUIPTYPE=?,EQUIPCODE=?," +
				"changeperson=?,changeTime=to_date(?,'yyyy-mm-dd,hh24:mi:ss'),isInUse=? "+
				"where  product_id=? and issue_num=? and fo_no=?  and isinuse=1  ";
			params = new String[]{foDetail.getFoOpName(),foDetail.getRatedTime()+"",foDetail.getPlanTime()+"",foDetail.getOperAidTime()+"",
					foDetail.getInspTime()+"",foDetail.getOperTime()+"",foDetail.getIsKey(),foDetail.getIsInsp(),foDetail.getIsArmInsp(),
					foDetail.getIsCerTop(),foDetail.getIsCo(),foDetail.getWCID(),foDetail.getEquipType(),foDetail.getEquipCode(),
					changePerson,changeTime,"1",
					foDetail.getProductId(),foDetail.getIssueNum(),foDetail.getFoNo()};
		}else {
			if (!StringUtil.isNullOrEmpty(foDetail.getFoOpcontent())&&StringUtil.isNullOrEmpty(foDetail.getCraftPaper())) {
				sql = "update fo_detail set " +
					"FO_OPNAME=?,FO_OPCONTENT=?,RATED_TIME=?,PLAN_TIME=?,OPER_AIDTIME=?," +
					"INSP_TIME=?,OPER_TIME=?,IS_KEY=?,IS_INSP=?,IS_ARMINSP=?," +
					"IS_CERTOP=?,IS_CO=?,WCID=?,EQUIPTYPE=?,EQUIPCODE=?," +
					"changeperson=?,changeTime=to_date(?,'yyyy-mm-dd,hh24:mi:ss'),isInUse=? "+
					"where  product_id=? and issue_num=? and fo_no=?  and isinuse=1 ";
				params = new String[]{foDetail.getFoOpName(),foDetail.getFoOpcontent(),foDetail.getRatedTime()+"",foDetail.getPlanTime()+"",foDetail.getOperAidTime()+"",
						foDetail.getInspTime()+"",foDetail.getOperTime()+"",foDetail.getIsKey(),foDetail.getIsInsp(),foDetail.getIsArmInsp(),
						foDetail.getIsCerTop(),foDetail.getIsCo(),foDetail.getWCID(),foDetail.getEquipType(),foDetail.getEquipCode(),
						changePerson,changeTime,"1",
						foDetail.getProductId(),foDetail.getIssueNum(),foDetail.getFoNo()};
			}else {
				if (StringUtil.isNullOrEmpty(foDetail.getFoOpcontent())&&!StringUtil.isNullOrEmpty(foDetail.getCraftPaper())) {
					sql = "update fo_detail set " +
						"FO_OPNAME=?,RATED_TIME=?,PLAN_TIME=?,OPER_AIDTIME=?," +
						"INSP_TIME=?,OPER_TIME=?,IS_KEY=?,IS_INSP=?,IS_ARMINSP=?," +
						"IS_CERTOP=?,IS_CO=?,WCID=?,EQUIPTYPE=?,EQUIPCODE=?," +
						"changeperson=?,changeTime=to_date(?,'yyyy-mm-dd,hh24:mi:ss'),isInUse=?,craftPaper=? "+
						"where  product_id=? and issue_num=? and fo_no=?  and isinuse=1 ";
					params = new String[]{foDetail.getFoOpName(),foDetail.getRatedTime()+"",foDetail.getPlanTime()+"",foDetail.getOperAidTime()+"",
							foDetail.getInspTime()+"",foDetail.getOperTime()+"",foDetail.getIsKey(),foDetail.getIsInsp(),foDetail.getIsArmInsp(),
							foDetail.getIsCerTop(),foDetail.getIsCo(),foDetail.getWCID(),foDetail.getEquipType(),foDetail.getEquipCode(),
							changePerson,changeTime,"1",foDetail.getCraftPaper(),
							foDetail.getProductId(),foDetail.getIssueNum(),foDetail.getFoNo()};
				}else {
					sql = "update fo_detail set " +
						"FO_OPNAME=?,FO_OPCONTENT=?,RATED_TIME=?,PLAN_TIME=?,OPER_AIDTIME=?," +
						"INSP_TIME=?,OPER_TIME=?,IS_KEY=?,IS_INSP=?,IS_ARMINSP=?," +
						"IS_CERTOP=?,IS_CO=?,WCID=?,EQUIPTYPE=?,EQUIPCODE=?," +
						"changeperson=?,changeTime=to_date(?,'yyyy-mm-dd,hh24:mi:ss'),isInUse=?,craftPaper=? "+
						"where  product_id=? and issue_num=? and fo_no=?  and isinuse=1 ";
					params = new String[]{foDetail.getFoOpName(),foDetail.getFoOpcontent(),foDetail.getRatedTime()+"",foDetail.getPlanTime()+"",foDetail.getOperAidTime()+"",
							foDetail.getInspTime()+"",foDetail.getOperTime()+"",foDetail.getIsKey(),foDetail.getIsInsp(),foDetail.getIsArmInsp(),
							foDetail.getIsCerTop(),foDetail.getIsCo(),foDetail.getWCID(),foDetail.getEquipType(),foDetail.getEquipCode(),
							changePerson,changeTime,"1",foDetail.getCraftPaper(),
							foDetail.getProductId(),foDetail.getIssueNum(),foDetail.getFoNo()};
				}
			}
		}
		
			
	    
		try {
			Sqlhelper.executeUpdate(sql, params);
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (Exception e) {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}
	
	public void Gofodetail(HttpServletRequest request, HttpServletResponse response,String pathTo) throws ServletException, IOException 
	{
//		String foNo = request.getParameter("foNo");
		String productType = request.getParameter("productType");
		String issueNum = request.getParameter("issueNum");
		String productId = request.getParameter("productId");
		String orderId = request.getParameter("orderId");
		String foId = request.getParameter("foId");
		String drawingId = request.getParameter("drawingId");
	    String totalCountSql = "select count(*) from foheader where orderId=? and productId=? and issuenum=?";
	    String sql="select isWaiXie from order_detail where order_Id=? and product_Id=? and issue_num=?";
	    String [] params = {orderId,productId,issueNum};
	    int totalCount=0;
	    Order order = new Order();
		try {
			order = Sqlhelper.exeQueryBean(sql, params, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
//	    try {
//			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, params);
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//	    request.setAttribute("processIssueNum", totalCount);
//		request.setAttribute("foNo", foNo);
		request.setAttribute("productId", productId);
		request.setAttribute("issueNum", issueNum);
		request.setAttribute("productType", productType);
		request.setAttribute("orderId", orderId);
		request.setAttribute("foId", foId);
		request.setAttribute("drawingId", drawingId);
		request.setAttribute("order", order);
		request.getRequestDispatcher("craftworkManage/"+pathTo).forward(request, response);
	}
	
	public void Goaodetail(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String productType = request.getParameter("productType");
		String productId = request.getParameter("productId");
		String issueNum = request.getParameter("issueNum");
		String aoId = request.getParameter("aoId");
		String orderId = request.getParameter("orderId");
		
		request.setAttribute("productType", productType);
		request.setAttribute("productId", productId);
		request.setAttribute("issueNum", issueNum);
		request.setAttribute("aoId", aoId);
		request.setAttribute("orderId", orderId);
		request.getRequestDispatcher("craftworkManage/"+pathTo).forward(request, response);
	}
	
	public void AddCMTYF(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String productId = ChineseCode.toUTF8(request.getParameter("productId"));
		String issueNum = ChineseCode.toUTF8(request.getParameter("issueNum"));
		String foNo = ChineseCode.toUTF8(request.getParameter("foNo"));
		String foId = ChineseCode.toUTF8(request.getParameter("foId"));
		
		String focut = StringUtil.isNullOrEmpty(request.getParameter("focut"))?"":request.getParameter("focut");
		String fomeasure = StringUtil.isNullOrEmpty(request.getParameter("fomeasure"))?"":request.getParameter("fomeasure");
		String fotool = StringUtil.isNullOrEmpty(request.getParameter("fotool"))?"":request.getParameter("fotool");
		String fomaterial = StringUtil.isNullOrEmpty(request.getParameter("fomaterial"))?"":request.getParameter("fomaterial");
		String foaccessory = StringUtil.isNullOrEmpty(request.getParameter("foaccessory"))?"":request.getParameter("foaccessory");
		
		String cut_num = StringUtil.isNullOrEmpty(request.getParameter("cut_num"))?"0":request.getParameter("cut_num");
		String measure_num = StringUtil.isNullOrEmpty(request.getParameter("measure_num"))?"0":request.getParameter("measure_num");
		String tool_num = StringUtil.isNullOrEmpty(request.getParameter("tool_num"))?"0":request.getParameter("tool_num");
		String material_num = StringUtil.isNullOrEmpty(request.getParameter("material_num"))?"0":request.getParameter("material_num");
		String accessory_num = StringUtil.isNullOrEmpty(request.getParameter("accessory_num"))?"0":request.getParameter("accessory_num");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
		
		String sql = "update fo_detail set " +
				"cut='"+focut+"',accessory='"+foaccessory+"',material='"+fomaterial+"',measure='"+fomeasure+"',tool='"+fotool+"'," +
				"cutNum='"+cut_num+"',measureNum='"+measure_num+"',toolNum='"+tool_num+"',materialNum='"+material_num+"',accessoryNum='"+accessory_num+"'," +
				"CHANGEPERSON='"+changePerson+"',CHANGETIME=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss') " +
				"where PRODUCT_ID='"+productId+"' and issue_num='"+issueNum+"' and fo_No='"+foNo+"' and foId='"+foId+"'";
		
		System.out.println("sql="+sql);
		
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(sql);
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (SQLException e) {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
		
	}
	
	public void Gofocmtyf(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String productId = request.getParameter("productId");
		String issueNum = request.getParameter("issueNum");
		String foNo = request.getParameter("foNo");
		String foId = request.getParameter("foId");
		System.out.println(productId);
		request.setAttribute("productId", productId);
		request.setAttribute("issueNum", issueNum);
		request.setAttribute("foNo", foNo);
		request.setAttribute("foId", foId);
		request.getRequestDispatcher("craftworkManage/"+pathTo).forward(request, response);
	}
	
	public void CMTYFList(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String productId = request.getParameter("productId");
		String issueNum = request.getParameter("issueNum");
		
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "PRODUCT_ID";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	    String totalCountSql = "select count(*) from fo_detail where product_id=? and issue_num=? and isInUse='1'";
	    String [] params = {productId,issueNum};
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, params);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    String sql= "select B.foId,B.PRODUCT_TYPE productType,B.PRODUCT_ID productId,B.ISSUE_NUM issueNum,B.ITEM_ID itemId,B.FO_NO foNo," +
				"B.FO_OPNAME foOpName,B.FO_OPCONTENT foOpcontent,B.RATED_TIME ratedTime,B.PLAN_TIME planTime,B.OPER_AIDTIME operAidTime," +
				"B.INSP_TIME inspTime,B.OPER_TIME operTime,B.IS_KEY isKey,B.IS_INSP isInsp,B.IS_ARMINSP isArmInsp," +
				"B.IS_CERTOP isCerTop,B.IS_CO isCo,B.WCID,B.EQUIPTYPE,B.EQUIPCODE," +
				"B.CUT,B.ACCESSORY,B.MATERIAL,B.MEASURE,B.TOOL," +
				"B.CUTNUM,B.MEASURENUM,B.TOOLNUM,B.MATERIALNUM,B.ACCESSORYNUM  " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from fo_detail EM where product_id=? and issue_num=? and isInUse='1' order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" and product_id=? and issue_num=? and isInUse='1' order by "+orderStr;
	
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
	
	public void focmtyfEdit(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String productId = request.getParameter("productId");
		String issueNum = request.getParameter("issueNum");
		String foNo = request.getParameter("foNo");
		String foId = request.getParameter("foId");
		
	    String sql= "select A.PRODUCT_TYPE productType,A.PRODUCT_ID productId,A.ISSUE_NUM issueNum,A.ITEM_ID itemId,A.FO_NO foNo," +
				"A.FO_OPNAME foOpName,A.FO_OPCONTENT foOpcontent,A.RATED_TIME ratedTime,A.PLAN_TIME planTime,A.OPER_AIDTIME operAidTime ," +
				"A.INSP_TIME inspTime,A.OPER_TIME operTime,A.IS_KEY isKey,A.IS_INSP isInsp,A.IS_ARMINSP isArmInsp," +
				"A.IS_CERTOP isCerTop,A.IS_CO isCo,A.WCID,A.EQUIPTYPE,A.EQUIPCODE," +
				"A.CUT,A.ACCESSORY,A.MATERIAL,A.MEASURE,A.TOOL," +
				"A.CUTNUM,A.MEASURENUM,A.TOOLNUM,A.MATERIALNUM,A.ACCESSORYNUM," +
				"B.item_name cutName,C.item_name accessoryName,D.item_name materialName,E.item_name measureName,F.item_name toolName  " +
	    	"from  fo_detail A " +
	    	"left join item B on B.item_id=A.cut "+
	    	"left join item C on C.item_id=A.ACCESSORY "+
	    	"left join item D on D.item_id=A.MATERIAL "+
	    	"left join item E on E.item_id=A.MEASURE "+
	    	"left join item F on F.item_id=A.TOOL "+
	    	"where product_id=? and issue_num=? and fo_no=? and isInUse='1' and foId=? ";
	    
	    String[] params = {productId,issueNum,foNo,foId};
	    
	    FoDetail foDetail = new FoDetail();
	    try {
			foDetail = Sqlhelper.exeQueryBean(sql, params, FoDetail.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if (!StringUtil.isNullOrEmpty(foDetail.getCut())) {
			String[] strArray = foDetail.getCut().split(",");
			String cut = "";
			for (int j = 0; j < strArray.length; j++) {
				String cutSql = "select item_Name from item where item_Id=?";
				String [] paramsCut = {strArray[j]};
				try {
					cut+=Sqlhelper.exeQueryString(cutSql, paramsCut)+",";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			cut = cut.substring(0,cut.length()-1);
			foDetail.setCutName(cut);
		}
	
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
			Accessory = Accessory.substring(0,Accessory.length()-1);
			foDetail.setAccessoryName(Accessory);
		}
	
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
			Material = Material.substring(0,Material.length()-1);
			foDetail.setMaterialName(Material);
		}
	
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
			Measure = Measure.substring(0,Measure.length()-1);
			foDetail.setMeasureName(Measure);
		}
		
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
			Tool = Tool.substring(0,Tool.length()-1);
			foDetail.setToolName(Tool);
		}
		
		request.setAttribute("result", foDetail);

		if (!"".equals(pathTo)) {
			request.getRequestDispatcher("craftworkManage/"+pathTo).forward(request, response);
		}
	}

	@Deprecated
	public void addAo(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String ao_no = ChineseCode.toUTF8(request.getParameter("ao_no").trim());
		String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
		String issueNum = ChineseCode.toUTF8(request.getParameter("issueNum").trim());
		String workplacecode = ChineseCode.toUTF8(request.getParameter("workplacecode").trim());
		String aoname = ChineseCode.toUTF8(request.getParameter("aoname").trim());
		String workplacename = ChineseCode.toUTF8(request.getParameter("workplacename").trim());
		String aover = ChineseCode.toUTF8(request.getParameter("aover").trim());
		String partno = ChineseCode.toUTF8(request.getParameter("partno").trim());
		String ao_time = ChineseCode.toUTF8(request.getParameter("ao_time").trim());
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    String sql = "insert into aodetail " +
	    		"(AO_NO,PRODUCTID,ISSUE_NUM,WORKPLACECODE,AONAME," +
	    		"WORKPLACENAME,AOVER,PARTNO,AO_TIME," +
	    		"createPerson,createTime,changePerson,changeTime)" +
	    		"values" +
	    		"('"+ao_no+"','"+productId+"','"+issueNum+"','"+workplacecode+"','"+aoname+"'," +
	    		"'"+workplacename+"','"+aover+"','"+partno+"','"+ao_time+"'," +
	    		"'"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'))";
	    System.out.println("sql="+sql);
	    
	    sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(sql);
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (SQLException e) {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}
	
	public void AOList(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String productId = request.getParameter("productId");
		String issueNum = request.getParameter("issueNum");
		String aoId = request.getParameter("aoId");
		
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "ao_no";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	    String totalCountSql = "select count(*) from aodetail where PRODUCTID=? and ISSUE_NUM=? and aoId=? ";
	    String[] paramsCount = {productId,issueNum,aoId};
	    try {
	    	totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, paramsCount);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    String sql= "select  AO_NO aoNo,PRODUCTID productId,ISSUE_NUM issueNum,WORKPLACECODE workplaceCode,AONAME aoName," +
	    		"WORKPLACENAME workplaceName,AOVER aoVer,PARTNO partNo,AO_TIME aoTime,aoId," +
	    		"AO_CONTENT aoContent " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from aoDetail EM where productid=? and issue_num=? and aoId=? order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" and productid=? and issue_num=? and aoId=?  order by "+orderStr;
	
	    String[] params = {productId,issueNum,aoId,productId,issueNum,aoId}; 
		ResultSet rs =null;
		try{
			List<AoDetail> list = Sqlhelper.exeQueryList(sql, params, AoDetail.class);
			String json = PluSoft.Utils.JSON.Encode(list);
			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			System.out.println(json);
		}catch(Exception e){
		}  finally{
			try {
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void getLazyTree(HttpServletRequest request, HttpServletResponse response,String pathTo) {

		//获取提交的数据
	    String id = request.getParameter("ID");
	    String sql = "select A.code id,A.name name,A.path path,A.layer layer, "+
			"case when A.code<100 then '0' "+
			"  when mod(A.code,100000000)=0 then substr(A.code,0,2) "+
			"  when mod(A.code,1000000)=0 then substr(A.code,0,4)||'00000000' "+
			"  when mod(A.code,1000)=0 then substr(A.code,0,6)||'000000' "+
			" else substr(A.code,0,9)||'000' "+
			" end as pid " +
			"from area A ";
	    if(StringUtil.isNullOrEmpty(id)){
	    	sql += "where length(code)<3 order by A.code";
	    }else {
	    	if (id.length()==2) {
		    	sql += "where code like '"+id+"%' and substr(A.code,5,8)='00000000'";
	    	}else {
	    		if ("00000000".equals(id.substring(4, 12))) {
					sql += "where code like '"+id.substring(0,4)+"%' and substr(A.code,7,6)='000000' and substr(A.code,5,8)!='00000000'";
				}else {
					if ("000000".equals(id.substring(6))) {
						sql += "where code like '"+id.substring(0,6)+"%' and substr(A.code,10,3)='000' and substr(A.code,7,6)!='000000'";
					}else {
						if ("000".equals(id.substring(9))) {
							sql += "where code like '"+id.substring(0,9)+"%' and substr(A.code,10,3)!='000'";
						}
					}
				}
			}
		}
    	
	    System.out.println(sql);
	    try {
	    	ArrayList folders = new Test.TestDB().DBSelect(sql);
		    for (int i = 0, l = folders.size(); i < l; i++)
		    {
		        HashMap node = (HashMap)folders.get(i);
		        String layer = node.get("LAYER").toString();
		        if ("5".equals(layer))
		        {
		            node.put("isLeaf", true);
		            node.put("expanded", false);
		        }else {
		        	node.put("isLeaf", false);
		            node.put("expanded", false);
				}
		    }
		    String json = Test.JSON.Encode(folders);
		    System.out.println(json);
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void craftProAllServlet(HttpServletRequest request, HttpServletResponse response,String pathTo) {
		try {
			Document doc = new Document();
			RtfWriter2.getInstance(doc, new FileOutputStream("E:/test/craftProAll.doc"));
			// 打开文档
			doc.open();
			// 设置页边距，上、下25.4毫米，即为20f，左、右31.8毫米，即为10f
			doc.setMargins(10f, 10f, 20f, 20f);

//			// 设置标题字体样式，粗体、二号、华文中宋
//			Font tfont = DocStyleUtils.setFontStyle("华文中宋", 22f, Font.BOLD);
//			// 设置正文内容的字体样式，常规、三号、仿宋_GB2312
//			Font bfont = DocStyleUtils.setFontStyle("仿宋_GB2312", 16f,
//					Font.NORMAL);
//
//			// 构建标题，居中对齐，12f表示单倍行距
//			Paragraph title = DocStyleUtils.setParagraphStyle(
//					"测试Itext导出Word文档", tfont, 12f, Paragraph.ALIGN_CENTER);
//			// 构建正文内容
//			StringBuffer contentSb = new StringBuffer();
//			contentSb.append("最近项目很忙，这个是项目中使用到的，所以现在总结一下，以便今后可以参考使用，");
//			contentSb.append("2011年4月27日 — 2011年5月20日，对以下技术进行使用，");
//			contentSb.append("Itext、");
//			contentSb.append("Excel、");
//			contentSb.append("Word、");
//			contentSb.append("PPT。");
//
//			// 首行缩进2字符，行间距1.5倍行距
//			Paragraph bodyPar = DocStyleUtils.setParagraphStyle(contentSb
//					.toString(), bfont, 32f, 18f);
//			Paragraph bodyEndPar = DocStyleUtils.setParagraphStyle(
//					"截至2011年4月28日，各种技术已经完全实现。", bfont, 32f, 18f);
//			// 设置空行
//			Paragraph blankRow = new Paragraph(18f, " ", bfont);
//			Paragraph deptPar = DocStyleUtils.setParagraphStyle("（技术开发部盖章）",
//					bfont, 12f, Paragraph.ALIGN_RIGHT);
//			Paragraph datePar = DocStyleUtils.setParagraphStyle("2011-04-30",
//					bfont, 12f, Paragraph.ALIGN_RIGHT);
//
//			// 向文档中添加内容
			
			
			Table table = new Table(9, 40);
			int[] withs = { 7,8,15,15,10,15,15,7,8 };
	        /** 设置每列所占比例 author:yyli Sep 15, 2010 */
	        table.setWidths(withs);
	        table.setWidth(100);
	        
			
			// 设置标题字体样式，粗体、二号、华文中宋
			Font titleFont = DocStyleUtils.setFontStyle("华文中宋", 20f, Font.NORMAL);
			// 设置正文内容的字体样式，常规、三号、仿宋_GB2312
			Font subTitleFont = DocStyleUtils.setFontStyle("仿宋_GB2312", 14f,Font.NORMAL);
			Font bodyFont = DocStyleUtils.setFontStyle("仿宋_GB2312", 12f,Font.NORMAL);
			
			Cell cell11 = new Cell();
			Paragraph paragraph11 = DocStyleUtils.setParagraphStyle("________________工艺过程卡", titleFont, 0, 0);
			paragraph11.setAlignment(Element.ALIGN_CENTER);
			cell11.setColspan(5);
			cell11.setRowspan(2);
			cell11.setVerticalAlignment(Element.ALIGN_CENTER);
			cell11.add(paragraph11);
			
			Cell cell12 = new Cell();
			Paragraph paragraph12 = new Paragraph("编 号");
			paragraph12.setFont(subTitleFont);
			paragraph12.setAlignment(Element.ALIGN_CENTER);
			cell12.add(paragraph12);
			
			Cell cell13 = new Cell();
			Paragraph paragraph13 = new Paragraph("标 记");
			paragraph13.setFont(subTitleFont);
			paragraph13.setAlignment(Element.ALIGN_CENTER);
			cell13.add(paragraph13);
			
			Cell cell14 = new Cell();
			Paragraph paragraph14 = new Paragraph("第   页");
			paragraph14.setFont(subTitleFont);
			paragraph14.setAlignment(Element.ALIGN_CENTER);
			cell14.setRowspan(2);
			cell14.setColspan(2);
			cell14.setVerticalAlignment(Element.ALIGN_CENTER);
			cell14.add(paragraph14);
			
			Cell cell21 = new Cell();
			Paragraph paragraph21 = new Paragraph("产品型号");
			paragraph21.setFont(subTitleFont);
			paragraph21.setAlignment(Element.ALIGN_CENTER);
			cell21.setVerticalAlignment(Element.ALIGN_CENTER);
			cell21.setColspan(2);
			cell21.add(paragraph21);
			
			Cell cell23 = new Cell();
			Paragraph paragraph23 = new Paragraph("零组件名称");
			paragraph23.setFont(subTitleFont);
			paragraph23.setAlignment(Element.ALIGN_CENTER);
			cell23.setVerticalAlignment(Element.ALIGN_CENTER);
			cell23.add(paragraph23);
			
			Cell cell24 = new Cell();
			cell24.setColspan(2);
			
			Cell cell25 = new Cell();
			Paragraph paragraph25 = new Paragraph("零组件图号");
			paragraph25.setFont(subTitleFont);
			paragraph25.setAlignment(Element.ALIGN_CENTER);
			cell25.setVerticalAlignment(Element.ALIGN_CENTER);
			cell25.add(paragraph25);
			
			Cell cell26 = new Cell();
			cell26.setColspan(2);
			
			Cell cell31 = new Cell();
			Paragraph paragraph31 = new Paragraph("材 料");
			paragraph31.setFont(subTitleFont);
			paragraph31.setAlignment(Element.ALIGN_CENTER);
			cell31.setVerticalAlignment(Element.ALIGN_CENTER);
			cell31.setColspan(2);
			cell31.add(paragraph31);
			
			Cell cell32 = new Cell();
			Paragraph paragraph32 = new Paragraph("供应状态");
			paragraph32.setFont(subTitleFont);
			paragraph32.setAlignment(Element.ALIGN_CENTER);
			cell32.setVerticalAlignment(Element.ALIGN_CENTER);
			cell32.add(paragraph32);
			
			Cell cell33 = new Cell();
			Paragraph paragraph33 = new Paragraph("技术要求");
			paragraph33.setFont(subTitleFont);
			paragraph33.setAlignment(Element.ALIGN_CENTER);
			cell33.setVerticalAlignment(Element.ALIGN_CENTER);
			cell33.add(paragraph33);
			
			Cell cell35 = new Cell();
			Paragraph paragraph35 = new Paragraph("技术规格");
			paragraph35.setFont(subTitleFont);
			paragraph35.setAlignment(Element.ALIGN_CENTER);
			cell35.setVerticalAlignment(Element.ALIGN_CENTER);
			cell35.add(paragraph35);
			
			Cell cell36 = new Cell();
			Paragraph paragraph36 = new Paragraph("毛坯尺寸");
			paragraph36.setFont(subTitleFont);
			paragraph36.setAlignment(Element.ALIGN_CENTER);
			cell36.setVerticalAlignment(Element.ALIGN_CENTER);
			cell36.add(paragraph36);
			
			Cell cell37 = new Cell();
			Paragraph paragraph37 = new Paragraph("可加工零件数");
			paragraph37.setFont(subTitleFont);
			paragraph37.setAlignment(Element.ALIGN_CENTER);
			cell37.setVerticalAlignment(Element.ALIGN_CENTER);
			cell37.setColspan(2);
			cell37.add(paragraph37);
			
			Cell cell41 = new Cell();
			cell41.setColspan(2);
			Cell cell48 = new Cell();
			cell48.setColspan(2);
			
			
			
			Cell cell51 = new Cell();
			Paragraph paragraph51 = new Paragraph("工序号");
			paragraph51.setFont(bodyFont);
			paragraph51.setAlignment(Element.ALIGN_CENTER);
			cell51.setVerticalAlignment(Element.ALIGN_CENTER);
			cell51.add(paragraph51);
			
			Cell cell52 = new Cell();
			Paragraph paragraph52 = new Paragraph("工 种");
			paragraph52.setFont(bodyFont);
			paragraph52.setAlignment(Element.ALIGN_CENTER);
			cell52.setVerticalAlignment(Element.ALIGN_CENTER);
			cell52.setWidth(15);
			cell52.add(paragraph52);
			
			Cell cell53 = new Cell();
			Paragraph paragraph53 = new Paragraph("工  艺  内  容");
			paragraph53.setFont(bodyFont);
			paragraph53.setAlignment(Element.ALIGN_CENTER);
			cell53.setVerticalAlignment(Element.ALIGN_CENTER);
			cell53.setColspan(4);
			cell53.setWidth(30);
			cell53.add(paragraph53);
			
			Cell cell54 = new Cell();
			Paragraph paragraph54 = new Paragraph("工装夹具");
			paragraph54.setFont(bodyFont);
			paragraph54.setAlignment(Element.ALIGN_CENTER);
			cell54.setVerticalAlignment(Element.ALIGN_CENTER);
			cell54.setWidth(15);
			cell54.add(paragraph54);
			
			Cell cell55 = new Cell();
			Paragraph paragraph55 = new Paragraph("单件 min");
			paragraph55.setFont(bodyFont);
			paragraph55.setAlignment(Element.ALIGN_CENTER);
			cell55.setVerticalAlignment(Element.ALIGN_CENTER);
			cell55.setWidth(15);
			cell55.add(paragraph55);
			
			Cell cell56 = new Cell();
			Paragraph paragraph56 = new Paragraph("准备 min");
			paragraph56.setFont(bodyFont);
			paragraph56.setAlignment(Element.ALIGN_CENTER);
			cell56.setVerticalAlignment(Element.ALIGN_CENTER);
			cell56.setWidth(15);
			cell56.add(paragraph56);
			
			Cell cell345 = new Cell();
			Paragraph paragraph345 = new Paragraph("编  制");
			paragraph345.setFont(bodyFont);
			paragraph345.setAlignment(Element.ALIGN_CENTER);
			cell345.setVerticalAlignment(Element.ALIGN_CENTER);
			cell345.add(paragraph345);
			
			Cell cell355 = new Cell();
			Paragraph paragraph355 = new Paragraph("校  对");
			paragraph355.setFont(bodyFont);
			paragraph355.setAlignment(Element.ALIGN_CENTER);
			cell355.setVerticalAlignment(Element.ALIGN_CENTER);
			cell355.add(paragraph355);
			
			Cell cell365 = new Cell();
			Paragraph paragraph365 = new Paragraph("审  核");
			paragraph365.setFont(bodyFont);
			paragraph365.setAlignment(Element.ALIGN_CENTER);
			cell365.setVerticalAlignment(Element.ALIGN_CENTER);
			cell365.add(paragraph365);
			
			Cell cell375 = new Cell();
			Paragraph paragraph375 = new Paragraph("会  签");
			paragraph375.setFont(bodyFont);
			paragraph375.setAlignment(Element.ALIGN_CENTER);
			cell375.setVerticalAlignment(Element.ALIGN_CENTER);
			cell375.add(paragraph375);
			
			
			Cell cell391 = new Cell();
			Paragraph paragraph391 = new Paragraph("更改标记");
			paragraph391.setFont(bodyFont);
			paragraph391.setAlignment(Element.ALIGN_CENTER);
			cell391.setVerticalAlignment(Element.ALIGN_CENTER);
			cell391.setColspan(2);
			cell391.add(paragraph391);
			
			Cell cell392 = new Cell();
			Paragraph paragraph392 = new Paragraph("更改单号");
			paragraph392.setFont(bodyFont);
			paragraph392.setAlignment(Element.ALIGN_CENTER);
			cell392.setVerticalAlignment(Element.ALIGN_CENTER);
			cell392.add(paragraph392);
			
			Cell cell393 = new Cell();
			Paragraph paragraph393 = new Paragraph("签  名");
			paragraph393.setFont(bodyFont);
			paragraph393.setAlignment(Element.ALIGN_CENTER);
			cell393.setVerticalAlignment(Element.ALIGN_CENTER);
			cell393.add(paragraph393);
			
			Cell cell394 = new Cell();
			Paragraph paragraph394 = new Paragraph("日  期");
			paragraph394.setFont(bodyFont);
			paragraph394.setAlignment(Element.ALIGN_CENTER);
			cell394.setVerticalAlignment(Element.ALIGN_CENTER);
			cell394.add(paragraph394);
			
			Cell cell395 = new Cell();
			Paragraph paragraph395 = new Paragraph("批  准");
			paragraph395.setFont(bodyFont);
			paragraph395.setAlignment(Element.ALIGN_CENTER);
			cell395.setVerticalAlignment(Element.ALIGN_CENTER);
			cell395.add(paragraph395);
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			table.addCell(cell11,0,0);
			table.addCell(cell12,0,5);
			table.addCell(cell13,0,6);
			table.addCell(cell14,0,7);
			table.addCell(cell21, 2, 0);
			table.addCell(cell23, 2, 3);
			table.addCell(cell24);
			table.addCell(cell25, 2, 6);
			table.addCell(cell26);
			table.addCell(cell31,3,0);
			table.addCell(cell32,3,2);
			table.addCell(cell33,3,3);
			table.addCell(cell35,3,5);
			table.addCell(cell36,3,6);
			table.addCell(cell37,3,7);
			
			table.addCell(cell41,4,0);
			table.addCell(cell48,4,7);
			
			table.addCell(cell51,5,0);
			table.addCell(cell52,5,1);
			table.addCell(cell53,5,2);
			table.addCell(cell54,5,6);
			table.addCell(cell55,5,7);
			table.addCell(cell56,5,8);
			
			table.addCell(cell345,34,5);
			table.addCell(cell355,35,5);
			table.addCell(cell365,36,5);
			table.addCell(cell375,37,5);
			
			
			table.addCell(cell391,39,0);
			table.addCell(cell392,39,2);
			table.addCell(cell393,39,3);
			table.addCell(cell394,39,4);
			table.addCell(cell395,39,5);
			
			for (int i = 0; i < 28; i++) {
				Cell cellCraftDetail = new Cell();
				cellCraftDetail.setColspan(4);
				table.addCell(cellCraftDetail,i+6,2);
			}
			for (int i = 34; i < 39; i++) {
				Cell cellCraftDetail = new Cell();
				cellCraftDetail.setColspan(2);
				table.addCell(cellCraftDetail,i,0);
				table.addCell(cellCraftDetail,i,7);
			}
			
			Cell cellBlank = new Cell();
			cellBlank.setColspan(2);
			table.addCell(cellBlank,39,7);
			
			
			doc.add(table);

			
			
			
			
			
			
			

			// 最后一定要记住关闭
			doc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void gofoAlldetail(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		String productId = request.getParameter("productId");
		String FProductId = request.getParameter("FProductId");
		System.out.println(FProductId);
		request.setAttribute("orderId", orderId);
		request.setAttribute("productId", productId);
		request.setAttribute("FProductId", FProductId);
		request.getRequestDispatcher("craftworkManage/"+pathTo).forward(request, response);
	}
	
	public void updatecraftwork(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String status = request.getParameter("status");
		String confirmedAdvice = ChineseCode.toUTF8(request.getParameter("confirmedAdvice"));
		String confirmAdvice = ChineseCode.toUTF8(request.getParameter("confirmAdvice"));
		String productId = request.getParameter("productId");
		String issueNum = request.getParameter("issueNum");
		String orderId = request.getParameter("orderId");
		String drawingId = request.getParameter("drawingId");
		
		String foType = request.getParameter("foType");		//xiem 2016 12 18
		String barcode = request.getParameter("barcode");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    int productStatus = 0;
	    String pass=null;
	    if(confirmAdvice.equals("")){
	    	if ("5".equals(status)) {					//审核通过
				productStatus = ProductStatus.FOPASS;
				pass="审核通过";
			}
		    if ("4".equals(status)) {					//审核不通过
				productStatus = ProductStatus.FONOTPASS;
				pass="审核不通过";
			}
		    if ("3".equals(status)) {					//提交上级审核
		    	productStatus = ProductStatus.FOCHECKING;
		    	pass="提交上级审核";
		    }
	    }
	    
	    if(foType.equals("1")){
	    	
	    	String sqla = "update todiscard t set t.state= '"+productStatus+"', t.confirmadvice='"+confirmedAdvice+confirmAdvice+pass+"("+createPerson+")"+"' ," +
	    			"t.checkperson= '"+createPerson+"' ,t.checktime = to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss') where t.barcode='"+barcode+"'";
	    	
	    	try {
				Sqlhelper.executeUpdate(sqla, null);
				System.out.println("ok "+sqla);
				String json = "{\"result\":\"操作成功!\"}";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
			} catch (Exception e) {
				// TODO: handle exception
				String json = "{\"result\":\"操作失败!\"}";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
			}
	    	return;
	    }
	    String sql = "update fo_detail set " +
				"status='"+status+"',confirmAdvice='"+confirmedAdvice+confirmAdvice+pass+"("+createPerson+")"+"'," +
				"checkPerson='"+createPerson+"',checkTime=to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss') "+
				"where  product_id='"+productId+"' and issue_num='"+issueNum+"' ";
	    System.out.println("sql="+sql);
	    
	    
	    String ProductStatusSql = "update order_detail set product_status=? " +
				"where order_id=? and product_id=? and issue_num=? and drawingId=? ";
		String[] params2 = {productStatus+"",orderId,productId,issueNum,drawingId};

	    sql_data sqlData = new sql_data();
		try {
			System.out.println("sql="+sql);
			sqlData.exeUpdateThrowExcep(sql);
			Sqlhelper.executeUpdate(ProductStatusSql, params2);
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (SQLException e) {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}
	
	public void AddFoHeader(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
//		String foId = ChineseCode.toUTF8(request.getParameter("foId"));
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId"));
		String productId = ChineseCode.toUTF8(request.getParameter("productId"));
		String productName = ChineseCode.toUTF8(request.getParameter("productName"));
		String issueNum = ChineseCode.toUTF8(request.getParameter("issueNum"));
		String drawingId = ChineseCode.toUTF8(request.getParameter("drawingId"));
		
		String productType = ChineseCode.toUTF8(request.getParameter("productType"));
		String fproductId = ChineseCode.toUTF8(request.getParameter("fproductId"));
		String productNum = ChineseCode.toUTF8(request.getParameter("productNum"));
		String spec = ChineseCode.toUTF8(request.getParameter("spec"));
		String matirial = ChineseCode.toUTF8(request.getParameter("matirial"));
		
		String supplyStatus = ChineseCode.toUTF8(request.getParameter("supplyStatus"));
		String techNeed = ChineseCode.toUTF8(request.getParameter("techNeed"));
		String techSpec = ChineseCode.toUTF8(request.getParameter("techSpec"));
		String roughSize = ChineseCode.toUTF8(request.getParameter("roughSize"));
		String memo = ChineseCode.toUTF8(request.getParameter("memo"));
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    String UUID = "";
//	    判断是否重复零件，若是，返回foid，否则返回null
	    String foid = null;
	    foid = IsRepeatProduct.searchFoId(orderId, productId, issueNum);
	    if(foid != null){
	    	UUID = foid;	//若是重复零件，则共用一个foid
	    }else{
	    	UUID = UUIDHexGenerator.getInstance().generate();
	    }
//		xiem 新材料维护
	    try {
			AddNewStuff.addNewStuff(matirial);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String sql = "insert into foHeader " +
                "(foId,orderId,productId,productName,issueNum,drawingId," +
			    "productType,fproductId,productNum,spec,matirial," +
				"supplyStatus,techNeed,techSpec,roughSize,memo," +
				"createPerson,createTime,changePerson,changeTime)" +
				"values" +
//				"('"+foId+"','"+orderId+"','"+productId+"','"+productName+"','"+issueNum+"','"+drawingId+"'," +
				"('"+UUID+"','"+orderId+"','"+productId+"','"+productName+"','"+issueNum+"','"+drawingId+"'," +
				"'"+productType+"','"+fproductId+"','"+productNum+"','"+spec+"','"+matirial+"'," +
				"'"+supplyStatus+"','"+techNeed+"','"+techSpec+"','"+roughSize+"','"+memo+"'," +
				"'"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'))";
		System.out.println("sql="+sql);
		
		try {
			Sqlhelper.executeUpdate(sql, null);
			
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (Exception e) {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}
	
	public void AddFoHeader1(HttpServletRequest request, HttpServletResponse response,String pathTo)
			throws ServletException, IOException {
//				String foId = ChineseCode.toUTF8(request.getParameter("foId"));
				String orderId = ChineseCode.toUTF8(request.getParameter("orderId"));
				String productId = ChineseCode.toUTF8(request.getParameter("productId"));
				String productName = ChineseCode.toUTF8(request.getParameter("productName"));
				String issueNum = ChineseCode.toUTF8(request.getParameter("issueNum"));
				String drawingId = ChineseCode.toUTF8(request.getParameter("drawingId"));
				
				String productType = ChineseCode.toUTF8(request.getParameter("productType"));
				String fproductId = ChineseCode.toUTF8(request.getParameter("fproductId"));
				String productNum = ChineseCode.toUTF8(request.getParameter("productNum"));
				String spec = ChineseCode.toUTF8(request.getParameter("spec"));
				String matirial = ChineseCode.toUTF8(request.getParameter("matirial"));
				
				String supplyStatus = ChineseCode.toUTF8(request.getParameter("supplyStatus"));
				String techNeed = ChineseCode.toUTF8(request.getParameter("techNeed"));
				String techSpec = ChineseCode.toUTF8(request.getParameter("techSpec"));
				String roughSize = ChineseCode.toUTF8(request.getParameter("roughSize"));
				String memo = ChineseCode.toUTF8(request.getParameter("memo"));
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			    String createTime = df.format(new Date());
			    String changeTime = df.format(new Date());
			    
			    HttpSession session = request.getSession();
			    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
			    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
			    String UUID = "";
//			    String sqla = "select count(*) from foheader a where a.productid=? and a.issuenum=? and rownum<=1";
//			    String sqla = "select  distinct a.foid from foheader a where a .productid= ? and a.issuenum = ? ";
//			    String [] parama = {productId,issueNum};
//			    Map< String, String> mapa = new HashMap<String, String>();
//			    try {
//			    	mapa = Sqlhelper.executeQueryMap(sqla, parama);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//				String afoId = mapa.get("FOID");
//				if(afoId==null || afoId.equals("")){
					UUID = UUIDHexGenerator.getInstance().generate();
//				}else{
//					UUID = afoId;
//				}
			    
//			    判断是否重复零件，若是，返回foid，否则返回null，启用
			    String foid = null;
			    foid = IsRepeatProduct.searchFoId(orderId, productId, issueNum);
			    if(foid != null){
			    	UUID = foid;	//若是重复零件，则共用一个foid
			    }else{
			    	UUID = UUIDHexGenerator.getInstance().generate();
			    }
			    
			    int count = 1;
			    
//				String countsql="select count(*) from foheader where orderId=? and productId=? and fproductId=?";
//			    String[] params = {orderId,productId,fproductId};
//			    int count=0;
//			    try {
//			    	 count = Sqlhelper.exeQueryCountNum(countsql, params);
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//				count =count+1;		//工艺版本
//				xiem 新材料维护
			    try {
					AddNewStuff.addNewStuff(matirial);
				} catch (Exception e) {
					// TODO: handle exception
				}
				String sql = "insert into foHeader " +
		                "(foId,orderId,productId,productName,issueNum,drawingId," +
					    "productType,fproductId,productNum,spec,matirial," +
						"supplyStatus,techNeed,techSpec,roughSize,memo," +
						"createPerson,createTime,changePerson,changeTime,processissue_num)" +
						"values" +
//						"('"+foId+"','"+orderId+"','"+productId+"','"+productName+"','"+issueNum+"','"+drawingId+"'," +
						"('"+UUID+"','"+orderId+"','"+productId+"','"+productName+"','"+issueNum+"','"+drawingId+"'," +
						"'"+productType+"','"+fproductId+"','"+productNum+"','"+spec+"','"+matirial+"'," +
						"'"+supplyStatus+"','"+techNeed+"','"+techSpec+"','"+roughSize+"','"+memo+"'," +
						"'"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+count+"')";
				
				
				try {
					System.out.println("sql="+sql);
					Sqlhelper.executeUpdate(sql, null);
					String json = "{\"result\":\"success\"}";
					response.setCharacterEncoding("UTF-8");
					response.getWriter().append(json).flush();
				} catch (Exception e) {
					String json = "{\"result\":\"操作失败!\"}";
					response.setCharacterEncoding("UTF-8");
					response.getWriter().append(json).flush();
					e.printStackTrace();
				}
//				修改产品状态
				String ProductStatusSql = "update order_detail set product_status=? " +
						"where order_id=? and product_id=? and issue_num=? and drawingId=? ";
				String[] params2 = {ProductStatus.FODOING+"",orderId,productId,issueNum,drawingId};
				try {
					Sqlhelper.executeUpdate(ProductStatusSql, params2);
					System.out.println("ok  "+ProductStatusSql);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
			
	public void GoCraftMainBody(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
		String FProductId = ChineseCode.toUTF8(request.getParameter("FProductId").trim());
		String issueNum = ChineseCode.toUTF8(request.getParameter("issueNum").trim());
		String drawingId = ChineseCode.toUTF8(request.getParameter("drawingId").trim());
     	String isLeaf = ChineseCode.toUTF8(request.getParameter("isLeaf").trim());
		
		
		String orderSql = "select count(1) from foHeader A " +
	    	"where orderId=? and productId=? and FProductId=?";
		String[] params = {orderId,productId,FProductId};

		if ("0".equals(isLeaf)) {				//需要装配
			String AoSql = "select count(1) from aoHeader A where orderId=? and productId=? and issueNum=? and drawingId=? ";
			String[] paramsAo = {orderId,productId,issueNum,drawingId};
			int count = 0;
			try {
				count = Sqlhelper.exeQueryCountNum(AoSql, paramsAo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(count);
			if (count>0) {
				request.setAttribute("isAoHeader","1");
			}else {
				request.setAttribute("isAoHeader", "0");
			}
		}
		
		try{
			int countNum = Sqlhelper.exeQueryCountNum(orderSql, params);
			if (countNum>0) {			//已经添加过了，改为查看和修改
				request.setAttribute("isHere", "1");
			}else {
				request.setAttribute("isHere", "0");
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("isLeaf", isLeaf);
		ProductInfoServlet2(request, response, pathTo);
	}
	
	
	public void GoCraftMainBody1(HttpServletRequest request, HttpServletResponse response,String pathTo)
			throws ServletException, IOException {
				ProductInfoServlet1(request, response, pathTo);
			}
	
	
	
	public void updateFoHeader(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String foId = ChineseCode.toUTF8(request.getParameter("foId"));
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId"));
		String productId = ChineseCode.toUTF8(request.getParameter("productId"));
		String productName = ChineseCode.toUTF8(request.getParameter("productName"));
		String issueNum = ChineseCode.toUTF8(request.getParameter("issueNum"));
		String drawingId = ChineseCode.toUTF8(request.getParameter("drawingId"));
		
		String productType = ChineseCode.toUTF8(request.getParameter("productType"));
		String fproductId = ChineseCode.toUTF8(request.getParameter("fproductId"));
		String productNum = ChineseCode.toUTF8(request.getParameter("productNum"));
		String spec = ChineseCode.toUTF8(request.getParameter("spec"));
		String matirial = ChineseCode.toUTF8(request.getParameter("matirial"));
		
		String supplyStatus = ChineseCode.toUTF8(request.getParameter("supplyStatus"));
		String techNeed = ChineseCode.toUTF8(request.getParameter("techNeed"));
		String techSpec = ChineseCode.toUTF8(request.getParameter("techSpec"));
		String roughSize = ChineseCode.toUTF8(request.getParameter("roughSize"));
		String memo = ChineseCode.toUTF8(request.getParameter("memo"));
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
//		xiem 新材料维护
	    try {
			AddNewStuff.addNewStuff(matirial);
		} catch (Exception e) {
			// TODO: handle exception
		}
	    String sql = "update foHeader set " +
				"productName='"+productName+"',drawingId='"+drawingId+"'," +
				"productType='"+productType+"',fproductId='"+fproductId+"',productNum='"+productNum+"',spec='"+spec+"',matirial='"+matirial+"'," +
				"supplyStatus='"+supplyStatus+"',techNeed='"+techNeed+"',techSpec='"+techSpec+"',roughSize='"+roughSize+"',memo='"+memo+"'," +
				"changePerson='"+changePerson+"',changeTime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss') " +
				"where orderId='"+orderId+"' and productId='"+productId+"' and issueNum='"+issueNum+"' and foId='"+foId+"' ";
		System.out.println("sql="+sql);
		
		try {
			Sqlhelper.executeUpdate(sql, null);
			
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (Exception e) {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}
	
	public void GetFoHeader(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
		String FProductId = ChineseCode.toUTF8(request.getParameter("FProductId").trim());
		String isHere = ChineseCode.toUTF8(request.getParameter("isHere").trim());
		String sql = "";
		if("1".equals(isHere)){
			sql = "select orderId,productId,productName,issueNum,drawingId," +
				"foId,productType,FProductId,productNum,spec,matirial," +
				"supplyStatus,roughSize,memo,techSpec,techNeed,C.ITEM_TYPEDESC productTypeName  " +
				"from foHeader A " +
				"left join itemtype C on C.item_typeid=A.productType  " +
				"where orderId=? and productId=? and FProductId=? ";
		}else {
			sql = "select ORDER_ID orderId,PRODUCT_ID productId,ISSUE_NUM issueNum, purduct_num productNum," +
	    	"PRODUCT_STATUS supplyStatus,FPRODUCT_ID FProductId,PRODUCT_NAME productName,SPEC spec,drawingId drawingId," +
	    	"B.ITEM_TYPEID productType,C.ITEM_TYPEDESC productTypeName,material matirial " +
	    	"from order_detail A " +
	    	"left join item B on B.ITEM_ID=A.PRODUCT_ID "+
//	    	"left join item B on B.ITEM_ID=A.PRODUCT_ID and B.FITEM_ID=A.FPRODUCT_ID " +
	    	"left join itemtype C on C.item_typeid=B.item_typeid  " +
	    	"where ORDER_ID=? and PRODUCT_ID=? and FPRODUCT_ID=? ";
		}
		String[] params = new String[]{orderId,productId,FProductId};
		FoHeader foHeader = null;
		try {
			foHeader = Sqlhelper.exeQueryBean(sql, params, FoHeader.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		request.setAttribute("result", foHeader);
		request.setAttribute("isHere", isHere);
		if (!StringUtil.isNullOrEmpty(pathTo)) {
			request.getRequestDispatcher("craftworkManage/"+pathTo).forward(request, response);
		}
	}
	
	public void GetFoHeader1(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
		String FProductId = ChineseCode.toUTF8(request.getParameter("FProductId").trim());
		String sql = "";
		sql = "select A.ORDER_ID orderId,A.PRODUCT_ID productId,A.ISSUE_NUM issueNum,D.REJECTNUM productNum," +
	    	"PRODUCT_STATUS supplyStatus,FPRODUCT_ID FProductId,PRODUCT_NAME productName,SPEC spec,A.drawingId drawingId," +
	    	"B.ITEM_TYPEID productType,C.ITEM_TYPEDESC productTypeName " +
	    	"from order_detail A " +
	    	"left join item B on B.ITEM_ID=A.PRODUCT_ID "+
//	    	"left join item B on B.ITEM_ID=A.PRODUCT_ID and B.FITEM_ID=A.FPRODUCT_ID " +
	    	"left join itemtype C on C.item_typeid=B.item_typeid  " +
	    	"left join todiscard D on D.order_id=A.order_id and D.product_id=A.PRODUCT_ID "+
	    	"where A.ORDER_ID=? and A.PRODUCT_ID=? and FPRODUCT_ID=? ";
	
		String[] params = new String[]{orderId,productId,FProductId};
		FoHeader foHeader = null;
		try {
			foHeader = Sqlhelper.exeQueryBean(sql, params, FoHeader.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		request.setAttribute("result", foHeader);
		if (!StringUtil.isNullOrEmpty(pathTo)) {
			request.getRequestDispatcher("craftworkManage/"+pathTo).forward(request, response);
		}
	}
	
	public void GetFoHeader2(HttpServletRequest request, HttpServletResponse response,String pathTo)
			throws ServletException, IOException {
				String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
				String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
				String FProductId = ChineseCode.toUTF8(request.getParameter("FProductId").trim());
				String issueNum = ChineseCode.toUTF8(request.getParameter("issueNum").trim());
				String sql = "";

				sql = "select orderId,productId,productName,issueNum,a.drawingId," +
//						"foId,productType,FProductId,productNum,spec,matirial," +    
						"foId,productType,FProductId,D.REJECTNUM productNum,spec,matirial," +
						"supplyStatus,roughSize,memo,techSpec,techNeed,C.ITEM_TYPEDESC productTypeName  " +
						"from foHeader A " +
						"left join itemtype C on C.item_typeid=A.productType  " +
						"left join todiscard D on D.order_id=A.orderid and D.product_id=a.productid and d.issue_num=a.issueNum "+
						"where a.orderId=? and a.productId=? and a.FProductId=? and a.issueNum=? ";
				String[] params = new String[]{orderId,productId,FProductId,issueNum};
				FoHeader foHeader = null;
				try {
					foHeader = Sqlhelper.exeQueryBean(sql, params, FoHeader.class);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				request.setAttribute("result", foHeader);
				if (!StringUtil.isNullOrEmpty(pathTo)) {
					request.getRequestDispatcher("craftworkManage/"+pathTo).forward(request, response);
				}
			}
	public void GetAoHeader(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
		String issueNum = ChineseCode.toUTF8(request.getParameter("issueNum").trim());
		
		System.out.println(issueNum);
		String drawingId = ChineseCode.toUTF8(request.getParameter("drawingId").trim());
		String isHere = ChineseCode.toUTF8(request.getParameter("isHere").trim());
		String sql = "";
		if ("1".equals(isHere)) {			//表头已经制定过
			
			sql = "select aoId,orderId,productId,productName,productType,issueNum," +
				"drawingId,spec,aoContent,productNum,deadLine," +
				"aoHeadermemo,aoPaper,B.item_typeDesc productTypeName " +
				"from aoHeader A " +
				"left join itemtype B on B.item_typeid=A.productType  "+
				"where orderId=? and productId=? and issueNum=? and drawingId=?  ";
			request.setAttribute("isHere", "1");
		}else {								//表头没有制定过，要增加表头信息
			request.setAttribute("isHere", "0");
			sql = "select ORDER_ID orderId,PRODUCT_ID productId,PRODUCT_NAME productName,B.ITEM_TYPEID productType,ISSUE_NUM issueNum," +
				"drawingId drawingId,SPEC spec,  PURDUCT_NUM productNum,C.ITEM_TYPEDESC productTypeName " +
				"from order_detail A " +
				"left join item B on B.ITEM_ID=A.PRODUCT_ID "+
//				"left join item B on B.ITEM_ID=A.PRODUCT_ID and B.FITEM_ID=A.FPRODUCT_ID " +
				"left join itemtype C on C.item_typeid=B.item_typeid  " +
				"where ORDER_ID=? and PRODUCT_ID=? and ISSUE_NUM=? and drawingId=?  ";
		}
		
		String[] params = {orderId,productId,issueNum,drawingId};
		AoHeader aoHeader = null;
		try {
			aoHeader = Sqlhelper.exeQueryBean(sql, params, AoHeader.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		request.setAttribute("result", aoHeader);
		if (!StringUtil.isNullOrEmpty(pathTo)) {
			request.getRequestDispatcher("craftworkManage/"+pathTo).forward(request, response);
		}
	}
	
	public void AddAoHeader(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		Map<String, String> requestValueMap = new HashMap<String, String>();	//用来封装request请求中的参数
		
		HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
		try {
			requestValueMap = UploadUtils.UploadLoadFile(request, response, createPerson, requestValueMap);	//保存文件+得到request属性值
		} catch (FileUploadException e1) {
			String json = "{\"result\":\"文件太大，请检查文件上大小！！\"}";
			response.getWriter().append(json).flush();
			e1.printStackTrace();
		}
		
		AoHeader aoHeader = Mapping.convertObjectFromMap(AoHeader.class, requestValueMap);
		String sql = "insert into aoHeader " +
				"(orderId,productId,productName,productType,issueNum," +
				"drawingId,spec,aoContent,productNum,deadLine," +
				"aoHeadermemo,aoPaper,aoId, " +
				"createPerson,createTime,changePerson,changeTime)" +
				"values" +
				"(?,?,?,?,?," +
				"?,?,?,?,to_date(?,'yyyy-mm-dd,hh24:mi:ss')," +
				"?,?,?," +
				"?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'),?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'))";
		String[] params = {aoHeader.getOrderId(),aoHeader.getProductId(),aoHeader.getProductName(),aoHeader.getProductType(),aoHeader.getIssueNum(),
				aoHeader.getDrawingId(),aoHeader.getSpec(),aoHeader.getAoContent(),aoHeader.getProductNum()+"",aoHeader.getDeadLine(),
				aoHeader.getAoHeadermemo(),aoHeader.getAoPaper(),aoHeader.getAoId(),
				createPerson,createTime,changePerson,changeTime};
		try {
			Sqlhelper.executeUpdate(sql, params);
			
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (Exception e) {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}
	
	public void updateAoHeader(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		Map<String, String> requestValueMap = new HashMap<String, String>();	//用来封装request请求中的参数
		
		HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    
		try {
			requestValueMap = UploadUtils.UploadLoadFile(request, response, createPerson, requestValueMap);	//保存文件+得到request属性值
		} catch (FileUploadException e1) {
			String json = "{\"result\":\"文件太大，请检查文件上大小！！\"}";
			response.getWriter().append(json).flush();
			e1.printStackTrace();
		}
		
		AoHeader aoHeader = Mapping.convertObjectFromMap(AoHeader.class, requestValueMap);
		
		String sql = "";
		String[] params = null;
		if (!StringUtil.isNullOrEmpty(aoHeader.getAoPaper())) {
			sql = "update aoHeader set " +
				"productName=?,productType=?," +
				"spec=?,aoContent=?,productNum=?,deadLine=to_date(?,'yyyy-mm-dd,hh24:mi:ss')," +
				"aoHeadermemo=?,aoPaper=?," +
				"changePerson=?,changeTime=to_date(?,'yyyy-mm-dd,hh24:mi:ss') " +
				"where  orderId=? and productId=? and issueNum=? and drawingId=?  " ;
			params = new String []{aoHeader.getProductName(),aoHeader.getProductType(),
				aoHeader.getSpec(),aoHeader.getAoContent(),aoHeader.getProductNum()+"",aoHeader.getDeadLine(),
				aoHeader.getAoHeadermemo(),aoHeader.getAoPaper(),
				createPerson,createTime,
				aoHeader.getOrderId(),aoHeader.getProductId(),aoHeader.getIssueNum(),aoHeader.getDrawingId()};
		}else {
			sql = "update aoHeader set " +
				"productName=?,productType=?," +
				"spec=?,aoContent=?,productNum=?,deadLine=to_date(?,'yyyy-mm-dd,hh24:mi:ss')," +
				"aoHeadermemo=?," +
				"changePerson=?,changeTime=to_date(?,'yyyy-mm-dd,hh24:mi:ss') " +
				"where  orderId=? and productId=? and issueNum=? and drawingId=?  " ;
			params = new String []{aoHeader.getProductName(),aoHeader.getProductType(),
				aoHeader.getSpec(),aoHeader.getAoContent(),aoHeader.getProductNum()+"",aoHeader.getDeadLine(),
				aoHeader.getAoHeadermemo(),
				createPerson,createTime,
				aoHeader.getOrderId(),aoHeader.getProductId(),aoHeader.getIssueNum(),aoHeader.getDrawingId()};
		}
		try {
			Sqlhelper.executeUpdate(sql, params);
			
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (Exception e) {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void addAoDetail(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		AoDetail aoDetail = MyBeanUtil.get(request, AoDetail.class);
		//AoDetail aoDetail = Mapping.convertObjectFromMap(AoDetail.class, request.getParameterMap());
		HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    int count = 0;
		String sql = "insert into aodetail " +
				"(AO_NO,PRODUCTID,ISSUE_NUM,WORKPLACECODE,AONAME," +
	    		"WORKPLACENAME,AOVER,PARTNO,AO_TIME,aoId," +
	    		"AO_CONTENT," +
	    		"createPerson,createTime,changePerson,changeTime)" +
				"values" +
				"(?,?,?,?,?," +
				"?,?,?,?,?," +
				"?," +
				"?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'),?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'))";
		String[] params = {aoDetail.getAoNo(),aoDetail.getProductId(),aoDetail.getIssueNum(),aoDetail.getWorkplaceCode(),aoDetail.getAoName(),
				aoDetail.getWorkplaceName(),aoDetail.getAoVer(),aoDetail.getPartNo(),aoDetail.getAoTime()+"",aoDetail.getAoId(),
				aoDetail.getAoContent(),
				createPerson,createTime,createPerson,createTime};
		
		try {
			count = Sqlhelper.executeUpdate(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (count>0) {
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		}else {
			
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		}
	}
	
	public void AodetailData(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String aoId = ChineseCode.toUTF8(request.getParameter("aoId").trim());
		String aoNo = ChineseCode.toUTF8(request.getParameter("aoNo").trim());
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		
		String sql = "select PRODUCTID productId,ISSUE_NUM issueNum,ITEMID,AO_NO aoNo,AOVER aoVer," +
				"AO_TIME aoTime,AONAME aoName,WORKPLACECODE,WORKPLACENAME," +
				"PARTNO partNo,AO_CONTENT aoContent,AOID aoId " +
				"from aodetail " +
				"where aoId=? and ao_no=? ";
		String[] params = {aoId,aoNo};
		
		AoDetail aoDetail = new AoDetail();
		try {
			aoDetail = Sqlhelper.exeQueryBean(sql, params, AoDetail.class);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("orderId", orderId);
		request.setAttribute("result", aoDetail);
		request.getRequestDispatcher("craftworkManage/"+pathTo).forward(request, response);
		
	}
	
	public void UpdateAodetail(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    
		AoDetail aoDetail = MyBeanUtil.get(request, AoDetail.class);
		
		String sql = "update aodetail set " +
				"PRODUCTID=?,ISSUE_NUM=?,WORKPLACECODE=?,AONAME=?," +
	    		"WORKPLACENAME=?,AOVER=?,PARTNO=?,AO_TIME=?," +
	    		"AO_CONTENT=?," +
	    		"changePerson=?,changeTime=to_date(?,'yyyy-mm-dd,hh24:mi:ss') " +
				"where aoId=? and ao_No=? ";
		
		String[] params = {aoDetail.getProductId(),aoDetail.getIssueNum(),aoDetail.getWorkplaceCode(),aoDetail.getAoName(),
				aoDetail.getWorkplaceName(),aoDetail.getAoVer(),aoDetail.getPartNo(),aoDetail.getAoTime()+"",
				aoDetail.getAoContent(),
				createPerson,createTime,
				aoDetail.getAoId(),aoDetail.getAoNo()};
		
		int count=0;
		try {
			count = Sqlhelper.executeUpdate(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (count>0) {
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		}else {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		}
		
	}
	
	public void GoAocmtyf(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String aoId = ChineseCode.toUTF8(request.getParameter("aoId").trim());
		String aoNo = ChineseCode.toUTF8(request.getParameter("aoNo").trim());
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		
		request.setAttribute("aoId",aoId );
		request.setAttribute("aoNo",aoNo );
		request.setAttribute("orderId",orderId );
		request.getRequestDispatcher("craftworkManage/"+pathTo).forward(request, response);
	}
	
	public void AddAoCMTYF(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String aoNo = ChineseCode.toUTF8(request.getParameter("aoNo"));
		String aoId = ChineseCode.toUTF8(request.getParameter("aoId"));
		//String orderId = ChineseCode.toUTF8(request.getParameter("orderId"));
		
		String focut = StringUtil.isNullOrEmpty(request.getParameter("focut"))?"":request.getParameter("focut");
		String fomeasure = StringUtil.isNullOrEmpty(request.getParameter("fomeasure"))?"":request.getParameter("fomeasure");
		String fotool = StringUtil.isNullOrEmpty(request.getParameter("fotool"))?"":request.getParameter("fotool");
		String fomaterial = StringUtil.isNullOrEmpty(request.getParameter("fomaterial"))?"":request.getParameter("fomaterial");
		String foaccessory = StringUtil.isNullOrEmpty(request.getParameter("foaccessory"))?"":request.getParameter("foaccessory");
		
		String cut_num = StringUtil.isNullOrEmpty(request.getParameter("cut_num"))?"0":request.getParameter("cut_num");
		String measure_num = StringUtil.isNullOrEmpty(request.getParameter("measure_num"))?"0":request.getParameter("measure_num");
		String tool_num = StringUtil.isNullOrEmpty(request.getParameter("tool_num"))?"0":request.getParameter("tool_num");
		String material_num = StringUtil.isNullOrEmpty(request.getParameter("material_num"))?"0":request.getParameter("material_num");
		String accessory_num = StringUtil.isNullOrEmpty(request.getParameter("accessory_num"))?"0":request.getParameter("accessory_num");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
	    HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    String sql = "update aodetail set " +
				"cut=?,accessory=?,material=?,measure=?,tool=?," +
				"cutNum=?,measureNum=?,toolNum=?,materialNum=?,accessoryNum=?," +
				"CHANGEPERSON=?,CHANGETIME=to_date(?,'yyyy-mm-dd,hh24:mi:ss') " +
				"where aoId=? and ao_no=? ";
	    
	    String[] params = {focut,foaccessory,fomaterial,fomeasure,fotool,
	    		cut_num,measure_num,tool_num,material_num,accessory_num,
	    		changePerson,changeTime,
	    		aoId,aoNo};
	    
	    int count=0;
		try {
			count = Sqlhelper.executeUpdate(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    if (count>0) {
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		}else {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		}
	}
	
	public void AoCMTYFList(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String aoNo = request.getParameter("aoNo");
		String aoId = request.getParameter("aoId");
		
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "ao_No";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    
	    String totalCountSql = "select count(*) from aodetail where aoId=? and isInUse='1'";
	    String[] params1 = {aoNo};
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, params1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    String sql= "select B.aoId,B.ao_no aoNo," +
				"B.CUT,B.ACCESSORY,B.MATERIAL,B.MEASURE,B.TOOL," +
				"B.CUTNUM,B.MEASURENUM,B.TOOLNUM,B.MATERIALNUM,B.ACCESSORYNUM  " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from aodetail EM where aoId=? and isInUse='1' order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" and aoId=? and isInUse='1' order by "+orderStr;
	    
	    String [] params = {aoId,aoId};
	
	    List<AoDetail> aoDetails = null;
	    try {
			aoDetails = Sqlhelper.exeQueryList(sql, params, AoDetail.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0,len=aoDetails.size();i<len;i++){
			AoDetail aoDetail = new AoDetail();
			aoDetail = aoDetails.get(i);
			if (!StringUtil.isNullOrEmpty(aoDetail.getCut())) {
				String[] strArray = aoDetail.getCut().split(",");
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
				aoDetail.setCut(cut);
			}
		}
		
		for(int i=0,len=aoDetails.size();i<len;i++){
			AoDetail aoDetail = new AoDetail();
			aoDetail = aoDetails.get(i);
			if (!StringUtil.isNullOrEmpty(aoDetail.getAccessory())) {
				String[] strArray = aoDetail.getAccessory().split(",");
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
				aoDetail.setAccessory(Accessory);
			}
		}
		
		for(int i=0,len=aoDetails.size();i<len;i++){
			AoDetail aoDetail = new AoDetail();
			aoDetail = aoDetails.get(i);
			if (!StringUtil.isNullOrEmpty(aoDetail.getMaterial())) {
				String[] strArray = aoDetail.getMaterial().split(",");
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
				aoDetail.setMaterial(Material);
			}
		}
		
		for(int i=0,len=aoDetails.size();i<len;i++){
			AoDetail aoDetail = new AoDetail();
			aoDetail = aoDetails.get(i);
			if (!StringUtil.isNullOrEmpty(aoDetail.getMeasure())) {
				String[] strArray = aoDetail.getMeasure().split(",");
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
				aoDetail.setMeasure(Measure);
			}
		}
		
		for(int i=0,len=aoDetails.size();i<len;i++){
			AoDetail aoDetail = new AoDetail();
			aoDetail = aoDetails.get(i);
			if (!StringUtil.isNullOrEmpty(aoDetail.getTool())) {
				String[] strArray = aoDetail.getTool().split(",");
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
				aoDetail.setTool(Tool);
			}
		}
		
		String json = PluSoft.Utils.JSON.Encode(aoDetails);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
		
	}
	
	public void aocmtyfEdit(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String aoId = request.getParameter("aoId");
		String aoNo = request.getParameter("aoNo");
		
	    String sql= "select A.aoId,A.ao_NO aoNo," +
				"A.CUT,A.ACCESSORY,A.MATERIAL,A.MEASURE,A.TOOL," +
				"A.CUTNUM,A.MEASURENUM,A.TOOLNUM,A.MATERIALNUM,A.ACCESSORYNUM," +
				"B.item_name cutName,C.item_name accessoryName,D.item_name materialName,E.item_name measureName,F.item_name toolName " +
	    	"from  aodetail A " +
	    	"left join item B on B.item_id=A.cut "+
	    	"left join item C on C.item_id=A.ACCESSORY "+
	    	"left join item D on D.item_id=A.MATERIAL "+
	    	"left join item E on E.item_id=A.MEASURE "+
	    	"left join item F on F.item_id=A.TOOL "+
	    	"where aoId=? and ao_no=? and isInUse='1' ";
	    
	    String[] params = {aoId,aoNo};
	    
	    AoDetail aoDetail = new AoDetail();
	    try {
			aoDetail = Sqlhelper.exeQueryBean(sql, params, AoDetail.class);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		if (!StringUtil.isNullOrEmpty(aoDetail.getCut())) {
			String[] strArray = aoDetail.getCut().split(",");
			String cut = "";
			for (int j = 0; j < strArray.length; j++) {
				String cutSql = "select item_Name from item where item_Id=?";
				String [] paramsCut = {strArray[j]};
				try {
					cut+=Sqlhelper.exeQueryString(cutSql, paramsCut)+",";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			cut = cut.substring(0,cut.length()-1);
			aoDetail.setCutName(cut);
		}
	
		if (!StringUtil.isNullOrEmpty(aoDetail.getAccessory())) {
			String[] strArray = aoDetail.getAccessory().split(",");
			String Accessory = "";
			for (int j = 0; j < strArray.length; j++) {
				String cutSql = "select item_Name from item where item_Id=?";
				String [] paramsCut = {strArray[j]};
				try {
					Accessory+=Sqlhelper.exeQueryString(cutSql, paramsCut)+",";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Accessory = Accessory.substring(0,Accessory.length()-1);
			aoDetail.setAccessoryName(Accessory);
		}
	
		if (!StringUtil.isNullOrEmpty(aoDetail.getMaterial())) {
			String[] strArray = aoDetail.getMaterial().split(",");
			String Material = "";
			for (int j = 0; j < strArray.length; j++) {
				String cutSql = "select item_Name from item where item_Id=?";
				String [] paramsCut = {strArray[j]};
				try {
					Material+=Sqlhelper.exeQueryString(cutSql, paramsCut)+",";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Material = Material.substring(0,Material.length()-1);
			aoDetail.setMaterialName(Material);
		}
	
		if (!StringUtil.isNullOrEmpty(aoDetail.getMeasure())) {
			String[] strArray = aoDetail.getMeasure().split(",");
			String Measure = "";
			for (int j = 0; j < strArray.length; j++) {
				String cutSql = "select item_Name from item where item_Id=?";
				String [] paramsCut = {strArray[j]};
				try {
					Measure+=Sqlhelper.exeQueryString(cutSql, paramsCut)+",";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Measure = Measure.substring(0,Measure.length()-1);
			aoDetail.setMeasureName(Measure);
		}
	
		if (!StringUtil.isNullOrEmpty(aoDetail.getTool())) {
			String[] strArray = aoDetail.getTool().split(",");
			String Tool = "";
			for (int j = 0; j < strArray.length; j++) {
				String cutSql = "select item_Name from item where item_Id=?";
				String [] paramsCut = {strArray[j]};
				try {
					Tool+=Sqlhelper.exeQueryString(cutSql, paramsCut)+",";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Tool = Tool.substring(0,Tool.length()-1);
			aoDetail.setToolName(Tool);
		}
		
		request.setAttribute("result", aoDetail);
	
		if (!"".equals(pathTo)) {
			request.getRequestDispatcher("craftworkManage/"+pathTo).forward(request, response);
		}
	}
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
