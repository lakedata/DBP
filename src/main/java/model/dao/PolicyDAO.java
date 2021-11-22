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
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
	
	/* Policy table�� ���ο� �� ����(��å �߰�), policyId(PK)�� Sequence ��� */
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
	
	/* ���� policy ���� ���� */
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
	
	/* policy ���� ���� */
	public int deletePolicy(String policyId) throws SQLException {
		
		String sql = "DELETE Policy "
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

	/* ���ϴ� �������� ��å �˻��Ͽ� List�� ���� �� ��ȯ */
	public List<Policy> findPolicyList(String category, int income, String local, int age) throws SQLException {
        String sql = "SELECT policyId, name "
        		   + "FROM Policy "
        		   + "WHERE category=?, income=?, local=?, age=? "
        		   + "ORDER BY policyId"; 
        
        Object[] param = new Object[] {category, income, local, age};
		jdbcUtil.setSqlAndParameters(sql, param);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Policy> polList = new ArrayList<Policy>();	// Policy���� ����Ʈ ����
			
			while (rs.next()) {
				Policy pol = new Policy (			// Policy ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getInt("policyId"),
						rs.getString("name"));
				polList.add(pol);				// List�� Policy ��ü ����
			}		
			return polList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	 /* policyId�� policy ã�Ƽ� ��ȯ 
	  	�־���  ID�� �ش��ϴ� ��å ������ �����ͺ��̽����� ã�� PolicyDetails ������ Ŭ������
	  	�����Ͽ� ��ȯ.
	   */
	   public Policy findPolicyList(int policyId) throws SQLException {
	        String sql = "SELECT name, contents, category, period, policySummary, qualificationForApplication, howToApply, local, age, income "
	                 + "FROM Policy "
	                 + "WHERE policyId=? ";   
	        
	      jdbcUtil.setSqlAndParameters(sql, new Object[] {policyId});      // JDBCUtil�� query�� ����
	      Policy pol = null;
	      
	      try {
	         ResultSet rs = jdbcUtil.executeQuery();         // query ����         
	  
	         while (rs.next()) {
	            pol = new Policy (   
	                  // Community ��ü�� �����Ͽ� ���� ���� ������ ����
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
	         jdbcUtil.close();      // resource ��ȯ
	      }
	      return pol;
	   }

}
