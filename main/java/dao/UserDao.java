package dao;

import vo.User;
import vo.UserGameInfo;

public interface UserDao {
	public int save(User user) throws Exception;
	public int countById(String id) throws Exception;
	public int countByName(String name) throws Exception;
	public User loginCheck(String id, String pw) throws Exception;
	public int getMnoByIdAndPassword(String id, String pw) throws Exception;
	public UserGameInfo getUserGameInfo(int mno) throws Exception;
	
}
