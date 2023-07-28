package config;

import dao.GamePageDao;
import dao.GamePageDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import lombok.Getter;
import services.GamePageService;
import services.GamePageServiceImpl;
import services.UserService;
import services.UserServiceImpl;

@Getter
public class ServletContextConfig {
	private static ServletContextConfig instance = null;
	/*
	 * Custom IoC (객체관리)
	 */
	//Repository
	private UserDao userDao;
	//Service
	private UserService userService;
	
	private GamePageDao gamePageDao;
	
	private GamePageService gamePageService;
	
	private ServletContextConfig() {}
	
	public static ServletContextConfig getInstance() {
		if(instance == null) {
			instance = new ServletContextConfig();
			instance.setIoC();
		}
		
		return instance;
	}
	
	private void setIoC() {
		if(userDao == null) {
			userDao = new UserDaoImpl();
		}
		if(userService == null) {
			userService = new UserServiceImpl();
		}
		if(gamePageDao == null) {
			gamePageDao = new GamePageDaoImpl();
		}
		if(gamePageService == null) {
			gamePageService = new GamePageServiceImpl();
		}
	}
	
}
