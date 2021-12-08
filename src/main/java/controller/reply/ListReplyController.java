package controller.reply;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.LoginController;
import model.Reply;
import model.service.ReplyManager;

public class ListReplyController  implements Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("in ViewReplyController");
		
		ReplyManager reMan = ReplyManager.getInstance();
		List<Reply> replyList = reMan.findReplyList();
		
		request.setAttribute("replyList", replyList);
		return "/policy/poilcyDetail.jsp";
	}
	
	

}
