package com.wl.testaction.orderManage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cfmes.util.sql_data;

import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.wl.common.ProductStatus;
import com.wl.forms.FoDetail;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.testaction.craftworkManage.IsRepeatProduct;
import com.wl.testaction.partPlanManage.ActionServlet;
import com.wl.testaction.partPlanManage.BaseServlet;
import com.wl.testaction.utils.UploadUtils;
import com.wl.tools.ChineseCode;
import com.wl.tools.Mapping;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;
import com.wl.tools.UUIDHexGenerator;
import com.xm.testaction.qualitycheck.ToBarcode;
import com.xm.testaction.qualitycheck.sum.AddNewStuff;

public class AddShiyanOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 3495646319327543417L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
/*	******************************************订单基本信息插入	*********************************************************/
		response.setCharacterEncoding("UTF-8");
		Map<String, String> requestValueMap = new HashMap<String, String>();	//用来封装request请求中的参数
		
		HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    Date d = new Date();    
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");  
        String time = sdf.format(d);  
        
        try {
			requestValueMap = UploadUtils.UploadLoadFile(request, response, createPerson, requestValueMap);	//保存文件+得到request属性值
		} catch (FileUploadException e1) {
			String json = "{\"result\":\"文件太大，请检查文件上大小！！\"}";
			response.getWriter().append(json).flush();
			e1.printStackTrace();
		}

		String orderId = ChineseCode.toUTF8(requestValueMap.get("orderId").trim());
		String customer = ChineseCode.toUTF8(requestValueMap.get("customer").trim());
	    String deptUser =requestValueMap.get("deptUser").trim();
	    String endTime = ChineseCode.toUTF8(requestValueMap.get("endTime").trim());
	    String orderDate = ChineseCode.toUTF8(requestValueMap.get("orderDate").trim())+" "+time;
	    String orderStatus = ChineseCode.toUTF8(requestValueMap.get("orderStatus").trim());
	    String memo = "null";
	    String connector = requestValueMap.get("connector").trim();
	    //System.out.println(connector);
	    String connectorTel = ChineseCode.toUTF8(requestValueMap.get("connectorTel").trim());
	    String orderPaper ="null" ;
	    String duizhanghan = "null";
	    String otherPaper = "null";
	   //获取并且拼接设备的预约时间
	    //String machineTime01 = ChineseCode.toUTF8(request.getParameter("machineTime01").trim())+ChineseCode.toUTF8(request.getParameter("machineTime0101").trim());
	    //String equipCode = ChineseCode.toUTF8(request.getParameter("equipCode").trim());
	    //System.out.print(machineTime01);
	    //System.out.print(equipCode);
	  
	    String equipCode = ChineseCode.toUTF8(requestValueMap.get("equipCode").trim());
	   // String machineName = requestValueMap.get("machineName").trim();
	    //String machineTime01 =ChineseCode.toUTF8(requestValueMap.get("machineTime01").trim())+ requestValueMap.get("machineTime0101").trim();
	    /*System.out.print(equipCode);
	    System.out.print(machineTime01);*/
	    
	    String bookStatus="11";
		String  addOrderSql1 = "insert into orders " +
				"(ORDER_ID,DEPT_USER,MEMO,endTime,ORDER_DATE," +
				"ORDER_STATUS,CUSTOMER,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME,connector,connectorTel," +
				"orderPaper,duizhanghan,otherPaper,book_status) values ('"+
				orderId+"','"+deptUser+"','"+memo+"',to_date('"+endTime+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+orderDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+
				orderStatus+"','"+customer+"','"+
				createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"
				+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+connector+"','"+connectorTel+"'," +
				"'"+orderPaper+"','"+duizhanghan+"','"+otherPaper+"','"+bookStatus+"')";
		
		
