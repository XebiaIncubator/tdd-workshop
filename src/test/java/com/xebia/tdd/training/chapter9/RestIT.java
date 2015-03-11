package com.xebia.tdd.training.chapter9;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class RestIT extends BaseSmokeTest {

	 @Test
	public void testGetDummyHotel() throws Exception {
		expect().statusCode(200)
				.body("id", equalTo(1), "name", equalTo(""), "rating",
						equalTo(3)).when().get("/rest/hotel/dummy");
	}

	 @Test
	public void testGetHotel() throws Exception {
		Response response = get("/rest/hotel/1001");
		assertEquals(200, response.getStatusCode());
		JsonPath jsonPath = new JsonPath(response.asString());
		assertEquals("Lakewood HOTELS", jsonPath.get("name"));
		assertEquals(1001, jsonPath.get("id"));
		assertEquals(3, jsonPath.get("rating"));
		assertEquals("1111 1st St", jsonPath.get("address.addressLine"));
		assertEquals("Santa Monica", jsonPath.get("address.city"));
		assertEquals("CA", jsonPath.get("address.state"));
		assertEquals("90403", jsonPath.get("address.zip"));
		assertEquals("USA", jsonPath.get("address.country"));
		assertEquals(110, jsonPath.get("rates.weekdayRates"));
		assertEquals(80, jsonPath.get("rates.weekdayRatesForRewardsMembers"));
		assertEquals(90, jsonPath.get("rates.weekendRates"));
		assertEquals(80, jsonPath.get("rates.weekendRatesForRewardsMembers"));
	}

	@Test
	public void testGetHotels() throws Exception {
		Response response = get("/rest/hotels");
		assertEquals(200, response.getStatusCode());
		JsonPath jsonPath = new JsonPath(response.asString());
		List<String> hotels = Arrays.asList("Lakewood HOTELS",
				"Bridgewood HOTELS", "Ridgewood HOTELS");
		assertEquals(hotels, jsonPath.get("name"));
	}
}
