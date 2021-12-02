package controller.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Post;
import model.service.PostManager;


public class ListPostController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		

		if(request.getMethod().equals("GET")) {
		
			return "/post/postList.jsp";
		}
		
		PostManager postMan = PostManager.getInstance();
		List<Post> postList = postMan.findPostList();
		
		request.setAttribute("postList", postList);
		return "/post/list";
	}

}