/*******************************************产品详细信息插入！！！！*********************************************/

		
	   
	    String FProductId = "";
	    if(StringUtil.isNullOrEmpty(requestValueMap.get("FProductId"))){	//空
	    	FProductId = orderId;
	    }else {
	    	FProductId = ChineseCode.toUTF8(requestValueMap.get("FProductId").trim());
		}
	    String spec = "null";
	    String drawingId="";
	    
	    String issueNum = "1";
	    String deptNo = deptUser;
	    String itemTypeId = ChineseCode.toUTF8(requestValueMap.get("itemTypeId").trim());
	    String productNum = ChineseCode.toUTF8(requestValueMap.get("productNum").trim());
	    
	    String unitPrice = "0";
	    String islailiao = "0";
	    String BTime = createTime;
	    String ETime = endTime;
	    String lot = "null";
	    String isWaiXie=ChineseCode.toUTF8(requestValueMap.get("iswaixie").trim());

	    
	 
	    String paper = "null";
	
	    int willpaynum = (int)((Integer.parseInt(productNum))*1.05+1);
	    
	    request.setCharacterEncoding("utf-8");
	    String productName = requestValueMap.get("productName").trim();
	    String productId = orderId.substring(6);
	    
	    String material=requestValueMap.get("material").trim();
	    
	    if(StringUtil.isNullOrEmpty(requestValueMap.get("drawingId"))){
	    	drawingId=productId;
	    }else{
	    	drawingId=ChineseCode.toUTF8(requestValueMap.get("drawingId"));
	    }
	    
//	   xiem  新材料维护
	    try {
			AddNewStuff.addNewStuff(material);
		} catch (Exception e) {
		}
		response.setCharacterEncoding("UTF-8");
//不在采用这种简单随便的编号了，我准备用订单号的后面进行编号
		/*//		产品号里串上客户编号，xiem，防止简单编号重复
		String customerId = StringUtil.isNullOrEmpty(requestValueMap.get("customerId"))?"":requestValueMap.get("customerId").trim();
		if(customerId.isEmpty()){
//			如果前台的客户编号为空，则从订单号里摘取
			String temp1 [] = orderId.split("-");
			String temp2 = temp1[2];
			customerId =temp2;
		}
		productId= customerId+"-"+productId;*/
		
		int productStatus = 16;//这个代表直接通过工艺审核!!!!!
		String  addOrderSql2 = "insert into order_detail " +
				"(ORDER_ID,PRODUCT_ID,PRODUCT_NAME,DRAWINGID,PURDUCT_NUM," +
	            "B_TIME,E_TIME,DEPT_ID," +
				"fproduct_id,SPEC,MEMO,createTime," +
				"changeTime,createPerson,changePerson,unitPrice,issue_Num,cengci," +
				"paper,otherPaper,isjiaohuo,PRODUCTTYPE,willpaynum,lot,islailiao,iswaixie,material,product_status) values ('"+
				orderId+"','"+productId+"','"+productName+"','"+drawingId+"','"+productNum+"',"+
				"to_date('"+BTime+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+ETime+"','yyyy-mm-dd,hh24:mi:ss'),'" +deptNo+"','"+
	            FProductId+"','"+spec+"','"+memo+"'" +
				",to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+
				createPerson+"','"+changePerson+"','"+unitPrice+"','"+issueNum+"','2'," +
				"'"+paper+"','"+otherPaper+"','"+islailiao+"','"+itemTypeId+"','"+willpaynum+"','"+lot+"','"+islailiao+"','"+isWaiXie+"','"+material+"','"+productStatus+"')";

		String addOrderItemSql = "insert into item " +
	            "(ITEM_TYPEID,ITEM_ID,ITEM_NAME,LOT_SIZE,ITEM_DRAWING," +
				"ITEM_SPECIFICATION,ITEM_PRICE,EXTRA_A,FITEM_ID,MEMO," +
				"CREATE_TIME,UPDATE_TIME,CREATE_PERSON,UPDATE_PERSON,EXTRA_B) " +
	            "values ('"+itemTypeId+"','"+productId+"','"+productName+"','"+productNum+"','"+drawingId+"'," +
						"'"+spec+"','"+unitPrice+"','"+issueNum+"','"+FProductId+"','"+memo+"'," +
						"to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+createPerson+"','"+changePerson+"','2')";
		
		
