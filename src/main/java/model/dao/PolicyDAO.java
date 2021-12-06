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
		
	
	/* Policy table�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉℡뜝�럩�뮲�뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�(�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯占썲뜝占� �뜝�럥�맶�뜝�럥�넰占썩벀�걠占쎄뎡), policyId(PK)�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� Sequence �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占� */
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
	
	/* �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� policy �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� */
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
	
	/* policy �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� */
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
	
	/* �뜝�럥�맶�뜝�럥�냲�뜝�럥�꽔�뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯占썲뜝占� ID�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥�냻�뜝�럥�냷�뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럥�냷�뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯占쏙옙寃�占쎌맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럥�냷�뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥堉묈뜝�럡�븫�뜝�럩援� */
	public boolean existingPolicy (int policyId) throws SQLException {
		String sql = "SELECT count(*) "
				   + "FROM Policy "
				   + "WHERE policyId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {policyId});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럩�꼶
		}
		return false;
	}
	

	/* �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럥�냷�뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯占썲뜝占� �뜝�럥�맶�뜝�럥堉묈뜝�럡�븫�뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럩�뮲�뜝�럩援� List�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럩�꼶 */
	public List<Policy> searchPolicyList(String category, int income, String local, int startAge, int endAge, int currentPage, int countPerPage) throws SQLException {
        String sql = "SELECT policyId, name, category "
        		   + "FROM Policy "
        		   + "WHERE category=?, income=?, local=?, startAge=?, endAge=? "
        		   + "ORDER BY policyId"; 
        
        Object[] param = new Object[] {category, income, local, startAge, endAge};
		jdbcUtil.setSqlAndParameters(sql, param, 
									ResultSet.TYPE_SCROLL_INSENSITIVE, 
									ResultSet.CONCUR_READ_ONLY);		// JDBCUtil�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� query�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�			
			
			int start = ((currentPage - 1) * countPerPage) + 1; 
			
			if((start >= 0) && rs.absolute(start)) {
				List<Policy> polList = new ArrayList<Policy>();
				
				do {
					Policy pol = new Policy (			// Policy �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯節뗫뼠占쎌맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럩�뮲�뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
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
			jdbcUtil.close();		// resource �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럩�꼶
		}
		return null;
	}
	
	/* �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯節륁삕 �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯占썲뜝占� List �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럩�꼶 */
	public List<Policy> findPolicyList() throws SQLException {
		String sql = "SELECT policyId, name, category "
				   + "FROM Policy "
				   + "ORDER BY policyId";
		
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� query�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�			
			List<Policy> polList = new ArrayList<Policy>();	// Community�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥諭� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
			while (rs.next()) {
				Policy pol = new Policy(			// Community �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯節뗫뼠占쎌맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럩�뮲�뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
						rs.getInt("policyId"),
						rs.getString("name"),
						rs.getString("category")
						);
				polList.add(pol);				// List�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� Community �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯節륁삕 �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
			}		
			return polList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럩�꼶
		}
		return null;
	}
	
	
	 /* policyId�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� policy 嶺뚢돦堉껓옙�맶�뜝�럥堉껃뜝�럥�떚�뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럩�꼶 
	  	�뜝�럥�맶�뜝�럥�냲�뜝�럥�꽔�뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�  ID�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥�냻�뜝�럥�냷�뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럥�냷�뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯占썲뜝占� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉잞옙�눀占쎈튂占쎄뎡�뜝�럥�맶�뜝�럥堉볟뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� 嶺뚢돦堉껓옙�맶�뜝�럥吏쀥뜝�럩援� PolicyDetails �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럡源삣뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
	  	�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럩�뮲�뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럩�꼶.
	   */
	   public Policy findPolicy(int policyId) throws SQLException {
	        String sql = "SELECT name, contents, category, starDate, endDate, policySummary, qualificationForApplication, howToApply, local, startAge, endAge, income "
	                 + "FROM Policy "
	                 + "WHERE policyId=? ";   
	        
	      jdbcUtil.setSqlAndParameters(sql, new Object[] {policyId});      // JDBCUtil�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� query�뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
	      Policy pol = null;
	      
	      try {
	         ResultSet rs = jdbcUtil.executeQuery();         // query �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�         
	  
	         if (rs.next()) {
	            pol = new Policy (   
	                  // Community �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯節뗫뼠占쎌맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럩�뮲�뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
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
	         jdbcUtil.close();      // resource �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럩�꼶
	      }
	      return pol;
	   }

}