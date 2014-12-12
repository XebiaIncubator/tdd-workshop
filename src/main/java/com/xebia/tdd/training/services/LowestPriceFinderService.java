package com.xebia.tdd.training.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xebia.tdd.training.dao.HotelDao;
import com.xebia.tdd.training.model.CustomerType;
import com.xebia.tdd.training.model.Hotel;
import com.xebia.tdd.training.model.SearchResult;
import com.xebia.tdd.training.utils.Terminal;

/**
 * <p>
 * Service that finds the Lowest Price for given dates, {@link CustomerType} and
 * {@link Hotel}(s)
 * </p>
 * 
 * @author Anand Swarup V
 */
@Component
public class LowestPriceFinderService {

    private final static Terminal TERMINAL = Terminal.getInstance(LowestPriceFinderService.class.getName());
    private ExecutorService executorService;
    private List<Hotel> hotels;
    
    @Autowired
    HotelDao hotelDao;

    /**
     * Get the best Price for {@link Hotel}(s) registered in the service for
     * given dates.
     * 
     * @param dates
     * @param customerType
     * @return
     */
    public SearchResult getLowestPriceHotelForGivenDates(List<Date> dates, CustomerType customerType) {
    	if (hotels==null) {
    		initializeHotels();
    	}
        List<SearchResult> searchResults = new ArrayList<SearchResult>();
        Set<PriceCalculator> callablePriceCalculators = getCallablePriceCalculators(dates, customerType);
        try {
            List<Future<SearchResult>> futureResults = executorService.invokeAll(callablePriceCalculators);
            for (Future<SearchResult> futureResult : futureResults) {
                SearchResult searchResult = futureResult.get();
                searchResults.add(searchResult);
                TERMINAL.debug(searchResult.toString());
            }
        } catch (ExecutionException e) {
            throw new RuntimeException("Rates for all hotels could not be checked : " + e.getCause());
        } catch (InterruptedException e) {
            throw new RuntimeException("Rates for all hotels could not be checked : " + e.getCause());
        }
        return getBestResult(searchResults);
    }
    
    public Hotel getHotel(long hotelId) {
    	return hotelDao.getHotel(hotelId);
    }

    public List<Hotel> getHotels() {
        return hotelDao.getHotels();
    }

    private void initializeHotels() {
        hotels = hotelDao.getHotels();
        TERMINAL.debug(hotels.size() + " hotels registered in the LowestPriceFinderService");
        executorService = Executors.newFixedThreadPool(hotels.size());
	}

	private SearchResult getBestResult(List<SearchResult> searchResults) {
        Collections.sort(searchResults);
        return searchResults.get(0);
    }

    private Set<PriceCalculator> getCallablePriceCalculators(List<Date> dates, CustomerType customerType) {
        List<PriceCalculator> priceCalculators = setupPriceCalculators(dates, customerType);
        Set<PriceCalculator> callables = new HashSet<PriceCalculator>();
        callables.addAll(priceCalculators);
        return callables;
    }

    private List<PriceCalculator> setupPriceCalculators(List<Date> dates, CustomerType customerType) {
        List<PriceCalculator> priceCalculators = new ArrayList<PriceCalculator>(hotels.size());
        for (Hotel hotel : hotels) {
            PriceCalculator priceCalculator = new PriceCalculator(hotel);
            priceCalculator.setDates(dates);
            priceCalculator.setRewardsCustomer(customerType.equals(CustomerType.REWARDS) ? true : false);
            priceCalculators.add(priceCalculator);
        }
        return priceCalculators;
    }
}
