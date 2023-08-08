package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import config.ServletContextConfig;
import services.CommunityBoardService;
import vo.Board;

@WebServlet("/CommunityServlet")
public class CommunityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CommunityBoardService communityBoardService;

	public CommunityServlet() {
		communityBoardService = ServletContextConfig.getInstance().getCommunityBoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Board> postList = new ArrayList<>(); // 이 리스트를 데이터베이스에서 가져온 결과로 채워야 합니다.

		// 예시 데이터 추가
		postList.add(new Board(1, "제목1", "작성자1", null, null, 2));
		postList.add(new Board(2, "제목2", "작성자2", null, null, 2));
		// 데이터베이스에서 게시물 데이터를 가져온 후 Post 객체의 리스트를 채운다.

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(postList);
		response.getWriter().write(jsonResponse);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
