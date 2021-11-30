package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.DispatcherServlet;
import model.User;
import model.dao.UserDAO;
import model.service.PasswordMismatchException;
import model.service.UserManager;
import model.service.UserNotFoundException;

public class LoginController implements Controller {
	
	private UserDAO userDAO = new UserDAO();
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	logger.debug("in LoginController1");
    	
    	String userId = request.getParameter("id");
		String password = request.getParameter("pw");
		
		logger.debug("in LoginController1" +userId+ "," +password);
		
		if(request.getMethod().equals("GET")) {
			return "/user/loginForm.jsp";
		}
		
		
		try {
			UserManager manager = UserManager.getInstance();
			manager.login(userId, password);
			
//			User user = userDAO.findUser(userId);
//			if(user == null) {
//				throw new UserNotFoundException(userId + "user not found");
//			}
//			if(!user.matchPassword(password)) {
//				throw new PasswordMismatchException("password mismatch");
//			}
//	
			// save user ID in session
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
            
            return "redirect:/";			
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
