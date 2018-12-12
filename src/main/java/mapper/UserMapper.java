package mapper;

import javaBean.machineInfo5505;
import javaBean.user;

public interface UserMapper {


	public user findUserById(String id) throws Exception;

	public machineInfo5505 selectMachineInfo5505ById(String machineId);
}
