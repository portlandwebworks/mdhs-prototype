package com.portlandwebworks.mdhs.utils;

import java.math.BigDecimal;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.opengis.referencing.FactoryException;

/**
 *
 * @author nick
 */
public class ZipCodeRangeFinderTest {

	ZipCodeRangeFinder instance;

	public ZipCodeRangeFinderTest() {
	}

	@Before
	public void setUp() throws FactoryException {
		instance = new ZipCodeRangeFinder();
		instance.addKnownLocation("38601", "Abbeville", 34.489297, -89.47412);
		instance.addKnownLocation("38602", "Arkabutla", 34.686272, -90.106756);
		instance.addKnownLocation("38603", "Ashland", 34.839417, -89.15818);
		instance.addKnownLocation("38606", "Batesville", 34.309659, -89.963);
		instance.addKnownLocation("38609", "Belen", 34.275423, -90.37392);
		instance.addKnownLocation("38610", "Blue Mountain", 34.659973, -89.02625);
		instance.addKnownLocation("38611", "Byhalia", 34.862416, -89.68014);
		instance.addKnownLocation("38614", "Clarksdale", 34.196126, -90.59442);
		instance.addKnownLocation("38617", "Coahoma", 34.362729, -90.5035);
		instance.addKnownLocation("38618", "Coldwater", 34.699588, -89.95004);
	}

	/**
	 * Test of allWithinMilesFrom method, of class ZipCodeRangeFinder.
	 */
	@Test
	public void withinMilesFrom() {
		List<String> result = instance.allWithinMilesFrom("38606", 30);
		System.out.println(result);
		assertEquals(4, result.size());
		//sorted, closes to furthest.
		assertEquals("38606", result.get(0));
		assertEquals("38609", result.get(1));
		assertEquals("38618", result.get(2));
		assertEquals("38602", result.get(3));
	}

	@Test
	public void distance() {
		final BigDecimal expectedDistance = new BigDecimal("93.17");
		ZipCodeRangeFinder.Distance dist = instance.distance("38614", "38603");
		assertEquals(expectedDistance, dist.distanceInMiles);
		ZipCodeRangeFinder.Distance reverseDist = instance.distance("38603", "38614");
		assertEquals(expectedDistance, reverseDist.distanceInMiles);
	}

}
