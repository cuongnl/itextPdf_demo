package com.loizenai.pdfreport.model;

public class PolicyUse extends BaseObject {
	public PolicyUse() {
		// TODO Auto-generated constructor stub
	}
	public PolicyUse(Long policyUseId, String policyName, String valueClob) {
		super();
		this.policyUseId = policyUseId;
		this.policyName = policyName;
		this.valueClob = valueClob;
	}
	private Long policyUseId;
	private String policyName;
	private String valueClob;
	public Long getPolicyUseId() {
		return policyUseId;
	}
	public void setPolicyUseId(Long policyUseId) {
		this.policyUseId = policyUseId;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getValueClob() {
		return valueClob;
	}
	public void setValueClob(String valueClob) {
		this.valueClob = valueClob;
	}
	
	
	
}
