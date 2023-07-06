package dao;

import vo.User;

public interface UserDao {
	public int save(User user) throws Exception;
}
