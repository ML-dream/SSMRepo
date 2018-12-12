package com.wl.forms;

import java.sql.ResultSet;

import cfmes.util.sql_data;

public class ResManage {

	/**判断item中是否有该物料*/
	public boolean isin_Item(String itemid) {
		
        String sql = "select item_id from work.item where item_id='"+itemid+"'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        boolean isin=false;
        try {
            if (rs.next()) {
            	isin=true;
            }rs.close();
//            System.out.println("isin_Item: "+isin);
        } catch (Exception ex) {
        	System.out.println("ResManage.isin_Item()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
    }
	public void update(String sql) {
		sql_data sqlbean = new sql_data();
        try {
        	sqlbean.executeUpdate(sql);	
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.update()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
}
