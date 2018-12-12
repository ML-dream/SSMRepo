///**
// * 项目名称: work
// * 创建日期：2016-7-2
// * 修改历史：
// *		1.[2016-7-2]创建文件 by Flair
// */
//package Test;
//
//import java.io.File;
//import java.sql.SQLException;
//
//import com.jspsmart.upload.SmartUpload;
//import com.jspsmart.upload.SmartUploadException;
//
///**
// * @author Flair
// *
// */
//public class SmartUpload {
//	// 文件上传个数
//	int count = 0;
//	// 文件上传地址
////	String filePath = getServletContext().getRealPath("/") + "smartUpload";
//	String filePath = "E:/test/1.png";
//
//	// 如果文件夹不存在 则创建这个文件夹
//	File file = new File(filePath);
//	if (!file.exists()) {
//		file.mkdir();
//	}
//	// 初始化对象
//	SmartUpload su = new SmartUpload();
//	su.initialize(getServletConfig(), request, response);
//
//	// 设置文件最大容量
//	su.setMaxFileSize(10 * 1024 * 1024);
//	// 设置所有文件最大容量
//	su.setTotalMaxFileSize(100 * 1024 * 1024);
//	// 设置上传文件类型
//	su.setAllowedFilesList("rar,txt,jpg,bmp,gif,doc");
//
//	try {
//		// 设置禁止上传的文件类型
//		su.setDeniedFilesList("jsp,js,html,css");
//		// 上传文件
//		su.upload();
//		System.out.println("userName="+ su.getRequest().getParameter("userName"));
//		count = su.save(filePath);
//	} catch (SQLException e) {			//这里的异常要进行处理！！！！！！！！
//		e.printStackTrace();
//	} catch (SmartUploadException e) {
//		e.printStackTrace();
//	}
//
//	for (int i = 0; i < su.getFiles().getCount(); i++) {
//		com.jspsmart.upload.File tempFile = su.getFiles().getFile(i);
//		System.out.println("-------------------------------------------------");
//		System.out.println("表单项名称:" + tempFile.getFieldName());
//		System.out.println("文件名：" + tempFile.getFileName());
//		System.out.println("文件长度：" + tempFile.getSize());
//		System.out.println("文件扩展名:" + tempFile.getFileExt());
//		System.out.println("文件全名：" + tempFile.getFilePathName());
//		System.out.println("-------------------------------------------------");
//	}
//	System.out.println("上传成功！共" + count + "个文件！");
//}
