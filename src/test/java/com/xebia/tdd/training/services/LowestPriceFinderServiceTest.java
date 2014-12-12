package com.xebia.tdd.training.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.xebia.tdd.training.dao.HotelDao;
import com.xebia.tdd.training.model.CustomerType;
import com.xebia.tdd.training.model.Hotel;
import com.xebia.tdd.training.model.SearchResult;
import com.xebia.tdd.training.utils.ObjectMother;

@RunWith(MockitoJUnitRunner.class)
public class LowestPriceFinderServiceTest {

    @Mock
    HotelDao hotelDao;

    @InjectMocks
    LowestPriceFinderService lowestPriceFinderService = new LowestPriceFinderService();

    List<Hotel> testHotels;

    @Before
    public void setUp() throws Exception {
        testHotels = ObjectMother.aFewHotels();
        Mockito.when(hotelDao.getHotels()).thenReturn(testHotels);
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testGetLowestPriceHotelForGivenDates() throws Exception {
        Date fromDate = new Date(2014, 9, 15);
        Date toDate = new Date(2014, 9, 17);
        List<Date> dates = new ArrayList<Date>();
        dates.add(fromDate);
        dates.add(toDate);
        SearchResult searchResult = lowestPriceFinderService.getLowestPriceHotelForGivenDates(dates, CustomerType.REGULAR);
        String actualHotelName = searchResult.getHotel().getName();
        String expectedHotelName = "Lakewood HOTELS";
        Assert.assertEquals(expectedHotelName, actualHotelName);
    }

    @Test
    public void testGetHotels() throws Exception {
        List<Hotel> actualHotels = lowestPriceFinderService.getHotels();
        Assert.assertEquals(testHotels, actualHotels);
        Mockito.verify(hotelDao).getHotels();
    }

}
