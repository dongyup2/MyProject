package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.ServletContextConfig;
import services.UserService;
import vo.User;
import vo.UserGameInfo;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final UserService userService;
	
	public LoginServlet() {
		userService = ServletContextConfig.getInstance().getUserService();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		UserGameInfo userGameInfo = null;
		
		try {
			userGameInfo = userService.loginCheck(id, pw);
			System.out.println("로그인 성공!");
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
		}
	
	}
}
