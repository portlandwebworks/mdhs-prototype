package com.portlandwebworks.mdhs.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.geotools.referencing.CRS;
import org.geotools.referencing.GeodeticCalculator;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.springframework.stereotype.Component;

/**
 * Not the most elegant solution, but lets us use the small data set we have and
 * calculate zip code distances in relation to each other. Purely a brute force
 * approach. Better options would be to use something like PostGIS or other
 * third party tool.
 *
 * @author nick
 */
@Component
public class ZipCodeRangeFinder {

	private static final BigDecimal MILES_PER_METER = new BigDecimal("0.0006213712");
	private final List<ZipCodeLocation> knownLocations = new ArrayList<>();
	private final CoordinateReferenceSystem crs;

	public ZipCodeRangeFinder() throws FactoryException {
		crs = CRS.decode("EPSG:4326");
	}

	public List<String> allWithinMilesFrom(String zipFrom, Integer miles) {
		ZipCodeLocation zipLocation = findZipLocation(zipFrom);

		return knownLocations.stream().parallel().map(zipTo -> distance(zipLocation, zipTo))
				.filter(distance -> distance.distanceInMiles.compareTo(new BigDecimal(miles)) <= 0)
				.sorted((d1, d2) -> d1.distanceInMiles.compareTo(d2.distanceInMiles))
				.map(range -> range.zipCode)
				.collect(Collectors.toList());
	}

	public void addKnownLocation(String zip, String city, Double lat, Double lng) {
		knownLocations.add(new ZipCodeLocation(city, zip, new BigDecimal(lat), new BigDecimal(lng)));
	}

	public Optional<String> zipForKnownCity(String city) {
		return knownLocations.stream().filter(loc -> loc.city.equalsIgnoreCase(city)).map(loc -> loc.zipCode).findFirst();
	}

	/**
	 *
	 * @param fromZip
	 * @param toZip
	 * @return a Distance with distance in miles between them
	 * @throws IllegalArgumentException if either zip code is not in the known
	 * locations list.
	 */
	public Distance distance(String fromZip, String toZip) {
		ZipCodeLocation fromLoc = findZipLocation(fromZip);
		ZipCodeLocation toLoc = findZipLocation(toZip);
		return distance(fromLoc, toLoc);
	}

	private Distance distance(ZipCodeLocation fromZip, ZipCodeLocation toZip) {
		if (fromZip.zipCode.equals(toZip.zipCode)) {
			return new Distance(toZip.zipCode, BigDecimal.ZERO);
		} else {
			GeodeticCalculator calc = new GeodeticCalculator(crs);
			calc.setStartingGeographicPoint(fromZip.lng.doubleValue(), fromZip.lat.doubleValue());
			calc.setDestinationGeographicPoint(toZip.lng.doubleValue(), toZip.lat.doubleValue());
			double distInMeters = calc.getOrthodromicDistance();
			double miles = distInMeters * MILES_PER_METER.doubleValue();
			return new Distance(toZip.zipCode, new BigDecimal(miles).setScale(2, RoundingMode.HALF_UP));
		}
	}

	private ZipCodeLocation findZipLocation(String toZip) {
		return knownLocations.stream().filter(l -> l.zipCode.equals(toZip)).findFirst().orElseThrow(() -> new IllegalArgumentException("Could not find zip entry for: " + toZip));
	}

	public static class Distance {

		String zipCode;
		BigDecimal distanceInMiles;

		public Distance(String zipCode, BigDecimal distanceInMiles) {
			this.zipCode = zipCode;
			this.distanceInMiles = distanceInMiles;
		}

		public BigDecimal getDistanceInMiles() {
			return distanceInMiles;
		}

		public String getZipCode() {
			return zipCode;
		}
		
	}

	private static class ZipCodeLocation {

		String city;
		String zipCode;
		BigDecimal lat;
		BigDecimal lng;

		public ZipCodeLocation(String city, String zipCode, BigDecimal lat, BigDecimal lng) {
			this.city = city;
			this.zipCode = zipCode;
			this.lat = lat;
			this.lng = lng;
		}
	}

}
