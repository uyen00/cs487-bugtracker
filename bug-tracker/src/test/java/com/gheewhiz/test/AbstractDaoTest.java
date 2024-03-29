package com.gheewhiz.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gheewhiz.BugTrackerDao;

public class AbstractDaoTest {

	private BugTrackerDao bugTrackerDao;
	
	public void setBugTrackerDao(BugTrackerDao bugTrackerDao) {
		this.bugTrackerDao = bugTrackerDao;
	}

	public BugTrackerDao getBugTrackerDao() {
		return bugTrackerDao;
	}

	protected void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:bug-tracker-datasource.xml",
						"classpath:bug-tracker-app-context.xml" });
		setBugTrackerDao((BugTrackerDao) context.getBean("bugTrackerDao"));
	}

}
