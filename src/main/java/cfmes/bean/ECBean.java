package cfmes.bean;

import java.util.ArrayList;

import cfmes.bom.dao.EcDao;
import cfmes.bom.dao.MbomBeanDao;
import cfmes.bom.dao.MbomDao;
import cfmes.bom.entity.BomRecord;

public class ECBean {
     public String DataToNMbom(String product_id,String issue_num){
    	 String str = "";
    	 MbomDao mbomdao = new MbomDao();
    	 BomRecord bomrecord = new BomRecord();
    	 String aofo = "";
    	 
    	 mbomdao.ClearNMbom(product_id, issue_num);
    	 ArrayList list =mbomdao.Getebomred(product_id, issue_num);
    	 /**将ebom表中的数据移动到MBOM中**/
    	 	    for(int i=0;i<list.size();i++){
    	 	    	bomrecord=(BomRecord)list.get(i);
    	 	    	mbomdao.InsertNmbom(bomrecord);//201504021113
    	 	    	
    	 	    	/**在每个物料下面寻找是否有Aofo**/	    	
    	 	    	aofo = mbomdao.Getaofostatus(bomrecord.getproduct_id(),bomrecord.getissue_num(),bomrecord.getxid());
    	 	    	if(aofo.equals("")){}else{
    	 	    		/**得到aofo**/
    	 	    		BomRecord bmrecd = mbomdao.Getaofo(bomrecord.getproduct_id(),bomrecord.getissue_num(),bomrecord.getxid(),aofo,bomrecord.getlevel_id());
    	 	    		mbomdao.InsertNmbom(bmrecd);
    	 	    		if(aofo.equals("ao")){/**ao时直接得到刀量夹原材料辅料**/
    	 	    			ArrayList list3 = mbomdao.GetCmtyf(bmrecd,aofo);
    	 	    			mbomdao.CmtyfIstNMb(list3);
    	 	    			/**boolean cmtyf = mbomdao.Isinmbom(bmrecdcmtyf);
    	 	    			if(!cmtyf){mbomdao.Insertmbom(bmrecdcmtyf);}else{}**/
    	 	    		}else if(aofo.equals("fo")){/**fo时先得到oper，再得到cmtyf**/
    	 	    			ArrayList list2 = mbomdao.GetFop(bmrecd);
    	 	    			for(int j=0;j<list2.size();j++){
    	 	    				BomRecord brcd_fop = (BomRecord)list2.get(j);
    	 	    				mbomdao.InsertNmbom(brcd_fop);
    	 	    				ArrayList list4 = mbomdao.GetCmtyf(brcd_fop,aofo);
    	 	    				mbomdao.CmtyfIstNMb(list4);
    	   	    				/**boolean cmtyf = mbomdao.Isinmbom(fbrcd_cmtyf);
    	 		    			if(!cmtyf){mbomdao.Insertmbom(fbrcd_cmtyf);}else{}**/
    	 	    			}
    	 	    		}
    	 	    	}
    	 	    if(i==list.size()){str = "<script>alert('变更存档完成');</script>";}
    	 	    }
    	 		
    	 		/*out.println("<script>top.window.location.href='mbom/mbom-frame.jsp?product_id="+product_id+"&issue_num="+issue_num+"&issue="+issue+"&lot="+lot+"&sortie="+sortie+"';</script>");
    	 		*/
    	 	     
    	 return str;
     }
     
     public ArrayList GetEcData(String product_id,int i,String issue_num){
    	 ArrayList list,listec,listresult = new ArrayList();
    	 MbomBeanDao mbombeandao = new MbomBeanDao();
    	 list = mbombeandao.getBomList(product_id, i, issue_num);
    	 listec = mbombeandao.getNBomList(product_id, i, issue_num);
    	 
    	 EcDao ecdao = new EcDao();
    	 BomRecord mbomred,nmbomred = new BomRecord();
    	 //查询nmbom里是否有数据存在
    	 if(ecdao.Is_NMbom()){}else{return list;}
    	//查询添加与更新操作
    	 for(int j=0;j<listec.size();j++){
    		 if(ecdao.Is_AccQuery((BomRecord)listec.get(j),"work.mbom")){
    			 //精确查询，在原mbom表中出现，表明该节点未变更，无操作该节点加入结果数组里
    			//查询工序级别的更新操作
        		 if(ecdao.Is_FoOpUpEc((BomRecord)listec.get(j))){
        			 BomRecord temp = (BomRecord)listec.get(j);
        			 temp.setEc_id("03");
        			 listresult.add(temp);
        		 }
        		 else
    		     listresult.add(listec.get(j));
    		 }else   if(ecdao.Is_BlurQuery((BomRecord)listec.get(j),"work.mbom") ||ecdao.Is_FoOpUpEc((BomRecord)listec.get(j))){
		    			 /**半精确查询，在原MBOM表中没有出现，则该节点是添加上去的；若在原MBOM表中出现，则该节点是更新过的。
		    			 需要将变更类型编号写入bomrecord中*/
		    			 BomRecord temp = (BomRecord)listec.get(j);
		    			 temp.setEc_id("03");
		    			 listresult.add(temp);
		    		 }else{
		    			 BomRecord temp = (BomRecord)listec.get(j);
		    			 temp.setEc_id("01");
		    			 listresult.add(temp);
    		         }
    		 /*
    		//查询工序级别的更新操作
    		 if(ecdao.Is_FoOpUpEc((BomRecord)listec.get(j))){
    			 BomRecord temp = (BomRecord)listec.get(j);
    			 temp.setEc_id("03");
    			 listresult.add(temp);}
    		*/	 
    	 }
    	 //查询有无删除操作
    	 for(int j=0;j<list.size();j++){
    		 if(ecdao.Is_BlurQuery((BomRecord)list.get(j),"work.nmbom")){
    			 //半精确查询，在nmbom表中出现，表明该节点未变更或更新，无操作
    		    
    		 }else {
    			 BomRecord temp = (BomRecord)list.get(j);
    			 temp.setEc_id("02");
    			 listresult.add(temp);}
    	 }
    	
    	     	 
    	 return listresult;
     }
     
     
}
