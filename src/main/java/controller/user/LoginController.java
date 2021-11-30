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
		
		if(request.getMethod().equals("GET")) {
			return "/user/loginForm.jsp";
		}
		
		
		try {
			// 占쎈쐻占쎈＃占쎈쑞占쎌뒻占쎌굲 占쎈쐻占쎈뼢繹먮씮�굲占쎈쐻占쎈짗占쎌굲 筌ｌ꼪�쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
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
