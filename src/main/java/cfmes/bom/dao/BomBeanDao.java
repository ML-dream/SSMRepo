package cfmes.bom.dao;

import cfmes.bom.entity.Bom;
import cfmes.bom.entity.CombinePlanBom;
import cfmes.bom.entity.PartPlan;
import cfmes.bom.entity.FinishReport;
import cfmes.bom.entity.AddTree;

import java.sql.ResultSet;
import java.util.ArrayList;
import cfmes.util.DealString;
import cfmes.util.sql_data;
public class BomBeanDao {
	
	/**读BOM中同层中没有这个物料*/
	public boolean getLevel_exist(String product_id,String item_id ,String level_id) {
		
        String sql = "SELECT * FROM work.ebom WHERE product_id='"+ product_id+"' and item_id='"+item_id+"' and level_id='"+level_id+"'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        boolean isin=false;
        try {
            if (rs.next()) {
            	isin=true;
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getLevel_exist()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
    }
	
	/**得到Capp维护bom上的AO/FO类型*/
	public String getAo(String flight_type, String product_id,String issue_num, String item_id) {
		
        String sql = "select ao_no from work.aomain where product_type='"+flight_type
		   +"' and product_id='"+product_id+"' and issue_num='"+issue_num+"' and item_id='"+item_id+"'";
        System.out.println("BomBeanDao的getAo方法的sql语句为："+sql);
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        String isin="";
        try {
        	if(rs.next()){
        		isin ="AO";
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getLevel_exist()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
    }
	/**得到Capp维护bom上的FO类型*/
	public String getFo(String flight_type, String product_id,String issue_num, String item_id) {
		
        String sql = "select fo_no from work.fo where product_type='"+flight_type
		   +"' and product_id='"+product_id+"' and issue_num='"+issue_num+"' and item_id='"+item_id+"'";
        System.out.println("BomBeanDao类的getFo方法中的sql语句为："+sql);
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        String isin="";
        try {
        	if(rs.next()){
		       isin = "FO";
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getLevel_exist()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
    }
	/**读BOM中同层中同父下没有这个物料*/
	public boolean getFather_exist(String product_id,String item_id ,String level_id,String father_item_id,String fid) {
		
        String sql = "select * from work.ebom where product_id='"+ product_id+"' and item_id='"+item_id+"' and level_id='"+level_id
                      +"' and father_item_id='"+father_item_id+"' and fid='"+fid+"'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        boolean isin=false;
        try {
            if (rs.next()) {
            	isin=true;
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getFather_exist()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
    }
	//------------------------------------fy
	/**读BOM中同层中同父下有这个物料,从而查出最大的fid*/
	public int getMaxFid(String product_id,String item_id ,String level_id,String father_item_id) {
		int i = 0;
        String sql = "select max(distinct fid) as a from meteor.ebom where product_id='"+ product_id+"' and item_id='"+item_id+"' and level_id='"+level_id
                      +"' and father_item_id='"+father_item_id+"'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        try {
            if (rs.next()) {
            	i = Integer.parseInt(rs.getString("a"));
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getFather_exist()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return i;
    }
	//------------------------------------fy
	/**读BOM中同层中同父下有这个物料,从而查出最大的id*/
	public int getMaxId2(String product_id,String item_id ,String level_id,String father_item_id) {
		int i = 0;
        String sql = "select max(distinct id) as a from meteor.ebom where product_id='"+ product_id+"' and item_id='"+item_id+"' and level_id='"+level_id
                      +"' and father_item_id='"+father_item_id+"'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        try {
            if (rs.next()) {
            	i = Integer.parseInt(rs.getString("a"));
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getFather_exist()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return i;
    }
	/**读BOM中同层中同父下没有这个物料且版本位置为‘1’*/
	public boolean isin_Father(String product_id,String item_id ,String level_id,String father_item_id,String fid,String issue_num) {
		
        String sql = "select * from work.ebom where product_id='"+ product_id+"' and item_id='"+item_id+"' and level_id='"+level_id
                      +"' and father_item_id='"+father_item_id+"' and fid='"+fid+"' and issue_num like '"+issue_num+"%'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        //boolean isin=false;
        boolean isin=false;
        try {
            if (rs.next()) {
            	isin=true;
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getFather_exist(issue_num)处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
    }
	/**读BOM中同层中同父下没有这个物料且版本位置为‘1’*/
	public int getNum(String product_id,String item_id ,String level_id,String father_item_id,String fid,String issue_num) {
		
        String sql = "select * from meteor.ebom where product_id='"+ product_id+"' and item_id='"+item_id+"' and level_id='"+level_id
                      +"' and father_item_id='"+father_item_id+"' and fid='"+fid+"' and issue_num like '"+issue_num+"%'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        //boolean isin=false;
        int item_num = 0;
        try {
            if (rs.next()) {
            	item_num = rs.getInt("item_num");
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getFather_exist(issue_num)处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return item_num;
    }
	/**读BOM中同层中同父下没有这个物料且版本位置为‘1’*/
	public boolean isinMHT(String type,String product_id,String item_id ,String issue_num,String ao_no,String aover,String ao_operid,String other_id) {
		String sql="";
		if (type.equals("Y")){
			sql = "select * from meteor.AO_MATERIAL where product_id = '"+product_id
			      +"' and issue_num='"+issue_num+"' and item_id='"+item_id+"' and AO_MATER_ID = '"+other_id+"'AND AO_NO='"+ao_no+"' AND AOVER='"+aover+"' AND AO_OPERID='"+ao_operid+"'" ;
		}else if(type.equals("F")){
			sql = "select * from meteor.AO_ACCESSORY where product_id='"+product_id
		      +"' and issue_num='"+issue_num+"' and item_id='"+item_id+"' and AO_ACCESSORY_ID = '"+other_id+"'AND AO_NO='"+ao_no+"' AND AOVER='"+aover+"' AND AO_OPERID='"+ao_operid+"'" ;
		}else if(type.equals("T")){
			sql = "select * from meteor.AO_JIG where product_id='"+product_id
		      +"' and issue_num='"+issue_num+"' and item_id='"+item_id+"' and AO_TOOL_ID = '"+other_id+"'AND AO_NO='"+ao_no+"' AND AOVER='"+aover+"' AND AO_OPERID='"+ao_operid+"'" ;
		}
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        boolean isin=false;
        try {
            if (rs.next()) {
            	isin=true;
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.isinMHT(issue_num)处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
    }
	/**读BOM中同层中同父下没有这个物料*/
	public ArrayList getBom(String product_id,String item_id ,String level_id,String father_item_id,String fid) {
		
        String sql = "select * from meteor.ebom where product_id='"+ product_id+"' and item_id='"+item_id+"' and level_id='"+level_id
                      +"' and father_item_id='"+father_item_id+"' and fid='"+fid+"'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        ArrayList list = new ArrayList();
        try {
        	while (rs.next()) {
                Bom bom = new Bom();
                //System.out.println("rs.getString(issue_num)"+rs.getString("issue_num"));
                bom.setIssue_num(rs.getString("issue_num"));
                bom.setId(rs.getString("id"));
                bom.setItem_num(rs.getString("item_num"));
                list.add(bom);
            }rs.close();
        }catch (Exception ex) {
        	System.out.println("BomBeanDao.getBom()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	/**读BOM中同层中同父下没有这个物料*/
	public String getBom_issue(String product_id,String item_id ,String level_id,String father_item_id,String fid,String issue_like) {
		
        String sql = "select * from meteor.ebom where product_id='"+ product_id+"' and item_id='"+item_id+"' and level_id='"+level_id
                      +"' and father_item_id='"+father_item_id+"' and fid='"+fid+"' and issue_num like'"+issue_like+"%'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        //ArrayList list = new ArrayList();
        String issue_num="";
        try {
        	if (rs.next()) {
                //Bom bom = new Bom();
                //System.out.println("rs.getString(issue_num)"+rs.getString("issue_num"));
                issue_num = rs.getString("issue_num");
                //bom.setId(rs.getString("id"));
                //bom.setItem_num(rs.getString("item_num"));
                //list.add(bom);
            }rs.close();
        }catch (Exception ex) {
        	System.out.println("BomBeanDao.getBom()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return issue_num;
    }
	/**存入表BOM*/
	public void InputBom(String product_id,String issue_num,String item_id,String id,String father_item_id,String fid,String level_id,String item_num,String scrap,String memo) {
		
		sql_data sqlbean = new sql_data();
		
		String sql = "INSERT INTO work.ebom(product_id,issue_num,item_id,id,father_item_id,fid,level_id,item_num,scrap,memo)"+
        " Values ('"+product_id+"','"+issue_num+"','"+item_id+"','"+id+"','"+father_item_id+"','"+fid+"','"+level_id
        +"','"+item_num+"','"+scrap+"','"+memo+"')";
		//System.out.println("InputBom:"+sql);
        try {
        	    sqlbean.executeUpdate(sql);
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.InputBom()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	
	//--------------------fy
	/**存入表partplan*/
	public void InputPartplan(String orderid,String equipment_drawid,String issue_num,String plan_id,String item_id,String quality_id,String father_item_id,String level_id,String partnum,String part_begintime,String part_endtime,String part_status,String depatch_pro,String is_receive,String reduce_rate,String jigstatus,String rowstatus,String standardstatus) {
		
		sql_data sqlbean = new sql_data();
		
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
		//ParsePosition pos = new ParsePosition(0);
		//ParsePosition pos1 = new ParsePosition(0);
		//Date part_begintime2=formatter.parse(part_begintime,pos);
		//Date part_endtime2=formatter.parse(part_endtime,pos1);
		
		//System.out.println("INSERT INTO meteor.partplan(orderid,equipment_drawid,issue_num,plan_id,item_id,quality_id,father_item_id,level_id,partnum,part_begintime,part_endtime,part_status,depatch_pro,is_receive,reduce_rate,jigstatus,rowstatus,standardstatus)"+
		//        " Values ('"+orderid+"','"+equipment_drawid+"','"+issue_num+"','"+plan_id+"','"+item_id+"','"+quality_id+"','"+father_item_id+"','"+level_id+"','"+partnum+"','"+part_begintime+"','"+part_endtime+"','"+part_status+"','"+depatch_pro+"','"+is_receive+"','"+reduce_rate+"','"+jigstatus+"','"+rowstatus+"','"+standardstatus+"')");
		String sql = "INSERT INTO meteor.partplan(orderid,equipment_drawid,issue_num,plan_id,item_id,quality_id,father_item_id,level_id,partnum,part_begintime,part_endtime,part_status,depatch_pro,is_receive,reduce_rate,jigstatus,rowstatus,standardstatus)"+
        " Values ('"+orderid+"','"+equipment_drawid+"','"+issue_num+"','"+plan_id+"','"+item_id+"','"+quality_id+"','"+father_item_id+"','"+level_id+"','"+partnum+"',to_date( '"+part_begintime+"', 'yyyy-mm-dd '),to_date( '"+part_endtime+"', 'yyyy-mm-dd '),'"+part_status+"','"+depatch_pro+"','"+is_receive+"','"+reduce_rate+"','"+jigstatus+"','"+rowstatus+"','"+standardstatus+"')";
		
        try {
        	    sqlbean.executeUpdate(sql);
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.InputPartplan()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	
	//--------------------fy
	/**存入表combineplan*/
	public void Inputcombineplan(String flight_type,String product_id,String father_item_id,String item_id,String item_num) {
		
		sql_data sqlbean = new sql_data();
		//System.out.println("INSERT INTO meteor.partplan(orderid,equipment_drawid,issue_num,plan_id,item_id,quality_id,father_item_id,level_id,partnum,part_begintime,part_endtime,part_status,depatch_pro,is_receive,reduce_rate,jigstatus,rowstatus,standardstatus)"+
		//        " Values ('"+orderid+"','"+equipment_drawid+"','"+issue_num+"','"+plan_id+"','"+item_id+"','"+quality_id+"','"+father_item_id+"','"+level_id+"','"+partnum+"','"+part_begintime+"','"+part_endtime+"','"+part_status+"','"+depatch_pro+"','"+is_receive+"','"+reduce_rate+"','"+jigstatus+"','"+rowstatus+"','"+standardstatus+"')");
		String sql = "INSERT INTO meteor.combineplan(flight_type,product_id,father_item_id,item_id,item_num,id)"+
        " Values ('"+flight_type+"','"+product_id+"','"+father_item_id+"','"+item_id+"','"+item_num+"','0')";
		
        try {
        	    sqlbean.executeUpdate(sql);
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.Inputcombineplan()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	//--------------------fy
	/**存入表combineplan*/
	public void Inputcombineplan2(String item_id,String item_num) {
		
		sql_data sqlbean = new sql_data();
		//System.out.println("INSERT INTO meteor.partplan(orderid,equipment_drawid,issue_num,plan_id,item_id,quality_id,father_item_id,level_id,partnum,part_begintime,part_endtime,part_status,depatch_pro,is_receive,reduce_rate,jigstatus,rowstatus,standardstatus)"+
		//        " Values ('"+orderid+"','"+equipment_drawid+"','"+issue_num+"','"+plan_id+"','"+item_id+"','"+quality_id+"','"+father_item_id+"','"+level_id+"','"+partnum+"','"+part_begintime+"','"+part_endtime+"','"+part_status+"','"+depatch_pro+"','"+is_receive+"','"+reduce_rate+"','"+jigstatus+"','"+rowstatus+"','"+standardstatus+"')");
		String sql = "INSERT INTO meteor.combineplan(item_id,item_num,id)"+
        " Values ('"+item_id+"','"+item_num+"','1')";
		
        try {
        	    sqlbean.executeUpdate(sql);
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.Inputcombineplan2()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	//--------------------fy
	/**存入表addtree*/
	public void Inputaddtree(String flight_type,String product_id,String issue_num,String issue,String lot,String sortie) {
		
		sql_data sqlbean = new sql_data();
		//System.out.println("INSERT INTO meteor.partplan(orderid,equipment_drawid,issue_num,plan_id,item_id,quality_id,father_item_id,level_id,partnum,part_begintime,part_endtime,part_status,depatch_pro,is_receive,reduce_rate,jigstatus,rowstatus,standardstatus)"+
		//        " Values ('"+orderid+"','"+equipment_drawid+"','"+issue_num+"','"+plan_id+"','"+item_id+"','"+quality_id+"','"+father_item_id+"','"+level_id+"','"+partnum+"','"+part_begintime+"','"+part_endtime+"','"+part_status+"','"+depatch_pro+"','"+is_receive+"','"+reduce_rate+"','"+jigstatus+"','"+rowstatus+"','"+standardstatus+"')");
		String sql = "INSERT INTO meteor.addtree(flight_type,product_id,issue_num,issue,lot,sortie)"+
        " Values ('"+flight_type+"','"+product_id+"','"+issue_num+"','"+issue+"','"+lot+"','"+sortie+"')";
		
        try {
        	    sqlbean.executeUpdate(sql);
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.Inputaddtree()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	//--------------------fy
	/**修改表partplan*/
	public void UpdatePartplan(String orderid,String equipment_drawid,String issue_num,String plan_id,String item_id,String quality_id,String father_item_id,String level_id,String partnum,String part_begintime,String part_endtime,String part_status,String depatch_pro,String is_receive,String reduce_rate,String jigstatus,String rowstatus,String standardstatus) {
		
		sql_data sqlbean = new sql_data();
		
		String sql = "update meteor.partplan set orderid='"+orderid+"' where plan_id ='"+plan_id+"'";
		
        try {
        	    sqlbean.executeUpdate(sql);
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.UpdatePartPlan()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }
	/**存入表BOM*/
	public int getMaxId(String product_id,String item_id,String level_id) {
        int i = 0;
        String sql= "SELECT max(distinct id) as a  FROM meteor.ebom WHERE product_id='"+ product_id
                                       +"' and item_id='"+item_id+"' and level_id='"+level_id+"' ";
		sql_data sqlbean = new sql_data();
		ResultSet rs = sqlbean.executeQuery(sql);
        try {
        	if (rs.next()) {
        		i = Integer.parseInt(rs.getString("a"));
        	}rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getMaxId()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return i;
    }
//----------------------------fy
	/**更新partplan*/
	public void updatepartplan(String sql) {
		sql_data sqlbean = new sql_data();
        try {
        	sqlbean.executeUpdate(sql);	
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.updatepartplan()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }

	
//-----------------fy
	/**删除partplan*/
	public void update2(String sql) {
		sql_data sqlbean = new sql_data();
        try {
        	sqlbean.executeUpdate(sql);	
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.update2()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
    }	
	/**更新BOM*/
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
	/**得到物料类型*/
	public String getItemType(String item_id) {
		String value = "";
        String sql= "select item_typedesc from meteor.item i,meteor.itemtype p where i.itemid='"+item_id+"' and p.item_typeid=i.item_typeid";
		sql_data sqlbean = new sql_data();
		ResultSet rs = sqlbean.executeQuery(sql);
        try {
        	if (rs.next()) {
        		value = rs.getString(1);
        	}rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getItemType()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return value;
    }
	/**得到物料名称*/
	public String getItemName(String item_id) {
		String value = "";
        String sql= "select item_name from meteor.item where itemid='"+item_id+"'";
		sql_data sqlbean = new sql_data();
		ResultSet rs = sqlbean.executeQuery(sql);
        try {
        	if (rs.next()) {
        		value = rs.getString(1);
        	}rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getItemName()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return value;
    }
    /**得到material_mark*/
	public String getMatMark(String item_id) {
		String value = "";
        String sql= "select material_mark from meteor.item where itemid='"+item_id+"'";
		sql_data sqlbean = new sql_data();
		ResultSet rs = sqlbean.executeQuery(sql);
        try {
        	if (rs.next()) {
        		value = rs.getString(1);
        	}rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getMatMark()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return value;
    }
	 /**得到最大的LevelId*/
	public int getMaxLevelId(String product_id) {
		int value = 0;
        String sql= "SELECT MAX(distinct level_id) as a  FROM work.ebom WHERE product_id='"+ product_id+"'";
        System.out.println("BomBeanDao的getMaxlevelId方法中的sql语句为："+sql);
		sql_data sqlbean = new sql_data();
		ResultSet rs = sqlbean.executeQuery(sql);
        try {
        	if (rs.next()) {
        		value = Integer.parseInt(rs.getString("a"));
        	}rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getMaxLevelId()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return value;
    }
	//--------------------------------------------fy
	/**从cbom得到最小的LevelId*/
	public int getMinLevelId(String product_id) {
		int value = 0;
        String sql= "SELECT MIN(distinct level_id) as a  FROM meteor.cbom WHERE product_id='"+ product_id+"'";
		sql_data sqlbean = new sql_data();
		ResultSet rs = sqlbean.executeQuery(sql);
        try {
        	if (rs.next()) {
        		value = Integer.parseInt(rs.getString("a"));
        	}rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getMinLevelId()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return value;
    }
	
	 /**得到最大的LevelId*/
	public int getMaxLevelId9(String flight_type) {
		int value = 0;
        String sql= "SELECT MAX(distinct b.level_id) as a  FROM meteor.ebom b,meteor.issue_deploy i " +
        		"WHERE i.flight_type='"+ flight_type+"' and i.memo2='1' and b.product_id=i.product_id";
		sql_data sqlbean = new sql_data();
		ResultSet rs = sqlbean.executeQuery(sql);
        try {
        	if (rs.next()) {
        		value = Integer.parseInt(rs.getString("a"));
        	}rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getMaxLevelId()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return value;
    }
	/**读BOM_del数据*/
	public ArrayList getBomDel(int level_id) {
		String level=level_id+"";
		if(level.length()==1){level="0"+level;}
        String sql = "select * from meteor.bom_del where level_id='"+level+"'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        ArrayList list = new ArrayList();
        try {
        	while (rs.next()) {
                Bom bom = new Bom();
                bom.setItem_id(rs.getString("item_id"));
                bom.setId(rs.getString("id"));
                list.add(bom);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getBomDel()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	/**从BOM选择数据*/
	public ArrayList selBom(String product_id,String father_item_id,String fid,int level_id,String issue_num) {
		String level=level_id+"";
		if(level.length()==1){level="0"+level;}
        String sql = "select * from meteor.ebom where product_id='"+product_id+"' and father_item_id='"+father_item_id+
                       "' and fid='"+fid+"' and level_id='"+level+"' and issue_num like '"+issue_num+"%'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        ArrayList list = new ArrayList();
        try {
        	while (rs.next()) {
                Bom bom = new Bom();
                bom.setItem_id(rs.getString("item_id"));
                bom.setId(rs.getString("id"));
//                bom.setFather_item_id(rs.getString("father_item_id"));
//                bom.setFid(rs.getString("fid"));
                bom.setIssue_num(rs.getString("issue_num"));
                list.add(bom);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.selBom()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	/**从BOM选择数据BomList 用于BomTreeDao*/
	public ArrayList getBomList(String product_id,int level_id,String issue_num) {
		String level=level_id+"";
		if(level.length()==1){level="0"+level;}
        String sql = "SELECT b.issue_num, b.item_id,b.id,b.father_item_id,b.fid,i.item_name from work.ebom b,work.item i "
	         +"where b.product_id='"+ product_id+"' and b.level_id='"+level+"' and b.issue_num = '"+issue_num+"' and i.item_id=b.item_id ORDER BY B.ITEM_ID";
        System.out.println("BomBeanDao的getBomList方法中的sql语句为："+sql);
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
                bom.setItem_name(rs.getString("item_name"));
                list.add(bom);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getBomList()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	
	//------fy
	/**复制用的*/
	public ArrayList getBomList2(String product_id,String issue_100,String father_item_id,int level_id,String fid) {
		String level=level_id+"";
		if(level.length()==1){level="0"+level;}
	
        String sql = "SELECT * from meteor.ebom "
	         +"where product_id='"+ product_id+"' and level_id='"+level+"' and issue_num='"+issue_100+"' and father_item_id='"+father_item_id+"' and fid='"+fid+"' ORDER BY ITEM_ID";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        ArrayList list = new ArrayList();
        DealString ds = new DealString();
        try {
        	while (rs.next()) {
        		
                Bom bom = new Bom();
                bom.setProduct_id(ds.toString(rs.getString("product_id")));
                bom.setIssue_num(ds.toString(rs.getString("issue_num")));
                bom.setItem_id(ds.toString(rs.getString("item_id")));
                bom.setFather_item_id(ds.toString(rs.getString("father_item_id")));
                bom.setLevel_id(ds.toString(rs.getString("level_id")));
                bom.setId(ds.toString(rs.getString("id")));
                bom.setFid(ds.toString(rs.getString("fid")));
                bom.setItem_num(ds.toString(rs.getString("item_num")));
                bom.setScrap(ds.toString(rs.getString("scrap")));
                bom.setIs_rl(ds.toString(rs.getString("is_rl")));
                bom.setRoute(ds.toString(rs.getString("route")));
                list.add(bom);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getBomList()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	//------fy
	/**复制BOM用的*/
	public ArrayList getBomList3(String product_id,String issue_100,String item_id,String father_item_id,String level_id,String id,String fid) {
		
        String sql = "SELECT * from meteor.ebom "
	         +"where product_id='"+ product_id+"' and level_id='"+level_id+"' and issue_num='"+issue_100+"' and item_id='"+item_id+"' and father_item_id='"+father_item_id+"' and id='"+id+"' and fid='"+fid+"' ORDER BY ITEM_ID";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        ArrayList list = new ArrayList();
        DealString ds = new DealString();
        try {
        	while (rs.next()) {
        		
                Bom bom = new Bom();
                bom.setProduct_id(ds.toString(rs.getString("product_id")));
                bom.setIssue_num(ds.toString(rs.getString("issue_num")));
                bom.setItem_id(ds.toString(rs.getString("item_id")));
                bom.setFather_item_id(ds.toString(rs.getString("father_item_id")));
                bom.setLevel_id(ds.toString(rs.getString("level_id")));
                bom.setId(ds.toString(rs.getString("id")));
                bom.setFid(ds.toString(rs.getString("fid")));
                bom.setItem_num(ds.toString(rs.getString("item_num")));
                bom.setScrap(ds.toString(rs.getString("scrap")));
                bom.setIs_rl(ds.toString(rs.getString("is_rl")));
                bom.setRoute(ds.toString(rs.getString("route")));
                list.add(bom);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getBomList()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	
	  //----------------------------------------fy
    
	  //----------------------------------------fy
     public ArrayList getfinishreport(String product_id,String issue_num,String item_id,String father_item_id,String level_id) {
    	
        String sql = "SELECT * from meteor.finish_report "
	         +"where equipment_drawid='"+ product_id+"' and level_id='"+level_id+"' and issue_num='"+issue_num+"' and item_id='"+item_id+"' and father_item_id='"+father_item_id+"' ORDER BY PLAN_ID";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        ArrayList list = new ArrayList();
        DealString ds = new DealString();
        try {
        	while (rs.next()) {
        		
                FinishReport finishreport = new FinishReport();
                finishreport.setOrderid(ds.toString(rs.getString("orderid")));
                finishreport.setEquipment_drawid(ds.toString(rs.getString("equipment_drawid")));
                finishreport.setIssue_num(ds.toString(rs.getString("issue_num")));
                finishreport.setPlan_id(ds.toString(rs.getString("plan_id")));
                finishreport.setItem_id(ds.toString(rs.getString("item_id")));
                finishreport.setOper_id(ds.toString(rs.getString("oper_id")));
                finishreport.setFather_item_id(ds.toString(rs.getString("father_item_id")));
                finishreport.setSheetid(ds.toString(rs.getString("sheetid")));
                finishreport.setLevel_id(ds.toString(rs.getString("level_id")));
                finishreport.setQuality_id(ds.toString(rs.getString("quality_id")));
                finishreport.setPlan_num(ds.toString(rs.getString("plan_num")));
                finishreport.setPass_num(ds.toString(rs.getString("pass_num")));
                finishreport.setFailure_num(ds.toString(rs.getString("failure_num")));
                finishreport.setBegin_time(ds.toString(rs.getString("begin_time")));
                finishreport.setFinish_time(ds.toString(rs.getString("finish_time")));
                finishreport.setReport_time(ds.toString(rs.getString("report_time")));
                finishreport.setEmp_id2(ds.toString(rs.getString("emp_id2")));
                finishreport.setPracticalhours(ds.toString(rs.getString("practicalhours")));
                finishreport.setPlan_hours(ds.toString(rs.getString("plan_hours")));
                finishreport.setFinish_num(ds.toString(rs.getString("finish_num")));
                list.add(finishreport);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getfinishreport()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	  //----------------------------------------fy
     public ArrayList getcombineplan() {
    	
        String sql = "SELECT * from meteor.combineplan "
	         +"where id='0' ORDER BY item_num";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        ArrayList list = new ArrayList();
        DealString ds = new DealString();
        try {
        	while (rs.next()) {
        		
        		CombinePlanBom combineplanbom = new CombinePlanBom();
        		combineplanbom.setFlight_type(ds.toString(rs.getString("flight_type")));
        		combineplanbom.setProduct_id(ds.toString(rs.getString("product_id")));
        		combineplanbom.setFather_item_id(ds.toString(rs.getString("father_item_id")));
        		combineplanbom.setItem_id(ds.toString(rs.getString("item_id")));
        		combineplanbom.setItem_num(ds.toString(rs.getString("item_num")));
                
                list.add(combineplanbom);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getcombineplan()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
     
	  //----------------------------------------fy
     public ArrayList getcombineplan2() {
    	
        String sql = "SELECT * from meteor.combineplan "
	         +"where  id='1' ORDER BY item_num";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        ArrayList list = new ArrayList();
        DealString ds = new DealString();
        try {
        	while (rs.next()) {
        		
        		CombinePlanBom combineplanbom = new CombinePlanBom();
        		
        		combineplanbom.setItem_id(ds.toString(rs.getString("item_id")));
        		combineplanbom.setItem_num(ds.toString(rs.getString("item_num")));
                
                list.add(combineplanbom);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getcombineplan2()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	  //----------------------------------------fy
     public ArrayList getaddtree2(String product_id) {
    	//System.out.println("进入了getaddtree");
        String sql = "SELECT * from meteor.addtree "+"where product_id='"+ product_id+"' ORDER BY flight_type";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        ArrayList list = new ArrayList();
        DealString ds = new DealString();
        try {
        	while (rs.next()) {
        		
        		AddTree addtree = new AddTree();
                addtree.setFlight_type(ds.toString(rs.getString("flight_type")));
                addtree.setProduct_id(ds.toString(rs.getString("product_id")));
                addtree.setIssue_num(ds.toString(rs.getString("issue_num")));
                addtree.setIssue(ds.toString(rs.getString("issue")));
                addtree.setLot(ds.toString(rs.getString("lot")));
                addtree.setSortie(ds.toString(rs.getString("sortie")));
                list.add(addtree);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getaddtree()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
     
     
	  //----------------------------------------fy
     public ArrayList getaddtree() {
    	//System.out.println("进入了getaddtree");
        String sql = "SELECT * from meteor.addtree "+"ORDER BY flight_type";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        ArrayList list = new ArrayList();
        DealString ds = new DealString();
        try {
        	while (rs.next()) {
        		
        		AddTree addtree = new AddTree();
                addtree.setFlight_type(ds.toString(rs.getString("flight_type")));
                addtree.setProduct_id(ds.toString(rs.getString("product_id")));
                addtree.setIssue_num(ds.toString(rs.getString("issue_num")));
                addtree.setIssue(ds.toString(rs.getString("issue")));
                addtree.setLot(ds.toString(rs.getString("lot")));
                addtree.setSortie(ds.toString(rs.getString("sortie")));
                list.add(addtree);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getaddtree()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }

	     
	//------fy
	/**复制AO用的*/
	public ArrayList getBomItem_id() {
		
        String sql = "SELECT distinct item_id from meteor.CBOM";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        ArrayList list = new ArrayList();
        
        try {
        	while (rs.next()) {
                Bom bom = new Bom();
                bom.setItem_id(rs.getString("item_id"));
                list.add(bom);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getBomList()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	/**从BOM选择数据BomList 用于BomTreeDao*/
	public ArrayList getBomList9(int level_id,String issue_num,String flight_type) {
		String level=level_id+"";
		if(level.length()==1){level="0"+level;}
        String sql = "SELECT b.product_id,b.issue_num, b.item_id,b.id,b.father_item_id,b.fid,i.item_name" +
        		" from meteor.ebom b,meteor.issue_deploy d,meteor.item i "
	            +"where i.flight_type='"+flight_type+"' and b.level_id='"+level+"' and b.issue_num like '"+issue_num+"%' and i.itemid=b.item_id " +
	            " and b.product_id=d.product_id and d.memo2='1' ORDER BY B.ITEM_ID";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        ArrayList list = new ArrayList();
        try {
        	while (rs.next()) {
                Bom bom = new Bom();
                bom.setProduct_id(rs.getString("product_id"));
                bom.setItem_id(rs.getString("item_id"));
                bom.setId(rs.getString("id"));
                bom.setFather_item_id(rs.getString("father_item_id"));
                bom.setFid(rs.getString("fid"));
                bom.setIssue_num(rs.getString("issue_num"));
                bom.setItem_name(rs.getString("item_name"));
                list.add(bom);
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getBomList()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return list;
    }
	/**判断issue_deploy中是否有FLIGHTTYPE*/
	public boolean getFlightType(String flight_type) {
		
        String sql = "select flight_type from meteor.issue_deploy where flight_type like '"+flight_type+"%'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        boolean isin=false;
        try {
            if (rs.next()) {
            	isin=true;
            }rs.close();
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.getFlightType()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
    }
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
        	System.out.println("BomBeanDao.isin_Item()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
    }
	/**判断employ中是否有该员工*/
	public boolean isin_Employee(String staff_code) {
		
        String sql = "select staff_code from meteor.employee_info where staff_code='"+staff_code+"'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        boolean isin=false;
        try {
            if (rs.next()) {
            	isin=true;
            }rs.close();
//            System.out.println("isin_Item: "+isin);
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.isin_Employee()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
    }
	/**判断itemtime中是否有该物料*/
	public boolean isin_ItemTime(String itemid) {
		
        String sql = "select item_id from meteor.ITEM_TIME where item_id='"+itemid+"'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        boolean isin=false;
        try {
            if (rs.next()) {
            	isin=true;
            }rs.close();
//            System.out.println("isin_Item: "+isin);
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.isin_ItemTime()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
    }
	/**判断FO中是否有此数据*/
	public boolean isin_FO(String item_id){
		sql_data sqlbean=new sql_data();
		String sql = "select item_id from meteor.FO where item_id='"+item_id+"' or product_id='"+item_id+"'";
		ResultSet rs = sqlbean.executeQuery(sql);
		boolean isin = false;
		try{
			if(rs.next()){
				isin = true;
			}rs.close();
		}catch(Exception e){
			System.out.println("BomBeanDao.isin_FO()处理时出错；错误为："+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return isin;
	}
	/**读fo工序名称 中 类似“准备的工序”*/
	public String getDept_id(String OPER_DEPART){
		sql_data sqlbean=new sql_data();
		String dept_no = OPER_DEPART;
		String sql = "select dept_id from meteor.CAPP_DEPT where dept_no='"+dept_no+"'";
		ResultSet rs = sqlbean.executeQuery(sql);
		try{
			if(rs.next()){
				dept_no = rs.getString("dept_id");
			}rs.close();
		}catch(Exception e){
			System.out.println("BomBeanDao.getDept_id()处理时出错；错误为："+e);
		}finally{
			sqlbean.closeConn(); 
		}
		return dept_no;
	}
	/**判断itemtime中是否有该物料*/
	public boolean isin_Capp_share(String itemid) {
		
        String sql = "select item_id from meteor.Capp_share where item_id='"+itemid+"'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        boolean isin=false;
        try {
            if (rs.next()) {
            	isin=true;
            }rs.close();
//            System.out.println("isin_Item: "+isin);
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.isin_Capp_share()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
    }
	/**判断bom中是否有该物料*/
	public boolean isin_Bom(String product_id,String issue_num) {
		
        String sql = "select * from work.ebom where product_id='"+product_id+"' and issue_num='"+issue_num+"'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        boolean isin=false;
        try {
            if (rs.next()) {
            	isin=true;
            }rs.close();
//            System.out.println("isin_Bom: "+isin);
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.isin_Bom()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
    }
	//判断item表中是否有该物料
public boolean isin_item(String product_id) {
		
        String sql = "select * from work.item where item_id='"+product_id+"'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        boolean isin=false;
        try {
            if (rs.next()) {
            	isin=true;
            }rs.close();
//            System.out.println("isin_Bom: "+isin);
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.isin_Bom()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
    }
   //判断bom中该物料是否是其他物料的父物料
public boolean isin_fbom(String product_id) {
	
    String sql = "select * from work.ebom where father_item_id='"+product_id+"'";
    sql_data sqlbean = new sql_data();
    ResultSet rs = sqlbean.executeQuery(sql);
    boolean isin=false;
    try {
        if (rs.next()) {
        	isin=true;
        }rs.close();
//        System.out.println("isin_Bom: "+isin);
    } catch (Exception ex) {
    	System.out.println("BomBeanDao.isin_Bom()处理时出错；错误为："+ex);
    } finally {
    	sqlbean.closeConn();
    }
    return isin;
}

	/**判断BOM中同层同父下是否有该物料*/
	public boolean inBom(String product_id) {
		
        String sql = "select * from meteor.ebom where product_id='"+product_id+"'";
        sql_data sqlbean = new sql_data();
        ResultSet rs = sqlbean.executeQuery(sql);
        boolean isin=false;
        try {
            if (rs.next()) {
            	isin=true;
            }rs.close();
//            System.out.println("isin_Bom: "+isin);
        } catch (Exception ex) {
        	System.out.println("BomBeanDao.isin_Bom()处理时出错；错误为："+ex);
        } finally {
        	sqlbean.closeConn();
        }
        return isin;
    }
}
