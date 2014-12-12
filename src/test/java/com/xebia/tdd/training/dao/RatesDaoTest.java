/**
 * 
 */
package com.xebia.tdd.training.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.xebia.tdd.training.model.Rates;
import com.xebia.tdd.training.utils.ConnectionManager;

@RunWith(PowerMockRunner.class)
@Ignore
public class RatesDaoTest extends BaseDaoTest {

    private static final String Hotel1_Id = "1001";
    private static final String Hotel1_Name = "Lakewood HOTELS";
    private static final String Hotel1_Rating = "3";

    private static final BigDecimal Rates1_WeekendRates = new BigDecimal(90);
    private static final BigDecimal Rates1_WeekendRatesForRewardsMembers = new BigDecimal(80);
    private static final BigDecimal Rates1_WeekdayRates = new BigDecimal(110);
    private static final BigDecimal Rates1_WeekdayRatesForRewardsMembers = new BigDecimal(80);

    @Mock
    ConnectionManager connectionManager;

    @InjectMocks
    RatesDao ratesDao = new RatesDao();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        setupMocks();
        createTestData();
    }

    @After
    public void cleanUp() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("delete from rates");
        statement.close();
    }

    @Test
    public void testGetRatesforHotelId() throws Exception {
        Rates ratesforHotelId = ratesDao.getRatesforHotelId((Long) 1001L);
        Assert.assertEquals(Rates1_WeekdayRates, ratesforHotelId.getWeekdayRates());
        Assert.assertEquals(Rates1_WeekdayRatesForRewardsMembers, ratesforHotelId.getWeekdayRatesForRewardsMembers());
        Assert.assertEquals(Rates1_WeekendRates, ratesforHotelId.getWeekendRates());
        Assert.assertEquals(Rates1_WeekendRatesForRewardsMembers, ratesforHotelId.getWeekendRatesForRewardsMembers());
    }

    private void setupMocks() {
        Mockito.when(connectionManager.getConnection()).thenReturn(connection);
    }

    private void createTestData() throws SQLException {
        initializeHotel();
        initializeRates();
    }

    private void initializeRates() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO RATES VALUES (" + Hotel1_Id + "," + Rates1_WeekdayRates + "," +
                Rates1_WeekdayRatesForRewardsMembers + "," + Rates1_WeekendRates + "," +
                Rates1_WeekendRatesForRewardsMembers + ")");
        statement.close();
    }

    private void initializeHotel() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO HOTEL VALUES (" + Hotel1_Id + ", '" + Hotel1_Name +
                "'," + Hotel1_Rating + " )");
        statement.close();
    }

}
