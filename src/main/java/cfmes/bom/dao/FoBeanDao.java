package cfmes.bom.dao;
import cfmes.bom.entity.Bom;
import cfmes.bom.entity.Dept;
import cfmes.bom.entity.Ao;
import cfmes.util.sql_data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoBeanDao {

	/**�ж�aomain���Ƿ��и�AO���*/
	public boolean isinFo(String product_id,String issue_num,String fo_no,String fover) {
			
	        String sql = "select * from work.fo where product_id='"+product_id+"' and issue_num='"+issue_num
	                     +"' and fo_no='"+fo_no+"' and fo_ver='"+fover+"'";//
	        sql_data sqlbean = new sql_data();
	        ResultSet rs = sqlbean.executeQuery(sql);
	        boolean isin=false;
	        try {
	            if (rs.next()) {
	            	isin=true;
	            }rs.close();
	        } catch (Exception ex) {
	        	System.out.println("FoBeanDao.isinFo()����ʱ���?����Ϊ��"+ex);
	        } finally {
	        	sqlbean.closeConn();
	        }
	        return isin;
	 }
	/**�жϸù������Ƿ��ظ���ӵ��������оߡ�ԭ���ϡ�����**/
	public boolean isin(String product_id,String issue_num,String fo_no,String fover,String fox_id,String fo_operid,String type) {
		
        String sql = "select * from work.fo_"+type+" where product_id='"+product_id+"' and issue_num='"+issue_num
                     +"' and fo_no='"+fo_no+"' and fo_ver='"+fover+"' and "+type+"_id='"+fox_id+"'and fo_operid='"+fo_operid+"'";
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
	
		/**�ж�FO_detail���Ƿ��и�FO���*/
	public boolean isinFoOp(String product_id,String issue_num,String fo_no,String fover,String oper_id) {
			
	        String sql = "select * from work.fo_detail where product_id='"+product_id+"' and issue_num='"+issue_num
	                     +"' and fo_no='"+fo_no+"' and fo_ver='"+fover+"' and fo_operid='"+oper_id+"'";
	        sql_data sqlbean = new sql_data();
	        ResultSet rs = sqlbean.executeQuery(sql);
	        boolean isin=false;
	        try {
	            if (rs.next()) {
	            	isin=true;
	            }rs.close();
	        } catch (Exception ex) {
	        	System.out.println("FoBeanDao.isinAoOp()����ʱ���?����Ϊ��"+ex);
	        } finally {
	        	sqlbean.closeConn();
	        }
	        return isin;
	 }
	
	public boolean isinFoOp2(String product_id,String issue_num,String fo_no,String fover) {
		
        String sql = "select * from work.fo_detail where product_id='"+product_id+"' and issue_num='"+issue_num
                     +"' and fo_no='"+fo_no+"' and fo_ver='"+fover+"'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        boolean isin=false;
        try {
            if (rs.next()) {
            	isin=true;
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("FoBeanDao.isinAoOp()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
 }
	
	//��FoOper_UpdateEc�����Ƿ��иù���
public boolean isinFoOp_Ec(String product_id,String issue_num,String fo_no,String fover,String oper_id) {
		
        String sql = "select * from work.fooper_updateec where product_id='"+product_id+"' and issue_num='"+issue_num
                     +"' and fo_no='"+fo_no+"' and fo_ver='"+fover+"' and fo_operid='"+oper_id+"'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        boolean isin=false;
        try {
            if (rs.next()) {
            	isin=true;
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("FoBeanDao.isinfoOp_updateec()����ʱ���?����Ϊ��"+ex);
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
	    	System.out.println("FoBeanDao.update()����ʱ���?����Ϊ��"+ex);
	    } finally {
	    	sqlbean.closeConn();
	    }
	}
	/**�ж�fo���Ƿ���FO*/
	public int FoNum(String product_id,String issue_num,String item_id) {
        String sql = "select count(fo_no) as a from meteor.fo where product_id='"+product_id+"' and issue_num='"+issue_num+"' and item_id='"+item_id+"'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        int isin = 0;
        try {
            if (rs.next()) {
            	isin = rs.getInt("a");
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("FoBeanDao.FoNum()����ʱ���?����Ϊ��"+ex);
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
        System.out.println("FoBeanDao.getBom()");
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
        	System.out.println("FoBeanDao.getBom()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	/**��BOM��������ϵ��б�*/
	public ArrayList getDept(int num,String father_dept_id) {
		String sql ="";
		if(father_dept_id.equals("")){
			sql = "select dept_id,dept_name from work.dept where dept_lc="+num+" order by dept_id desc";
		}else{
			sql = "select dept_id,dept_name from work.dept where dept_lc="+num+" and father_dept_id='"+father_dept_id+"' order by dept_id desc";
		}
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
	
	/**�Ӹ��Ա��л�õ����о�**/
	public ArrayList getCMT2(String product_id,String item_id,String issue_num,String fo_no,String fover,String fo_operid ) {
		String sql ="";
		sql_data sqlbean = new sql_data();
		 ArrayList list = new ArrayList();
		 String demp="";
		 String table,field="";
		for(int i=1;i<4;i++){
			if(i==1){field = "cut";table="fo_cut";}else if(i==2){field="measure";table="fo_measure";}else{field="tool";table="fo_tool";}
			sql = "select "+field+"_id"+ " from work."+table+" where product_id = '"+product_id+"' and item_id='"+
			item_id+"' and issue_num ='"+issue_num+"' and fo_no='"+fo_no+"' and fo_ver='"+fover+"' and fo_operid='"+fo_operid+"'";
        ResultSet rs = sqlbean.executeQuery(sql);
        System.out.print("he");
        try {
        	while (rs.next()) {
        		demp="";
            	demp = rs.getString(1);
                list.add(demp);
            }rs.close();
        }catch (Exception ex) {
        	System.out.println("FoBeanDao.getCMT2()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        }
        return list;
    }
	
	/**��fo_detail�����ȡ���**/
	public ArrayList getFooper(String product_type,String product_id,String issue_num,String item_id,String fo_no,String fover ) {
		String sql ="";
		sql_data sqlbean = new sql_data();
		 ArrayList list = new ArrayList();
			sql = "select fo_operid,fo_opname from work.fo_detail where product_type = '"+product_type+"' and product_id = '"+product_id+"' and issue_num = '"+issue_num+"' and item_id = '"+item_id+
			       "' and fo_no = '"+fo_no+"' and fo_ver = '"+fover+"' order by fo_operid desc";
		        ResultSet rs = sqlbean.executeQuery(sql);
		        try {
		        	while (rs.next()) {
		        		Dept dept = new Dept();
		        		dept.setDept_id(rs.getString(1));
		        		dept.setDept_name(rs.getString(2));
		                list.add(dept);
		            }rs.close();
        }catch (Exception ex) {
        	System.out.println("FoBeanDao.getCMT2()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }

	/**��DEPT���*/
	public String getDept_name(String dept_id) {
		String dept_name="";
		if(!dept_id.equals("") && null!=dept_id){
			String sql = "select dept_name from work.dept where dept_id='"+dept_id+"'";
	        sql_data sqlbean = new sql_data();
	        ResultSet rs = sqlbean.executeQuery(sql);
	        try {
	        	if (rs.next()) {
	        		dept_name=rs.getString(1);//System.out.println(dept_name);
	            }else{
	            	dept_name = dept_id;
	            }
	        	rs.close();
	        }catch (Exception ex) {
	        	System.out.println("FoBeanDao.getDept_name(String dept_id)����ʱ���?����Ϊ��"+ex);
	        } finally {
	        	sqlbean.closeConn();
	        }
		}
        return dept_name;
    }
}
