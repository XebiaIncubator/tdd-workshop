package com.xebia.tdd.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xebia.tdd.training.model.Address;
import com.xebia.tdd.training.utils.ConnectionManager;

@Component
public class AddressDao {

    ConnectionManager connectionManager;

    @Autowired
    public AddressDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    private static final String GET_ADDRESS_FOR_HOTELID = "select * from "
            + "address where hotel_id = ?";

    public Address getAddressForHotelId(Long id) {
        Address address = null;
        Connection connection = connectionManager.getConnection();
        if (connection != null) {
            try {
                PreparedStatement prepareStatement = connection.prepareStatement(GET_ADDRESS_FOR_HOTELID);
                prepareStatement.setLong(1, id);
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    String addressLine = resultSet.getString(2);
                    String city = resultSet.getString(3);
                    String state = resultSet.getString(4);
                    String zip = resultSet.getString(5);
                    String country = resultSet.getString(6);
                    address = new Address(addressLine, city, state, zip, country);
                }
                connection.commit();
                resultSet.close();
                prepareStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            System.out.println("No Database Connection");
        }
        return address;
    }

}
