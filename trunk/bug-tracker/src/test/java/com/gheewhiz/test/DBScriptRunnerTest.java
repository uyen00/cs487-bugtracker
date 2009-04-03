package com.gheewhiz.test;

import java.io.FileInputStream;

import javax.sql.DataSource;

import org.apache.derby.tools.ij;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DBScriptRunnerTest {

	private DataSource bugTrackerDataSource;

	public void setBugTrackerDataSource(DataSource bugTrackerDataSource) {
		this.bugTrackerDataSource = bugTrackerDataSource;
	}

	@Test(groups = { "derby" })
	public void testRunScript() throws Exception {
		FileInputStream fin = new FileInputStream("src/sql/create.sql");
		ij.runScript(bugTrackerDataSource.getConnection(), fin, "US-ASCII",
				System.out, "US-ASCII");
	}

	@BeforeClass(alwaysRun = true)
	public void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:bug-tracker-datasource.xml" });
		setBugTrackerDataSource((DataSource) context
				.getBean("bugTrackerDataSource"));
	}
}
