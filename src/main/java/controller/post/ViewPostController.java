package controller.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Post;
import model.Reply;
import model.service.PostManager;
import model.service.ReplyManager;

public class ViewPostController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
//		if (request.getMethod().equals("GET")) {	
//
//			return "/post/postDetail.jsp";  
//	    }
		
		
		PostManager postMan = PostManager.getInstance();
		int postNum = Integer.parseInt(request.getParameter("postNum"));
		
		Post post = null;
		
		post = postMan.findPost(postNum);
		
		ReplyManager reMan = ReplyManager.getInstance();
		List<Reply> replyList = reMan.findReplyList(postNum);
		request.setAttribute("replyList", replyList);
		
		request.setAttribute("post", post);
		return "/post/postDetail.jsp";
		
	}
	

}
