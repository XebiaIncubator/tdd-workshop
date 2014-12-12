package com.xebia.tdd.training.soap;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

@WebService(endpointInterface = "com.xebia.tdd.training.soap.HotelService")
public class HotelServiceImpl implements HotelService {

    private static final Map<Long, String> hotelsMap;

    static
    {
        hotelsMap = new HashMap<Long, String>();
        hotelsMap.put((Long) 1001L, "Lakewood HOTELS");
        hotelsMap.put((Long) 1002L, "Bridgewood HOTELS");
        hotelsMap.put((Long) 1003L, "Ridgewood HOTELS");
    }

    @Override
    public String getHotelName(Long id) {
        String hotelName = hotelsMap.get(id);
        if (hotelName != null)
            return hotelName;
        else
            return "No HOTEL with id: " + id + " found";

    }

}
