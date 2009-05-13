package com.gheewhiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
	public Bug createBug(Bug bug) {
		final Bug dbBug = new Bug();
		dbBug.setState(bug.getState());
		dbBug.setProductId(bug.getProductId());
		dbBug.setResolution(bug.getResolution());
		dbBug.setOpened(bug.getOpened());
		dbBug.setSteps(bug.getSteps());
		dbBug.setShortdesc(bug.getShortdesc());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con
						.prepareStatement(
								"insert into Bug (state, product_id,  resolution, open_date, steps, shortdesc) values (?, ?, ?, ?, ?, ?)",
								Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, dbBug.getState());
				ps.setInt(2, dbBug.getProductId());
				ps.setString(3, dbBug.getResolution());
				Timestamp ts = new Timestamp(dbBug.getOpened().getTime());
				ps.setTimestamp(4, ts);
				ps.setString(5, dbBug.getSteps());
				ps.setString(6, dbBug.getShortdesc());
				
				return ps;
			}
		}, keyHolder);
		Integer bugId = keyHolder.getKey().intValue();
		dbBug.setBugId(bugId);
		return dbBug;
	}

	public Comment createComment(Comment comment) {
		final Comment dbComment = new Comment();
		dbComment.setBugId(comment.getBugId());
		dbComment.setComment(comment.getComment());
		dbComment.setCommenter(comment.getCommenter());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con
						.prepareStatement(
								"insert into Comment (bug_id, comment, commenter_id) values (?, ?, ?)",
								Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, dbComment.getBugId());
				ps.setString(2, dbComment.getComment());
				ps.setInt(3, dbComment.getCommenter().getAccountId());
				return ps;
			}
		}, keyHolder);
		Integer commentId = keyHolder.getKey().intValue();
		dbComment.setCommentId(commentId);
		return dbComment;
	}

	public ProductCategory createProductCategory(ProductCategory product) {
		final ProductCategory dbProduct = new ProductCategory();
		dbProduct.setName(product.getName());
		dbProduct.setManager(product.getManager());
		dbProduct.setVersion(product.getVersion());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con
						.prepareStatement(
								"insert into Comment (manager_id, version, name) values (?, ?, ?)",
								Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, dbProduct.getManager().getAccountId());
				ps.setString(2, dbProduct.getVersion());
				ps.setString(3, dbProduct.getName());
				return ps;
			}
		}, keyHolder);
		Integer productCategoryId = keyHolder.getKey().intValue();
		dbProduct.setProductCategoryId(productCategoryId);
		return dbProduct;
	}

	public Set<ProductCategory> getAllProducts() {
		final Set<ProductCategory> products = new HashSet<ProductCategory>();
		jdbcTemplate.query("select * from Product", new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
						ProductCategory product = new ProductCategory();
						product.setManager(getAccount(rs.getInt("manager_id")));
						product.setVersion(rs.getString("version"));
						product.setProductCategoryId(rs.getInt("product_id"));
						product.setName(rs.getString("name"));
						//TODO: NEED TO GET DEVELOPERS AND QA
						products.add(product);
					}
				});
		return products;
	}

	public Bug getBug(Integer bugId) {
		final Bug bug = new Bug();
		jdbcTemplate.query("select * from bug where bug_id = ?",
				new Object[] { bugId }, new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
						Integer bugId = rs.getInt("bug_id");
						bug.setBugId(bugId);
						bug.setState(rs.getString("state"));
						bug.setProductId(rs.getInt("product_id"));
						bug.setResolution(rs.getString("resolution"));
					    bug.setOpened(rs.getDate("open_date"));
					    bug.setSteps(rs.getString("steps"));
					    bug.setShortdesc(rs.getString("shortdesc"));
					}
				});
		if (bugId.equals(bug.getBugId())) {			
			bug.setComments(getComments(bugId));
			return bug;
		}
		return null;
	}

	public Set<Bug> getBugs(Integer productId) {
		final Set<Bug> bugs = new HashSet<Bug>();
		jdbcTemplate.query("select * from bug where product_id = ?",
				new Object[] { productId }, new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
						Bug bug = new Bug();
						bug.setBugId(rs.getInt("bug_id"));
						bug.setState(rs.getString("state"));
						bug.setProductId(rs.getInt("product_id"));
						bug.setResolution(rs.getString("resolution"));
					    bug.setOpened(rs.getDate("open_date"));
					    bug.setSteps(rs.getString("steps"));
					    bug.setShortdesc(rs.getString("shortdesc"));
					    bugs.add(bug);
					}
				});
		return bugs;
	}

	public ProductCategory getProductCategory(Integer productId) {
		final ProductCategory product = new ProductCategory();
		jdbcTemplate.query("select * from Product where product_id = ?",
				new Object[] { productId }, new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
						ProductCategory product = new ProductCategory();
						product.setManager(getAccount(rs.getInt("manager_id")));
						product.setVersion(rs.getString("version"));
						product.setProductCategoryId(rs.getInt("product_id"));
						product.setName(rs.getString("name"));
						//TODO: NEED TO GET DEVELOPERS AND QA
					}
				});
		if (productId.equals(product.getProductCategoryId())) {
			return product;
		}
		return null;
	}

	public void updateBug(Bug bug) {
		jdbcTemplate
				.update(
						"update Bug set state =?,  product_id = ?, resolution = ?, open_date = ?, state = ?, shortdesc =? where bug_id = ?",
						new Object[] { bug.getState(),
					    bug.getProductId(), bug.getResolution(), bug.getOpened(), bug.getSteps(), bug.getShortdesc(), bug.getBugId() });
		//updateComment();
	}

	public void updateComment(Comment comment) {
		jdbcTemplate.update(
				"update Comment set bug_id = ?, comment = ?, account_id = ? where comment_id = ?",
				new Object[] { comment.getBugId(), comment.getComment(),
						comment.getCommenter().getAccountId(), comment.getCommentId() });
	}

	public void updateProductCategory(ProductCategory product) {
		jdbcTemplate
		.update(
				"update Product set manager_id = ?, name = ?, version = ? where account_id = ?",
				new Object[] { product.getManager().getAccountId(), product.getName(),
						product.getVersion(), product.getProductCategoryId() });
	}

	public Set<Comment> getComments(Integer bugId) {
		final Set<Comment> comments = new HashSet<Comment>();
		jdbcTemplate.query("select * from Comment where bud_id = ?", new Object[] { bugId }, 
				new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
						Comment comment = new Comment();
						comment.setBugId(rs.getInt("bug_id"));
						comment.setComment(rs.getString("comment"));
						comment.setCommenter(getAccount(rs.getInt("account_id")));
						comment.setCommentId(rs.getInt("comment_id"));
						comments.add(comment);
					}
				});
		return comments;
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
