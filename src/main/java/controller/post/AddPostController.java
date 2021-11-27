package controller.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Post;
import model.service.PostManager;

public class AddPostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Post post = new Post(
				0,
				Integer.parseInt(request.getParameter("policyId")),
				request.getParameter("userId"),
				request.getParameter("title"),
				request.getParameter("writeDate"),
				request.getParameter("content"));
		try {
			PostManager postMan = PostManager.getInstance();
			postMan.insert(post);
			
			return "redirection:/post/view";
		} catch (Exception e) {
			request.setAttribute("insertFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("post", post);
			
			return "/post/addPostForm.jsp";
		}

	}

}
