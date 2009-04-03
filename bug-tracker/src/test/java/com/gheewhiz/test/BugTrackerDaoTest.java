package com.gheewhiz.test;

import java.util.Collections;

import javax.annotation.Resource;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.gheewhiz.Account;
import com.gheewhiz.BugTrackerDao;
import com.gheewhiz.Entitlement;

@ContextConfiguration(locations = { "/bug-tracker-datasource.xml",
		"/bug-tracker-app-context.xml" })
public class BugTrackerDaoTest extends
		AbstractTransactionalTestNGSpringContextTests {

	@Resource
	BugTrackerDao bugTrackerDao;

	public void setBugTrackerDao(BugTrackerDao bugTrackerDao) {
		this.bugTrackerDao = bugTrackerDao;
	}

	@Test(groups = { "basic" })
	@Rollback(true)
	public void testAccountDaoOperations() {
		Account account = new Account();
		account.setPassword("123");
		account.setEntitlements(Collections.singleton(Entitlement.DEVELOPER));
		account.setScreenName("testdeveloper");
		Account dbAccount = bugTrackerDao.createAccount(account);
		Integer accountId = dbAccount.getAccountId();
		assert accountId != null;
		assert dbAccount.getScreenName().equals("testdeveloper");
		dbAccount.setScreenName("testdev2");
		bugTrackerDao.updateAccount(dbAccount);
		dbAccount = bugTrackerDao.getAccount(accountId);
		assert dbAccount.getScreenName().trim().equals("testdev2");
		bugTrackerDao.removeAccount(dbAccount);
		assert bugTrackerDao.getAccount(accountId) == null;
	}
}
