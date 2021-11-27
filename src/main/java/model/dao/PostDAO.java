package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Policy;
import model.Post;

public class PostDAO {

	private JDBCUtil jdbcUtil = null;
	
	public PostDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	/* 게시글 등록 */
	public Post insertPost(Post po) throws SQLException {
		
		int generatedKey;
		
		String sql = "INSERT INTO Post VALUES (?, ?, ?, ?, ?, ?)";
		
		Object[] param = new Object[] {po.getPostNum(), po.getPolicyId(), po.getUserId(), po.getTitle(), po.getWriteDate(), po.getContent()};	
		
		jdbcUtil.setSqlAndParameters(sql, param);
		
		String key[] = {"postNum"};
		
		try {
			jdbcUtil.executeUpdate();
			
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			if(rs.next()) {
				generatedKey = rs.getInt(1);
				po.setPostNum(generatedKey);
			}
			return po;
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		
		return null;
	}
	
	public int updatePost(Post po) throws SQLException{
	
		String sql = "UPDATE Post "
					+ "SET content=? "
					+ "WHERE postNum=?";
		
		String content = po.getContent();
		
		if (content.equals("")) content = null;
		
		Object[] param = new Object[] {content};
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
	
	public int deletePost(String postNum) throws SQLException {
		
		String sql = "DELETE Post "
					+ "WHERE postNum=?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postNum});
		
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
	
	/* 전체 post 찾기 */
	public List<Post> findPostList() throws SQLException {
		
        String sql = "SELECT postNum, policyId, userId, title, writeDate, content "
     		   + "FROM Post "
     		   + "ORDER BY postNum";   
        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Post> postList = new ArrayList<Post>();	// Community들의 리스트 생성
			while (rs.next()) {
				Post po = new Post(			// Community 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("postNum"),
						rs.getInt("policyId"),
						rs.getString("userId"),
						rs.getString("title"),
						rs.getString("writeDate"),
						rs.getString("content"));
				postList.add(po);				// List에 Community 객체 저장
			}		
			return postList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	
}
