package com.wl.tools;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharactorFilter implements Filter {
	
	String encoding = "utf-8";

	public void destroy() {
		// TODO Auto-generated method stub
		encoding=null;

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(encoding);//设置request字符编码
		response.setContentType("text/html;charset="+encoding); //设置response字符编码
		chain.doFilter(request, response);//传递给下一个过滤器
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		encoding=filterConfig.getInitParameter("encoding");//获取初始化参数


	}

}
