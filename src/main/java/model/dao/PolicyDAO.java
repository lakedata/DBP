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
		jdbcUtil = new JDBCUtil();	// JDBCUtil �뜝�룞�삕泥� �뜝�룞�삕�뜝�룞�삕
	}
		
	
	/* Policy table�뜝�룞�삕 �뜝�룞�삕�뜝�떥�슱�삕 �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕(�뜝�룞�삕梨� �뜝�뙥怨ㅼ삕), policyId(PK)�뜝�룞�삕 Sequence �뜝�룞�삕�뜝占� */
	public Policy insertPolicy(Policy pol) throws SQLException {

		int generatedKey;
		
		String sql = "INSERT INTO Policy VALUES (policyIdSeq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Object[] param = new Object[] {pol.getName(), pol.getContents(), pol.getCategory(), pol.getPolicySummary(),
				pol.getQualificationForApplication(), pol.getHowToApply(), pol.getLocal(), pol.getStartAge(), pol.getEndAge(), 
				pol.getStartDate(), pol.getEndAge(), pol.getIncome()};	
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
	
	/* �뜝�룞�삕�뜝�룞�삕 policy �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 */
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
	
	/* policy �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 */
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
	
	/* �뜝�뙇�뼲�삕�뜝�룞�삕 �뜝�룞�삕梨� ID�뜝�룞�삕 �뜝�뙏�뙋�삕�뜝�떦�뙋�삕 �뜝�룞�삕梨끻뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�뙋�삕�뜝�룞�삕 �뜝�떙�궪�삕 */
	public boolean existingPolicy (int policyId) throws SQLException {
		String sql = "SELECT count(*) "
				   + "FROM Policy "
				   + "WHERE policyId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {policyId});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �뜝�룞�삕�뜝�룞�삕
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource �뜝�룞�삕�솚
		}
		return false;
	}
	

	/* �뜝�룞�삕�뜝�떦�뙋�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕梨� �뜝�떙�궪�삕�뜝�떦�슱�삕 List�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�룞�삕�솚 */
	public List<Policy> searchPolicyList(String category, int income, String local, int startAge, int endAge, int currentPage, int countPerPage) throws SQLException {
        String sql = "SELECT policyId, name, category "
        		   + "FROM Policy "
        		   + "WHERE category=?, income=?, local=?, startAge=?, endAge=? "
        		   + "ORDER BY policyId"; 
        
        Object[] param = new Object[] {category, income, local, startAge, endAge};
		jdbcUtil.setSqlAndParameters(sql, param, 
									ResultSet.TYPE_SCROLL_INSENSITIVE, 
									ResultSet.CONCUR_READ_ONLY);		// JDBCUtil�뜝�룞�삕 query�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query �뜝�룞�삕�뜝�룞�삕			
			
			int start = ((currentPage - 1) * countPerPage) + 1; 
			
			if((start >= 0) && rs.absolute(start)) {
				List<Policy> polList = new ArrayList<Policy>();
				
				do {
					Policy pol = new Policy (			// Policy �뜝�룞�삕泥닷뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�슱�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
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
			jdbcUtil.close();		// resource �뜝�룞�삕�솚
		}
		return null;
	}
	
	/* �뜝�룞�삕泥� �뜝�룞�삕梨� List �뜝�룞�삕�솚 */
	public List<Policy> findPolicyList() throws SQLException {
		String sql = "SELECT policyId, name, category "
				   + "FROM Policy "
				   + "ORDER BY policyId";
		
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�뜝�룞�삕 query�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query �뜝�룞�삕�뜝�룞�삕			
			List<Policy> polList = new ArrayList<Policy>();	// Community�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�듃 �뜝�룞�삕�뜝�룞�삕
			while (rs.next()) {
				Policy pol = new Policy(			// Community �뜝�룞�삕泥닷뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�슱�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
						rs.getInt("policyId"),
						rs.getString("name"),
						rs.getString("category")
						);
				polList.add(pol);				// List�뜝�룞�삕 Community �뜝�룞�삕泥� �뜝�룞�삕�뜝�룞�삕
			}		
			return polList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource �뜝�룞�삕�솚
		}
		return null;
	}
	
	
	 /* policyId�뜝�룞�삕 policy 李얍뜝�떍�눦�삕 �뜝�룞�삕�솚 
	  	�뜝�뙇�뼲�삕�뜝�룞�삕  ID�뜝�룞�삕 �뜝�뙏�뙋�삕�뜝�떦�뙋�삕 �뜝�룞�삕梨� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떢釉앹삕�뜝�떛�룞�삕�뜝�룞�삕�뜝�룞�삕 李얍뜝�룞�삕 PolicyDetails �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �겢�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
	  	�뜝�룞�삕�뜝�룞�삕�뜝�떦�슱�삕 �뜝�룞�삕�솚.
	   */
	   public Policy findPolicy(int policyId) throws SQLException {
	        String sql = "SELECT name, contents, category, starDate, endDate, policySummary, qualificationForApplication, howToApply, local, startAge, endAge, income "
	                 + "FROM Policy "
	                 + "WHERE policyId=? ";   
	        
	      jdbcUtil.setSqlAndParameters(sql, new Object[] {policyId});      // JDBCUtil�뜝�룞�삕 query�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
	      Policy pol = null;
	      
	      try {
	         ResultSet rs = jdbcUtil.executeQuery();         // query �뜝�룞�삕�뜝�룞�삕         
	  
	         if (rs.next()) {
	            pol = new Policy (   
	                  // Community �뜝�룞�삕泥닷뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�슱�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
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
	         jdbcUtil.close();      // resource �뜝�룞�삕�솚
	      }
	      return pol;
	   }

}
