package com.xebia.tdd.training.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import com.xebia.tdd.training.chapter2.CalendarUtil;
import com.xebia.tdd.training.model.Hotel;
import com.xebia.tdd.training.model.Rates;
import com.xebia.tdd.training.model.SearchResult;

/**
 * <p>
 * A task that calculates for total Booking Price for given dates, {@link Hotel}
 * and Customer Type
 * </p>
 * 
 * @author Anand Swarup V
 */
public class PriceCalculator implements Callable<SearchResult>{
    
    private final Hotel hotel;
    private List<Date> dates;
    private boolean rewardsCustomer;
    
    /**
     * Constructor which takes {@link Hotel}
     * 
     * @param hotel
     */
    public PriceCalculator(Hotel hotel) {
        this.hotel = hotel;
    }

    public SearchResult call() throws Exception {
        if (dates == null){
            throw new RuntimeException("Set dates for finding price");
        }
        BigDecimal totalBookingPrice = calculateBookingPrice(dates);
        return new SearchResult(hotel, totalBookingPrice);
    }

    /**
     * Calculate booking price for given dates
     * 
     * @param dates
     * @return
     */
    private BigDecimal calculateBookingPrice(List<Date> dates) {
        BigDecimal totalBookingPrice;
        Rates rates = hotel.getRates();
        if (rewardsCustomer) {
            totalBookingPrice = getTotalBookingPriceForRewardsCustomers(rates, dates);
        } else {
            totalBookingPrice = getTotalBookingPriceForRegularCustomers(rates, dates);
        }
        return totalBookingPrice;
    }

    private BigDecimal getTotalBookingPriceForRegularCustomers(Rates rates, List<Date> dates) {
        BigDecimal totalBookingPrice = new BigDecimal(0);
        BigDecimal priceForTheDate;
        for (Date date : dates) {
            if (CalendarUtil.isWeekend(date)) {
                priceForTheDate = rates.getWeekendRates();
            } else {
                priceForTheDate = rates.getWeekdayRates();
            }
            totalBookingPrice = totalBookingPrice.add(priceForTheDate);
        }
        return totalBookingPrice;
    }

    private BigDecimal getTotalBookingPriceForRewardsCustomers(Rates rates, List<Date> dates) {
        BigDecimal totalBookingPrice = new BigDecimal(0);
        BigDecimal priceForTheDate;
        for (Date date : dates) {
            if (CalendarUtil.isWeekend(date)) {
                priceForTheDate = rates.getWeekendRatesForRewardsMembers();
            } else {
                priceForTheDate = rates.getWeekdayRatesForRewardsMembers();
            }
            totalBookingPrice = totalBookingPrice.add(priceForTheDate);
        }
        return totalBookingPrice;
    }

    /**
     * Set dates for which price needs to be calculated
     * 
     * @param dates
     */
    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    /**
     * Set if the Customer is a rewards Customer or not
     * 
     * @param rewardsCustomer
     */
    public void setRewardsCustomer(boolean rewardsCustomer) {
        this.rewardsCustomer = rewardsCustomer;
    }
}
