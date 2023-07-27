package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import config.ServletContextConfig;
import services.UserService;

@WebServlet("/CreateRoomServlet")
public class CreateRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final UserService userService;
	 private final RoomService roomService; // RoomService 추가

	    public CreateRoomServlet() {
	        userService = ServletContextConfig.getInstance().getUserService();
	        roomService = new RoomService(); // RoomService 인스턴스 생성
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String roomTitle = request.getParameter("roomTitle");
	        String password = request.getParameter("password");
	        String gameType = request.getParameter("gameType");

	        // 처리 결과 변수 초기화
	        JSONObject result = new JSONObject();

	        try {
	            // Create room using roomService
	            int roomId = roomService.createRoom(roomTitle, password, gameType);

	            // 성공적으로 방 생성
	            result.put("status", "success");
	            result.put("roomId", roomId);
	            result.put("roomTitle", roomTitle);
	            result.put("gameType", gameType);
	        } catch (Exception e) {
	            e.printStackTrace();
	            result.put("status", "fail");
	            result.put("message", e.getMessage());
	        }

	        // JSON 형식으로 응답 설정
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");

	        // JSON 응답 반환
	        try (PrintWriter out = response.getWriter()) {
	            out.write(result.toJSONString());
	        }
	    }

}
