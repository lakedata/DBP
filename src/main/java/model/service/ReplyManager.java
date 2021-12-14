package model.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Reply;
import model.dao.ReplyDAO;
import model.dao.PolicyDAO;

public class ReplyManager {
	
	private static final Logger logger = LoggerFactory.getLogger(ReplyManager.class);
	
	
	private static ReplyManager reMan = new ReplyManager();
	private ReplyDAO replyDAO;
	
	private ReplyManager() {
		try {
			replyDAO = new ReplyDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ReplyManager getInstance() {
		return reMan;
	}
	
	public Reply create(Reply re) throws SQLException {
		
		logger.debug("in ReplyManager: " +re);
		
		return replyDAO.createReply(re);
		
	}
	
	public int delete (int replyNum) throws SQLException {
		return replyDAO.deleteReply(replyNum);
	}
	
	public List<Reply> findReplyList(int postNum) throws SQLException {
		
		logger.debug("in ReplyManager, findReplyList " );
		
		return replyDAO.findReplyList(postNum);
	}
}
