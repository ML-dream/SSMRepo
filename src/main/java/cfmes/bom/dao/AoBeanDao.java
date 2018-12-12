package cfmes.bom.dao;
import cfmes.bom.entity.Bom;
import cfmes.bom.entity.Dept;
import cfmes.util.sql_data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class AoBeanDao {

	/**�ж�aomain���Ƿ��и�AO���*/
	public boolean isinAover(String product_id,String issue_num,String ao_no,String aover) {
			
	        String sql = "select * from work.aomain where product_id='"+product_id+"' and issue_num='"+issue_num
	                     +"' and ao_no='"+ao_no+"' and ao_ver='"+aover+"'";//
	        sql_data sqlbean = new sql_data();
	        ResultSet rs = sqlbean.executeQuery(sql);
	        boolean isin=false;
	        try {
	            if (rs.next()) {
	            	isin=true;
	            }rs.close();
	        } catch (Exception ex) {
	        	System.out.println("AoBeanDao.isinAo()����ʱ���?����Ϊ��"+ex);
	        } finally {
	        	sqlbean.closeConn();
	        }
	        return isin;
	 }
		/**�ж�aomain���Ƿ��и�AO��źͰ汾*/
	public boolean isinAoOp(String product_id,String issue_num,String ao_no,String aover,String ao_opid) {
			
	        String sql = "select * from meteor.ao_oper where produceid='"+product_id+"' and issue_num='"+issue_num
	                     +"' and ao_no='"+ao_no+"' and aover='"+aover+"' and ao_opid='"+ao_opid+"'";
	        sql_data sqlbean = new sql_data();
	        ResultSet rs = sqlbean.executeQuery(sql);
	        boolean isin=false;
	        try {
	            if (rs.next()) {
	            	isin=true;
	            }rs.close();
	        } catch (Exception ex) {
	        	System.out.println("AoBeanDao.isinAoOp()����ʱ���?����Ϊ��"+ex);
	        } finally {
	        	sqlbean.closeConn();
	        }
	        return isin;
	 }
	/**�ж�aomain���Ƿ��и�AO���*/
	public boolean ExistAo_no(String product_id,String issue_num,String item_id,String ao_no) {
			
	        String sql = "select * from work.aomain where product_id='"+product_id+"' and issue_num='"+issue_num
	                     +"' and ao_no='"+ao_no+"' and item_id !='"+item_id+"'";
	        sql_data sqlbean = new sql_data();
	        ResultSet rs = sqlbean.executeQuery(sql);
	        boolean isin=false;
	        try {
	            if (rs.next()) {
	            	isin=true;
	            }rs.close();
	        } catch (Exception ex) {
	        	System.out.println("AoBeanDao.isinAoOp()����ʱ���?����Ϊ��"+ex);
	        } finally {
	        	sqlbean.closeConn();
	        }
	        return isin;
	 }
	/**����BOM*/
	public void update(String sql) {
		sql_data sqlbean = new sql_data();
	    try {
	    	sqlbean.executeUpdate(sql);	
	    } catch (Exception ex) {
	    	System.out.println("AoBeanDao.update()����ʱ���?����Ϊ��"+ex);
	    } finally {
	    	sqlbean.closeConn();
	    }
	}
	/**�ж�aomain���Ƿ���AO*/
	public int AoNum(String product_id,String issue_num,String item_id) {
		
        String sql = "select count(ao_no) as a from meteor.aomain where productid='"+product_id+"' and issue_num='"+issue_num+"' and itemid='"+item_id+"'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        int isin = 0;
        try {
            if (rs.next()) {
            	isin = rs.getInt("a");
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("AoBeanDao.isAoNull()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
    }
	/**��BOM��������ϵ��б�*/
	public ArrayList getBom(String product_id,String item_id ,String issue) {
		int p = Integer.parseInt(issue);//�õ��汾λ��
	    String issue_like="";
	    for(int i=1;i<=p;i++){       //���� issue_numֵ
		   if(i==p){
		      issue_like = issue_like+'1';
		   }if(i<p){
		      issue_like = issue_like+'_';
		   }
		}
        String sql = "select b.father_item_id,b.fid,b.id,b.level_id,i.item_name from meteor.ebom b,meteor.item i where b.product_id='"+ product_id
                     +"' and b.item_id='"+item_id+"' and b.issue_num like '"+issue_like+"%' and i.itemid=b.item_id ";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        ArrayList list = new ArrayList();
        //System.out.println("AoBeanDao.getBom()");
        try {
        	while (rs.next()) {
                Bom bom = new Bom();
                bom.setFather_item_id(rs.getString(1));
                bom.setFid(rs.getString(2));
                bom.setId(rs.getString(3));
                bom.setLevel_id(rs.getString(4));
                bom.setItem_name(rs.getString(5));
                list.add(bom);
            }rs.close();
        }catch (Exception ex) {
        	System.out.println("AoBeanDao.getBom()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	/**�õ�AO���*/
	public ArrayList getAo_no(String flight_type,String product_id,String issue_num,String item_id) {
			
	        String sql = "select ao_no,ao_ver from work.aomain where product_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num='"+issue_num
	                     +"' and item_id='"+item_id+"'";
	        sql_data sqlbean = new sql_data();
	        ResultSet rs = sqlbean.executeQuery(sql);
	        ArrayList list = new ArrayList();
	        try {
	            while (rs.next()) {
	            	Dept dept = new Dept();
	        		dept.setDept_id(rs.getString(1));
	        		dept.setDept_name(rs.getString(2));
	                list.add(dept);
	            }rs.close();
	        } catch (Exception ex) {
	        	System.out.println("AoBeanDao.getAo_no()����ʱ���?����Ϊ��"+ex);
	        } finally {
	        	sqlbean.closeConn();
	        }
	        return list;
	 }

	/**�õ�AO���2*/
	public ArrayList getAo_no2(String flight_type,String product_id,String issue_num,String item_id) {
			
	        String sql = "select distinct ao_no,ao_name from work.aomain where product_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num='"+issue_num
	                     +"' and item_id='"+item_id+"'";
	        sql_data sqlbean = new sql_data();
	        ResultSet rs = sqlbean.executeQuery(sql);
	        ArrayList list = new ArrayList();
	        try {
	            while (rs.next()) {
	            	Dept dept = new Dept();
	        		dept.setDept_id(rs.getString(1));
	        		dept.setDept_name(rs.getString(2));
	                list.add(dept);
	            }rs.close();
	        } catch (Exception ex) {
	        	System.out.println("AoBeanDao.getAo_no()����ʱ���?����Ϊ��"+ex);
	        } finally {
	        	sqlbean.closeConn();
	        }
	        return list;
	 }
	

/**��item�õ������б?��������dept��**/
public ArrayList getCMT(String type) {
	String sql ="";
		sql = "select item_id,item_name from work.item where item_typeid = '"+type+"' order by item_id desc";
	
    sql_data sqlbean = new sql_data();
    ResultSet rs = sqlbean.executeQuery(sql);
    ArrayList list = new ArrayList();
    try {
    	while (rs.next()) {
    		Dept dept = new Dept();
    		dept.setDept_id(rs.getString(1));
    		dept.setDept_name(rs.getString(2));
            list.add(dept);
        }rs.close();
    }catch (Exception ex) {
    	System.out.println("FoBeanDao.getDept(int num)����ʱ���?����Ϊ��"+ex);
    } finally {
    	sqlbean.closeConn();
    }
    return list;
}

/**�жϸ�AO���Ƿ��ظ���ӵ��������оߡ�ԭ���ϡ�����**/
public boolean isin(String product_id,String issue_num,String fox_id,String ao_operid,String aover,String type) {
	
    String sql = "select * from work.ao_"+type+" where product_id='"+product_id+"' and issue_num='"+issue_num
                 +"' and "+type+"_id='"+fox_id+"'and ao_no='"+ao_operid+"' and ao_ver='"+aover+"'";
    sql_data sqlbean = new sql_data();
    ResultSet rs = sqlbean.executeQuery(sql);
    boolean isin=false;
    try {
        if (rs.next()) {
        	isin=true;
        }rs.close();
    } catch (Exception ex) {
    	System.out.println("FoBeanDao.isin()����ʱ���?����Ϊ��"+ex);
    } finally {
    	sqlbean.closeConn();
    }
    return isin;
}
}