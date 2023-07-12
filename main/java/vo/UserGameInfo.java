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
    private int user_id;
    private String user_name;
    private String email;
    private Timestamp registration_date;
    private int total_games;
    private int wins;
    private int losses;
    private int draws;
    private double winning_percentage;
    private int winning_streak;
    private int losing_streak;

    public UserGameInfo toUserGameInfo() {
        return UserGameInfo.builder()
                .user_id(user_id)
                .user_name(user_name)
                .email(email)
                .registration_date(registration_date)
                .total_games(total_games)
                .wins(wins)
                .losses(losses)
                .draws(draws)
                .winning_percentage(winning_percentage)
                .winning_streak(winning_streak)
                .losing_streak(losing_streak)
                .build();
    }
}
