package service.serviceImpl;

import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ForbiddenException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.wl.forms.Customer;
import com.wl.forms.Machine;
import com.wl.forms.Order;
import com.wl.forms.OrdersMachinesAudits;
import com.wl.forms.User;
import com.wl.testaction.utils.DaiUtils;
import com.wl.tools.DataBaseTool;
import com.wl.tools.Sqlhelper;

import javaBean.BookOrderMachine;
import javaBean.machBookDatelines;
import javaBean.machineInfo5505;
import javaBean.machineInfoSiemens;
import machineOrderYuyue.beans.bookingInfoBean;
import machineOrderYuyue.beans.contentBean;
import machineOrderYuyue.beans.dateContent;
import machineOrderYuyue.beans.jsonBean;
import machineOrderYuyue.beans.loadJson;
import machineOrderYuyue.beans.machineBean;
import machineOrderYuyue.beans.timeLinesBean;
import mapper.BookOrderMapper;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import service.OrderService;


/*  
*    
* 项目名称：SSM   
* 类名称：OrderServiceImpl   
* 类描述：   
* 创建人：dell   
* 创建时间：2018年12月27日 下午12:54:45   
* @version        
*/

/*  
*    
* 项目名称：SSM   
* 类名称：OrderServiceImpl   
* 类描述：   
* 创建人：dell   
* 创建时间：2018年12月27日 下午12:54:48   
* @version        
*/
@Service
public class OrderServiceImpl implements OrderService  {

	@Autowired
	HttpServletRequest request;
	@Resource
	BookOrderMapper bookOrdeMapper;
	
/*	@Override
	public String (String machineId5501, String machineId5502, String machineId5503,
			String machineId5513, String machineId5505) {
		machineInfoSiemens  machineInfoSiemens1 =userMapper.selectTotalMachineInfo5501ById(machineId5501);
		machineInfoSiemens  machineInfoSiemens2 =userMapper.selectTotalMachineInfo5502ById(machineId5502);
		machineInfoSiemens  machineInfoSiemens3 =userMapper.selectTotalMachineInfo5503ById(machineId5503);
		machineInfoSiemens  machineInfoSiemens4 =userMapper.selectTotalMachineInfo5513ById(machineId5513);
		machineInfo5505      machineInfo5505    =userMapper.selectTotalMachineInfo5505ById(machineId5505);
		
		@SuppressWarnings("rawtypes")
		List list =new ArrayList();
		
		list.add(machineInfoSiemens1);
		list.add(machineInfoSiemens2);
		list.add(machineInfoSiemens3);
		list.add(machineInfoSiemens4);
		list.add(machineInfo5505);
		
		String json = PluSoft.Utils.JSON.Encode(list);
	
		return json;
	
	}*/


	public String returnMybookMachine(String orderId, int pageNO, int pageSize, String sortField, String sortOrder) {
	/*	int one =countPerPage*pageNO;
		int two =(pageNO-1)*countPerPage+1;*/
//		使用分页及其自动排序进行查询
		PageHelper.startPage(pageNO,pageSize,DaiUtils.jspNameToOralceName(sortField.equals("")?"unid":sortField)+" "+DaiUtils.jspNameToOralceName(sortOrder));
		List<BookOrderMachine> list = bookOrdeMapper.selectBookOrderMachineListByOrderId(orderId);
		String json = DaiUtils.returnMiniUiJson(list);
		return json;
	}
	public String returnMybookMachine1(String orderId, int pageNO, int pageSize, String sortField, String sortOrder) {
		/*	int one =countPerPage*pageNO;
		int two =(pageNO-1)*countPerPage+1;*/
		
		HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
		String staffCode =  ((User)session.getAttribute("user")).getStaffCode();
//		使用分页及其自动排序进行查询
		PageHelper.startPage(pageNO,pageSize,DaiUtils.jspNameToOralceName(sortField.equals("")?"unid":sortField)+" "+DaiUtils.jspNameToOralceName(sortOrder));
		List<BookOrderMachine> list = bookOrdeMapper.selectBookOrderMachineListByOrderId1(orderId,staffCode);
		String json = DaiUtils.returnMiniUiJson(list);
		return json;
	}

	

