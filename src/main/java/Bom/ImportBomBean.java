package Bom;

import java.util.*;


import cfmes.bom.dao.BomBeanDao;
import cfmes.bom.dao.BomImportDao;
import cfmes.bom.entity.Bom;
import cfmes.bom.entity.MDMAO;
import cfmes.bom.entity.TempBom;
import cfmes.bom.entity.MDMWorkPlace;
import cfmes.bom.entity.Pbom;
import cfmes.util.DealString;
import cfmes.util.sql_data;
import Bom.Bom_Bean;

public class ImportBomBean {
	DealString ds = new DealString();
	String product_id=null;
	String flight_type=null;
	String lot=null;
	String sortie=null;
	String workplace=null;
	String bom_other_id;
	Bom_Bean bom_bean = new Bom_Bean();
/**	���ݲ�ѯ����flight_type, product_id, lot��part_route���Ƿ���ƥ������*/
	public boolean isin(String flight_type,String product_id,String lot){
		BomImportDao bomimportdao=new BomImportDao();
		TempBom tempbom= new TempBom();
		boolean isin=false;
		try{
            ArrayList array=bomimportdao.getPART_ROUTE(flight_type, product_id, lot);
			if(array.size()!=0 && array!=null){
				isin=true;
			    bomimportdao.TableExist("TEMP_BOM");
			    bomimportdao.TableExist("PBOM");
			    bomimportdao.CreatTable("CREATE TABLE meteor.temp_bom ( PRODUCTCODE      VARCHAR2(5)     NOT NULL,"
					                                               +"PARENTITEM     VARCHAR2(192)   NOT NULL,"
					                                               +"CHILDITEM      VARCHAR2(192)   NOT NULL,"
					                                               +"CHILDVERSION   VARCHAR2(24)            ,"
					                                               +"STARTPLANENO   VARCHAR2(10)    NOT NULL,"
					                                               +"ENDPLANENO     VARCHAR2(10)    NOT NULL,"
					                                               +"PERQUITY       VARCHAR2(24)            ,"
					                                               +"ITEMNAME       VARCHAR2(192),"
					                                               +"ROUTE       VARCHAR2(192)            )");
			    bomimportdao.CreatTable("ALTER TABLE meteor.temp_bom ADD CONSTRAINT PK_TEMP_BOM PRIMARY KEY"
					                  +" (PRODUCTCODE,PARENTITEM,CHILDITEM,STARTPLANENO,ENDPLANENO)");
			    
			    for(int i = 0;i<array.size();i++){
	                 tempbom = (TempBom)array.get(i);
	                 bomimportdao.InputTempBom(tempbom);
	            }  
			}	
		}catch(Exception e){
			System.out.println("ImportBomBean.isin()����ʱ���?����Ϊ��"+e);
		}
		return isin;
	}
	/**	����ε�����BOM.����ѯ�����Ķ���temp_bom�еļ�¼һ��һ���Ĵ���pbom�У�Ȼ������ÿ����¼������һ���汾��BOM*/
	public void second(String str1,String str2,String str3){
		BomImportDao bomimportdao=new BomImportDao();
		TempBom tempbom2= new TempBom();
		this.flight_type=str1;
		this.product_id =str2;
		this.lot=str3;

		try{
			//������ʱ��pbom������ѭ������bom���м��
			
			bomimportdao.CreatTable("CREATE TABLE meteor.pbom ( PRODUCTCODE    VARCHAR2(5)     NOT NULL,"
					                                         +"PARENTITEM     VARCHAR2(192)   NOT NULL,"
					                                         +"CHILDITEM      VARCHAR2(192)   NOT NULL,"
					                                         +"LEVEL_ID       VARCHAR2(24)    NOT NULL,"
					                                         +"FID            VARCHAR2(4)             ,"
					                                         +"CHILDVERSION   VARCHAR2(24)            ,"
					                                         +"STARTPLANENO   VARCHAR2(10)    NOT NULL,"
					                                         +"ENDPLANENO     VARCHAR2(10)    NOT NULL,"
					                                         +"PERQUITY       VARCHAR2(24)            ,"
					                                         +"ITEMNAME       VARCHAR2(192)           ,"
					                                         +"ROUTE       VARCHAR2(192)              )");
			bomimportdao.CreatTable("ALTER TABLE meteor.pbom ADD CONSTRAINT PK_PBOM PRIMARY KEY "
					                 +"(PRODUCTCODE,PARENTITEM,CHILDITEM,LEVEL_ID,STARTPLANENO,ENDPLANENO)");
			
			int num = bomimportdao.Issue_deployNum(flight_type, product_id);//�жϸû��Ͳ�Ʒ���ж��ٸ����
			if(num > 1){ //���û��Ͳ�Ʒ���ж����Σ���bom����ɾ��λ�ô�ֻ������
				bomimportdao.delTable(flight_type,product_id,lot);
				//bomimportdao.delTable("delete from meteor.issue_deploy where flight_type='"+flight_type+"' and product_id='"+product_id+"' and lot='"+lot+"'");
			}else{//���û��Ͳ�Ʒ��ֻ��һ����Σ���ɾ��bom
				bomimportdao.delTable("delete from meteor.ao_oper where flighttpye='"+flight_type+"' and produceid='"+product_id+"'");
				bomimportdao.delTable("delete from meteor.aomain where flighttype='"+flight_type+"' and productid='"+product_id+"'");
				bomimportdao.delTable("delete from meteor.fo_detail where flight_type='"+flight_type+"' and product_id='"+product_id+"'");
				bomimportdao.delTable("delete from meteor.fo where flight_type='"+flight_type+"' and product_id='"+product_id+"'");
				
				bomimportdao.delTable("delete from meteor.T_OPPROEQUIP where flight_type='"+flight_type+"' and product_id='"+product_id+"'");//FO����װ
				bomimportdao.delTable("delete from meteor.T_MDMWORKPLACE where flight_type='"+flight_type+"' and product_id='"+product_id+"'");//��λ�ṹ
				bomimportdao.delTable("delete from meteor.T_MDMAO where flight_type='"+flight_type+"' and product_id='"+product_id+"'");//��λAO�ṹ
				bomimportdao.delTable("delete from meteor.T_AOSTANDARDPART where flight_type='"+flight_type+"' and product_id='"+product_id+"'");//AO��׼��
				bomimportdao.delTable("delete from meteor.T_AOAUXMATLIST where flight_type='"+flight_type+"' and product_id='"+product_id+"'");//AO����
				bomimportdao.delTable("delete from meteor.T_AOPROEQUIPLIST where flight_type='"+flight_type+"' and product_id='"+product_id+"'");//AO����װ
				bomimportdao.delTable("delete from meteor.T_AOOPPROEQUIP where flight_type='"+flight_type+"' and product_id='"+product_id+"'");//AO��װ
				bomimportdao.delTable("delete from meteor.T_AOREQUIREPART where flight_type='"+flight_type+"' and product_id='"+product_id+"'");//AO�������
				
				bomimportdao.delTable("delete from meteor.mater_bom where product_id='"+product_id+"'");
				bomimportdao.delTable("delete from meteor.hmater_bom where product_id='"+product_id+"'");
				bomimportdao.delTable("delete from meteor.tool_bom where product_id='"+product_id+"'");
				bomimportdao.delTable("delete from meteor.ebom where product_id='"+product_id+"'");
				bomimportdao.delTable("delete from meteor.issue_deploy where flight_type='"+flight_type
		                 +"' and product_id='"+product_id+"' and lot='"+lot+"'");
			}
			//int i=0;
			int issue_pos=0;
            ArrayList array=bomimportdao.getTempBom();
			if(array.size()!=0 && array!=null){
			for(int j = 0;j<array.size();j++){
				//i++;//������µİ汾
				for(int k = 1;k <= 100; k++){
					issue_pos = bomimportdao.getMinIssue_pos(flight_type, product_id, k);//�õ���ǰ���Ͳ�Ʒ�¿������Сλ�ú�--------------------------//����
				    if(issue_pos != 0) break;
				}
				//System.out.println("issue_pos��"+issue_pos);
				tempbom2 = (TempBom)array.get(j);
		        bomimportdao.InputPbom(tempbom2,1,"1");
				String b_sortie   = ds.backStr(tempbom2.getSTARTPLANENO(), 2);
				String e_sortie   = ds.backStr(tempbom2.getENDPLANENO(), 2);
				String issue_num  = "P"+lot+"T" + b_sortie;
				bomimportdao.InputISSUE_DEPLOY(flight_type, product_id, lot, b_sortie, e_sortie, issue_num, issue_pos);
                third(issue_pos,issue_num);
                bomimportdao.delTable(tempbom2);
			}
			}
			bomimportdao.DropTable("PBOM");
		}catch(Exception e){
			System.out.println("ImportBomBean.second()����ʱ���?����Ϊ��"+e);
		}
	}

//	ѭ����ȡpbom�е���ݣ���ŵ�bom�У�ֱ��PBOMΪ�ա�
	public void third(int i,String issue_num){
		BomImportDao bomimportdao=new BomImportDao();
		Pbom pbom = new Pbom();
		TempBom tempbom3= new TempBom();
		String father_item_id=null;
		String id ="1";
		String item_type="";
		boolean isnot_null;
		try{
			do{ isnot_null=false;
			    ArrayList array = bomimportdao.getPbom();
			    //if(array.size()!=0 && array!=null)//��˳���ѯ�����һ����¼��
			    	pbom = (Pbom)array.get(0);
				    String PRODUCTCODE  = pbom.getPRODUCTCODE();
				    String PARENTITEM   = pbom.getPARENTITEM();
				    String CHILDITEM    = pbom.getCHILDITEM();
				    String LEVEL_ID     = pbom.getLEVEL_ID();
				    String CHILDVERSION = pbom.getCHILDVERSION();
				    String STARTPLANENO = pbom.getSTARTPLANENO();
				    String ENDPLANENO   = pbom.getENDPLANENO();
				    String PERQUITY     = pbom.getPERQUITY();
				    String ITEMNAME     = pbom.getITEMNAME();
				    String fid          = pbom.getFID();
				    String ROUTE        = pbom.getROUTE();
				    //System.out.println("ROUTE: "+ROUTE);
				if(CHILDITEM.equals(product_id)){
					father_item_id="#";
					//item_type = bomimportdao.Item_type("����");
				}else{
					father_item_id=PARENTITEM;
					//item_type = bomimportdao.Item_type("���");
				}
//				bomimportdao.isin_Item(CHILDITEM,ITEMNAME,item_type);//����meteor item����
//				����meteor BOM����
				bom_bean.setBom_other_id(CHILDITEM);
				Hashtable ht = new Hashtable();
				String s="";
			    for(int k=1;k<=100;k++){       //���� issue_numֵ
			        if(k==i){
			           s=s+'1';
			        }else{
			           s=s+'0';
			        }
			    }
			    String issue_like="";
			    for(int y=1;y<=i;y++){       //���� issue_numֵ
			        if(y==i){
			        	issue_like=issue_like+'1';
			        }else{
			        	issue_like=issue_like+'_';               //������ֶ���� ��Ϊ  1   ��Ϊ���� ��Ϊ0
			        }
			    }
				ht.put("PRODUCT_ID",product_id);
				ht.put("ISSUE_NUM",s);
				ht.put("ISSUE", i+"");
				ht.put("FATHER_ITEM_ID",father_item_id);
				//ht.put("ITEM_ID",CHILDITEM);
				ht.put("FID",fid);
				ht.put("LEVEL_ID",LEVEL_ID);
				ht.put("ITEM_NUM",PERQUITY);
				ht.put("SCRAP","0");
				ht.put("IS_RL","3");
				ht.put("ROUTE",ROUTE);
				ht.put("ISSUE_LIKE",issue_like);
				//String turnback = bom_bean.addBom(ht);
				String[] num = new String[2];
				//System.out.println(turnback);
		        //  num = turnback.split("--");
		          id  = num[0];
		          int level=Integer.parseInt(LEVEL_ID);
		        //System.out.println("level"+level);
		       String dept = ds.strByteCopy(ROUTE, 3);
		       //System.out.println("dept��"+dept);
		       if(dept.equals("280")){//�������280��ʼ��·�� ���� �������FO
//			        ��ʼ����FO����Ϣ�������š����͡���ʼ�ܴΡ���ֹ�ܴ���Ϣ��DOC_EFFSTATE(�ĵ���Ч��״̬��)�в�ѯ��DOCNUMBER,DOCVERSION��Ȼ������FO�����в��PLAN_ID
					String Turn_Back=bomimportdao.getEFF(PRODUCTCODE, CHILDITEM, lot, sortie,"FO");
					if(Turn_Back!=null && !Turn_Back.equals("")){
						System.out.println("��FO");
						String[] num2 = Turn_Back.split("---");
					       String PLAN_ID = num2[0];System.out.println("PLAN_ID:"+PLAN_ID);
					       String FO_NO   = num2[1];//FO��
						bomimportdao.getMd(PLAN_ID,PRODUCTCODE, CHILDITEM, STARTPLANENO, ENDPLANENO,level+1,id);//í��
						bomimportdao.getYcl(PLAN_ID,product_id, CHILDITEM, father_item_id,level,fid,id,issue_num);//ԭ����
						bomimportdao.getFl(PLAN_ID,product_id, CHILDITEM, father_item_id,level,fid,id,issue_num);//����
						bomimportdao.getTool(PLAN_ID,product_id, CHILDITEM, father_item_id,level,fid,id,issue_num);//��װ
						bomimportdao.getOperTool(PLAN_ID,flight_type,product_id,CHILDITEM,issue_num,FO_NO);//����װ
						bomimportdao.getFo(PRODUCTCODE,PLAN_ID,product_id,CHILDITEM,issue_num);//FO ���乤����Ϣ
					}
		       }

                //�����������������뵽PBOM�У�Ȼ��ɾ������� (!(!dept.equals("280") && dept2.equals("280")))
				level= level+1;
				System.out.println(level);
				String dept2 = ds.backStr(ROUTE, 3);	
				if( dept.equals("280") || CHILDITEM.equals(product_id)){//�������280��ͷ��·�� ���������������
					boolean havechild = bomimportdao.havechild(PRODUCTCODE, CHILDITEM, STARTPLANENO, ENDPLANENO);
					if(havechild){
						    ArrayList array2 = bomimportdao.getchild(PRODUCTCODE, CHILDITEM, STARTPLANENO, ENDPLANENO);
							for(int j = 0;j<array2.size();j++){
								tempbom3 = (TempBom)array2.get(j);
								bomimportdao.InputPbom(tempbom3,level,id);
						    }
							if(CHILDITEM.equals(product_id)){
								item_type = bomimportdao.Item_type("����");
							}else{
								item_type = bomimportdao.Item_type("���");
							}
							bomimportdao.isin_Item(CHILDITEM,ITEMNAME,item_type);//����meteor item����
					}else{//û�������ϵĸ����� ������������ '���'���� 
						bomimportdao.isin_Item(CHILDITEM,ITEMNAME,bomimportdao.Item_type("���"));//����meteor item����
					}
				}else{//������ ������������ '���'���� 
					bomimportdao.isin_Item(CHILDITEM,ITEMNAME,bomimportdao.Item_type("���"));//����meteor item����
				}
				
				bomimportdao.delTable(pbom);
				if(bomimportdao.pbom_notnull()){isnot_null=true;}
			}while(isnot_null);
		}catch(Exception e){
			System.out.println("ImportBomBean.third()����ʱ���?����Ϊ��"+e);
		}
	}
	/**�Ƿ��з�������Ĺ�λAO �ṹ*/
	public boolean existMDMWorkPlace(String flight_type,String product_id,String workplace,String lot){
		BomImportDao bomimportdao = new BomImportDao();
		boolean isin = false;
		MDMWorkPlace MDMwork= new MDMWorkPlace();
		try{
            ArrayList array=bomimportdao.getMDMWorkPlace(flight_type,product_id,workplace,lot);
            ArrayList array2=bomimportdao.getMDMMileStone(flight_type,product_id,workplace,lot);
			if((array.size()!=0 && array!=null)||(array2.size()!=0 && array2!=null)){ 
				isin=true;
			    bomimportdao.TableExist("TEMP_MBOM");
			    bomimportdao.TableExist("TEMP_MDMAO");
			    bomimportdao.CreatTable("CREATE TABLE meteor.TEMP_MBOM (flight_type VARCHAR2(5),parentplace VARCHAR2(60),childplace VARCHAR2(60),"
					        +"product_id VARCHAR2(120),issue_num  VARCHAR2(100),workplacename VARCHAR2(60),shop     VARCHAR2(3),"
					        +"lowflage   VARCHAR2(1),  madecenter VARCHAR2(4),  usecenter     VARCHAR2(4), perquity VARCHAR2(4),"
					        +"unitcode   VARCHAR2(6),  leadtime   VARCHAR2(126),milestonecode VARCHAR2(60),note     VARCHAR2(300),"
					        +"startplaneno VARCHAR2(6),endplaneno   VARCHAR2(6))");
			    bomimportdao.CreatTable("ALTER TABLE meteor.TEMP_MBOM ADD CONSTRAINT PK_TEMP_MBOM PRIMARY KEY"
		                  +" (flight_type,product_id,parentplace,childplace,issue_num)");
			    bomimportdao.CreatTable("CREATE TABLE meteor.TEMP_MDMAO (flight_type VARCHAR2(5),product_id VARCHAR2(120),issue_num VARCHAR2(100),"
					        +"workplacecode  VARCHAR2(60),childaocode  VARCHAR2(192),aoname  VARCHAR2(192),parentaocode VARCHAR2(192),"
					        +"rationworktime VARCHAR2(10),planworktime VARCHAR2(10),leadtime VARCHAR2(6),  deptcode     VARCHAR2(10),"
					        +"shop           VARCHAR2(3), madecenter   VARCHAR2(4),usecenter VARCHAR2(4),  perquity     VARCHAR2(4),"
					        +"unitcode       VARCHAR2(6), lowflage     VARCHAR2(1), note     VARCHAR2(300),"
				            +"startplaneno   VARCHAR2(6), endplaneno    VARCHAR2(6))");
			    bomimportdao.CreatTable("ALTER TABLE meteor.TEMP_MDMAO ADD CONSTRAINT PK_TEMP_MDMAO PRIMARY KEY"
		                  +" (flight_type,product_id,workplacecode,childaocode,parentaocode,issue_num)");
//			    for(int i = 0;i<array.size();i++){
//			    	 MDMwork = (MDMWorkPlace)array.get(i);
//	                 bomimportdao.InputTEMP_MBOM(MDMwork);
//	            }
			    if(array.size()!=0 && array!=null){//����λ�����Ƿ��д˹�λ
			    	for(int i = 0;i<array.size();i++){
				    	 MDMwork = (MDMWorkPlace)array.get(i);
		                 bomimportdao.InputTEMP_MBOM(MDMwork);
		            }
			    }else if(array2.size()!=0 && array2!=null){//���λ����û�д˹�λ�����λ�����Ƿ��д˹�λ
			    	for(int i = 0;i<array2.size();i++){
				    	 MDMwork = (MDMWorkPlace)array2.get(i);
		                 bomimportdao.InputTEMP_MBOM(MDMwork);
		            }
			    }
			}	
		}catch(Exception e){
			System.out.println("ImportBomBean.existMDMWorkPlace()����ʱ���?����Ϊ��"+e);
		}
		return isin;
	}
	/**���빤λ�ṹ*/
	public void downMDMWorkPlace(String flight_type,String product_id,String workplace,String lot){
		BomImportDao bomimportdao=new BomImportDao();
		MDMWorkPlace MDMwork= new MDMWorkPlace();
		MDMAO MDMAO= new MDMAO();
		MDMAO MDMAO2= new MDMAO();
		this.flight_type=flight_type;
		this.product_id =product_id;
		this.lot=lot;
        this.workplace=workplace;
        boolean MBOMnot_null;
        boolean MAOnot_null;
		try{
			do{ //���빤λ�ṹ
				System.out.println("���빤λ�ṹ");
				MBOMnot_null=false;
				ArrayList array=bomimportdao.getTEMP_MBOM();//�õ�TEMP_MBOM��ʱ������һ�����
			    MDMwork = (MDMWorkPlace)array.get(0);     
	            bomimportdao.InputT_MDMWORKPLACE(MDMwork);  //��������ݴ�����ݿ�Ĺ�λ����
//				if(!MDMwork.getLowflage().equals("Y")){     //������ӹ�λ �������ʱ��TEMP_MBOM
	            if(bomimportdao.havechild(MDMwork)){
					//System.out.println("���빤λ�ṹ");
					ArrayList array_2 = bomimportdao.getChildMDM(MDMwork);
						for(int j = 0;j<array_2.size();j++){
							MDMWorkPlace MDMwork2 = (MDMWorkPlace)array_2.get(j);
							bomimportdao.InputTEMP_MBOM(MDMwork2);
					    }
				}
				   ArrayList arrayMAO = bomimportdao.getMDMAO(MDMwork);//�õ��˹�λ�µ����AO
	               if(arrayMAO.size()!=0 && arrayMAO!=null){           //���Ϊ�գ������TEMP_MDMAO��ʱ����
	            	   System.out.println("arrayMAO.size():"+arrayMAO.size());
	            	   for(int k = 0;k<arrayMAO.size();k++){
	            		   MDMAO = (MDMAO)arrayMAO.get(k);
	            	       bomimportdao.InputTEMP_MDMAO(MDMAO);
	            	       System.out.println(MDMAO.getAoname());
	            	   }
	            	   downMDMAO();
	               }
	               System.out.println(MDMwork.getIssue_num());
				bomimportdao.delTable(MDMwork);    //ɾ��TEMP_MBOM��ʱ���и������
				if(bomimportdao.TEMP_MBOM_notnull()){MBOMnot_null=true;}// ���TEMP_MBOM��Ϊ�� �����ѭ��
			}while(MBOMnot_null);
			DropTable("TEMP_MDMAO");
		}catch(Exception e){
			System.out.println("ImportBomBean.downMDMWorkPlace()����ʱ���?����Ϊ��"+e);
		}
	}
	/**���빤λAO �ṹ*/
	public void downMDMAO(){
		BomImportDao bomimportdao=new BomImportDao();
		MDMAO MDMAO2= new MDMAO();
        boolean MAOnot_null;
		try{
		    do{
     	       System.out.println("���빤λAO�ṹ");
     	       MAOnot_null=false;
			   ArrayList array_3=bomimportdao.getTEMP_MDMAO();//�õ�TEMP_MDMAO��ʱ������һ�����
			   MDMAO2 = (MDMAO)array_3.get(0);
	               bomimportdao.InputT_MDMAO(MDMAO2);         //��������ݴ�����ݿ�Ĺ�λAO��
				  // if(!MDMAO2.getLowflage().equals("Y")){     //�������AO �������ʱ��TEMP_MDMAO
	               if(bomimportdao.havechild(MDMAO2)){  
					   System.out.println("���빤λAO�ṹ2");
					   ArrayList array_4 = bomimportdao.getChildMDMAO(MDMAO2);
						   for(int j = 0;j<array_4.size();j++){
							   MDMAO MDMAO3 = (MDMAO)array_4.get(j);
							   bomimportdao.InputTEMP_MDMAO(MDMAO3);
					       }
				   }
//				  ��ʼ����AO����Ϣ�������š����͡���ʼ�ܴΡ���ֹ�ܴ���Ϣ��DOC_EFFSTATE(�ĵ���Ч��״̬��)�в�ѯ��DOCNUMBER,DOCVERSION��Ȼ������AO�����в��PLAN_ID
//				   String Turn_Back=bomimportdao.getEFF(MDMAO2.getFlight_type(),MDMAO2.getChildaocode(),MDMAO2.getStartplaneno(),MDMAO2.getEndplaneno(),"AO");
				   String Turn_Back = bomimportdao.getPlanId_test(MDMAO2.getChildaocode());	//�����Ч�Ե���AO���¹���
				   if(Turn_Back!=null && !Turn_Back.equals("")){
					   String[] num = Turn_Back.split("---");
				       String plan_id = num[0];
				       String partno  = num[1];//װ��ͼ��
				       String ao_no   = num[2];//AO���
				       //String[] num2 = partno.split("��"); //String item_id  = num2[0];//���Ϻ�
				       //System.out.println("partno item_id:"+item_id);
				       if(partno!=null && !partno.equals("")){//ֻ��AO��װ��ͼ�ŵĲŵ���AO ������
//				    	 �õ������� ��bom�е�λ��
				    	   String item_id = bomimportdao.getMaxLevel(product_id, partno);
//					    	 �õ������� ��bom�е�λ��
					       if(item_id!=null && !item_id.equals("")){
					        	if(ds.strByteCopy(item_id, 3).equals("DDD")){
					        		item_id = item_id.substring(1, item_id.length());
					        	}
					        	Hashtable ht = bomimportdao.getBomPos(product_id, item_id);
							    bomimportdao.getAoMd(plan_id,flight_type,product_id,MDMAO2.getIssue_num(),item_id,ao_no,ht);//í������׼��
							       //bomimportdao.getAoFl(plan_id,flight_type,product_id,MDMAO2.getIssue_num(),item_id,ao_no,ht);//����
							    bomimportdao.getAoTool(plan_id,flight_type,product_id,MDMAO2.getIssue_num(),item_id,ao_no,ht);//��װ
							    bomimportdao.getAoOperTool(plan_id,flight_type,product_id,MDMAO2.getIssue_num(),item_id,ao_no);//����װ
							    bomimportdao.getAoPart(plan_id,flight_type,product_id,MDMAO2.getIssue_num(),item_id,ao_no);//������ױ�
							    bomimportdao.getAo(plan_id,flight_type,product_id,MDMAO2.getIssue_num(),item_id,ao_no);//AO ���乤����Ϣ
					       }
				       }
				   }
				   //System.out.println("��2"+MDMAO2.getAoname());
				   bomimportdao.delTable(MDMAO2);            //ɾ��TEMP_MDMAO��ʱ���и������
         	   if(bomimportdao.TEMP_MDMAO_notnull()){MAOnot_null=true;}// ���TEMP_MDMAO��Ϊ�� �����ѭ��
		   }while(MAOnot_null);
	    } catch (Exception ex) {
	    	System.out.println("downMDMAO()����ʱ���?����Ϊ��"+ex);
	    }
	}
	/**ɾ����ʱ��*/
	public void DropTable(String tablename){
	    sql_data sqlbean = new sql_data();
	    try {
	    	sqlbean.executeUpdate("DROP TABLE meteor."+tablename+"");
	    } catch (Exception ex) {
	    	System.out.println("DropTable()����ʱ���?����Ϊ��"+ex);
	    } finally {
	    	sqlbean.closeConn();
	    }
	}
	//�ֶ���ӡ�������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������	
	
