package javaBean;

public class user {
	private String userId;
	private String nckType;
	private String machineName;
	private String machineSpec;
	

	public user() {
		super();
		// TODO Auto-generated constructor stub
	}

	public user(String id,String nckType,String machineSpec, String machineName) {
		super();
		this.userId = id;
		this.nckType=nckType;
		this.machineName=machineName;
		this.machineSpec=machineSpec;
	}
	
	
	public String getNckType() {
		return nckType;
	}

	public void setNckType(String nckType) {
		this.nckType = nckType;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getMachineSpec() {
		return machineSpec;
	}

	public void setMachineSpec(String machineSpec) {
		this.machineSpec = machineSpec;
	}

	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String id) {
		this.userId = id;
	}
	
}
