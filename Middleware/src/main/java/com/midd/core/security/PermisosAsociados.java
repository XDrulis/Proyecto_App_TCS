package com.midd.core.security;

public enum PermisosAsociados {
	USER_WRITE("user:write"),
	USER_READ("user:read"),
	ADMIN_WRITE("admin:write"),
	ADMIN_READ("admin:read");
	
	private final String permission;

	private PermisosAsociados(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
	
}
