package com.wl.forms;

import java.io.Serializable;


public class User implements Serializable{

	private static final long serialVersionUID = -8290392392373777264L;
	private String userId;
	private String userName;
   	private String staffCode;
    private String staffName;
    private String password;
    private String authority;
    private String authorityName;
    private String oldPassword;
    private String registerTime;
    private String pwdModifyTime;
    private String firstLoginTime;
    private String onlineTime;
    private String exitTime;
    private String workerType;	//xiem 工种
    
    
	public String getWorkerType() {
		return workerType;
	}
	public void setWorkerType(String workerType) {
		this.workerType = workerType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getPwdModifyTime() {
		return pwdModifyTime;
	}
	public void setPwdModifyTime(String pwdModifyTime) {
		this.pwdModifyTime = pwdModifyTime;
	}
	public String getFirstLoginTime() {
		return firstLoginTime;
	}
	public void setFirstLoginTime(String firstLoginTime) {
		this.firstLoginTime = firstLoginTime;
	}
	public String getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}
	public String getExitTime() {
		return exitTime;
	}
	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}
    
	/*
	public void getStaffCodeFromUserName(){
		String staffcode="";
		sql_data sd=new sql_data();
		ResultSet rs=null;
		String sql="select staff_code from work.sysusers where user_id='"+this.user_id+"'";
		rs=sd.executeQuery(sql);
		try {
			if(rs.next()){
				this.StaffCode=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sd.closeConn();
	}*/
}
