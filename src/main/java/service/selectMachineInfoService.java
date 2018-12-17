package service;

import javaBean.machineInfo5505;
import javaBean.machineInfoSiemens;
import javaBean.user;

public interface selectMachineInfoService {

		public machineInfo5505 selectMachineInfo5505ById(String machineId);
		
		public String selectTotalMachineInfo(String machineId5501,String machineId5502,String machineId5503,String machineId5513,String machineId5505);
}
