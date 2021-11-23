package controller.policy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Policy;
import model.service.PolicyManager;

public class SearchPolicyController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PolicyManager polMan = PolicyManager.getInstance();
		
		String category = request.getParameter("category");
		int income = Integer.parseInt(request.getParameter("income"));
		String local = request.getParameter("local");
		int age = Integer.parseInt(request.getParameter("age"));
		List<Policy> searchPolList = polMan.searchPolicyList(category, income, local, age);
		
		/*
    	String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}		
    	*/
		
		// List<User> userList = manager.findUserList(currentPage, countPerPage);
		
		request.setAttribute("searchPolList", searchPolList);
		return "/policy/policyList.jsp";
	}

}
