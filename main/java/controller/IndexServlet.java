package controller;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.User;

/*
 * 요청의 종류
 * 1. view(HTML) 요청 -> 무조건 get요청(주소창)
 * 2. api(Application Programming Interface) 요청 -> CRUD(post, get, put, delete): JavaScript(AJAX 동기, 비동기)
 */

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("principal");
        if (user != null) {
            request.setAttribute("sessionId", user.getId());
        }
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("id")) {
//                    request.setAttribute("storedId", cookie.getValue());
//                    break;
//                }
//            }
//        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
	


