package dao;

import java.util.List;

import vo.Reply;

public interface ReplyDao {
	public int deleteReply(int rno);
	public int registReply(Reply reply);
	public List<Reply> getReplyList(int bno);
}
