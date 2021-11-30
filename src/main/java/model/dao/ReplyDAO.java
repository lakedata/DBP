package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Reply;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

	public class ReplyDAO {
		
		private JDBCUtil jdbcUtil = null;
	
		public ReplyDAO() {	
			jdbcUtil = new JDBCUtil();	
		}

	public Reply createReply(Reply re) throws SQLException {
		
		int generatedKey;
		
		String sql = "INSERT INTO Reply VALUES (postNumSeq.nextval, ?, ?)";		
		Object[] param = new Object[] {re.getReplyContent(), re.getAgree()};		
	
		jdbcUtil.setSqlAndParameters(sql, param);
		
		String key[] = {"postNum"};
					
		try {				
			jdbcUtil.executeUpdate();
			
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			if(rs.next()) {
				generatedKey = rs.getInt(1);
				re.setPostNum(generatedKey);
			}
			
		
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return null;			
	}

}
