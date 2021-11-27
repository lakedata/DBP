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
				throw new UserNotFoundException(userId + "占쎈뮉 鈺곕똻�삺占쎈릭筌욑옙 占쎈륫占쎈뮉 占쎈툡占쎌뵠占쎈탵占쎌뿯占쎈빍占쎈뼄. ");
			}
			if(!user.matchPassword(password)) {
				throw new PasswordMismatchException("�뜮袁⑨옙甕곕뜇�깈揶쏉옙 占쎌뵬燁살꼹釉�筌욑옙 占쎈륫占쎈뮸占쎈빍占쎈뼄.");
			}
	
			//占쎄쉭占쎈�∽옙肉� userid 占쏙옙占쎌삢 
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
            
            return "redirect:/user/list";			
		} catch (Exception e) {
			/* UserNotFoundException�뜝�떛�냲�삕 PasswordMismatchException �뜝�뙥�궪�삕 �뜝�룞�삕
			 * �뜝�뙐�룞�삕 login form�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�궣�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�떦怨ㅼ삕 �뜝�룞�삕�뜝�룞�삕 �뜝�뙣�눦�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝占�
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/user/loginForm.jsp";			
		}	
    }
}
