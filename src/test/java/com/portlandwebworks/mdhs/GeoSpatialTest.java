package com.portlandwebworks.mdhs;

import org.geotools.referencing.CRS;
import org.geotools.referencing.GeodeticCalculator;
import org.junit.Test;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 *
 * @author nick
 */
public class GeoSpatialTest {

	@Test
	public void distanceCalc() throws FactoryException {
		CoordinateReferenceSystem crs = CRS.decode("EPSG:4326");
		GeodeticCalculator gc = new GeodeticCalculator(crs);
		gc.setStartingGeographicPoint(-88.55463, 33.833689);
		gc.setDestinationGeographicPoint(-89.47412, 34.489297);

		double distance = gc.getOrthodromicDistance();
		System.out.println("Calculated distance of " + distance + "meters");

	}
}
