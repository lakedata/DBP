package controller.policy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Policy;
import model.service.PolicyManager;

public class ListPolicyController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PolicyManager polMan = PolicyManager.getInstance();
		List<Policy> polList = polMan.findPolicyList();
		
		request.setAttribute("polList", polList);
		return "/policy/policyList";
		
	}

}
