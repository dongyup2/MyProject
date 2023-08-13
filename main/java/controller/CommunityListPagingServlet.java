package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import config.ServletContextConfig;
import dao.CommunityBoardDao;
import services.CommunityBoardService;
import vo.Board;

/**
 * Servlet implementation class CommunityListPagingServlet
 */
@WebServlet("/CommunityListPagingServlet")
public class CommunityListPagingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final CommunityBoardService communityBoardService;
	
	public CommunityListPagingServlet() {
		communityBoardService = ServletContextConfig.getInstance().getCommunityBoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("p"); 
		if (param == null) {
			param = "1";
		}
		int p = Integer.parseInt(param);
		List<Board> blist = communityBoardService.getListWithPaging(p);
		int total = communityBoardService.getListNum();
		int startNum = p - ((p-1)%5); 
		int lastPageNum = startNum+4;

		// JSON 형식으로 응답
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String jsonResponse = "{\"total\":" + total + ", \"lastPageNum\":" + lastPageNum + ", \"startNum\":" + startNum + ", \"list\":" + new Gson().toJson(blist) + "}";
		out.print(jsonResponse);
		out.flush();
	}

}
