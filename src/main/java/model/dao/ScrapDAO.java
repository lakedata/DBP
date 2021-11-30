<<<<<<< HEAD
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
	
	/* 정책 스크랩하기 */
	public Scrap addScrap(Scrap scrap) {
		int generatedKey1, generatedKey2;
		
		String sql = "INSERT INTO Scrap VALUES (?, ?) ";

		Object[] param = new Object[] {scrap.getUserId(), scrap.getPolicyId()};
		jdbcUtil.setSqlAndParameters(sql, param);
		
		String key[] = {"userId", "policyId"};
		
		try {
			jdbcUtil.executeUpdate();
			
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			if(rs.next()) {
				generatedKey1 = rs.getInt(1);
				scrap.setPolicyId(generatedKey1);
				generatedKey2 = rs.getInt(2);
				scrap.setPolicyId(generatedKey2);
			}
			return scrap;				
			
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		
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
	
	/* 사용자가 스크랩한 정책 리스트 불러오기 */
	public List<Scrap> getScrapList(String userId) throws SQLException {
		
		String sql = "SELECT * "
				   + "FROM Scrap"
				   + "WHERE userId=? ";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<Scrap> scrapList = new ArrayList<Scrap>();
			
			while (rs.next()) {
				Scrap scrap = new Scrap (	
						rs.getString("userId"),
						rs.getInt("policyId")
						);
				scrapList.add(scrap);				
			}		
			return scrapList;		
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		
		return null;
	}

	
}
=======
package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Policy;
import model.Scrap;
import model.User;

public class ScrapDAO {

	private JDBCUtil jdbcUtil = null;
	
	public ScrapDAO() {			
		jdbcUtil = new JDBCUtil();	
	}
	
	/* 정책 스크랩하기 */
	public Scrap addScrap(Scrap sc) {
		int generatedKey;
		Scrap scrap = null;
		
		String sql = "INSERT INTO Scrap VALUES (?, ?) ";

		Object[] param = new Object[] {sc.getUserId(), sc.getPolicyId()};
		jdbcUtil.setSqlAndParameters(sql, param);
		
		String key[] = {"userId", "policyId"};
		
		try {
			jdbcUtil.executeUpdate();
			
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			if(rs.next()) {
				generatedKey = rs.getInt(1);
				scrap.setPolicyId(generatedKey);
			}
			return scrap;				
			
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		
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
	
	/* 사용자가 스크랩한 정책 리스트 불러오기 */
	public List<Scrap> getScrapList(String userId) throws SQLException {
		
		String sql = "SELECT * "
				   + "FROM Scrap"
				   + "WHERE userId=? ";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<Scrap> scrapList = new ArrayList<Scrap>();
			
			while (rs.next()) {
				Scrap scrap = new Scrap (	
						rs.getString("userId"),
						rs.getInt("policyId")
						);
				scrapList.add(scrap);				
			}		
			return scrapList;		
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		
		return null;
	}

	
}
>>>>>>> branch 'develop' of https://github.com/juri123123/DBP.git
