package mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wl.forms.Customer;
import com.wl.forms.Machine;
import com.wl.forms.Order;
import com.wl.forms.OrdersMachinesAudits;

import javaBean.BookOrderMachine;
import javaBean.machBookDatelines;

public interface BookOrderMapper {
	
	public List<Order> selectBookOrderList(@Param("orderId") String orderId,@Param("staffCode") String staffCode,@Param("bookStatus")  String bookStatus,@Param("companyId")  String companyId,@Param("companyName")  String companyName) ;
	
	
	/**
	 * @param staffCode
	 * @param bookStatus
	 * @return
	 * 这个是针对设备管理员审核的查询！
	 */
	public List<Order> selectBookOrderList1(@Param("staffCode") String staffCode, @Param("bookStatus") String bookStatus);
	
	
	public List<Order> selectBookOrderListToo(@Param("orderId") String orderId,@Param("staffCode") String staffCode,@Param("bookStatus")  String bookStatus,@Param("companyId")  String companyId,@Param("companyName")  String companyName) ;
	public int selectBookOrderCount(@Param("staffCode") String staffCode);

	public List<BookOrderMachine> selectBookOrderMachineListByOrderId(@Param("orderId") String orderId);

	public int updateAuditingBookStatus(@Param("bookStatus") String bookStatus,@Param("auditingStaffCode") String staffCode,@Param("checkAdvice") String checkAdvice,@Param("orderId") String orderId);
	/**
	 * @param unid
	 * @return
	 */
	public int deleteAuditingBookOrderMapper(@Param("unid") String unid);
	/**
	 * @param orderId
	 * @return
	 * 这个两个表示，当删除订单的时候，就会把相应的预约信息一块删除！应该使用事务回滚，但是我没有使用，还不会
	 */
	public int deleteAuditingOrderMapper(@Param("orderId") String orderId);
	public int deleteAuditingOrderbookingInfoMapper(@Param("orderId") String orderId);
	/**
	 * @param orderId
	 * @return
	 */
	public Order AuditingBookUpdateStatusMapper(String orderId);
	/**
	 * @param orderId
	 * @return
	 */
	public List<OrdersMachinesAudits> AuditingBookUpdateStatusMapper1(String orderId);
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
	public int insertAddShiYanOrder(@Param("orderId") String orderId, @Param("customer") String customer,@Param("connector")  String connector,@Param("connectorTel")  String connectorTel,
			@Param("orderName") String orderName, @Param("productNum") String productNum,@Param("material")  String material,@Param("createTime") String createTime,@Param("staffCode") String staffCode,@Param("bookOrderStatus") String bookOrderStatus);
	/**
	 * @param staffCode
	 * @return
	 */
	public Customer loadDefaultShiYanOrder(String staffCode);
	/**
	 * @param machineId
	 * @param deptId
	 * @return
	 */
	public ArrayList<machBookDatelines> loadListByOrderMachineInfoMapper(@Param("machineId") String machineId,@Param("deptId") String deptId);
	/**
	 * @param orderId
	 * @return
	 */
	public int noPassDeleteAuditingBookOrderMapper(String orderId);
	public int noPassDeleteAuditingBookOrderMapper13(@Param("orderId") String orderId,@Param("machineId") String machineId);
	/**
	 * @param unid
	 * @return
	 */
	public void deleteSelectedBookingInfoByAudit(String unid);
	/**
	 * @param unid
	 * @return
	 */
	public int deleteSelectedBookingInfo(String unid);
	/**
	 * @param orderId
	 * @return
	 */
	public List<BookOrderMachine> completedBookMapper(String orderId);
	/**
	 * @param hashMap
	 */
	public void saveCompletedBookingInfoMapper(HashMap<String, String> hashMap);
	/**
	 * @param orderId
	 */
	public void deleteCompletedBookingInfoMapper(String orderId);
	/**
	 * @param orderId
	 * @Description 这个是针对的更新orders的状态值
	 */
	public void updateCompletedOrderInfoMapper(@Param("bookStatus") String bookStatus,@Param("orderId") String orderId);
	/**
	 * @param staffCode
	 * @param orderId
	 *  用于插入完工审核人的信息
	 */
	public void insertCompletedAuditerInfoMapper(@Param("staffCode") String staffCode,@Param("orderId") String orderId,@Param("completedAdvice") String completedAdvice);
	

	
	/**
	 * @param orderId
	 * @param completedAdvice
	 * @param bookStatus
	 * 这个是更新最终的确认的信息的！
	 * @param staffCode 
	 */
	public void completedOrderAuditMapper(@Param("orderId") String orderId,@Param("completedAdvice") String completedAdvice,@Param("bookStatus") String bookStatus,@Param("staffCode") String staffCode);
	/**
	 * @param systemTime
	 * @param staffCode
	 * @return
	 */
	public int isCanBookMapper(@Param("systemTime") String systemTime, @Param("staffCode") String staffCode);


	/**
	 * @param orderId
	 * @param staffCode
	 * @return
	 */
	public List<BookOrderMachine> selectBookOrderMachineListByOrderId1(@Param("orderId") String orderId,@Param("staffCode") String staffCode);


	/**
	 * @param orderId
	 * @param staffCode
	 * @return
	 */
	public List<Machine> updateAuditsMapper(@Param("orderId") String orderId,@Param("staffCode")  String staffCode);


	/**
	 * @param orderId
	 * @param a
	 */
	public void deleteAuditingBookingAll13(@Param("orderId") String orderId, @Param("machineId") String machineId);


	/**
	 * @param orderId
	 * @param staffCode
	 * @param a
	 * @param isPass
	 * @param checkAdvice
	 */
	public void updateAuditingBookingAll13(@Param("orderId") String orderId,@Param("staffCode") String staffCode,@Param("machineId") String a,@Param("isPass") String isPass,
			@Param("checkAdvice") String checkAdvice);


	/**
	 * @param hashMap
	 */
	public void saveAuditAdviceServiceUpdateMapper(HashMap<String, String> hashMap);


	/**
	 * @param orderId
	 */
	public int selectBookOrderInfoIsPassCount(String orderId);


	/**
	 * @param orderId
	 * @param string
	 */
	public void updateOrderStatus(@Param("orderId") String orderId,@Param("bookStatus") String string);

		
}
