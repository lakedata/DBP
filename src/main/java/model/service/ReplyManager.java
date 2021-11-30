package model.service;

import java.sql.SQLException;

import model.Reply;
import model.dao.ReplyDAO;
import model.dao.PolicyDAO;

public class ReplyManager {
	
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
	
	public Reply create(Reply comm) throws SQLException {
		return replyDAO.createReply(comm);
		
	}

}
