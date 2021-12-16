package model.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Agree;
import model.dao.AgreeDAO;


public class AgreeManager {
private static final Logger logger = LoggerFactory.getLogger(AgreeManager.class);
	
	
	private static AgreeManager aMan = new AgreeManager();
	private AgreeDAO agreeDAO;
	
	private AgreeManager() {
		try {
			agreeDAO = new AgreeDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Agree create(int postNum) {
		return agreeDAO.createAgree(postNum);
	}
	public static AgreeManager getInstance() {
		return aMan;
	}
	
	public int addAgree(int postNum) throws SQLException {
		
		
		return agreeDAO.addAgree(postNum);
	}
	
	public int addDisAgree(int postNum) throws SQLException {
		
		
		return agreeDAO.addDisagree(postNum);
	}
	public Agree findAgree(int postNum) throws SQLException{
		Agree agree = agreeDAO.findAgree(postNum);
		return agree;
	}
}
