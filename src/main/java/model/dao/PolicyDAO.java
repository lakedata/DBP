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
		jdbcUtil = new JDBCUtil();	
	}
		
	
	/* Policy table占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈뼢占쎌뒻占쎌굲 占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲(占쎈쐻占쎈짗占쎌굲筌�占� 占쎈쐻占쎈솯�ⓦ끉�굲), policyId(PK)占쎈쐻占쎈짗占쎌굲 Sequence 占쎈쐻占쎈짗占쎌굲占쎈쐻�뜝占� */
	public Policy insertPolicy(Policy pol) throws SQLException {

		int generatedKey;
		
		String sql = "INSERT INTO Policy VALUES (policyIdSeq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Object[] param = new Object[] {pol.getName(), pol.getContents(), pol.getCategory(), pol.getStartDate(), 
				pol.getEndDate(), pol.getPolicySummary(), pol.getQualificationForApplication(), pol.getHowToApply(), 
				pol.getLocal(), pol.getStartAge(), pol.getEndAge(), pol.getIncome(), 'n'};	
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
	
	/* 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 policy 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 */
	public int updatePolicy(Policy pol) throws SQLException{
	
		String sql = "UPDATE Policy "
					+ "SET startAge=?, endAge=?, qualificationForApplication=?, startDate=?, endDate=?, income=? "
					+ "WHERE policyId=?";
		
		int startAge = pol.getStartAge();
		int endAge = pol.getEndAge();
		String qualApp = pol.getQualificationForApplication();
		String startDate = pol.getStartDate();
		String endDate = pol.getEndDate();
		int income = pol.getIncome();
		
		if (qualApp.equals("")) qualApp = null;
		if (startDate.equals("")) startDate = null;
		if (endDate.equals("")) endDate = null;
		
		Object[] param = new Object[] {pol.getStartAge(), pol.getEndAge(), pol.getQualificationForApplication(), 
										pol.getStartDate(), pol.getEndDate(), pol.getIncome()};
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
	
	/* policy 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 */
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
	
	/* 占쎈쐻占쎈솂占쎈섣占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲筌�占� ID占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈솋占쎈솇占쎌굲占쎈쐻占쎈뼣占쎈솇占쎌굲 占쎈쐻占쎈짗占쎌굲筌��겭�쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈뼣占쎈솇占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈뼑占쎄땔占쎌굲 */
	public boolean existingPolicy (int policyId) throws SQLException {
		String sql = "SELECT count(*) "
				   + "FROM Policy "
				   + "WHERE policyId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {policyId});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쎈쐻占쎈짗占쎌굲占쎌넎
		}
		return false;
	}
	

	/* 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈뼣占쎈솇占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲筌�占� 占쎈쐻占쎈뼑占쎄땔占쎌굲占쎈쐻占쎈뼣占쎌뒻占쎌굲 List占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎌넎 */
	public List<Policy> searchPolicyList(String category, int income, String local, int startAge, int endAge, int currentPage, int countPerPage) throws SQLException {
        String sql = "SELECT policyId, name, category "
        		   + "FROM Policy "
        		   + "WHERE category=?, income=?, local=?, startAge=?, endAge=? "
        		   + "ORDER BY policyId"; 
        
        Object[] param = new Object[] {category, income, local, startAge, endAge};
		jdbcUtil.setSqlAndParameters(sql, param, 
									ResultSet.TYPE_SCROLL_INSENSITIVE, 
									ResultSet.CONCUR_READ_ONLY);		// JDBCUtil占쎈쐻占쎈짗占쎌굲 query占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲			
			
			int start = ((currentPage - 1) * countPerPage) + 1; 
			
			if((start >= 0) && rs.absolute(start)) {
				List<Policy> polList = new ArrayList<Policy>();
				
				do {
					Policy pol = new Policy (			// Policy 占쎈쐻占쎈짗占쎌굲筌ｋ떣�쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈뼣占쎌뒻占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
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
			jdbcUtil.close();		// resource 占쎈쐻占쎈짗占쎌굲占쎌넎
		}
		return null;
	}
	
	/* 占쎈쐻占쎈짗占쎌굲筌ｏ옙 占쎈쐻占쎈짗占쎌굲筌�占� List 占쎈쐻占쎈짗占쎌굲占쎌넎 */
	public List<Policy> findPolicyList() throws SQLException {
		String sql = "SELECT policyId, name, category "
				   + "FROM Policy "
				   + "ORDER BY policyId";
		
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil占쎈쐻占쎈짗占쎌굲 query占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲			
			List<Policy> polList = new ArrayList<Policy>();	// Community占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈뱜 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
			while (rs.next()) {
				Policy pol = new Policy(			// Community 占쎈쐻占쎈짗占쎌굲筌ｋ떣�쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈뼣占쎌뒻占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
						rs.getInt("policyId"),
						rs.getString("name"),
						rs.getString("category")
						);
				polList.add(pol);				// List占쎈쐻占쎈짗占쎌굲 Community 占쎈쐻占쎈짗占쎌굲筌ｏ옙 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
			}		
			return polList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쎈쐻占쎈짗占쎌굲占쎌넎
		}
		return null;
	}
	
	
	 /* policyId占쎈쐻占쎈짗占쎌굲 policy 筌≪뼃�쐻占쎈뼃占쎈닰占쎌굲 占쎈쐻占쎈짗占쎌굲占쎌넎 
	  	占쎈쐻占쎈솂占쎈섣占쎌굲占쎈쐻占쎈짗占쎌굲  ID占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈솋占쎈솇占쎌굲占쎈쐻占쎈뼣占쎈솇占쎌굲 占쎈쐻占쎈짗占쎌굲筌�占� 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈뼟�뇡�빘�굲占쎈쐻占쎈뼓占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 筌≪뼃�쐻占쎈짗占쎌굲 PolicyDetails 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎄깻占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
	  	占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈뼣占쎌뒻占쎌굲 占쎈쐻占쎈짗占쎌굲占쎌넎.
	   */
	   public Policy findPolicy(int policyId) throws SQLException {
	        String sql = "SELECT name, contents, category, starDate, endDate, policySummary, qualificationForApplication, howToApply, local, startAge, endAge, income "
	                 + "FROM Policy "
	                 + "WHERE policyId=? ";   
	        
	      jdbcUtil.setSqlAndParameters(sql, new Object[] {policyId});      // JDBCUtil占쎈쐻占쎈짗占쎌굲 query占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
	      Policy pol = null;
	      
	      try {
	         ResultSet rs = jdbcUtil.executeQuery();         // query 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲         
	  
	         if (rs.next()) {
	            pol = new Policy (   
	                  // Community 占쎈쐻占쎈짗占쎌굲筌ｋ떣�쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈뼣占쎌뒻占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲
	                  rs.getString("name"),
	                  rs.getString("conents"),
	                  rs.getString("category"),
	                  rs.getString("startDate"),
	                  rs.getString("endDate"),
	                  rs.getString("policySummary"),
	                  rs.getString("qualificationForApplication"),
	                  rs.getString("howToApply"),
	                  rs.getString("local"), 
	                  rs.getInt("startAge"),
	                  rs.getInt("endAge"),
	                  rs.getInt("income"));  
	         }                    
	         
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         jdbcUtil.close();      // resource 占쎈쐻占쎈짗占쎌굲占쎌넎
	      }
	      return pol;
	   }

}
