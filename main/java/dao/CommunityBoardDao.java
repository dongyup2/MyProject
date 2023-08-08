package dao;

import java.util.List;

import vo.Board;

public interface CommunityBoardDao {
	public int insertBoard(Board board);
	public List<Board> selectBoard();
	public Board selectOneBoard(int bno);
	public int updateBoard(Board board);
	public int deleteBoard(int bno);
}
