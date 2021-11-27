package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Comment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

	public class CommentDAO {
		
		private JDBCUtil jdbcUtil = null;
	
		public CommentDAO() {	
			jdbcUtil = new JDBCUtil();	
		}

	public Comment createComment(Comment comm) throws SQLException {
		
		int generatedKey;
		
		String sql = "INSERT INTO Comment VALUES (?, ?, ?)";		
		Object[] param = new Object[] {comm.getPostNum(), comm.getContent(), comm.getAgree()};		
	
		jdbcUtil.setSqlAndParameters(sql, param);
		
		String key[] = {"postNum"};
					
		try {				
			jdbcUtil.executeUpdate();
			
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			if(rs.next()) {
				generatedKey = rs.getInt(1);
				comm.setPostNum(generatedKey);
			}
			
			return comm
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;			
	}
	
	// ´ñ±Û ¼öÁ¤
	public int updateComment(Comment comm) throws SQLException {
		
		String sql = "UPDATE Comment "
				   + "SET agree=?, commentContent=? "
				   + "WHERE postNum=?";
				
		char agree = comm.getAgree();
		String commentContent = comm.getContent();
		
		if (commentContent.equals("")) commentContent = null;
		
		Object[] param = new Object[] {agree, commentContent, comm.getPostNum()};		
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {		
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}		
		return 0;
	}
	
	// ´ñ±Û »èÁ¦
	public int deleteComment(int postNum) throws SQLException {
		String sql = "DELETE FROM Comment "
				   + "WHERE postNum=?";	

		jdbcUtil.setSqlAndParameters(sql, new Object[] {postNum});
		
		try {		
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	

}
