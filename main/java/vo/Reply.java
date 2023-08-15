package vo;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class Reply {
	private int rno;
	private String comment;
	private String writer;
	private Date regdate;
	private int bno;
}
