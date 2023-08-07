package vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TempVO {
	private long roomId; // 방이름 
    private String roomTitle; // 방제목
    private String roomPassword; // 방비밀번호
    private String gameType;//게임 유형
    private String roomStatus;//방 정보
    private Timestamp createdAt;//방이 만들어진시간
    private Timestamp updatedAt;//방이 업데이트된 시간

    
    private int pno1;
	private String userId1;
	private String name1;
	private int time1;
	private int x1;
	private int y1;
	private boolean turn1;
	
	private int pno2;
	private String userId2;
	private String name2;
	private int time2;
	private int x2;
	private int y2;
	private boolean turn2;
	
	private int[][] board; 
	public TempVO() {};
}
