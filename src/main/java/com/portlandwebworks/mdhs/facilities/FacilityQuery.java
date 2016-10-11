package com.portlandwebworks.mdhs.facilities;

import com.portlandwebworks.mdhs.facilities.model.Facility;
import java.util.List;

/**
 *
 * @author nick
 */
public class FacilityQuery {

	private final String city;
	private final Facility.LicenseType licenseType;
	private final List<Facility.AllowedGender> genders;
	private final List<Facility.AgeRange> ageRanges;
	private final Integer withinDistance;
	private final Integer capacity;

	public FacilityQuery(String city, Facility.LicenseType licenseType, List<Facility.AllowedGender> genders, List<Facility.AgeRange> ageRanges, Integer withinDistance, Integer capacity) {
		this.city = city;
		this.licenseType = licenseType;
		this.genders = genders;
		this.ageRanges = ageRanges;
		this.withinDistance = withinDistance;
		this.capacity = capacity;
	}

	public String getCity() {
		return city;
	}

	public Facility.LicenseType getLicenseType() {
		return licenseType;
	}

	public Integer getWithinDistance() {
		return withinDistance;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public List<Facility.AllowedGender> getGenders() {
		return genders;
	}

	public List<Facility.AgeRange> getAgeRanges() {
		return ageRanges;
	}
}
