package controller.reply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Reply;
import model.service.ReplyManager;

public class createReplyController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Reply re = new Reply (
				Integer.parseInt(request.getParameter("postNum")),
				(request.getParameter("agree")).charAt(0),
				request.getParameter("replyContent")
				);
		
		
		try {
			ReplyManager reMan = ReplyManager.getInstance();
			reMan.create(re);		
			
			
			return "redirection:/policy/view";
			//return "post/postReply.jsp";
			//return "post/postList.jsp";
			
		} catch (Exception e) {
			request.setAttribute("createReplyFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("reply", re);
			
			return "/policy/view";

		}
		
	}

}
