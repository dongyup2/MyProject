package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import config.ServletContextConfig;
import services.GamePageService;
import vo.GameRoom;

@WebServlet("/getRoomListServlet")
public class getRoomListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final GamePageService gamePageService;
	
    public getRoomListServlet() {
    	gamePageService = ServletContextConfig.getInstance().getGamePageService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<GameRoom> gameRoomList = gamePageService.getRoomList();
        
        JSONArray jsonArray = new JSONArray();
        for (GameRoom gameRoom : gameRoomList) {
          JSONObject jsonObject = new JSONObject();
          jsonObject.put("room_id", gameRoom.getRoomId());
          jsonObject.put("room_title", gameRoom.getRoomTitle());
          jsonObject.put("user_id_1", gameRoom.getUserId1());
          jsonObject.put("user_id_2", gameRoom.getUserId2());
          jsonObject.put("game_type", gameRoom.getGameType());
          jsonArray.put(jsonObject);
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonArray.toString());
      }
    }
