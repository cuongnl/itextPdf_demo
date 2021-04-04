package com.loizenai.pdfreport.model;

import java.util.List;

public class Component extends BaseObject {
	
	public Component() {
		// TODO Auto-generated constructor stub
	}
	public Component(Long componentId, String componentName, List<PolicyUse> policyUse) {
		super();
		this.componentId = componentId;
		this.componentName = componentName;
		this.policyUse = policyUse;
	}

	private Long componentId;
	private String componentName;
	
	private List<PolicyUse> policyUse;

	public Long getComponentId() {
		return componentId;
	}

	public void setComponentId(Long componentId) {
		this.componentId = componentId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public List<PolicyUse> getPolicyUse() {
		return policyUse;
	}

	public void setPolicyUse(List<PolicyUse> policyUse) {
		this.policyUse = policyUse;
	}
}
