package controller.reply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.ReplyManager;

public class DeleteReplyController implements Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(DeleteReplyController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.debug("in DeleteReplyController");
		
		// TODO Auto-generated method stub
		int deleteReplyNum = Integer.parseInt(request.getParameter("replyNum"));
		int postNum = Integer.parseInt(request.getParameter("postNum"));
		
		logger.debug("in DeleteReplyController, postNum, replyNum: " +postNum+ " " +deleteReplyNum);
		
		try {
			ReplyManager reMan = ReplyManager.getInstance();

			reMan.delete(deleteReplyNum);
			
			return "redirect:/post/view?postNum=" + postNum;
			
			
		} catch (Exception e) {
			request.setAttribute("deleteFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("deletePolId", deleteReplyNum);
			
			return "redirect:/post/view?postNum=" + postNum;
		}
	}

}
