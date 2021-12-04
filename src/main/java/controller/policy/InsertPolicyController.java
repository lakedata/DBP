package controller.policy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.RegisterUserController;
import model.Policy;
import model.service.PolicyManager;

public class InsertPolicyController implements Controller {
	  private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);
	    
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		log.debug("in InsertPolicyController");
		
		if (request.getMethod().equals("GET")) {	
			log.debug("RegisterForm Request");
			return "/policy/policyRegisterForm.jsp";   
	    }	
		
		log.debug("in InsertPolicyController: GET");
		
		Policy pol = new Policy (
				0, // sequence로 policyId 지정
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
		
		log.debug("pol in InsertPolicyController: " +pol);
		
		try {
			PolicyManager polMan = PolicyManager.getInstance();
			polMan.insert(pol);
			
			log.debug("find insert" +polMan);
	
			return "redirection:/policy/view"; //성공 시 
//			return "redirection:/policy/list"; // redirection -성공시 
//			return "redirection:/policy/policySearch"; // redirection

			
		} catch (Exception e) {  // 예외 발생 시 forwarding
			request.setAttribute("insertFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("pol", pol);
			
			return "/policy/policyRegister.jsp";
		}
		
	}

}
