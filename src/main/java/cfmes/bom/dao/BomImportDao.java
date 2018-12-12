package cfmes.bom.dao;

import cfmes.bom.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Hashtable;


import cfmes.util.PDM_Conn;
import cfmes.util.DealString;
import cfmes.util.sql_data;
import Bom.ImportBomBean;
//import cfmes.bom.entity.MDMWorkPlace;
//import cfmes.util.DealString;
import Bom.Bom_Bean;
public class BomImportDao {
	
/**�ж���ʱ���Ƿ����*/	
	public void TableExist(String tablename){
        sql_data sqlbean = new sql_data();
        ResultSet rs = null;
        try {
        	rs=sqlbean.executeQuery("select * from tab where tname='"+tablename+"'");
        	if(rs.next()){
        		DropTable(tablename);
			}rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.TableExist()����ʱ���?����Ϊ��"+ex);
        } finally {
        		sqlbean.closeConn();
        }
    }
/**ɾ����ʱ��*/
	public void DropTable(String tablename){
        sql_data sqlbean = new sql_data();
        try {
        	sqlbean.executeUpdate("DROP TABLE meteor."+tablename+"");
        } catch (Exception ex) {
        	System.out.println("BomImportDao.DropTable()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
/**������ʱ��*/
	public void CreatTable(String sql){
        sql_data sqlbean = new sql_data();
        try {
        	sqlbean.executeUpdate(sql);
        } catch (Exception ex) {
        	System.out.println("BomImportDao.CreatTable()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	/**�ж�pbom�����һ�������PDM�� PART_ROUTE ���Ƿ�������*/
	public boolean havechild(String PRODUCTCODE,String CHILDITEM,String STARTPLANENO,String ENDPLANENO){
		boolean havechild=false;
		PDM_Conn pbombean= new PDM_Conn();
		//=null;
		try{
			ResultSet rs_have =pbombean.executeQuery("select * from EXCHANGE.PART_ROUTE where PRODUCTCODE='"+PRODUCTCODE
					+"' and PARENTITEM='"+CHILDITEM+"' and STARTPLANENO='"+STARTPLANENO+"' and ENDPLANENO='"+ENDPLANENO+"'");
			if(rs_have.next()){
				havechild=true;
			}rs_have.close();
		}catch (SQLException SqlE) {
			SqlE.printStackTrace();
		}catch(Exception e){
			System.out.println("BomImportDao.havechild()����ʱ���?����Ϊ��"+e);
		}finally{
			 pbombean.closeConn();
		 }
		return havechild;
	}
	/**�ж������һ�������MBOM�� T_WORKPLACE ���Ƿ�������*/
	public boolean havechild(MDMWorkPlace MDMwork){
		boolean havechild=false;
		PDM_Conn pbombean= new PDM_Conn();
		//=null;
		String sql = "select * from EXCHANGE.T_MDMWORKPLACE where PRODUCTCODE='"+MDMwork.getFlight_type()
        +"' and PARENTPLACE='"+MDMwork.getChildplace()+"' and STARTPLANENO="+MDMwork.getStartplaneno()
        +" and ENDPLANENO="+MDMwork.getEndplaneno()+"";
		//System.out.println(sql);
		try{
			ResultSet rs_have =pbombean.executeQuery(sql);
			if(rs_have.next()){
				havechild=true;
			}rs_have.close();
		}catch (SQLException SqlE) {
			SqlE.printStackTrace();
		}catch(Exception e){
			System.out.println("BomImportDao.havechild(MDMWorkPlace)����ʱ���?����Ϊ��"+e);
		}finally{
			 pbombean.closeConn();
		 }
		return havechild;
	}
	/**�ж������һ�������MBOM�� T_MDMAO ���Ƿ�������*/
	public boolean havechild(MDMAO MDMAO){
		boolean havechild=false;
		PDM_Conn pbombean= new PDM_Conn();
		//=null;
		String sql = "select * from EXCHANGE.T_MDMAO where PRODUCTCODE='"+MDMAO.getFlight_type()
        +"' and WORKPLACECODE='"+MDMAO.getWorkplacecode()+"' and PARENTAOCODE='"+MDMAO.getChildaocode()
        +"' and STARTPLANENO='"+MDMAO.getStartplaneno()+"' and ENDPLANENO='"+MDMAO.getEndplaneno()+"'";
		try{
			ResultSet rs_have=pbombean.executeQuery(sql);
			if(rs_have.next()){
				havechild=true;
			}rs_have.close();
		}catch (SQLException SqlE) {
			SqlE.printStackTrace();
		}catch(Exception e){
			System.out.println("BomImportDao.havechild(MDMAO)����ʱ���?����Ϊ��"+e);
		}finally{
			 pbombean.closeConn();
		 }
		return havechild;
	}
	/**�ж�issue_deploy���Ƿ��д����*/
	public String isin_Issue(String flight_type,String product_id,String lot,String sortie){
		sql_data sqlbean=new sql_data();
		String sql = "select * from meteor.ISSUE_DEPLOY where flight_type='"+flight_type+"' AND product_id='"+product_id
        +"' AND lot='"+lot+"' and b_sortie <= "+sortie+" and e_sortie >= "+sortie+"";
		ResultSet rs = sqlbean.executeQuery(sql);
		String isin = "";
		try{
			if(rs.next()){
				isin = rs.getString("b_sortie")+"--"+rs.getString("e_sortie");
			}rs.close();
		}catch(Exception e){
			System.out.println("BomImportDao.isin_Issue()����ʱ���?����Ϊ��"+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return isin;
	}
	/**�ж�issue_deploy���Ƿ��д����*/
	public boolean isin_Issue(String flight_type,String product_id,String lot){
		sql_data sqlbean=new sql_data();
		String sql = "select * from meteor.ISSUE_DEPLOY where flight_type='"+flight_type+"' AND product_id='"+product_id
        +"' AND lot='"+lot+"'";
		ResultSet rs_have = sqlbean.executeQuery(sql);
		boolean isin = false;
		try{
			if(rs_have.next()){
				isin = true;
			}rs_have.close();
		}catch(Exception e){
			System.out.println("BomImportDao.isin_Issue()����ʱ���?����Ϊ��"+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return isin;
	}
	/**�ж�item���Ƿ��д�����,��û�������*/
//	public void isin_Item(String CHILDITEM,String ITEMNAME){
//		sql_data sqlbean=new sql_data();
//		ResultSet rs_have=null;
//		try{
//			rs_have=sqlbean.executeQuery("select * from meteor.ITEM where itemid='"+CHILDITEM+"'");
//			if(!rs_have.next()){
//				insert_Item( CHILDITEM, ITEMNAME);
//			}rs_have.close();
//		}catch (SQLException SqlE) {
//			SqlE.printStackTrace();
//		}catch(Exception e){
//			System.out.println("BomImportDao.haveItem()����ʱ���?����Ϊ��"+e);
//		}finally{
//			sqlbean.closeConn(); 
//		}
//	}
	/**�ж�item���Ƿ��д�����,��û�������*/
//	public void insert_Item(String CHILDITEM,String ITEMNAME){
//		sql_data sqlbean=new sql_data();
//		try{
//			sqlbean.executeUpdate("insert into meteor.item (itemid,item_name) values('"+CHILDITEM+"','"+ITEMNAME+"')");
//		}catch(Exception e){
//			System.out.println("BomImportDao.haveItem()����ʱ���?����Ϊ��"+e);
//		}finally{
//			sqlbean.closeConn(); 
//		}
//	}
	/**�ж�item���Ƿ��д�����,��û�������*/
	public void isin_Item(String CHILDITEM,String ITEMNAME,String ITEMTYPE){
		sql_data sqlbean=new sql_data();
		ResultSet rs=null;
		try{
			rs=sqlbean.executeQuery("select itemid,item_typeid,item_name from meteor.ITEM where itemid='"+CHILDITEM+"'");
			if(!rs.next()){
				insert_Item(CHILDITEM,ITEMNAME,ITEMTYPE);
			}else if(!ITEMTYPE.equals(rs.getString("item_typeid")) || !ITEMNAME.equals(rs.getString("item_name"))){
				if(!ITEMTYPE.equals(rs.getString("item_typeid"))){
					update_Item(CHILDITEM,ITEMTYPE,"item_typeid");
				}else{
					update_Item(CHILDITEM,ITEMNAME,"item_name");
				}
			}
			rs.close();
		}catch (SQLException SqlE) {
			SqlE.printStackTrace();
		}catch(Exception e){
			System.out.println("BomImportDao.isin_Item()����ʱ���?����Ϊ��"+e);
		}finally{
			sqlbean.closeConn();  
		}
	}
	/**�ж�item���Ƿ��д�����,,��û������룬�����������޸���������*/
//	public void isin_Item2(String CHILDITEM,String ITEMNAME,String ITEMTYPE){
//		sql_data sqlbean=new sql_data();
//		ResultSet rs_have=null;
//		try{
//			rs_have=sqlbean.executeQuery("select * from meteor.ITEM where itemid='"+CHILDITEM+"'");
//			if(!rs_have.next()){
//				insert_Item(CHILDITEM,ITEMNAME,ITEMTYPE);
//			}else if(!ITEMTYPE.equals(rs_have.getString("item_typeid"))){
//				update_Item(CHILDITEM,ITEMTYPE);
//			}
//			rs_have.close();
//		}catch (SQLException SqlE) {
//			SqlE.printStackTrace();
//		}catch(Exception e){
//			System.out.println("BomImportDao.isin_Item2()����ʱ���?����Ϊ��"+e);
//		}finally{
//			sqlbean.closeConn(); 
//		}
//	}
	/**���빤װ���� */
	public void insert_Tool(String sql,String sql2){
		sql_data sqlbean=new sql_data();
		ResultSet rs = sqlbean.executeQuery(sql);
		try{
			if(!rs.next()){
				sqlbean.executeUpdate(sql2);
			}rs.close();
		}catch(Exception e){
			System.out.println("BomImportDao.insert_Tool()����ʱ���?����Ϊ��"+e);
		}finally{
			sqlbean.closeConn(); 
		}
	}
	/**��������*/
	public void insert_Item(String CHILDITEM,String ITEMNAME,String ITEMTYPE){
		sql_data sqlbean=new sql_data();
		try{
			sqlbean.executeUpdate("insert into meteor.item (itemid,item_name,item_typeid) values('"+CHILDITEM+"','"+ITEMNAME+"','"+ITEMTYPE+"')");
		}catch(Exception e){
			System.out.println("BomImportDao.haveItem()����ʱ���?����Ϊ��"+e);
		}finally{
			sqlbean.closeConn(); 
		}
	}
	/**��������*/
	public void update_Item(String CHILDITEM,String ITEM,String TYPE){
		sql_data sqlbean=new sql_data();
		try{
			sqlbean.executeUpdate("update meteor.item set "+TYPE+"='"+ITEM+"' where itemid='"+CHILDITEM+"'");
		}catch(Exception e){
			System.out.println("BomImportDao.haveItem()����ʱ���?����Ϊ��"+e);
		}finally{
			sqlbean.closeConn(); 
		}
	}
	/**�ж�pbom���Ƿ�����ݴ���*/
	public boolean pbom_notnull(){
		sql_data sqlbean=new sql_data();
		ResultSet rs1 = sqlbean.executeQuery("select * from meteor.pbom");
		boolean status=false;
		try{
			if(rs1.next()){
				status=true;
			}rs1.close();
		}catch(Exception e){
			System.out.println("BomImportDao.temp_bom_exist()����ʱ���?����Ϊ��"+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return status;
	}
	/**ɾ����е����*/
	public void delTable(String sql){
        sql_data sqlbean = new sql_data();
        try {
        	sqlbean.executeUpdate(sql);
        } catch (Exception ex) {
        	System.out.println("BomImportDao.delTable()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	/**�õ�issue���� ����������*/
	public int Issue_deployNum(String flight_type,String product_id){
		sql_data sqlbean=new sql_data();
		ResultSet rs = sqlbean.executeQuery("select count(distinct lot) as a from meteor.issue_deploy where flight_type='"+flight_type+"' and product_id='"+product_id+"'");
		int num = 0;
		try{
			if(rs.next()){
				num=rs.getInt("a");
			}rs.close();
		}catch(Exception e){
			System.out.println("BomImportDao.Issue_deployNum()����ʱ���?����Ϊ��"+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return num;
	}
	/**Issue_Num�õ�100Ϊ������ʽ*/
	public String getIssue_Num(String product_id, String issue){
		sql_data sqlbean=new sql_data();
		int p = Integer.parseInt(issue);             //�õ��汾λ��
	       String issue_like="";
	       for(int i=1;i<=p;i++){       //���� issue_numֵ
		        if(i==p){
		        	issue_like = issue_like+'1';
		        }if(i<p){
		        	issue_like = issue_like+'_';
		        }
		    }
		ResultSet rs = sqlbean.executeQuery("select issue_num from meteor.ebom where product_id='"+product_id+"' and item_id='"+product_id+"' and level_id='01' and issue_num like '"+issue_like+"%'");
		String issue_num = "";
		try{
			if(rs.next()){
				issue_num = rs.getString("issue_num");
			}rs.close();
		}catch(Exception e){
			System.out.println("BomImportDao.getIssue_Num()����ʱ���?����Ϊ��"+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return issue_num;
	}
	/**�����ɾ����BOM*/
	public void delTable(String flight_type,String product_id,String lot){
		Bom bom = new Bom();
		sql_data sqlbean = new sql_data();
		IssueBeanDao issuebeandao = new IssueBeanDao();
        String sql= "select * from meteor.issue_deploy where flight_type='"+flight_type+"' and product_id='"+product_id+"' and lot='"+lot+"'";
        ResultSet rs = sqlbean.executeQuery(sql);
        String issue_num_new ="";//�°汾��
        Double isZero1=0.0;
        Double isZero2=0.0;
        try {
        	while (rs.next()){
        		delTable("delete from meteor.mater_bom where product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");
        		delTable("delete from meteor.hmater_bom where product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");
        		delTable("delete from meteor.tool_bom where product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");
        		delTable("delete from meteor.aomain where flighttype='"+flight_type+"' and productid='"+product_id+"' and issue_num='"+rs.getString("issue_num")+"'");
        		delTable("delete from meteor.ao_oper where flighttpye='"+flight_type+"' and produceid='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");
        		delTable("delete from meteor.fo_detail where flight_type='"+flight_type+"' and product_id='"+product_id +"' and issue_num = '"+rs.getString("issue_num")+"'");
      		    delTable("delete from meteor.fo where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");
        		
      		    delTable("delete from meteor.T_OPPROEQUIP where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");//FO����װ
				delTable("delete from meteor.T_MDMWORKPLACE where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");//��λ�ṹ
				delTable("delete from meteor.T_MDMAO where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");//��λAO�ṹ
				delTable("delete from meteor.T_AOSTANDARDPART where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");//AO��׼��
				delTable("delete from meteor.T_AOAUXMATLIST where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");//AO����
				delTable("delete from meteor.T_AOPROEQUIPLIST where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");//AO����װ
				delTable("delete from meteor.T_AOOPPROEQUIP where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");//AO��װ
				delTable("delete from meteor.T_AOREQUIREPART where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");//AO�������
				
      		    
      		    String issue_num = getIssue_Num(product_id,rs.getString("issue_pos"));
        		//int m = 
        		String issue_pos = getIssue_Num(product_id,rs.getString("issue_pos"));//�õ�100Ϊ������ʽ
        		//����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������
        		int p = Integer.parseInt(issue_pos);//Ҫ���ݵİ汾λ��
 			    String issue_like="";
 			    //��Ҫ���ݵİ汾λ�����ò�ѯ����issue_numֵ
 		        for(int i=1;i<=p;i++){       
 			        if(i==p){
 			        	issue_like = issue_like+'1';
 			        }if(i<p){
 			        	issue_like = issue_like+'_';
 			        }
 			    }
                //�õ��б?ѭ���԰汾�����µ�λ���Ͻ��б�0�仯��
 		        ArrayList array = issuebeandao.getBomList(product_id,issue_like);
 			    if(array.size()!=0 && array!=null){
 					for(int j = 0;j<array.size();j++){
 						bom = (Bom)array.get(j);
 	                    StringBuffer buf   = new StringBuffer(bom.getIssue_num());
 	                    buf.replace(p-1,p,"0");
 	                    issue_num_new = buf.toString();//��0
 	                    //isZero = new Double(Double.parseDouble(issue_num_new));
 	                    String t1=issue_num_new.substring(0, 50);
	                    String t2=issue_num_new.substring(50,99);
	                    //System.out.println(t1+t2);
	                    isZero1 = new Double(Double.parseDouble(t1));
	                    isZero2 = new Double(Double.parseDouble(t2));
	                    if(isZero1==0 && isZero2==0){
 	                    	issuebeandao.update("delete from meteor.ebom WHERE product_id='"+product_id
 	 	 			                  +"' and item_id='"+bom.getItem_id()+"' and issue_num ='"+bom.getIssue_num()+"' and level_id='"+bom.getLevel_id()
 	 	                              +"' and father_item_id='"+bom.getFather_item_id()+"' and fid='"+bom.getFid()+"' and id='"+bom.getId()+"'");
 	                    }else{
 	                    	issuebeandao.update("update meteor.ebom set issue_num='"+issue_num_new+"' where product_id='"+ product_id
 	 	                  		      +"' and item_id='"+bom.getItem_id()+"' and issue_num ='"+bom.getIssue_num()+"' and level_id='"+bom.getLevel_id()
 	 	                              +"' and father_item_id='"+bom.getFather_item_id()+"' and fid='"+bom.getFid()+"' and id='"+bom.getId()+"'");
 	                    }
 					}
 			    }
        		//bombean.delBom(flight_type,issue_num, rs.getString("issue_num"), rs.getString("issue_pos"), product_id, product_id, "#", "1", "1", "1");
        	    //System.out.println(m+"m33333333333333");
        	}rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.delTable(String flight_type,String product_id,String lot)����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	/**�� flight_type, product_id, lot, sortieɾ��һ��BOM*/
	public String delTable(String flight_type,String product_id,String lot,String sortie){
		Bom bom = new Bom();
		sql_data sqlbean = new sql_data();
		IssueBeanDao issuebeandao = new IssueBeanDao();
        String sql= "select * from meteor.issue_deploy where flight_type='"+flight_type+"' and product_id='"+product_id
                    +"' and lot='"+lot+"'  and b_sortie <= "+sortie+" and e_sortie >= "+sortie+"";
        ResultSet rs = sqlbean.executeQuery(sql);
        String issue_name="";
        String issue_num_new ="";//�°汾��
        Double isZero1=0.0;
        Double isZero2=0.0;
        try {
        	if (rs.next()){
        		delTable("delete from meteor.mater_bom where product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");
        		delTable("delete from meteor.hmater_bom where product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");
        		delTable("delete from meteor.tool_bom where product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");
        		delTable("delete from meteor.aomain where flighttype='"+flight_type+"' and productid='"+product_id+"' and issue_num='"+rs.getString("issue_num")+"'");
      		    delTable("delete from meteor.ao_oper where flighttpye='"+flight_type+"' and produceid='"+product_id+"' and issue_num='"+rs.getString("issue_num")+"'");
      		    delTable("delete from meteor.fo_detail where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num='"+rs.getString("issue_num")+"'");
    		    delTable("delete from meteor.fo where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num='"+rs.getString("issue_num")+"'");
      		    
    		    delTable("delete from meteor.T_OPPROEQUIP where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");//FO����װ
				delTable("delete from meteor.T_MDMWORKPLACE where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");//��λ�ṹ
				delTable("delete from meteor.T_MDMAO where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");//��λAO�ṹ
				delTable("delete from meteor.T_AOSTANDARDPART where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");//AO��׼��
				delTable("delete from meteor.T_AOAUXMATLIST where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");//AO����
				delTable("delete from meteor.T_AOPROEQUIPLIST where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");//AO����װ
				delTable("delete from meteor.T_AOOPPROEQUIP where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");//AO��װ
				delTable("delete from meteor.T_AOREQUIREPART where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_num = '"+rs.getString("issue_num")+"'");//AO�������
				
				delTable("delete from meteor.issue_deploy where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_pos='"+rs.getString("issue_pos")+"'");
    		    issue_name = rs.getString("issue_num");
    		    //System.out.println("issue_num:"+issue_name);
    		    //String issue_num = getIssue_Num(product_id,rs.getString("issue_pos"));//�õ�100Ϊ������ʽ
    		    //System.out.println("issue_pos:"+issue_pos);
        		//����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������
        		int p = Integer.parseInt(rs.getString("issue_pos"));//Ҫ���ݵİ汾λ��
        		//System.out.println("issue_pos:"+rs.getString("issue_pos"));
 			    String issue_like="";
 			    //��Ҫ���ݵİ汾λ�����ò�ѯ����issue_numֵ
 		        for(int i=1;i<=p;i++){       
 			        if(i==p){
 			        	issue_like = issue_like+'1';
 			        }if(i<p){
 			        	issue_like = issue_like+'_';
 			        }
 			    }
                //�õ��б?ѭ���԰汾�����µ�λ���Ͻ��б�0�仯��
 		        ArrayList array = issuebeandao.getBomList(product_id,issue_like);
 			    if(array.size()!=0 && array!=null){
 					for(int j = 0;j<array.size();j++){
 						bom = (Bom)array.get(j);
 	                    StringBuffer buf   = new StringBuffer(bom.getIssue_num());
 	                    buf.replace(p-1,p,"0");
 	                    issue_num_new = buf.toString();//��0
 	                    //System.out.println("issue_num_new:"+issue_num_new);
 	                    String t1=issue_num_new.substring(0, 50);
 	                    String t2=issue_num_new.substring(50,99);
 	                    
 	                    isZero1 = new Double(Double.parseDouble(t1));
 	                    isZero2 = new Double(Double.parseDouble(t2));
 	                    if(isZero1==0 && isZero2==0){
 	                    	issuebeandao.update("delete from meteor.ebom WHERE product_id='"+product_id
 	 	 			                  +"' and item_id='"+bom.getItem_id()+"' and issue_num ='"+bom.getIssue_num()+"' and level_id='"+bom.getLevel_id()
 	 	                              +"' and father_item_id='"+bom.getFather_item_id()+"' and fid='"+bom.getFid()+"' and id='"+bom.getId()+"'");
 	                    }else{
 	                    	issuebeandao.update("update meteor.ebom set issue_num='"+issue_num_new+"' where product_id='"+ product_id
 	 	                  		      +"' and item_id='"+bom.getItem_id()+"' and issue_num ='"+bom.getIssue_num()+"' and level_id='"+bom.getLevel_id()
 	 	                              +"' and father_item_id='"+bom.getFather_item_id()+"' and fid='"+bom.getFid()+"' and id='"+bom.getId()+"'");
 	                    }
 					}
 			    }  
//        		bombean.delBom(flight_type,issue_num, rs.getString("issue_num"), rs.getString("issue_pos"), product_id, product_id, "#", "1", "1", "1");
        		//delTable("delete from meteor.issue_deploy where flight_type='"+flight_type+"' and product_id='"+product_id+"' and issue_pos='"+rs.getString("issue_pos")+"'");
        	}rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.delTable(String flight_type,String product_id,String lot,String sortie)����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return issue_name;
    }
	/**ɾ��TempBom���е����*/
	public void delTable(TempBom tempbom) {
        sql_data sqlbean = new sql_data();
        try {
        	sqlbean.executeUpdate("delete from meteor.temp_bom where PRODUCTCODE='"+tempbom.getPRODUCTCODE()
					+"' and PARENTITEM='"+tempbom.getPARENTITEM()+"' and CHILDITEM='"+tempbom.getCHILDITEM()
					+"' and STARTPLANENO='"+tempbom.getSTARTPLANENO()+"' and ENDPLANENO='"+tempbom.getENDPLANENO()+"'");
        } catch (Exception ex) {
        	System.out.println("BomImportDao.delTable(TempBom )����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	/**ɾ��Pbom���е����*/
	public void delTable(Pbom pbom) {
        sql_data sqlbean = new sql_data();
        try {
        	sqlbean.executeUpdate("delete from meteor.pbom where PRODUCTCODE='"+pbom.getPRODUCTCODE()
					+"' and PARENTITEM='"+pbom.getPARENTITEM()+"' and CHILDITEM='"+pbom.getCHILDITEM()
					+"' and LEVEL_ID='"+pbom.getLEVEL_ID()+"' and STARTPLANENO='"+pbom.getSTARTPLANENO()
					+"' and ENDPLANENO='"+pbom.getENDPLANENO()+"'");
        } catch (Exception ex) {
        	System.out.println("BomImportDao.delTable(Pbom )����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
/**��PDM��PART_ROUTE����� flight_type, product_id, lot*/
	public ArrayList getPART_ROUTE(String flight_type,String product_id,String lot) {
		String sql = "select PARENTITEM,CHILDVERSION,STARTPLANENO,ENDPLANENO,PERQUITY,ITEMNAME,ROUTE"
				+" from EXCHANGE.PART_ROUTE where PRODUCTCODE='"+flight_type
            +"' and CHILDITEM='"+product_id+"' and (STARTPLANENO LIKE '_' or STARTPLANENO LIKE '__')"
            +" order by STARTPLANENO";;
		int n = Integer.parseInt(lot);
//		if(n==0){
//			sql = "select PARENTITEM,CHILDVERSION,STARTPLANENO,ENDPLANENO,PERQUITY,ITEMNAME,ROUTE"
//				+" from EXCHANGE.PART_ROUTE where PRODUCTCODE='"+flight_type
//            +"' and CHILDITEM='"+product_id+"' and (STARTPLANENO LIKE '_' or STARTPLANENO LIKE '__')"
//            +" order by STARTPLANENO";
//		}
		if(n!=0){
			sql = "select PARENTITEM,CHILDVERSION,STARTPLANENO,ENDPLANENO,PERQUITY,ITEMNAME,ROUTE"
				+" from EXCHANGE.PART_ROUTE where PRODUCTCODE='"+flight_type
            +"' and CHILDITEM='"+product_id+"' and STARTPLANENO LIKE '"+lot+"__' "
            +" order by STARTPLANENO";
		}
//        String sql = "select * from EXCHANGE.PART_ROUTE where PRODUCTCODE='"+flight_type
//                      +"' and CHILDITEM='"+product_id+"' and STARTPLANENO LIKE '"+lot+"__'  order by STARTPLANENO";
        PDM_Conn pbombean= new PDM_Conn();
        ResultSet rs = pbombean.executeQuery(sql);
        ArrayList list = new ArrayList();
        DealString ds = new DealString();
        try {
            while (rs.next()) {
                TempBom tempbom = new TempBom();
                tempbom.setPRODUCTCODE(flight_type);
                tempbom.setPARENTITEM(ds.toString(rs.getString("PARENTITEM")));
                tempbom.setCHILDITEM(product_id);
                tempbom.setCHILDVERSION(ds.toString(rs.getString("CHILDVERSION")));
                tempbom.setSTARTPLANENO(ds.toString(rs.getString("STARTPLANENO")));
                tempbom.setENDPLANENO(ds.toString(rs.getString("ENDPLANENO")));
                tempbom.setPERQUITY(ds.toString(rs.getString("PERQUITY")));
                tempbom.setITEMNAME(ds.toString(rs.getString("ITEMNAME")));
                tempbom.setROUTE(ds.toString(rs.getString("ROUTE")));
                list.add(tempbom);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getPART_ROUTE()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
        return list;
    }
	/**��PDM��PART_ROUTE����� flight_type, product_id, lot, sortie*/
	public ArrayList getPART_ROUTE(String flight_type,String product_id,String lot,String sortie) {
		
		String planeno = lot+sortie;
		System.out.println("planeno"+planeno);
        String sql = "select PARENTITEM,CHILDVERSION,STARTPLANENO,ENDPLANENO,PERQUITY,ITEMNAME,ROUTE "
        	          +" from EXCHANGE.PART_ROUTE where PRODUCTCODE='"+flight_type+"' and CHILDITEM='"+product_id
        	          +"' and STARTPLANENO <= "+planeno+" and ENDPLANENO >= "+planeno+" and ROUTE like '%280%'";
        PDM_Conn pbombean= new PDM_Conn();
        ResultSet rs = pbombean.executeQuery(sql);
        ArrayList list = new ArrayList();
        DealString ds = new DealString();
        try {
            while (rs.next()) {
                TempBom tempbom = new TempBom();
                tempbom.setPRODUCTCODE(flight_type);
                tempbom.setPARENTITEM(ds.toString(rs.getString("PARENTITEM")));
                tempbom.setCHILDITEM(product_id);
                tempbom.setCHILDVERSION(ds.toString(rs.getString("CHILDVERSION")));
                tempbom.setSTARTPLANENO(ds.toString(rs.getString("STARTPLANENO")));
                tempbom.setENDPLANENO(ds.toString(rs.getString("ENDPLANENO")));
                tempbom.setPERQUITY(ds.toString(rs.getString("PERQUITY")));
                tempbom.setITEMNAME(ds.toString(rs.getString("ITEMNAME")));
                tempbom.setROUTE(ds.toString(rs.getString("ROUTE")));
                list.add(tempbom);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getPART_ROUTE(flight_type, product_id, lot, sortie)����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
        return list;
    }
	/**��PDM��PART_ROUTE������Ƿ��и���ε����lot*/
	public boolean isExistLot(String flight_type,String product_id,String lot) {
		String sql = "";
		int n = Integer.parseInt(lot);
		if(n==0){
			sql = "select STARTPLANENO from EXCHANGE.PART_ROUTE where PRODUCTCODE='"+flight_type
            +"' and CHILDITEM='"+product_id+"' and (STARTPLANENO LIKE '_' or STARTPLANENO LIKE '__')"
            +" and ROUTE like '%280%' order by STARTPLANENO";
		}else{
			sql = "select STARTPLANENO from EXCHANGE.PART_ROUTE where PRODUCTCODE='"+flight_type
            +"' and CHILDITEM='"+product_id+"' and STARTPLANENO LIKE '"+lot+"__' "
            +" and ROUTE like '%280%' order by STARTPLANENO";
		}
		//System.out.println(sql);
        PDM_Conn pbombean= new PDM_Conn();
        ResultSet rs = pbombean.executeQuery(sql);
        boolean isin = false;
        try {
            if (rs.next()) {
            	isin = true;
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.isExistLot()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
        return isin;
    }
	/**��TEMP_BOM�����*/
	public ArrayList getTempBom() {
		
        String sql = "select * from meteor.temp_bom";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        ArrayList list = new ArrayList();
        DealString ds = new DealString();
        try {
            while (rs.next()) {
                TempBom tempbom = new TempBom();
                tempbom.setPRODUCTCODE(ds.toString(rs.getString("PRODUCTCODE")));
                tempbom.setPARENTITEM(ds.toString(rs.getString("PARENTITEM")));
                tempbom.setCHILDITEM(ds.toString(rs.getString("CHILDITEM")));
                tempbom.setCHILDVERSION(ds.toString(rs.getString("CHILDVERSION")));
                tempbom.setSTARTPLANENO(ds.toString(rs.getString("STARTPLANENO")));
                tempbom.setENDPLANENO(ds.toString(rs.getString("ENDPLANENO")));
                tempbom.setPERQUITY(ds.toString(rs.getString("PERQUITY")));
                tempbom.setITEMNAME(ds.toString(rs.getString("ITEMNAME")));
                tempbom.setROUTE(ds.toString(rs.getString("ROUTE")));
                list.add(tempbom);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getTempBom()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	/**��PDM��PART_ROUTE��  ������ʱ��TEMP_BOM*/
	public void InputTempBom(TempBom tempbom) {
		
		sql_data sqlbean = new sql_data();
        try {
        	    sqlbean.executeUpdate("insert into meteor.temp_bom (PRODUCTCODE,PARENTITEM,CHILDITEM,CHILDVERSION,"
        	    		              +"STARTPLANENO,ENDPLANENO,PERQUITY,ITEMNAME,ROUTE)"
					+" values('"+tempbom.getPRODUCTCODE()+"','"+tempbom.getPARENTITEM()+"','"+tempbom.getCHILDITEM()
					+"','"+tempbom.getCHILDVERSION()+"','"+tempbom.getSTARTPLANENO()+"','"+tempbom.getENDPLANENO()
					+"','"+tempbom.getPERQUITY()+"','"+tempbom.getITEMNAME()+"','"+tempbom.getROUTE()+"')");
        } catch (Exception ex) {
        	System.out.println("BomImportDao.InputTempBom()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	/**�õ����Ͳ�Ʒ�¿������Сλ�ú�*/
	public int getMinIssue_pos(String flight_type,String product_id,int issue_pos) {
        int i = 0;
        String sql= "SELECT * FROM meteor.issue_deploy WHERE product_id='"+ product_id
                       +"' and flight_type='"+flight_type+"' and issue_pos='"+issue_pos+"'";
		sql_data sqlbean = new sql_data();
		ResultSet rs = sqlbean.executeQuery(sql);
        try {
        	if (!rs.next()) {
        		i = issue_pos;
        	}rs.close();
        	//System.out.println("BomBeanDao.getIssue_pos()��"+i);
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getIssue_pos()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return i;
    }
	/**��TEMP_BOM�����  ������ʱ��PBOM*/
	public void InputPbom(TempBom tempbom,int level_id,String fid) {
		String level=level_id+"";
		if(level.length()==1){level="0"+level;}
		sql_data sqlbean = new sql_data();
        try { 
        	sqlbean.executeUpdate("insert into meteor.pbom (PRODUCTCODE,PARENTITEM,CHILDITEM,LEVEL_ID,FID,CHILDVERSION,"
        			             +"STARTPLANENO,ENDPLANENO,PERQUITY,ITEMNAME,ROUTE) values ('"+tempbom.getPRODUCTCODE()
        			             +"','"+tempbom.getPARENTITEM()+"','"+tempbom.getCHILDITEM()+"','"+level+"','"+fid
        			             +"','"+tempbom.getCHILDVERSION()+"','"+tempbom.getSTARTPLANENO()+"','"+tempbom.getENDPLANENO()
					             +"','"+tempbom.getPERQUITY()+"','"+tempbom.getITEMNAME()+"','"+tempbom.getROUTE()+"')");
        } catch (Exception ex) {
        	System.out.println("BomImportDao.InputPbom()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	/**��TEMP_BOM�����  ������ʱ��ISSUE_DEPLOY*/
	public void InputISSUE_DEPLOY(String flight_type,String product_id,String lot,String b_sortie,String e_sortie,String issue_num,int i) {
		
		sql_data sqlbean = new sql_data();
        try { 
        	sqlbean.executeUpdate("INSERT INTO meteor.issue_deploy (flight_type,product_id,lot,b_sortie,e_sortie,issue_num,issue_pos)"
		               +" Values ('"+flight_type+"','"+product_id+"','"+lot+"','"+b_sortie+"','"+e_sortie+"','"+issue_num+"','"+i+"')");
        } catch (Exception ex) {
        	System.out.println("BomImportDao.InputISSUE_DEPLOY()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	/**��pbom�����*/
	public ArrayList getPbom() {
		
        String sql = "select * from meteor.pbom order by rownum desc";
        sql_data sqlbean = new sql_data();
        ResultSet rs     = sqlbean.executeQuery(sql);
        ArrayList list   = new ArrayList();
        DealString ds = new DealString();
        try {
            if (rs.next()) {
                Pbom pbom = new Pbom();
                
				pbom.setPRODUCTCODE(ds.toString(rs.getString("PRODUCTCODE")));
				pbom.setPARENTITEM(ds.toString(rs.getString("PARENTITEM")));
				pbom.setCHILDITEM(ds.toString(rs.getString("CHILDITEM")));
				pbom.setLEVEL_ID(ds.toString(rs.getString("LEVEL_ID")));
				pbom.setFID(ds.toString(rs.getString("FID")));
				pbom.setCHILDVERSION(ds.toString(rs.getString("CHILDVERSION")));
				pbom.setSTARTPLANENO(ds.toString(rs.getString("STARTPLANENO")));
				pbom.setENDPLANENO(ds.toString(rs.getString("ENDPLANENO")));
				pbom.setPERQUITY(ds.toString(rs.getString("PERQUITY")));
				pbom.setITEMNAME(ds.toString(rs.getString("ITEMNAME")));
				pbom.setROUTE(ds.toString(rs.getString("ROUTE")));
                list.add(pbom);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getPbom()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	/**��pbom�����*/
	public ArrayList getchild(String PRODUCTCODE,String CHILDITEM,String STARTPLANENO,String ENDPLANENO) {
		
        String sql = "select * from EXCHANGE.PART_ROUTE where PRODUCTCODE='"+PRODUCTCODE+"' and PARENTITEM='"+CHILDITEM
                                               +"' and STARTPLANENO='"+STARTPLANENO+"' and ENDPLANENO='"+ENDPLANENO+"'";
        PDM_Conn pbombean= new PDM_Conn();
        ResultSet rs     = pbombean.executeQuery(sql);
        ArrayList list   = new ArrayList();
        DealString ds = new DealString();
        try {
            while (rs.next()) {
            	TempBom tempbom = new TempBom();
                
            	tempbom.setPRODUCTCODE(ds.toString(rs.getString("PRODUCTCODE")));
            	tempbom.setPARENTITEM(ds.toString(rs.getString("PARENTITEM")));
            	tempbom.setCHILDITEM(ds.toString(rs.getString("CHILDITEM")));
//				pbom.setLEVEL_ID(rs.getString("LEVEL_ID"));
//				pbom.setFID(rs.getString("FID"));
            	tempbom.setCHILDVERSION(ds.toString(rs.getString("CHILDVERSION")));
            	tempbom.setSTARTPLANENO(ds.toString(rs.getString("STARTPLANENO")));
            	tempbom.setENDPLANENO(ds.toString(rs.getString("ENDPLANENO")));
            	tempbom.setPERQUITY(ds.toString(rs.getString("PERQUITY")));
            	tempbom.setITEMNAME(ds.toString(rs.getString("ITEMNAME")));
            	tempbom.setROUTE(ds.toString(rs.getString("ROUTE")));
                list.add(tempbom);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getchild()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
        return list;
    }
	/**����������DOC_EFFSTATE�е�FO��DOCNUMBER,DOCVERSION��*/
	public String getEFF(String PRODUCTCODE,String PARTNUMBER,String lot,String sortie,String DOCTYPE){
		String planeno = lot+sortie;
		String sql = "SELECT DOCNUMBER,DOCVERSION FROM EXCHANGE.DOC_EFFSTATE WHERE  PRODUCTCODE='"+PRODUCTCODE//+"' AND EFFSTSHIP >= "+STARTPLANENO
		              +"' AND EFFEDSHIP >= "+planeno+" AND PARTNUMBER='"+PARTNUMBER+"' ORDER by DOCVERSIONID desc";//AND DOCVERSIONID IN ( SELECT MAX(DOCVERSIONID)"
		              //+" FROM EXCHANGE.DOC_EFFSTATE WHERE PRODUCTCODE='"+PRODUCTCODE+"' AND EFFSTSHIP <= "+STARTPLANENO
		              //+" AND EFFEDSHIP >= "+ENDPLANENO+" AND PARTNUMBER='"+PARTNUMBER+"') ORDER by EFFSTSHIP desc";
		//System.out.println("getEFF:"+sql);
		PDM_Conn pbombean= new PDM_Conn();
        ResultSet rs     = pbombean.executeQuery(sql);
        String PLAN_ID="";
		try{
			if(rs.next()){
				if(DOCTYPE.equals("FO")){
					PLAN_ID=getFoPlanId(PRODUCTCODE,rs.getString("DOCNUMBER"),rs.getString("DOCVERSION"),PARTNUMBER);
				}else if(DOCTYPE.equals("AO")){
					PLAN_ID=getAoPlanId(PRODUCTCODE,rs.getString("DOCNUMBER"),rs.getString("DOCVERSION"),PARTNUMBER);
				}
			}rs.close();
		}catch(Exception e){
			System.out.println("BomImportDao.getEFF()����ʱ���?����Ϊ��"+e);
		}finally{
			pbombean.closeConn(); 
		}
		return PLAN_ID;
	}
	/**��DOCNUMBER,DOCVERSION��FO�����е�FO��PLAN_ID��*/
	public String getFoPlanId(String PRODUCTCODE,String DOCNUMBER,String DOCVERSION,String PARTNUMBER){
		String sql = "SELECT PLAN_ID FROM EXCHANGE.T_FOITEMINFO WHERE FO_NO='"+DOCNUMBER
		             +"' AND FOVERSION='"+DOCVERSION+"' AND ITEMCODE='"+PARTNUMBER+"'";
		PDM_Conn pbombean= new PDM_Conn();
        ResultSet rs     = pbombean.executeQuery(sql);
        String PLAN_ID="";//,d="";
		try{
			if(rs.next()){
				PLAN_ID = rs.getString("PLAN_ID")+"---"+DOCNUMBER;
			}rs.close();
		}catch(Exception e){
			System.out.println("BomImportDao.getFoPlanId()����ʱ���?����Ϊ��"+e);
		}finally{
			pbombean.closeConn(); 
		}
		return PLAN_ID;
	}
	/**��DOCNUMBER,DOCVERSION��AO�����е�AO��PLAN_ID��*/
	public String getAoPlanId(String PRODUCTCODE,String DOCNUMBER,String DOCVERSION,String PARTNUMBER){
		String sql = "SELECT PLAN_ID,PARTNO FROM EXCHANGE.T_ASSAOMAIN WHERE"// PRODUCTCODE='"+PRODUCTCODE
		          +" AO_NO='"+DOCNUMBER+"' AND AOVER='"+DOCVERSION+"' AND AOCODE='"+PARTNUMBER+"'";
		PDM_Conn pbombean= new PDM_Conn();
        ResultSet rs     = pbombean.executeQuery(sql);
        String PLAN_ID="";//,d="";
        DealString ds = new DealString();
		try{
			if(rs.next()){
				PLAN_ID = rs.getString("PLAN_ID")+"---"+ds.toString(rs.getString("PARTNO"))+"---"+DOCNUMBER;
			}rs.close();
		}catch(Exception e){
			System.out.println("BomImportDao.getAoPlanId()����ʱ���?����Ϊ��"+e);
		}finally{
			pbombean.closeConn(); 
		}
		return PLAN_ID;
	}
	/**��aocode��AO�����е�AO��PLAN_ID����������*/
	public String getPlanId_test(String DOCNUMBER){
		String sql = "SELECT PLAN_ID,PARTNO,AO_NO FROM EXCHANGE.T_ASSAOMAIN where AOCODE='"+DOCNUMBER
		              +"' order by aover desc";
		//System.out.println(sql);
		PDM_Conn pbombean= new PDM_Conn();
        ResultSet rs     = pbombean.executeQuery(sql);
        //System.out.println(sql);
        String PLAN_ID="";//,d="";
        DealString ds = new DealString();
		try{
			if(rs.next()){
				PLAN_ID = rs.getString("PLAN_ID")+"---"+ds.toString(rs.getString("PARTNO"))+"---"+ds.toString(rs.getString("AO_NO"));
			}rs.close();
		}catch(Exception e){
			System.out.println("BomImportDao.getAoPlanId()����ʱ���?����Ϊ��"+e);
		}finally{
			pbombean.closeConn(); 
		}
		return PLAN_ID;
	}
	/**��ѯitemtype�е�ID*/
	public String Item_type(String name){
		sql_data sqlbean=new sql_data();
		ResultSet rs=sqlbean.executeQuery("select item_typeid from meteor.ITEMTYPE where item_typedesc='"+name+"'");
		String item_typeid="";
		try{
			if(rs.next()){
				item_typeid = rs.getString("item_typeid");
			}rs.close();
		}catch (SQLException SqlE) {
			SqlE.printStackTrace();
		}catch(Exception e){
			System.out.println("BomImportDao.Item_type()����ʱ���?����Ϊ��"+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return item_typeid;
	}
	/**��PLAN_IDȥ��FO ��í����Ϣ*/
	public void getMd(String PLAN_ID,String PRODUCTCODE,String CHILDITEM,String STARTPLANENO,String ENDPLANENO,int level,String id) {
//		String level_id=level+"";
//		if(level_id.length()==1){level_id="0"+level_id;}
		String sql = "select * from EXCHANGE.T_STANDARDPART where PLAN_ID='"+PLAN_ID+"'";
		DealString ds = new DealString();
        PDM_Conn pbombean= new PDM_Conn();
        ResultSet rs     = pbombean.executeQuery(sql);
        try {
            while (rs.next()) {
            	System.out.println("getMd:"+sql);
            	isin_Item(ds.toString(rs.getString("STPART_NO")),ds.toString(rs.getString("STPART_NAME")),Item_type("��׼��"));//����meteor item���У�������������������
            	if(rs.getInt("STPART_TYPE")==3){// �����í�� �����tempbom��
            		TempBom tempbom = new TempBom();
                	tempbom.setPRODUCTCODE(PRODUCTCODE);
                	tempbom.setPARENTITEM(CHILDITEM);
                	tempbom.setCHILDITEM(ds.toString(rs.getString("STPART_NO")));
//                	tempbom.setLEVEL_ID(level+"");
//                	tempbom.setFID(id);
                	tempbom.setCHILDVERSION("");
                	tempbom.setSTARTPLANENO(STARTPLANENO);
                	tempbom.setENDPLANENO(ENDPLANENO);
                	tempbom.setPERQUITY(ds.toString(rs.getString("STPART_QTY")));
                	tempbom.setITEMNAME(ds.toString(rs.getString("STPART_NAME")));
                	InputPbom(tempbom,level,id);
            	}
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getMd()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
    }
	/**��PLAN_IDȥ��FO ��ԭ������Ϣ*/
	public void getYcl(String PLAN_ID,String product_id,String item_id,String father_item_id,int level,String fid,String id,String issue_num) {
		String level_id=level+"";
		if(level_id.length()==1){level_id="0"+level_id;}
        String sql = "select * from EXCHANGE.T_MATERIAL where PLAN_ID='"+PLAN_ID+"'";
        PDM_Conn pbombean = new PDM_Conn();
        Bom_Bean bom_bean = new Bom_Bean();
        ResultSet rs      = pbombean.executeQuery(sql);
        DealString ds = new DealString();
        try {
            while (rs.next()) {
            	//System.out.println("getYcl:"+sql);
            	isin_Item(rs.getString("MARLCODE"),rs.getString("MARLCODE"),Item_type("ԭ����"));//����meteor item����
            	Vector vect = new Vector();
            	vect.add("meteor.MATER_BOM");
    			vect.add(addVector("PRODUCT_ID",product_id,"CHAR")); 
    			vect.add(addVector("BOM_ITEM_ID",item_id,"CHAR"));
    			vect.add(addVector("FATHER_ITEM_ID",father_item_id,"CHAR"));
    			vect.add(addVector("ID",id,"CHAR"));
    			vect.add(addVector("FID",fid,"CHAR"));
    			vect.add(addVector("LEVEL_ID",level_id,"CHAR"));
    			vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
    			
    			vect.add(addVector("ITEM_ID",ds.toString(rs.getString("MARLCODE")),"CHAR"));
    			vect.add(addVector("MATERICAL_ID",ds.toString(rs.getString("MATRLTYPE")),"CHAR"));
    			vect.add(addVector("MATRLSPEC",ds.toString(rs.getString("MATRLSPEC")),"CHAR"));
    			vect.add(addVector("ITEM_NUM",ds.toString(rs.getString("MATRLSIZE")),"CHAR"));
    			vect.add(addVector("BLANKING_PER",ds.toString(rs.getString("BLANKING_PER")),"CHAR"));
    			vect.add(addVector("MARL_QULITYCODE",ds.toString(rs.getString("MARL_QULITYCODE")),"CHAR"));
    			bom_bean.insertRecord(vect);
            }rs.close();
            
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getYcl()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
    }
	/**��PLAN_IDȥ��FO �ĸ�����Ϣ*/
	public void getFl(String PLAN_ID,String product_id,String item_id,String father_item_id,int level,String fid,String id,String issue_num) {
		String level_id=level+"";
		if(level_id.length()==1){level_id="0"+level_id;}
        String sql = "select * from EXCHANGE.T_AUXMATLIST where PLAN_ID='"+PLAN_ID+"'";
        PDM_Conn pbombean = new PDM_Conn();
        Bom_Bean bom_bean = new Bom_Bean();
        ResultSet rs      = pbombean.executeQuery(sql);
        DealString ds = new DealString();
        try {
            while (rs.next()) {
            	isin_Item(rs.getString("AUXMAT_NO"),rs.getString("AUXMAT_NAME"),Item_type("����"));//����meteor item����
//            	Vector vect = new Vector();
//            	vect.add("meteor.HMATER_BOM");
//    			vect.add(addVector("PRODUCT_ID",product_id,"CHAR")); 
//    			vect.add(addVector("ITEM_ID",item_id,"CHAR"));
//    			vect.add(addVector("FATHER_ITEM_ID",father_item_id,"CHAR"));
//    			vect.add(addVector("ID",id,"CHAR"));
//    			vect.add(addVector("FID",fid,"CHAR"));
//    			vect.add(addVector("LEVEL_ID",level_id,"CHAR"));
//    			vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
//    			
//    			vect.add(addVector("H_ITEM_ID",ds.toString(rs.getString("AUXMAT_NO")),"CHAR"));
//    			vect.add(addVector("H_MATER_ID",ds.toString(rs.getString("AUXMAT_NO")),"CHAR"));
//    			vect.add(addVector("MATRLSPEC",ds.toString(rs.getString("AUXMAT_SPEC")),"CHAR"));
//    			vect.add(addVector("NUM",ds.toString(rs.getString("AUXMAT_QTY")),"NUM"));
//    			vect.add(addVector("SCARP","0","NUM"));
//    			vect.add(addVector("REMARK1","","CHAR"));
//    			bom_bean.insertRecord(vect);
    			String sql1 = "select H_ITEM_ID from meteor.HMATER_BOM where PRODUCT_ID='"+product_id+"' and ITEM_ID='"+item_id
	               +"' and FATHER_ITEM_ID='"+father_item_id+"' and ID='"+id+"' and FID='"+fid+"' and LEVEL_ID='"+level_id
	               +"' and ISSUE_NUM='"+issue_num+"' and H_ITEM_ID='"+ds.toString(rs.getString("AUXMAT_NO"))
	               +"' and H_MATER_ID='"+ds.toString(rs.getString("AUXMAT_NO"))+"'";
	String sql2 = "insert into meteor.HMATER_BOM (PRODUCT_ID,ITEM_ID,FATHER_ITEM_ID,ID,FID,LEVEL_ID,ISSUE_NUM,"
		+"H_ITEM_ID,H_MATER_ID,MATRLSPEC,NUM,REMARK1) values ('"+product_id+"','"+item_id+"','"+father_item_id
		+"','"+id+"','"+fid+"','"+level_id+"','"+issue_num
		+"','"+ds.toString(rs.getString("AUXMAT_NO"))+"','"+ds.toString(rs.getString("AUXMAT_NO"))
		+"','"+ds.toString(rs.getString("AUXMAT_SPEC"))+"',"+ds.toString(rs.getString("AUXMAT_QTY"))
		+",'"+ds.toString(rs.getString("AUXMAT_UNIT"))+"')";
	//System.out.println(sql1);System.out.println(sql2);
	insert_Tool(sql1,sql2);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getFl()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
    }
	/**��PLAN_IDȥ��FO �Ĺ�װ��Ϣ*/
	public void getTool(String PLAN_ID,String product_id,String item_id,String father_item_id,int level,String fid,String id,String issue_num) {
		String level_id=level+"";
		if(level_id.length()==1){level_id="0"+level_id;}
        String sql = "select * from EXCHANGE.T_PROEQUIPLIST where PLAN_ID='"+PLAN_ID+"'";
        PDM_Conn pbombean = new PDM_Conn();
        Bom_Bean bom_bean = new Bom_Bean();
        ResultSet rs      = pbombean.executeQuery(sql);
        DealString ds = new DealString();
        try {
            while (rs.next()) {
            	int type=rs.getInt("CLASS");
            	if(type==2){
            		isin_Item(rs.getString("PROEQUIPCODE"),rs.getString("PROEQUIPNAME"),Item_type("���"));//����meteor item����
            	}else if(type==3){
            		isin_Item(rs.getString("PROEQUIPCODE"),rs.getString("PROEQUIPNAME"),Item_type("������"));//����meteor item����
            	}else if(type==4){
            		isin_Item(rs.getString("PROEQUIPCODE"),rs.getString("PROEQUIPNAME"),Item_type("�������"));//����meteor item����
            	}else{
            		isin_Item(rs.getString("PROEQUIPCODE"),rs.getString("PROEQUIPNAME"),Item_type("��װ"));//����meteor item����
            	}
//            	Vector vect = new Vector();
//            	vect.add("meteor.TOOL_BOM");
//    			vect.add(addVector("PRODUCT_ID",product_id,"CHAR")); 
//    			vect.add(addVector("ITEM_ID",item_id,"CHAR"));
//    			vect.add(addVector("FATHER_ITEM_ID",father_item_id,"CHAR"));
//    			vect.add(addVector("ID",id,"CHAR"));
//    			vect.add(addVector("FID",fid,"CHAR"));
//    			vect.add(addVector("LEVEL_ID",level_id,"CHAR"));
//    			vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
//    			
//    			vect.add(addVector("TOOL_ID",ds.toString(rs.getString("PROEQUIPCODE"))+"-"+ds.toString(rs.getString("PROEQUIPNAME")),"CHAR"));
//    			vect.add(addVector("TOOL_NAME",ds.toString(rs.getString("PROEQUIPNAME")),"CHAR"));
//    			vect.add(addVector("SEQUENCE",ds.toString(rs.getString("SEQUENCE")),"CHAR"));
//    			vect.add(addVector("ITEM_NUM","1","NUM"));
//    			vect.add(addVector("SCRAP","0","NUM"));
//    			vect.add(addVector("REMARK1","","CHAR"));
//    			bom_bean.insertRecord(vect);
            	String sql1 = "select TOOL_ID from meteor.TOOL_BOM where PRODUCT_ID='"+product_id+"' and ITEM_ID='"+item_id
	               +"' and FATHER_ITEM_ID='"+father_item_id+"' and ID='"+id+"' and FID='"+fid+"' and LEVEL_ID='"+level_id
	               +"' and ISSUE_NUM='"+issue_num+"' and TOOL_ID='"+ds.toString(rs.getString("PROEQUIPCODE"))+"'";
	            String sql2 = "insert into meteor.tool_bom (PRODUCT_ID,ITEM_ID,FATHER_ITEM_ID,ID,FID,LEVEL_ID,ISSUE_NUM,"
		           +"TOOL_ID,TOOL_NAME,SEQUENCE,ITEM_NUM) values ('"+product_id+"','"+item_id+"','"+father_item_id
		           +"','"+id+"','"+fid+"','"+level_id+"','"+issue_num+"','"+ds.toString(rs.getString("PROEQUIPCODE"))
		           +"','"+ds.toString(rs.getString("PROEQUIPNAME"))+"','"+ds.toString(rs.getString("SEQUENCE"))+"',1)";
	            insert_Tool(sql1,sql2);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getTool()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
    }
	/**��PLAN_IDȥ��FO �Ĺ�װ��Ϣ*/
	public void getOperTool(String PLAN_ID,String flight_type,String product_id,String item_id,String issue_num,String FO_NO) {
		
        String sql = "select * from EXCHANGE.T_OPPROEQUIP where PLAN_ID='"+PLAN_ID+"'";
        PDM_Conn pbombean = new PDM_Conn();
        Bom_Bean bom_bean = new Bom_Bean();
        ResultSet rs      = pbombean.executeQuery(sql);
        DealString ds = new DealString();
        try {
            while (rs.next()) {
            	Vector vect = new Vector();
            	vect.add("meteor.T_OPPROEQUIP");
            	vect.add(addVector("FLIGHT_TYPE",flight_type,"CHAR")); 
    			vect.add(addVector("PRODUCT_ID",product_id,"CHAR")); 
    			vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
    			vect.add(addVector("ITEM_ID",item_id,"CHAR"));
    			
    			vect.add(addVector("FO_NO",FO_NO,"CHAR"));
    			vect.add(addVector("OPNO",ds.toString(rs.getString("OPNO")),"NUM"));
    			vect.add(addVector("PROEQUIPCODE",ds.toString(rs.getString("PROEQUIPCODE")),"CHAR"));
    			vect.add(addVector("PROEQUIPNAME",ds.toString(rs.getString("PROEQUIPNAME")),"CHAR"));
    			vect.add(addVector("SEQUENCE",ds.toString(rs.getString("SEQUENCE")),"CHAR"));
    			vect.add(addVector("CLASS",ds.toString(rs.getString("CLASS")),"NUM"));
    			bom_bean.insertRecord(vect);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getOperTool()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
    }
	/**��PLAN_IDȥ��FO ����Ϣ*/
	public void getFo(String flight_type,String PLAN_ID,String product_id,String item_id,String issue_num) {
		
        String sql = "select * from EXCHANGE.T_FOITEMINFO where PLAN_ID='"+PLAN_ID+"'";
        PDM_Conn pbombean = new PDM_Conn();
        Bom_Bean bom_bean = new Bom_Bean();
        ResultSet rs      = pbombean.executeQuery(sql);
        DealString ds = new DealString();
        try {
            while (rs.next()) {
            	Vector vect = new Vector();
            	vect.add("meteor.FO");
            	vect.add(addVector("FLIGHT_TYPE",flight_type,"CHAR")); 
    			vect.add(addVector("PRODUCT_ID",product_id,"CHAR")); 
    			vect.add(addVector("ITEM_ID",item_id,"CHAR"));
    			vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
    			vect.add(addVector("FO_NO",ds.toString(rs.getString("FO_NO")),"CHAR"));
    			vect.add(addVector("FOVER",ds.toString(rs.getString("FOVERSION")),"CHAR"));
    			vect.add(addVector("ITEM_NAME",ds.toString(rs.getString("ITEMNAME")),"CHAR"));
    			bom_bean.insertRecord(vect);
    			
//    			String sql1 = "select FO_NO from meteor.FO_BACK where FLIGHT_TYPE='"+flight_type
//			       +"' and PRODUCT_ID='"+product_id+"' and ITEM_ID='"+item_id
//	               +"' and ISSUE_NUM='"+issue_num+"' and FO_NO='"+ds.toString(rs.getString("AO_NO"))
//	               +"' and FOVER='"+ds.toString(rs.getString("AOVER"))+"'";
//	            String sql2 = "insert into meteor.FO_BACK (FLIGHT_TYPE,PRODUCT_ID,ITEM_ID,ISSUE_NUM,FO_NO,FOVER,ITEM_NAME"
//		           +") values ('"+flight_type+"','"+product_id+"','"+item_id+"','"+issue_num
//		           +"','"+ds.toString(rs.getString("FO_NO"))+"','"+ds.toString(rs.getString("FOVERSION"))
//		           +"','"+ds.toString(rs.getString("ITEMNAME"))+"')";
//	            insert_Tool(sql1,sql2);
//    			
    			getFoDetail( flight_type, PLAN_ID, product_id, item_id, issue_num, rs.getString("FO_NO"), rs.getString("FOVERSION"));
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getFo()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
    }
	/**��capp_dept�����쵥λ��Ӧ�Ĳ���*/
	public String dept_id(String OPER_DEPART){
		sql_data sqlbean=new sql_data();
		String dept_no = OPER_DEPART;
		if(OPER_DEPART.startsWith("280")){
			dept_no = OPER_DEPART.substring(3);//ȥ����280��
		}
		String sql = "select dept_id from meteor.CAPP_DEPT where dept_no='"+dept_no+"'";
		ResultSet rs = sqlbean.executeQuery(sql);
		//String dept_id = "";
		try{
			if(rs.next()){
				dept_no = rs.getString("dept_id");
			}else{
				sqlbean.executeUpdate("insert into meteor.CAPP_DEPT (dept_no) values('"+dept_no+"')");
			}rs.close();
			
		}catch(Exception e){
			System.out.println("BomImportDao.dept_id()����ʱ���?����Ϊ��"+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return dept_no;
	}
	/**��PLAN_IDȥ��FO �Ĺ�����Ϣ*/
	public void getFoDetail(String flight_type,String PLAN_ID,String product_id,String item_id,String issue_num,String fo_no,String fover) {
		
        String sql = "select * from EXCHANGE.T_FOOPINFO where PLAN_ID='"+PLAN_ID+"'";
        PDM_Conn pbombean = new PDM_Conn();
        Bom_Bean bom_bean = new Bom_Bean();
        ResultSet rs      = pbombean.executeQuery(sql);
        DealString ds = new DealString();
        String dept_id="";
        try {
            while (rs.next()) {
            	Vector vect = new Vector();
            	vect.add("meteor.FO_DETAIL");
            	vect.add(addVector("FLIGHT_TYPE",flight_type,"CHAR")); 
    			vect.add(addVector("PRODUCT_ID",product_id,"CHAR")); 
    			vect.add(addVector("ITEM_ID",item_id,"CHAR"));
    			vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
    			vect.add(addVector("FO_NO",fo_no,"CHAR"));
    			vect.add(addVector("FOVER",fover,"CHAR"));
    			String opid = rs.getString("OPNO");
    			if(opid.length() == 1){
    				opid="00"+opid;
    			}else if(opid.length() == 2){
    				opid="0"+opid;
    			}
    			vect.add(addVector("OPER_ID",opid,"CHAR"));
    			vect.add(addVector("OPER_NAME",ds.toString(rs.getString("OPNAME")),"CHAR"));
    			vect.add(addVector("OPER_CONTENT",ds.toString(rs.getString("OPDSCRP")),"CHAR"));
    			vect.add(addVector("OPER_TIME",ds.toString(rs.getString("OPR_WORKTIME")),"NUM"));//mes ����ʱ  = pdm ����ʱ
    			vect.add(addVector("RATED_TIME",ds.toString(rs.getString("RMANHOUR")),"NUM"));   //mes ���ʱ  = pdm ׼�Ṥʱ
    			vect.add(addVector("PLAN_TIME",ds.toString(rs.getString("CPREMH")),"NUM"));      //mes �ƻ���ʱ  = pdm ������ʱ
    			vect.add(addVector("OPER_AIDTIME","0","NUM"));                      //mes ����ʱ
    			vect.add(addVector("INSP_TIME","0","NUM"));                         //mes ���鹤ʱ
    			
    			vect.add(addVector("IS_KEYOP",ds.toString(rs.getString("KEYOP")),"CHAR"));
    			vect.add(addVector("IS_INSP",ds.toString(rs.getString("INSP")),"CHAR"));
    			vect.add(addVector("IS_ARMINSP",ds.toString(rs.getString("ARMINSP")),"CHAR"));
    			vect.add(addVector("IS_CERTOP",ds.toString(rs.getString("CERTOP")),"CHAR"));
    			vect.add(addVector("IS_CO","0","CHAR"));
    			
    			dept_id = dept_id(ds.toString(rs.getString("OPER_DEPART")));
    			vect.add(addVector("WCID",dept_id,"CHAR")); //mes ����
    			vect.add(addVector("EQUIPTYPE",ds.toString(rs.getString("EQUIPTYPE")),"CHAR"));
    			vect.add(addVector("EQUIPCODE",ds.toString(rs.getString("EQUIPCODE")),"CHAR"));
    			bom_bean.insertRecord(vect);
    			
//    			String sql1 = "select AO_NO from meteor.FO_DETAIL_BACK where FLIGHT_TYPE='"+flight_type
//			       +"' and PRODUCT_ID='"+product_id+"' and ITEM_ID='"+item_id+"' and ISSUE_NUM='"+issue_num
//			       +"' and FO_NO='"+fo_no+"' and FOVER='"+fover+"' and OPER_ID='"+opid+"'";
//	            String sql2 = "insert into meteor.FO_DETAIL_BACK (FLIGHT_TYPE,PRODUCT_ID,ITEM_ID,ISSUE_NUM,FO_NO,FOVER,OPER_ID,"
//		           +"OPER_NAME,OPER_CONTENT,OPER_TIME,RATED_TIME,PLAN_TIME,OPER_AIDTIME,INSP_TIME,IS_KEYOP,IS_INSP"
//		           +",IS_ARMINSP,IS_CERTOP,IS_CO,WCID,EQUIPTYPE,EQUIPCODE) values ('"+flight_type
//		           +"','"+product_id+"','"+item_id+"','"+issue_num+"','"+fo_no+"','"+fover
//		           +"','"+opid+"','"+ds.toString(rs.getString("OPNAME"))+"','"+ds.toString(rs.getString("OPDSCRP"))
//		           +"','"+ds.toString(rs.getString("OPR_WORKTIME"))+"','"+ds.toString(rs.getString("RMANHOUR"))
//		           +"','"+ds.toString(rs.getString("CPREMH"))+"',0,0,'"+ds.toString(rs.getString("KEYOP"))
//		           +"','"+ds.toString(rs.getString("INSP"))+"','"+ds.toString(rs.getString("ARMINSP"))
//		           +"','"+ds.toString(rs.getString("CERTOP"))+"',0,'"+ds.toString(rs.getString("OPER_DEPART"))
//		           +"','"+ds.toString(rs.getString("EQUIPTYPE"))+"','"+ds.toString(rs.getString("EQUIPCODE"))+"')";
//	            insert_Tool(sql1,sql2);
//    			
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getFoDetail()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
    }
	/**Issue_Num�õ��汾��ƺ�*/
	public String getIssue_Num(String flight_type,String product_id,String lot, String b_sortie,String e_sortie){
		sql_data sqlbean=new sql_data();
		 String sql = "SELECT issue_num FROM meteor.issue_deploy WHERE flight_type='"+ flight_type+"' and product_id='"+ product_id
         +"' and lot='"+lot+"' and  e_sortie<="+e_sortie+"  and b_sortie>="+b_sortie+"";

		ResultSet rs = sqlbean.executeQuery(sql);
		String issue_num = "";
		try{
			if(rs.next()){
				issue_num = rs.getString("issue_num");
			}rs.close();
		}catch(Exception e){
			System.out.println("BomImportDao.getIssue_Num()����ʱ���?����Ϊ��"+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return issue_num;
	}
	/**�õ��ӹ�λ��Ϣ*/
	public ArrayList getChildMDM(MDMWorkPlace MDMwork) {
		
        String sql = "select * from EXCHANGE.T_MDMWORKPLACE where PRODUCTCODE='"+MDMwork.getFlight_type()
                      +"' and PARENTPLACE='"+MDMwork.getChildplace()+"' and STARTPLANENO="+MDMwork.getStartplaneno()
                      +" and ENDPLANENO="+MDMwork.getEndplaneno()+" order by STARTPLANENO";
        PDM_Conn pbombean= new PDM_Conn();
        ResultSet rs = pbombean.executeQuery(sql);
        DealString ds = new DealString();
        ArrayList list = new ArrayList();
        try {
            while (rs.next()) {
            	MDMWorkPlace MDMwork2= new MDMWorkPlace();
            	MDMwork2.setFlight_type(ds.toString(rs.getString("PRODUCTCODE")));
            	MDMwork2.setParentplace(ds.toString(rs.getString("parentplace")));
            	MDMwork2.setChildplace(ds.toString(rs.getString("childplace")));
				MDMwork2.setProduct_id(MDMwork.getProduct_id());
				MDMwork2.setStartplaneno(ds.toString(rs.getString("startplaneno")));
				MDMwork2.setEndplaneno(ds.toString(rs.getString("endplaneno")));
            	MDMwork2.setIssue_num(MDMwork.getIssue_num());
            	MDMwork2.setWorkplacename(ds.toString(rs.getString("workplacename")));
				MDMwork2.setShop(ds.toString(rs.getString("shop")));
				MDMwork2.setLowflage(ds.toString(rs.getString("lowflage")));
				MDMwork2.setMadecenter(ds.toString(rs.getString("madecenter")));
				MDMwork2.setUsecenter(ds.toString(rs.getString("usecenter")));
				MDMwork2.setPerquity(ds.toString(rs.getString("perquity")));
				MDMwork2.setUnitcode(ds.toString(rs.getString("unitcode")));
				MDMwork2.setLeadtime(ds.toString(rs.getString("leadtime")));
				MDMwork2.setMilestonecode(ds.toString(rs.getString("milestonecode")));
				MDMwork2.setNote(ds.toString(rs.getString("note")));
                list.add(MDMwork2);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getChildMDM()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
        return list;
    }
	/**��PDM��MDMWORKPLACE����� flight_type, product_id, lot*/
	public ArrayList getMDMWorkPlace(String flight_type,String product_id,String workplace,String lot) {
		String sql = "";
		int n = Integer.parseInt(lot);
		if(n==0){
			sql = "select * from EXCHANGE.T_MDMWORKPLACE where PRODUCTCODE='"+flight_type
            +"' and CHILDPLACE='"+workplace+"' and (STARTPLANENO LIKE '_' or STARTPLANENO LIKE '__')"
            +" order by STARTPLANENO";
		}else{
			sql = "select * from EXCHANGE.T_MDMWORKPLACE where PRODUCTCODE='"+flight_type
            +"' and CHILDPLACE='"+workplace+"' and STARTPLANENO LIKE '"+lot+"__' "
            +" order by STARTPLANENO";
		}
//        String sql = "select * from EXCHANGE.T_MDMWORKPLACE where PRODUCTCODE='"+flight_type
//                      +"' and CHILDPLACE='"+workplace+"' and STARTPLANENO LIKE '"+lot+"%' order by STARTPLANENO";
        PDM_Conn pbombean= new PDM_Conn();
        ResultSet rs = pbombean.executeQuery(sql);
        DealString ds = new DealString();
        ArrayList list = new ArrayList();
        try {
            while (rs.next()) {
            	String b_sortie   = ds.backStr(ds.toString(rs.getString("startplaneno")), 2);
				String e_sortie   = ds.backStr(ds.toString(rs.getString("endplaneno")), 2);
				String issue_num  = getIssue_Num( flight_type, product_id, lot,  b_sortie, e_sortie);

            	MDMWorkPlace MDMwork= new MDMWorkPlace();
            	MDMwork.setFlight_type(ds.toString(rs.getString("PRODUCTCODE")));
            	MDMwork.setParentplace("#");//ds.toString(rs.getString("parentplace"))
            	MDMwork.setChildplace(ds.toString(rs.getString("childplace")));
				MDMwork.setProduct_id(product_id);
				MDMwork.setStartplaneno(ds.toString(rs.getString("startplaneno")));
				MDMwork.setEndplaneno(ds.toString(rs.getString("endplaneno")));
            	MDMwork.setIssue_num(issue_num);
            	MDMwork.setWorkplacename(ds.toString(rs.getString("workplacename")));
				MDMwork.setShop(ds.toString(rs.getString("shop")));
				MDMwork.setLowflage(ds.toString(rs.getString("lowflage")));
				MDMwork.setMadecenter(ds.toString(rs.getString("madecenter")));
				MDMwork.setUsecenter(ds.toString(rs.getString("usecenter")));
				MDMwork.setPerquity(ds.toString(rs.getString("perquity")));
				MDMwork.setUnitcode(ds.toString(rs.getString("unitcode")));
				MDMwork.setLeadtime(ds.toString(rs.getString("leadtime")));
				MDMwork.setMilestonecode(ds.toString(rs.getString("milestonecode")));
				MDMwork.setNote(ds.toString(rs.getString("note")));
                list.add(MDMwork);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getMDMWorkPlace()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
        return list;
    }
	/**��PDM��MDMMileStone�й�λ��� flight_type, product_id, lot*/
	public ArrayList getMDMMileStone(String flight_type,String product_id,String workplace,String lot) {
		String sql = "";
		int n = Integer.parseInt(lot);
		if(n==0){
			sql = "select * from EXCHANGE.T_MDMMILESTONE where PRODUCTCODE='"+flight_type
            +"' and CHILDMILESTONE='"+workplace+"' and (STARTPLANENO LIKE '_' or STARTPLANENO LIKE '__')"
            +" order by STARTPLANENO";
		}else{
			sql = "select * from EXCHANGE.T_MDMMILESTONE where PRODUCTCODE='"+flight_type
            +"' and CHILDMILESTONE='"+workplace+"' and STARTPLANENO LIKE '"+lot+"__' "
            +" order by STARTPLANENO";
		}
        //System.out.println("getMDMMileStone planeno:"+planeno);
//        String sql = "select * from EXCHANGE.T_MDMMILESTONE where PRODUCTCODE='"+flight_type
//        +"' and CHILDMILESTONE='"+workplace+"' and STARTPLANENO <= "+planeno
//        +" and ENDPLANENO >= "+planeno+" order by STARTPLANENO";
        //System.out.println("getMDMMileStone��"+sql);
        PDM_Conn pbombean= new PDM_Conn();
        ResultSet rs = pbombean.executeQuery(sql);
        DealString ds = new DealString();
        ArrayList list = new ArrayList();
        //list = null;
        try {
            while (rs.next()) {
            	String b_sortie   = ds.backStr(rs.getString("startplaneno"), 2);
				String e_sortie   = ds.backStr(rs.getString("endplaneno"), 2);
				String issue_num  = getIssue_Num( flight_type, product_id, lot,  b_sortie, e_sortie);

            	MDMWorkPlace MDMwork= new MDMWorkPlace();
            	MDMwork.setFlight_type(ds.toString(rs.getString("PRODUCTCODE")));
            	MDMwork.setParentplace("#");//ds.toString(rs.getString("parentmilestone"))
            	MDMwork.setChildplace(ds.toString(rs.getString("childmilestone")));
				MDMwork.setProduct_id(product_id);
				MDMwork.setStartplaneno(ds.toString(rs.getString("startplaneno")));
				MDMwork.setEndplaneno(ds.toString(rs.getString("endplaneno")));
            	MDMwork.setIssue_num(issue_num);
            	MDMwork.setWorkplacename(ds.toString(rs.getString("milestonename")));
				MDMwork.setShop(ds.toString(rs.getString("shop")));
				MDMwork.setLowflage(ds.toString(rs.getString("lowflage")));
				//MDMwork.setMadecenter(ds.toString(rs.getString("madecenter")));
				//MDMwork.setUsecenter(ds.toString(rs.getString("usecenter")));
				MDMwork.setPerquity(ds.toString(rs.getString("perquity")));
				MDMwork.setUnitcode(ds.toString(rs.getString("unitcode")));
				MDMwork.setLeadtime(ds.toString(rs.getString("leadtime")));
				MDMwork.setMilestonecode(ds.toString(rs.getString("parentmilestone")));
				MDMwork.setNote(ds.toString(rs.getString("note")));
                list.add(MDMwork);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getMDMMileStone(sortie)����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
        return list;
    }
	/**��PDM��MDMWORKPLACE����� flight_type, product_id, lot*/
	public ArrayList getMDMWorkPlace(String flight_type,String product_id,String workplace,String lot,String sortie) {
        String planeno = lot+sortie;
        //System.out.println("getMDMWorkPlace planeno:"+planeno);
        String sql = "select * from EXCHANGE.T_MDMWORKPLACE where PRODUCTCODE='"+flight_type
                      +"' and CHILDPLACE='"+workplace+"' and STARTPLANENO <= "+planeno+" and ENDPLANENO >= "+planeno+" order by STARTPLANENO";
        PDM_Conn pbombean= new PDM_Conn();
        ResultSet rs = pbombean.executeQuery(sql);
        DealString ds = new DealString();
        ArrayList list = new ArrayList();
        //list = null;
        try {
            while (rs.next()) {
            	String b_sortie   = ds.backStr(rs.getString("startplaneno"), 2);
				String e_sortie   = ds.backStr(rs.getString("endplaneno"), 2);
				String issue_num  = getIssue_Num( flight_type, product_id, lot,  b_sortie, e_sortie);

            	MDMWorkPlace MDMwork= new MDMWorkPlace();
            	MDMwork.setFlight_type(ds.toString(rs.getString("PRODUCTCODE")));
            	MDMwork.setParentplace("#");//ds.toString(rs.getString("parentplace"))
            	MDMwork.setChildplace(ds.toString(rs.getString("childplace")));
				MDMwork.setProduct_id(product_id);
				MDMwork.setStartplaneno(ds.toString(rs.getString("startplaneno")));
				MDMwork.setEndplaneno(ds.toString(rs.getString("endplaneno")));
            	MDMwork.setIssue_num(issue_num);
            	MDMwork.setWorkplacename(ds.toString(rs.getString("workplacename")));
				MDMwork.setShop(ds.toString(rs.getString("shop")));
				MDMwork.setLowflage(ds.toString(rs.getString("lowflage")));
				MDMwork.setMadecenter(ds.toString(rs.getString("madecenter")));
				MDMwork.setUsecenter(ds.toString(rs.getString("usecenter")));
				MDMwork.setPerquity(ds.toString(rs.getString("perquity")));
				MDMwork.setUnitcode(ds.toString(rs.getString("unitcode")));
				MDMwork.setLeadtime(ds.toString(rs.getString("leadtime")));
				MDMwork.setMilestonecode(ds.toString(rs.getString("milestonecode")));
				MDMwork.setNote(ds.toString(rs.getString("note")));
                list.add(MDMwork);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getMDMWorkPlace(sortie)����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
        return list;
    }
	/**��PDM��MDMMileStone�й�λ��� flight_type, product_id, lot*/
	public ArrayList getMDMMileStone(String flight_type,String product_id,String workplace,String lot,String sortie) {
        String planeno = lot+sortie;
        //System.out.println("getMDMMileStone planeno:"+planeno);
        String sql = "select * from EXCHANGE.T_MDMMILESTONE where PRODUCTCODE='"+flight_type+"' and CHILDMILESTONE='"+workplace+"' and STARTPLANENO <= "+planeno+" and ENDPLANENO >= "+planeno+" order by STARTPLANENO";
        //System.out.println("getMDMMileStone��"+sql);
        PDM_Conn pbombean= new PDM_Conn();
        ResultSet rs = pbombean.executeQuery(sql);
        DealString ds = new DealString();
        ArrayList list = new ArrayList();
        //list = null;
        try {
            while (rs.next()) {
            	String b_sortie   = ds.backStr(rs.getString("startplaneno"), 2);
				String e_sortie   = ds.backStr(rs.getString("endplaneno"), 2);
				String issue_num  = getIssue_Num( flight_type, product_id, lot,  b_sortie, e_sortie);

            	MDMWorkPlace MDMwork= new MDMWorkPlace();
            	MDMwork.setFlight_type(ds.toString(rs.getString("PRODUCTCODE")));
            	MDMwork.setParentplace("#");//ds.toString(rs.getString("parentmilestone"))
            	MDMwork.setChildplace(ds.toString(rs.getString("childmilestone")));
				MDMwork.setProduct_id(product_id);
				MDMwork.setStartplaneno(ds.toString(rs.getString("startplaneno")));
				MDMwork.setEndplaneno(ds.toString(rs.getString("endplaneno")));
            	MDMwork.setIssue_num(issue_num);
            	MDMwork.setWorkplacename(ds.toString(rs.getString("milestonename")));
				MDMwork.setShop(ds.toString(rs.getString("shop")));
				MDMwork.setLowflage(ds.toString(rs.getString("lowflage")));
				//MDMwork.setMadecenter(ds.toString(rs.getString("madecenter")));
				//MDMwork.setUsecenter(ds.toString(rs.getString("usecenter")));
				MDMwork.setPerquity(ds.toString(rs.getString("perquity")));
				MDMwork.setUnitcode(ds.toString(rs.getString("unitcode")));
				MDMwork.setLeadtime(ds.toString(rs.getString("leadtime")));
				MDMwork.setMilestonecode(ds.toString(rs.getString("parentmilestone")));
				MDMwork.setNote(ds.toString(rs.getString("note")));
                list.add(MDMwork);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getMDMMileStone(sortie)����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
        return list;
    }
	/**��PDM��MDMWORKPLACE��  ������ʱ��TEMP_MBOM*/
	public void InputTEMP_MBOM(MDMWorkPlace MDMwork) {
		sql_data sqlbean = new sql_data();
		DealString ds = new DealString();
		String sql="insert into meteor.TEMP_MBOM (flight_type,parentplace,childplace,product_id,startplaneno,endplaneno,"
	        +" issue_num,workplacename,shop,lowflage,madecenter,usecenter,perquity,unitcode,leadtime,milestonecode,note)"
			+" values('"+MDMwork.getFlight_type()+"','"+MDMwork.getParentplace()+"','"+MDMwork.getChildplace()
			+"','"+MDMwork.getProduct_id()+"','"+MDMwork.getStartplaneno()+"','"+MDMwork.getEndplaneno()+"','"+MDMwork.getIssue_num()
			+"','"+MDMwork.getWorkplacename()+"','"+MDMwork.getShop()+"','"+MDMwork.getLowflage()+"','"+MDMwork.getMadecenter()
			+"','"+MDMwork.getUsecenter()+"','"+MDMwork.getPerquity()+"','"+MDMwork.getUnitcode()+"','"+MDMwork.getLeadtime()
			+"','"+MDMwork.getMilestonecode()+"','"+MDMwork.getNote()+"')";
        //System.out.println(sql);
		try {
        	    sqlbean.executeUpdate(sql);
        } catch (Exception ex) {
        	System.out.println("BomImportDao.InputTEMP_MBOM()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	/**��PDM��MDMWORKPLACE��  ������ʱ��MES �� T_MDMWORKPLACE*/
	public void InputT_MDMWORKPLACE(MDMWorkPlace MDMwork){
		sql_data sqlbean = new sql_data();
		String sql="insert into meteor.T_MDMWORKPLACE (flight_type,parentplace,childplace,product_id,"
	        +" issue_num,workplacename,shop,lowflage,madecenter,usecenter,perquity,unitcode,leadtime,milestonecode,note)"
			+" values('"+MDMwork.getFlight_type()+"','"+MDMwork.getParentplace()+"','"+MDMwork.getChildplace()
			+"','"+MDMwork.getProduct_id()+"','"+MDMwork.getIssue_num()
			+"','"+MDMwork.getWorkplacename()+"','"+MDMwork.getShop()+"','"+MDMwork.getLowflage()+"','"+MDMwork.getMadecenter()
			+"','"+MDMwork.getUsecenter()+"','"+MDMwork.getPerquity()+"','"+MDMwork.getUnitcode()+"','"+MDMwork.getLeadtime()
			+"','"+MDMwork.getMilestonecode()+"','"+MDMwork.getNote()+"')";
        //System.out.println(sql);
		try {
        	    sqlbean.executeUpdate(sql);
        } catch (Exception ex) {
        	System.out.println("BomImportDao.InputT_MDMWORKPLACE()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	/**��TEMP_MBOM�����*/
	public ArrayList getTEMP_MBOM() {
        String sql = "select * from meteor.TEMP_MBOM order by rownum desc";
        sql_data sqlbean = new sql_data();
        ResultSet rs     = sqlbean.executeQuery(sql);
        ArrayList list   = new ArrayList();
        DealString ds = new DealString();
        try {
            if (rs.next()) {
                MDMWorkPlace MDMwork= new MDMWorkPlace();
            	MDMwork.setFlight_type(ds.toString(rs.getString("flight_type")));
            	MDMwork.setParentplace(ds.toString(rs.getString("parentplace")));
            	MDMwork.setChildplace(ds.toString(rs.getString("childplace")));
				MDMwork.setProduct_id(ds.toString(rs.getString("product_id")));
				MDMwork.setStartplaneno(ds.toString(rs.getString("startplaneno")));
				MDMwork.setEndplaneno(ds.toString(rs.getString("endplaneno")));
            	MDMwork.setIssue_num(ds.toString(rs.getString("issue_num")));
            	MDMwork.setWorkplacename(ds.toString(rs.getString("workplacename")));
				MDMwork.setShop(ds.toString(rs.getString("shop")));
				MDMwork.setLowflage(ds.toString(rs.getString("lowflage")));
				MDMwork.setMadecenter(ds.toString(rs.getString("madecenter")));
				MDMwork.setUsecenter(ds.toString(rs.getString("usecenter")));
				MDMwork.setPerquity(ds.toString(rs.getString("perquity")));
				MDMwork.setUnitcode(ds.toString(rs.getString("unitcode")));
				MDMwork.setLeadtime(ds.toString(rs.getString("leadtime")));
				MDMwork.setMilestonecode(ds.toString(rs.getString("milestonecode")));
				MDMwork.setNote(ds.toString(rs.getString("note")));
                list.add(MDMwork);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getTEMP_MBOM()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	/**ɾ��TEMP_MBOM���е����*/
	public void delTable(MDMWorkPlace MDMwork) {
        sql_data sqlbean = new sql_data();
        try {
        	sqlbean.executeUpdate("delete from meteor.TEMP_MBOM where flight_type='"+MDMwork.getFlight_type()
					+"' and parentplace='"+MDMwork.getParentplace()+"' and childplace='"+MDMwork.getChildplace()
					+"' and STARTPLANENO='"+MDMwork.getStartplaneno()+"' and ENDPLANENO='"+MDMwork.getEndplaneno()+"'");
        } catch (Exception ex) {
        	System.out.println("BomImportDao.delTable(MDMwork )����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	/**�ж�pbom���Ƿ�����ݴ���*/
	public boolean TEMP_MBOM_notnull(){
		sql_data sqlbean=new sql_data();
		ResultSet rs1 = sqlbean.executeQuery("select * from meteor.TEMP_MBOM");
		boolean status=false;
		try{
			if(rs1.next()){
				status=true;
			}rs1.close();
		}catch(Exception e){
			System.out.println("BomImportDao.TEMP_MBOM_notnull()����ʱ���?����Ϊ��"+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return status;
	}
	/**��PDM��MDMAO����� flight_type, product_id, lot*/
	public ArrayList getMDMAO(MDMWorkPlace MDMwork) {
		
        String sql = "select * from EXCHANGE.T_MDMAO where PRODUCTCODE='"+MDMwork.getFlight_type()
                      +"' and WORKPLACECODE='"+MDMwork.getChildplace()+"' and STARTPLANENO='"+MDMwork.getStartplaneno()
                      +"' and ENDPLANENO='"+MDMwork.getEndplaneno()+"'";
        PDM_Conn pbombean= new PDM_Conn();
        ResultSet rs = pbombean.executeQuery(sql);
        DealString ds = new DealString();
        ArrayList list = new ArrayList();
        try {
            while (rs.next()) {
            	MDMAO MDMAO= new MDMAO();
            	MDMAO.setFlight_type(ds.toString(rs.getString("PRODUCTCODE")));
            	MDMAO.setProduct_id(MDMwork.getProduct_id());
            	MDMAO.setStartplaneno(ds.toString(rs.getString("startplaneno")));
            	MDMAO.setEndplaneno(ds.toString(rs.getString("endplaneno")));
            	MDMAO.setIssue_num(MDMwork.getIssue_num());
            	MDMAO.setWorkplacecode(ds.toString(rs.getString("workplacecode")));
            	MDMAO.setChildaocode(ds.toString(rs.getString("childaocode")));
            	MDMAO.setAoname(ds.toString(rs.getString("aoname")));
            	MDMAO.setParentaocode(ds.toString(rs.getString("parentaocode")));
            	MDMAO.setRationworktime(ds.toString(rs.getString("rationworktime")));
            	MDMAO.setPlanworktime(ds.toString(rs.getString("planworktime")));
            	MDMAO.setLeadtime(ds.toString(rs.getString("leadtime")));
            	MDMAO.setDeptcode(ds.toString(rs.getString("deptcode")));
            	MDMAO.setShop(ds.toString(rs.getString("shop")));
            	MDMAO.setMadecenter(ds.toString(rs.getString("madecenter")));
            	MDMAO.setUsecenter(ds.toString(rs.getString("usecenter")));
            	MDMAO.setPerquity(ds.toString(rs.getString("perquity")));
            	MDMAO.setUnitcode(ds.toString(rs.getString("unitcode")));
            	MDMAO.setLowflage(ds.toString(rs.getString("lowflage")));
            	MDMAO.setNote(ds.toString(rs.getString("note")));
                list.add(MDMAO);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getMDMAO()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
        return list;
    }
	/**��PDM��MDMAO��  ������ʱ��TEMP_MDMAO*/
	public void InputTEMP_MDMAO(MDMAO MDMAO) {
		sql_data sqlbean = new sql_data();
		String sql= "insert into meteor.TEMP_MDMAO (flight_type,product_id,issue_num,workplacecode,childaocode,aoname,parentaocode,"
			+"rationworktime,planworktime,leadtime,deptcode,shop,madecenter,usecenter,perquity,unitcode,lowflage,note,startplaneno,endplaneno)"
			+" values('"+MDMAO.getFlight_type()+"','"+MDMAO.getProduct_id()+"','"+MDMAO.getIssue_num()+"','"+MDMAO.getWorkplacecode()
			+"','"+MDMAO.getChildaocode()+"','"+MDMAO.getAoname()+"','"+MDMAO.getParentaocode()+"','"+MDMAO.getRationworktime()
			+"','"+MDMAO.getPlanworktime()+"','"+MDMAO.getLeadtime()+"','"+MDMAO.getDeptcode()+"','"+MDMAO.getShop()
			+"','"+MDMAO.getMadecenter()+"','"+MDMAO.getUsecenter()+"','"+MDMAO.getPerquity()+"','"+MDMAO.getUnitcode()
			+"','"+MDMAO.getLowflage()+"','"+MDMAO.getNote()+"','"+MDMAO.getStartplaneno()+"','"+MDMAO.getEndplaneno()+"')";
		//System.out.println("InputTEMP_MDMAO"+sql);
		try {
        	 sqlbean.executeUpdate(sql);
        } catch (Exception ex) {
        	System.out.println("BomImportDao.InputTEMP_MDMAO()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	/**��TEMP_MBOM�����*/
	public ArrayList getTEMP_MDMAO() {
        String sql = "select * from meteor.TEMP_MDMAO order by rownum desc";
        sql_data sqlbean = new sql_data();
        ResultSet rs     = sqlbean.executeQuery(sql);
        ArrayList list   = new ArrayList();
        DealString ds = new DealString();
        try {
            if (rs.next()) {
            	MDMAO MDMAO= new MDMAO();
            	MDMAO.setFlight_type(ds.toString(rs.getString("flight_type")));
            	MDMAO.setProduct_id(ds.toString(rs.getString("product_id")));
            	MDMAO.setStartplaneno(ds.toString(rs.getString("startplaneno")));
            	//System.out.println("startplaneno:"+rs.getString("startplaneno"));
            	MDMAO.setEndplaneno(ds.toString(rs.getString("endplaneno")));
            	MDMAO.setIssue_num(ds.toString(rs.getString("issue_num")));
            	MDMAO.setWorkplacecode(ds.toString(rs.getString("workplacecode")));
            	MDMAO.setChildaocode(ds.toString(rs.getString("childaocode")));
            	MDMAO.setAoname(ds.toString(rs.getString("aoname")));
            	MDMAO.setParentaocode(ds.toString(rs.getString("parentaocode")));
            	MDMAO.setRationworktime(ds.toString(rs.getString("rationworktime")));
            	MDMAO.setPlanworktime(ds.toString(rs.getString("planworktime")));
            	MDMAO.setLeadtime(ds.toString(rs.getString("leadtime")));
            	MDMAO.setDeptcode(ds.toString(rs.getString("deptcode")));
            	MDMAO.setShop(ds.toString(rs.getString("shop")));
            	MDMAO.setMadecenter(ds.toString(rs.getString("madecenter")));
            	MDMAO.setUsecenter(ds.toString(rs.getString("usecenter")));
            	MDMAO.setPerquity(ds.toString(rs.getString("perquity")));
            	MDMAO.setUnitcode(ds.toString(rs.getString("unitcode")));
            	MDMAO.setLowflage(ds.toString(rs.getString("lowflage")));
            	MDMAO.setNote(ds.toString(rs.getString("note")));
                list.add(MDMAO);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getTEMP_MBOM()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	/**����ʱ����  ����MES �� T_MDMWORKPLACE*/
	public void InputT_MDMAO(MDMAO MDMAO){
		sql_data sqlbean = new sql_data();
		String sql="insert into meteor.T_MDMAO (flight_type,product_id,issue_num,workplacecode,childaocode,aoname,parentaocode,"
			+"rationworktime,planworktime,leadtime,deptcode,shop,madecenter,usecenter,perquity,unitcode,lowflage,note)"
			+" values('"+MDMAO.getFlight_type()+"','"+MDMAO.getProduct_id()+"','"+MDMAO.getIssue_num()+"','"+MDMAO.getWorkplacecode()
			+"','"+MDMAO.getChildaocode()+"','"+MDMAO.getAoname()+"','"+MDMAO.getParentaocode()+"','"+MDMAO.getRationworktime()
			+"','"+MDMAO.getPlanworktime()+"','"+MDMAO.getLeadtime()+"','"+MDMAO.getDeptcode()+"','"+MDMAO.getShop()
			+"','"+MDMAO.getMadecenter()+"','"+MDMAO.getUsecenter()+"','"+MDMAO.getPerquity()+"','"+MDMAO.getUnitcode()
			+"','"+MDMAO.getLowflage()+"','"+MDMAO.getNote()+"')";
		//System.out.println("InputT_MDMAO"+sql);
        try {
        	sqlbean.executeUpdate(sql);
        } catch (Exception ex) {
        	System.out.println("BomImportDao.InputT_MDMAO()����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	/**��PDM��MDMAO��AO����AO���*/
	public ArrayList getChildMDMAO(MDMAO MDMAO) {
        String sql = "select * from EXCHANGE.T_MDMAO where PRODUCTCODE='"+MDMAO.getFlight_type()
                      +"' and WORKPLACECODE='"+MDMAO.getWorkplacecode()+"' and PARENTAOCODE='"+MDMAO.getChildaocode()
                      +"' and STARTPLANENO='"+MDMAO.getStartplaneno()+"' and ENDPLANENO='"+MDMAO.getEndplaneno()+"'";
        PDM_Conn pbombean= new PDM_Conn();
        ResultSet rs = pbombean.executeQuery(sql);
        DealString ds = new DealString();
        ArrayList list = new ArrayList();
        try {
            while (rs.next()) {
            	MDMAO MDMAO2= new MDMAO();
            	MDMAO2.setFlight_type(ds.toString(rs.getString("PRODUCTCODE")));
            	MDMAO2.setProduct_id(MDMAO.getProduct_id());
            	MDMAO2.setStartplaneno(ds.toString(rs.getString("startplaneno")));
            	MDMAO2.setEndplaneno(ds.toString(rs.getString("endplaneno")));
            	MDMAO2.setIssue_num(MDMAO.getIssue_num());
            	MDMAO2.setWorkplacecode(ds.toString(rs.getString("workplacecode")));
            	MDMAO2.setChildaocode(ds.toString(rs.getString("childaocode")));
            	MDMAO2.setAoname(ds.toString(rs.getString("aoname")));
            	MDMAO2.setParentaocode(ds.toString(rs.getString("parentaocode")));
            	MDMAO2.setRationworktime(ds.toString(rs.getString("rationworktime")));
            	MDMAO2.setPlanworktime(ds.toString(rs.getString("planworktime")));
            	MDMAO2.setLeadtime(ds.toString(rs.getString("leadtime")));
            	MDMAO2.setDeptcode(ds.toString(rs.getString("deptcode")));
            	MDMAO2.setShop(ds.toString(rs.getString("shop")));
            	MDMAO2.setMadecenter(ds.toString(rs.getString("madecenter")));
            	MDMAO2.setUsecenter(ds.toString(rs.getString("usecenter")));
            	MDMAO2.setPerquity(ds.toString(rs.getString("perquity")));
            	MDMAO2.setUnitcode(ds.toString(rs.getString("unitcode")));
            	MDMAO2.setLowflage(ds.toString(rs.getString("lowflage")));
            	MDMAO2.setNote(ds.toString(rs.getString("note")));
                list.add(MDMAO2);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getChildMDMAO()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
        return list;
    }
	/**ɾ��TEMP_MDMAO���е����*/
	public void delTable(MDMAO MDMAO2) {
		//System.out.println("delTable MDMAO");
        sql_data sqlbean = new sql_data();
        try {
        	sqlbean.executeUpdate("delete from meteor.TEMP_MDMAO where flight_type='"+MDMAO2.getFlight_type()
                      +"' and WORKPLACECODE='"+MDMAO2.getWorkplacecode()+"' and CHILDAOCODE='"+MDMAO2.getChildaocode()
                      +"' and PARENTAOCODE='"+MDMAO2.getParentaocode()+"' and issue_num = '"+MDMAO2.getIssue_num()
                      +"' and product_id = '"+MDMAO2.getProduct_id()+"'");
        } catch (Exception ex) {
        	System.out.println("BomImportDao.delTable(MDMAO )����ʱ���?����Ϊ��"+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	/**�ж�pbom���Ƿ�����ݴ���*/
	public boolean TEMP_MDMAO_notnull(){
		sql_data sqlbean=new sql_data();
		ResultSet rs1 = sqlbean.executeQuery("select * from meteor.TEMP_MDMAO");
		boolean status=false;
		try{
			if(rs1.next()){
				status=true;
			}rs1.close();
		}catch(Exception e){
			System.out.println("BomImportDao.TEMP_MDMAO_notnull()����ʱ���?����Ϊ��"+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return status;
	}
	/**�ж�AOװ��ͼ�����ǲ���������Ϻ�*/
	public String getMaxLevel(String product_id,String partno){
		sql_data sqlbean=new sql_data();
		ResultSet rs1;
		String sql="";
		int a=-1,b=-1;
		String[] num = partno.split("��"); 
		String item_id  = "";
		try{
			if(num.length==1){
				item_id=partno;
			}else{
				for(int e=0; e<num.length; e++){
					sql = "select level_id,item_id from meteor.ebom where product_id='"+product_id+"' and item_id like '%"+num[e]+"'";
			      	rs1 = sqlbean.executeQuery(sql);
			      	if(rs1.next()){
			      		a = Integer.parseInt(rs1.getString("level_id"));
			      		if(b == -1){
			      			b = a;
			      			item_id=rs1.getString("item_id");
			      		}else if(b != -1 && a<b){
			      			b = a;
			      			item_id=rs1.getString("item_id");
			      		}
			      	}rs1.close();
		        }
			}
		}catch(Exception e){
			System.out.println("BomImportDao.getMaxLevel()����ʱ���?����Ϊ��"+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return item_id;
	}
	/**�õ�AOװ��ͼ����bom�е���Ϣ*/
	public Hashtable getBomPos(String product_id,String item_id){
		sql_data sqlbean=new sql_data();
		String sql = "SELECT * FROM meteor.ebom WHERE product_id='"+product_id+"' and item_id='"+item_id+"'";
		//System.out.println("getBomPos:"+sql);
		ResultSet rs = sqlbean.executeQuery(sql);
		Hashtable ht = new Hashtable();
		try{
			if(rs.next()){
				//System.out.println("FATHER_ITEM_ID1:"+rs.getString("FATHER_ITEM_ID"));
				ht.put("FATHER_ITEM_ID",rs.getString("FATHER_ITEM_ID"));
				ht.put("LEVEL_ID",rs.getString("LEVEL_ID"));
				ht.put("ID",rs.getString("ID"));
				ht.put("FID",rs.getString("FID"));
			}rs.close();
		}catch(Exception e){
			System.out.println("BomImportDao.getBomPos()����ʱ���?����Ϊ��"+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return ht;
	}
	/**�õ��汾�ŵ�λ��*/
	public String getIssue_Pos(String flight_type,String product_id, String issue_num){
		sql_data sqlbean=new sql_data();
		ResultSet rs = sqlbean.executeQuery("select issue_pos from meteor.issue_deploy where flight_type='"+flight_type
				         +"' and product_id='"+product_id+"' and issue_num='"+issue_num+"'");
		String issue_pos = "";
		try{
			if(rs.next()){
				issue_pos = rs.getString("issue_pos");
			}rs.close();
		}catch(Exception e){
			System.out.println("BomImportDao.getIssue_Pos()����ʱ���?����Ϊ��"+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return issue_pos;
	}
	/**��PLAN_IDȥ��AO ��í����Ϣ*/
	public void getAoMd(String plan_id,String flight_type,String product_id,String issue_num,String item_id,String ao_no,Hashtable ht) {
        String sql = "select * from EXCHANGE.T_AOSTANDARDPART where PLAN_ID='"+plan_id+"'";
        PDM_Conn pbombean= new PDM_Conn();
        Bom_Bean bom_bean= new Bom_Bean();
        ImportBomBean importbombean= new ImportBomBean();
        DealString ds= new DealString();
        ResultSet rs     = pbombean.executeQuery(sql);
        try {
            while (rs.next()) {
            	isin_Item(rs.getString("STPART_NO"),rs.getString("STPART_NAME"),Item_type("��׼��"));//����meteor item���У�������������������
            		Vector vect = new Vector();
            		//System.out.println("getAoMd:"+ds.toString(rs.getString("STPART_NAME")));
            		vect.add("meteor.T_AOSTANDARDPART");//����meteor.T_AOSTANDARDPART��
                	vect.add(addVector("FLIGHT_TYPE",flight_type,"CHAR")); 
        			vect.add(addVector("PRODUCT_ID",product_id,"CHAR")); 
        			vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
        			vect.add(addVector("AO_NO",ao_no,"CHAR"));
        			
        			vect.add(addVector("OPNO",ds.toString(rs.getString("OPNO")),"NUM"));
        			vect.add(addVector("STPART_NO",ds.toString(rs.getString("STPART_NO")),"CHAR"));
        			vect.add(addVector("STPART_NAME",ds.toString(rs.getString("STPART_NAME")),"CHAR"));
        			vect.add(addVector("STPART_TYPE",ds.toString(rs.getString("STPART_TYPE")),"NUM"));
        			vect.add(addVector("STPART_QTY",ds.toString(rs.getString("STPART_QTY")),"NUM"));
        			vect.add(addVector("STPART_ASS",ds.toString(rs.getString("STPART_ASS")),"CHAR"));
        			bom_bean.insertRecord(vect);
        		//if(rs.getInt("STPART_TYPE")==3){// �����í��	�����BOM��
        			
        			int level= Integer.parseInt((String)ht.get("LEVEL_ID"))+1;
        			//System.out.println("level:"+level);
        			String level_id=level+"";
        			if(level_id.length()==1){level_id="0"+level_id;}
        			String issue= getIssue_Pos(flight_type,product_id,issue_num); //�õ�����°汾λ�ú�
        			//System.out.println("issue:"+issue);
        			int p=Integer.parseInt(issue);    
        			String s="";
                	for(int i=1;i<=100;i++){       //���� issue_numֵ
    			        if(i==p){
    			           s=s+'1';
    			        }else{
    			           s=s+'0';               //������ֶ���� ��Ϊ  1   ��Ϊ���� ��Ϊ0
    			        }
    			    }
                	String issue_like="";
                	for(int y=1;y<=p;y++){       //���� issue_numֵ
    			        if(y==p){
    			        	issue_like=issue_like+'1';
    			        }else{
    			        	issue_like=issue_like+'_';               //������ֶ���� ��Ϊ  1   ��Ϊ���� ��Ϊ0
    			        }
    			    }
                	importbombean.setBom_other_id(rs.getString("STPART_NO"));//��í���ŷ���bean�С�
    				Hashtable addht = new Hashtable();
    				addht.put("PRODUCT_ID",product_id);
    				addht.put("ISSUE_NUM", s);
    				addht.put("ISSUE",issue+"");
    				addht.put("FATHER_ITEM_ID",item_id);
    				addht.put("FID",(String)ht.get("ID"));
    				addht.put("LEVEL_ID",level_id);
    				addht.put("ITEM_NUM",ds.toString(rs.getString("STPART_QTY")));
    				ht.put("ISSUE_LIKE",issue_like);
    				importbombean.addBom(addht);
            	//}
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getAoMd()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
    }
	/**��PLAN_IDȥ��AO �ĸ�����Ϣ*/
	public void getAoFl(String plan_id,String flight_type,String product_id,String issue_num,String item_id,String ao_no,Hashtable ht) {
		
        String sql = "select * from EXCHANGE.T_AOAUXMATLIST where PLAN_ID='"+plan_id+"'";
        PDM_Conn pbombean = new PDM_Conn();
        Bom_Bean bom_bean = new Bom_Bean();
        ResultSet rs      = pbombean.executeQuery(sql);
        DealString ds= new DealString();
        //Hashtable ht1 = getBomPos(product_id, item_id);
        try {
            while (rs.next()) {
//            	if(!rs.getString("AUXMAT_CODE").equals("") && rs.getString("AUXMAT_CODE")!=null){
            		isin_Item(rs.getString("AUXMAT_NO"),ds.toString(rs.getString("AUXMAT_NAME")),Item_type("����"));//����meteor item����
//                	Vector vect = new Vector();
//                	vect.add("meteor.HMATER_BOM");
//        			vect.add(addVector("PRODUCT_ID",product_id,"CHAR")); 
//        			vect.add(addVector("ITEM_ID",item_id,"CHAR"));
//        			vect.add(addVector("FATHER_ITEM_ID",(String)ht.get("FATHER_ITEM_ID"),"CHAR"));
//        			vect.add(addVector("ID",(String)ht.get("ID"),"CHAR"));
//        			vect.add(addVector("FID",(String)ht.get("FID"),"CHAR"));
//        			vect.add(addVector("LEVEL_ID",(String)ht.get("LEVEL_ID"),"CHAR"));
//        			vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
//        			
//        			vect.add(addVector("H_ITEM_ID",ds.toString(rs.getString("AUXMAT_NO")),"CHAR"));
//        			vect.add(addVector("H_MATER_ID",ds.toString(rs.getString("AUXMAT_NO")),"CHAR"));
//        			vect.add(addVector("MATRLSPEC",ds.toString(rs.getString("AUXMAT_SPEC")),"CHAR"));
//        			vect.add(addVector("NUM",ds.toString(rs.getString("AUXMAT_QTY")),"NUM"));
//        			vect.add(addVector("SCARP","0","NUM"));
//        			vect.add(addVector("REMARK1","","CHAR"));
//        			bom_bean.insertRecord(vect);
    String sql1 = "select H_ITEM_ID from meteor.HMATER_BOM where PRODUCT_ID='"+product_id+"' and ITEM_ID='"+item_id
 	               +"' and FATHER_ITEM_ID='"+(String)ht.get("FATHER_ITEM_ID")+"' and ID='"+(String)ht.get("ID")
 	               +"' and FID='"+(String)ht.get("FID")+"' and LEVEL_ID='"+(String)ht.get("LEVEL_ID")
 	               +"' and ISSUE_NUM='"+issue_num+"' and H_ITEM_ID='"+ds.toString(rs.getString("AUXMAT_NO"))
 	               +"' and H_MATER_ID='"+ds.toString(rs.getString("AUXMAT_NO"))+"'";
 	String sql2 = "insert into meteor.HMATER_BOM (PRODUCT_ID,ITEM_ID,FATHER_ITEM_ID,ID,FID,LEVEL_ID,ISSUE_NUM,"
 		+"H_ITEM_ID,H_MATER_ID,MATRLSPEC,NUM,REMARK1) values ('"+product_id+"','"+item_id+"','"+(String)ht.get("FATHER_ITEM_ID")
 		+"','"+(String)ht.get("ID")+"','"+(String)ht.get("FID")+"','"+(String)ht.get("LEVEL_ID")+"','"+issue_num
 		+"','"+ds.toString(rs.getString("AUXMAT_NO"))+"','"+ds.toString(rs.getString("AUXMAT_NO"))
 		+"','"+ds.toString(rs.getString("AUXMAT_SPEC"))+"',"+ds.toString(rs.getString("AUXMAT_QTY"))
 		+",'"+ds.toString(rs.getString("AUXMAT_UNIT"))+"')";
 	//System.out.println(sql1);System.out.println(sql2);
 	insert_Tool(sql1,sql2);
        			Vector vect2 = new Vector();
        			vect2.add("meteor.T_AOAUXMATLIST");
        			vect2.add(addVector("FLIGHT_TYPE",flight_type,"CHAR")); 
        			vect2.add(addVector("PRODUCT_ID",product_id,"CHAR")); 
        			vect2.add(addVector("AO_NO",ao_no,"CHAR"));
        			vect2.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
        			
        			vect2.add(addVector("OPNO",ds.toString(rs.getString("OPNO")),"NUM"));
        			vect2.add(addVector("AUXMAT_NO",ds.toString(rs.getString("AUXMAT_NO")),"CHAR"));
        			vect2.add(addVector("AUXMAT_NAME",ds.toString(rs.getString("AUXMAT_NAME")),"CHAR"));
        			vect2.add(addVector("AUXMAT_SPEC",ds.toString(rs.getString("AUXMAT_SPEC")),"CHAR"));
        			vect2.add(addVector("AUXMAT_QTY",ds.toString(rs.getString("AUXMAT_QTY")),"NUM"));
        			vect2.add(addVector("AUXMAT_ASS",ds.toString(rs.getString("AUXMAT_ASS")),"CHAR"));
        			vect2.add(addVector("AUXMAT_CODE",ds.toString(rs.getString("AUXMAT_CODE")),"CHAR"));
        			vect2.add(addVector("AUXMAT_UNIT",ds.toString(rs.getString("AUXMAT_UNIT")),"CHAR"));
        			bom_bean.insertRecord(vect2);
//            	}
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getAoFl()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
    }
	/**��PLAN_IDȥ��AO �Ĺ�װ��Ϣ*/
	public void getAoTool(String plan_id,String flight_type,String product_id,String issue_num,String item_id,String ao_no,Hashtable ht) {
        String sql = "select * from EXCHANGE.T_AOPROEQUIPLIST where PLAN_ID='"+plan_id+"'";
        PDM_Conn pbombean = new PDM_Conn();
        Bom_Bean bom_bean = new Bom_Bean();
        ResultSet rs      = pbombean.executeQuery(sql);
        DealString ds= new DealString();
        try {
            while (rs.next()) {
            	int type=rs.getInt("CLASS");
            	if(type==2){
            		isin_Item(rs.getString("PROEQUIPCODE"),ds.toString(rs.getString("PROEQUIPNAME")),Item_type("���"));//����meteor item����
            	}else if(type==3){
            		isin_Item(rs.getString("PROEQUIPCODE"),ds.toString(rs.getString("PROEQUIPNAME")),Item_type("������"));//����meteor item����
            	}else if(type==4){
            		isin_Item(rs.getString("PROEQUIPCODE"),ds.toString(rs.getString("PROEQUIPNAME")),Item_type("�������"));//����meteor item����
            	}else{
            		isin_Item(rs.getString("PROEQUIPCODE"),ds.toString(rs.getString("PROEQUIPNAME")),Item_type("��װ"));//����meteor item����
            	}
            	String sql1 = "select TOOL_ID from meteor.TOOL_BOM where PRODUCT_ID='"+product_id+"' and ITEM_ID='"+item_id
            	               +"' and FATHER_ITEM_ID='"+(String)ht.get("FATHER_ITEM_ID")+"' and ID='"+(String)ht.get("ID")
            	               +"' and FID='"+(String)ht.get("FID")+"' and LEVEL_ID='"+(String)ht.get("LEVEL_ID")
            	               +"' and ISSUE_NUM='"+issue_num+"' and TOOL_ID='"+ds.toString(rs.getString("PROEQUIPCODE"))+"'";
            	String sql2 = "insert into meteor.tool_bom (PRODUCT_ID,ITEM_ID,FATHER_ITEM_ID,ID,FID,LEVEL_ID,ISSUE_NUM,"
            		+"TOOL_ID,TOOL_NAME,SEQUENCE,ITEM_NUM) values ('"+product_id+"','"+item_id+"','"+(String)ht.get("FATHER_ITEM_ID")
            		+"','"+(String)ht.get("ID")+"','"+(String)ht.get("FID")+"','"+(String)ht.get("LEVEL_ID")+"','"+issue_num
            		+"','"+ds.toString(rs.getString("PROEQUIPCODE"))+"','"+ds.toString(rs.getString("PROEQUIPNAME"))
            		+"','"+ds.toString(rs.getString("SEQUENCE"))+"',1)";
            	insert_Tool(sql1,sql2);
//            	Vector vect = new Vector();
//            	//System.out.println("FATHER_ITEM_ID:"+ht.get("FATHER_ITEM_ID"));
//            	vect.add("meteor.TOOL_BOM");
//    			vect.add(addVector("PRODUCT_ID",product_id,"CHAR")); 
//    			vect.add(addVector("ITEM_ID",item_id,"CHAR"));
//    			vect.add(addVector("FATHER_ITEM_ID",(String)ht.get("FATHER_ITEM_ID"),"CHAR"));
//    			vect.add(addVector("ID",(String)ht.get("ID"),"CHAR"));
//    			vect.add(addVector("FID",(String)ht.get("FID"),"CHAR"));
//    			vect.add(addVector("LEVEL_ID",(String)ht.get("LEVEL_ID")+"","CHAR"));
//    			vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
//    			
//    			vect.add(addVector("TOOL_ID",ds.toString(rs.getString("PROEQUIPCODE")),"CHAR"));
//    			vect.add(addVector("TOOL_NAME",ds.toString(rs.getString("PROEQUIPNAME")),"CHAR"));
//    			vect.add(addVector("SEQUENCE",ds.toString(rs.getString("SEQUENCE")),"CHAR"));
//    			vect.add(addVector("ITEM_NUM","1","NUM"));
//    			vect.add(addVector("SCRAP","0","NUM"));
//    			//vect.add(addVector("REMARK1","","CHAR"));
//    			bom_bean.insertRecord(vect);
    			
    			Vector vect2 = new Vector();
    			vect2.add("meteor.T_AOPROEQUIPLIST");
    			vect2.add(addVector("FLIGHT_TYPE",flight_type,"CHAR")); 
    			vect2.add(addVector("PRODUCT_ID",product_id,"CHAR")); 
    			vect2.add(addVector("AO_NO",ao_no,"CHAR"));
    			vect2.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
    			
    			vect2.add(addVector("PROEQUIPCODE",ds.toString(rs.getString("PROEQUIPCODE"))+"-"+ds.toString(rs.getString("PROEQUIPNAME")),"CHAR"));
    			vect2.add(addVector("PROEQUIPNAME",ds.toString(rs.getString("PROEQUIPNAME")),"CHAR"));
    			vect2.add(addVector("SEQUENCE",ds.toString(rs.getString("SEQUENCE")),"CHAR"));
    			vect2.add(addVector("CLASS",ds.toString(rs.getString("CLASS")),"NUM"));
    			vect2.add(addVector("BC",ds.toString(rs.getString("BC")),"CHAR"));
    			bom_bean.insertRecord(vect2);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getAoTool()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
    }

	/**��PLAN_IDȥ��AO �Ĺ���װ��Ϣ*/
	public void getAoOperTool(String plan_id,String flight_type,String product_id,String issue_num,String item_id,String ao_no) {
        String sql = "select * from EXCHANGE.T_AOOPPROEQUIP where PLAN_ID='"+plan_id+"'";
        PDM_Conn pbombean = new PDM_Conn();
        Bom_Bean bom_bean = new Bom_Bean();
        ResultSet rs      = pbombean.executeQuery(sql);
        DealString ds= new DealString();
        try {
            while (rs.next()) {
    			Vector vect2 = new Vector();
    			vect2.add("meteor.T_AOOPPROEQUIP");
    			vect2.add(addVector("FLIGHT_TYPE",flight_type,"CHAR")); 
    			vect2.add(addVector("PRODUCT_ID",product_id,"CHAR")); 
    			vect2.add(addVector("AO_NO",ao_no,"CHAR"));
    			vect2.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
    			
    			vect2.add(addVector("OPNO",ds.toString(rs.getString("OPNO")),"NUM"));
    			vect2.add(addVector("PROEQUIPCODE",ds.toString(rs.getString("PROEQUIPCODE")),"CHAR"));
    			vect2.add(addVector("PROEQUIPNAME",ds.toString(rs.getString("PROEQUIPNAME")),"CHAR"));
    			vect2.add(addVector("SEQUENCE",ds.toString(rs.getString("SEQUENCE")),"CHAR"));
    			vect2.add(addVector("CLASS",ds.toString(rs.getString("CLASS")),"NUM"));
    			bom_bean.insertRecord(vect2);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getAoOperTool()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
    }
	/**��PLAN_IDȥ��AO �������Ʒ���ױ�*/
	public void getAoPart(String plan_id,String flight_type,String product_id,String issue_num,String item_id,String ao_no) {
        String sql = "select * from EXCHANGE.T_AOREQUIREPART where PLAN_ID='"+plan_id+"'";
        PDM_Conn pbombean = new PDM_Conn();
        Bom_Bean bom_bean = new Bom_Bean();
        ResultSet rs      = pbombean.executeQuery(sql);
        DealString ds= new DealString();
        try {
            while (rs.next()) {
    			Vector vect2 = new Vector();
    			vect2.add("meteor.T_AOREQUIREPART");
    			vect2.add(addVector("FLIGHT_TYPE",flight_type,"CHAR")); 
    			vect2.add(addVector("PRODUCT_ID",product_id,"CHAR")); 
    			vect2.add(addVector("AO_NO",ao_no,"CHAR"));
    			vect2.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
    			
    			vect2.add(addVector("OPNO",ds.toString(rs.getString("OPNO")),"NUM"));
    			vect2.add(addVector("REPART_NO",ds.toString(rs.getString("REPART_NO")),"CHAR"));
    			vect2.add(addVector("REPART_NAME",ds.toString(rs.getString("REPART_NAME")),"CHAR"));
    			vect2.add(addVector("REPART_TYPE",ds.toString(rs.getString("REPART_TYPE")),"NUM"));
    			vect2.add(addVector("REPART_QTY",ds.toString(rs.getString("REPART_QTY")),"NUM"));
    			vect2.add(addVector("REPART_ASS",ds.toString(rs.getString("REPART_ASS")),"CHAR"));
    			bom_bean.insertRecord(vect2);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getAoOperTool()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
    }
	/**��PLAN_IDȥ��AO ����Ϣ*/
	public void getAo(String plan_id,String flight_type,String product_id,String issue_num,String item_id,String ao_no) {
        String sql = "select * from EXCHANGE.T_ASSAOMAIN where PLAN_ID='"+plan_id+"'";
        PDM_Conn pbombean = new PDM_Conn();
        Bom_Bean bom_bean = new Bom_Bean();
        DealString ds = new DealString();
        ResultSet rs      = pbombean.executeQuery(sql);
        try {
            while (rs.next()) {
            	//System.out.println("getAo+"+rs.getString("AO_NO"));
            	String ao_order = ds.backStr(rs.getString("AO_NO"), 3); 
            	
            	Vector vect = new Vector();
            	vect.add("meteor.AOMAIN");
            	vect.add(addVector("FLIGHTTYPE",flight_type,"CHAR")); 
    			vect.add(addVector("PRODUCTID",product_id,"CHAR")); 
    			vect.add(addVector("ITEMID",item_id,"CHAR"));
    			vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
    			vect.add(addVector("AO_NO",ds.toString(rs.getString("AO_NO")),"CHAR"));
    			vect.add(addVector("AOVER",ds.toString(rs.getString("AOVER")),"CHAR"));
    			vect.add(addVector("AO_TIME","8","NUM"));
    			vect.add(addVector("AONAME",ds.toString(rs.getString("AONAME")),"CHAR"));
    			vect.add(addVector("WORKPLACECODE",ds.toString(rs.getString("WORKPLACECODE")),"CHAR"));
    			vect.add(addVector("WORKPLACENAME",ds.toString(rs.getString("WORKPLACENAME")),"CHAR"));
    			vect.add(addVector("PARTNO",ds.toString(rs.getString("PARTNO")),"CHAR"));
    			vect.add(addVector("AO_ORDER",ao_order,"NUM"));
    			bom_bean.insertRecord(vect);
    			
//    			String sql1 = "select AO_NO from meteor.AOMAIN_BACK where FLIGHTTYPE='"+flight_type
//    			   +"' and PRODUCTID='"+product_id+"' and ITEMID='"+item_id
//	               +"' and ISSUE_NUM='"+issue_num+"' and AO_NO='"+ds.toString(rs.getString("AO_NO"))
//	               +"' and AOVER='"+ds.toString(rs.getString("AOVER"))+"'";
//	            String sql2 = "insert into meteor.AOMAIN_BACK (FLIGHTTYPE,PRODUCTID,ITEMID,ISSUE_NUM,AO_NO,AOVER,AO_TIME,"
//		           +"AONAME,WORKPLACECODE,WORKPLACENAME,PARTNO,AO_ORDER) values ('"+flight_type+"','"+product_id
//		           +"','"+item_id+"','"+issue_num+"','"+ds.toString(rs.getString("AO_NO"))+"','"+ds.toString(rs.getString("AOVER"))
//		           +"',8,'"+ds.toString(rs.getString("AONAME"))+"','"+ds.toString(rs.getString("WORKPLACECODE"))
//		           +"','"+ds.toString(rs.getString("WORKPLACENAME"))+"','"+ds.toString(rs.getString("PARTNO"))+"','"+ao_order+"')";
//	            insert_Tool(sql1,sql2);
    			getAoDetail( flight_type, plan_id, product_id, item_id, issue_num, rs.getString("AO_NO"), rs.getString("AOVER"));
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getAo()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
    }
	/**��PLAN_IDȥ��AO �Ĺ�����Ϣ*/
	public void getAoDetail(String flight_type,String plan_id,String product_id,String item_id,String issue_num,String ao_no,String aover) {
        String sql = "select * from EXCHANGE.T_ASSEMBLEORDER where PLAN_ID='"+plan_id+"'";
        PDM_Conn pbombean = new PDM_Conn();
        Bom_Bean bom_bean = new Bom_Bean();
        ResultSet rs      = pbombean.executeQuery(sql);
        DealString ds = new DealString();
        try {
            while (rs.next()) {
            	//System.out.println("getAoDetail+"+rs.getString("OPNO"));
            	Vector vect = new Vector();
            	vect.add("meteor.AO_OPER");
            	vect.add(addVector("FLIGHTTPYE",flight_type,"CHAR")); 
    			vect.add(addVector("PRODUCEID",product_id,"CHAR")); 
    			vect.add(addVector("ITEMID",item_id,"CHAR"));
    			vect.add(addVector("ISSUE_NUM",issue_num,"CHAR"));
    			vect.add(addVector("AO_NO",ao_no,"CHAR"));
    			vect.add(addVector("AOVER",aover,"CHAR"));
    			String opid=rs.getString("OPNO");
    			if(opid.length() == 1){
    				opid="00"+opid;
    			}else if(opid.length() == 2){
    				opid="0"+opid;
    			}
    			vect.add(addVector("AO_OPID",opid,"CHAR"));
    			vect.add(addVector("AO_OPNAME",ds.toString(rs.getString("OPNAME")),"CHAR"));
    			vect.add(addVector("AO_OPCONTENT",ds.toString(rs.getString("OPCONTENT")),"CHAR"));
    			vect.add(addVector("WORKTYPENO",ds.toString(rs.getString("WORKTYPENO")),"CHAR"));
    			
    			vect.add(addVector("IFKEY",ds.toString(rs.getString("IFKEY")),"CHAR"));
    			vect.add(addVector("IFACHECH",ds.toString(rs.getString("IFACHECK")),"CHAR"));
    			vect.add(addVector("IFHCO",ds.toString(rs.getString("IFHCO")),"CHAR"));
    			vect.add(addVector("IMPORT",ds.toString(rs.getString("IMPORT")),"CHAR"));
    			vect.add(addVector("RMANHOUR",ds.toString(rs.getString("RMANHOUR")),"NUM"));
    			vect.add(addVector("MMANHOUR",ds.toString(rs.getString("MMANHOUR")),"NUM"));
    			bom_bean.insertRecord(vect);
    			
//    			String sql1 = "select AO_OPID from meteor.AO_OPER_BACK where FLIGHTTPYE='"+flight_type
// 			       +"' and PRODUCEID='"+product_id+"' and ITEMID='"+item_id
//	               +"' and ISSUE_NUM='"+issue_num+"' and AO_NO='"+ds.toString(rs.getString("AO_NO"))
//	               +"' and AOVER='"+ds.toString(rs.getString("AOVER"))+"' and AO_OPID='"+opid+"'";
//	            String sql2 = "insert into meteor.AO_OPER_BACK (FLIGHTTPYE,PRODUCEID,ITEMID,ISSUE_NUM,AO_NO,AOVER,AO_OPID,"
//		           +"AO_OPNAME,AO_OPCONTENT,WORKTYPENO,IFKEY,IFACHECH,IFHCO,IMPORT,RMANHOUR,MMANHOUR) values ('"+flight_type
//		           +"','"+product_id+"','"+item_id+"','"+issue_num+"','"+ao_no+"','"+aover
//		           +"','"+opid+"','"+ds.toString(rs.getString("OPNAME"))+"','"+ds.toString(rs.getString("OPCONTENT"))
//		           +"','"+ds.toString(rs.getString("WORKTYPENO"))+"','"+ds.toString(rs.getString("IFKEY"))
//		           +"','"+ds.toString(rs.getString("IFACHECK"))+"','"+ds.toString(rs.getString("IFHCO"))
//		           +"','"+ds.toString(rs.getString("IMPORT"))+"','"+ds.toString(rs.getString("RMANHOUR"))
//		           +"','"+ds.toString(rs.getString("MMANHOUR"))+"')";
//	            insert_Tool(sql1,sql2);
    			
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomImportDao.getAoDetail()����ʱ���?����Ϊ��"+ex);
        } finally {
        	pbombean.closeConn();
        }
    }
	/**д��ݿ�ʱĳһ���ֶεĴ洢����*/
	public Vector addVector(String field,String value,String type)
	{
		Vector vect = new Vector();
		vect.add(field);
		vect.add(value);
		vect.add(type);
		return vect;
	}
}
