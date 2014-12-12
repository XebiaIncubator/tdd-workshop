package com.xebia.tdd.training.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import com.xebia.tdd.training.model.Hotel;
import com.xebia.tdd.training.utils.ConnectionManager;
import com.xebia.tdd.training.utils.HotelBuilder;
import com.xebia.tdd.training.utils.MotherOfAllBuilders;

@Ignore
public class HotelDaoTest extends BaseDaoTest {

    private static HotelDao hotelDao;
    private static RatesDao ratesDao = Mockito.mock(RatesDao.class);
    private static AddressDao addressDao = Mockito.mock(AddressDao.class);
    private static ConnectionManager connectionManager = Mockito.mock(ConnectionManager.class);

    private static final Long Hotel1_Id = 1001L;

    private Hotel testHotel;

    @Before
    public void setup() throws Exception {
        createTestData();
        createMocks();
        hotelDao = new HotelDao(connectionManager, ratesDao, addressDao);
    }

    @After
    public void cleanUp() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("delete from rates");
        statement.executeUpdate("delete from address");
        statement.executeUpdate("delete from hotel");
        statement.close();
    }

    @Test
    public void testGetHotel() throws Exception {
        Hotel hotel = hotelDao.getHotel(Hotel1_Id);
        Assert.assertEquals(testHotel.getId(), hotel.getId());
        Assert.assertEquals(testHotel.getName(), hotel.getName());
        Assert.assertEquals(testHotel.getRating(), hotel.getRating());
        Assert.assertEquals(testHotel.getAddress().getAddressLine(), hotel.getAddress().getAddressLine());
        Assert.assertEquals(testHotel.getAddress().getZip(), hotel.getAddress().getZip());
    }

    @Test
    public void testGetHotels() throws Exception {
        List<Hotel> hotels = hotelDao.getHotels();
        Assert.assertEquals(3, hotels.size());
    }

    @Test
    public void testGetHotelNameUsingStoredProcedure() throws Exception {
        String hotelName = hotelDao.getHotelNameUsingStoredProcedure((Long) 1001L);
        Assert.assertEquals(testHotel.getName(), hotelName);
    }

    @Test
    public void testGetHotelUsingStoredProcedure() throws Exception {
        Hotel hotel = hotelDao.getHotelUsingStoredProcedure((Long) 1001L);
        Assert.assertEquals(testHotel.getName(), hotel.getName());
    }

    @Test
    public void testAddHotel() {
        Hotel hotel = MotherOfAllBuilders.aHotel().withId(5001L).build();
        hotelDao.addHotel(hotel);

    }

    private void createMocks() {
        Mockito.when(connectionManager.getConnection()).thenReturn(connection);
        Mockito.when(addressDao.getAddressForHotelId(Hotel1_Id)).thenReturn(testHotel.getAddress());
    }

    private void createTestData() throws SQLException {
        testHotel = HotelBuilder.aHotel().withId(Hotel1_Id).build();
        initializeHotel(testHotel);
        initializeAddress(testHotel);
        initializeRates(testHotel);
    }

    private void initializeRates(Hotel testHotel) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO RATES VALUES (" + testHotel.getId() + "," + testHotel.getRates().getWeekdayRates() + "," +
                testHotel.getRates().getWeekdayRatesForRewardsMembers() + "," + testHotel.getRates().getWeekendRates() + "," +
                testHotel.getRates().getWeekendRatesForRewardsMembers() + ")");
        statement.close();
    }

    private void initializeAddress(Hotel testHotel) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO ADDRESS VALUES (" + testHotel.getId() + ", '"
                + testHotel.getAddress().getAddressLine() + "','" + testHotel.getAddress().getCity() + "','" + testHotel.getAddress().getState()
                + "','"
                + testHotel.getAddress().getZip() + "','" + testHotel.getAddress().getCountry() + "')");
        statement.close();
    }

    private void initializeHotel(Hotel testHotel) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO HOTEL VALUES (" + testHotel.getId() + ", '" + testHotel.getName() +
                "'," + testHotel.getRating() + " )");
        statement.execute("INSERT INTO HOTEL VALUES (1002, 'Bridgewood HOTELS', 4)");
        statement.execute("INSERT INTO HOTEL VALUES (1003, 'Ridgewood HOTELS', 5)");
        statement.close();
    }
}
