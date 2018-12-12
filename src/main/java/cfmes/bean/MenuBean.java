package cfmes.bean;

import java.util.*;
import java.sql.*;


import cfmes.util.*;
import cfmes.bom.entity.Menu;

public class MenuBean {

	public ArrayList GetMenu(){
		ArrayList list = new ArrayList();
		
		String sql = "select * from work.menu_list where memo='main'";
		sql_data sqlbean =new sql_data();
		ResultSet rs = sqlbean.executeQuery(sql);
		try{
			while(rs.next()){
				Menu menu = new Menu();
				menu.setMenu_id(rs.getString("menu_id"));
				menu.setMenu_name(rs.getString("menu_name"));
                list.add(menu);
			}
		}catch(Exception ex){
			System.out.println("MenuBean.GetMenu()����ʱ���?����Ϊ��"+ex);
		}finally{
			sqlbean.closeConn();
		}
		return list;
		
	}
	
	public ArrayList GetChildMenu(String menu_id){
		ArrayList list =new ArrayList();
		String sql = "select * from work.menu_list where father_menuid='"+menu_id+"'";
		sql_data sqlbean =new sql_data();
		ResultSet rs = sqlbean.executeQuery(sql);
		try{
			while(rs.next()){
				Menu menu = new Menu();
				menu.setMenu_id(rs.getString("menu_id"));
				menu.setMenu_name(rs.getString("menu_name"));
                list.add(menu);
			}
		}catch(Exception ex){
			System.out.println("MenuBean.GetChildMenu()����ʱ���?����Ϊ��"+ex);
		}finally{
			sqlbean.closeConn();
		}
		return list;
	}
}