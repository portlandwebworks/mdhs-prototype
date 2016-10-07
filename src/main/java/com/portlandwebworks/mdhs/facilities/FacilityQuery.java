package com.portlandwebworks.mdhs.facilities;

/**
 *
 * @author nick
 */
public class FacilityQuery {
	
	private String city;
	private Integer withinDistance;

	public FacilityQuery(String city, Integer withinDistance) {
		this.city = city;
		this.withinDistance = withinDistance;
	}

	public String getCity() {
		return city;
	}

	public Integer getWithinDistance() {
		return withinDistance;
	}
	
}
