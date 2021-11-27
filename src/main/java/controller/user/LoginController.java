rpackage controller.user;

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
			// 占쏜델울옙 占싸깍옙占쏙옙 처占쏙옙占쏙옙 占쏙옙占쏙옙
			UserManager manager = UserManager.getInstance();
			manager.login(userId, password);
			
			User user = userDAO.findUser(userId);
			if(user == null) {
				throw new UserNotFoundException(userId + "�뒗 議댁옱�븯吏� �븡�뒗 �븘�씠�뵒�엯�땲�떎. ");
			}
			if(!user.matchPassword(password)) {
				throw new PasswordMismatchException("鍮꾨�踰덊샇媛� �씪移섑븯吏� �븡�뒿�땲�떎.");
			}
	
			//�꽭�뀡�뿉 userid ���옣 
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
            
            return "redirect:/user/list";			
		} catch (Exception e) {
			/* UserNotFoundException占싱놂옙 PasswordMismatchException 占쌩삼옙 占쏙옙
			 * 占쌕쏙옙 login form占쏙옙 占쏙옙占쏙옙悶占쏙옙占� 占쏙옙占쏙옙占싹곤옙 占쏙옙占쏙옙 占쌨쇽옙占쏙옙占쏙옙 占쏙옙占�
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/user/loginForm.jsp";			
		}	
    }
}
