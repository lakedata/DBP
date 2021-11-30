package controller.policy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Policy;
import model.service.PolicyManager;

public class ViewPolicyController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Policy pol = null;
		
		PolicyManager polMan = PolicyManager.getInstance();
		int policyId = Integer.parseInt(request.getParameter("policyId"));
		pol = polMan.findPolicy(policyId);
		
		request.setAttribute("Policy", pol);
		return "/policy/PolicyDetail.jsp";
	}

}
