package DataCollection;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.util.sql_data;

import com.wl.forms.Notice;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.forms.dataCollectionTable;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;
import com.wl.tools.UUIDHexGenerator;

public class dataFeedback extends HttpServlet {
	private static final long serialVersionUID = 8211416997978746631L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 1;
	    String orderStr = "sendTime";
	    //orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    //pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    //countPerPage = Integer.parseInt(request.getParameter("pageSize"));

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
//	    HttpSession session = request.getSession();
//	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
//	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();	
	    
	    /*String totalCountSql = "select count(*) from datacollection  ";
//	    String[] paramss = {createPerson};
		try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}*/
		String machineId = request.getParameter("machineId");
	    
	    //String noticeWillSql = "select t.x_axis_feed_speed, t.x_axis_coordinates,t.machineid from DATACOLLECTION t where machineId = '"+machineId+"'";
	    //List<dataCollectionTable> notices = new ArrayList<dataCollectionTable>();
		
		String machineInfoDataName = getDatabaseName(machineId);
		
		String machineDataInfoSql = "select nckType,createtime,numChannels,numAlarms,numMachAxes,numSpindles,poweronTime,operatingTime,cuttingTime,cycleTime,progName,blockNoStr,totalParts,speedOvr,driveLoad,cmdSpeed,actSpeed,cmdConstCutSpeed,actTNumber,actFeedRate,cmdFeedRate,actSpeedRel,cmdSpeedRel,fillText,textIndex, aaMm1,aaMm2, aaMm2,aaMm3,aaMm4,aaMm5,aaMw1,aaMw2,aaMw3,aaMw4,aaMw5,progDistToGo1,progDistToGo2,progDistToGo3,progDistToGo4,progDistToGo5,aaLoad1,aaLoad2,aaLoad3,aaLoad4,aaLoad5, CASE opmode  WHEN 1 THEN '亚洲' when 2 then 'dddd' ELSE '其他' END as opmode, CASE progStatus  WHEN 4 THEN '取消'  WHEN 2 THEN '停止' WHEN 3 THEN '运行' WHEN 4 THEN '运行' else   '获取失败'  END as progStatus    from " + machineInfoDataName ;
		List<machineDataBean> notices = null;
		try {
			notices = Sqlhelper.exeQueryList(machineDataInfoSql,null, machineDataBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String json = PluSoft.Utils.JSON.Encode(notices);
		json = json.substring(1, json.length()-1);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	}
	private String getDatabaseName(String machineId) {

		 Properties pps = new Properties();
	        try {
	        	InputStream in = dataFeedback.class.getClassLoader().getResourceAsStream("DataCollection/machineDatabase.properties");  
	            pps.load(in);
	            String value = pps.getProperty(machineId);
	          //  System.out.println(key + " = " + value);
	            return value;
	            
	        }catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
		
	}


}
