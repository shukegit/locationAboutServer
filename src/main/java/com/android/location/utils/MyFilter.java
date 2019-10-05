package com.android.location.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;


public class MyFilter implements Filter {

	public void destroy() {
		System.out.println("销毁过滤器方法");
		System.out.println("销毁过滤器方法");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		String servletPath = request.getServletPath();
		System.out.println("<-------------------------------Filter Begin------------------------------------->");
		System.out.println("请求路径过滤信息路径为：" + servletPath);

	    String token = HttpServletRequestUtil.getString(request, "token");
	    String username = HttpServletRequestUtil.getString(request, "username");
	    System.out.println(username+"登录  token:" + token);
	    Date date = new Date();
	    int dateString = date.getHours() + date.getMinutes() + date.getSeconds();
	    token = String.valueOf(dateString);
	    request.getSession().setAttribute("token", token);
        System.out.println("已经存入session:" + request.getSession().getAttribute("token"));
        HttpSession session = request.getSession(false);
        session.setAttribute("token", token);
        if(token == null) {
        	PrintWriter pw = response.getWriter();
        	pw.print("error operation!!!");
        } else {
        	chain.doFilter(req, res);
        }
		
//		String token = (String) request.getSession().getAttribute("token");
//		if(token != null || !"".equals(token)) {
//			chain.doFilter(req, res);
//		} else {
//			String contextPath = request.getContextPath();
//			response.setCharacterEncoding("utf-8");
//			request.setCharacterEncoding("utf-8");
//			response.setContentType("text/html");
//			PrintWriter pw = response.getWriter();
//			// 否则拦截，跳转指定的页面
////			pw.print("<script>window.top.location.href='" + contextPath + "/aa.jsp'" + ";</script>");
//			pw.print("error operation!!!");
//		}

		// 访问login.jsp时，才放过，并且login.jsp的后续操作，继续执行，不拦截
//		if (servletPath.equals("/login.jsp")) {
//			chain.doFilter(req, res);
//			return;
//		} else {
//			String contextPath = request.getContextPath();
//			response.setCharacterEncoding("utf-8");
//			request.setCharacterEncoding("utf-8");
//			response.setContentType("text/html");
//			PrintWriter pw = response.getWriter();
//			// 否则拦截，跳转指定的页面
//			pw.print("<script>window.top.location.href='" + contextPath + "/aa.jsp'" + ";</script>");
//		}
        
		System.out.println("<-------------------------------Filter End------------------------------------->");
	}

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("初始化过滤器的方法");
		System.out.println("初始化过滤器的方法");
	}

}
