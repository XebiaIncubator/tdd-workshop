package com.xebia.tdd.training.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * <strong>SearchResult</strong> is the model/entity class that represents the search results
 * containing {@link Hotel} and total booking price for that hotel.
 * </p>
 * 
 * @author Anand Swarup V
 */
public class SearchResult implements Comparable<SearchResult>, Serializable {
    
	private static final long serialVersionUID = 3184012319580077718L;
	private final Hotel hotel;
    private final BigDecimal totalBookingPrice;
    
    public SearchResult(Hotel hotel, BigDecimal totalBookingPrice) {
        this.hotel = hotel;
        this.totalBookingPrice = totalBookingPrice;
    }

    /**
     * @return the hotel
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * @return the totalBookingPrice
     */
    public BigDecimal getTotalBookingPrice() {
        return totalBookingPrice;
    }
    
    @Override
    public String toString() {
        return "Booking Price for " + hotel.getName() + " : " + totalBookingPrice.toString();
    }

    public int compareTo(SearchResult otherSearchResult) {
        int priceComparisonResult = this.totalBookingPrice.compareTo(otherSearchResult.getTotalBookingPrice());
        if (priceComparisonResult < 0) {
            return -1;
        } else if (priceComparisonResult > 0) {
            return 1;
        } else {
            return (this.hotel.getRating() > otherSearchResult.getHotel().getRating()) ? -1 : 1;
        }
    }

}
