package services;

import config.ServletContextConfig;
import dao.UserDao;
import vo.User;

public class UserServiceImpl implements UserService{
	private final UserDao userDao;
	
	public UserServiceImpl() {
		userDao = ServletContextConfig.getInstance().getUserDao();
	}
	
	@Override
	public boolean createUser(User user) throws Exception {
		return userDao.save(user.toUser()) > 0;
	}

}
