package com.gheewhiz;


public interface BugTrackerDao {
	public Account createAccount(Account account);

	public Account getAccount(Integer accountId);

	public Account getAccountByScreenName(String screenName);
	
	public void removeAccount(Account account);

	public void updateAccount(Account account);
}
