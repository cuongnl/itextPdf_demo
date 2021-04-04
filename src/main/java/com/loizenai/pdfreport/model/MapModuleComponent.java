package com.loizenai.pdfreport.model;

public class MapModuleComponent extends BaseObject{
	public MapModuleComponent() {
		// TODO Auto-generated constructor stub
	}
	public MapModuleComponent(Long mapModuleComponentId, String mapModuleComponentName, Component component) {
		super();
		this.mapModuleComponentId = mapModuleComponentId;
		this.mapModuleComponentName = mapModuleComponentName;
		this.component = component;
	}
	private Long mapModuleComponentId;
	private String mapModuleComponentName;
	private Component component;
	public Long getMapModuleComponentId() {
		return mapModuleComponentId;
	}
	public void setMapModuleComponentId(Long mapModuleComponentId) {
		this.mapModuleComponentId = mapModuleComponentId;
	}
	public String getMapModuleComponentName() {
		return mapModuleComponentName;
	}
	public void setMapModuleComponentName(String mapModuleComponentName) {
		this.mapModuleComponentName = mapModuleComponentName;
	}
	public Component getComponent() {
		return component;
	}
	public void setComponent(Component component) {
		this.component = component;
	}
	
	
	
}
