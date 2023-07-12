package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdbc.DBcon;
import services.UserService;
import services.UserServiceImpl;
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
	public UserGameInfo loginCheck(String id, String pw) throws Exception {
		UserGameInfo usergameinfo = null;
		sql = "SELECT\r\n"
				+ "  u.mno AS user_id, u.NAME AS user_name,\r\n"
				+ "  u.email AS email, u.regdate AS registration_date,\r\n"
				+ "  COUNT(g.record_id) AS total_games,\r\n"
				+ "  SUM(g.win) AS wins, SUM(g.lose) AS losses,\r\n"
				+ "  SUM(g.draw) AS draws, SUM(g.win) / COUNT(g.record_id) * 100 AS winning_percentage,\r\n"
				+ "  (SELECT COUNT(*) FROM omok_game_record WHERE win >= 1 ORDER BY play_date DESC) AS winning_streak,\r\n"
				+ "  (SELECT COUNT(*) FROM omok_game_record WHERE lose >= 1 ORDER BY play_date DESC) AS losing_streak\r\n"
				+ "FROM\r\n"
				+ "  omok_user_regist u\r\n"
				+ "LEFT JOIN\r\n"
				+ "  omok_game_record g ON u.mno = g.mno\r\n"
				+ "WHERE\r\n"
				+ "  id = ? AND pw = ?\r\n";
		con = pool.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				usergameinfo = UserGameInfo.builder()
			                .user_id(rs.getInt("user_id"))
			                .user_name(rs.getString("user_name"))
			                .email(rs.getString("email"))
			                .registration_date(rs.getTimestamp("registration_date"))
			                .total_games(rs.getInt("total_games"))
			                .wins(rs.getInt("wins"))
			                .losses(rs.getInt("losses"))
			                .draws(rs.getInt("draws"))
			                .winning_percentage(rs.getDouble("winning_percentage"))
			                .winning_streak(rs.getInt("winning_streak"))
			                .losing_streak(rs.getInt("losing_streak"))
			                .build();
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
		return usergameinfo;
	}
	
}