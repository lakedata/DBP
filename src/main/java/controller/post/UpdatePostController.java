package controller.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.DispatcherServlet;
import controller.user.UserSessionUtils;
import model.Post;
import model.service.PostManager;

public class UpdatePostController implements Controller{
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("update controller");
		PostManager postMan = PostManager.getInstance();
		
		if(request.getMethod().equals("GET")) {
			logger.debug("update controller _ get");
			int postId = Integer.parseInt(request.getParameter("postNum"));
			
			Post post = postMan.findPost(postId);
			
			HttpSession session = request.getSession();
			
			request.setAttribute("post", post);
				
			return "/post/postUpdateForm.jsp";
			
//			else {
//				request.setAttribute("updateFailed", true);
//				request.setAttribute("exception", new IllegalStateException("사용자 본인이 작성한 글만 수정 가능합니다."));
//				return "redirect:/post/list";
//			}
		}
		else {
			logger.debug("update controller _ put");
			System.out.println(request.getParameter("postNum"));
			Post updatePost = new Post(
					Integer.parseInt(request.getParameter("postNum")),
					Integer.parseInt(request.getParameter("policyId")),
					request.getParameter("userId"),
					request.getParameter("title"),
					request.getParameter("writeDate"),
					request.getParameter("content"));
			postMan.update(updatePost);
			return "redirect:/post/list";
			
		}
	}
	

}
