package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import config.ServletContextConfig;
import dao.UserDao;
import vo.User;

@WebServlet("/FindIdServlet")
public class FindIdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDao userDao;

    public FindIdServlet() {
        userDao = ServletContextConfig.getInstance().getUserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        System.out.println(name);
        User user = userDao.findUserId(name, email);
        System.out.println(user);
        String resultJson = "{\"result\":";
        
        if (user != null) {
            resultJson += "\"success\",\"id\":\"" + user.getId() + "\",\"name\":\"" + user.getName() +"\"}";
        } else {
            resultJson += "\"failure\"}";
        }

        response.getWriter().write(resultJson);
    }


}
