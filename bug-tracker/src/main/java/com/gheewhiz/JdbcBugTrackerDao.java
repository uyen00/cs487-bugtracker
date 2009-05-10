package com.gheewhiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class JdbcBugTrackerDao implements BugTrackerDao {
	public void createBug(Bug bug) {

	}

	public void createComment(Comment comment) {
		// TODO Auto-generated method stub
		
	}

	public void createProductCategory(ProductCategory product) {
		// TODO Auto-generated method stub
		
	}

	public Set<ProductCategory> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	public Bug getBug(Integer bugId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Bug> getBugs(Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void getProductCategory(ProductCategory product) {
		// TODO Auto-generated method stub
		
	}

	public void updateBug(Bug bug) {
		// TODO Auto-generated method stub
		
	}

	public void updateComment(Comment comment) {
		// TODO Auto-generated method stub
		
	}

	public void updateProductCategory(ProductCategory product) {
		// TODO Auto-generated method stub
		
	}

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Account createAccount(Account account) {
		final Account dbAccount = new Account();
		dbAccount.setPassword(account.getPassword());
		dbAccount.setScreenName(account.getScreenName());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con
						.prepareStatement(
								"insert into Account (password,  screen_name) values (?, ?)",
								Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, dbAccount.getPassword());
				ps.setString(2, dbAccount.getScreenName());
				return ps;
			}
		}, keyHolder);
		Integer accountId = keyHolder.getKey().intValue();
		dbAccount.setAccountId(accountId);
		updateAccountEntitlements(accountId, account.getEntitlements());
		dbAccount.setEntitlements(account.getEntitlements());
		return dbAccount;
	}

	public Account getAccount(Integer accountId) {
		final Account account = new Account();
		jdbcTemplate.query("select * from account where account_id = ?",
				new Object[] { accountId }, new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
						Integer accountId = rs.getInt("account_id");
						account.setAccountId(accountId);
						account.setPassword(rs.getString("password"));
						account
								.setEntitlements(getAccountEntitlements(accountId));
						account.setScreenName(rs.getString("screen_name"));
					}
				});
		if (accountId.equals(account.getAccountId())) {
			return account;
		}
		return null;
	}

	public Account getAccountByScreenName(String screenName) {
		final Account account = new Account();
		jdbcTemplate.query(
				"select * from account where lower(screen_name) = lower(?)",
				new Object[] { screenName }, new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
						Integer accountId = rs.getInt("account_id");
						account.setAccountId(accountId);
						account.setPassword(rs.getString("password"));
						account
								.setEntitlements(getAccountEntitlements(accountId));
						account.setScreenName(rs.getString("screen_name"));
					}
				});
		if (screenName.equalsIgnoreCase(account.getScreenName())) {
			return account;
		}
		return null;
	}

	public void removeAccount(Account account) {
		jdbcTemplate.update("delete from AccountEntitlement where account_id = ?",
				new Object[] { account.getAccountId() });
		jdbcTemplate.update("delete from Account where account_id = ?",
				new Object[] { account.getAccountId() });
	}

	public void updateAccount(Account account) {
		jdbcTemplate
				.update(
						"update Account set password =?,  screen_name = ? where account_id = ?",
						new Object[] { account.getPassword(),
								account.getScreenName(), account.getAccountId() });
		updateAccountEntitlements(account.getAccountId(), account
				.getEntitlements());
	}

	private void updateAccountEntitlements(int account_id,
			Set<Entitlement> entitlements) {
		jdbcTemplate.update(
				"delete from AccountEntitlement where account_id = ?",
				new Object[] { account_id });
		for (Entitlement entitlement : entitlements) {
			jdbcTemplate
					.update(
							"insert into AccountEntitlement (account_id, entitlement_id) "
									+ "select ? as account_id, entitlement_id from Entitlement where "
									+ "entitlement_type = ?", new Object[] {
									account_id, entitlement.getType() });
		}
	}

	private Set<Entitlement> getAccountEntitlements(int accountId) {
		final Set<Entitlement> entitlements = new HashSet<Entitlement>();
		jdbcTemplate
				.query(
						"select * from Entitlement where entitlement_id in "
								+ "(select entitlement_id from AccountEntitlement where account_id = ?)",
						new Object[] { accountId }, new RowCallbackHandler() {
							public void processRow(ResultSet rs)
									throws SQLException {
								Entitlement entitlement = new Entitlement();
								entitlement.setEntitlementId(rs
										.getInt("entitlement_id"));
								entitlement.setType(rs
										.getString("entitlement_type"));
								entitlements.add(entitlement);
							}
						});
		return entitlements;
	}
}
