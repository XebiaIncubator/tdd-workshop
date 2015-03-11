package com.xebia.tdd.training.chapter1;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

public class UnitTestSideEffects {

	private static CacheService cacheService ;
	
	@BeforeClass
	public static void init(){
		cacheService = new CacheService();
	}
	
	@Test
	public void shouldReturnNullValueFromCacheIfNotPresent(){
		Assert.assertTrue(null == cacheService.get("Key1"));
		
	}
	
	@Test
	public void shouldReturnBlankValueFromCacheIfSettingsUpdated(){
		cacheService.setReturnBlankForNull(true);
		
		Assert.assertTrue("".equals(cacheService.get("Key1")));
		
	}
	
}
	