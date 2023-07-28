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
public class GameRecord {
    private int record_id; //게임 번호
    private int mno; // 유저의 번호
    private int win; // 승
    private int lose;// 패
    private int draw;// 무승부
    private double odds;//승률
    private Timestamp play_date;//마지막으로 플레이한 시간
    
    public GameRecord toGameRecord() {
        return GameRecord.builder()
                .record_id(record_id)
                .mno(mno)
                .win(win)
                .lose(lose)
                .draw(draw)
                .odds(odds)
                .play_date(play_date)
                .build();
    }
}
