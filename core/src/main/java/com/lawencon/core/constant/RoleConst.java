package com.lawencon.core.constant;

public enum RoleConst {
    SYSTEM("RLSYS", "System"),
    EMPLOYEES("RLEMP", "Employees");

	private final String code;
	private final String name;
	
	RoleConst(final String code, final String name) {
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
