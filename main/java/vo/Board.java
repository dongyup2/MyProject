package vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {
	private int bno;
	private String title;
	private String writer;
	private Timestamp createDate;
	private String content;
	private int views;
}
