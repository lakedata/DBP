package controller.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Comment;
import model.service.CommentManager;

public class createCommentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Comment comm = new Comment (
				Integer.parseInt(request.getParameter("postNum")),
				(request.getParameter("agree")).charAt(0),
				request.getParameter("policyId")
				);
		
		try {
			CommentManager commMan = CommentManager.getInstance();
			commMan.create(comm);		
			
			return "redirection:/policy/view";
			
		} catch (Exception e) {
			request.setAttribute("createCommentFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("comm", comm);
			
			return "/policy/view";
		}
		
	}

}
