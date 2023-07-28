package services;

import java.util.List;

import config.ServletContextConfig;
import dao.GamePageDao;
import vo.GameRoom;

public class GamePageServiceImpl implements GamePageService{

	private final GamePageDao gamePageDao;
	
	public GamePageServiceImpl(){
		gamePageDao = ServletContextConfig.getInstance().getGamePageDao();
	}
	
	@Override
	public int createRoom(String roomTitle, String password, String gameType,String userId) {
		System.out.println("방제목, 비밀번호, 게임유형 정보가 dao로 갑니다.");
		int roomId = gamePageDao.createRoom(roomTitle, password, gameType, userId);
		 return roomId;
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
	public void handleUserJoinOrLeaveRoom(int roomId, String userId, boolean isJoining) {
		  if (isJoining) {
			  gamePageDao.addUserToRoom(roomId, userId);
	        } else {
	        	gamePageDao.removeUserFromRoom(roomId, userId);

	            int remainingUserCount = gamePageDao.getNumberOfUsersInRoom(roomId);

	            if (remainingUserCount == 0) {
	            	gamePageDao.deleteRoom(roomId);
	            }
	        }
	}

}
