package com.lawencon.core.constant;

public enum SystemConst {
    PRINCIPAL("RLSYS", "846b92a3-d694-4ca1-a01e-7879ca1887f1"),
	FILE_DEFAULT("FILE", "4b33713f-5921-492b-a72f-60a2ec5291f0");

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
