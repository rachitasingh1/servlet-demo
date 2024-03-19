package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out= resp.getWriter();
		String email= req.getParameter("email");
		String password= req.getParameter("password");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registerservlet","root","mysql");
			PreparedStatement ps = con.prepareStatement("select * from register where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				HttpSession session = req.getSession();
				session.setAttribute("session_name", rs.getString("name"));
				
				RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
				rd.include(req, resp);
				
			}else {
				
				out.print("<h1 style='color:red'>OOPS! Email and Password didn't match. </h1>");
				RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
				rd.include(req, resp);
			}
		}catch(Exception e) {
			e.printStackTrace();
			
			out.print("<h1 style='color:red'>"+e.getMessage()+" </h1>");
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.include(req, resp);
		}
	}

}
