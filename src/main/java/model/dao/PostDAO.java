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
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	/* �Խñ� ��� */
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
        
      jdbcUtil.setSqlAndParameters(sql, new Object[] {postNum});      // JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙
      Post post = null;
      
      try {
         ResultSet rs = jdbcUtil.executeQuery();         // query 占쏙옙占쏙옙         
  
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
         jdbcUtil.close();      // resource 占쏙옙환
      }
      return post;
   }
	
	/* ��ü post ã�� */
	public List<Post> findPostList() throws SQLException {
		
        String sql = "SELECT postNum, policyId, userId, title, writeDate, content "
     		   + "FROM Post "
     		   + "ORDER BY postNum";   
        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Post> postList = new ArrayList<Post>();	// Community���� ����Ʈ ����
			while (rs.next()) {
				Post po = new Post(			// Community ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getInt("postNum"),
						rs.getInt("policyId"),
						rs.getString("userId"),
						rs.getString("title"),
						rs.getString("writeDate"),
						rs.getString("content"));
				postList.add(po);				// List�� Community ��ü ����
			}		
			return postList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	
}
