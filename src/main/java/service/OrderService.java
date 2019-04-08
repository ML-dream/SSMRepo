package service;

import com.wl.forms.Order;

import javaBean.BookOrderMachine;
import javaBean.machineInfo5505;

public interface OrderService {
	
	
	//public String returnMyBookOrder(String staffCode,int pageNo,int countPerPage);
	
	public String returnMybookMachine(String orderId,int pageNO,int countPerPage, String sortField, String sortOrder);

	/**
	 * @param staffCode
	 * @param pageNo
	 * @param countPerPage
	 * @param bookStatus
	 * @param companyId
	 * @param companyName
	 * @return
	 */
	String returnMyBookOrder(String orderId,String staffCode, int pageNo, int countPerPage,String sortField, String sortOrder, String bookStatus, String companyId,
			String companyName);

	

	
}
