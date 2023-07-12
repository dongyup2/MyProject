package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.ServletContextConfig;
import services.UserService;
import vo.User;

@WebServlet("/login")
public class LoginCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private final UserService userService;
	
	public LoginCookieServlet() {
		userService = ServletContextConfig.getInstance().getUserService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 코드 추가: 쿠키에서 저장된 정보 가져오기
		List<Cookie> cookies = Arrays.asList(request.getCookies());

		cookies.forEach(cookie -> {
			if(cookie != null) {
				if(cookie.getName().equals("username")) {
					request.setAttribute("username", cookie.getValue());
				}else if(cookie.getName().equals("password")) {
					request.setAttribute("password", cookie.getValue());
				}else if(cookie.getName().equals("maintain")) {
					request.setAttribute("maintain", cookie.getValue());
				}
			}
		});

		request.getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String maintainFlag = request.getParameter("maintain"); // 코드 추가: 로그인 정보 유지 여부 가져오기
		HttpSession session = request.getSession();
		
		try {
			User user = userService.loginCheck(id, pw); // 코드 추가: 로그인 처리 함수 변경
			if (user != null) {
		        System.out.println("로그인 성공!");
		        session.setAttribute("principal", user); // 코드 추가: 세션에 사용자 정보 저장

		        // 코드 추가: 로그인 정보 유지 여부 확인 및 쿠키 저장
				if(maintainFlag != null && maintainFlag.equalsIgnoreCase("on")) {
					List<Cookie> cookies = new ArrayList<Cookie>();
					
					cookies.add(new Cookie("username", id));
					cookies.add(new Cookie("password", pw));
					cookies.add(new Cookie("maintain", "true"));
					
					cookies.forEach(cookie -> {
						cookie.setMaxAge(60 * 60 * 24 * 30);
						response.addCookie(cookie);
					});
				}

		        response.getWriter().write("success");
		    } else {
		        response.getWriter().write("fail");
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
