package dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import jdbc.DBcon;
import vo.User;
import vo.UserGameInfo;

public class UserDaoImpl implements UserDao{
	private DBcon pool;
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;
	
	public UserDaoImpl() {
		pool = DBcon.getInstance().getInstance();
	}
	
	@Override
	public int save(User user) throws Exception {
		int result = 0;
		sql = "INSERT INTO omok_user_regist \r\n"
				+ "VALUES(0,?,?,?,?,NOW());";
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);			
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getId());
			pstmt.setString(3, user.getPw());
			pstmt.setString(4, user.getEmail());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
		}
		return result;
	}
}

	@Override
	public int countById(String id) throws Exception {
		System.out.println("Dao의 countId 메소드가 호출되었습니다." + id);
		int result = 0;
		sql = "SELECT COUNT(*) FROM omok_user_regist WHERE id = ?;";
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
	            result = rs.getInt(1);
	            System.out.println(result);
	        }		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null) con.close();				
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int countByName(String name) throws Exception {
		System.out.println("Dao의 countByName 메소드가 호출되었습니다." + name);
		int result = 0;
		sql = "SELECT COUNT(*) FROM omok_user_regist WHERE NAME=?;";
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
	            result = rs.getInt(1);
	            System.out.println(result);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public User loginCheck(String id, String pw) throws Exception {
		User user = null;
		sql = "SELECT * FROM omok_user_regist WHERE id = ? AND pw = ?";
		con = pool.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setMno(rs.getInt("mno"));
				user.setId(rs.getString("id"));
				user.setPw(rs.getString("pw"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setRegDate(rs.getTimestamp("regDate"));
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return user;
		
		}

	@Override
	public int getMnoByIdAndPassword(String id, String pw) throws Exception {
	    int result = -1; // 초기 mno 값을 -1으로 변경
	    sql = "SELECT mno FROM omok_user_regist WHERE id= ? AND pw = ?;"; // COUNT(*) 대신 mno를 선택
	    try {
	        con = pool.getConnection();
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, id);
	        pstmt.setString(2, pw);
	        rs = pstmt.executeQuery();
	        if(rs.next()) {
	            result = rs.getInt("mno"); // mno를 얻어오는 코드로 변경
	            System.out.println(result);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
	    }
	    return result;
	}


	@Override
	public UserGameInfo getUserGameInfo(int mno) throws Exception {
		UserGameInfo userGameInfo = null;
		
		sql = "SELECT u.mno, u.name, u.id, u.pw, u.email, u.regdate, g.record_id, g.win, g.lose, g.draw, g.play_date " +
                "FROM omok_user_regist u " +
                "LEFT JOIN omok_game_record g ON u.mno = g.mno " +
                "WHERE u.mno = ?";
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				  int recordMno = rs.getInt("mno");
	                String name = rs.getString("name");
	                String id = rs.getString("id");
	                String pw = rs.getString("pw");
	                String email = rs.getString("email");
	                Timestamp regdate = rs.getTimestamp("regdate");
	                int recordId = rs.getInt("record_id");
	                int win = rs.getInt("win");
	                int lose = rs.getInt("lose");
	                int draw = rs.getInt("draw");
	                Timestamp playDate = rs.getTimestamp("play_date");

	                userGameInfo = UserGameInfo.builder()
	                        .mno(recordMno)
	                        .name(name)
	                        .id(id)
	                        .pw(pw)
	                        .email(email)
	                        .regDate(regdate)
	                        .record_id(recordId)
	                        .win(win)
	                        .lose(lose)
	                        .draw(draw)
	                        .play_date(playDate)
	                        .build();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return userGameInfo;
	}
}
