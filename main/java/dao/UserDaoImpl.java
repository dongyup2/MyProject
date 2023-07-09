package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdbc.DBcon;
import services.UserService;
import services.UserServiceImpl;
import vo.User;

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
}