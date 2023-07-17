package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.ServletContextConfig;
import services.UserService;

/**
 * Servlet implementation class userInfoServlet
 */
@WebServlet("/userInfoServlet")
public class userInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final UserService userService;
	
	public userInfoServlet() {
		userService = ServletContextConfig.getInstance().getUserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user1Name = request.getParameter("user1Name");
		String user1Info = request.getParameter("user1Info");
		
		
	}


}
