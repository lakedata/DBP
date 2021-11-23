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
			
			
			HttpSession session = request.getSession();
			if(UserSessionUtils.isLoginUser("admin", session)) { // �������� ��쿡�� ��å ���� ����
				request.setAttribute("policy", pol);
				
				return "/policy/updatePolicyForm.jsp"; // ã�� ��å ���� updatePolicyForm���� ����
				
			}
			else { // ������ �ƴ� ���
				request.setAttribute("updateFailed", true);
				request.setAttribute("exception", new IllegalStateException("������ �� �Ϲ� ����ڴ� ��å ������ �Ұ��� �մϴ�."));
				return "���� ������ uri";
			}
		}
		else { // "POST" -> �Է�(����)�� ��å ���� parameter�� ����
			Policy updatePol = new Policy(
					Integer.parseInt(request.getParameter("policyId")),
					request.getParameter("name"),
					request.getParameter("contents"),
					request.getParameter("category"),
					request.getParameter("period"),
					request.getParameter("policySummary"),
					request.getParameter("qualificationForApplication"),
					request.getParameter("howToApply"),
					request.getParameter("local"),
					Integer.parseInt(request.getParameter("age")),
					Integer.parseInt(request.getParameter("income"))
					
					);
			
		polMan.update(updatePol);
		return "redirect:/policy/policyList";
	
		}
		
	}

}
