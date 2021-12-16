package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Agree;
import model.Post;

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
    public int addAgree (int postNum) throws SQLException{
    	logger.debug("in addAgree");
		String sql = "UPDATE AGREE "
				+ "SET AGREE = AGREE + 1 "
				+ "WHERE POSTNUM=? ";
		Object[] param = new Object[] {postNum};
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
    public int addDisagree (int postNum) throws SQLException{
    	logger.debug("in disagree");
		String sql = "UPDATE AGREE "
				+ "SET DISAGREE = DISAGREE + 1 "
				+ "WHERE POSTNUM=? ";
		Object[] param = new Object[] {postNum};
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
    public Agree findAgree(int postNum) throws SQLException{
    	String sql = "SELECT agree, disagree, postnum "
                + "FROM agree "
                + "WHERE postnum=? ";
    	
    	jdbcUtil.setSqlAndParameters(sql, new Object[] {postNum});
    	Agree agree = null; 
    	
    	try {
            ResultSet rs = jdbcUtil.executeQuery();                
     
            if (rs.next()) {
               agree = new Agree (
               		rs.getInt("agree"),
               		rs.getInt("disagree"),
               		rs.getInt("postnum")
               		);  
            }                    
            
         } catch (Exception ex) {
            ex.printStackTrace();
         } finally {
            jdbcUtil.close();      // resource 
         }
         return agree;
    }
    
}
