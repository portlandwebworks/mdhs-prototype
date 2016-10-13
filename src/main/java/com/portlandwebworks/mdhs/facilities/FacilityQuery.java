package com.portlandwebworks.mdhs.facilities;

import com.portlandwebworks.mdhs.facilities.model.Facility;
import java.util.List;

/**
 *
 * @author nick
 */
public class FacilityQuery {

	private final Boolean licensed;
	private final Boolean availableOpenings;
	private final Boolean noConvictions;
	private final String description;
	private final String city;
	private final List<Facility.AllowedGender> genders;
	private final List<Facility.AgeRange> ageRanges;
	private final Integer withinDistance;
	private final Integer capacityMin;
	private final Integer capacityMax;

	public FacilityQuery(Boolean licensed, Boolean availableOpenings, Boolean noConvictions,
			String description, String city, List<Facility.AllowedGender> genders,
			List<Facility.AgeRange> ageRanges, Integer withinDistance,
			Integer capacityMin, Integer capacityMax) {
		this.noConvictions = noConvictions;
		this.description = description;
		this.city = city;
		this.genders = genders;
		this.ageRanges = ageRanges;
		this.withinDistance = withinDistance;
		this.capacityMin = capacityMin;
		this.capacityMax = capacityMax;
		this.licensed = licensed;
		this.availableOpenings = availableOpenings;
	}

	public Boolean getLicensed() {
		return licensed;
	}

	public Boolean getAvailableOpenings() {
		return availableOpenings;
	}

	public Boolean getNoConvictions() {
		return noConvictions;
	}

	public String getDescription() {
		return description;
	}

	public String getCity() {
		return city;
	}

	public Integer getWithinDistance() {
		return withinDistance;
	}

	public Integer getCapacityMin() {
		return capacityMin;
	}

	public Integer getCapacityMax() {
		return capacityMax;
	}

	public List<Facility.AllowedGender> getGenders() {
		return genders;
	}

	public List<Facility.AgeRange> getAgeRanges() {
		return ageRanges;
	}
}
