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
			int postId = Integer.parseInt(request.getParameter("postNum"));
			
			Post post = postMan.findPost(postId);
			
			HttpSession session = request.getSession();
			if(UserSessionUtils.isLoginUser(request.getParameter("userId"), session)) {
				request.setAttribute("post", post);
				
				return "/post/postUpdateForm.jsp";
			}
			else {
				request.setAttribute("updateFailed", true);
				request.setAttribute("exception", new IllegalStateException("사용자 본인이 작성한 글만 수정 가능합니다."));
				return "/post/list";
			}
		}
		else {
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
