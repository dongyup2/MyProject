package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import vo.TempVO;

@WebServlet("/ConnTest")
public class ConnTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TempVO tv = new TempVO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf8");
		PrintWriter out = response.getWriter();
		String run = request.getParameter("run");
		
		if(run.equals("roomInfo")) {
			if(tv.getPno1() == 0) {
				// p1 값 생성
				tv.setPno1(1);
				tv.setUserId1("asd");
				tv.setName1("철수");
				tv.setTurn1(false);
			} else if(tv.getPno2() == 0) {
				tv.setPno2(2);
				tv.setUserId2("kkk");
				tv.setName2("영희");
				tv.setTurn2(false);
			} else {
				;
			}
			JSONObject obj = new JSONObject(tv);
			out.print(obj);
		} else if (run.equals("check")) {
			if(tv.getPno2() != 0) {
				JSONObject obj = new JSONObject(tv);
				out.print(obj);
			}
		}
	}

}
