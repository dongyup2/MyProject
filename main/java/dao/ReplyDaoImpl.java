package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.DBcon;
import vo.Reply;

public class ReplyDaoImpl implements ReplyDao{
	private DBcon db;
	
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;
	private Connection con;
	
	public ReplyDaoImpl() {
		db = DBcon.getInstance();
	}
	
	@Override
	public int deleteReply(int rno) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int registReply(Reply reply) {
		int result = 0;		
		sql = "INSERT INTO omok_community_reply (bno, comment, writer, regdate) "
				   + " VALUES (?, ?, ?, NOW())";
		try {
			con = db.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reply.getBno());
			pstmt.setString(2, reply.getComment());
			pstmt.setString(3, reply.getWriter());			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public List<Reply> getReplyList(int bno) {
	    List<Reply> replyList = new ArrayList<>();
	    try {
	        con = db.getConnection(); // DB 연결 객체 생성

	        sql = "SELECT * FROM omok_community_reply WHERE bno = ?"; // 해당 게시물의 댓글만 불러오는 쿼리
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, bno); // 첫 번째 인자값을 bno로 설정

	        rs = pstmt.executeQuery(); // 조회 결과를 ResultSet 객체에 저장

	        while (rs.next()) {
	            Reply reply = new Reply();
	            reply.setRno(rs.getInt("rno"));
	            reply.setBno(rs.getInt("bno"));
	            reply.setComment(rs.getString("comment"));
	            reply.setWriter(rs.getString("writer"));
	            reply.setRegdate(rs.getDate("regdate"));
	            replyList.add(reply); // 리스트에 댓글 객체 추가
	            
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    System.out.println(replyList);
	    return replyList; // 결과 리스트 반환
	}

}
