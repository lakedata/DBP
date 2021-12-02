package controller.scrap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.ScrapManager;

public class CancelScrapController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		
		try {
			ScrapManager scMan = ScrapManager.getInstance();
			HttpSession session = request.getSession();	
			
			scMan.cancel(userId);
			
			return "redirection:/policy/view"; //mypage?
			
		} catch (Exception e) {
			request.setAttribute("cancelFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("scrapCancelUserId", userId);
			
			return "/policy/policyDetail.jsp";
		}
	}

}
