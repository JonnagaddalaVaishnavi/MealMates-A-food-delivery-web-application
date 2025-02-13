package com.myfoods.servlets;
import java.io.IOException;

import com.myfoods.daoimple.UserDAOImplementation;
import com.myfoods.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signUpServletNew")
public class SignUpServletNew extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String address = request.getParameter("address");

		UserDAOImplementation udi = new UserDAOImplementation();
		User existingUser = udi.getUserByEmail(email);

		try {
			if (existingUser != null) {
				request.setAttribute("error", "User already exists with this email.");
				request.getRequestDispatcher("varshaout.jsp").forward(request, response);

			}
			else {

				User user = new User(0, name, username, password, email, phone, address, null, null, null);
				udi.addUser(user);
				request.setAttribute("message", "Registration Sucessfull!!");
				request.getRequestDispatcher("varshain.jsp").forward(request, response);
      			
			}
		}
		
		catch (ServletException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
