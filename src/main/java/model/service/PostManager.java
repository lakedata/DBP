package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.PostDAO;
import model.Post;

public class PostManager {
	
	private static PostManager postMan = new PostManager();
	private PostDAO postDAO;
	
	private PostManager() {
		try {
			postDAO = new PostDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static PostManager getInstance() {
		return postMan;
	}
	
	
	public Post insert(Post post) throws SQLException {
		return postDAO.insertPost(post);
	}

	public int delete (int postId) throws SQLException {
		return postDAO.deletePost(postId);
	}
	public int update (Post post) throws SQLException {
		return postDAO.updatePost(post);
	}
	public Post findPost(int postId) throws SQLException {
		Post post = postDAO.findPost(postId);
		
		return post;
	}
	public List<Post> findPostList() throws SQLException {
		return postDAO.findPostList();
	}
	
}
