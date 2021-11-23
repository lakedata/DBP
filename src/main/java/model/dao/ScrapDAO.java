package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Policy;
import model.Scrap;

public class ScrapDAO {

	private JDBCUtil jdbcUtil = null;
	
	public ScrapDAO() {			
		jdbcUtil = new JDBCUtil();	
	}
	
	/* ½ºÅ©·¦ÇÑ Á¤Ã¥ ¸®½ºÆ®¿¡ Ãß°¡ */
	public List<Scrap> addScrap(String userId, int policyId) {
		
		String sql = "INSERT INTO Scrap VALUES (?, ?) ";
				
		
		Object[] param = new Object[] {userId, policyId};
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ï¿½ï¿½ï¿½ï¿½			
			List<Scrap> scrapList = new ArrayList<Scrap>();	// Policyï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½Æ® ï¿½ï¿½ï¿½ï¿½
			
			while (rs.next()) {
				Scrap scrap = new Scrap (			// Policy ï¿½ï¿½Ã¼ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Ï¿ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
						rs.getString("userId"),
						rs.getInt("policyId")
						);
				scrapList.add(scrap);				// Listï¿½ï¿½ Policy ï¿½ï¿½Ã¼ ï¿½ï¿½ï¿½ï¿½
			}		
			return scrapList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ï¿½ï¿½È¯
		}
		return null;
	}
	
	public int cancelScrap(String userId) throws SQLException {
		
		String sql = "DELETE FROM Scrap "
				   + "WHERE userId=?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}

	
}