	public String returnMyBookOrder(String orderId,String staffCode,int pageNo, int pageSize,String sortField,String sortOrder, String bookStatus, String companyId, String companyName) {
//			int one =countPerPage*pageNo;
//			int two =(pageNo-1)*countPerPage+1;
			 sortField=sortField.equals("")?"orderId":sortField;
			 sortOrder=sortOrder.equals("")?"desc":sortOrder;
			 String orderBy=DaiUtils.jspNameToOralceName(sortField)+" "+DaiUtils.jspNameToOralceName(sortOrder);
			 PageHelper.startPage(pageNo,pageSize,orderBy);
			 List<Order> list = bookOrdeMapper.selectBookOrderList(orderId,staffCode,bookStatus,companyId,companyName);
//			 int count = bookOrdeMapper.selectBookOrderCount(staffCode);
			
			 String json = DaiUtils.returnMiniUiJson(list);
			
//			String jsonData = PluSoft.Utils.JSON.Encode(list);
//			String json = "{\"total\":"+count+",\"data\":"+jsonData+"}";
			return json;
		
	
	}
	
	public String returnMyBookOrder1(String staffCode,int pageNo, int pageSize,String sortField,String sortOrder, String bookStatus) {

		sortField=sortField.equals("")?"orderId":sortField;
		sortOrder=sortOrder.equals("")?"desc":sortOrder;
		
		String orderBy=DaiUtils.jspNameToOralceName(sortField)+" "+DaiUtils.jspNameToOralceName(sortOrder);
		
		PageHelper.startPage(pageNo,pageSize,orderBy);
		
		List<Order> list = bookOrdeMapper.selectBookOrderList1(staffCode,bookStatus);
//			 int count = bookOrdeMapper.selectBookOrderCount(staffCode);
		
		String json = DaiUtils.returnMiniUiJson(list);
		
//			String jsonData = PluSoft.Utils.JSON.Encode(list);
//			String json = "{\"total\":"+count+",\"data\":"+jsonData+"}";
		return json;
		
		
	}
	
	public String returnMyBookOrderToo(String orderId,String staffCode,int pageNo, int pageSize,String sortField,String sortOrder, String bookStatus, String companyId, String companyName) {
//			int one =countPerPage*pageNo;
//			int two =(pageNo-1)*countPerPage+1;
		sortField=sortField.equals("")?"orderId":sortField;
		sortOrder=sortOrder.equals("")?"desc":sortOrder;
		String orderBy=DaiUtils.jspNameToOralceName(sortField)+" "+DaiUtils.jspNameToOralceName(sortOrder);
		PageHelper.startPage(pageNo,pageSize,orderBy);
		List<Order> list = bookOrdeMapper.selectBookOrderListToo(orderId,staffCode,bookStatus,companyId,companyName);
//			 int count = bookOrdeMapper.selectBookOrderCount(staffCode);
		
		String json = DaiUtils.returnMiniUiJson(list);
		
//			String jsonData = PluSoft.Utils.JSON.Encode(list);
//			String json = "{\"total\":"+count+",\"data\":"+jsonData+"}";
		return json;
		
		
	}
	
	public String updateAudutingBookStatus(String orderId,String bookStatus,String staffCode,String checkAdvice) {
	
		
		if(bookStatus.equals("14")) {
			//此处首先把删除的bookInfo插寻出来然后放到另外的一个表中!
			//其次在把删除的设备的已经审核的意见置为无效,用于给客户进行反馈!同时为了保证有效的不重复,然后加载的时候不冲突!!!
			//还有要修改的是地方,就是之前写好的关于每次修改都是插入到哪个ordersMachinesAudits的表中!
			int countNopass=bookOrdeMapper.noPassDeleteAuditingBookOrderMapper(orderId);
			System.out.println(countNopass);
		}else if(bookStatus.equals("13")){
//			表示通过的时候的逻辑!
//			逻辑如下:首先把插入一条新的通过的状态,直接把前端的表单中的全部信息都直接获取到,然后进行分析,把去全部的信息获取到,然后插入到数据库中,删除之前的里已经有的,在重新加载!!!
			
			
			
			
		}
		
//		int count = bookOrdeMapper.updateAuditingBookStatus(bookStatus,staffCode,checkAdvice,orderId);
//		在这个地方调用一个函数，该函数用于判断当前是否是所有的设备的相应的管理员都已经审核结束 ，也就是可以通过当前的提交审核人的信息，。查询到它管理的设备在当前订单的所有的设备中占几个？，然后维护一个订单总共有几个设备？
//		然后进行对比，直至相等，就将当前的状态置为下一个！但是有个12.5的状态，代表审核中！
		String result;
		try {
			result = DataBaseTool.updateOrderMachinesAudits(bookStatus, staffCode, checkAdvice, orderId, bookOrdeMapper);
		} catch (Exception e) {
			e.printStackTrace();
			result="操作失败";
		}
		
		
		String json = "{\"result\":"+"\""+result+"\"}";
		return json;
	

	}
	@Transactional
	public String deleteAuditingBookOrder(String unid, String orderId) throws Exception {
	
		
		int count = bookOrdeMapper.deleteAuditingBookOrderMapper(unid);
		
		String jsonData="";
		
		if (count>=1) {
			jsonData="操作成功";
			
		}else {
			jsonData="操作失败";
		}
		Boolean isNewDate = DataBaseTool.selectNewDate(orderId);
		if(!isNewDate) jsonData="操作失败";
		String json = "{\"result\":"+"\""+jsonData+"\"}";
		return json;
	
	}
	
//	
	
