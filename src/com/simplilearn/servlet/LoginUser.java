package com.simplilearn.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.model.User;
import com.simplilearn.dao.UserDao;

public class LoginUser extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		res.setContentType("text/html");

		PrintWriter pw = res.getWriter();

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		

		User user = null;
		
		if (email != null && password != null && !email.equals("") && !password.equals("")) {
			user = new User();
			
			user.setEmail(email);
			user.setPassword(password);
			
			
		}
		
		
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		
		
		boolean isValidUser = UserDao.authenticate(user);
		
		if (isValidUser) {
			pw.print("<b>User Login Successful!<b><br><br>");
			req.getRequestDispatcher("home.html").include(req, res);
			
		}
        else {
        
            pw.print("<b>Invalid username or password. Please try again.<b><br><br>");
            req.getRequestDispatcher("login.html").include(req, res);
        }
	

		pw.close();

	}

}
