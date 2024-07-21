package com.rstudios;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class Login
 */

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		 resp.setContentType("text/html");
		String email = req.getParameter("Email");
		String password = req.getParameter("MyPassword");
		
		try {
              Class.forName("com.mysql.cj.jdbc.Driver");
              Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlets","root","Raghu@03041996$");
			  PreparedStatement ps = con.prepareStatement("select * from registeruser where email=? and password =?" );
			  ps.setString(1, email);
			  ps.setString(2, password);
			  ResultSet rs = ps.executeQuery();
			  
			  if(rs.next())
			  {
				 
				   HttpSession session = req.getSession();
				   session.setAttribute("session_name", rs.getString("name"));
					// out.print("<h3 style= 'Color:green'> User Registred Successfully </h3>");
					RequestDispatcher rd = req.getRequestDispatcher("/Profile.jsp");
					rd.include(req, resp);
			  }
			  else
			  {
				
					out.print("<h3 style= 'Color:red'> User Registred failed Successfully </h3>");
					RequestDispatcher rd = req.getRequestDispatcher("/Login.jsp");
					rd.include(req, resp);
			  }
			
			
			
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
			// resp.setContentType("text/html");
			out.print("<h3 style= 'Color:red'>  Exception Occured :"+e.getMessage()+" </h3>");
			RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
			rd.include(req, resp);
			
		}
		
		
		
		
	}

}

//PrintWriter pw = response.getWriter();
//pw.println("<h1> You have entered \n </h1> " + name + "\n" + pass);
//
//System.out.println(name +" \n " + pass);
