package com.portlandwebworks.mdhs.facilities;

import com.portlandwebworks.mdhs.facilities.model.Facility;
import java.math.BigDecimal;

/**
 *
 * @author nick
 */
public class FacilityResult {
	private BigDecimal distance;
	private Facility facility;

	public FacilityResult(BigDecimal distance, Facility facility) {
		this.distance = distance;
		this.facility = facility;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	public Facility getFacility() {
		return facility;
	}
	
}
