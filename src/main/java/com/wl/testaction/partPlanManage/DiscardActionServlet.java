package com.wl.testaction.partPlanManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import JSOM.FandT;
import JSOM.JackSonTrans;

import com.wl.common.ProductStatus;
import com.wl.forms.FoDetail;
import com.wl.forms.Order;
import com.wl.forms.PartsPlan;
import com.wl.forms.Product;
import com.wl.forms.User;
import com.wl.testaction.utils.GandTResult;
import com.wl.tools.ChineseCode;
import com.wl.tools.DateTimeUtil;
import com.wl.tools.MyBeanUtil;
import com.wl.tools.Sqlhelper;
import com.wl.tools.UUIDHexGenerator;
import com.xm.testaction.qualitycheck.ToBarcode;

public class DiscardActionServlet extends HttpServlet {
	private static final Log log = LogFactory.getLog(DateTimeUtil.class);
	public void ProductInfoServlet(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
		String FProductId = ChineseCode.toUTF8(request.getParameter("FProductId").trim());
		
		String orderSql = "select ORDER_ID orderId,PRODUCT_ID productId,ISSUE_NUM issueNum,LOT,PURDUCT_NUM productNum," +
			    	"PRODUCT_STATUS productStatus,FPRODUCT_ID fproductId,PRODUCT_NAME productName,SPEC,drawingId," +
			    	"B.ITEM_TYPEID itemTypeId,C.ITEM_TYPEDESC itemTypeName " +
			    	"from order_detail A " +
			    	"left join item B on B.ITEM_ID=A.PRODUCT_ID "+
//			    	"left join item B on B.ITEM_ID=A.PRODUCT_ID and B.FITEM_ID=A.FPRODUCT_ID " +
			    	"left join itemtype C on C.item_typeid=B.item_typeid  " +
			    	"where ORDER_ID=? and PRODUCT_ID=? and FPRODUCT_ID=? ";
		String[] params = {orderId,productId,FProductId};
		Order order = new Order();
		try {
			order = Sqlhelper.exeQueryBean(orderSql, params, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("result", order);

		if (!"".equals(pathTo)) {
			request.getRequestDispatcher("partPlanManage/"+pathTo).forward(request, response);
		}
	}
	
	public void GoMainBody(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
//		String FProductId = ChineseCode.toUTF8(request.getParameter("FProductId").trim());
		String issueNum = ChineseCode.toUTF8(request.getParameter("issueNum").trim());
		String drawingId = ChineseCode.toUTF8(request.getParameter("drawingId").trim());
		String barcode=ChineseCode.toUTF8(request.getParameter("barcode").trim());
		String isDone = "0";
		String countSql = "select state from TODISCARD " +
				"where order_Id=? and product_Id=? and issue_Num=? and drawingId=? ";
		
		String[] params1 = {orderId,productId,issueNum,drawingId};
		
		int count = 0;
		try {
			count = Integer.parseInt(Sqlhelper.exeQueryString(countSql, params1));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		isDone = count>=20?"1":"0";					//count>0做过零件计划了,否则没有做过零件计划
		
		request.setAttribute("isDone", isDone);

		String sql = "select A.ORDER_ID orderId,A.PRODUCT_ID productId,A.ISSUE_NUM issueNum,E.rejectNum productNum," +
	    	"E.state productStatus,A.FPRODUCT_ID fproductId,A.PRODUCT_NAME productName,A.SPEC spec,A.drawingId ," +
	    	"B.ITEM_TYPEID productType,C.ITEM_TYPEDESC productTypeName,A.isWaiXie,A.isGongYi,A.isCaiGou, " +
	    	"to_char(A.E_Time,'yyyy-mm-dd') ETime,to_char(D.endTIme,'yyyy-mm-dd') endTime,E.barcode " +
	    	"from order_detail A " +
	    	"left join orders D on D.order_Id=A.order_Id "+
	    	"left join item B on B.ITEM_ID=A.PRODUCT_ID "+
//	    	"left join item B on B.ITEM_ID=A.PRODUCT_ID and B.FITEM_ID=A.FPRODUCT_ID " +
	    	"left join itemtype C on C.item_typeid=B.item_typeid  " +
	    	"left join todiscard E on E.order_Id=A.order_Id and E.product_Id=A.product_Id and E.issue_num=A.issue_Num " +
	    	"where A.ORDER_ID=? and A.PRODUCT_ID=? and A.issue_num=? and A.drawingId=? and E.barcode=?";

		String[] params = {orderId,productId,issueNum,drawingId,barcode};
		
		Order order = new Order();
		try {
			order = Sqlhelper.exeQueryBean(sql, params, Order.class);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("result", order);
		
		if (!"".equals(pathTo)) {
			request.getRequestDispatcher("partPlanManage/"+pathTo).forward(request, response);
		}
	}
	
	public void GoPartPlanAdd(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
		String fproductId = ChineseCode.toUTF8(request.getParameter("fproductId").trim());
		String issueNum = ChineseCode.toUTF8(request.getParameter("issueNum").trim());
		String drawingId = ChineseCode.toUTF8(request.getParameter("drawingId").trim());
		String partNum = ChineseCode.toUTF8(request.getParameter("partNum"));
		
		String sql = "select ORDER_ID orderId,PRODUCT_ID productId,ISSUE_NUM issueNum,PURDUCT_NUM productNum," +
				"E_TIME planETime,FPRODUCT_ID fproductId,PRODUCT_NAME productName,drawingId,productType productTypeId " +
				"from order_detail " +
				"where ORDER_ID=? and PRODUCT_ID=? and FPRODUCT_ID=? and ISSUE_NUM=? and  drawingId=? ";
		String[] params = {orderId,productId,fproductId,issueNum,drawingId};
		PartsPlan partsPlan = new PartsPlan();
		try {
			partsPlan = Sqlhelper.exeQueryBean(sql, params, PartsPlan.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("result", partsPlan);
		request.setAttribute("isDone", "0");
		
		if (!"".equals(pathTo)) {
			request.getRequestDispatcher("partPlanManage/"+pathTo).forward(request, response);
		}
	}
//	生产零件计划   第一步 
	public void AddpartPlan(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
	    Order order = MyBeanUtil.get(request, Order.class);
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		
		try {
			addPartsPlanUtil(request, orders);
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (Exception e) {
			e.printStackTrace();
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		}
		
//		String sql = "insert into partsPlan" +
//				"(partsPlanId,productId,orderId,partNum,planBTime," +
//				"planETime,productType,issueNum,planPerson,planTime," +
//				"drawingId, "+
//				"createPerson,createTime,changePerson,changeTime) " +
//				"values" +
//				"(?,?,?,?,to_date(?,'yyyy-mm-dd,hh24:mi:ss')," +
//				"to_date(?,'yyyy-mm-dd,hh24:mi:ss'),?,?,?,to_date(?,'yyyy-mm-dd,hh24:mi:ss')," +
//				"?,"+
//				"?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'),?,to_date(?,'yyyy-mm-dd,hh24:mi:ss')) ";
//		String[] params = {partsPlan.getPartsPlanId(),partsPlan.getProductId(),partsPlan.getOrderId(),partsPlan.getPartNum()+"",partsPlan.getPlanBTime(),
//				partsPlan.getPlanETime(),partsPlan.getProductTypeId(),partsPlan.getIssueNum(),createPerson,createTime,
//				partsPlan.getDrawingId(),
//				createPerson,createTime,createPerson,createTime};
//		
//		String ProductStatusSql = "update order_detail set product_status=? " +
//				"where order_id=? and product_id=? and issue_num=? and drawingId=? ";
//		String[] params2 = {ProductStatus.PARTPLANING+"",partsPlan.getOrderId(),partsPlan.getProductId(),partsPlan.getIssueNum(),partsPlan.getDrawingId()};
//
//		try {
//			Sqlhelper.executeUpdate(sql, params);
//			Sqlhelper.executeUpdate(ProductStatusSql, params2);
//			
//			String json = "{\"result\":\"操作成功!\"}";
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append(json).flush();
//		} catch (SQLException e) {
//			String json = "{\"result\":\"操作失败!\"}";
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append(json).flush();
//			e.printStackTrace();
//		}
		
	}
	
	public void SeePartsPlan(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
		//String fproductId = ChineseCode.toUTF8(request.getParameter("fproductId").trim());
		String issueNum = ChineseCode.toUTF8(request.getParameter("issueNum").trim());
		String drawingId = ChineseCode.toUTF8(request.getParameter("drawingId").trim());
		String barcode=ChineseCode.toUTF8(request.getParameter("barcode").trim());
		
		String sql = "select A.partsPlanId,A.orderId,A.productId,A.issueNum,A.drawingId," +
				"A.planPerson,A.planTime,A.partNum productNum,A.planBTime,A.planETime," +
				"A.productType,B.barcode " +
				"from partsPlan A " +
				"left join todiscard B ON A.orderId=B.order_Id and A.productId=B.product_Id and A.issueNum=B.issue_num and A.isDiscard=B.BARCODE " +
				"where A.orderId=? and A.productId=? and A.issueNum=? and A.drawingId=? and barcode=?";
		String[] params = {orderId,productId,issueNum,drawingId,barcode};
		
		PartsPlan partsPlan = new PartsPlan();
		try {
			partsPlan = Sqlhelper.exeQueryBean(sql, params, PartsPlan.class);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String proPlanSql = "select state productStatus " +
					"from todiscard A " +
					"where order_Id=? and product_id=? and issue_Num=? and drawingId=? ";
		String[] params2 = {orderId,productId,issueNum,drawingId};
		
		int productStatus = 0;
		try {
			productStatus = Integer.parseInt(Sqlhelper.exeQueryString(proPlanSql, params2));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("转化成整数的过程中产生异常！");
			log.error("转化成整数的过程中产生异常！", e);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("查询数据库失败，没找到产品状态！");
			log.error("查询数据库失败，没找到产品状态！");
		}
		if (productStatus>=ProductStatus.PROPLANING) {			//做过工序计划了
			request.setAttribute("proPlanDone", "1");
		}else {
			request.setAttribute("proPlanDone", "0");
		}
		request.setAttribute("result", partsPlan);
		request.setAttribute("isDone", "1");
		
		if (!"".equals(pathTo)) {
			request.getRequestDispatcher("partPlanManage/"+pathTo).forward(request, response);
		}
	}
	
	
	
	public void UpdatepartPlan(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    
		PartsPlan partsPlan = MyBeanUtil.get(request, PartsPlan.class);
		String sql = "update partsPlan set partNum=?,planBTime=to_date(?,'yyyy-mm-dd,hh24:mi:ss')," +
				"planETime=to_date(?,'yyyy-mm-dd,hh24:mi:ss'),issueNum=?,planPerson=?,planTime=to_date(?,'yyyy-mm-dd,hh24:mi:ss')," +
				"changePerson=?,changeTime=to_date(?,'yyyy-mm-dd,hh24:mi:ss') " +
				"where partsPlanId=? ";
		String[] params = {partsPlan.getPartNum()+"",partsPlan.getPlanBTime(),
				partsPlan.getPlanETime(),partsPlan.getIssueNum(),createPerson,createTime,
				createPerson,createTime,partsPlan.getPartsPlanId()};
		
		int count = 0;
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
	
	public void addProPlan(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		Order order = MyBeanUtil.get(request, Order.class);
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		
		try {
			addPartsPlanUtil(request, orders);
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (Exception e) {
			e.printStackTrace();
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		}
		
	}
	
	public void GoProPlan(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		PartsPlan partsPlan = MyBeanUtil.get(request, PartsPlan.class);
		
		request.setAttribute("result", partsPlan);
		
		if (!"".equals(pathTo)) {
			request.getRequestDispatcher("partPlanManage/"+pathTo).forward(request, response);
		}
	}
	
	public void listProPlan(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		PartsPlan partsPlan = MyBeanUtil.get(request, PartsPlan.class);
		List<FandT> fandTs = null;
		fandTs = GandTResult.getPartProGandT(partsPlan);
		
		response.setCharacterEncoding("UTF-8");
		String json = JackSonTrans.JsonBack(fandTs);
		response.getWriter().append(json).flush();
		System.out.println(json);
	}
	
	public void allPartsGandT(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		List<FandT> fandTs = null;
		fandTs = GandTResult.getAllPartsGandT();
		
		response.setCharacterEncoding("UTF-8");
		String json = JackSonTrans.JsonBack(fandTs);
		response.getWriter().append(json).flush();
		System.out.println(json);
	}
	
	public void GoPartGT(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {	
		String data = ChineseCode.toUTF8(request.getParameter("data")).replaceAll("\"", "");
		request.getSession().setAttribute("DiscardWillSeeParts",data);
		System.out.println(data);
		System.out.println(pathTo);
//	if (!"".equals(pathTo)) {
//			request.getRequestDispatcher("partPlanManage/"+pathTo).forward(request, response);
//		}
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void AddpartsPlan(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {	
		String data = ChineseCode.toUTF8(request.getParameter("data")).replaceAll("\"", "");
		System.out.println(data);
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<Order> orders = JSONArray.toList(jsonArray, Order.class);
		try {
			addPartsPlanUtil(request, orders);
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (Exception e) {
			e.printStackTrace();
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		}
	}
//	
	@SuppressWarnings("unchecked")
	private int addPartsPlanUtil(HttpServletRequest request,List<Order> orders) throws Exception{
		HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
//	    批量定制零件计划
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    
	    for(Order order: orders){
	    	
	    	String orderSql = "select A.order_Id orderId,A.product_id productId,A.issue_num issueNum,A.drawingId,B.rejectNum productNum," +
					"B_time btime,E_time etime, product_status productStatus,product_name productName,iswaixie," +
					"isgongyi,iscaigou,productType,B.barCode " +
					"from order_detail A " +
					"left join todiscard B on A.order_Id=B.order_Id and A.product_Id=B.product_Id and A.issue_Num=B.issue_Num " +
					"where A.order_Id=? and A.product_id=? and A.issue_num=? and B.barCode=? ";
			String[] orderParams = {order.getOrderId(),order.getProductId(),order.getIssueNum(),order.getBarcode()};
			
			Order selectedOrder = null;
			selectedOrder = Sqlhelper.exeQueryBean(orderSql, orderParams, Order.class);
			////////////////////////////查出零件相关信息//////////////////////////////////
			
			
			
			String getProSql = "select A.foId,A.orderId,A.productId,A.productName,A.issueNum," +
					"A.drawingId,A.productType,C.rejectNum productNum," +
					"B.FO_NO,B.FO_OPNAME,B.OPER_TIME,B.EQUIPCODE,B.IS_CO isco " +
					"from foHeader A  " +
					"left join fo_detail B on A.foId=B.foId and A.productId=B.product_id  " +
					"left join todiscard C on A.orderId=C.order_Id and A.productId=C.product_Id and A.issueNum=C.issue_Num " +
					"where A.orderId=? and A.productId=? and A.issueNum=? and A.drawingId=? and C.barCode=? and B.isinuse='1' ";
			String[] getProParams = {order.getOrderId(),order.getProductId(),order.getIssueNum(),order.getDrawingId(),order.getBarcode()};
			
			List<FoDetail> foDetails = new ArrayList<FoDetail>();
			foDetails = Sqlhelper.exeQueryList(getProSql, getProParams, FoDetail.class);
			//////////////////以上为查出零件对应的所有工序/////////////////
	    	
	    	
	    	/**************************************零件计划*****************************************/
//			9-10  空指针异常 
			String[] timeString = order.getSortie().split(",");
			int jiangeTime = Integer.parseInt(timeString[0])*60+Integer.parseInt(timeString[1]);//工序间时间间隔，以分钟为单位
			System.out.println("jiangeTime="+jiangeTime);
			
			double partPlanAheadTime = 0;
			double jiaohuotiqianqi = 12*60;				//比计划早半天时间做完
			for(FoDetail foDetail:foDetails){
				partPlanAheadTime += (foDetail.getOperTime())*(foDetail.getProductNum())+jiangeTime;
			}
			partPlanAheadTime = partPlanAheadTime - jiangeTime+jiaohuotiqianqi;
	    	
	    	String UUID = UUIDHexGenerator.getInstance().generate();
	    	
			String sql = "insert into partsPlan" +
					"(partsPlanId,productId,orderId,partNum,partStatus," +
					"planBTime," +
					"planETime," +
					"productType,issueNum,planPerson,planTime,drawingId, "+
					"createPerson,createTime,changePerson,changeTime,isDiscard,codeid) " +
					"values" +
					"(?,?,?,?,?," +
					"to_date(?,'yyyy-mm-dd,hh24:mi:ss')-numtodsinterval("+partPlanAheadTime+",'minute')," +
					"to_date(?,'yyyy-mm-dd,hh24:mi:ss')-numtodsinterval("+jiaohuotiqianqi+",'minute')," +
					"?,?,?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'),?,"+
					"?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'),?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'),?,?) ";
			String[] params = {UUID,selectedOrder.getProductId(),selectedOrder.getOrderId(),selectedOrder.getProductNum()+"",ProductStatus.PARTPLANING+"",
					selectedOrder.geteTime(),
					selectedOrder.geteTime(),
					selectedOrder.getProductType(),selectedOrder.getIssueNum(),createPerson,createTime,selectedOrder.getDrawingId(),
					createPerson,createTime,createPerson,createTime,selectedOrder.getBarcode(),selectedOrder.getBarcode()};
			
			String ProductStatusSql = "update toDiscard  set state=? " +
					"where order_id=? and product_id=? and issue_num=? and drawingId=? ";
			String[] params2 = {ProductStatus.PARTPLANING+"",selectedOrder.getOrderId(),selectedOrder.getProductId(),selectedOrder.getIssueNum(),selectedOrder.getDrawingId()};
		
			Sqlhelper.executeUpdate(sql, params);
			Sqlhelper.executeUpdate(ProductStatusSql, params2);
//			9-5 xiexiaoming 生成条形码 
//			String barcode ="";
//			System.out.println();
//	    	try {
//				barcode = ToBarcode.toBarcode(UUID);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
	    	/**************************************零件计划****************************************/
	    	
	    	
	    	/**************************************工序计划****************************************/
			String EndTimesql = "select planETime from partsPlan where partsPlanId='"+UUID+"' ";
			//String Etime =selectedOrder.geteTime();					//零件计划中的计划结束时间
			String Etime =Sqlhelper.exeQueryString(EndTimesql, null);	//零件计划中的计划结束时间
			int partNum = selectedOrder.getProductNum();				//零件数量
			double head = 0;											//head在前面,	head________________________________end
			//double end = GOAHEADTIME*partNum/60;						//
			double end = 0;
			int count =0;												//插入成功的记录数
			Collections.sort(foDetails);								//此时的fodetails已经按照foNo从大到小的顺序排列了
			
			for(int i=0,len=foDetails.size();i<len;i++){
				FoDetail foDetail = foDetails.get(i);
				
				head = end + foDetail.getOperTime()*partNum;	
				String uuid = UUIDHexGenerator.getInstance().generate();
//				String ProPlanSql = "insert into processesPlan " +
//						"(processPlanId,orderId,productId,issueNum,drawingId," +
//						"operId,num,planEndTime,planStartTime,operStatus," +
//						"planPerson,planTime," +
//						"createPerson,createTime,changePerson,changeTime)" +
//						"values " +
//						"(?,?,?,?,?," +
//						"?,?,to_date(?,'yyyy-mm-dd,hh24:mi:ss')-"+end+"/24,to_date(?,'yyyy-mm-dd,hh24:mi:ss')-"+head+"/24,?," +
//						"?,to_date(?,'yyyy-mm-dd,hh24:mi:ss')," +
//						"?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'),?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'))";
				
				String ProPlanSql = "insert into processesPlan " +
						"(processPlanId,orderId,productId,issueNum,drawingId," +
						"operId,num,planEndTime,planStartTime,operStatus," +
						"planPerson,planTime," +
						"createPerson,createTime,changePerson,changeTime,machineId,isco,isDiscard)" +
						"values " +
						"(?,?,?,?,?," +
						"?,?,to_date(?,'yyyy-mm-dd,hh24:mi:ss')-numtodsinterval("+end+",'minute'),to_date(?,'yyyy-mm-dd,hh24:mi:ss')-numtodsinterval("+head+",'minute'),?," +
						"?,to_date(?,'yyyy-mm-dd,hh24:mi:ss')," +
						"?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'),?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'),?,?,?)";
				
				String[] ProPlanParams2 = {uuid,foDetail.getOrderId(),foDetail.getProductId(),foDetail.getIssueNum(),foDetail.getDrawingId(),
						foDetail.getFoNo(),foDetail.getProductNum()+"",Etime,Etime,"1",
						createPerson,createTime,
						createPerson,createTime,createPerson,createTime,foDetail.getEquipCode(),foDetail.getIsCo(),selectedOrder.getBarcode()};
				try {
					count += Sqlhelper.executeUpdate(ProPlanSql, ProPlanParams2);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				end = head+jiangeTime;
			}
			
			String ProductStatusProSql = "update toDiscard set state=? " +
					"where order_id=? and product_id=? and issue_num=? and drawingId=? ";
			String[] ProductStatusProParams2 = {ProductStatus.PROPLANING+"",order.getOrderId(),order.getProductId(),order.getIssueNum(),order.getDrawingId()};

			Sqlhelper.executeUpdate(ProductStatusProSql, ProductStatusProParams2);
			
			
		/**************************************工序计划****************************************/
	    }
		return 0;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void seePartsPlan(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {	
		String data = ChineseCode.toUTF8(request.getParameter("data"));
		request.setAttribute("data", data);
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<Order> orders = JSONArray.toList(jsonArray, Order.class);
	
		List<FandT> fandTs = null;
		if (orders.size()==1) {
			Order order = orders.get(0);
			fandTs = DiscardGandTResult.getOnePartGandT(order);
		}else {
			fandTs = DiscardGandTResult.getSelectedPartsProGandT(orders);
		}
		
		
		response.setCharacterEncoding("UTF-8");
		String json = JackSonTrans.JsonBack(fandTs);
		response.getWriter().append(json).flush();
		System.out.println(json);
	}
	
	public void GoPartsPlanGandT(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String data = ChineseCode.toUTF8(request.getParameter("data")).replaceAll("\"", "");
		request.getSession().setAttribute("DiscardWillSeeParts",data);
		System.out.println(data);
	}
	
	public void GoSeePlanDetail(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String data = ChineseCode.toUTF8(request.getParameter("data")).replaceAll("\"", "");
		request.getSession().setAttribute("DiscardWillSeeParts",data);
		System.out.println(data);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void SeePlanDetailServlet(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String data = session.getAttribute("DiscardWillSeeParts").toString().replaceAll("\"", "");
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<Product> products = JSONArray.toList(jsonArray, Product.class);
		
		List<Product> resultProducts = new ArrayList<Product>();
		for(Product product : products){
			Product resultProduct = new Product();
//			String sql = "select A.order_id orderId,A.product_id productId,A.issue_num issueNum,A.drawingId,E.rejectNum productNum," +
//					"A.B_time orderDate,A.E_Time eTime,A.product_name productName,A.spec," +
//					"A.isWaiXie,A.isGongYi,A.isCaiGou,B.partNum partPlanNum,(B.planEtime-B.planBTime)*24*60 planDuration," +
//					"B.partNum*(B.planEtime-B.planBTime)*24*60 totalDuration,B.partStatus productStatus,C.statusName productStatusName,D.endTime " +
//					"from order_detail A " +
//					"left join orders D on A.order_Id=D.order_Id " +
//					"left join partsPlan B on A.order_id=B.orderId and A.product_id=B.productId and A.issue_num=B.issueNum and A.drawingId=B.drawingId "+
//					"left join productStatus C on B.partStatus=C.statusId "+
//					"left join todiscard E on E.order_Id=A.order_Id and E.product_Id=A.product_Id and E.issue_Num=A.issue_Num and E.drawingId=A.drawingId and E.barCode=B.isdiscard  "+
//					"where A.order_id=? and A.product_id=? and A.issue_num=? and A.drawingId=? and E.barCode=?";
			String sql = "select A.order_id orderId,A.product_id productId,A.issue_num issueNum,A.drawingId,E.rejectNum productNum," +
					"A.B_time orderDate,A.E_Time eTime,A.product_name productName,A.spec," +
					"A.isWaiXie,A.isGongYi,A.isCaiGou,(B.planEtime-B.planBTime)*24*60 planDuration," +
					"B.partNum*(B.planEtime-B.planBTime)*24*60 totalDuration,B.partStatus productStatus,C.statusName productStatusName,D.endTime " +
					"from order_detail A " +
					"left join orders D on A.order_Id=D.order_Id " +
					"left join partsPlan B on A.order_id=B.orderId and A.product_id=B.productId and A.issue_num=B.issueNum and A.drawingId=B.drawingId "+
					"left join productStatus C on B.partStatus=C.statusId "+
					"left join todiscard E on E.order_Id=A.order_Id and E.product_Id=A.product_Id and E.issue_Num=A.issue_Num and E.drawingId=A.drawingId  "+
					"where A.order_id=? and A.product_id=? and A.issue_num=? and A.drawingId=? and E.barCode=?";
			String[] params = {product.getOrderId(),product.getProductId(),product.getIssueNum(),product.getDrawingId(),product.getBarCode()};
			
			try {
				resultProduct = Sqlhelper.exeQueryBean(sql, params, Product.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resultProducts.add(resultProduct);
		}
		
		String json = PluSoft.Utils.JSON.Encode(resultProducts);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	}
	
	public void SeeSubmitPlanServlet(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String sql = "select A.orderId,A.productId,A.issueNum,A.drawingId,A.partNum partPlanNum," +
				"A.planBTime Partbegintime,A.planETime Partendtime,A.partStatus productStatus,B.product_name productName,B.E_Time eTime," +
				"B.purduct_num productNum,C.statusName productStatusName " +
				"from partsPlan A " +
				"left join order_detail B on A.orderId=B.order_Id and A.productId=B.product_id and A.issueNum=B.issue_num and A.drawingId=B.drawingId "+
				"left join productStatus C on A.partStatus=C.statusId "+
				"where partStatus>=? and partStatus<=? ";
		String[] params = {ProductStatus.PARTPLANSBUMIT+"",ProductStatus.PARTPLANPASS+""};
		List<Product> products = new ArrayList<Product>();
		try {
			products = Sqlhelper.exeQueryList(sql, params, Product.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String json = PluSoft.Utils.JSON.Encode(products);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void PartsPlanSubmit(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String data = ChineseCode.toUTF8(request.getParameter("data")).replaceAll("\"", "");
		System.out.println(data);
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<Product> products = JSONArray.toList(jsonArray, Product.class);
		
		for(Product product : products){
			String sql = "update partsplan set partStatus=? " +
					"where orderId=? and productId=? and issueNum=? and drawingId=? ";
			String[] params = {ProductStatus.PARTPLANSBUMIT+"",product.getOrderId(),product.getProductId(),product.getIssueNum(),product.getDrawingId()};
			
			int count = 0;
			try {
				count = Sqlhelper.executeUpdate(sql, params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (count==products.size()) {
				String json = "{\"result\":\"操作成功!\"}";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
			}else {
				String json = "{\"result\":\"操作失败!\"}";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void PartsPlanPass(HttpServletRequest request, HttpServletResponse response,String pathTo)
	throws ServletException, IOException {
		String data = ChineseCode.toUTF8(request.getParameter("data")).replaceAll("\"", "");
		System.out.println(data);
		JSONArray jsonArray = JSONArray.fromObject(data);
		List<Product> products = JSONArray.toList(jsonArray, Product.class);
		
		for(Product product : products){
			String sql = "update partsplan set partStatus=? " +
					"where orderId=? and productId=? and issueNum=? and drawingId=? ";
			String[] params = {product.getProductStatus()+"",product.getOrderId(),product.getProductId(),product.getIssueNum(),product.getDrawingId()};
			
			int count = 0;
			try {
				count = Sqlhelper.executeUpdate(sql, params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (count==products.size()) {
				String json = "{\"result\":\"操作成功!\"}";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
			}else {
				String json = "{\"result\":\"操作失败!\"}";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
