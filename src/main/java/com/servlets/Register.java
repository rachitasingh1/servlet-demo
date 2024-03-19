package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("doPost invoked");
		PrintWriter out = resp.getWriter();
		String name= req.getParameter("name");
		String email= req.getParameter("email");
		String password= req.getParameter("password");
		String region= req.getParameter("region");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registerservlet","root","mysql");
			PreparedStatement ps = con.prepareStatement("insert into register values(?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, region);
			
			
			int count=ps.executeUpdate();
			if(count >0) {
				resp.setContentType("text/html");
				out.print("<h1 style='color:green'>User registered successfully. </h1>");
				RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
				rd.include(req, resp);
			}else {
				resp.setContentType("text/html");
				out.print("<h1 style='color:red'>OOPS! Registration failed. </h1>");
				RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
				rd.include(req, resp);
			}
		}catch(Exception e) {
			e.printStackTrace();
			resp.setContentType("text/html");
			out.print("<h1 style='color:red'>OOPS! Registration failed: Exception occurred: "+e.getMessage()+" </h1>");
			RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
			rd.include(req, resp);
		}finally {
			
		}
		
		
	}

}