/***************************************插入工艺信息基本的foheader**********************************************************/
//插入工艺信息
		String foId = "0";//工艺编号，由于没有做工艺，只是将一些基本的信息传递进去，所以设为0
		
		
		String productType = itemTypeId;
		String fproductId = orderId;

		String supplyStatus = "";
		String techNeed = "";
		String techSpec = "";
		String roughSize = "";

	    //String UUID = "";
	    int count = 1;
	    
		String foHeadersql = "insert into foHeader " +
                "(foId,orderId,productId,productName,issueNum,drawingId," +
			    "productType,fproductId,productNum,spec,matirial," +
				"supplyStatus,techNeed,techSpec,roughSize,memo," +
				"createPerson,createTime,changePerson,changeTime,processissue_num)" +
				"values" +
				"('"+foId+"','"+orderId+"','"+productId+"','"+productName+"','"+issueNum+"','"+drawingId+"'," +
				"'"+productType+"','"+fproductId+"','"+productNum+"','"+spec+"','"+material+"'," +
				"'"+supplyStatus+"','"+techNeed+"','"+techSpec+"','"+roughSize+"','"+memo+"'," +
				"'"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+count+"')";
/******************************************c插入工序默认的信息，主要是有关设备的信息****************************************************/
		response.setCharacterEncoding("UTF-8");
		//以下信息都已经存在！！直接使用默认的信息即可
		//Map<String, String> requestValueMap = new HashMap<String, String>();	//用来封装request请求中的参数
		//HttpSession session = request.getSession();
	    //String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    //String changePerson = ((User)session.getAttribute("user")).getStaffCode();

	    //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    //String createTime = df.format(new Date());
	    //String changeTime = df.format(new Date());
		
		String fono= 0+"";//工序编号
		String foIdId=productId+fono;//这个是表示工序唯一的id，对应Fo_id
		String FoOpName = "默认";
		String processissuenum="1";//默认都是1
		String OperTime = "0";
		String RatedTime="0";
		String PlanTime="0";
		String OperAidTime="0";
		String FoOpcontent="默认";//工序内容
		String InspTime ="0";
		String WCID = "1";//morenshi 1
		String  EquipType="";//!!
		String EquipCode=equipCode;//!!!
		String IsKey = "0";//默认非关键0
		String IsInsp="1";//moren shi  1检查
		String ArmInsp="0";//默认
		String fIsCerTop = "1";//moren
		String IsCo ="0";
		String ProductType =productType;
		String ProductId =productId;
		String IssueNum =issueNum;
		String WorkBranchId  ="默认";//工种默认
		String CraftPaper ="";//文件
		//foid非空！！！！必须插入！
		
		String fo_detailsql = "insert into fo_detail " +
				"(FO_ID,foId,FO_NO,FO_OPNAME,OPER_TIME,RATED_TIME,PLAN_TIME," +
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

			
		
		String[] params = {foIdId,foId,fono,FoOpName,OperTime,RatedTime,PlanTime,
				OperAidTime,FoOpcontent,InspTime,WCID,EquipType,
				EquipCode,IsKey,IsInsp,ArmInsp,fIsCerTop,
				IsCo,ProductType,ProductId,IssueNum,WorkBranchId,
				createPerson,createTime,changePerson,changeTime,processissuenum,CraftPaper};
		
		
/*******************************************************一下是插入设备时间的详细信息***************************************************************/		
		String MachineInfoTime_Sql = "insert into machineinfo_time " +
				"(orderId,fo_id,machineId,machineName,deptId,connector,machineTime,MACHINEID_MACHINETIME)" +
				"values" +
				"(?,?,?,?," +
				"?,?," +
				"?,?)";
		//String[] params2 = {orderId,foIdId,equipCode,machineName,deptUser,connector,machineTime01,equipCode+machineTime01};
		
		
