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
import vo.UserGameInfo;

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

		if (request.getCookies() != null) {
		    for (Cookie cookie : request.getCookies()) {
		        if (cookie.getName().equals("id")) {
		            request.setAttribute("id", cookie.getValue());
		        } else if (cookie.getName().equals("pw")) {
		            request.setAttribute("pw", cookie.getValue());
		        } else if (cookie.getName().equals("maintain")) {
		            request.setAttribute("maintain", cookie.getValue());
		        }
		    }
		}


		request.getRequestDispatcher("loginPageCookie.jsp").forward(request, response);
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
		        if (maintainFlag != null && maintainFlag.equalsIgnoreCase("on")) {
		            Cookie[] cookies = new Cookie[3];
		            
		            cookies[0] = new Cookie("id", id);
		            cookies[1] = new Cookie("pw", pw);
		            cookies[2] = new Cookie("maintain", "true");
		            
		            for (Cookie cookie : cookies) {
		                cookie.setMaxAge(60 * 60 * 24 * 30);
		                response.addCookie(cookie);
		            }
		        }


		        response.getWriter().write("success");
		        response.sendRedirect("index.jsp");
		        System.out.println("로그인 중...");
		    } else {
		        response.getWriter().write("fail");
		        response.sendRedirect("loginPageCookie.jsp");
		        System.out.println("로그인 실패...");
		    }		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
