package controller.policy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.PolicyManager;

public class DeletePolicyController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int deletePolId = Integer.parseInt(request.getParameter("policyId"));
		
		try {
			
			PolicyManager polMan = PolicyManager.getInstance();
			HttpSession session = request.getSession();	
			
			polMan.delete(deletePolId);
			
			return "redirection:/policy/policyList";
			
		} catch (Exception e) {
			request.setAttribute("deleteFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("deletePolId", deletePolId);
			
			return "/policy/policyDetail.jsp";
		}
	}

}
