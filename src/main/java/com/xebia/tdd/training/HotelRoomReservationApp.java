package com.xebia.tdd.training;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xebia.tdd.training.model.CustomerType;
import com.xebia.tdd.training.model.Hotel;
import com.xebia.tdd.training.model.SearchResult;
import com.xebia.tdd.training.services.LowestPriceFinderService;

/**
 * <p>
 * Program to help an online customer find the cheapest hotel
 * </p>
 * 
 * @author Anand Swarup V
 */
@Component
public class HotelRoomReservationApp {
    
    @Autowired
    LowestPriceFinderService lowestPriceFinderService;
    
    /**
     * Main method to find the cheapest {@link Hotel} for the given dates and {@link CustomerType}
     * 
     * @param arguments
     */
    public String findHotel(List<Date> dates, CustomerType customerType) {
        SearchResult lowestPriceHotelForGivenDates = lowestPriceFinderService.getLowestPriceHotelForGivenDates(dates, customerType);
        return lowestPriceHotelForGivenDates.getHotel().getName();
    }

}
