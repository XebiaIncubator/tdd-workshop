package com.xebia.tdd.training.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private Connection connection;

	public DBUtil(Connection connection) {
		this.connection = connection;
	}

	public void init() throws SQLException {
		createTables();
		createFunctions();
	}

	private void createTables() throws SQLException {
		createTableHotel();
		createTableAddress();
		createTableRates();
	}

	private void createFunctions() throws SQLException {
		createFunctionGetHotelNameFromId();
		createFunctionGetHotelFromId();
	}

	private void createFunctionGetHotelNameFromId() throws SQLException {
		Statement statement = connection.createStatement();
		statement
				.execute("CREATE ALIAS GET_HOTEL_NAME FOR "
						+ "\"com.xebia.tdd.training.hotelreservation.dao.Functions.getHotelNameFromId\" ");
		statement.close();   
	}

	private void createFunctionGetHotelFromId() throws SQLException {
		Statement statement = connection.createStatement();
		statement
				.execute("CREATE ALIAS GET_HOTEL FOR "
						+ "\"com.xebia.tdd.training.hotelreservation.dao.Functions.getHotel\" ");
		statement.close();
	}

	private void createTableRates() throws SQLException {
		Statement statement = connection.createStatement();
		statement
				.execute("CREATE TABLE RATES ("
						+ "HOTEL_ID BIGINT NOT NULL, "
						+ "WEEKDAYRATES BIGINT, "
						+ "WEEKDAYRATESFORREWARDSMEMBERS BIGINT, "
						+ "WEEKENDRATES BIGINT, "
						+ "WEEKENDRATESFORREWARDSMEMBERS BIGINT, "
						+ "CONSTRAINT HOTEL_ID_ROW_FK FOREIGN KEY (HOTEL_ID) REFERENCES HOTEL (ID)"
						+ ")");
		statement.close();
	}

	private void createTableAddress() throws SQLException {
		Statement statement = connection.createStatement();
		statement
				.execute("CREATE TABLE ADDRESS ("
						+ "HOTEL_ID BIGINT, "
						+ "ADDRESSLINE VARCHAR, "
						+ "CITY VARCHAR, "
						+ "STATE VARCHAR, "
						+ "ZIP VARCHAR, "
						+ "COUNTRY VARCHAR, "
						+ "CONSTRAINT ADDRESS_ROW_FK FOREIGN KEY (HOTEL_ID) REFERENCES HOTEL (ID)"
						+ ")");
		statement.close();
	}

	private void createTableHotel() throws SQLException {
		Statement statement = connection.createStatement();
		statement.execute("CREATE TABLE HOTEL (" + "ID BIGINT NOT NULL, "
				+ "NAME CHARACTER VARYING(36) NOT NULL, " + "RATING BIGINT,  "
				+ "CONSTRAINT PRIMARY_KEY_HOTEL PRIMARY KEY (ID )" + ")");
		statement.close();
	}

	public void populateDefaultData() throws SQLException {
		initializeHotel();
		initializeAddress();
		initializeRates();
	}

	private void initializeRates() throws SQLException {
		Statement statement = connection.createStatement();
		statement.execute("INSERT INTO RATES VALUES (1001, 110, 80, 90, 80)");
		statement.execute("INSERT INTO RATES VALUES (1002, 160, 110, 60, 50)");
		statement.execute("INSERT INTO RATES VALUES (1003, 220, 100, 150, 40)");
		statement.close();
	}

	private void initializeAddress() throws SQLException {
		Statement statement = connection.createStatement();
		statement
				.execute("INSERT INTO ADDRESS VALUES (1001, '1111 1st St','Santa Monica','CA','90403','USA')");
		statement
				.execute("INSERT INTO ADDRESS VALUES (1002, '1112 2nd St','Santa Monica','CA','90403','USA')");
		statement
				.execute("INSERT INTO ADDRESS VALUES (1003, '1113 3rd St','Santa Monica','CA','90403','USA')");
		statement.close();
	}

	private void initializeHotel() throws SQLException {
		Statement statement = connection.createStatement();
		statement
				.execute("INSERT INTO HOTEL VALUES (1001, 'Lakewood HOTELS', 3)");
		statement
				.execute("INSERT INTO HOTEL VALUES (1002, 'Bridgewood HOTELS', 4)");
		statement
				.execute("INSERT INTO HOTEL VALUES (1003, 'Ridgewood HOTELS', 5)");
		statement.close();
	}

	public void cleanup() throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("drop table rates");
		statement.executeUpdate("drop table address");
		statement.executeUpdate("drop table hotel");
		statement.executeUpdate("drop alias GET_HOTEL_NAME");
		statement.executeUpdate("drop alias GET_HOTEL");
		statement.close();
	}
}
