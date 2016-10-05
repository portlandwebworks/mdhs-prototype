package com.portlandwebworks.mdhs.facilities.model;

/**
 *
 * @author nick
 */
public class Facility {
	
	private String name;
	private String city;
	private String county;

	public Facility() {
	}

	public Facility(String name, String city, String county) {
		this.name = name;
		this.city = city;
		this.county = county;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	
}
