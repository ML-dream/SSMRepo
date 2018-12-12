/**
 * 项目名称: work
 * 创建日期：2016-8-4
 * 修改历史：
 *		1.[2016-8-4]创建文件 by Flair
 */
package com.wl.testaction.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.ProductStatus;
import com.wl.tools.Sqlhelper;

/**
 * @author Flair
 *
 */
public class GetProductStatus extends HttpServlet {
	private static final long serialVersionUID = -2474427336026675357L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    String Sql= "select statusid,statusname from productStatus order by statusId";
	    List<ProductStatus> resultList = new ArrayList<ProductStatus>();
	    try {
			resultList = Sqlhelper.exeQueryList(Sql, null, ProductStatus.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    String json = PluSoft.Utils.JSON.Encode(resultList);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}
