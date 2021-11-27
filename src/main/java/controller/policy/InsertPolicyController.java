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
				0, // id는 sequence로 자동 생성
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
		
		try {
			PolicyManager polMan = PolicyManager.getInstance();
			polMan.insert(pol);
			
			return "redirection:/policy/view"; // 정책 등록 성공 시 정책 리스트 화면으로 redirection
			
		} catch (Exception e) {  // 예외 발생 시 입력 form으로 forwarding
			request.setAttribute("insertFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("pol", pol);
			
			return "/policy/addPolicyForm.jsp";
		}
		
	}

}
