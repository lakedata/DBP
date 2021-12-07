package controller.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import controller.Controller;
import controller.user.RegisterUserController;
import model.Post;
import model.service.PostManager;

public class AddPostController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		log.debug("in AddPostController");
		
		String userId = request.getParameter("userId");
		
		
//		if (request.getMethod().equals("POST")) {
//	
//			return "/post/postWrite.jsp";
//		}
//		
		//test
		log.debug(request.getParameter("title"));
		int policyId = Integer.parseInt(request.getParameter("policyId"));
		
		System.out.println("policyId:" + policyId);
		
		String uId = request.getParameter("userId");
		System.out.println("uId:" + uId);
		
		String title = request.getParameter("title");
		System.out.println("title:" + title);
		
		String writeDate = request.getParameter("writeDate");
		System.out.println("writeDate:" + writeDate);
		
		String content = request.getParameter("content");
		System.out.println("content:" + content);
		
		
		Post post = new Post(
				0,
				Integer.parseInt(request.getParameter("policyId")),
				request.getParameter("userId"),
				request.getParameter("title"),
				request.getParameter("writeDate"),
				request.getParameter("content"));
		try {
			PostManager postMan = PostManager.getInstance();
			postMan.insert(post);
			
			return "redirect:/post/list";
//			return "redirection:/post/list";

			
		} catch (Exception e) {
			request.setAttribute("insertFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("post", post);
			System.out.print(e); //юс╫ц
			return "/post/postWrite.jsp"; 
		}

	}

}
