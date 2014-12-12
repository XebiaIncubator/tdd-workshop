package com.xebia.tdd.training.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.xebia.tdd.training.dao.AddressDao;
import com.xebia.tdd.training.dao.HotelDao;
import com.xebia.tdd.training.dao.RatesDao;
import com.xebia.tdd.training.model.Address;
import com.xebia.tdd.training.model.Hotel;
import com.xebia.tdd.training.model.Rates;
import com.xebia.tdd.training.services.LowestPriceFinderService;
import com.xebia.tdd.training.utils.ConnectionManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class LowestPriceFinderControllerTest {

    private static final String expectedContent = "{\"id\":1001,\"name\":\"Test HOTELS\",\"address\":{"
            + "\"addressLine\":\"addressLine1\",\"city\":\"city1\",\"state\":\"state1\",\"zip\":\"zip1\",\"country\":"
            + "\"country1\"},\"rating\":3,\"rates\":{\"weekdayRates\":100,\"weekdayRatesForRewardsMembers\":80,"
            + "\"weekendRates\":110,\"weekendRatesForRewardsMembers\":90}}";

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetDummyHotel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/hotel/dummy")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetHotel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/hotel/1001")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedContent));
    }

    @Test
    public void testGetHotelWithJsonPath() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/hotel/1001")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1001)))
                .andExpect(jsonPath("$.name", is("Test HOTELS")))
                .andExpect(jsonPath("$.rating", is(3)))
                .andExpect(jsonPath("$.address.addressLine", is("addressLine1")))
                .andExpect(jsonPath("$.address.city", is("city1")))
                .andExpect(jsonPath("$.address.state", is("state1")))
                .andExpect(jsonPath("$.address.zip", is("zip1")))
                .andExpect(jsonPath("$.address.country", is("country1")))
                .andExpect(jsonPath("$.rates.weekdayRates", is(100)))
                .andExpect(jsonPath("$.rates.weekdayRatesForRewardsMembers", is(80)))
                .andExpect(jsonPath("$.rates.weekendRates", is(110)))
                .andExpect(jsonPath("$.rates.weekendRatesForRewardsMembers", is(90)));
    }

    @Test
    public void testShouldThrow404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/hotel")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testGetHotels() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/hotels")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].id", is(1001)))
                .andExpect(jsonPath("$.[0].name", is("Test HOTELS")))
                .andExpect(jsonPath("$.[0].rating", is(3)))
                .andExpect(jsonPath("$.[0].address.addressLine", is("addressLine1")))
                .andExpect(jsonPath("$.[0].address.city", is("city1")))
                .andExpect(jsonPath("$.[0].address.state", is("state1")))
                .andExpect(jsonPath("$.[0].address.zip", is("zip1")))
                .andExpect(jsonPath("$.[0].address.country", is("country1")))
                .andExpect(jsonPath("$.[0].rates.weekdayRates", is(100)))
                .andExpect(jsonPath("$.[0].rates.weekdayRatesForRewardsMembers", is(80)))
                .andExpect(jsonPath("$.[0].rates.weekendRates", is(110)))
                .andExpect(jsonPath("$.[0].rates.weekendRatesForRewardsMembers", is(90)))
                .andExpect(jsonPath("$.[1].id", is(1002)))
                .andExpect(jsonPath("$.[2].id", is(1003)));
    }

    @Configuration
    @EnableWebMvc
    public static class TestConfiguration {

        @Bean
        public LowestPriceFinderController lowestPriceFinderController() {
            LowestPriceFinderController LowestPriceFinderController = new LowestPriceFinderController();
            return LowestPriceFinderController;
        }

        @Bean
        public LowestPriceFinderService lowestPriceFinderService() {
            LowestPriceFinderService lowestPriceFinderService = Mockito.mock(LowestPriceFinderService.class);
            ArrayList<Hotel> hotels = getDummyHotels();
            Mockito.when(lowestPriceFinderService.getHotel((Long) 1001L)).thenReturn(hotels.get(0));
            Mockito.when(lowestPriceFinderService.getHotels()).thenReturn(hotels);
            return lowestPriceFinderService;
        }

        @Bean
        public HotelDao hotelDao() {
            HotelDao hotelDao = new HotelDao(connectionManager(), ratesDao(), addressDao());
            return hotelDao;
        }

        @Bean
        public RatesDao ratesDao() {
            RatesDao ratesDao = new RatesDao();
            return ratesDao;
        }

        @Bean
        public AddressDao addressDao() {
            AddressDao addressDao = new AddressDao(connectionManager());
            return addressDao;
        }

        @Bean
        public ConnectionManager connectionManager() {
            ConnectionManager connectionManager = new ConnectionManager();
            return connectionManager;
        }

        private ArrayList<Hotel> getDummyHotels() {
            Address address1 = new Address("addressLine1", "city1", "state1", "zip1", "country1");
            Rates rates1 = new Rates(new BigDecimal(100), new BigDecimal(80), new BigDecimal(110), new BigDecimal(90));
            Hotel hotel1 = new Hotel((long) 1001, "Test HOTELS", address1, 3, rates1);

            Address address2 = new Address("addressLine2", "city2", "state2", "zip2", "country2");
            Rates rates2 = new Rates(new BigDecimal(200), new BigDecimal(180), new BigDecimal(210), new BigDecimal(190));
            Hotel hotel2 = new Hotel((long) 1002, "Test HOTELS", address2, 4, rates2);

            Address address3 = new Address("addressLine3", "city3", "state3", "zip3", "country3");
            Rates rates3 = new Rates(new BigDecimal(300), new BigDecimal(280), new BigDecimal(310), new BigDecimal(290));
            Hotel hotel3 = new Hotel((long) 1003, "Test HOTELS", address3, 5, rates3);

            ArrayList<Hotel> hotels = new ArrayList<Hotel>();
            hotels.add(hotel1);
            hotels.add(hotel2);
            hotels.add(hotel3);
            return hotels;
        }

    }

}
