package com.xm.testaction.qualitycheck.sum;

import com.wl.tools.Sqlhelper;

public class RoughsizeToVol {
	public static void main (String [] args){
		CostConfirmBean bean = new CostConfirmBean();
		bean.setStuff("45");
		bean.setOrderid("NL-XS-103755-1611161038524");
		bean.setDraid("wf-szwk-190.2");
		
		String s = "D80*1000++10d0*10,0*100+D20*100";
		roughsizeToVol(s, bean);
	}

	public static void roughsizeToVol(String roughsize,CostConfirmBean bean){
		roughsize =roughsize.trim();
		roughsize.replaceAll(",", "");
		if(roughsize.contains("+")){
//			如果是多个毛坯尺寸
			String rough [] = roughsize.split("\\+");
			helpRoughsize(rough[0], bean);	//第一个尺寸，其余尺寸直接保存到 costinput表
			saveRoughsize(rough,bean);
		}else{
//		只有一个毛坯尺寸
			helpRoughsize(roughsize, bean);
		}
//		System.out.println(bean.getDia());
//		System.out.println(bean.getLen());
//		System.out.println(bean.getWid());
//		System.out.println(bean.getHei());
		
	}
	public static void helpRoughsize(String roughsize,CostConfirmBean bean){
//		柱体 尺寸规范： D16*10
		char s0 = roughsize.charAt(0);	//第一个字母
		if(s0 =='d' || s0== 'D'){
			String para = roughsize.substring(1);
			para.replaceAll("[a-zA-Z]","");
			String size[] = para.split("\\*", 2);
			bean.setDia(size[0].replaceAll("\\D", ""));
			bean.setLen(size[1].replaceAll("\\D", ""));
			
		}else{
			roughsize.replaceAll("[a-zA-Z]","");
//			板件 10*10*10
			String size[] = roughsize.split("\\*",3);
			bean.setDia("0");
			bean.setLen(size[0].replaceAll("\\D", ""));
			bean.setWid(size[1].replaceAll("\\D", ""));
			bean.setHei(size[2].replaceAll("\\D", ""));
		}
	}
	public static void saveRoughsize(String [] rough,CostConfirmBean bean){
		String rougString = "";		//毛坯尺寸
		String [] size = null;		//各尺寸集
		String dia = "0";
		String len = "1";
		String wid = "1";
		String hei ="1";

//		保存到表中
		String orderid = bean.getOrderid();
		String draid = bean.getDraid();
		String stuff = bean.getStuff();
		String  sqla = "";
		String stufflevel = "2";
		for(int j=1,k=rough.length;j<k;j++){
//			除了第一个毛坯尺寸，其余的直接保存到表中
			dia = "0";
			len = "1";
			wid = "1";
			hei ="1";
			
			rougString = rough[j];	//第J+1个毛坯尺寸
			if(rougString ==null||rougString.equals("")){
				continue;
			}
			char s0 = rougString.charAt(0);	//第一个字母
			if(s0 =='d' || s0== 'D'){
				String para = rougString.substring(1);
				para.replaceAll("[a-zA-Z]","");
				size = para.split("\\*", 2);
				dia = size[0].replaceAll("\\D", "");
				len = size[1].replaceAll("\\D", "");
			}else{
				rougString.replaceAll("[a-zA-Z]","");
//				板件 10*10*10
				size = rougString.split("\\*",3);
				len = size[0].replaceAll("\\D", "");
				wid = size[1].replaceAll("\\D", "");
				hei = size[2].replaceAll("\\D", "");
			}
			String helpkey = ""+j;
			String [] params ={orderid,draid,stuff,dia,len,wid,hei,rougString,stufflevel,helpkey};
			sqla="insert into costinput (orderid,draid,stuff,dia,len,wid,hei,roughsize,stufflevel,helpkey) values(?,?,?,? ,?,?,?,?,?,?)";
			try {
//				System.out.println(sqla);
				Sqlhelper.executeUpdate(sqla, params);
				System.out.println("ok  "+ sqla);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		

	}
}
