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
    private int record_id;
    private int mno;
    private int win;
    private int lose;
    private int draw;
    private Timestamp play_date;
    
    public GameRecord toGameRecord() {
        return GameRecord.builder()
                .record_id(record_id)
                .mno(mno)
                .win(win)
                .lose(lose)
                .draw(draw)
                .play_date(play_date)
                .build();
    }
}
