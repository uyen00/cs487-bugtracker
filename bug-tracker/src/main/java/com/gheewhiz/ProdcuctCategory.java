package com.gheewhiz;

import java.util.Set;

public class ProdcuctCategory {
	private Integer productCategoryId;
	private String version;
	private Account manager;
	private Set<Account> QA;
	private Set<Account> Developers;

	public Integer getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Integer productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Account getManager() {
		return manager;
	}

	public void setManager(Account manager) {
		this.manager = manager;
	}

	public Set<Account> getQA() {
		return QA;
	}

	public void setQA(Set<Account> qa) {
		QA = qa;
	}

	public Set<Account> getDevelopers() {
		return Developers;
	}

	public void setDevelopers(Set<Account> developers) {
		Developers = developers;
	}
}
