package cfmes.bom.dao;

import java.sql.ResultSet;
import java.util.ArrayList;


import cfmes.bom.entity.BomRecord;
import cfmes.util.sql_data;

public class MbomBeanDao {
	/**��MBOMѡ�����BomList ����BomTreeDao*/
	public ArrayList getBomList(String product_id,int level_id,String issue_num) {
		String level=level_id+"";
		product_id = product_id.trim();
		issue_num = issue_num.trim();
		if(level.length()==1){level="0"+level;}
        String sql = "SELECT * from work.mbom "+"where product_id='"+ product_id
	         +"' and level_id='"+level+"' and issue_num = '"+issue_num+"'";
        
        System.out.println("MbomBeanDao.java中的sql语句为："+sql);
        
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        ArrayList list = new ArrayList();
        
        try {
        	while (rs.next()) {
        		
                BomRecord mbom = new BomRecord();
                mbom.SetProduct_id(rs.getString("product_id"));
                mbom.SetLevel_id(rs.getString("level_id"));
                mbom.SetXid(rs.getString("node_id"));
                mbom.SetId(rs.getString("id"));
                mbom.SetFxid(rs.getString("father_node_id"));
                mbom.SetFid(rs.getString("fid"));
                mbom.SetIssue_num(rs.getString("issue_num"));
                mbom.SetName(rs.getString("node_name"));
                mbom.SetNum(rs.getString("node_num"));
                mbom.SetMemo1(rs.getString("memo1"));
                mbom.SetMemo2(rs.getString("memo2"));
                mbom.SetMemo3(rs.getString("memo3"));
                mbom.SetMemo4(rs.getString("memo4"));
                
           
                
                System.out.println("MbomBeanDao.java中："+"  product_id==="+mbom.getproduct_id()
                		+"  level_id==="+mbom.getlevel_id()
                		+"  node_id==="+mbom.getxid()
                		+"  id==="+mbom.getid()
                		+"  father_node_id==="+mbom.getfxid()
                		+"  fid==="+mbom.getfid()
                		+"  issue_num==="+mbom.getissue_num()
                		+"  node_name==="+mbom.getname()
                		+"  node_num==="+mbom.getnum()
                		+"  memo1==="+mbom.getmemo1()
                		+"  memo2==="+mbom.getmemo2()
                		+"  memo3==="+mbom.getmemo3()
                		+"  memo4==="+mbom.getmemo4());
                
                
        
                list.add(mbom);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("mBomBeanDao.getBomList()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	
	/**��NMBOMѡ�����BomList ����BomTreeDao*/
	public ArrayList getNBomList(String product_id,int level_id,String issue_num) {
		String level=level_id+"";
		product_id = product_id.trim();
		issue_num = issue_num.trim();
		if(level.length()==1){level="0"+level;}
        String sql = "SELECT * from work.nmbom "+"where product_id='"+ product_id
	         +"' and level_id='"+level+"' and issue_num = '"+issue_num+"' ";
        
        System.out.println("mBomBeanDao.getNBomList()中的sql语句是："+sql);
        
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        ArrayList list = new ArrayList();
        
        try {
        	while (rs.next()) {
        		
                BomRecord mbom = new BomRecord();
                mbom.SetProduct_id(rs.getString("product_id"));
                mbom.SetLevel_id(rs.getString("level_id"));
                mbom.SetXid(rs.getString("node_id"));
                mbom.SetId(rs.getString("id"));
                mbom.SetFxid(rs.getString("father_node_id"));
                mbom.SetFid(rs.getString("fid"));
                mbom.SetIssue_num(rs.getString("issue_num"));
                mbom.SetName(rs.getString("node_name"));
                mbom.SetNum(rs.getString("node_num"));
                mbom.SetMemo1(rs.getString("memo1"));
                mbom.SetMemo2(rs.getString("memo2"));
                mbom.SetMemo3(rs.getString("memo3"));
                mbom.SetMemo4(rs.getString("memo4"));
      
                System.out.println("MbomBeanDao.java中getNBomList："+"  product_id==="+mbom.getproduct_id()
                		+"  level_id==="+mbom.getlevel_id()
                		+"  node_id==="+mbom.getxid()
                		+"  id==="+mbom.getid()
                		+"  father_node_id==="+mbom.getfxid()
                		+"  fid==="+mbom.getfid()
                		+"  issue_num==="+mbom.getissue_num()
                		+"  node_name==="+mbom.getname()
                		+"  node_num==="+mbom.getnum()
                		+"  memo1==="+mbom.getmemo1()
                		+"  memo2==="+mbom.getmemo2()
                		+"  memo3==="+mbom.getmemo3()
                		+"  memo4==="+mbom.getmemo4());
          
                list.add(mbom);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("mBomBeanDao.getBomList()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	
	 /**�õ�����LevelId*/
	public int getMaxLevelId(String product_id) {
		int t_value = 0;
		product_id=product_id.trim();
        String sql= "SELECT distinct level_id  FROM work.mbom where product_id='"+product_id+"'";
		sql_data sqlbean = new sql_data();
		ResultSet rs = sqlbean.executeQuery(sql);
        try {
        	while(rs.next()) {
        		
        		int value=0;
        		value = Integer.parseInt(rs.getString("level_id"));
        		if(value>t_value){t_value=value;}else{}
        		
        		//System.out.println(rs.getString("level_id"));
        	
        	}rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getMaxLevelId()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return t_value;
    }
}
