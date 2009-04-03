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

	public void createAccount(String screenName, Integer memberId,
			String password, String memberType, Set<Entitlement> entitlements) {
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
}
