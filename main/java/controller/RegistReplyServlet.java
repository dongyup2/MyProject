package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReplyDaoImpl;
import vo.Reply;
import vo.User;

@WebServlet("/registReply")
public class RegistReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("principal");
		String name = user.getName();
		
		String param_bno = request.getParameter("bno");
		int bno = Integer.parseInt(param_bno);
		String comment = request.getParameter("comment");
		String writer = request.getParameter("writer");
		
		Reply reply = new Reply();
		reply.setBno(bno);
		reply.setComment(comment);
		reply.setWriter(name);
		
		ReplyDaoImpl rDao = new ReplyDaoImpl();
		
		int result = rDao.registReply(reply);
		
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
