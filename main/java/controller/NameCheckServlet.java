package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.ServletContextConfig;
import services.UserService;

/**
 * Servlet implementation class NameCheckServlet
 */
@WebServlet("/nicknameCheck")
public class NameCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserService userService;
	
	public NameCheckServlet() {
		userService = ServletContextConfig.getInstance().getUserService();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain;charset=UTF-8");
		String param_Name = request.getParameter("signupName");
		try {
			boolean isValid = userService.nameCheckUser(param_Name);
			System.out.println(isValid);
			out.print(isValid ? "1" : "0");
		} catch (Exception e) {
			e.printStackTrace();
		}	   
	}

}
