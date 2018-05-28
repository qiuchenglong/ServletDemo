package com.qiucl.server.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletConfigServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.print("ServletConfig test");
		out.close();	
	}

	@Override
	public void init() throws ServletException {
		ServletContext ctx = this.getServletContext();
		String globeValue1 = ctx.getInitParameter("globeData1");
		String globeValue2 = ctx.getInitParameter("globeData2");
		System.out.println("servlet config globe value1: "+globeValue1 +",globe value2: "+globeValue2);
		
		String attribute = (String) ctx.getAttribute("attribute1");
		System.out.println("attribute: "+ attribute);
	}

}
