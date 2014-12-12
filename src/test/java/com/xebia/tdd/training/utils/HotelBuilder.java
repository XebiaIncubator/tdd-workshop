package com.xebia.tdd.training.utils;

import java.math.BigDecimal;

import com.xebia.tdd.training.model.Address;
import com.xebia.tdd.training.model.Hotel;
import com.xebia.tdd.training.model.Rates;

public class HotelBuilder {

    private static final Address default_address = new Address("1111 1st St", "Santa Monica", "CA", "90403", "USA");
    private static final Rates default_rates = new Rates(new BigDecimal(110), new BigDecimal(80), new BigDecimal(90), new BigDecimal(80));
    private static final String default_name = "Lakewood HOTELS";
    private static final int default_rating = 3;
    private static final Long default_id = 1001L;

    private Long id = default_id;
    private String name = default_name;
    private Address address = default_address;
    private int rating = default_rating;
    private Rates rates = default_rates;

    private HotelBuilder() {
    }

    public static HotelBuilder aHotel() {
        return new HotelBuilder();
    }

    public HotelBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public HotelBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public HotelBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public HotelBuilder withRates(Rates rates) {
        this.rates = rates;
        return this;
    }

    public HotelBuilder withRating(int rating) {
        this.rating = rating;
        return this;
    }

    public Hotel build() {
        return new Hotel(id, name, address, rating, rates);
    }

}
