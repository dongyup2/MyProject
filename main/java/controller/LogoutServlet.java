package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.User;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logout(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logout(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("principal") != null) {
            String userId = ((User)session.getAttribute("principal")).getId();
            session.invalidate();
            System.out.println(userId + "님 로그아웃 성공!");
        } else {
            System.out.println("로그인 상태가 아닙니다.");
        }
        // index 페이지로 리다이렉트
        response.sendRedirect("omokMainPage3.jsp");
    }
}
