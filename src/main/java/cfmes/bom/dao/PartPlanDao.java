package cfmes.bom.dao;

import java.sql.ResultSet;
import java.util.ArrayList;


import cfmes.bom.entity.FoDetail;

import cfmes.bom.entity.PartPlan;
import cfmes.bom.entity.PartStatus;
import cfmes.util.DealString;
import cfmes.util.Stringtoint;
import cfmes.util.sql_data;

public class PartPlanDao {
   public boolean IsPart(String id){
	   boolean istrue = false;
	   String a="";
	   id=id.trim();
	   String sql = "select item_typeid from work.ITEM where item_id='"+id+"'";//
       sql_data sqlbean = new sql_data();
       ResultSet rs = sqlbean.executeQuery(sql);

      try {
      if (rs.next()) {
	     a=rs.getString("item_typeid");
	     if(a.equals("L")){istrue=true;}
         }rs.close();
         } catch (Exception ex) {
           System.out.println("partplandao.ispart()����ʱ���?����Ϊ��"+ex);
           } finally {
              sqlbean.closeConn();
                      }
	   return istrue;
   }
   public String PartPlanStatus(String productid,String issuenum,String id){
	   String status="";
	   productid=productid.trim();
	   issuenum=issuenum.trim();
	   id=id.trim();
	   String sql = "select part_status from work.partplan where product_id='"+productid+"' and issue_num='"+issuenum+"' and item_id='"+id+"'";//
       sql_data sqlbean = new sql_data();
       ResultSet rs = sqlbean.executeQuery(sql);

      try {
      if (rs.next()) {
	     status = rs.getString("part_status");
         }rs.close();
         } catch (Exception ex) {
           System.out.println("partplandao.partplanstatus()����ʱ���?����Ϊ��"+ex);
           } finally {
              sqlbean.closeConn();
                      }
	   return status;
   }
   public String StatusResult(String status){
	   String result="";
	   status=status.trim();
	   String sql = "select status_result from work.partstatus where id='"+status+"'";
       sql_data sqlbean = new sql_data();
       ResultSet rs = sqlbean.executeQuery(sql);

      try {
      if (rs.next()) {
	     result=rs.getString("status_result");
         }rs.close();
         } catch (Exception ex) {
           System.out.println("partplandao.statusresult()����ʱ���?����Ϊ��"+ex);
           } finally {
              sqlbean.closeConn();
                      }
	   return result;
   }
   public String StatusDo(String status){
	   String statusdo="";
	   status=status.trim();
	   String sql = "select do from work.partstatus where id='"+status+"'";
       sql_data sqlbean = new sql_data();
       ResultSet rs = sqlbean.executeQuery(sql);

      try {
      if (rs.next()) {
	     statusdo=rs.getString("do");
         }rs.close();
         } catch (Exception ex) {
           System.out.println("partplandao.statusdo()����ʱ���?����Ϊ��"+ex);
           } finally {
              sqlbean.closeConn();
                      }
	   return statusdo;
	   
   }
   
   /**�õ�partplan�б?�����������������ƻ���**/
   public ArrayList getpartplan(String product_id,String father_item_id,String id) {
   	   product_id=product_id.trim();
   	   father_item_id=father_item_id.trim();
   	   id=id.trim();
       String sql = "SELECT * from work.partplan "
	         +"where product_id='"+ product_id+"' and father_item_id='"+father_item_id+"' and item_id='"+id+"' ORDER BY PLAN_ID";
      
       System.out.println("PartPlanDao.java中的getpartplan中的sql语句为:"+sql);
       
       sql_data sqlbean = new sql_data();
       ResultSet rs = sqlbean.executeQuery(sql);
       ArrayList list = new ArrayList();
       DealString ds = new DealString();
      
       try {
       	while (rs.next()) {
       		
               PartPlan partplan = new PartPlan();
               partplan.setOrder_id(ds.toString(rs.getString("order_id")));
               partplan.setProduct_id(ds.toString(rs.getString("product_id")));
               partplan.setIssue_num(ds.toString(rs.getString("issue_num")));
               partplan.setPlan_id(ds.toString(rs.getString("plan_id")));
               partplan.setQuality_id(ds.toString(rs.getString("quality_id")));
               partplan.setPlan_peo(ds.toString(rs.getString("plan_peo")));
               partplan.setPart_status(ds.toString(rs.getString("part_status")));
               partplan.setPart_num(ds.toString(rs.getString("part_num")));
               partplan.setPart_bgtime(ds.toString(rs.getString("plan_bgtime")));
               partplan.setPart_edtime(ds.toString(rs.getString("plan_edtime")));
               
               list.add(partplan);
           }rs.close();
       } catch (Exception ex) {
       	System.out.println("BomBeanDao.getpartplan()����ʱ���?����Ϊ��"+ex);
       } finally {
       	sqlbean.closeConn();
       }
       return list;
   }
   
   /**�õ�status�б?��״̬�б�**/
   public ArrayList GetStatus(String partstatus) {
   	   Stringtoint stoi = new Stringtoint();
       String sql = "SELECT * from work.partstatus ";
       sql_data sqlbean = new sql_data();
       ResultSet rs = sqlbean.executeQuery(sql);
       ArrayList list = new ArrayList();
       DealString ds = new DealString();
      
       try {
       	while (rs.next()) {
       		PartStatus pstatus = new PartStatus();
            pstatus.setStatus_id(ds.toString(rs.getString("id")));
            pstatus.setStatus_result(ds.toString(rs.getString("status_result")));
            pstatus.setStatus_do(ds.toString(rs.getString("do")));
            
               if(stoi.stoi(pstatus.getStatus_id())>stoi.stoi(partstatus)){
            	   
               list.add(pstatus);}else{}
           }rs.close();
       } catch (Exception ex) {
       	System.out.println("BomBeanDao.getpartplan()����ʱ���?����Ϊ��"+ex);
       } finally {
       	sqlbean.closeConn();
       }
       return list;
   }
   
   /**�õ���ϸ�Ĺ����б�**/
   public ArrayList GetFoDetail(String product_id,String father_item_id,String item_id,String issue_num){
	   product_id = product_id.trim();
	   item_id = item_id.trim();
	   issue_num = issue_num.trim();
	   ArrayList list = new ArrayList();
	   String sql = "SELECT * from work.fo_detail "
	         +"where product_id='"+ product_id+"' and item_id='"+item_id+"' and issue_num='"+issue_num+"'";
     sql_data sqlbean = new sql_data();
     ResultSet rs = sqlbean.executeQuery(sql);
    
     DealString ds = new DealString();
    
     try {
     	while (rs.next()) {
     		
             FoDetail fodetail = new FoDetail();
             fodetail.setInsp_time(rs.getFloat("insp_time"));
             fodetail.setOper_aidtime(rs.getFloat("oper_aidtime"));
             fodetail.setOper_time(rs.getFloat("oper_time"));
             fodetail.setPlan_time(rs.getFloat("plan_time"));
             fodetail.setRated_time(rs.getFloat("rated_time"));
             
             list.add(fodetail);
         }rs.close();
     } catch (Exception ex) {
     	System.out.println("BomBeanDao.getpartplan()����ʱ���?����Ϊ��"+ex);
     } finally {
     	sqlbean.closeConn();
     }
	   return list;
   }
}
