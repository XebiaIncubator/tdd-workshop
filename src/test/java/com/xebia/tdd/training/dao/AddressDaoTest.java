package com.xebia.tdd.training.dao;

import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import com.xebia.tdd.training.model.Address;
import com.xebia.tdd.training.utils.ConnectionManager;

@Ignore
public class AddressDaoTest extends BaseDaoTest {

    private static AddressDao addressDao;
    private static ConnectionManager connectionManager = Mockito.mock(ConnectionManager.class);

    private static final Long Hotel1_Id = 1001L;
    private static final String Hotel1_Name = "Lakewood HOTELS";
    private static final String Hotel1_Rating = "3";
    
    private static final String Address1_AddressLine = "1111 1st St";
    private static final String Address1_City = "Santa Monica";
    private static final String Address1_State = "CA";
    private static final String Address1_Zip = "90403";
    private static final String Address1_Country = "USA";

    @Before
    public void setUp() throws Exception {
        createTestData();
        createMocks();
        addressDao = new AddressDao(connectionManager);
    }

    @Test
    public void testGetAddressForHotelId() throws Exception {
        Address expectedAddress = new Address(Address1_AddressLine, Address1_City,
                Address1_State, Address1_Zip, Address1_Country);
        Address actualAddress = addressDao.getAddressForHotelId(Hotel1_Id);
        Assert.assertEquals(expectedAddress, actualAddress);
    }

    private void createMocks() {
        Mockito.when(connectionManager.getConnection()).thenReturn(connection);
    }

    @After
    public void cleanUp() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("delete from address");
        statement.close();
    }

    private void createTestData() throws SQLException {
        initializeHotel();
        initializeAddress();
    }

    private void initializeAddress() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO ADDRESS VALUES (" + Hotel1_Id + ", '"
                + Address1_AddressLine + "','" + Address1_City + "','" + Address1_State + "','"
                + Address1_Zip + "','" + Address1_Country + "')");
        statement.close();
    }
    
    private void initializeHotel() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO HOTEL VALUES (" + Hotel1_Id + ", '" + Hotel1_Name +
                "'," + Hotel1_Rating + " )");
        statement.close();
    }    

}
