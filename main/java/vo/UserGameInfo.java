package vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGameInfo {
    // User 클래스의 필드
    private int mno;
    private String name;
    private String id;
    private String pw;
    private String email;
    private Timestamp regDate; 
    // GameRecord 클래스의 필드
    private int record_id;
    private int win;
    private int lose;
    private int draw;
    private Timestamp play_date;
}