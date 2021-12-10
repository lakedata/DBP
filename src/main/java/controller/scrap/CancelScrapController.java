package controller.scrap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.ScrapManager;

public class CancelScrapController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int policyId = Integer.parseInt(request.getParameter("policyId"));
		HttpSession session = request.getSession();	
		String userId = (String) session.getAttribute("userId");
		
		try {
			ScrapManager scMan = ScrapManager.getInstance();
			
			scMan.cancel(userId);
			
			//return "redirection:/policy/view"; //mypage?
			return "redirect:/policy/view?policyId=" + policyId;
			
		} catch (Exception e) {
			request.setAttribute("cancelFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("scrapCancelUserId", userId);
			
			//return "/policy/policyDetail.jsp";
			return "redirect:/policy/view?policyId=" + policyId;
		}
	}

}
