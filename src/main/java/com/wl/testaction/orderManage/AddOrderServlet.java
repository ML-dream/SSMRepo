package com.wl.testaction.orderManage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cfmes.util.sql_data;

import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.wl.forms.User;
import com.wl.testaction.utils.UploadUtils;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class AddOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 3495646319327543417L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
//		request.setCharacterEncoding("utf-8");  //设置编码  
//        String path = request.getRealPath("/wl");  								//获取文件需要上传到的路径  
//        
//        //如果没以下两行设置的话，上传大的 文件 会占用 很多内存，  
//        //设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同  
//        /** 
//         * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上，  
//         * 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的  
//         * 然后再将其真正写到 对应目录的硬盘上 
//         */ 
//        File wenjianjia = new File(path);
//        if (!wenjianjia.exists()) {
//        	wenjianjia.mkdir();
//		}
//        DiskFileItemFactory factory = new DiskFileItemFactory();  				//获得磁盘文件条目工厂 
//        factory.setSizeThreshold(1024*1024) ;									//设置向硬盘写数据时所用的缓冲区的大小，此处为1M  
//        																		//设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室  
//        factory.setRepository(wenjianjia);
//        
//        ServletFileUpload upload = new ServletFileUpload(factory);				//高水平的API文件上传处理  
//        upload.setSizeMax(10*1024*1024);
//        try {
//            List<FileItem> list = (List<FileItem>)upload.parseRequest(request); //可以上传多个文件  
//            
//            for(FileItem item : list){ 
//                String name = item.getFieldName();  							//获取表单的属性名字  
//                System.out.println("name=="+name);
//                
//                if(item.isFormField())  										//如果获取的 表单信息是普通的 文本 信息  
//                {                     
//                    //获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的  
//                    String value = item.getString() ; 
//                    requestValueMap.put(name, value);
//                    request.setAttribute(name, value);  
//                }  
//                //对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些  
//                else  
//                {  
//                    /** 
//                     * 以下三步，主要获取 上传文件的名字 
//                     */  
//                    //获取路径名  
//                    String value = item.getName() ;  				//这是上传的文件名
//                    int start = value.lastIndexOf("\\");  			//索引到最后一个反斜杠
//                    String filename = value.substring(start+1);  	//截取 上传文件的 字符串名字，加1是 去掉反斜杠，  
//                    
//                    request.setAttribute(name, filename);  
//                      
//                    //真正写到磁盘上  
//                    //它抛出的异常 用exception 捕捉  
//                    //item.write( new File(path,filename) );//第三方提供的  
//                    //手动写的  
//                    OutputStream out = new FileOutputStream(new File(path,filename)); //path+filename是文件的完整路径
//                    System.out.println(path);
//                      
//                    InputStream in = item.getInputStream() ;  
//                      
//                    int length = 0 ;  
//                    byte [] buf = new byte[1024] ;  
//                      
//                    System.out.println("获取上传文件的总共的容量："+item.getSize());  
//  
//                    // in.read(buf) 每次读到的数据存放在   buf 数组中  
//                    while( (length = in.read(buf) ) != -1){  
//                        out.write(buf, 0, length);  		//在   buf 数组中 取出数据 写到 （输出流）磁盘上  
//                    }
//                    in.close();  
//                    out.close();  
//                }  
//            }
//        } catch (FileUploadException e) {  
//            e.printStackTrace();  
//        }  
//        catch (Exception e) {  
//        }  

		
//		// 文件上传个数
//		int count = 0;
//		// 文件上传地址
//		String filePath = getServletContext().getRealPath("/") + "smartUpload";
//
//		// 如果文件夹不存在 则创建这个文件夹
//		File file = new File(filePath);
//		if (!file.exists()) {
//			file.mkdir();
//		}
//		// 初始化对象
//		
//		SmartUpload su = new SmartUpload();
//		su.initialize(getServletConfig(), requestold, response);
//
//		
//		// 设置文件最大容量
//		su.setMaxFileSize(10 * 1024 * 1024);
//		// 设置所有文件最大容量
//		su.setTotalMaxFileSize(100 * 1024 * 1024);
//		// 设置上传文件类型
//		su.setAllowedFilesList("rar,txt,jpg,bmp,gif,doc,docx,xls");
//		
//		try {
//			// 设置禁止上传的文件类型
//			su.setDeniedFilesList("jsp,js,html,css");
//			// 上传文件
//			su.upload();
//			System.out.println("deptUser="+ su.getRequest().getParameter("deptUser"));
//			count = su.save(filePath);
//		} catch (SQLException e) {			//这里的异常要进行处理！！！！！！！！
//			e.printStackTrace();
//		} catch (SmartUploadException e) {
//			e.printStackTrace();
//		}
//
//		for (int i = 0; i < su.getFiles().getCount(); i++) {
//			com.jspsmart.upload.File tempFile = su.getFiles().getFile(i);
//			System.out.println("-------------------------------------------------");
//			System.out.println("表单项名称:" + tempFile.getFieldName());
//			System.out.println("文件名：" + tempFile.getFileName());
//			System.out.println("文件长度：" + tempFile.getSize());
//			System.out.println("文件扩展名:" + tempFile.getFileExt());
//			System.out.println("文件全名：" + tempFile.getFilePathName());
//			System.out.println("-------------------------------------------------");
//		}
//		System.out.println("上传成功！共" + count + "个文件！");
//		
//		Request request = su.getRequest();
//		
		response.setCharacterEncoding("UTF-8");
		Map<String, String> requestValueMap = new HashMap<String, String>();	//用来封装request请求中的参数
		
		HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    Date d = new Date();    
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");  
        String time = sdf.format(d);  

	    
		try {
			requestValueMap = UploadUtils.UploadLoadFile(request, response, createPerson, requestValueMap);	//保存文件+得到request属性值
		} catch (FileUploadException e1) {
			String json = "{\"result\":\"文件太大，请检查文件上大小！！\"}";
			response.getWriter().append(json).flush();
			e1.printStackTrace();
		}
		
		String orderId = requestValueMap.get("orderHead")+ChineseCode.toUTF8(requestValueMap.get("orderId").trim());
		String customer = ChineseCode.toUTF8(requestValueMap.get("customer").trim());
	    String deptUser = ChineseCode.toUTF8(requestValueMap.get("deptUser").trim());
	    
	    String endTime = ChineseCode.toUTF8(requestValueMap.get("endTime").trim());
	    String orderDate = ChineseCode.toUTF8(requestValueMap.get("orderDate").trim())+" "+time;
	    String orderStatus = ChineseCode.toUTF8(requestValueMap.get("orderStatus").trim());
	    String memo = ChineseCode.toUTF8(requestValueMap.get("memo"));
	 
 //     String connector = ChineseCode.toUTF8(requestValueMap.get("connector").trim());
	    String connector = requestValueMap.get("connector").trim();
	    System.out.println(connector);
	    String connectorTel = ChineseCode.toUTF8(requestValueMap.get("connectorTel").trim());
	    
	    String orderPaper = requestValueMap.get("orderPaper");
	    String duizhanghan = requestValueMap.get("duizhanghan");
	    String otherPaper = requestValueMap.get("otherPaper");
	    
		String  addOrderSql = "insert into orders " +
				"(ORDER_ID,DEPT_USER,MEMO,endTime,ORDER_DATE," +
				"ORDER_STATUS,CUSTOMER,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME,connector,connectorTel," +
				"orderPaper,duizhanghan,otherPaper) values ('"+
				orderId+"','"+deptUser+"','"+memo+"',to_date('"+endTime+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+orderDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+
				orderStatus+"','"+customer+"','"+
				createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"
				+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+connector+"','"+connectorTel+"'," +
				"'"+orderPaper+"','"+duizhanghan+"','"+otherPaper+"')";

		System.out.println("addOrderSql=="+addOrderSql);
		
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(addOrderSql);
			
			String json = "{\"result\":\"操作成功!\"}";
			response.getWriter().append(json).flush();
		} catch (SQLException e) {
			String json = "{\"result\":\"操作失败!\"}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}


}
