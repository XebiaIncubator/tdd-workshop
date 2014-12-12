package com.xebia.tdd.training.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xebia.tdd.training.model.Rates;
import com.xebia.tdd.training.utils.ConnectionManager;

@Component
public class RatesDao {

    @Autowired
    ConnectionManager connectionManager;

    private static final String GET_RATES_FOR_HOTELID = "select * from "
            + "rates where hotel_id = ?";

    public Rates getRatesforHotelId(Long id) {
        Rates rates = null;
        Connection connection = connectionManager.getConnection();
        if (connection != null) {
            try {
                PreparedStatement prepareStatement = connection.prepareStatement(GET_RATES_FOR_HOTELID);
                prepareStatement.setLong(1, id);
                ResultSet resultSet = prepareStatement.executeQuery();
                while (resultSet.next()) {
                    BigDecimal weekdayRates = resultSet.getBigDecimal(2);
                    BigDecimal weekdayRatesForRewardsMembers = resultSet.getBigDecimal(3);
                    BigDecimal weekendRates = resultSet.getBigDecimal(4);
                    BigDecimal weekendRatesForRewardsMembers = resultSet.getBigDecimal(5);
                    rates = new Rates(weekdayRates, weekdayRatesForRewardsMembers, weekendRates, weekendRatesForRewardsMembers);
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
        return rates;
    }

}
