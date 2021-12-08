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
		jdbcUtil = new JDBCUtil();	// JDBCUtil 占쎈쐻占쎈짗占쎌굲筌ｏ옙 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
	}
	
	/* 占쎈쐻占쎈셾占쎈뻻繹먮씮�굲 占쎈쐻占쎈짗占쎌굲占쎈쐻�뜝占� */
	public Post insertPost(Post po) throws SQLException {
			
		int generatedKey;
		
		String sql = "INSERT INTO Post VALUES (postNumSeq.nextval, ?, ?, ?, ?, ?)";
		
		Object[] param = new Object[] { po.getTitle(), po.getWriteDate(), po.getContent(), po.getUserId(), po.getPolicyId() };	
		
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
        String sql = "SELECT postNum, policyId, user_Id, title, writeDate, content "
                 + "FROM Post "
                 + "WHERE postNum=? ";   
        
      jdbcUtil.setSqlAndParameters(sql, new Object[] {postNum});      // JDBCUtil�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� query�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
      Post post = null;
      
      try {
         ResultSet rs = jdbcUtil.executeQuery();         // query �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�         
  
         if (rs.next()) {
            post = new Post (
            		rs.getInt("postNum"),
            		rs.getInt("policyId"),
            		rs.getString("user_Id"),
            		rs.getString("title"),
            		rs.getString("writeDate"),
            		rs.getString("content")
      		 );  
         }                    
         
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      // resource �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럩�꼶
      }
      return post;
   }
	
	/* 占쎈쐻占쎈짗占쎌굲筌ｏ옙 post 筌≪뼃�쐻占쎈짗占쎌굲 */
	public List<Post> findPostList() throws SQLException {
		
        String sql = "SELECT postNum, policyId, user_Id, title, writeDate, content "
     		   + "FROM Post "
     		   + "ORDER BY postNum";   
        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil占쎈쐻占쎈짗占쎌굲 query占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲			
			List<Post> postList = new ArrayList<Post>();	// Community占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈뱜 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
			while (rs.next()) {
				Post po = new Post(			// Community 占쎈쐻占쎈짗占쎌굲筌ｋ떣�쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈뼣占쎌뒻占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
						rs.getInt("postNum"),
						rs.getInt("policyId"),
						rs.getString("user_Id"),
						rs.getString("title"),
						rs.getString("writeDate"),
						rs.getString("content"));
				postList.add(po);				// List占쎈쐻占쎈짗占쎌굲 Community 占쎈쐻占쎈짗占쎌굲筌ｏ옙 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
			}		
			return postList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쎈쐻占쎈짗占쎌굲占쎌넎
		}
		return null;
	}
	
	/* myPost 내가 쓴글 보기  */
	public List<Post> findMyPostList(String userId) throws SQLException {
		
        String sql = "SELECT postNum, policyId, user_Id, title, writeDate, content "
     		   + "FROM Post "
        	   + "WHERE user_Id = ? "
     		   + "ORDER BY postNum";   
        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil占쎈쐻占쎈짗占쎌굲 query占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲			
			List<Post> postList = new ArrayList<Post>();	// Community占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈뱜 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
			while (rs.next()) {
				Post po = new Post(			// Community 占쎈쐻占쎈짗占쎌굲筌ｋ떣�쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈뼣占쎌뒻占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
						rs.getInt("postNum"),
						rs.getInt("policyId"),
						rs.getString("user_Id"),
						rs.getString("title"),
						rs.getString("writeDate"),
						rs.getString("content"));
				postList.add(po);				// List占쎈쐻占쎈짗占쎌굲 Community 占쎈쐻占쎈짗占쎌굲筌ｏ옙 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
			}		
			return postList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쎈쐻占쎈짗占쎌굲占쎌넎
		}
		return null;
	}
}
