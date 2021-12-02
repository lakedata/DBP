package controller.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Post;
import model.service.PostManager;

public class ViewPostController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		if (request.getMethod().equals("GET")) {	

			return "/post/postDetail.jsp";  
	    }
		
		
		PostManager postMan = PostManager.getInstance();
		int postNum = Integer.parseInt(request.getParameter("postNum"));
		
		Post post = null;
		
		post = postMan.findPost(postNum);
		
		request.setAttribute("post", post);
		return "/post/postDetail.jsp";
		
	}
	

}
