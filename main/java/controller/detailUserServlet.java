package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.ServletContextConfig;
import services.UserService;
import vo.UserGameInfo;


@WebServlet("/detailUser")
public class detailUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final UserService userService;

	public detailUserServlet() {
		userService = ServletContextConfig.getInstance().getUserService();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String id = null;
	    String pw = null;

	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if ("id".equals(cookie.getName())) {
	                id = cookie.getValue();
	            } else if ("pw".equals(cookie.getName())) {
	                pw = cookie.getValue();
	            }
	        }
	    }

	    try {
	        UserGameInfo userGameInfo = userService.getUserGameInfo(id, pw);
	        request.setAttribute("userGameInfo", userGameInfo);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    request.getRequestDispatcher("detailUserPage.jsp").forward(request, response);
	}

}
