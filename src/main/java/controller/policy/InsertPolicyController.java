package controller.policy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Policy;
import model.service.PolicyManager;

public class InsertPolicyController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Policy pol = new Policy (
				0, // sequence·Î id ÁöÁ¤
				request.getParameter("name"),
				request.getParameter("contents"),
				request.getParameter("category"),
				request.getParameter("startDate"),
				request.getParameter("endDate"),
				request.getParameter("policySummary"),
				request.getParameter("qualificationForApplication"),
				request.getParameter("howToApply"),
				request.getParameter("local"),
				Integer.parseInt(request.getParameter("startAge")),
				Integer.parseInt(request.getParameter("endAge")),
				Integer.parseInt(request.getParameter("income"))
				);
		
		try {
			PolicyManager polMan = PolicyManager.getInstance();
			polMan.insert(pol);
			
			return "redirection:/policy/view"; // redirection
			
		} catch (Exception e) {  // forwarding
			request.setAttribute("insertFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("pol", pol);
			
			return "/policy/policyRegister.jsp";
		}
		
	}

}
