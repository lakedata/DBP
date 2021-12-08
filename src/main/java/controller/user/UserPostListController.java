package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Post;
import model.service.PostManager;


public class UserPostListController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String order = (request.getParameter("order") == null ? "orderOfId" : request.getParameter("order"));
		String userId = request.getParameter("userId");
    	
    	
	      PostManager postMan = PostManager.getInstance();
	      List<Post> postList = null;
	      postList = postMan.findPostList();
	      
	  	  if (order.equals("my_post")) { 
	  		  postList = postMan.findMyPostList(userId);
	  	  }
	      request.setAttribute("postList", postList);
	      
	      return "/user/myPost.jsp";

	}

}
