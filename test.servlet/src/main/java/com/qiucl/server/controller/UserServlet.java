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
		//1.��ȡ��¼�û����û���������
		String user = request.getParameter("userName");
		String password = request.getParameter("userPassword");
		
		//2.��һ�ε�½������Cookie��Session
		Cookie userCookie = new Cookie("user",user);
		userCookie.setMaxAge(30*60);
		response.addCookie(userCookie);
		
		HttpSession session = request.getSession();
		String sessionPwd = (String) session.getAttribute("password");
		session.setAttribute("password", password);
		if(sessionPwd !=null ) {
			//3.�����½������apiʹ��sessionʧЧ
			session.invalidate();
			password = sessionPwd;
		}

		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("user"))
				{
					//3.��������cookie���û�����ֵ�Ƿ�͵�ǰ���û����Ƿ�һ�£������һ�����ӡ����ǰ������û���
					if(cookie.getValue()!=user) {
						System.out.println("��ǰ������û���:"+user);
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
				writer.println("<head><title>�û�����</title></head>");
				writer.println("<body>");
				writer.println("<p>�û�����" + user + "</p>");
				writer.println("<p>�û����룺" + password + "</p>");
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
