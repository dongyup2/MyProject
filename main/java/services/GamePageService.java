package services;

import java.util.List;

import vo.GameRoom;

public interface GamePageService {
    public int createRoom(String roomTitle, String password, String gameType, String userId);
    public List<GameRoom> getRoomList();
    public GameRoom getRoomById(int roomId);
    public void handleUserJoinOrLeaveRoom(int roomId, String userId, boolean isJoining);
}

