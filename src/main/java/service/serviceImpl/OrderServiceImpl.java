package service.serviceImpl;

import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ForbiddenException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.wl.forms.Customer;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.testaction.utils.DaiUtils;
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

	
	@Override
	public String returnMyBookOrder(String staffCode,int pageNo, int pageSize,String sortField,String sortOrder, String bookStatus, String companyId, String companyName) {
//			int one =countPerPage*pageNo;
//			int two =(pageNo-1)*countPerPage+1;
			 sortField=sortField.equals("")?"orderId":sortField;
			 sortOrder=sortOrder.equals("")?"desc":sortOrder;
			 String orderBy=DaiUtils.jspNameToOralceName(sortField)+" "+DaiUtils.jspNameToOralceName(sortOrder);
			 PageHelper.startPage(pageNo,pageSize,orderBy);
			 List<Order> list = bookOrdeMapper.selectBookOrderList(staffCode,bookStatus,companyId,companyName);
//			 int count = bookOrdeMapper.selectBookOrderCount(staffCode);
			
			 String json = DaiUtils.returnMiniUiJson(list);
			
//			String jsonData = PluSoft.Utils.JSON.Encode(list);
//			String json = "{\"total\":"+count+",\"data\":"+jsonData+"}";
			return json;
		
	
	}
	
	public String updateAudutingBookStatus(String orderId,String bookStatus,String staffCode,String checkAdvice) {
	
		
		if(bookStatus.equals("14")) {
			
			int countNopass=bookOrdeMapper.noPassDeleteAuditingBookOrderMapper(orderId);
			System.out.println(countNopass);
		}
		
		
		int count = bookOrdeMapper.updateAuditingBookStatus(bookStatus,staffCode,checkAdvice,orderId);
		String jsonData="";
		if (count>=1) {
			jsonData="操作成功";
			
		}else {
			jsonData="操作失败";
		}
		String json = "{\"result\":"+"\""+jsonData+"\"}";
		return json;
	

	}

	public String deleteAuditingBookOrder(String unid) {
	
		
		int count = bookOrdeMapper.deleteAuditingBookOrderMapper(unid);
		
		String jsonData="";
		
		if (count>=1) {
			jsonData="操作成功";
			
		}else {
			jsonData="操作失败";
		}
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
	        	machBookDateline.setDescription("暂时没有任何说明");
	        	machBookDateline.setIsDeleted(0);
	        	
	        	machBookDateline.setSequence(i+1);
	        	i++;
	        	machBookDateline.setState(1);//应该没什么用！！！！也可以有用，，前面进行判断，然后锁住所有的！
		        String deptMachineYearNo = machineId+"-"+date+"-";
		        machBookDateline.setDeptMachineYearNo(deptMachineYearNo);
				
		        //for(int i=0)//此处不需要循环，直接查询出来就是list的结构，直接就可以了
		        
		        
		        List<bookingInfoBean> sessionsList = new ArrayList<bookingInfoBean>();
		        String bookingInfoSql ="SELECT a.*,B.*,c.* FROM (select t.*from bookingInfo t  where time_YMD = '"+date+"' and machineId = '"+machineId+"') B right join bookingInfosecond a on B.time_md = a. timeline left join orders c on c.order_id=B.orderId  order by a.timeline";
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
		 * @return
		 */
		
		public String deleteSelectedBookingInfo(String unid) {
			String json = "";
			String result ="删除成功";
			int count=bookOrdeMapper.deleteSelectedBookingInfo(unid);
			if(count !=1) {
				
				result="删除失败";
				
			}
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
			bookOrdeMapper.updateCompletedOrderInfoMapper("16",orderId);
			
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
