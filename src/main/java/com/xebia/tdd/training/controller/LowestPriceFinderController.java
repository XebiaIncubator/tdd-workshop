package com.xebia.tdd.training.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xebia.tdd.training.chapter2.CalendarUtil;
import com.xebia.tdd.training.model.Address;
import com.xebia.tdd.training.model.CustomerType;
import com.xebia.tdd.training.model.Hotel;
import com.xebia.tdd.training.model.Rates;
import com.xebia.tdd.training.model.SearchResult;
import com.xebia.tdd.training.services.LowestPriceFinderService;
import com.xebia.tdd.training.utils.RESTConstants;

@Controller
public class LowestPriceFinderController {

    private static final Logger logger = LoggerFactory.getLogger(LowestPriceFinderController.class);

    @Autowired
    LowestPriceFinderService lowestPriceFinderService;

    @RequestMapping(value = RESTConstants.DUMMY_HOTEL, method = RequestMethod.GET)
    public @ResponseBody Hotel getDummyHotel() {
        logger.info("Start getDummyHotel");
        Address address = new Address("addressLine", "city", "state", "zip", "country");
        Rates rates = new Rates(new BigDecimal(100), new BigDecimal(100), new BigDecimal(100), new BigDecimal(100));
        Hotel dummyHotel = new Hotel((long) 1, "", address, 3, rates);
        return dummyHotel;
    }

    @RequestMapping(value = RESTConstants.GET_HOTEL, method = RequestMethod.GET)
    public @ResponseBody Hotel getHotel(@PathVariable("id") long hotelId) {
        logger.info("Start getHotel. ID=" + hotelId);
        return lowestPriceFinderService.getHotel(hotelId);
    }

    @RequestMapping(value = RESTConstants.GET_ALL_HOTELS, method = RequestMethod.GET)
    public @ResponseBody List<Hotel> getHotels() {
        logger.info("Start getAllHotels");
        return lowestPriceFinderService.getHotels();
    }

    @RequestMapping(value = RESTConstants.GET_HOTEL_FOR_GIVEN_DATES_AND_CUSTOMER_TYPE, method = RequestMethod.GET)
    public @ResponseBody SearchResult getHotelForGivenDatesAndCustomerType(
            @PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate,
            @PathVariable("customerType") String customerType) throws ParseException {
        logger.info("Start getHotelForGivenDatesAndCustomerType for " + fromDate + ", " + toDate + ", " + customerType);
        List<Date> dates = CalendarUtil.getDatesFromStringOfDates(fromDate + "," + toDate);
        if (customerType.equalsIgnoreCase(CustomerType.REGULAR.toString()))
            return lowestPriceFinderService.getLowestPriceHotelForGivenDates(dates, CustomerType.REGULAR);
        else
            return lowestPriceFinderService.getLowestPriceHotelForGivenDates(dates, CustomerType.REWARDS);
    }

}
