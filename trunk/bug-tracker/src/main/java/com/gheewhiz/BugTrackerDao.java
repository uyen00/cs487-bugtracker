package com.gheewhiz;

import java.util.Set;


public interface BugTrackerDao {
	public Account createAccount(Account account);

	public Account getAccount(Integer accountId);

	public Account getAccountByScreenName(String screenName);
	
	public Set<Account> getAccountsByEntitlement(Entitlement entitlement);
	
	public void removeAccount(Account account);

	public void updateAccount(Account account);
	
	public Bug createBug(Bug bug);
	
	public void updateBug(Bug bug);
	
	public Bug getBug(Integer bugId);
	
	public Comment createComment(Comment comment);
	
	public void updateComment(Comment comment);
	
	public ProductCategory createProductCategory(ProductCategory product);
	
	public void updateProductCategory(ProductCategory product);
	
	public ProductCategory getProductCategory(Integer productId);
	
	public Set<ProductCategory> getAllProducts();
	
	public Set<Bug> getBugs(Integer productId);
	
	public Set<Comment> getComments(Integer bugId);
	
	public void addQAToProduct(Integer productId, Integer accountId);
	
	public void addDeveloperToProduct(Integer productId, Integer accountId);
}
