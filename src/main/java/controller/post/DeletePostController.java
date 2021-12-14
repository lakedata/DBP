package controller.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PostManager;

public class DeletePostController  implements Controller {
	//a

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int deletePostId = Integer.parseInt(request.getParameter("postNum"));
		
		try {
			PostManager postMan = PostManager.getInstance();
			
			postMan.delete(deletePostId);
			
			return "redirect:/post/list";
		} catch (Exception e) {
			request.setAttribute("deleteFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("deletePolId", deletePostId);
			
			return "/post/postDetail.jsp";
		}
	}

}
