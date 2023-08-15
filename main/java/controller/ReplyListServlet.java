package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ReplyDaoImpl;
import vo.Reply;

@WebServlet("/replyList")
public class ReplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReplyListServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		int bno = Integer.parseInt(request.getParameter("bno")); // 게시물 번호를 가져온다
		ReplyDaoImpl replyDaoImpl = new ReplyDaoImpl();
		List<Reply> replyList = replyDaoImpl.getReplyList(bno);
		Gson gson = new Gson();
		String replyListJson = gson.toJson(replyList);
		System.out.println(replyListJson);
		PrintWriter out = response.getWriter();
		out.print(replyListJson); 
		out.flush(); 
		out.close();

//		request.setAttribute("replyList", replyList);
//		request.getRequestDispatcher("CommunityInfo.jsp").forward(request, response);

	}
}
