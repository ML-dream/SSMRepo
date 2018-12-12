package cfmes.bom.dao;
import cfmes.bom.entity.Bom;
import cfmes.bom.entity.Issue;
import cfmes.util.sql_data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IssueBeanDao {
	
	/**�ж�Issue��Ӱ汾ʱ���ܴ���û���ظ�*/
	public boolean isRepeat_sortie(String flight_type,String product_id ,String lot ,String b_sortie,String e_sortie) {
		
        String sql = "SELECT * FROM meteorfan.issue_deploy WHERE flight_type='"+ flight_type+"' and product_id='"+ product_id
                      +"' and lot='"+lot+"' and (e_sortie  between "+b_sortie+" and "+e_sortie+"  or b_sortie  between "+b_sortie+" and "+e_sortie+")";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        boolean isin=false;
        try {
            if (rs.next()) {
            	isin = true;
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getLevel_exist()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
    }
	/**�ж�Issue��Ӱ汾ʱ���汾����û���ظ�*/
	public int near_num(String flight_type,String product_id ,int sortie) {
		
        String sql = "SELECT * FROM meteor.issue_deploy WHERE flight_type='"+ flight_type+"' and product_id='"+ product_id+"''";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        boolean isin=false;
        int a=0,b,c;
        try {
            while (rs.next()) {
            	//isin = true;
            	//rs.getInt(e_sortie);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getLevel_exist()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return a;
    }
	/**�ж�Issue��Ӱ汾ʱ���汾����û���ظ�*/
	public boolean isRepeat_num(String flight_type,String product_id ,String issue_num) {
		
        String sql = "SELECT * FROM meteor.issue_deploy WHERE flight_type='"+ flight_type+"' and product_id='"+ product_id
                      +"' and issue_num='"+issue_num+"'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        boolean isin=false;
        try {
            if (rs.next()) {
            	isin = true;
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getLevel_exist()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
    }
	/**���ڹ���bom*/
	public ArrayList getBomList(String product_id,String issue_num) {
		
        String sql = "SELECT * from meteor.ebom where product_id='"+ product_id+"' and issue_num like '"+issue_num+"%'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        ArrayList list = new ArrayList();
        try {
        	while (rs.next()) {
                Bom bom = new Bom();
                bom.setItem_id(rs.getString("item_id"));
                bom.setId(rs.getString("id"));
                bom.setFather_item_id(rs.getString("father_item_id"));
                bom.setFid(rs.getString("fid"));
                bom.setIssue_num(rs.getString("issue_num"));
                bom.setLevel_id(rs.getString("level_id"));
                list.add(bom);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("IssueBeanDao.getBomList()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	/**����BOM*/
	public void update(String sql) {
		sql_data sqlbean = new sql_data();
	    try {
	    	sqlbean.executeUpdate(sql);	
	    } catch (Exception ex) {
	    	System.out.println("BomBeanDao.update()����ʱ���?����Ϊ��"+ex);
	    } finally {
	    	sqlbean.closeConn();
	    }
	}
}
