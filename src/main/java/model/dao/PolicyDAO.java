package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Policy;

public class PolicyDAO {

private JDBCUtil jdbcUtil = null;
	
	public PolicyDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 占쏙옙체 占쏙옙占쏙옙
	}
		
	
	/* Policy table占쏙옙 占쏙옙占싸울옙 占쏙옙 占쏙옙占쏙옙(占쏙옙책 占쌩곤옙), policyId(PK)占쏙옙 Sequence 占쏙옙占� */
	public Policy insertPolicy(Policy pol) throws SQLException {

		int generatedKey;
		
		String sql = "INSERT INTO Policy VALUES (policyIdSeq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Object[] param = new Object[] {pol.getName(), pol.getContents(), pol.getCategory(), pol.getPolicySummary(),
				pol.getQualificationForApplication(), pol.getHowToApply(), pol.getLocal(), pol.getAge(), 
				pol.getPeriod(), pol.getIncome()};	
		jdbcUtil.setSqlAndParameters(sql, param);
		
		String key[] = {"policyId"};
		
		try {
			jdbcUtil.executeUpdate();
			
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			if(rs.next()) {
				generatedKey = rs.getInt(1);
				pol.setPolicyId(generatedKey);
			}
			return pol;
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		
		return null;
	}
	
	/* 占쏙옙占쏙옙 policy 占쏙옙占쏙옙 占쏙옙占쏙옙 */
	public int updatePolicy(Policy pol) throws SQLException{
	
		String sql = "UPDATE Policy "
					+ "SET age=?, qualificationForApplication=?, peroid=?, income=? "
					+ "WHERE policyId=?";
		
		int age = pol.getAge();
		String qualApp = pol.getQualificationForApplication();
		String period = pol.getPeriod();
		int income = pol.getIncome();
		
		if (qualApp.equals("")) qualApp = null;
		if (period.equals("")) period = null;
		
		Object[] param = new Object[] {pol.getAge(), pol.getQualificationForApplication(), pol.getPeriod(), pol.getIncome()};
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
	
	/* policy 占쏙옙占쏙옙 占쏙옙占쏙옙 */
	public int deletePolicy(int policyId) throws SQLException {
		
		String sql = "DELETE FROM Policy "
					+ "WHERE policyId=?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {policyId});
		
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
	
	/* 占쌍억옙占쏙옙 占쏙옙책 ID占쏙옙 占쌔댐옙占싹댐옙 占쏙옙책占쏙옙 占쏙옙占쏙옙占싹댐옙占쏙옙 占싯삼옙 */
	public boolean existingPolicy (int policyId) throws SQLException {
		String sql = "SELECT count(*) "
				   + "FROM Policy "
				   + "WHERE policyId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {policyId});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쏙옙占쏙옙
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return false;
	}
	

	/* 占쏙옙占싹댐옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙책 占싯삼옙占싹울옙 List占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙환 */
	public List<Policy> searchPolicyList(String category, int income, String local, int age, int currentPage, int countPerPage) throws SQLException {
        String sql = "SELECT policyId, name, category "
        		   + "FROM Policy "
        		   + "WHERE category=?, income=?, local=?, age=? "
        		   + "ORDER BY policyId"; 
        
        Object[] param = new Object[] {category, income, local, age};
		jdbcUtil.setSqlAndParameters(sql, param, 
									ResultSet.TYPE_SCROLL_INSENSITIVE, 
									ResultSet.CONCUR_READ_ONLY);		// JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 占쏙옙占쏙옙			
			
			int start = ((currentPage - 1) * countPerPage) + 1; 
			
			if((start >= 0) && rs.absolute(start)) {
				List<Policy> polList = new ArrayList<Policy>();
				
				do {
					Policy pol = new Policy (			// Policy 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
							rs.getInt("policyId"),
							rs.getString("name"),
							rs.getString("category")
							);
					polList.add(pol);	
				} while ((rs.next()) && (--countPerPage > 0));
				return polList;
			}
						
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
	}
	
	/* 占쏙옙체 占쏙옙책 List 占쏙옙환 */
	public List<Policy> findPolicyList() throws SQLException {
		String sql = "SELECT policyId, name, category "
				   + "FROM Policy "
				   + "ORDER BY policyId";
		
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 占쏙옙占쏙옙			
			List<Policy> polList = new ArrayList<Policy>();	// Community占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙
			while (rs.next()) {
				Policy pol = new Policy(			// Community 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
						rs.getInt("policyId"),
						rs.getString("name"),
						rs.getString("category")
						);
				polList.add(pol);				// List占쏙옙 Community 占쏙옙체 占쏙옙占쏙옙
			}		
			return polList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
	}
	
	
	 /* policyId占쏙옙 policy 찾占싣쇽옙 占쏙옙환 
	  	占쌍억옙占쏙옙  ID占쏙옙 占쌔댐옙占싹댐옙 占쏙옙책 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싶븝옙占싱쏙옙占쏙옙占쏙옙 찾占쏙옙 PolicyDetails 占쏙옙占쏙옙占쏙옙 클占쏙옙占쏙옙占쏙옙
	  	占쏙옙占쏙옙占싹울옙 占쏙옙환.
	   */
	   public Policy findPolicy(int policyId) throws SQLException {
	        String sql = "SELECT name, contents, category, period, policySummary, qualificationForApplication, howToApply, local, age, income "
	                 + "FROM Policy "
	                 + "WHERE policyId=? ";   
	        
	      jdbcUtil.setSqlAndParameters(sql, new Object[] {policyId});      // JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙
	      Policy pol = null;
	      
	      try {
	         ResultSet rs = jdbcUtil.executeQuery();         // query 占쏙옙占쏙옙         
	  
	         if (rs.next()) {
	            pol = new Policy (   
	                  // Community 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
	                  rs.getString("name"),
	                  rs.getString("conents"),
	                  rs.getString("category"),
	                  rs.getString("period"),
	                  rs.getString("policySummary"),
	                  rs.getString("qualificationForApplication"),
	                  rs.getString("howToApply"),
	                  rs.getString("local"), 
	                  rs.getInt("age"), 
	                  rs.getInt("income"));  
	         }                    
	         
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         jdbcUtil.close();      // resource 占쏙옙환
	      }
	      return pol;
	   }

}
