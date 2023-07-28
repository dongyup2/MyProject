package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.ServletContextConfig;
import services.GamePageService;

/**
 * Servlet implementation class CheckEmptyAndDeleteRoomServlet
 */
@WebServlet("/checkEmptyAndDeleteRoom")
public class CheckEmptyAndDeleteRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final GamePageService gamePageService;
	
    public CheckEmptyAndDeleteRoomServlet() {
    	gamePageService = ServletContextConfig.getInstance().getGamePageService();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roomid = request.getParameter("roomId");
		
		
	}

}
