package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.PolicyDAO;
import model.Policy;

public class PolicyManager {
	
	private static PolicyManager polMan = new PolicyManager();
	private PolicyDAO polDAO;
	
	private PolicyManager() {
		try {
			polDAO = new PolicyDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static PolicyManager getInstance() {
		return polMan;
	}
	

	/* ��å ��� */
	public Policy insert(Policy pol) throws SQLException, ExistingPolicyException {
		if (polDAO.existingPolicy(pol.getPolicyId()) == true) {
			throw new ExistingPolicyException(pol.getPolicyId() + "�� �����ϴ� ��å�Դϴ�.");			
		}
		return polDAO.insertPolicy(pol);
	}
	
	
	/* ��å ���� */
	public int update(Policy pol) throws SQLException, NoExistingPolicyException {
		
		if (polDAO.existingPolicy(pol.getPolicyId()) == false) {
			throw new NoExistingPolicyException(pol.getPolicyId() + "�� �������� �ʴ� ��å�Դϴ�.");
		}
		
		return polDAO.updatePolicy(pol);
	}
	
	/* ��å ���� */
	public int delete(int policyId) throws SQLException, NoExistingPolicyException {
		
		if (polDAO.existingPolicy(policyId) == false) {
			throw new NoExistingPolicyException(policyId + "�� �������� �ʴ� ��å�Դϴ�.");
		}
		
		return polDAO.deletePolicy(policyId);
	}
	
	/* ��å �˻� */
	public List<Policy> searchPolicyList(String category, int income, String local, int age) throws SQLException {
		
		return polDAO.searchPolicyList(category, income, local, age);
	}
	
	/* ��å ã�� */
	public Policy findPolicy(int policyId) throws SQLException, NoExistingPolicyException {
		Policy pol = polDAO.findPolicy(policyId);
		
		if(pol == null) {
			throw new NoExistingPolicyException(policyId + "�� �������� �ʴ� ��å�Դϴ�.");
		}
		
		return pol;
	}

	public List<Policy> findPolicyList() throws SQLException {
		return polDAO.findPolicyList();
	}

	

}
