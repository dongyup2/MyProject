package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.ServletContextConfig;
import services.UserService;

@WebServlet("/idCheck")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final UserService userService;
	
    public IdCheckServlet() {
        userService = ServletContextConfig.getInstance().getUserService();
    }
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain;charset=UTF-8");
		String param_id = request.getParameter("id");
		try {
			boolean isValid = userService.idCheckUser(param_id);	
			System.out.println(isValid);
			out.println(isValid ? "0" : "1");
		} catch (Exception e) {
			e.printStackTrace();
		}	   
	}    
}