/******************************************以下是所有sql语句的操作！！！****************************************************/
		sql_data sqlData = new sql_data();
		System.out.println("addOrderSql1=="+addOrderSql1);
		System.out.println("addOrderSql=="+addOrderSql2);
		System.out.println("addOrderItemSql=="+addOrderItemSql);
		System.out.println("foHeadersql="+foHeadersql);
		System.out.println("fo_detailsql="+fo_detailsql);
		
		String result = "操作成功";
		String json = "";
		try {
			sqlData.exeUpdateThrowExcep(addOrderSql1);
			
		} catch (SQLException e) {
			result = "操作失败";
			e.printStackTrace();
			
		}
		try {
			sqlData.exeUpdateThrowExcep(addOrderSql2);
			
		} catch (SQLException e) {
			result = "操作失败";
			e.printStackTrace();
		}
		try {
			sqlData.exeUpdateThrowExcep(addOrderItemSql);
		} catch (Exception e) {
			result = "操作失败";
			e.printStackTrace();
		}
		try {
			
			Sqlhelper.executeUpdate(foHeadersql, null);
			
		} catch (Exception e) {
			result = "操作失败";
			e.printStackTrace();
		}
		try {
			Sqlhelper.executeUpdate(fo_detailsql, params);
			
		} catch (Exception e) {
			
			result = "操作失败";
			e.printStackTrace();
		}
		/*try {
			Sqlhelper.executeUpdate(MachineInfoTime_Sql, params2);
			
		} catch (Exception e) {
			
			result = "时间冲突，重新核实设备的预约时间！";
			e.printStackTrace();
		}*/
/*****************************************************制作零件计划*************************************************/
		// "{'orderId':'"+orderId+"','productId':'"+productId+"','issueNum':'"+issueNum+"','productName':'"+productName+"','sortie':'"+sortie+"','drawingId':'"+drawingId+"'}";
		request.setAttribute("orderId", orderId);
		request.setAttribute("productId", productId);
		request.setAttribute("issueNum", issueNum);
		request.setAttribute("productName", productName);
		request.setAttribute("sortie", "1,0");
		request.setAttribute("drawingId", productId);
		Order order= new Order();
		order.setOrderId(orderId);
		order.setProductId(ProductId);
		order.setIssueNum(IssueNum);
		
		List <Order> orders = new ArrayList<Order>() ;
		orders.add(order);
		int num=1;
		
		try {
			num =addPartsPlanUtil(request,orders);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(num==0) {
			
		}else {
			result = "操作失败";
		}
			
/****************************************************************************************************/
		json = "{\"result\":\""+result+"!\"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		
	}


	
	
