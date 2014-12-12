package com.xebia.tdd.training.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.xebia.tdd.training.model.Address;
import com.xebia.tdd.training.model.Hotel;
import com.xebia.tdd.training.model.Rates;

public class ObjectMother {

    private static final Address address1 = new Address("1111 1st St", "Santa Monica", "CA", "90403", "USA");
    private static final Rates rates1 = new Rates(new BigDecimal(110), new BigDecimal(80), new BigDecimal(90), new BigDecimal(80));
    private static final Hotel hotel1 = new Hotel(1001L, "Lakewood HOTELS", address1, 3, rates1);
    private static final Address address2 = new Address("1112 1st St", "Santa Monica", "CA", "90403", "USA");
    private static final Rates rates2 = new Rates(new BigDecimal(160), new BigDecimal(110), new BigDecimal(60), new BigDecimal(50));
    private static final Hotel hotel2 = new Hotel(1002L, "Bridgewood HOTELS", address2, 4, rates2);
    private static final Address address3 = new Address("1113 1st St", "Santa Monica", "CA", "90403", "USA");
    private static final Rates rates3 = new Rates(new BigDecimal(220), new BigDecimal(110), new BigDecimal(150), new BigDecimal(40));
    private static final Hotel hotel3 = new Hotel(1003L, "Ridgewood HOTELS", address3, 5, rates3);

    public static Hotel aHotel() {
        return hotel1;
    }

    public static List<Hotel> aFewHotels() {
        List<Hotel> hotels = new ArrayList<Hotel>();
        hotels.add(hotel1);
        hotels.add(hotel2);
        hotels.add(hotel3);
        return hotels;
    }

    public static Address anAddress() {
        return address1;
    }

    public static Rates aRate() {
        return rates1;
    }

}
