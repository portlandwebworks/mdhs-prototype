package com.portlandwebworks.mdhs.facilities;

import com.portlandwebworks.mdhs.facilities.model.Facility;
import java.math.BigDecimal;

/**
 *
 * @author nick
 */
public class FacilityResult {
	private BigDecimal lat;
	private BigDecimal lng;
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

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}
}
