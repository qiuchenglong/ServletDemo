package com.qiucl.server.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletForwardExample extends HttpServlet {

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("user");
		
		PrintWriter writer = resp.getWriter();
		writer.println("<html>");
		writer.println("<head><title>转发示例a</title></head>");
		writer.println("<body>");
		writer.println("<p>用户名a11 "+user +"</p>");
		writer.println("</body>");
		writer.println("</html>");
		resp.setContentType("text/html;charset=GBK");
		writer.close();
	}


}
