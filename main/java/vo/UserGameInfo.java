package vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGameInfo {
	private int mno;
	private String name;
	private String id;
	private String pw;
	private String email;
	private Timestamp regDate; 
	
	private int record_id; //게임 번호
    private User u_mno; // 유저의 번호
    private int win; // 승
    private int lose;// 패
    private int draw;// 무승부
    private double odds;//승률
    private Timestamp play_date;//마지막으로 플레이한 시간
}
