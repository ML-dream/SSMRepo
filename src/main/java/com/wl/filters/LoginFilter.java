package com.wl.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;

public class LoginFilter implements Filter {
	private FilterConfig config;
	
	public void init(FilterConfig Config) throws ServletException {
		this.config = Config;
	}
	
	public void destroy() {
		this.config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String encoding =config.getInitParameter("encoding");
		String loginpage = config.getInitParameter("loginPage");

//		String loginHandler = "";
		request.setCharacterEncoding(encoding);
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		System.out.println(httpServletRequest.getRemoteAddr()+"访问了："+httpServletRequest.getServletPath());
		HttpSession httpSession = httpServletRequest.getSession(true);
		String requestPath = httpServletRequest.getServletPath();
		String urlRequest = httpServletRequest.getRequestURL().toString();
//		System.out.println(urlRequest);
		if (urlRequest.endsWith("/bg14.jpg")) {
			System.out.println("url以images等结尾！！！");
			chain.doFilter(request, response);
		}
		
		
		if (httpSession.getAttribute("user")==null ) {    //此时session中没有，说明之前没有登录
			System.out.println("用户之前没有登录！！！");
			if(requestPath.endsWith("LoginHandler")){
				chain.doFilter(request, response);
			}else {
				request.getRequestDispatcher(loginpage).forward(request, response);
			}
			
		}else {                                           //用户已经登录了
			int authority = Integer.parseInt(((User)httpSession.getAttribute("user")).getAuthority());
			if (999999==authority) {//99999999代表超级管理员，此处的逻辑是只对超级管理员开放所有权限
				//下面代码是对用户请求放行！！！
				chain.doFilter(request, response);
			}
			if (99==authority) {
				System.out.println("总监理登录");
			}
			if (66==authority) {
				System.out.println("财务");
			}
			if (44==authority) {
				System.out.println("采购");
			}
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
