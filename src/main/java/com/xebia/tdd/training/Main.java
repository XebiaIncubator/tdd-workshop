package com.xebia.tdd.training;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xebia.tdd.training.chapter2.CalendarUtil;
import com.xebia.tdd.training.exceptions.InvalidArgumentsException;
import com.xebia.tdd.training.model.CustomerType;
import com.xebia.tdd.training.utils.Terminal;

public class Main {
	
    private final static Terminal TERMINAL = Terminal.getInstance(Main.class.getName());
    
    /**
     * Main method for the program
     * 
     * @param arguments
     * @throws Exception 
     */
    public static void main(String[] arguments) throws Exception {
        if (arguments.length == 0 || arguments.length > 1) {
            throw new InvalidArgumentsException("Invalid arguments");
        }
        CustomerType customerType = getCustomerTypeFromArguments(arguments[0]);
        List<Date> dates = getDatesFromArguments(arguments[0]);
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        TERMINAL.debug("Application Context Loded successfully");
        HotelRoomReservationApp hotelRoomReservationApp = (HotelRoomReservationApp) applicationContext.getBean("hotelRoomReservationApp");
        String cheapestHotel = hotelRoomReservationApp.findHotel(dates, customerType);
        TERMINAL.out("Lowest booking price available at : " + cheapestHotel);
        applicationContext.close();
    }

    private static List<Date> getDatesFromArguments(String inputArguments) {
        String[] inputStrings = inputArguments.split(":");
        String stringOfDates = inputStrings[1];
        if (invalidDates(stringOfDates)) {
            throw new InvalidArgumentsException("Invalid Date");
        }
        try {
            return CalendarUtil.getDatesFromStringOfDates(stringOfDates);
        } catch (ParseException e) {
            throw new InvalidArgumentsException("Invalid Date");
        }
    }

    private static boolean invalidDates(String stringOfDates) {
        return stringOfDates == null || stringOfDates.isEmpty();
    }

    private static CustomerType getCustomerTypeFromArguments(String inputArguments) {
        String[] inputStrings = inputArguments.split(":");
        String customerType = inputStrings[0];
        if (invalidCustomerType(customerType)) {
            throw new InvalidArgumentsException("Invalid Customer Type");
        }
        return customerType.equalsIgnoreCase(CustomerType.REGULAR.toString()) ? CustomerType.REGULAR : CustomerType.REWARDS;
    }

    private static boolean invalidCustomerType(String customerType) {
        return customerType == null || customerType.isEmpty() ||
                (!(customerType.equalsIgnoreCase(CustomerType.REGULAR.toString()) ||
                customerType.equalsIgnoreCase(CustomerType.REWARDS.toString())));
    }

}
