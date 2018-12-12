package com.wl.testaction.warehouse.PR;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.payMethod;
import com.wl.tools.Sqlhelper;

public class ReceiptServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ReceiptServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String sql="select receiptid id,receiptname text from receipt";
		List<payMethod> resultList = new ArrayList<payMethod>();
		try {
			resultList = Sqlhelper.exeQueryList(sql, null, payMethod.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String json = PluSoft.Utils.JSON.Encode(resultList);
		out.append(json).flush();
		System.out.println(json);
		
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