		public String deleteAuditingOrder(String orderId) {
		
			
			int count1 = bookOrdeMapper.deleteAuditingOrderMapper(orderId);
			int count2 = bookOrdeMapper.deleteAuditingOrderbookingInfoMapper(orderId);
			String jsonData="";
			
			if (count1>=1) {
				jsonData="操作成功";
				
			}else {
				jsonData="操作失败";
			}
			String json = "{\"result\":"+"\""+jsonData+"\"}";
			return json;
		
		}
	

		/**
		 * @param orderId
		 * @return
		 */
		public String AuditingBookOrderUpdateStatus(String orderId) {
			Order order = bookOrdeMapper.AuditingBookUpdateStatusMapper(orderId);
			
			String json = PluSoft.Utils.JSON.Encode(order);
//			String json = "{\"result\":"+"\""+jsonData+"\"}";
			return json;
		}
			
			/**
			 * @param orderId
			 * @return
			 */
			public String AuditingBookOrderUpdateStatus1(String orderId) {
				List<OrdersMachinesAudits>  list= bookOrdeMapper.AuditingBookUpdateStatusMapper1(orderId);
			
				ArrayList<HashMap<String,String>> arrayList = new ArrayList<HashMap<String,String>>();
				HashMap<String,String> hashMap = new HashMap<String,String>();
				for(OrdersMachinesAudits a:list) {
					
//					hashMap.put("machineName"+a.getMachineId(), a.getMachineName());
					hashMap.put("auditPerson"+a.getMachineId(), a.getStaffName());
					hashMap.put("isPass"+a.getMachineId(), a.getYesNo());
					hashMap.put("checkAdvice"+a.getMachineId(), a.getAdvice());
					arrayList.add(hashMap);
				}
				String json = PluSoft.Utils.JSON.Encode(hashMap);	
				
				return  json/*"{\"result\":"+"\""+json+"\"}"*/;
		}
			
			
			/**
			 * @param orderId
			 * @return
			 */
			public String updateAuditsService(String orderId) {
				
				
				HttpSession session = request.getSession();
				String userId = ((User)session.getAttribute("user")).getUserId();
				String staffCode =  ((User)session.getAttribute("user")).getStaffCode();
				
				
				
				List<Machine>  list= bookOrdeMapper.updateAuditsMapper(orderId,staffCode);
			
				
				String result = "";
					result+="<fieldset style=\\\"width: 100%;\\\" align=\\\"center\\\">";
					result+=" <legend>审核意见</legend>";
					result+="  <table  style=\\\"text-align: right;border-collapse:collapse;\\\" border=\\\"gray 1px solid;\\\"  width=\\\"100%\\\" >";
				
					
				for(Machine a:list) {
					result+=" <tr>";
					result+=" <td width=\\\"10%\\\" class=\\\"labelTd\\\"><label for=\\\"orderId$text\\\">设备名称</label></td>";
					result+=" <td width=\\\"10%\\\" ><input id=\\\"machineId"+a.getMachineId()+"\\\"  name=\\\"machineName\\\" class=\\\"machineName\\\"  width=\\\"100%\\\"   value=\\\""+a.getMachineName()+"\\\" enabled=\\\"false\\\" borderStyle=\\\"border:0\\\"/></td>";
					result+=" <td width=\\\"10%\\\" class=\\\"labelTd\\\"><label for=\\\"orderId$text\\\">审核人</label></td>";
					result+=" <td width=\\\"10%\\\" ><input id=\\\"auditPerson"+a.getMachineId()+"\\\"  name=\\\"auditPerson"+a.getMachineId()+"\\\" class=\\\"mini-textbox\\\"  width=\\\"100%\\\"   value=\\\"\\\" enabled=\\\"false\\\" borderStyle=\\\"border:0\\\"/></td>";
					result+=" <td width=\\\"10%\\\" class=\\\"labelTd\\\"><label for=\\\"IsOrNo"+a.getMachineId()+"$text\\\">是否通过</label></td>";
					result+=" <td width=\\\"10%\\\" ><input id=\\\"isPass"+a.getMachineId()+"\\\"  name=\\\"isPass"+a.getMachineId()+"\\\" class=\\\"mini-textbox\\\"  width=\\\"100%\\\"   value=\\\"\\\" enabled=\\\"true\\\" borderStyle=\\\"border:0\\\"/></td>";
					result+=" <td class=\\\"labelTd\\\"><label for=\\\"checkAdvice"+a.getMachineId()+"$text\\\">审核进度</label></td>";
					result+=" <td colspan=\\\"2\\\"><input id=\\\"checkAdvice"+a.getMachineId()+"\\\" name=\\\"checkAdvice"+a.getMachineId()+"\\\" class=\\\"mini-textarea\\\" emptyText=\\\"请输入审核意见\\\" style=\\\"height:100%;width:100%\\\" borderStyle=\\\"border:0\\\"/></td>";
					result+=" </tr>";
				}
				result+="</table>";
				result+="</fieldset>";
				
				
				/*String json = PluSoft.Utils.JSON.Encode(list);*/	
				return  "{\"result\":"+"\""+result+"\"}";
			}

