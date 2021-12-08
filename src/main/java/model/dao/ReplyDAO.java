package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Post;
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
		
		String sql = "INSERT INTO Reply VALUES (postNumSeq.nextval, ?, ?, replyNumSeq.nextval)";		
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
	
	public List<Reply> findReplyList() throws SQLException {
		
		String sql = "SELECT * "
				+ "FROM Reply "
				+ "ORDER BY replyNum";
		
		jdbcUtil.setSqlAndParameters(sql, null);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				
			List<Reply> replyList = new ArrayList<Reply>();	
			while (rs.next()) {
				Reply re = new Reply(			
						rs.getInt("postNum"),
						rs.getString("agree").charAt(0),
						rs.getString("replyContent"),
						rs.getInt("replyNum"));
				replyList.add(re);				
			}		
			return replyList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource �뜝�룞�삕�솚
		}
		
		return null;
		
	}

}
