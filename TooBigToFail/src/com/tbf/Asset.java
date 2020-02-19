package com.tbf;

public abstract class Asset {
	protected String code;
	protected String id;
	protected String label;

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