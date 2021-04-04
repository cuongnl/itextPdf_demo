package com.loizenai.pdfreport.model;

public class Module extends BaseObject {
	public Module() {
		// TODO Auto-generated constructor stub
	}
	public Module(long idModule, String moduleName, MapModuleComponent mapModuleComponent) {
		super();
		this.idModule = idModule;
		this.moduleName = moduleName;
		this.mapModuleComponent = mapModuleComponent;
	}

	private long idModule;
	private String moduleName;
	private MapModuleComponent mapModuleComponent;

	public long getIdModule() {
		return idModule;
	}

	public void setIdModule(long idModule) {
		this.idModule = idModule;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public MapModuleComponent getMapModuleComponent() {
		return mapModuleComponent;
	}

	public void setMapModuleComponent(MapModuleComponent mapModuleComponent) {
		this.mapModuleComponent = mapModuleComponent;
	}

}
