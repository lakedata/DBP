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