	/**	���ݲ�ѯ����flight_type, product_id, lot,sortie��part_route���Ƿ���ƥ������*/
	public boolean isin(String flight_type,String product_id,String lot,String sortie){
		BomImportDao bomimportdao=new BomImportDao();
		TempBom tempbom= new TempBom();
		boolean isin=false;
		try{
            
			ArrayList array=bomimportdao.getPART_ROUTE(flight_type, product_id, lot, sortie);//sortie ����λ������ʽ
			if(array.size()!=0 && array!=null){ 
				isin=true;
			    bomimportdao.TableExist("TEMP_BOM");
			    bomimportdao.TableExist("PBOM");
			    bomimportdao.CreatTable("CREATE TABLE meteor.temp_bom ( PRODUCTCODE  VARCHAR2(5)     NOT NULL,"
					                                               +"PARENTITEM     VARCHAR2(192)   NOT NULL,"
					                                               +"CHILDITEM      VARCHAR2(192)   NOT NULL,"
					                                               +"CHILDVERSION   VARCHAR2(24)            ,"
					                                               +"STARTPLANENO   VARCHAR2(10)    NOT NULL,"
					                                               +"ENDPLANENO     VARCHAR2(10)    NOT NULL,"
					                                               +"PERQUITY       VARCHAR2(24)            ,"
					                                               +"ITEMNAME       VARCHAR2(192)           ,"
					                                               +"ROUTE       VARCHAR2(192)               )");
			    bomimportdao.CreatTable("ALTER TABLE meteor.temp_bom ADD CONSTRAINT PK_TEMP_BOM PRIMARY KEY"
					                  +" (PRODUCTCODE,PARENTITEM,CHILDITEM,STARTPLANENO,ENDPLANENO)");
			    
			    for(int i = 0;i<array.size();i++){
	                 tempbom = (TempBom)array.get(i);
	                 bomimportdao.InputTempBom(tempbom);
	            }  
			}	
		}catch(Exception e){
			System.out.println("ImportBomBean.isin()����ʱ���?����Ϊ��"+e);
		}
		return isin;
	}
	/**	�ֹ����һ��BOM.����ѯ�����Ķ���temp_bom�еļ�¼һ��һ���Ĵ���pbom�У�Ȼ������ÿ����¼������һ���汾��BOM*/
	public void second(String str1,String str2,String str3,String str4){
		BomImportDao bomimportdao=new BomImportDao();
		TempBom tempbom2= new TempBom();
		this.flight_type=str1;
		this.product_id =str2;
		this.lot=str3;
        this.sortie=str4;
		try{
			//������ʱ��pbom������ѭ������bom���м��
			bomimportdao.CreatTable("CREATE TABLE meteor.pbom ( PRODUCTCODE    VARCHAR2(5)     NOT NULL,"
					                                         +"PARENTITEM     VARCHAR2(192)   NOT NULL,"
					                                         +"CHILDITEM      VARCHAR2(192)   NOT NULL,"
					                                         +"LEVEL_ID       VARCHAR2(24)    NOT NULL,"
					                                         +"FID            VARCHAR2(4)             ,"
					                                         +"CHILDVERSION   VARCHAR2(24)            ,"
					                                         +"STARTPLANENO   VARCHAR2(10)    NOT NULL,"
					                                         +"ENDPLANENO     VARCHAR2(10)    NOT NULL,"
					                                         +"PERQUITY       VARCHAR2(24)            ,"
					                                         +"ITEMNAME       VARCHAR2(192)           ,"
					                                         +"ROUTE       VARCHAR2(192)               )");
			bomimportdao.CreatTable("ALTER TABLE meteor.pbom ADD CONSTRAINT PK_PBOM PRIMARY KEY "
					                 +"(PRODUCTCODE,PARENTITEM,CHILDITEM,LEVEL_ID,STARTPLANENO,ENDPLANENO)");
			String issue_name = bomimportdao.delTable(flight_type,product_id,lot,sortie);//�õ���ɾȥBOM�İ汾���
			int i=0;
			int issue_pos=0;
            ArrayList array=bomimportdao.getTempBom();
			if(array.size()!=0 && array!=null){
			for(int j = 0;j<array.size();j++){
				i++;//������µİ汾
				for(int k = 1;k <= 100; k++){
					issue_pos = bomimportdao.getMinIssue_pos(flight_type, product_id, k);//�õ���ǰ���Ͳ�Ʒ�¿������Сλ�ú�--------------------------//����
				    if(issue_pos != 0) break;
				}
				//System.out.println("issue_pos��"+issue_pos);
				tempbom2 = (TempBom)array.get(j);
		        bomimportdao.InputPbom(tempbom2,1,"1");

				String b_sortie   = ds.backStr(tempbom2.getSTARTPLANENO(), 2);
				String e_sortie   = ds.backStr(tempbom2.getENDPLANENO(), 2);
				String issue_num  = "P"+lot+"T" + b_sortie;
		        if(issue_name!=null && !issue_name.equals("")){
		        	issue_num = issue_name;
		        }
				bomimportdao.InputISSUE_DEPLOY(flight_type, product_id, lot, b_sortie, e_sortie, issue_num, issue_pos);
                third(issue_pos,issue_num);
                bomimportdao.delTable(tempbom2);
			}}
			bomimportdao.DropTable("PBOM");
		}catch(Exception e){
			System.out.println("ImportBomBean.second()����ʱ���?����Ϊ��"+e);
		}
	}
	/**�Ƿ��з�������Ĺ�λAO �ṹ*/
	public boolean existMDMWorkPlace(String flight_type,String product_id,String workplace,String lot,String sortie){
		BomImportDao bomimportdao = new BomImportDao();
		boolean isin = false;
		MDMWorkPlace MDMwork= new MDMWorkPlace();
		try{
            ArrayList array=bomimportdao.getMDMWorkPlace(flight_type,product_id,workplace,lot,sortie);
            ArrayList array2=bomimportdao.getMDMMileStone(flight_type,product_id,workplace,lot,sortie);
			if((array.size()!=0 && array!=null)||(array2.size()!=0 && array2!=null)){ 
				isin=true;
			    bomimportdao.TableExist("TEMP_MBOM");
			    bomimportdao.CreatTable("CREATE TABLE meteor.TEMP_MBOM (flight_type VARCHAR2(5),parentplace VARCHAR2(60),childplace VARCHAR2(60),"
					        +"product_id VARCHAR2(120),issue_num  VARCHAR2(100),workplacename VARCHAR2(60),shop     VARCHAR2(3),"
					        +"lowflage   VARCHAR2(1),  madecenter VARCHAR2(4),  usecenter     VARCHAR2(4), perquity VARCHAR2(4),"
					        +"unitcode   VARCHAR2(6),  leadtime   VARCHAR2(126),milestonecode VARCHAR2(60),note     VARCHAR2(300),"
					        +"startplaneno VARCHAR2(6),endplaneno   VARCHAR2(6))");
			    bomimportdao.TableExist("TEMP_MDMAO");
			    bomimportdao.CreatTable("CREATE TABLE meteor.TEMP_MDMAO (flight_type VARCHAR2(5),product_id VARCHAR2(120),issue_num VARCHAR2(100),"
					        +"workplacecode  VARCHAR2(60),childaocode  VARCHAR2(192),aoname  VARCHAR2(192),parentaocode VARCHAR2(192),"
					        +"rationworktime VARCHAR2(10),planworktime VARCHAR2(10),leadtime VARCHAR2(6),  deptcode     VARCHAR2(10),"
					        +"shop           VARCHAR2(3), madecenter   VARCHAR2(4),usecenter VARCHAR2(4),  perquity     VARCHAR2(4),"
					        +"unitcode       VARCHAR2(6), lowflage     VARCHAR2(1), note     VARCHAR2(300),"
				            +"startplaneno   VARCHAR2(6), endplaneno    VARCHAR2(6))");
			    if(array.size()!=0 && array!=null){//����λ�����Ƿ��д˹�λ
			    	for(int i = 0;i<array.size();i++){
				    	 MDMwork = (MDMWorkPlace)array.get(i);
		                 bomimportdao.InputTEMP_MBOM(MDMwork);
		            }
			    }else if(array2.size()!=0 && array2!=null){//���λ����û�д˹�λ�����λ�����Ƿ��д˹�λ
			    	for(int i = 0;i<array2.size();i++){
				    	 MDMwork = (MDMWorkPlace)array2.get(i);
		                 bomimportdao.InputTEMP_MBOM(MDMwork);
		            }
			    }
			}	
		}catch(Exception e){
			System.out.println("ImportBomBean.existMbom(this.sortie)����ʱ���?����Ϊ��"+e);
		}
		return isin;
	}
	/**���빤λAO �ṹ*/
	public void downMDMWorkPlace(String flight_type,String product_id,String workplace,String lot,String sortie){
		BomImportDao bomimportdao=new BomImportDao();
		MDMWorkPlace MDMwork= new MDMWorkPlace();
		MDMAO MDMAO= new MDMAO();
		MDMAO MDMAO2= new MDMAO();
		this.flight_type=flight_type;
		this.product_id =product_id;
		this.lot=lot;
        this.workplace=workplace;
        this.sortie=sortie;
        boolean MBOMnot_null;
        boolean MAOnot_null;
		try{
			do{ //���빤λ�ṹ
			    System.out.println("���빤λ�ṹ");
				MBOMnot_null=false;
				ArrayList array=bomimportdao.getTEMP_MBOM();//�õ�TEMP_MBOM��ʱ������һ�����
			    MDMwork = (MDMWorkPlace)array.get(0);     
	            bomimportdao.InputT_MDMWORKPLACE(MDMwork);  //��������ݴ�����ݿ�Ĺ�λ����
//				if(!MDMwork.getLowflage().equals("Y")){     //������ӹ�λ �������ʱ��TEMP_MBOM
	            if(bomimportdao.havechild(MDMwork)){
					ArrayList array_2 = bomimportdao.getChildMDM(MDMwork);
						for(int j = 0;j<array_2.size();j++){
							MDMWorkPlace MDMwork2 = (MDMWorkPlace)array_2.get(j);
							bomimportdao.InputTEMP_MBOM(MDMwork2);
					    }
				}
	            ArrayList arrayMAO = bomimportdao.getMDMAO(MDMwork);//�õ��˹�λ�µ����AO
	               if(arrayMAO.size()!=0 && arrayMAO!=null){           //���Ϊ�գ������TEMP_MDMAO��ʱ����
	            	   for(int k = 0;k<arrayMAO.size();k++){
	            		   MDMAO = (MDMAO)arrayMAO.get(k);
	            	       bomimportdao.InputTEMP_MDMAO(MDMAO);
	            	   }
	               }   
	               //
				bomimportdao.delTable(MDMwork);    //ɾ��TEMP_MBOM��ʱ���и������
				if(bomimportdao.TEMP_MBOM_notnull()){MBOMnot_null=true;}// ���TEMP_MBOM��Ϊ�� �����ѭ��
			}while(MBOMnot_null);
			do{//���빤λAO�ṹ
         	   System.out.println("���빤λAO�ṹ");
         	   MAOnot_null=false;
	   			   ArrayList array_3=bomimportdao.getTEMP_MDMAO();//�õ�TEMP_MDMAO��ʱ������һ�����
	   			   MDMAO2 = (MDMAO)array_3.get(0);
	   	               bomimportdao.InputT_MDMAO(MDMAO2);         //��������ݴ�����ݿ�Ĺ�λAO��
//	   				   if(!MDMAO2.getLowflage().equals("Y")){     //�������AO �������ʱ��TEMP_MDMAO
	   	               if(bomimportdao.havechild(MDMAO)){
	   					   ArrayList array_4 = bomimportdao.getChildMDMAO(MDMAO2);
	   						   for(int j = 0;j<array_4.size();j++){
	   							   MDMAO MDMAO3 = (MDMAO)array_4.get(j);
	   							   bomimportdao.InputTEMP_MDMAO(MDMAO3);
	   					       }
	   				   }
	   				   
//	  ��ʼ����AO����Ϣ�������š����͡���ʼ�ܴΡ���ֹ�ܴ���Ϣ��DOC_EFFSTATE(�ĵ���Ч��״̬��)�в�ѯ��DOCNUMBER,DOCVERSION��Ȼ������AO�����в��PLAN_ID
		   //String Turn_Back=bomimportdao.getEFF(MDMAO2.getFlight_type(),MDMAO2.getChildaocode(),lot,sortie,"AO");//
		   String Turn_Back = bomimportdao.getPlanId_test(MDMAO2.getChildaocode());				     
	   if(Turn_Back!=null && !Turn_Back.equals("")){
		   
	   	   String[] num = Turn_Back.split("---");
	       String plan_id = num[0];System.out.println("plan_id+"+plan_id);
	       String partno  = num[1];//װ��ͼ��
	       String ao_no   = num[2];//AO���
	       if(partno!=null && !partno.equals("")){//ֻ��AO��װ��ͼ�ŵĲŵ���AO ������
	          //String[] num2 = partno.split("��"); String item_id  = "";//num2[0];//���Ϻ�
	          String item_id = bomimportdao.getMaxLevel(product_id, partno);
//	    	 �õ������� ��bom�е�λ��
	          if(item_id!=null && !item_id.equals("")){
	        	  if(ds.strByteCopy(item_id, 3).equals("DDD")){
	        		  item_id = item_id.substring(1, item_id.length());
	        	  }
	        	   Hashtable ht = bomimportdao.getBomPos(product_id, item_id);
			       bomimportdao.getAoMd(plan_id,flight_type,product_id,MDMAO2.getIssue_num(),item_id,ao_no,ht);//í������׼��
			       bomimportdao.getAoFl(plan_id,flight_type,product_id,MDMAO2.getIssue_num(),item_id,ao_no,ht);//����
			       bomimportdao.getAoTool(plan_id,flight_type,product_id,MDMAO2.getIssue_num(),item_id,ao_no,ht);//��װ
			       bomimportdao.getAoOperTool(plan_id,flight_type,product_id,MDMAO2.getIssue_num(),item_id,ao_no);//����װ
			       bomimportdao.getAoPart(plan_id,flight_type,product_id,MDMAO2.getIssue_num(),item_id,ao_no);//������ױ�
			       bomimportdao.getAo(plan_id,flight_type,product_id,MDMAO2.getIssue_num(),item_id,ao_no);//AO ���乤����Ϣ
	          }
	       }
	   }
	   		   bomimportdao.delTable(MDMAO2);   //ɾ��TEMP_MDMAO��ʱ���и������
	           if(bomimportdao.TEMP_MDMAO_notnull()){MAOnot_null=true;}// ���TEMP_MDMAO��Ϊ�� �����ѭ��
			}while(MAOnot_null);
		}catch(Exception e){
			System.out.println("ImportBomBean.downMDMWorkPlace(sortie)����ʱ���?����Ϊ��"+e);
		}
	}
	/**	���BOM*/
	public String addBom(Hashtable hash){
		Bom bom = new Bom();
		BomBeanDao bombeandao = new BomBeanDao();
		String product_id = (String)hash.get("PRODUCT_ID");
		String s = (String)hash.get("ISSUE_NUM");//100λ������ʽ
		int p = Integer.parseInt((String)hash.get("ISSUE"));
		String item_id = bom_other_id;
		String father_item_id =(String)hash.get("FATHER_ITEM_ID");
		String fid = (String)hash.get("FID");
		String level_id = (String)hash.get("LEVEL_ID");
		String item_num = (String)hash.get("ITEM_NUM");
		String scrap = (String)hash.get("SCRAP");
		String is_rl = (String)hash.get("IS_RL");
		String route = (String)hash.get("ROUTE");
		String issue_like = (String)hash.get("ISSUE_LIKE");
		String id_num="";
		String issue_turn="";
		if(level_id.length()==1){
			level_id="0"+level_id;
		}
//		for(int k=1;k<=100;k++){       //���� issue_numֵ
//	        if(k==i){
//	           s=s+'1';
//	        }else{
//	           s=s+'0';
//	        }
//	    }
		try{

		    boolean isin_level = bombeandao.getLevel_exist(product_id, item_id, level_id);   
		    if(!isin_level){       //ͬ����û��������ϣ�ֱ����ӣ������λ������1������λ����0������Ķ���1
		    	//bombeandao.InputBom(product_id, s, item_id, "1", father_item_id, fid, level_id, item_num, scrap, is_rl, route);   
		    	id_num="1";
		    	issue_turn= s;
		    }else{          //ͬ������������ϣ������ж�
		        boolean isin_father = bombeandao.getFather_exist(product_id, item_id, level_id, father_item_id, fid);
                if(!isin_father){//��������û��������ϣ�ͬ������ͬ��������id���б�ֱ����ӣ��汾�������λ������1������λ����0������Ķ���1
                     int n = bombeandao.getMaxId(product_id, item_id, level_id);
                     n=n+1;
                     
                     id_num = n+"";
                     issue_turn= s;
		            // bombeandao.InputBom(product_id, s, item_id, id_num, father_item_id, fid, level_id, item_num, scrap, is_rl, route);
		        }else{//����������������ϣ�����λ�ú��ǡ�0�����ڸ�λ����1��ͬ������ͬ��������id���б�
		        	//System.out.println("ͬ������������� �����������������item_id��" + item_id+"--level_id��" + level_id+"--- item_num :"+item_num);
		            //����ڸ���������������ϣ�����λ�ú�Ϊ1��������������޸ġ�
		        	boolean isin = bombeandao.isin_Father(product_id, item_id, level_id, father_item_id, fid, issue_like);
		        	if(isin){
		        		int in_num = bombeandao.getNum(product_id, item_id, level_id, father_item_id, fid, issue_like);
		        			//System.out.println("item_id:"+item_id+" [num]"+in_num);
			        		String issue_num1 = bombeandao.getBom_issue(product_id, item_id, level_id, father_item_id, fid, issue_like);
			        		bombeandao.update("update meteor.ebom set item_num='"+(Integer.parseInt(item_num)+in_num)+"' where product_id='"+ product_id
	           		             +"' and item_id='"+item_id+"' and issue_num ='"+issue_num1+"' and level_id='"+level_id
	                                +"' and father_item_id='"+father_item_id+"' and fid='"+fid+"'");
		        		//}
		        	}else{
		        		boolean is_equal=false;
			            ArrayList array3 = bombeandao.getBom(product_id, item_id, level_id, father_item_id, fid);
			      		    for(int i = 0;i<array3.size();i++){//�����������ͬ ��Ҫ�¼�һ����ݡ�
			      			    bom = (Bom)array3.get(i);
		                        id_num=bom.getId();
		                        String num=bom.getItem_num();
		                        if(num.equals(item_num)){
		                        	//System.out.println("��������ͬ level_id" + level_id+"--- num :"+num);
		                        		  is_equal=true;
			                              String issue_num_a = bom.getIssue_num();
			                              StringBuffer buf   = new StringBuffer(issue_num_a);
			                              int a=p-1;
			                              int b=p;
			                              buf.replace(a,b,"1");
			                              s=buf.toString();//��1
			                              issue_turn= s;
			                              //System.out.println("s :" + s);
			                              bombeandao.update("update meteor.ebom set issue_num='"+s+"' where product_id='"+ product_id
			                            		             +"' and item_id='"+item_id+"' and issue_num ='"+issue_num_a+"' and level_id='"+level_id
		                                                     +"' and father_item_id='"+father_item_id+"' and fid='"+fid+"' and id='"+id_num+"'");
		                        break;
		                        }
		                    }if(!is_equal){
		                    	issue_turn= s;
		                    	//System.out.println("û��������ͬ level_id" + level_id+"--- item_num :"+item_num);
		                    	//bombeandao.InputBom(product_id, s, item_id, id_num, father_item_id, fid, level_id, item_num, scrap, is_rl, route);
		                    }
		        	}
		         }
		     }
		}catch(Exception e){
			System.out.println("addBom���г��?"+e);
		}
		return id_num+"--"+issue_turn;
	}
	public String getBom_other_id() {
		return bom_other_id;
	}
	public void setBom_other_id(String bom_other_id) {
		this.bom_other_id = bom_other_id;
	}
}