		/**
		 * @param orderId
		 * @param customer
		 * @param connector
		 * @param connectorTel
		 * @param productName
		 * @param productNum
		 * @param material
		 * @return
		 */


		
		public String addShiYanOrder(String orderId, String customer, String connector, String connectorTel,
				String orderName, String productNum, String material) {
			HttpSession session = request.getSession();
			String userId = ((User)session.getAttribute("user")).getUserId();
			String staffCode =  ((User)session.getAttribute("user")).getStaffCode();
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	         System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	         String createTime=df.format(new Date());
	         
			int count1 = bookOrdeMapper.insertAddShiYanOrder(orderId,customer,connector,connectorTel,orderName,productNum,material,createTime,staffCode,new String("11"));
			String jsonData="";
			if (count1>=1) {
				jsonData="操作成功";
				
			}else {
				jsonData="操作失败";
			}
			 jsonData = "{\"result\":"+"\""+jsonData+"\"}";
			return jsonData;
			
		}

		/**
		 * @param staffCode
		 * @return
		 */
		public String loadDefaultShiYanOrder(String staffCode) {
			Customer customer = bookOrdeMapper.loadDefaultShiYanOrder(staffCode);
			
			String json = PluSoft.Utils.JSON.Encode(customer);
//			String json = "{\"result\":"+"\""+jsonData+"\"}";
			return json;
		}

