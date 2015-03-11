package com.xebia.tdd.training.chapter3;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CitySortTest {

	@Mock
	ConfigurationsDao configurationsDao;
	
	@InjectMocks
    CitySort citySort = new CitySort();
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldSortCitiesBasedOnNameInASCOrder() {
    	Mockito.when(configurationsDao.getSortOrder()).thenReturn(CitySortOrder.ASC);
    	List<String> sortedCities = citySort.sort(Arrays.asList("New York","London","Delhi","Paris"));
    	Assert.assertThat(sortedCities, Matchers.contains("Delhi","London","New York","Paris"));
    	Mockito.verify(configurationsDao).getSortOrder();
    }

    @Test
    public void shouldSortCitiesBasedOnNameInDESCOrder() {
    	Mockito.when(configurationsDao.getSortOrder()).thenReturn(CitySortOrder.DESC);
    	List<String> sortedCities = citySort.sort(Arrays.asList("New York","London","Delhi","Paris"));
    	Assert.assertThat(sortedCities, Matchers.contains("Paris","New York","London","Delhi"));
    	Mockito.verify(configurationsDao).getSortOrder();
    	
    }

}
