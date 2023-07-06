package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdbc.DBcon;
import services.UserService;
import services.UserServiceImpl;
import vo.User;

public class UserDaoImpl implements UserDao{
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;
	
	@Override
	public int save(User user) throws Exception {
		int result = 0;
		sql = "INSERT INTO omok_user_regist \r\n"
				+ "VALUES(0,?,?,?,?,NOW());";
		try {
			con = DBcon.getConnection();
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
}