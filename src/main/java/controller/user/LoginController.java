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
			// �𵨿� �α��� ó���� ����
			UserManager manager = UserManager.getInstance();
			manager.login(userId, password);
			
			User user = userDAO.findUser(userId);
			if(user == null) {
				throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다. ");
			}
			if(!user.matchPassword(password)) {
				throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
			}
	
			//세션에 userid 저장 
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
            
            return "redirect:/user/list";			
		} catch (Exception e) {
			/* UserNotFoundException�̳� PasswordMismatchException �߻� ��
			 * �ٽ� login form�� ����ڿ��� �����ϰ� ���� �޼����� ���
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/user/loginForm.jsp";			
		}	
    }
}
