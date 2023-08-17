package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.google.gson.Gson;

import config.ServletContextConfig;
import services.GamePageService;
import vo.GameRoom;

@WebServlet("/checkEmptyRoom")
public class CheckEmptyRoomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private final GamePageService gamePageService;
    
    public CheckEmptyRoomServlet() {
        gamePageService = ServletContextConfig.getInstance().getGamePageService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<GameRoom> gameRoomList = gamePageService.getRoomList();
        
        // JSON 형식으로 응답을 반환합니다.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String json = gson.toJson(gameRoomList);
        System.out.println(json);
        response.getWriter().write(json);
    }
}																																																				



