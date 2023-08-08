package services;

import java.util.List;
import vo.Board;

public interface CommunityBoardService {
    public boolean insertBoard(Board board);
    public List<Board> selectBoard();
    public Board selectOneBoard(int bno);
    public boolean updateBoard(Board board);
    public boolean deleteBoard(int bno);
}
