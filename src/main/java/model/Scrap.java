package model;

import java.io.Serializable;

public class Scrap implements Serializable {
	
	private String userId;
	private int policyId;
	
	public Scrap() {
	
	}
	
	public Scrap(String userId, int policyId) {
		this.userId = userId;
		this.policyId = policyId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	
	
}
