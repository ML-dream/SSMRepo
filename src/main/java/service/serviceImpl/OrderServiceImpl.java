package service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl.forms.Customer;
import com.wl.forms.Order;
import com.wl.forms.User;

import javaBean.BookOrderMachine;
import javaBean.machineInfo5505;
import javaBean.machineInfoSiemens;
import mapper.BookOrderMapper;
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

	


	public String returnMybookMachine(String orderId, int pageNO, int countPerPage) {
		int one =countPerPage*pageNO;
		int two =(pageNO-1)*countPerPage+1;
		List<BookOrderMachine> list = bookOrdeMapper.selectBookOrderMachineListByOrderId(orderId, one, two);
		String json = PluSoft.Utils.JSON.Encode(list);
		return json;
	}

	@Override
	public String returnMyBookOrder(String staffCode,int pageNo, int countPerPage) {
			int one =countPerPage*pageNo;
			int two =(pageNo-1)*countPerPage+1;
			List<Order> list = bookOrdeMapper.selectBookOrderList(one,staffCode,two);
			int count = bookOrdeMapper.selectBookOrderCount(staffCode);
			
		
			
			
			String jsonData = PluSoft.Utils.JSON.Encode(list);
			String json = "{\"total\":"+count+",\"data\":"+jsonData+"}";
			return json;
		
	
	}
	
	public String updateAudutingBookStatus(String orderId,String bookStatus,String staffCode,String checkAdvice) {
	
		
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
	//AuditingBookOrderDelete
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
		@Autowired
		HttpServletRequest request;
		
		public String addShiYanOrder(String orderId, String customer, String connector, String connectorTel,
				String productName, String productNum, String material) {
			HttpSession session = request.getSession();
			String userId = ((User)session.getAttribute("user")).getUserId();
			String staffCode =  ((User)session.getAttribute("user")).getStaffCode();
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	         System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	         String createTime=df.format(new Date());
	         
			int count1 = bookOrdeMapper.insertAddShiYanOrder(orderId,customer,connector,connectorTel,productName,productNum,material,createTime,staffCode,new String("11"));
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
}
