package service;

import com.wl.forms.Order;

import javaBean.BookOrderMachine;
import javaBean.machineInfo5505;

public interface OrderService {
	
	
	public String returnMyBookOrder(String staffCode,int pageNo,int countPerPage);
	
	public String returnMybookMachine(String orderId,int pageNO,int countPerPage);
	
}
