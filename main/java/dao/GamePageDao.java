package dao;

import java.util.List;

import vo.GameRoom;

public interface GamePageDao {
	public int createRoom(String roomTitle, String password, String gameType,String userId);
	public List<GameRoom> getRoomList();
	public GameRoom getRoomById(int roomId);
	public void addUserToRoom(int roomId, String userId);
	public void removeUserFromRoom(int roomId, String userId);
	public int getNumberOfUsersInRoom(int roomId);
	public void deleteRoom(int roomId);
}
