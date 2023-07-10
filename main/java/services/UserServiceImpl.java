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
		 System.out.println("createUser() 메소드가 호출되었습니다. 이름: " + user);
		return userDao.save(user.toUser()) > 0;
	}

	@Override
	public boolean idCheckUser(String id) throws Exception {
		System.out.println("idCheckUser() 메소드가 호출되었습니다. 아이디: " + id);
		return userDao.countById(id) > 0;
	}

	@Override
	public boolean nameCheckUser(String name) throws Exception {
		 System.out.println("nameCheckUser() 메소드가 호출되었습니다. 이름: " + name);
		return userDao.countByName(name) > 0;
	}

	@Override
	public int loginCheck(String id, String pw) throws Exception {
		return userDao.loginCheck(id, pw);
	}

}
