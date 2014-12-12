package com.xebia.tdd.training.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.h2.tools.SimpleResultSet;

import com.xebia.tdd.training.model.Address;
import com.xebia.tdd.training.model.Hotel;
import com.xebia.tdd.training.model.Rates;

public class Functions {

    protected static Connection connection;

    private static void setConnection() {
        String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=PostgreSQL;TRACE_LEVEL_SYSTEM_OUT=1";
        String driver = "org.h2.Driver";
        String userName = "sa";
        String password = "password";
        try {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url, userName, password);
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private static final String GET_HOTEL = "SELECT ID, NAME, RATING, ADDRESSLINE, CITY, STATE, ZIP, COUNTRY, "
            + "WEEKDAYRATES, WEEKDAYRATESFORREWARDSMEMBERS, WEEKENDRATES, WEEKENDRATESFORREWARDSMEMBERS "
            + "FROM HOTEL H, ADDRESS A, RATES R WHERE H.ID = A.HOTEL_ID AND H.ID = R.HOTEL_ID AND H.ID = ?";

    public static String getHotelNameFromId(Long hotelId) {
        Hotel hotel = null;
        try {
            setConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(GET_HOTEL);
            prepareStatement.setLong(1, hotelId);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Address address = getAddressFromResultSet(resultSet);
                Rates rates = getRatesFromResultSet(resultSet);
                String name = resultSet.getString("name");
                int rating = resultSet.getInt("rating");
                hotel = new Hotel(hotelId, name, address, rating, rates);
            }
            resultSet.close();
            prepareStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return hotel.getName();
    }

    public static ResultSet getHotel(Long hotelId) {
        SimpleResultSet simpleResultSet = null;
        try {
            setConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(GET_HOTEL);
            prepareStatement.setLong(1, hotelId);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                simpleResultSet = new SimpleResultSet();
                simpleResultSet.addColumn("name", Types.VARCHAR, 255, 0);
                simpleResultSet.addColumn("rating", Types.INTEGER, 10, 0);
                simpleResultSet.addRow(resultSet.getString("name"), resultSet.getInt("rating"));
            }
            resultSet.close();
            prepareStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return simpleResultSet;
    }

    private static Rates getRatesFromResultSet(ResultSet resultSet) throws SQLException {
        BigDecimal weekdayRates = resultSet.getBigDecimal("weekdayrates");
        BigDecimal weekdayRatesForRewardsMembers = resultSet.getBigDecimal("weekdayratesforrewardsmembers");
        BigDecimal weekendRates = resultSet.getBigDecimal("weekendrates");
        BigDecimal weekendRatesForRewardsMembers = resultSet.getBigDecimal("weekendratesforrewardsmembers");
        Rates rates = new Rates(weekdayRates, weekdayRatesForRewardsMembers, weekendRates, weekendRatesForRewardsMembers);
        return rates;
    }

    private static Address getAddressFromResultSet(ResultSet resultSet) throws SQLException {
        String addressLine = resultSet.getString("addressline");
        String city = resultSet.getString("city");
        String state = resultSet.getString("state");
        String zip = resultSet.getString("zip");
        String country = resultSet.getString("country");
        Address address = new Address(addressLine, city, state, zip, country);
        return address;
    }

}
