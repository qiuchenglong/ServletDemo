package com.qiucl.server.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		System.out.println("destortory method");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("init method");
	}


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service method");
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet method");
		PrintWriter pw = resp.getWriter();
		pw.print("hello world");
		pw.close();

	}
}
