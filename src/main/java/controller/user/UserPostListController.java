package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.DispatcherServlet;
import model.Post;
import model.service.PostManager;


public class UserPostListController implements Controller{
	 private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("userpostlist controller");
		
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		
		String userId = (String) session.getAttribute("userId");
    	
    	
	      PostManager postMan = PostManager.getInstance();
	      List<Post> postList = null;
	      postList = postMan.findMyPostList(userId);
	      
//	  	  if (order.equals("my_post")) { 
//	  		logger.debug("my_post");
//	  		  postList = postMan.findMyPostList(userId);
//	  	  }
	      request.setAttribute("postList", postList);
	      
	      return "/user/myPost.jsp";

	}

}
