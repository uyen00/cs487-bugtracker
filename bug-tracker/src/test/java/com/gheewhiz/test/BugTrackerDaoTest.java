package com.gheewhiz.test;

import java.util.Collections;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import com.gheewhiz.Account;
import com.gheewhiz.Bug;
import com.gheewhiz.BugTrackerDao;
import com.gheewhiz.Entitlement;
import com.gheewhiz.ProductCategory;

@ContextConfiguration(locations = { "/bug-tracker-datasource.xml",
		"/bug-tracker-app-context.xml" })
@Transactional
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

	public Bug bugStub(ProductCategory product) {
		Bug bug = new Bug();
		bug.setOpened(new Date());
		bug.setShortdesc("DESC");
		bug.setResolution("IN PROGRESS");
		bug.setState("OPEN");
		bug.setSteps("Do this");
		bug.setProductId(product.getProductCategoryId());
		return bugTrackerDao.createBug(bug);
	}

	public ProductCategory productCategoryStub(Account manager) {
		ProductCategory product = new ProductCategory();
		product.setManager(manager);
		product.setName("Good Product");
		product.setVersion("1.0");
		return bugTrackerDao.createProductCategory(product);
	}

	public Account accountManagerStub() {
		Account account = bugTrackerDao.getAccountByScreenName("BigBoss");
		if (account != null) {
			return account;
		}
		account = new Account();
		account.setPassword("123");
		account.setScreenName("BigBoss");
		account.setEntitlements(Collections.singleton(Entitlement.MANAGER));
		return bugTrackerDao.createAccount(account);
	}
}
