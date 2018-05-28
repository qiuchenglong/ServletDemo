package com.qiucl.server.controller;

import java.io.IOException;
import java.io.PrintWriter;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 4607606190625660785L;
	
	/**
	 * Logger for this class.
	 */
	//private static Logger logger = Logger.getLogger(UserServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//logger.info("UserServlet post method is invoke.");
		System.out.println("UserServlet post method is invok");
		resp.setContentType("text/html;charset=UTF-8");
		process(req, resp);
	}
	
	protected void process(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		RequestDispatcher dispatcher = null;
		//1.获取登录用户的用户名和密码
		String user = request.getParameter("userName");
		String password = request.getParameter("userPassword");
		
		//2.第一次登陆：创建Cookie、Session
		Cookie userCookie = new Cookie("user",user);
		userCookie.setMaxAge(30*60);
		response.addCookie(userCookie);
		
		HttpSession session = request.getSession();
		String sessionPwd = (String) session.getAttribute("password");
		session.setAttribute("password", password);
		if(sessionPwd !=null ) {
			//3.后面登陆：调用api使得session失效
			session.invalidate();
			password = sessionPwd;
		}

		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("user"))
				{
					//3.查请求中cookie的用户名的值是否和当前的用户名是否一致，如果不一致则打印出当前请求的用户名
					if(cookie.getValue()!=user) {
						System.out.println("当前请求的用户名:"+user);
						user = cookie.getValue();
					}
				}
			}
		}
		try {
			
			if(user.equals("qiucl") && password.equals("123654"))
			{
				PrintWriter writer = response.getWriter();
				writer.println("<html>");
				writer.println("<head><title>用户中心</title></head>");
				writer.println("<body>");
				writer.println("<p>用户名：" + user + "</p>");
				writer.println("<p>用户密码：" + password + "</p>");
				writer.println("</body>");
				writer.println("</html>");
				writer.close();
				response.setContentType("text/html;charset=GBK");
				
			}else {
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}
		} catch (Exception ex) {
			// TODO: handle exception
		}
	}
}
