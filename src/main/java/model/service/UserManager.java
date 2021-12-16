package model.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.LoginController;
import model.User;
import model.dao.PostDAO;
import model.dao.ScrapDAO;
import model.dao.UserDAO;

public class UserManager {
	private static UserManager userMan = new UserManager();
	private UserDAO userDAO;
	private static final Logger logger = LoggerFactory.getLogger(UserManager.class);

	private UserManager() {
		try {
			userDAO = new UserDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static UserManager getInstance() {
		return userMan;
	}

	public int create(User user) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(user.getUserId()) == true) {
			throw new ExistingUserException(user.getUserId() + "님은 이미 존재합니다.");
		}
		return userDAO.create(user);
	}

	public int update(User user) throws SQLException, UserNotFoundException {
		return userDAO.update(user);
	}

	public int remove(String userId) throws SQLException, UserNotFoundException { // 회원탈퇴 +POST/SCRAP
		PostDAO.deleteUserAllPost(userId);
		ScrapDAO.deleteUserAllScrap(userId);

		return userDAO.remove(userId);
	}

	public User findUser(String userId) throws SQLException, UserNotFoundException {
		User user = userDAO.findUser(userId);

		if (user == null) {
			throw new UserNotFoundException(userId + "님을 찾을 수 없습니다.");
		}
		return user;
	}

	public boolean login(String userId, String password)
			throws SQLException, UserNotFoundException, PasswordMismatchException {

		logger.debug("Usermanager login");

		User user = findUser(userId);

		logger.debug("Usermanager findUser " + user);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");

		}

		logger.debug("Usermanager return ");

		return true;
	}

	public UserDAO getUserDAO() {
		return this.userDAO;
	}
}
