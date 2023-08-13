package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import config.ServletContextConfig;
import services.CommunityBoardService;
import vo.Board;

@WebServlet("/CommunityPostDetailServlet")
public class CommunityPostDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CommunityBoardService communityBoardService;

	public CommunityPostDetailServlet() {
		communityBoardService = ServletContextConfig.getInstance().getCommunityBoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Board board = new Board();
		String postIdStr = request.getParameter("postId");
		int postId = Integer.parseInt(postIdStr);
		board = communityBoardService.selectOneBoard(postId);

		System.out.println(postId + "번의 게시글 조회성공!");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(board);
		System.out.println(jsonResponse);
		response.getWriter().write(jsonResponse);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
