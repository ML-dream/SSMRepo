package com.xm.testaction.qualitycheck.cardhandle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Sqlhelper0;
import com.wl.tools.StringUtil;
import com.xm.testaction.qualitycheck.ToSum;

public class OpinionSave extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OpinionSave() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String barcode = request.getParameter("fbarcode");
//		统计用  barcode 
		String sumbarcode = barcode;
		
		int fo_no = Integer.parseInt(request.getParameter("fo_no"));
		String opiniontext = ChineseCode.toUTF8(request.getParameter("opiniontext"));
		String opinion = request.getParameter("opinion");
		 String runnum1 = request.getParameter("runnum");
		String user = "";
//		待矫正
		HttpSession session = request.getSession();
	    user = ((User)session.getAttribute("user")).getStaffName();
//		判断是否已经生成报废单或者返修单
//		9-1  9-26
		String opinionerstate = "0";
		String ischange = "0";	//如果是不同的意见，则为1,返回到前台
		String  para = "0";
		try {
			para = StringUtil.isNullOrEmpty(request.getParameter("para"))? para : request.getParameter("para");
		} catch (Exception e) {
			// TODO: handle exception
		}
		String sonbarcode = "";	//从返修改报废，要删掉 流程卡中的数据
		if(para.equals("1")){
//			如果是修改意见 ，与上一次意见相同则不处理，如不同，则删除上一次的报废单、返修单
			opinionerstate = "0";	//如果修改了意见，则把它重新放到待处理审理单中
			String sqla = "select t.opinion,o.barcode from REJECT_STATE t " +
					" left join attach_info o on o.runnum = t.runnum "+
					"where t.runnum= '"+runnum1+"'";
			
			String s = null;	//已保存的意见。
			ResultSet rsa = null;
			try {
				rsa  = Sqlhelper0.executeQuery(sqla, null);
				rsa.next();
				s = rsa.getString(1);
				sonbarcode = rsa.getString(2);
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				try {
					rsa.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(s!= null && !s.equals(opinion)){
//				如果意见不同,把已保存的 记录都删除。
				ischange = "1";
				String sqlb = "begin " +
						"delete from fixdetail t where t.staterunnum = '"+runnum1+"' ;" +
						"delete from disdetail o where o.staterunnum = '"+runnum1+"';" +
						"delete from po_routing2 p where p.barcode = '"+sonbarcode+"' and fo_no="+fo_no+";"+
						"end;";
//				修改attached_info 里子卡状态
				String attachsql ="update attach_info set barcode='"+barcode+"',status=3 where runnum='"+runnum1+"'";
				try {
					Sqlhelper.executeUpdate(attachsql, null);
					System.out.println("ok  "+attachsql);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					System.out.println(sqlb);
					Sqlhelper0.executeUpdate(sqlb, null);
					System.out.println("ok  "+sqlb);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

//		9-6   opinionstate 为0 表示，未处理完 
		String savesql = "update reject_state set opiniontext='"+opiniontext+"',opinion='"+opinion+"',opinioner='"+user+"',opinionerstate=0"+
				" where barcode='"+barcode+"'  and runnum='"+runnum1+"'";
//		如果是超差使用，则处理结束
		if(opinion.equals("toSale")){
			savesql = "update reject_state set opiniontext='"+opiniontext+"',opinion='"+opinion+"',opinioner='"+user+"',opinionerstate=1"+
			" where barcode='"+barcode+"'  and runnum='"+runnum1+"'";
		}
		System.out.println(savesql);
		try{
			System.out.println(savesql);
			Sqlhelper0.executeUpdate(savesql, null);
			System.out.println("ok  "+savesql);
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
//			Sqlhelper0.close();
		}
//		9-6  储存当前时间 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	   
	    String datesql = "update opiniondate set opiniondate=to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss') where runnum ='"+runnum1+"'";
	    try {
			Sqlhelper0.executeUpdate(datesql, null);
			System.out.println(datesql+"ok");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(datesql+"die");
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(ischange).flush();

/*	
		if(para.equals("1")){
//			如果是修改，理论上应该 更新数据，待矫正  load on 
			try {
				int lastfo = Integer.parseInt(request.getParameter("lastfo"));
				System.out.println();
				String numsql = "select a.runnum,a.rejectnum from reject_state a where a.barcode ='"+sumbarcode+"' and a.fo_no ="+fo_no +" and runnum='"+runnum1+"'";
				String runnum = "";
				int num = 0 ;
				ResultSet Rs =null;
				try{
					Rs=Sqlhelper0.executeQuery(numsql, null);
					Rs.next();
					runnum = Rs.getString(1);
					num = Rs.getInt(2);
					System.out.println();
				}catch (Exception e) {
						// TODO: handle exception
					}finally{
						if(Rs != null){
							try {
								Rs.close();
							} catch (Exception e2) {
								// TODO: handle exception
							}
						}
					}
	//				如果是最后一道工序
//				if (lastfo == 1){
//					ToSum.fstateToSum(opinion, runnum, num, fo_no, sumbarcode);
//				}else{
					ToSum.stateToSum(opinion, runnum, num, fo_no, sumbarcode);
//				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
*/
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
