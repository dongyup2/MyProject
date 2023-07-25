package services;

import config.ServletContextConfig; 
import dao.UserDao;
import vo.User;
import vo.UserGameInfo;

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
	public User loginCheck(String id, String pw) throws Exception {
		User user = userDao.loginCheck(id, pw);
		if(user == null) {
			return null;
		}else {
			System.out.println("아이디: " + id + "비밀번호: " + pw);
			return user.getPw().equals(pw) ? user : null;
		}
		
	}

	@Override
	public UserGameInfo getUserGameInfo(String id, String pw) throws Exception {
		 int mno = userDao.getMnoByIdAndPassword(id, pw);
	        if (mno != -1) {
	        	System.out.println("해당되는 유저를 가져옵니다.");
	            return userDao.getUserGameInfo(mno);
	        } else {
	        	System.err.println("해당되는 정보의 유저가 없습니다!");
	            return null;
	        }
	}
	public UserGameInfo getUser1Info() throws Exception{
		return null;
		
	}

}
