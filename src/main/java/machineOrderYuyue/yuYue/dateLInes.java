package machineOrderYuyue.yuYue;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;




import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.testaction.utils.DaiUtils;

import machineOrderYuyue.beans.*;
import net.sf.json.JSONObject;

public class dateLInes extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		
			request.setCharacterEncoding("utf-8");
		
			String bday = request.getParameter("bday");
			String eday = request.getParameter("eday");
			
			Calendar calendar2 = Calendar.getInstance();
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			
	        String time = sdf2.format(calendar2.getTime());// new Date()Ϊ��ȡ��ǰϵͳʱ��
			
	        ArrayList<dateContent> dateContentList = new ArrayList<dateContent>();
	        
	        int count;
			Date beginDate;
			if((bday==null?"":bday).equals("")||(eday==null?"":bday).equals("")) {
				
				count=18;
				beginDate=calendar2.getTime();
			
			
			}else {
				 Date bdate = null;
				try {
					bdate = sdf2.parse(bday);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         Date edate = null;
				try {
					edate = sdf2.parse(eday);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		            
		         	count=DaiUtils.differentDays(bdate,edate);
		         	beginDate=bdate;
		         	calendar2.setTime(beginDate);
				
			}
	        
	        
	        
	        for(int i=0;i<count;i++){
	        	
	        	dateContent dateContent = new dateContent();
	        	calendar2.add(Calendar.DATE, 0);
	        	dateContent.setDate(sdf2.format(calendar2.getTime()));
	        	dateContent.setIsSelected(false);
	        	dateContent.setIsToday(false);
	        	dateContent.setWeek(calendar2.get(Calendar.DAY_OF_WEEK)-1);
	        	dateContentList.add(dateContent);
	        	calendar2.add(Calendar.DATE, 1);
	        }
	        jsonBean jsonBean =new jsonBean();
	        
	        jsonBean.setContent(dateContentList);
	        jsonBean.setMsg("操作成功");
	        jsonBean.setNo(2000);
	        jsonBean.setNow(time);
	        response.setContentType("application/json;charset=utf-8");
	        JSONObject json = JSONObject.fromObject(jsonBean);
	       // JSONObject json=  JSONObject.fromObject(jsonBean);
	        String json1 = json.toString();
			//String json = PluSoft.Utils.JSON.Encode(notices);
			//json = json.substring(1, json.length()-1);
			//json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
			/*response.setCharacterEncoding("UTF-8");*/
			response.getWriter().append(json1).flush();
			System.out.println(json1);
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
