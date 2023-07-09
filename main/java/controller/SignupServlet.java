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

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final UserService userService;
	
	public SignupServlet() {
		userService = ServletContextConfig.getInstance().getUserService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("signupPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = User.builder()
					.name(request.getParameter("signupName"))
					.id(request.getParameter("signupId"))
					.pw(request.getParameter("signupPw"))
					.email(request.getParameter("signupEmail"))
					.build();
		try {
			if(userService.createUser(user)) {
				System.out.println("회원가입 성공!");
			}else {
				System.out.println("회원가입 실패!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
