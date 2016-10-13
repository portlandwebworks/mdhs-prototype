package com.portlandwebworks.mdhs.bootstrap;

import com.portlandwebworks.mdhs.facilities.model.Facility;
import java.io.ByteArrayInputStream;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import static org.easymock.EasyMock.*;

/**
 *
 * @author nick
 */
public class CsvParserTest {

	private String sampleCsv = "ProviderName,LicenseType,ProviderType,ProviderTypeDescription,QualityRating,QualityRatingDescription,ProviderCapacity,PhysicalCity,PhysicalZipCode,CountyNumber,CountyName,PhoneNumber,Gender,Age,Slots\n"
			+ "Alpha Montessori #2,LICENSED,4,Center,0,Not Rated,700,STARKVILLE,39759,53,OKTIBBEHA,601-655-8239,3,\"1,2,3,4,5\",35,0\n"
			+ "Angela Goforth,LICENSED,3,Group Home,0,Not Rated,405,CLINTON,39056,25,HINDS,601-876-5483,3,\"1,2,3\",20,0\n"
			+ "Aurora's Day Care,UNLICENSED,4,Center,0,Not Rated,400,DIBERVILLE,39540,24,HARRISON,662-383-5504,2,\"3\",20,1";

	public CsvParserTest() {
	}

	@Before
	public void setUp() {
	}

	/**
	 * Test of parse method, of class FacilityCsvParser.
	 */
	@Test
	public void testParse() throws Exception {
		ResourceLoader resourceLoader = createMock(ResourceLoader.class);
		Resource resource = createMock(Resource.class);
		FacilityCsvParser instance = new FacilityCsvParser(resourceLoader);

		expect(resourceLoader.getResource(FacilityCsvParser.CSV_RESOURCE)).andReturn(resource);
		expect(resource.getInputStream()).andReturn(new ByteArrayInputStream(sampleCsv.getBytes("UTF-8")));

		replay(resourceLoader, resource);
		List<Facility> result = instance.parse();
		verify(resourceLoader, resource);
		assertEquals(result.size(), 3);
		Facility first = result.get(0);
		assertEquals("Alpha Montessori #2", first.getName());
		assertEquals(Facility.LicenseType.LICENSED, first.getLicenseType());
		assertEquals(35, first.getOpenings().intValue());
		assertEquals(5, first.getOpenToAgeRange().size());
		assertEquals(Facility.AgeRange.INFANT, first.getOpenToAgeRange().get(0));
		assertFalse(first.isAcceptsConvictions());
		
		Facility last = result.get(2);
		assertEquals("Aurora's Day Care", last.getName());
		assertEquals(Facility.LicenseType.UNLICENSED, last.getLicenseType());
		assertEquals(1, last.getOpenToAgeRange().size());
		assertEquals(Facility.AgeRange.CHILD, last.getOpenToAgeRange().get(0));
		assertTrue(last.isAcceptsConvictions());
	}

}
