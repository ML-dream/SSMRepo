package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wl.forms.Customer;
import com.wl.forms.Order;

import javaBean.BookOrderMachine;

public interface BookOrderMapper {
	
	public List<Order> selectBookOrderList(@Param("one") int one ,@Param("staffCode") String staffCode,@Param("two") int two) ;
	public int selectBookOrderCount(@Param("staffCode") String staffCode);

	public List<BookOrderMachine> selectBookOrderMachineListByOrderId(@Param("orderId") String orderId,@Param("one") int one,@Param("two") int two);

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
	 * @param customer
	 * @param connector
	 * @param connectorTel
	 * @param productName
	 * @param productNum
	 * @param material
	 * @return
	 */
	public int insertAddShiYanOrder(@Param("orderId") String orderId, @Param("customer") String customer,@Param("connector")  String connector,@Param("connectorTel")  String connectorTel,
			@Param("productName") String productName, @Param("productNum") String productNum,@Param("material")  String material,@Param("createTime") String createTime,@Param("staffCode") String staffCode,@Param("bookOrderStatus") String bookOrderStatus);
	/**
	 * @param staffCode
	 * @return
	 */
	public Customer loadDefaultShiYanOrder(String staffCode);
		
}