//	^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^制作零件计划的具体函数^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	@SuppressWarnings("unchecked")
	private int addPartsPlanUtil(HttpServletRequest request,List<Order> orders) throws Exception{
		HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
//	    批量定制零件计划
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    
	    for(Order order: orders){
	    	
	    	String orderSql = "select order_Id orderId,product_id productId,issue_num issueNum,drawingId,purduct_num productNum," +
					"B_time btime,E_time etime, product_status productStatus,product_name productName, iswaixie," +
					"isgongyi,iscaigou,productType " +
					"from order_detail " +
					"where order_Id=? and product_id=? and issue_num=?";
			String[] orderParams = {order.getOrderId(),order.getProductId(),order.getIssueNum()};
			
			Order selectedOrder = null;
			selectedOrder = Sqlhelper.exeQueryBean(orderSql, orderParams, Order.class);
			////////////////////////////查出零件相关信息//////////////////////////////////
			
			
			
			String getProSql = "select A.foId,A.orderId,A.productId,A.productName,A.issueNum," +
					"A.drawingId,A.productType,A.productNum," +
					"B.FO_NO,B.FO_OPNAME,B.OPER_TIME,B.EQUIPCODE,B.IS_CO isco " +
					"from foHeader A  " +
					"left join fo_detail B on A.foId=B.foId and A.productId=B.product_id  " +
					"where A.orderId=? and A.productId=? and A.issueNum=?  and B.isinuse='1' ";
			String[] getProParams = {order.getOrderId(),order.getProductId(),order.getIssueNum()};
			
			List<FoDetail> foDetails = new ArrayList<FoDetail>();
			foDetails = Sqlhelper.exeQueryList(getProSql, getProParams, FoDetail.class);
			//////////////////以上为查出零件对应的所有工序/////////////////
	    	
	    	
	    	/**************************************零件计划*****************************************/
			int jiangeTime = 60;//工序间时间间隔，以分钟为单位
			
			
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
					"createPerson,createTime,changePerson,changeTime) " +
					"values" +
					"(?,?,?,?,?," +
					"to_date(?,'yyyy-mm-dd,hh24:mi:ss')-numtodsinterval("+partPlanAheadTime+",'minute')," +
					"to_date(?,'yyyy-mm-dd,hh24:mi:ss')-numtodsinterval("+jiaohuotiqianqi+",'minute')," +
					"?,?,?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'),?,"+
					"?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'),?,to_date(?,'yyyy-mm-dd,hh24:mi:ss')) ";
			String[] params = {UUID,selectedOrder.getProductId(),selectedOrder.getOrderId(),selectedOrder.getProductNum()+"",ProductStatus.PARTPLANING+"",
					selectedOrder.geteTime(),
					selectedOrder.geteTime(),
					selectedOrder.getProductType(),selectedOrder.getIssueNum(),createPerson,createTime,selectedOrder.getDrawingId(),
					createPerson,createTime,createPerson,createTime};
			
			String ProductStatusSql = "update order_detail set product_status=? " +
					"where order_id=? and product_id=? and issue_num=? and drawingId=? ";
			String[] params2 = {ProductStatus.PARTPLANING+"",selectedOrder.getOrderId(),selectedOrder.getProductId(),selectedOrder.getIssueNum(),selectedOrder.getDrawingId()};
		
			Sqlhelper.executeUpdate(sql, params);
			Sqlhelper.executeUpdate(ProductStatusSql, params2);
//			9-5 xiexiaoming 生成条形码 
			String barcode ="";
			System.out.println();
	    	try {
				barcode = ToBarcode.toBarcode(UUID);
			} catch (Exception e) {
				// TODO: handle exception
			}
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
						"createPerson,createTime,changePerson,changeTime,machineId,isco)" +
						"values " +
						"(?,?,?,?,?," +
						"?,?,to_date(?,'yyyy-mm-dd,hh24:mi:ss')-numtodsinterval("+end+",'minute'),to_date(?,'yyyy-mm-dd,hh24:mi:ss')-numtodsinterval("+head+",'minute'),?," +
						"?,to_date(?,'yyyy-mm-dd,hh24:mi:ss')," +
						"?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'),?,to_date(?,'yyyy-mm-dd,hh24:mi:ss'),?,?)";
				
				String[] ProPlanParams2 = {uuid,foDetail.getOrderId(),foDetail.getProductId(),foDetail.getIssueNum(),foDetail.getDrawingId(),
						foDetail.getFoNo(),foDetail.getProductNum()+"",createTime,createTime,"1",
						createPerson,createTime,
						createPerson,createTime,createPerson,createTime,foDetail.getEquipCode(),foDetail.getIsCo()};
				try {
					count += Sqlhelper.executeUpdate(ProPlanSql, ProPlanParams2);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				end = head+jiangeTime;
			}
			
			String ProductStatusProSql = "update order_detail set product_status=? " +
					"where order_id=? and product_id=? and issue_num=? and drawingId=? ";
			String[] ProductStatusProParams2 = {ProductStatus.PROPLANING+"",order.getOrderId(),order.getProductId(),order.getIssueNum(),order.getDrawingId()};

			Sqlhelper.executeUpdate(ProductStatusProSql, ProductStatusProParams2);
			
			
		/**************************************工序计划****************************************/
	    }
		return 0;
	}
}

