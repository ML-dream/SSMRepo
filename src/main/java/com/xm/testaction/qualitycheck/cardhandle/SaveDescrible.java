package com.xm.testaction.qualitycheck.cardhandle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SaveDescrible extends HttpServlet {
//不合格品描述上传 
	/**
	 * Constructor of the object.
	 */
	public SaveDescrible() {
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
		System.out.println("图片上传0");
		String image = request.getParameter("image");
		String filename = request.getParameter("runnum") + ".png";
//		String imgPath = "E:\\tomcat\\tomcat\\webapps\\work\\qualitycheck\\describe";	//dell
		String imgPath = "D:\\apache-tomcat\\webapps\\work\\qualitycheck\\describe";	//纳连
//		String imgPath ="E:\\tomcat6.0\\webapps\\work\\qualitycheck\\describe";	//本机
		image = image.replace("data:image/png;base64,", "");
//		String filepath = request.getRealPath();
		if(image ==null){
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try{
		byte[] b = decoder.decodeBuffer(image);
		for(int i =0,n=b.length;i<n;++i){
			if(b[i] < 0){
//				调整异常数据
				b[i]+=256;
			}
		}
			OutputStream out = new FileOutputStream(new File(imgPath,filename));
			out.write(b);
			out.flush();
			out.close();
			System.out.println("图片上传成功");
		}catch (Exception e) {
			// TODO: handle exception
		}
//		String result = imgPath+"\\"+filename;
//		response.setCharacterEncoding("utf-8");
//		response.getWriter().append(result).flush();
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
