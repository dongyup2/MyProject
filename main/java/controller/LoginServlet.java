package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.ServletContextConfig;
import services.UserService;
import vo.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final UserService userService;
	
	public LoginServlet() {
		userService = ServletContextConfig.getInstance().getUserService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		HttpSession session = request.getSession();
		
		try {
			User user = userService.loginCheck(id, pw); // 코드 추가: 로그인 처리 함수 변경
			if (user != null) {
		        System.out.println("로그인 성공!");
		        session.setAttribute("principal", user);
		        request.getRequestDispatcher("omokMainPage3.jsp").forward(request, response);
		    } else {		       
		    	 request.setAttribute("errorMessage", "잘못된 아이디 또는 비밀번호입니다! ");
		         request.getRequestDispatcher("loginPageCookie.jsp").forward(request, response);
		         System.out.println("로그인 실패...");
		    }		
			} catch (Exception e) {
				e.printStackTrace();
		}
	}
}
