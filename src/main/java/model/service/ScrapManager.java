package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Scrap;
import model.dao.PolicyDAO;
import model.dao.ScrapDAO;

public class ScrapManager {
	
	private static ScrapManager scMan = new ScrapManager();
	private ScrapDAO scrapDAO;
	
	private ScrapManager() {
		try {
			scrapDAO = new ScrapDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ScrapManager getInstance() {
		return scMan;
	}

	// Ω∫≈©∑¶
	public List<Scrap> add(Scrap sc) throws SQLException {
		return scrapDAO.addScrap(sc.getUserId(), sc.getPolicyId());
	}
	
	// Ω∫≈©∑¶ √Îº“
	public int cancel(String userId) throws SQLException {
		return scrapDAO.cancelScrap(userId);
	}
}
