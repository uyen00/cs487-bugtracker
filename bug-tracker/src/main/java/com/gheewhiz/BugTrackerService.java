package com.gheewhiz;

import java.util.Set;

public class BugTrackerService {
	BugTrackerDao bugTrackerDao;

	public void setBugTrackerDao(BugTrackerDao bugTrackerDao) {
		this.bugTrackerDao = bugTrackerDao;
	}

	public BugTrackerDao getBugTrackerDao() {
		return bugTrackerDao;
	}

	public Account authenticate(String screenName, String password) {
		Account account = bugTrackerDao.getAccountByScreenName(screenName);
		if (password.trim().equals(password)) {
			return account;
		}
		return null;
	}

	public Account getAccountById(Integer accountId) {
		return bugTrackerDao.getAccount(accountId);
	}

	public void createAccount(String screenName,
			String password, Set<Entitlement> entitlements) {
		Account account = bugTrackerDao.getAccountByScreenName(screenName);
		if (account == null) {
			account = new Account();
			account.setScreenName(screenName);
			account.setEntitlements(entitlements);
			account.setPassword(password);
			bugTrackerDao.createAccount(account);
		}
	}

	public void updateAccount(Account account) {
		bugTrackerDao.updateAccount(account);
	}
	
	public Set<Bug> getBugs(Integer productId) {
		return bugTrackerDao.getBugs(productId);
	}
	
	public Set<Bug> getOpenBugs() {
		return bugTrackerDao.getOpenBugs();
	}
	
	public Bug getBug(Integer bugID) {
		return bugTrackerDao.getBug(bugID);
	}
	
	public ProductCategory getProduct(Integer productId) {
		return bugTrackerDao.getProductCategory(productId);
	}
	
	public Bug createBug(Integer productId, String state, String resolution, String shortdesc, String steps, Set<Comment> comments) {
		Bug bug = new Bug();
		bug.setProductId(productId);
		bug.setState(state);
		bug.setResolution(resolution);
		java.util.Date opened = new java.util.Date();
		bug.setOpened(opened);
		bug.setShortdesc(shortdesc);
		bug.setSteps(steps);
		bug.setComments(comments);
		return bugTrackerDao.createBug(bug);
	}
	
	public Comment createComment(String com, Integer bugId, Account commenter) {
		Comment comment = new Comment();
		comment.setComment(com);
		comment.setBugId(bugId);
		comment.setCommenter(commenter);		
		return bugTrackerDao.createComment(comment);
	}
	
	public Set<ProductCategory> getProducts() {
		return bugTrackerDao.getAllProducts();
	}
	
	public ProductCategory createProductCategory(String name, String version, Integer managerID) {
		ProductCategory pc = new ProductCategory();
		pc.setName(name);
		pc.setVersion(version);
		pc.setManager(getAccountById(managerID));
		return bugTrackerDao.createProductCategory(pc);
	}
	
	public Set<Account> getManagerAccounts(){
		return bugTrackerDao.getAccountsByEntitlement(Entitlement.MANAGER);
	}
	
	public Set<Account> getQAAccounts(){
		return bugTrackerDao.getAccountsByEntitlement(Entitlement.QA);
	}
	
	public Set<Account> getDeveloperAccounts(){
		return bugTrackerDao.getAccountsByEntitlement(Entitlement.DEVELOPER);
	}
}
