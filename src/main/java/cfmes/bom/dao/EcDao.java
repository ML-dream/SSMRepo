package cfmes.bom.dao;

import java.sql.ResultSet;
import java.util.Vector;


import cfmes.bean.MultiRecordBean;
import cfmes.bom.entity.BomRecord;
import cfmes.util.sql_data;

public class EcDao {
   public boolean Is_AccQuery(BomRecord bmrecd,String table){
	   boolean has = false;
	   sql_data sqlbean = new sql_data();
	   MultiRecordBean mrbean = new MultiRecordBean();
	   String sql;
	 
	   Vector vect = new Vector();
		vect.add(table);
		vect.add(mrbean.addVector("PRODUCT_ID",bmrecd.getproduct_id(),"CHAR"));
		vect.add(mrbean.addVector("ISSUE_NUM",bmrecd.getissue_num(),"CHAR"));
		vect.add(mrbean.addVector("NODE_ID",bmrecd.getxid(),"CHAR"));
		vect.add(mrbean.addVector("FATHER_NODE_ID",bmrecd.getfxid(),"CHAR"));
		vect.add(mrbean.addVector("LEVEL_ID",bmrecd.getlevel_id(),"CHAR"));
		vect.add(mrbean.addVector("ID",bmrecd.getid(),"CHAR"));
		vect.add(mrbean.addVector("FID",bmrecd.getfid(),"CHAR"));
		vect.add(mrbean.addVector("NODE_NUM",bmrecd.getnum(),"CHAR"));
		vect.add(mrbean.addVector("MEMO1",bmrecd.getmemo1(),"CHAR"));
		vect.add(mrbean.addVector("MEMO2",bmrecd.getmemo2(),"CHAR"));
		vect.add(mrbean.addVector("MEMO3",bmrecd.getmemo3(),"CHAR"));
		vect.add(mrbean.addVector("MEMO4",bmrecd.getmemo4(),"CHAR"));
		sql=mrbean.Query_Record(vect);
		
		System.out.println("EcDao.java的 Is_AccQuery  第34行的sql语句是：==="+sql);
       ResultSet rs = sqlbean.executeQuery(sql);
       try {
           if (rs.next()) {
           	has=true;
           }rs.close();
       } catch (Exception ex) {
       	System.out.println("EcDao.is_accquery()����ʱ���?����Ϊ��"+ex);
       } finally {
       	sqlbean.closeConn();
       }
	   return has;
   }
   
   public boolean Is_BlurQuery(BomRecord bmrecd,String tablename){
	   boolean has = false;
	   sql_data sqlbean = new sql_data();
	   MultiRecordBean mrbean = new MultiRecordBean();
	   String sql;
	 
	   Vector vect = new Vector();
		vect.add(tablename);
		vect.add(mrbean.addVector("PRODUCT_ID",bmrecd.getproduct_id(),"CHAR"));
		vect.add(mrbean.addVector("ISSUE_NUM",bmrecd.getissue_num(),"CHAR"));
		vect.add(mrbean.addVector("NODE_ID",bmrecd.getxid(),"CHAR"));
		vect.add(mrbean.addVector("FATHER_NODE_ID",bmrecd.getfxid(),"CHAR"));
		vect.add(mrbean.addVector("LEVEL_ID",bmrecd.getlevel_id(),"CHAR"));
		vect.add(mrbean.addVector("ID",bmrecd.getid(),"CHAR"));
		vect.add(mrbean.addVector("FID",bmrecd.getfid(),"CHAR"));
		sql=mrbean.Query_Record(vect);
		
		System.out.println("EcDao.java的 Is_BlurQuery  第34行的sql语句是：==="+sql);
		
       ResultSet rs = sqlbean.executeQuery(sql);
       try {
           if (rs.next()) {
           	has=true;
           }rs.close();
       } catch (Exception ex) {
       	System.out.println("EcDao.is_blurquery()����ʱ���?����Ϊ��"+ex);
       } finally {
       	sqlbean.closeConn();
       }
	   return has;
   }
   
   public String GetEcName(String ec_id){
	   String sql,name="";
	   sql_data sqlbean =new sql_data();
	   sql = "select ec_name from work.ec_inf where ec_id = '"+ec_id+"'";
	   ResultSet rs = sqlbean.executeQuery(sql);
	   try{
		   if(rs.next()){
			   name= rs.getString("ec_name");
		   }
	   }catch (Exception ex){
		   System.out.println("ecdao.getecname()����ʱ���?����Ϊ��"+ex);
	   }finally{
		   sqlbean.closeConn();
	   }
	   
	   return name;
	   
   }
   //��ѯnmbom�Ƿ�����ݴ���
   public boolean Is_NMbom(){
	   boolean has = false;
	   sql_data sqlbean = new sql_data();
	   String sql = "select * from work.nmbom";		
       ResultSet rs = sqlbean.executeQuery(sql);
       try {
           if (rs.next()) {
           	has=true;
           }rs.close();
       } catch (Exception ex) {
       	System.out.println("EcDao.is_FOOPUPEC()����ʱ���?����Ϊ��"+ex);
       } finally {
       	sqlbean.closeConn();
       }
	   return has;
   }
   //��ѯ�Ƿ��й���������ֱ��Ĵ���
   public boolean Is_FoOpUpEc(BomRecord bmrecd){
	   boolean has = false;
	   sql_data sqlbean = new sql_data();
	   MultiRecordBean mrbean = new MultiRecordBean();
	   String sql;
	   
	    Vector vect = new Vector();
		vect.add("work.fooper_updateec");
		vect.add(mrbean.addVector("PRODUCT_ID",bmrecd.getproduct_id(),"CHAR"));
		vect.add(mrbean.addVector("ISSUE_NUM",bmrecd.getissue_num(),"CHAR"));
		vect.add(mrbean.addVector("ITEM_ID",bmrecd.getmemo4(),"CHAR"));
		vect.add(mrbean.addVector("FO_NO",bmrecd.getmemo1(),"CHAR"));
		vect.add(mrbean.addVector("FO_VER",bmrecd.getmemo2(),"CHAR"));
		vect.add(mrbean.addVector("FO_OPERID",bmrecd.getxid(),"CHAR"));
		sql=mrbean.Query_Record(vect);
		
		System.out.println("EcDao.java的 Is_FoOpUpEc  第130行的sql语句是：==="+sql);
		
       ResultSet rs = sqlbean.executeQuery(sql);
       try {
           if (rs.next()) {
           	has=true;
           }rs.close();
       } catch (Exception ex) {
       	System.out.println("EcDao.is_FOOPUPEC()����ʱ���?����Ϊ��"+ex);
       } finally {
       	sqlbean.closeConn();
       }
	   return has;
   }
}
