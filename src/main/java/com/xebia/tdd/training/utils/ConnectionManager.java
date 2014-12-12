package com.xebia.tdd.training.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class ConnectionManager implements DisposableBean {

    private static String url = "jdbc:h2:mem:appdb;DB_CLOSE_DELAY=-1;MODE=PostgreSQL;TRACE_LEVEL_SYSTEM_OUT=1";
    private static String driver = "org.h2.Driver";
    private static String userName = "sa";
    private static String password = "password";

    private static Connection connection;

    @PostConstruct
    private void initConnection() {
        try {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url, userName, password);
            connection.setAutoCommit(true);
            DBUtil dbUtils = new DBUtil(connection);
            dbUtils.init();
            dbUtils.populateDefaultData();
        } catch (Exception e) {
            System.out.println("Unable to establish the connection "
                    + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void destroy() throws Exception {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection Closed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
