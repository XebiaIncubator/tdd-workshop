package com.xebia.tdd.training.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.xebia.tdd.training.utils.DBUtil;

public abstract class BaseDaoTest {

	protected static Connection connection;
	private static DBUtil dbUtils;

	/**
	 * sets the system properties to use In Memory Datasource Instead of running
	 * instances of postgres.
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void init() throws Exception {
		try {
			String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=PostgreSQL;TRACE_LEVEL_SYSTEM_OUT=1";
			String driver = "org.h2.Driver";
			String userName = "sa";
			String password = "password";

			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url, userName, password);
			dbUtils = new DBUtil(connection);
			dbUtils.init();

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void cleanUpDB() throws SQLException {
		dbUtils.cleanup();
	}

	public Connection getConnection() {
		return connection;
	}

}
