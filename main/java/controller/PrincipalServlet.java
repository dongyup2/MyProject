package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import vo.GameRecord;
import vo.User;

@WebServlet("/principal")
public class PrincipalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        System.out.println("세션에 저장된 데이터 확인");
        HttpSession session = request.getSession();
        if(session.getAttribute("principal") != null) {
            System.out.println("로그인된 User 정보: " + session.getAttribute("principal"));
        } else {
            System.out.println("현재 로그인된 정보 없음");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "로그인되어 있지 않습니다.");
            return;
        }

        User user = (User) session.getAttribute("principal");

        // 게임 데이터는 데이터베이스에서 가져오기 --지금은 임시--
        GameRecord gameRecord = new GameRecord(1, user.getMno(), 10, 5, 3,50.6, new Timestamp(System.currentTimeMillis()));
        System.out.println(user.getId() +"님의 게임 정보: " + gameRecord);
        JSONObject userGameInfoJson = new JSONObject();
        userGameInfoJson.put("user", new JSONObject(user));
        userGameInfoJson.put("game_record", new JSONObject(gameRecord));

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(userGameInfoJson.toString());
    }
}
