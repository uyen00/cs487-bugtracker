package com.gheewhiz;

import java.util.HashSet;
import java.util.Set;

public class Account {

	Integer accountId;
	String password;
	String screenName;
	Set<Entitlement> entitlements;

	public Account() {
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setEntitlements(Set<Entitlement> entitlements) {
		this.entitlements = entitlements;
	}

	public Set<Entitlement> getEntitlements() {
		if (entitlements == null) {
			return new HashSet<Entitlement>();
		}
		return entitlements;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getScreenName() {
		return screenName;
	}

	public boolean isEntitledWithManager() {
		return getEntitlements().contains(Entitlement.MANAGER);
	}

	public boolean isEntitledWithDeveloper() {
		return getEntitlements().contains(Entitlement.DEVELOPER);
	}

	public boolean isEntitledWithQA() {
		return getEntitlements().contains(Entitlement.QA);
	}

	public boolean isEntitledWIthAdmin() {
		return getEntitlements().contains(Entitlement.ADMIN);
	}
}
