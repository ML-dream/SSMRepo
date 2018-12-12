/**
 * 项目名称: work
 * 创建日期：2016-6-22
 * 修改历史：
 *		1.[2016-6-22]创建文件 by Flair
 */
package com.wl.testaction.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.wl.tools.ChineseCode;
import com.wl.tools.StringUtil;

/**
 * @author Flair
 *
 */
public class UploadUtils {
	private static final int FILESIZEMAX = 20*1024*1024;				//最大上传20M的文件
	@SuppressWarnings("unchecked")
	public static Map<String, String> UploadLoadFile(HttpServletRequest request, HttpServletResponse response,String fileBorderName,Map<String, String> map)
	throws ServletException, IOException,FileUploadException {
		
		request.setCharacterEncoding("utf-8");  //设置编码  
        String path = request.getRealPath("/"+fileBorderName);  							//获取文件需要上传到的路径  
        
        //如果没以下两行设置的话，上传大的 文件 会占用 很多内存，  
        //设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同  
        /** 
         * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上，  
         * 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的  
         * 然后再将其真正写到 对应目录的硬盘上 
         */ 
        File wenjianjia = new File(path);
        if (!wenjianjia.exists()) {
        	wenjianjia.mkdir();
		}
        System.out.println(path);
        
        
        DiskFileItemFactory factory = new DiskFileItemFactory();  				//获得磁盘文件条目工厂 
        factory.setSizeThreshold(1024*1024) ;									//设置向硬盘写数据时所用的缓冲区的大小，此处为1M  
        																		//设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室  
        factory.setRepository(wenjianjia);
        
        ServletFileUpload upload = new ServletFileUpload(factory);				//高水平的API文件上传处理  
        upload.setSizeMax(FILESIZEMAX);
        List<FileItem> list = (List<FileItem>)upload.parseRequest(request); //可以上传多个文件  
        
        for(FileItem item : list){ 
            String name = item.getFieldName();  							//获取表单的属性名字  
            if(item.isFormField())  										//如果获取的 表单信息是普通的 文本 信息  
            {                     
                //获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的  
                String value = ChineseCode.toUTF8(item.getString()) ; 
                map.put(name, value);
            }  
            //对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些  
            else  
            {  
                /** 
                 * 以下三步，主要获取 上传文件的名字 
                 */  
                //获取路径名  
                String value = item.getName() ;  				//这是上传的文件名
                if (!StringUtil.isNullOrEmpty(value)) {
                	int start = value.lastIndexOf("\\");  			//索引到最后一个反斜杠
                    String filename = value.substring(start+1);  	//截取 上传文件的 字符串名字，加1是 去掉反斜杠，  
                    filename = filename.replaceAll("@", "");
                    filename = filename.replaceAll("&", "");
                    filename = filename.replaceAll("\\?", "");
                    filename = filename.replaceAll("=", "");
                    filename = filename.replaceAll(" ", "");
                    
                    map.put(name, fileBorderName+"/"+filename);			//返回项目名称以后的路径名称
                    request.setAttribute(name, filename);  
                    //真正写到磁盘上  
                    //它抛出的异常 用exception 捕捉  
                    //item.write( new File(path,filename) );//第三方提供的  
                    //手动写的  
                    OutputStream out = new FileOutputStream(new File(path,filename)); //path+filename是文件的完整路径
                    InputStream in = item.getInputStream() ;  
                    int length = 0 ;  
                    byte [] buf = new byte[1024] ;  
                    System.out.println("获取上传文件的总共的容量："+item.getSize());  
      
                        // in.read(buf) 每次读到的数据存放在   buf 数组中  
                    while((length = in.read(buf)) != -1){  
                        out.write(buf, 0, length);  		//在   buf 数组中 取出数据 写到 （输出流）磁盘上  
                    }
                    in.close();  
                    out.close(); 
				}
            }  
        }
        
        return map;
	}
}
