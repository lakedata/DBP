package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.reply.ListReplyController;
import model.Post;
import model.Reply;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

	public class ReplyDAO {
		
		private static final Logger logger = LoggerFactory.getLogger(ReplyDAO.class);
		
		private JDBCUtil jdbcUtil = null;
	
		public ReplyDAO() {	
			jdbcUtil = new JDBCUtil();	
		}

	public Reply createReply(Reply re) throws SQLException {
		
		logger.debug("in ReplyDAO");
		
		int generatedKey;
		
		String sql = "INSERT INTO Reply VALUES ( ?, ?, ?, replyNumSeq.nextval)";		
		Object[] param = new Object[] {"n", re.getReplyContent(), re.getPostNum()};		
	
		jdbcUtil.setSqlAndParameters(sql, param);
		
		String key[] = {"replyNum"};
					
		try {				
			jdbcUtil.executeUpdate();
			
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			if(rs.next()) {
				generatedKey = rs.getInt(4);
				re.setReplyNum(generatedKey);
				logger.debug("generatedKey: " +generatedKey);
			}
			
			return re;
		
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
						rs.getString("agree"),
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
