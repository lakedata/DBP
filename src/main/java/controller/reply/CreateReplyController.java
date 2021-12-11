package controller.reply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.LoginController;
import model.Reply;
import model.service.ReplyManager;

public class CreateReplyController implements Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(CreateReplyController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.debug("in CreateReplyController");
		
		int postNum = Integer.parseInt(request.getParameter("postNum"));
		
		Reply re = new Reply (
				postNum,
				request.getParameter("agree"),
				request.getParameter("replyContent"),
				0,
				request.getParameter("disagree")); //replyNum => sequence
		
		logger.debug("Reply: " +re);
		
		try {
			ReplyManager reMan = ReplyManager.getInstance();
			reMan.create(re);		
			
			logger.debug("reMan create");
			
			request.setAttribute("postNum", postNum);
			
//			return "/post/postDetail.jsp";
//			return "redirect:/post/view?postNum=" + postNum;
			return "redirect:/post/view?postNum=" +postNum;
//			return "post/postReply.jsp";
//			return "post/postList.jsp";
			
		} catch (Exception e) {
			request.setAttribute("createReplyFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("reply", re);
			
			return "redirect:/post/view?postNum=" + postNum;

		}
		
	}

}
