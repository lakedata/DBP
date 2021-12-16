package controller.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Agree;
import model.Post;
import model.Reply;
import model.service.AgreeManager;
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
		ReplyManager reMan = ReplyManager.getInstance();
		AgreeManager aMan = AgreeManager.getInstance();
		
		
		
		
		int postNum = Integer.parseInt(request.getParameter("postNum"));
		
		Post post = null;
		Agree agree = null;
		post = postMan.findPost(postNum);
		agree = aMan.findAgree(postNum);
//		agree = aMan.create(postNum);
		List<Reply> replyList = reMan.findReplyList(postNum);
		
		request.setAttribute("replyList", replyList);
		request.setAttribute("post", post);
		request.setAttribute("agree", agree);
		
		return "/post/postDetail.jsp";
		
	}
	

}
