package controller.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PostManager;

public class DeletePostController  implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int deletePostId = Integer.parseInt(request.getParameter("postId"));
		
		try {
			PostManager postMan = PostManager.getInstance();
			
			postMan.delete(deletePostId);
			
			return "redirection:/post/postList";
		} catch (Exception e) {
			request.setAttribute("deleteFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("deletePolId", deletePostId);
			
			return "/post/postDetail.jsp";
		}
	}

}
