package com.lawencon.core.constant;

public enum SystemConst {
    PRINCIPAL("RLSYS", "846b92a3-d694-4ca1-a01e-7879ca1887f1");

	private final String code;
	private final String name;
	
	SystemConst(final String code, final String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}
