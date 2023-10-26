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

public class SaveUser extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		res.setContentType("text/html");

		PrintWriter pw = res.getWriter();

		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String country = req.getParameter("country");

		User user = null;
		if (name != null && password != null && !name.equals("") && !password.equals("")) {
			user = new User();
			user.setName(name);
			user.setPassword(password);
			user.setEmail(email);
			user.setCountry(country);
		}
		
		System.out.println(user.getName());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		System.out.println(user.getCountry());
		

		int status = UserDao.save(user);
		
		if(status > 0) {
			pw.print("<b>User Saved Successfully!<b><br><br>");
			req.getRequestDispatcher("login.html").include(req, res);
		}
		
		else {
			pw.print("<b>Sorry! unable to save the user!<b><br><br>");
		}

		pw.close();

	}

}
