package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.User;
import model.dao.UserDAO;
import model.service.PasswordMismatchException;
import model.service.UserManager;
import model.service.UserNotFoundException;

public class LoginController implements Controller {
	
	private UserDAO userDAO = new UserDAO();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		try {
			// �뜝�룣�뜽�슱�삕 �뜝�떥源띿삕�뜝�룞�삕 泥섇뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
			UserManager manager = UserManager.getInstance();
			manager.login(userId, password);
			
			User user = userDAO.findUser(userId);
			if(user == null) {
				throw new UserNotFoundException(userId + "user not found");
			}
			if(!user.matchPassword(password)) {
				throw new PasswordMismatchException("password mismatch");
			}
	
			// save user ID in session
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
            
            return "redirect:/user/list";			
		} catch (Exception e) {
			/* 
			 	UserNotFoundException or PasswordMismatchException 
			 	send login form to user, print error message			 
			 */
			
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/user/loginForm.jsp";			
		}	
    }
}
