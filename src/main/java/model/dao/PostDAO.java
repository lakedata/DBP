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
		jdbcUtil = new JDBCUtil();	// JDBCUtil 占쏙옙체 占쏙옙占쏙옙
	}
	
	/* 占쌉시깍옙 占쏙옙占� */
	public Post insertPost(Post po) throws SQLException {
			
		int generatedKey;
		
		String sql = "INSERT INTO Post VALUES (postNumSeq.nextval, ?, ?, ?, ?, ?)";
		
		Object[] param = new Object[] { po.getPolicyId(), po.getUserId(), po.getTitle(), po.getWriteDate(), po.getContent()};	
		
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
	
	public int deletePost(int postNum) throws SQLException {
		
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
	
	public Post findPost(int postNum) throws SQLException {
        String sql = "SELECT policyId, userId, title, writeDate, content "
                 + "FROM Post "
                 + "WHERE postNum=? ";   
        
      jdbcUtil.setSqlAndParameters(sql, new Object[] {postNum});      // JDBCUtil�뜝�룞�삕 query�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
      Post post = null;
      
      try {
         ResultSet rs = jdbcUtil.executeQuery();         // query �뜝�룞�삕�뜝�룞�삕         
  
         if (rs.next()) {
            post = new Post (
            		rs.getInt("postNum"),
            		rs.getInt("policyId"),
            		rs.getString("userId"),
            		rs.getString("title"),
            		rs.getString("writeDate"),
            		rs.getString("content")
      		 );  
         }                    
         
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      // resource �뜝�룞�삕�솚
      }
      return post;
   }
	
	/* 占쏙옙체 post 찾占쏙옙 */
	public List<Post> findPostList() throws SQLException {
		
        String sql = "SELECT postNum, policyId, userId, title, writeDate, content "
     		   + "FROM Post "
     		   + "ORDER BY postNum";   
        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 占쏙옙占쏙옙			
			List<Post> postList = new ArrayList<Post>();	// Community占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙
			while (rs.next()) {
				Post po = new Post(			// Community 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
						rs.getInt("postNum"),
						rs.getInt("policyId"),
						rs.getString("userId"),
						rs.getString("title"),
						rs.getString("writeDate"),
						rs.getString("content"));
				postList.add(po);				// List占쏙옙 Community 占쏙옙체 占쏙옙占쏙옙
			}		
			return postList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
	}
	
	
}
