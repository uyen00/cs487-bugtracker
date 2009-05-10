package com.gheewhiz;

import java.util.Set;


public interface BugTrackerDao {
	public Account createAccount(Account account);

	public Account getAccount(Integer accountId);

	public Account getAccountByScreenName(String screenName);
	
	public void removeAccount(Account account);

	public void updateAccount(Account account);
	
	public void createBug(Bug bug);
	
	public void updateBug(Bug bug);
	
	public Bug getBug(Integer bugId);
	
	public void createComment(Comment comment);
	
	public void updateComment(Comment comment);
	
	public void createProductCategory(ProductCategory product);
	
	public void updateProductCategory(ProductCategory product);
	
	public void getProductCategory(ProductCategory product);
	
	public Set<ProductCategory> getAllProducts();
	
	public Set<Bug> getBugs(Integer productId);
}
