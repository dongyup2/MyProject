package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.ServletContextConfig;
import dao.GamePageDao;

@WebServlet("/User2RoomEnterServlet")
public class User2RoomEnterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final GamePageDao gamePageDao;
 
    public User2RoomEnterServlet() {
       gamePageDao = ServletContextConfig.getInstance().getGamePageDao(); 
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomId = request.getParameter("roomId");
        String userId2 = request.getParameter("userId");	
        System.out.println(roomId + "번방에 " + userId2 + "님이 입장하셨습니다!");
        int updateResult = gamePageDao.updateRoomUserId2(roomId, userId2);
        if (updateResult > 0) {
            // userId2 업데이트가 성공한 경우, 클라이언트에 성공에 대한 응답을 전합니다.
            response.getWriter().write("success");
        } else {
            // userId2 업데이트가 실패한 경우, 클라이언트에 실패에 대한 응답을 전송합니다.
            response.getWriter().write("failed");        
        }
    }
}
