package controller.scrap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Scrap;
import model.service.ScrapManager;

public class AddScrapController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int policyId = Integer.parseInt(request.getParameter("policyId"));
		Scrap scrap = new Scrap(
				request.getParameter("userId"),
				Integer.parseInt(request.getParameter("policyId"))
				);
		
		try {
		
			ScrapManager scMan = ScrapManager.getInstance();
			scMan.add(scrap);
			return "redirect:/policy/view?policyId=" + policyId;
			//return "/policy/policyDetail.jsp";
			
		} catch (Exception e) {
			request.setAttribute("scrapFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("scrap", scrap);
			return "redirect:/policy/view?policyId=" + policyId;
			//return "/policy/policyDetail.jsp";
		}
	}

}
