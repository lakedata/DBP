package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Agree;

public class AgreeDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(AgreeDAO.class);

	
	private JDBCUtil jdbcUtil = null;
	   
    public AgreeDAO() {   
       jdbcUtil = new JDBCUtil();   
    }
    
    public Agree createAgree(int postNum) {
    	logger.debug("in AgreeDAO");
    	
    	String sql = "INSERT INTO AGREE VALUES ( 0, 0, ? )";
    	Object[] param = new Object[] {postNum};
    	
    	jdbcUtil.setSqlAndParameters(sql, param);
    	
    	try {				
			jdbcUtil.executeUpdate();
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();
		}		
		return null;			   
    	
    }
    public int addAgree (Agree agree) throws SQLException{
		String sql = "UPDATE AGREE "
				+ "SET AGREE=? "
				+ "WHERE POSTNUM=? ";
		Object[] param = new Object[] {agree.getAgree(), agree.getPostNum()};
		jdbcUtil.setSqlAndParameters(sql, param);
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
    public int addDisagree (Agree agree) throws SQLException{
		String sql = "UPDATE AGREE "
				+ "SET DISAGREE=? "
				+ "WHERE POSTNUM=? ";
		Object[] param = new Object[] {agree.getDisagree(), agree.getPostNum()};
		jdbcUtil.setSqlAndParameters(sql, param);
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
