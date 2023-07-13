package services;

import vo.User; 
import vo.UserGameInfo;

public interface UserService{
	public boolean createUser(User user) throws Exception;
	public boolean idCheckUser(String id) throws Exception;
	public boolean nameCheckUser(String name) throws Exception;
	public User loginCheck(String id, String pw) throws Exception;
	public UserGameInfo getUserGameInfo(String id, String pw) throws Exception; 
}
