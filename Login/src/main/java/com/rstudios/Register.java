package com.rstudios;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		String name= req.getParameter("username");
		String email= req.getParameter("email");
		String password= req.getParameter("password");
		String country = req.getParameter("gender");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlets","root","Raghu@03041996$");
			PreparedStatement ps = con.prepareStatement("insert into registeruser values(?,?,?,?) ");
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, country);
			int count = ps.executeUpdate();
			if(count>0)
			{
				resp.setContentType("text/html");
				out.print("<h3 style= 'Color:green'> User Registred Successfully </h3>");
				RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
				rd.include(req, resp);
			}
			else
			{
				resp.setContentType("text/html");
				out.print("<h3 style= 'Color:red'> User Registred failed Successfully </h3>");
				RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
				rd.include(req, resp);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			resp.setContentType("text/html");
			out.print("<h3 style= 'Color:red'>  Exception Occured :"+e.getMessage()+" </h3>");
			RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
			rd.include(req, resp);
			
			
		}
		
		
	}
	
	
	

}
