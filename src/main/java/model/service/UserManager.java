package model.service;

import java.sql.SQLException;

import model.User;
import model.dao.UserDAO;

public class UserManager {
	private static UserManager userMan = new UserManager();
	private UserDAO userDAO;

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
			throw new ExistingUserException(user.getUserId() + "존재하지 않는 사용자입니다.");
		}
		return userDAO.create(user);
	}

	public int update(User user) throws SQLException, UserNotFoundException {
		return userDAO.update(user);
	}	

	public int remove(String userId) throws SQLException, UserNotFoundException {
		return userDAO.remove(userId);
	}

	public User findUser(String userId)
		throws SQLException, UserNotFoundException {
		User user = userDAO.findUser(userId);
		
		if (user == null) {
			throw new UserNotFoundException(userId + "�뒗 議댁옱�븯吏� �븡�뒗 �븘�씠�뵒�엯�땲�떎.");
		}		
		return user;
	}

	public boolean login(String userId, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		User user = findUser(userId);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("鍮꾨�踰덊샇媛� �씪移섑븯吏� �븡�뒿�땲�떎.");
		}
		return true;
	}

	public UserDAO getUserDAO() {
		return this.userDAO;
	}
}
