package model.service;

import java.sql.SQLException;

import model.Comment;
import model.dao.CommentDAO;
import model.dao.PolicyDAO;

public class CommentManager {
	
	private static CommentManager commMan = new CommentManager();
	private CommentDAO commDAO;
	
	private CommentManager() {
		try {
			commDAO = new CommentDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static CommentManager getInstance() {
		return commMan;
	}
	
	public Comment create(Comment comm) throws SQLException {
		return commDAO.createComment(comm);
		
	}

}
