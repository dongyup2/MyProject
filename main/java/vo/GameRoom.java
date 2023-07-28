package vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameRoom {
	 	private long roomId; // 방이름 
	    private String roomTitle; // 방제목
	    private String roomPassword; // 방비밀번호
	    private String gameType;//게임 유형
	    private String userId1;//유저 1
	    private String userId2;// 유저 2
	    private int userCount;//현재 방의 유저수
	    private int maxUserCount;//최대 방의수
	    private String roomStatus;//방 정보
	    private Timestamp createdAt;//방이 만들어진시간
	    private Timestamp updatedAt;//방이 업데이트된 시간
}
