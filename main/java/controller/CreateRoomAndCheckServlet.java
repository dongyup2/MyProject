package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import config.ServletContextConfig;
import services.GamePageService;
import vo.GameRoom;
import vo.TempVO;
import vo.User;

@WebServlet("/createRoomAndCheck")
public class CreateRoomAndCheckServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private final GamePageService gamepageService;

    public CreateRoomAndCheckServlet() {   
        gamepageService = ServletContextConfig.getInstance().getGamePageService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomTitle = request.getParameter("roomTitle");
        String password = request.getParameter("password");
        String gameType = request.getParameter("gameType");

        // 처리 결과 변수 초기화
        JSONObject result = new JSONObject();
        
        // 세션에 저장된 사용자 확인 후 처리 결과 JSON에 추가
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("principal");

        if (user == null) {
            result.put("status", "fail");
            result.put("message", "로그인이 필요합니다.");
        } else {
            try {
//                GameRoom gameRoom = new GameRoom();
//                gameRoom.setRoomTitle(roomTitle);
//                gameRoom.setRoomPassword(password);
//                gameRoom.setGameType(gameType);
//                
//                TempVO tempVO = gameRoom;
                int roomId = gamepageService.createRoom(roomTitle, password, gameType, user.getId());
                
                // 성공적으로 방 생성
                result.put("status", "success");
                result.put("roomId", roomId);
                result.put("roomTitle", roomTitle);
                result.put("gameType", gameType);
                result.put("userId", user.getId());
                result.put("userName", user.getName());
            } catch (Exception e) {
                e.printStackTrace();
                result.put("status", "fail");
                result.put("message", e.getMessage());
            }
        }

        // JSON 형식으로 응답 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // JSON 응답 반환
        try (PrintWriter out = response.getWriter()) {
            out.write(result.toString());
        }
       
    }
}
