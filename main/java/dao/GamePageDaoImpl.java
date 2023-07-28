package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import jdbc.DBcon;
import vo.GameRoom;

public class GamePageDaoImpl implements GamePageDao{
	private DBcon db;
	
	private String sql;
	private PreparedStatement pstmt;
	private Connection con;
	private ResultSet rs;
	
	public GamePageDaoImpl() {
		db = DBcon.getInstance();
	}
	
	@Override
	public int createRoom(String roomTitle, String password, String gameType, String userId) {
	    int result = 0;
	    String sql = "INSERT INTO omok_game_rooms (room_title, room_password, game_type, user_id_1) VALUES(?, ?, ?, ?);";
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        con = db.getConnection();
	        pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        pstmt.setString(1, roomTitle);
	        pstmt.setString(2, password);
	        pstmt.setString(3, gameType);
	        pstmt.setString(4, userId);
	        
	        pstmt.executeUpdate();

	        // 생성된 room_id 가져오기
	        rs = pstmt.getGeneratedKeys();
	        if (rs.next()) {
	            result = rs.getInt(1);
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // 리소스 정리
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    return result;
	}


	@Override
	public List<GameRoom> getRoomList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameRoom getRoomById(int roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUserToRoom(int roomId, String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUserFromRoom(int roomId, String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNumberOfUsersInRoom(int roomId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteRoom(int roomId) {
		// TODO Auto-generated method stub
		
	}

}
