package com.tbf;

public abstract class Asset {
	private String code;
	private String id;
	private String label;
	
	public Asset(String code, String id, String label) {
		super();
		this.code = code;
		this.id = id;
		this.label = label;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
}
