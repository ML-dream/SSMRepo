/**
 * 项目名称: work
 * 创建日期：2016-7-13
 * 修改历史：
 *		1.[2016-7-13]创建文件 by Flair
 */
package com.wl.testaction.utils;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nl101 on 2016/3/8.
 */
public class MultipartRequestWrapper extends HttpServletRequestWrapper {

    //存储参数的集合
    private Map<String,String[]> params = new HashMap<String,String[]>();

    public MultipartRequestWrapper(HttpServletRequest request) {
        super(request);
        setParams(request);//对request进行解析,并存入map中
    }

    /**
     * 通过StreamingAPI的方式上传文件
     */
    private void setParams(HttpServletRequest request){
        //获取上传文件类型
        if (ServletFileUpload.isMultipartContent(request)){
            //创建ServletFileUpload实例
            ServletFileUpload fileUpload = new ServletFileUpload();
            InputStream is = null;//输出流
            try {
                //解析request请求 返回FileItemStream的iterator实例
                FileItemIterator iter = fileUpload.getItemIterator(request);
                
                //迭代取出
                while (iter.hasNext()){
                    FileItemStream item = iter.next();//获取文件流
                    String name = item.getFieldName();//返回表单中标签的name值
                    is = item.openStream();//得到对应表单的输出流
                    if (item.isFormField()){//如果是非文件域,设置进入map,这里要注意多值处理
                        setFormParam(name,is);//如果不是文件上传,处理
                    }//else {
//                        if (is.available()>0){//如果输出流的内容大于0
//                            String fname = item.getName();//获取文件名
//                            Streams.copy(is,new FileOutputStream(PATH+fname),true);//拷贝内容到上传路径
//                            params.put(name,new String[]{fname});//把文件名设置进request中
//                        }

                    //}
                }
                is.close();
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
            	if (null!=is) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
            }
        }else {
            params = request.getParameterMap();//如果不是文件上传请求,则直接设置map
        }
    }

    /**
     * 处理非上传的表单
     * @param name
     * @param is
     */
    private void setFormParam(String name, InputStream is) {
        try {
            if (params.containsKey(name)){//判断当前值name是否已经存储过
                String[] values = params.get(name);//取出已经存储过的值
                values = Arrays.copyOf(values,values.length+1);//把当前数组扩大
                values[values.length-1] = Streams.asString(is);//增加新值
                params.put(name,values);//重新添加到map中
            }else {
                params.put(name,new String[]{Streams.asString(is)});//直接存入参数中
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回参数map集合,自定义后上传文件,上传成功则返回文件名
     * @return
     */
    @Override
    public Map getParameterMap() {
        return params;
    }

    /**
     * 从请求中取出参数
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if(values!=null) {
            return values[0];
        }
        return null;
    }

    /**
     * 从请求中取出多个参数值,如checkbox的值
     * @param name
     * @return
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] values = params.get(name);
        if(values!=null) {
            return values;
        }
        return null;
    }

}