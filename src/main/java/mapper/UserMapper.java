package mapper;

import antlr.collections.List;
import javaBean.machineInfo5505;
import javaBean.machineInfoSiemens;
import javaBean.user;

public interface UserMapper {


	public user findUserById(String id) throws Exception;

	public machineInfo5505 machineInfo5505(String machineId);
	
	public machineInfoSiemens selectTotalMachineInfo5501ById(String machineId);
	public machineInfoSiemens selectTotalMachineInfo5502ById(String machineId);
	public machineInfoSiemens selectTotalMachineInfo5503ById(String machineId);
	public machineInfoSiemens selectTotalMachineInfo5513ById(String machineId);
	public machineInfo5505 selectTotalMachineInfo5505ById(String machineId);
}
