/**
 * 项目名称: work
 * 创建日期：2017-3-1
 * 修改历史：
 *		1.[2017-3-1]创建文件 by Flair
 */
package com.wl.tools;

import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;  
import org.apache.commons.lang.StringUtils;  
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility; 


/**
 * @author Flair
 *
 */
public class Util {
	
		/** 
		   * 设置下载文件中文件的名称 
		   *  
		   * @param filename 
		   * @param request 
		   * @return 
		   */  
		  public static String encodeFilename(String filename, HttpServletRequest request) {  
		    /** 
		     * 获取客户端浏览器和操作系统信息 
		     * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar) 
		     * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6 
		     */  
		    String agent = request.getHeader("USER-AGENT");  
		    try {  
		      if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {  
		        String newFileName = URLEncoder.encode(filename, "UTF-8");  
		        newFileName = StringUtils.replace(newFileName, "+", "%20");  
		        if (newFileName.length() > 150) {  
		          newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");  
		          newFileName = StringUtils.replace(newFileName, " ", "%20");  
		        }  
		        return newFileName;  
		      }  
		      if ((agent != null) && (-1 != agent.indexOf("Mozilla")))  
		        return MimeUtility.encodeText(filename, "UTF-8", "B");  
		  
		      return filename;  
		    } catch (Exception ex) {  
		      return filename;  
		    }  
		  }  
	}