		/**
		 * @param machineId
		 * @param deptId
		 * @param eday 
		 * @param bday 
		 * @return
		 * 此方法对应返回按照机床预约的信息
		 */
		public String returnShowOrderByMachineService(String machineId, String deptId, String bday, String eday) {
			
			/* ClassPathResource classPathResource = new ClassPathResource("translate.json");
		     String str = IOUtils.toString(new InputStreamReader(classPathResource.getInputStream(),"UTF-8"));
		     map = JSONObject.parseObject(str, Map.class);
		     System.out.println(map);*/
			
//			声明一个总的消息bean，针对前端的额预约的界面，就类似miniui中的固定格式
			//此处是最外层的Bean的构造
			
			loadJson loadJson =new loadJson();
			
			loadJson.setMsg("操作成功");
			loadJson.setNo(2000);
			
			Calendar calendar2 = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			loadJson.setNow(df.format(calendar2.getTime()));
			
			//接下来开始contentBean的构造，这个contentBena也是一个定义内容的bean，不需要在数据库找中查找
			contentBean contentBean = new contentBean();
			
			contentBean.setDeptId(Integer.parseInt(deptId));
			contentBean.setDeptName(Integer.parseInt(deptId)>550 ? "实验室103":"实验室101");
//………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………
//			这个地方暂时不需要修改，但是可以做成动态加载的，也是在数据库进行获取，获取的的结果进行动态加载的，从而可以做到以后进行修改！使用xml的话就是一个步骤，而且刻印做到不同的车间，不同的机床预约的间隔可以调整！
			List<timeLinesBean> timeLinesList = new ArrayList<timeLinesBean>();
			
			
			for(int i = 0;i<14;i++){
				
				String srcStart = String.valueOf(i+8); 
				String srcEnd = String.valueOf(i+9); 
				String srcPattern = "HH"; 
				String outPattern = "HH:mm"; 
				String outStart = null;
				try {
					outStart = new SimpleDateFormat(outPattern).format(new SimpleDateFormat(srcPattern).parse(srcStart));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String outEnd = null;
				try {
					outEnd = new SimpleDateFormat(outPattern).format(new SimpleDateFormat(srcPattern).parse(srcEnd));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				timeLinesBean timeLineBean =new timeLinesBean(); 
				
				timeLineBean.setStartTime(outStart);
				timeLineBean.setEndTime(outEnd);
				
				timeLinesList.add(timeLineBean);
				
				
			}
			
			contentBean.setTimeLines(timeLinesList);
			
//…………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………			
//			接下来是对于contentbean中的具体信息的构造------------暂时不用数据库加载【因为数据库无法做到动态加载】
		/*	ArrayList<machBookDatelines> timeLines=bookOrdeMapper.loadListByOrderMachineInfoMapper(machineId,deptId);
			
			for (machBookDatelines machBookDatelines : timeLines) {
				
				String dateline = machBookDatelines.getDateline();
				
			}*/
		
//			Calendar calendar2 = Calendar.getInstance();
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			
			int count;
			Date beginDate;
			if((bday==null?"":bday).equals("")||(eday==null?"":bday).equals("")) {
				
				count=15;
				beginDate=calendar2.getTime();
			
			
			}else {
				 Date bdate = null;
				try {
					bdate = sdf2.parse(bday);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         Date edate = null;
				try {
					edate = sdf2.parse(eday);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		            
		         	count=DaiUtils.differentDays(bdate,edate);
		         	beginDate=bdate;
		         	calendar2.setTime(beginDate);
				
			}
           
			
//	        String time = sdf2.format(calendar2.getTime());// new Date()Ϊ��ȡ��ǰϵͳʱ��
			
	        ArrayList<dateContent> dateContentList = new ArrayList<dateContent>();
	        
	        for(int i=0;i<count;i++){
	        	
	        	dateContent dateContent = new dateContent();
	        	
	        	calendar2.add(Calendar.DATE, 0);
	        	dateContent.setDate(sdf2.format(calendar2.getTime()));
	        	dateContent.setIsSelected(false);
	        	dateContent.setIsToday(false);
	        	dateContent.setWeek(calendar2.get(Calendar.DAY_OF_WEEK)-1);
	        	dateContentList.add(dateContent);
	        	calendar2.add(Calendar.DATE, 1);
	        }
	       
	        List<machBookDatelines> machBookDatelines = new ArrayList<machBookDatelines>();
	       
	        for (dateContent dateContent : dateContentList) {
		    	String date = dateContent.getDate();
	        	int i=0;
	        	machBookDatelines machBookDateline = new machBookDatelines();
	        	machBookDateline.setMachineId(machineId);
	        	machBookDateline.setDateline(date);
	        	machBookDateline.setCreateTime(df.format(calendar2.getTime()));//后续再加上，不过这个没什么用
	        	
	        	
//		      	开始判断当前设备在当前日期是否维修中！！！
//		        查询当前的时间的的哪个设备在维修中！根据日期进行查询把！！！
//		        修改状态。修改描述
		        String isRepair ="select count(*) from machineRepair  a  where a.machineId=? and a.startDate<=? and a.endDate>=?  and a.repairState='是'";
		        String []  isRepairParam= {String.valueOf(machineId),date,date};
		        int isRepairNum = 0;
		        try {
					isRepairNum = Sqlhelper.exeQueryCountNum(isRepair, isRepairParam);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//		      
		        if(isRepairNum>0)  {
		        	machBookDateline.setState(2);
		        	machBookDateline.setDescription("设备异常");
		        }else {
		        	machBookDateline.setState(1);
		        	machBookDateline.setDescription("状态正常");
		        }
		        
	        	
	        	machBookDateline.setIsDeleted(0);
	        	
	        	machBookDateline.setSequence(i+1);
	        	i++;
	        	//machBookDateline.setState(1);//应该没什么用！！！！也可以有用，，前面进行判断，然后锁住所有的！
		        String deptMachineYearNo = machineId+"-"+date+"-";
		        machBookDateline.setDeptMachineYearNo(deptMachineYearNo);
				
		        //for(int i=0)//此处不需要循环，直接查询出来就是list的结构，直接就可以了
		        
		        
		        List<bookingInfoBean> sessionsList = new ArrayList<bookingInfoBean>();
		        String bookingInfoSql ="SELECT a.*,B.*,c.* FROM (select t.*from bookingInfo t  where time_YMD = '"+date+"' and machineId = '"+machineId+"' and  (t.is_pass <> '否' or t.is_pass is null)) B right join bookingInfosecond a on B.time_md = a. timeline left join orders c on c.order_id=B.orderId  order by a.timeline";
		        System.out.println(bookingInfoSql);
		        try {
					sessionsList = Sqlhelper.exeQueryList(bookingInfoSql,null,bookingInfoBean.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
		        
		        System.out.println(sessionsList.size());
		        
		        machBookDateline.setSessionsList(sessionsList);
				
		        machBookDatelines.add(machBookDateline);
			}
	        
	        contentBean.setMachBookDatelines(machBookDatelines);
			
			loadJson.setContent(contentBean);//此处需要传入一个contentbean
			
			
			  
	        String json = PluSoft.Utils.JSON.Encode(loadJson);
			System.out.println(json);
	        
	        
	        
			
//………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………………	        
	        
	        
			
			return json;
		}

		/**
		 * @param machineId
		 * @param deptId
		 * @return
		 */
		public String returnBookMachByMachService(String machineId, String deptId) {
			// TODO Auto-generated method stub
			
			
			Calendar calendar2 = Calendar.getInstance();
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			
	        String time = sdf2.format(calendar2.getTime());// new Date()Ϊ��ȡ��ǰϵͳʱ��
			
	        ArrayList<dateContent> dateContentList = new ArrayList<dateContent>();
			
	        List<String> machineIds = new ArrayList<String>();
			List<String> machineNames = new ArrayList<String>();
			
			if(deptId.equals("550")){
				machineNames.add("数控电火花成形机床");
				machineNames.add("数控低速走丝电火花线切割");
				machineNames.add("数控高速成型磨床");
				machineNames.add("超精密成形平面磨床");				
				machineNames.add("数控车床");
				machineNames.add("CNC雕刻机");
				machineIds.add("5501");
				machineIds.add("5502");
				machineIds.add("5503");
				machineIds.add("5504");
				machineIds.add("5505");
				machineIds.add("5506");
				
			}else{
				
				
				machineNames.add("车铣复合加工中心");
				machineNames.add("超高速磨床");
				machineNames.add("超声辅助高速加工中心");
				machineNames.add("高速五坐标加工中心");				
				machineNames.add("加工中心");
				machineIds.add("5511");
				machineIds.add("5512");
				machineIds.add("5513");
				machineIds.add("5514");
				machineIds.add("5515");
				
			}
	       
			for(int i=0;i<machineNames.size();i++){
	        	
	        	dateContent dateContent = new dateContent();
	        	calendar2.add(Calendar.DATE, 0);
	        	dateContent.setDate(machineNames.get(i));
	        	dateContent.setIsSelected(false);
	        	dateContent.setIsToday(false);
	        	dateContent.setWeek(calendar2.get(Calendar.DAY_OF_WEEK)-1);
	        	dateContent.setMachineId(machineIds.get(i));
	        	dateContentList.add(dateContent);
	        	calendar2.add(Calendar.DATE, 1);
	        }
	        
			jsonBean jsonBean =new jsonBean();
	        
	        jsonBean.setContent(dateContentList);
	        jsonBean.setMsg("操作成功");
	        jsonBean.setNo(2000);
	        jsonBean.setNow(time);
	        JSONObject json = JSONObject.fromObject(jsonBean);
	        String json1 = json.toString();
			System.out.println(json1);
			
			return json1;
		}


		/**
		 * @param unid
		 * @param orderId 
		 * @return
		 */
		@Transactional
		public String deleteSelectedBookingInfo (String unid, String orderId)throws Exception {
			String json = "";
			String result ="删除成功";
			int count=bookOrdeMapper.deleteSelectedBookingInfo(unid);
			if(count !=1) {
				result="删除失败";
			}
			Boolean isNewDate = DataBaseTool.selectNewDate(orderId);
			if(!isNewDate) result="更新失败！";
			json = "{\"result\":"+"\""+result+"\"}";
			return json;
		}
		/**
		 * @param unid
		 * @param orderId 
		 * @return
		 */
		@Transactional
		public String deleteSelectedBookingInfoByAudit (String unid, String orderId)throws Exception {
			String json = "";
			String result ="删除成功";
//			并不是真正的意义的删除，而是修改成不通过
			bookOrdeMapper.deleteSelectedBookingInfoByAudit(unid);
			
			Boolean isNewDate = DataBaseTool.selectNewDate(orderId);
			if(!isNewDate) result="更新失败！";
			json = "{\"result\":"+"\""+result+"\"}";
			return json;
		}


		/**
		 * @param orderId
		 * @return
		 */
		public String LoadCompletedOrderInfoService(String orderId) {
				
			Order order = bookOrdeMapper.AuditingBookUpdateStatusMapper(orderId);
				
				String json = PluSoft.Utils.JSON.Encode(order);
	//			String json = "{\"result\":"+"\""+jsonData+"\"}";
				return json;
				
		}


		/**
		 * @param orderId
		 * @param pageNo
		 * @param countPerPage
		 * @param sortField
		 * @param sortOrder
		 * @return
		 */
		public String returnCompletedBookMachine(String orderId, int pageNo, int pageSize, String sortField,
				String sortOrder) {
//			使用分页及其自动排序进行查询
			
			PageHelper.startPage(pageNo,pageSize,DaiUtils.jspNameToOralceName(sortField.equals("")?"unid":sortField)+" "+DaiUtils.jspNameToOralceName(sortOrder));
			List<BookOrderMachine> list = bookOrdeMapper.completedBookMapper(orderId);
			String json = DaiUtils.returnMiniUiJson(list);
			return json;
		}


		/**
		 * @param saveList
		 * @param orderId
		 * @return
//		 * 此service针对的是可以不断保存数据，同时使用事务增强健壮性！
		 */
		@Transactional
		public void saveCompletedBookingInfoService(ArrayList<HashMap<String, String>> saveList, String orderId,String completedAdvice) throws Exception{
			
			HttpSession session = request.getSession();
			String userId = ((User)session.getAttribute("user")).getUserId();
			String staffCode =  ((User)session.getAttribute("user")).getStaffCode();
			bookOrdeMapper.insertCompletedAuditerInfoMapper(staffCode,orderId,completedAdvice==null?"":completedAdvice);
			bookOrdeMapper.updateCompletedOrderInfoMapper("15",orderId);
			
			bookOrdeMapper.deleteCompletedBookingInfoMapper(orderId);
			for (HashMap<String,String> hashMap : saveList) {
				hashMap.put("orderId", orderId);
				bookOrdeMapper.saveCompletedBookingInfoMapper(hashMap);
				
//				String string =  hashMap.get("bookState");
//				/*System.out.println(string);*/
			}
			/*for (JSON json : saveList) {
				System.out.println(json.toString());
				JSONObject.parseObject(json,Map.class);
				 bookOrdeMapper.saveCompletedBookingInfoMapper();
			}*/
		/*	for (String saveString : saveList) {
				Map<String, Object> json2Map = DaiUtils.json2Map(saveString);
			}*/
			
		}



		/**
		 * @param orderId
		 * @param completedAdvice
		 * @param bookStatus
		 * @param staffCode 
		 */
		public void completedOrderAuditService(String orderId, String completedAdvice, String bookStatus, String staffCode) {
			// TODO Auto-generated method stub
			bookOrdeMapper.completedOrderAuditMapper(orderId,completedAdvice,bookStatus,staffCode);
		}



		/**
		 * @param staffCode
		 * @return
		 */
		public String isCanBookService(String staffCode) {
			// TODO Auto-generated method stub
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			String systemTime = df.format(new Date());// new Date()为获取当前系统时间
//			判断mapper
			int count=bookOrdeMapper.isCanBookMapper(systemTime,staffCode);
			
			return count>=1?"false":"true";
		}
		/**
		 * @param orderId
		 * @param bookStatus
		 * @param staffCode
		 * @param map
		 * @return
		 */
		public String AuditingBookingAll13(String orderId, String bookStatus, String staffCode, Map<String,String> map) {
			
			Iterator<String> iterator = map.keySet().iterator();
			HashSet<String> set = new HashSet<String>();
			while(iterator.hasNext()) {
				String next = iterator.next();
				set.add(next.substring(next.length()-4));
			}
			for(String a:set) {
				String  isPass =  map.get("isPass"+a);
				String  checkAdvice =  map.get("checkAdvice"+a);
				String  auditPerson =  map.get("auditPerson"+a).equals("")?staffCode:map.get("auditPerson"+a);
				bookOrdeMapper.deleteAuditingBookingAll13(orderId,a);
				if(isPass.equals("是")) {
				
				bookOrdeMapper.updateAuditingBookingAll13(orderId,staffCode,a,isPass,checkAdvice);
				}else {
//					删除bookInInof的信息
//					把删除的信息放在一个其他的表中[这个先不做了，直接根据里面的信息进行显示就好了！]
//					然后同时更新ordersMachinesAudits的中的状态，更改为不通过，不需要其他的设置了，因为这个订单不可能在进行预定这个机床了，这个基本的上不会发生 ，就算是发生也会给他重新换个时间的！！！
					int countNopass=bookOrdeMapper.noPassDeleteAuditingBookOrderMapper13(orderId,a);
					bookOrdeMapper.updateAuditingBookingAll13(orderId,staffCode,a,isPass,checkAdvice);
				}
			}
			String json = "{\"result\":"+"\""+"操作成功"+"\"}";
			return json;
		}
		/**
		 * @param saveList
		 * @param orderId
		 */
		@Transactional
		public void saveAuditAdviceService(ArrayList<HashMap<String, String>> saveList, String orderId) throws Exception{
			
			HttpSession session = request.getSession();
			String userId = ((User)session.getAttribute("user")).getUserId();
			String staffCode =  ((User)session.getAttribute("user")).getStaffCode();

//			目前的想法应该是新建一个表，表示审核意见 否的，然后把它们全部插入到这个新建的表中
//			然后分析list数据，遍历一遍，然后先进行全部更新，然后进行判断 否，多加一条信息，插入另一个表，删除原有信息！---目前没有按照这个写
			for (HashMap<String,String> hashMap : saveList) {
				hashMap.put("staffCode", staffCode);
				hashMap.put("orderId", orderId);
				bookOrdeMapper.saveAuditAdviceServiceUpdateMapper(hashMap);
			}
			
//			每次修改都把状态更新为预约审核中12.5
			bookOrdeMapper.updateOrderStatus(orderId,"12.5");
//			此处需要一个判断，是否全部审核完毕，没有空的值！！！ 
			
			int isPassCount = bookOrdeMapper.selectBookOrderInfoIsPassCount(orderId);
			if(isPassCount==0) bookOrdeMapper.updateOrderStatus(orderId,"13");
			
			
		}
		/**
		 * @param bookStatus 
		 * @param sortOrder 
		 * @param sortField 
		 * @param pageSize 
		 * @param pageNo 
		 * @param int pageSize 
		 * @param pageNo 
		 * @param bookStatus
		 * @param bookStatus2 
		 * @param sortOrder 
		 * @return
		 * 此处是显示需要完工审核的订单的！
		 */
		public String AuditingCompletedOrderService(int pageNo, int pageSize, String sortField, String sortOrder, String bookStatus) {
			
			String orderBy="e.createTime";
			PageHelper.startPage(pageNo,pageSize,orderBy);
			List<Order> list = bookOrdeMapper.selectCompletedBookOrderList(bookStatus);
			String json = DaiUtils.returnMiniUiJson(list);
			return json;
		}








		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
