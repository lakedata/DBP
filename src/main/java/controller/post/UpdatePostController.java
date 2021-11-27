package controller.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Post;
import model.service.PostManager;

public class UpdatePostController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		PostManager postMan = PostManager.getInstance();
		
		if(request.getMethod().equals("GET")) {
			int postId = Integer.parseInt(request.getParameter("postId"));
			
			Post post = postMan.findPost(postId);
		}
	}

}
