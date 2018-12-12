package cfmes.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cfmes.util.DealString;
import cfmes.bean.ECBean;
import cfmes.bom.dao.MbomDao;
import java.util.ArrayList;
import java.util.Hashtable;

import cfmes.bom.entity.BomRecord;

public class mbomsvlt extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		DealString ds = new DealString();
		MbomDao mbomdao = new MbomDao();
		BomRecord bomrecord = new BomRecord();
		ECBean ecbean = new ECBean();
		String str ="";
		
		String product_id = ds.toGBK(request.getParameter("product_id"));
		String issue_num = ds.toGBK(request.getParameter("issue_num"));
		String issue = ds.toGBK(request.getParameter("issue"));
		String lot = ds.toGBK(request.getParameter("lot"));
		String sortie = ds.toGBK(request.getParameter("sortie"));
		String flight_type = ds.toGBK(request.getParameter("flight_type"));
		
		String aofo ="";
		if( mbomdao.HasMbom(product_id, issue_num)){
			ecbean.DataToNMbom(product_id, issue_num);
		}else{
			ArrayList list =mbomdao.Getebomred(product_id, issue_num);
			/**将ebom表中的数据移动到MBOM中**/
		    for(int i=0;i<list.size();i++){
		    	bomrecord=(BomRecord)list.get(i);
		    	boolean isin = mbomdao.Isinmbom(bomrecord);
		    	if(!isin){
		    		mbomdao.Insertmbom(bomrecord);
		    	}else{}	
		    	/**在每个物料下面寻找是否有Aofo**/	    	
		    	aofo = mbomdao.Getaofostatus(bomrecord.getproduct_id(),bomrecord.getissue_num(),bomrecord.getxid());
		    	if(aofo.equals("")){}else{
		    		/**得到aofo**/
		    		BomRecord bmrecd = mbomdao.Getaofo(bomrecord.getproduct_id(),bomrecord.getissue_num(),bomrecord.getxid(),aofo,bomrecord.getlevel_id());
		    		boolean aofoisin = mbomdao.Isinmbom(bmrecd);
		    		if(!aofoisin){mbomdao.Insertmbom(bmrecd);}else{}
		    		if(aofo.equals("ao")){/**ao时直接得到刀量夹原材料辅料**/
		    			ArrayList list3 = mbomdao.GetCmtyf(bmrecd,aofo);
		    			mbomdao.CmtyfIstMb(list3);
		    			/**boolean cmtyf = mbomdao.Isinmbom(bmrecdcmtyf);
		    			if(!cmtyf){mbomdao.Insertmbom(bmrecdcmtyf);}else{}**/
		    		}else if(aofo.equals("fo")){/**fo时先得到oper，再得到cmtyf**/
		    			ArrayList list2 = mbomdao.GetFop(bmrecd);
		    			for(int j=0;j<list2.size();j++){
		    				BomRecord brcd_fop = (BomRecord)list2.get(j);
		    				boolean fopisin = mbomdao.Isinmbom(brcd_fop);
		    				if(!fopisin){mbomdao.Insertmbom(brcd_fop);}else{}
		    				ArrayList list4 = mbomdao.GetCmtyf(brcd_fop,aofo);
		    				mbomdao.CmtyfIstMb(list4);
	  	    				/**boolean cmtyf = mbomdao.Isinmbom(fbrcd_cmtyf);
			    			if(!cmtyf){mbomdao.Insertmbom(fbrcd_cmtyf);}else{}**/
		    			}
		    		}
		    	}
		    }
		    str = "<script>alert('MBOM构造成功，查看请到零件计划模块！');window.history.back();</script>";
		}
		/*out.println("<script>top.window.location.href='mbom/mbom-frame.jsp?product_id="+product_id+"&issue_num="+issue_num+"&issue="+issue+"&lot="+lot+"&sortie="+sortie+"';</script>");
		*/
	    out.println("<script>alert('MBOM构造成功，查看请到零件计划模块！');window.history.back();</script>");
		out.flush();
		out.close();
	}

}
	
