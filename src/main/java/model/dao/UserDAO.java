package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

public class UserDAO {
	//birth string->date
	private JDBCUtil jdbcUtil = null;
	
	public UserDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil °´Ã¼ »ý¼º
	}
		
	public int create(User user) throws SQLException {
		String sql = "INSERT INTO USER1 VALUES (?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {user.getUserId(), user.getName(), 
				user.getEmail(), user.getPassword(), user.getBirth(), user.getPhoneNumber() };
		jdbcUtil.setSqlAndParameters(sql, param);	
				
		try {				
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();
		}		
		return 0;			
	}

	
	public int update(User user) throws SQLException {
		String sql = "UPDATE USER1 "
					+ "SET password=?, name=?, email=?, birth=?, phoneNumber=? "
					+ "WHERE user_Id=?";
		Object[] param = new Object[] {user.getPassword(), user.getName(), user.getEmail(), 
					user.getBirth(), user.getPhoneNumber(), 
					user.getUserId()};				
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

	public int remove(String userId) throws SQLException {
		String sql = "DELETE FROM USER1 WHERE user_Id=? ";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});

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

	public User findUser(String userId) throws SQLException {
        String sql = "SELECT password, name, email, birth, phoneNumber "
        			+ "FROM User1 "
        			+ "WHERE user_Id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {		
				User user = new User(		
					userId,
					rs.getString("password"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getString("birth"),
					rs.getString("phoneNumber"));
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();	
		}
		return null;
	}
	
	public boolean existingUser(String userId) throws SQLException {
		String sql = "SELECT count(*) FROM USER1 WHERE user_Id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	

		try {
			ResultSet rs = jdbcUtil.executeQuery();	
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();	
		}
		return false;
	}
}
