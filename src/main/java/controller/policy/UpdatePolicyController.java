package controller.policy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Policy;
import model.service.PolicyManager;

public class UpdatePolicyController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PolicyManager polMan = PolicyManager.getInstance();
		
		if(request.getMethod().equals("GET")) {
			int policyId = Integer.parseInt(request.getParameter("policyId"));
			
			Policy pol = polMan.findPolicy(policyId);
			
			//admin
			HttpSession session = request.getSession();
			if(UserSessionUtils.isLoginUser("dbpro0102", session)) {
				request.setAttribute("policy", pol);
				
				return "/policy/policyUpdateForm.jsp";
				
			}
			else { // 관리자 아닐 경우
				request.setAttribute("updateFailed", true);
				request.setAttribute("exception", new IllegalStateException("관리자 외 일반 사용자는 정책 수정이 불가능 합니다."));
				return "/policy/list";
			}
		}
		else { // "POST" -> 입력(수정)한 정책 정보 parameter로 전송
			Policy updatePol = new Policy(
					Integer.parseInt(request.getParameter("policyId")),
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
			//test
			//System.out.println(request.getParameter("name"));
			polMan.update(updatePol);
			return "redirect:/policy/list";
			
	
		}
		
	}

}
