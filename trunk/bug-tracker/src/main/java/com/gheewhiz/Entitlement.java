package com.gheewhiz;

public class Entitlement {
	private Integer entitlementId;
	private String type;
	public static Entitlement MANAGER = new Entitlement("MANAGER");
	public static Entitlement DEVELOPER = new Entitlement("DEVELOPER");
	public static Entitlement QA = new Entitlement("QA");
	public static Entitlement ADMIN = new Entitlement("ADMIN");

	public Entitlement() {
	}

	public Entitlement(String type) {
		this.type = type;
	}

	public Integer getEntitlementId() {
		return entitlementId;
	}

	public void setEntitlementId(Integer entitlementId) {
		this.entitlementId = entitlementId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Entitlement)) {
			return false;
		}
		Entitlement entitlement = (Entitlement) obj;
		if (this == obj) {
			return true;
		}
		if (type.equals(entitlement.getType())) {
			return true;
		}
		return super.equals(obj);
	}
}
