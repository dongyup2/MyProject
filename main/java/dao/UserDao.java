package dao;

import vo.User;
import vo.UserGameInfo;

public interface UserDao {
	public int save(User user) throws Exception;
	public int countById(String id) throws Exception;
	public int countByName(String name) throws Exception;
	public UserGameInfo loginCheck(String id, String pw) throws Exception;
}
