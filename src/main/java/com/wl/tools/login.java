package com.wl.tools;

import cfmes.bom.entity.Menu;
import cfmes.util.DealString;
import cfmes.util.sql_data;
import cfmes.util.strFormat;

import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class login {
	private PrintWriter out = null;
	private String username;     // 登录用户名
	private String passwd;       // 登录密码
	private String userid;       // 登录用户名
	private String role_id;      // 用户角色
	private int user_status;     // 用户状态
	String sqlStr;               // 检索数据库SQL语句
	private String staff_name;
	private String role_name;
	private int day;             // 密码修改天数
	private String SectionName;
	private String DepName;

	sql_data sqlbean = new sql_data();

	public String getDepName() {
		try {
			String sta = "select e.dept_id from work.employee_info e,work.sysusers s " +
					"where s.user_id='"+ userid+ "' and s.staff_code=e.staff_code";
			ResultSet rs = sqlbean.executeQuery(sta);
			while (rs.next())
				DepName = rs.getString("dept_id");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return DepName;
	}

	public String getSectionName() {
		try {
		ResultSet rs=sqlbean.executeQuery("select e.section_code " +
				"from employee_info e,sysusers s where s.user_id='"
				+userid+"' and s.staff_code=e.staff_code");
			if(rs.next())
				SectionName=rs.getString("section");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SectionName;
	}

	public String getUsername() {
		return username;
	}

	public void setUserid(String newuserid) {
		userid = newuserid;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String newpasswd) {
		passwd = newpasswd;
	}

	public String getUserid() {
		return userid;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getStaff_name() {
		return staff_name;
	}

	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public int getUser_status() {
		return user_status;
	}

	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}

	public String getSql() {
		sqlStr = "select * from work.sysusers where user_id = '"
				+ strFormat.toSql(userid) + "'";
		return sqlStr;

	}

	/** 进入系统操作 */
	public int excute() {

		int flag = 0;
		ResultSet rs = sqlbean.executeQuery(getSql());
		try {
			if (rs.next()) {
				flag = 1;
				if (rs.getString("password").equals(passwd)) {
					flag = 2;
				}
				role_name = rs.getString("role_id");
				day = getIntervalDays(rs.getDate("pwd_modify_time") == null ? new Date():rs.getDate
						("pwd_modify_time"), new Date());
				System.out.println("day:" + day);
				if (day > 3000) {// 改掉，原来是day>30，及大鱼30天没改密码就修改状态。现在调试，改掉
					user_status = 3;
					Up_status(userid, 3);
				} else {
					user_status = rs.getInt("user_status");
				}
				if (day <= 0) {
					Up_Modify_time(userid);
				}
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("login.excute():" + e.getMessage());
			// out.println("<script>alert('数据库cfmes连接失败!')</script>");out.flush();out.close();
		} finally {
			sqlbean.closeConn();
		}
		return flag;
	}

	public boolean isin(String user_id, String password) {
		sql_data sqlbean = new sql_data();
		boolean flag = false;
		ResultSet rs = sqlbean.executeQuery("select * from work.sysusers where user_id = '"
				+ strFormat.toSql(user_id) + "' and password='"+ strFormat.toSql(password) + "'");
		try {
			if (rs.next()) {
				flag = true;
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("login.isin():" + e.getMessage());
			// out.println("<script>alert('数据库cfmes连接失败!')</script>");out.flush();out.close();
		} finally {
			sqlbean.closeConn();
		}
		return flag;
	}

	/** 查询用户信息 */
	public void query(){
		ResultSet rs = sqlbean.executeQuery("select s.pwd_modify_time,e.staff_name,s.role_id " +
				"from work.sysusers s," +"work.employee_info e " +
				"where "+" s.user_id='"+userid+"' and e.staff_code=s.staff_code");
//		ResultSet rs = sqlbean.executeQuery("select s.user_role from meteor.sysusers_new s," +
//				"meteor.role_info r where "
//				+" s.user_id='"+userid+"' and r.user_role=s.user_role");
		try{
			if (rs.next()) {
				staff_name = rs.getString("staff_name");
				role_name = rs.getString("role_id");
				day = getIntervalDays(rs.getDate("pwd_modify_time"),new Date());
			}
			rs.close();
		}catch(Exception e){
			System.out.println("login.query():" + e.getMessage());
		}finally{
			sqlbean.closeConn();
		}
	}

	/** 进入系统操作 */
	public void setinfo(String userid,String role_id,String computer_name){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        String str=sdf.format(new Date()); 
        try{
//        	sqlbean.executeUpdate("insert into meteor.userlog(user_id,login_time,log_roll,computer_name) " +
//    				"values ('"+userid+"',to_date('"+str+"','yyyy-MM-dd HH24:mi:ss'),'"+user_role+"','"+computer_name+"')");
//    		sqlbean.executeUpdate("update meteor.sysusers_new SET user_status ='1', computer_name='"+computer_name+"' where user_id='"+userid+"'");
// 这几条语句运行时会出现  Computer-name 标示符无效，所以把 Computer-name 删掉了    
    		sqlbean.executeUpdate
    		("insert into work.userlog(user_id,login_time,log_roll,computer_name) " 
    			+"values ('"+userid+"',to_date('"+str+"','yyyy-MM-dd HH24:mi:ss'),'"
    			+role_id+"','"+computer_name+"')");
    		//sqlbean.executeUpdate("update work.sysusers SET user_status ='1' where user_id='"+userid+"'");
    		sqlbean.executeUpdate("update work.sysusers SET user_status ='0' " +
    				"where user_id='"+userid+"'");//状态该为零，防止调试时，不正常退出重新该状态
        }catch(Exception e){
    		System.out.println("login.setinfo():" + e.getMessage());
    	}finally{
    		sqlbean.closeConn();
    	}
	}

	/** 退出系统操作 */
	public void setout(String userid){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        String str=sdf.format(new Date()); 
        ResultSet rs=sqlbean.executeQuery("select user_status from work.sysusers " +
        		"where user_id='"+userid+"'");  
        try{
        	//if(rs.next() && rs.getInt("user_status")==1){//状态该为零，防止调试时，不正常退出重新该状态
        	if(rs.next() && rs.getInt("user_status")==0){
        		sqlbean.executeUpdate("update work.sysusers SET user_status ='0' " +
        				"where user_id='"+userid+"'");
        		sqlbean.executeUpdate("update work.userlog SET " +
        				"logout_time=to_date('"+str+"','yyyy-MM-dd HH24:mi:ss') "
        				+"where user_id='"+userid+"' and login_time=(select max(login_time) " +
        				"from work.userlog where user_id='"+userid+"')");
        	}rs.close();
        }catch(Exception e){
    		System.out.println("login.setout():" + e.getMessage());
    	}finally{
    		sqlbean.closeConn();
    	}
	}

	/** 修改用户状态操作 */
	public void Up_status(String userid, int status) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(new Date());
		try {
			sqlbean.executeUpdate("update work.sysusers SET user_status ='"
					+ status + "' where user_id='" + userid + "'");
		} catch (Exception e) {
			System.out.println("login.Up_status():" + e.getMessage());
		} finally {
			sqlbean.closeConn();
		}
	}

	/** 修改密码操作 */
	public void Up_Modify_time(String userid){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        String str=sdf.format(new Date()); 
        try{
    		sqlbean.executeUpdate("update work.sysusers SET pwd_modify_time=to_date('"
    				+str+"','yyyy-MM-dd HH24:mi:ss') where user_id='"+userid+"'");
        }catch(Exception e){
    		System.out.println("login.Up_Modify_time():" + e.getMessage());
    	}finally{
    		sqlbean.closeConn();
    	}
	}

	/** 修改密码 */
	public void Up_Password(String user_id,String password){
		sql_data sqlbean = new sql_data();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        String str=sdf.format(new Date()); 
        try{
    		sqlbean.executeUpdate("update work.sysusers SET pwd_modify_time=to_date('"
    				+str+"','yyyy-MM-dd HH24:mi:ss'), password='"+password
    				+"',user_status ='0' where user_id='"+user_id+"'");
        }catch(Exception e){
    		System.out.println("login.Up_Password():" + e.getMessage());
    	}finally{
    		sqlbean.closeConn();
    	}
	}

	/** 读用户权限中的菜单数据 */
	public ArrayList getMenu(String role_id) {

		String sql = "select r.menu_id,l.menu_name from work.menu_list l,work.menu_right r " +
				"where r.role_id='"+ role_id+ "' and l.menu_id=r.menu_id " +
				"and l.bc='B' and l.parent_menu_id is null order by l.id";
		sql_data sqlbean = new sql_data();
		ResultSet rs = sqlbean.executeQuery(sql);
		ArrayList list = new ArrayList();
		DealString ds = new DealString();
		try {
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setMenu_id(ds.toString(rs.getString("menu_id")));
				menu.setMenu_name(ds.toString(rs.getString("menu_name")));
				list.add(menu);
			}
			rs.close();
		} catch (Exception ex) {
			System.out.println("Login.getMenu()处理时出错；错误为：" + ex);
		} finally {
			sqlbean.closeConn();
		}
		return list;
	}

	/** 读用户权限中的子菜单数据 */
	public ArrayList getChildMenu(String role_id, String menu_id) {

		String sql = "select r.menu_id,l.menu_name from work.menu_list l,work.menu_right r " +
				"where r.role_id='"+ role_id+ "' and l.menu_id=r.menu_id and l.bc='B' " +
				"and l.parent_menu_id='"+ menu_id + "' order by l.id";
		sql_data sqlbean = new sql_data();
		ResultSet rs = sqlbean.executeQuery(sql);
		ArrayList list = new ArrayList();
		DealString ds = new DealString();
		try {
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setMenu_id(ds.toString(rs.getString("menu_id")));
				menu.setMenu_name(ds.toString(rs.getString("menu_name")));
				list.add(menu);
			}
			rs.close();
		} catch (Exception ex) {
			System.out.println("Login.getChildMenu()处理时出错；错误为：" + ex);
		} finally {
			sqlbean.closeConn();
		}
		return list;
	}

	public int getIntervalDays(Date startday, Date endday) {
		// 确保startday在endday之前
		// if(startday.after(endday)){
		// Date cal=startday;
		// startday=endday;
		// endday=cal;
		// }
		// 分别得到两个时间的毫秒数
		long sl = startday.getTime();
		long el = endday.getTime();

		long ei = el - sl;
		// 根据毫秒数计算间隔天数
		return (int) (ei / (1000 * 60 * 60 * 24));
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

}
