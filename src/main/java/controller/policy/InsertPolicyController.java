package controller.policy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Policy;
import model.service.PolicyManager;

public class InsertPolicyController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {	
    		
			return "/policy/policyRegisterForm.jsp";   //검색한 사용자 정보를 update form으로 전송 
	    }	
		
		
		Policy pol = new Policy (
				0, // sequence로 id 지정
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
			
//			return "redirection:/policy/view"; // redirection
			return "redirection:/policy/policySearch"; // redirection
			
		} catch (Exception e) {  // forwarding
			request.setAttribute("insertFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("pol", pol);
			
			return "/policy/policyRegister.jsp";
		}
		
	}

}
