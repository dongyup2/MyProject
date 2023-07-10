package services;

import vo.User;

public interface UserService{
	public boolean createUser(User user) throws Exception;
	public boolean idCheckUser(String id) throws Exception;
	public boolean nameCheckUser(String name) throws Exception;
	public int loginCheck(String id, String pw) throws Exception;
}
