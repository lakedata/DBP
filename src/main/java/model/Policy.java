
package model;

import java.io.Serializable;

public class Policy implements Serializable {
	
	private int policyId = 0;		// ��å ���̵�
	private String name = null;		// ��å �̸�
	private String contents = null;		// ��å ����
	private String category = null; 	// ��å �з� ī�װ�
	private String period = null;	// ��å ���� �Ⱓ
	private String policySummary = null;	// ��å ���
	private String qualificationForApplication = null;	  // ��å ��û �ڰ� ���
	private String howToApply = null;	  // ��û ���
	private String local = null;	 // ��å ����
	private int age = 20; 	 // ��å ���� ���� ����
	private int income = 0; // �ҵ����
	
	
	public Policy(int policyId, String name) {
		super();
		this.policyId = policyId;
		this.name = name;
	}
	
	public Policy(String name, String contents, String category, String period, String policySummary, 
	         String qualificationForApplication, String howToApply, String local, int age, int income) {
	      this.name = name;
	      this.contents = contents;
	      this.category = category;
	      this.period = period;
	      this.policySummary = policySummary;
	      this.qualificationForApplication = qualificationForApplication;
	      this.howToApply = howToApply;
	      this.local = local;
	      this.income = income;
	      this.age = age;
	   }

	

	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	
	public String getPolicySummary() {
		return policySummary;
	}
	public void setPolicySummary(String policySummary) {
		this.policySummary = policySummary;
	}
	
	public String getQualificationForApplication() {
		return qualificationForApplication;
	}
	public void setQualificationForApplication(String qualificationForApplication) {
		this.qualificationForApplication = qualificationForApplication;
	}
	
	public String getHowToApply() {
		return howToApply;
	}
	public void setHowToApply(String howToApply) {
		this.howToApply = howToApply;
	}
	
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
}
