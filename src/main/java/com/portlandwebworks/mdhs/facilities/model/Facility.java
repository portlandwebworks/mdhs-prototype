package com.portlandwebworks.mdhs.facilities.model;

import com.portlandwebworks.mdhs.BasePersistable;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author nick
 */
@Entity
@Table(name = "facilities")
public class Facility extends BasePersistable {

	@NotNull
	@Column(name="name_")
	private String name;
	@NotNull
	private String city;
	@NotNull
	private String county;
	@NotNull
	@Column(name="zip_code")
	private String zipCode;
	private String phoneNumber;
	private String description;
	@Column(name="quality_rating")
	private Integer qualityRating;
	private Integer capacity;
	@NotNull
	@Column(name="license_type")
	private LicenseType licenseType;
	private AllowedGender openToGender;
	@ElementCollection()
	@CollectionTable(name="facility_age_ranges")
	@Column(name="age_range")
	@JoinColumn(name = "facility_id")
	private List<AgeRange> openToAgeRange;
	private Integer openings;
	@Column(name="accepts_convictions")
	private boolean acceptsConvictions;

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

	public LicenseType getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(LicenseType licenseType) {
		this.licenseType = licenseType;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQualityRating() {
		return qualityRating;
	}

	public void setQualityRating(Integer qualityRating) {
		this.qualityRating = qualityRating;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public AllowedGender getOpenToGender() {
		return openToGender;
	}

	public void setOpenToGender(AllowedGender openToGender) {
		this.openToGender = openToGender;
	}

	public List<AgeRange> getOpenToAgeRange() {
		return openToAgeRange;
	}

	public void setOpenToAgeRange(List<AgeRange> openToAgeRange) {
		this.openToAgeRange = openToAgeRange;
	}

	public Integer getOpenings() {
		return openings;
	}

	public void setOpenings(Integer openings) {
		this.openings = openings;
	}

	public boolean isAcceptsConvictions() {
		return acceptsConvictions;
	}

	public void setAcceptsConvictions(boolean acceptsConvictions) {
		this.acceptsConvictions = acceptsConvictions;
	}

	public static enum AllowedGender {
		MALE,
		FEMALE,
		BOTH;
	}

	public static enum AgeRange {
		INFANT(0, 2),
		TODDLER(3, 6),
		CHILD(7, 10),
		PRETEEN(11, 14),
		TEEN(15, 18);
		private final Integer minimumAge;
		private final Integer maximumAge;

		private AgeRange(Integer minimumAge, Integer maximumAge) {
			this.minimumAge = minimumAge;
			this.maximumAge = maximumAge;
		}

		public Integer getMinimumAge() {
			return minimumAge;
		}

		public Integer getMaximumAge() {
			return maximumAge;
		}
	}

	public static enum LicenseType {
		LICENSED,
		UNLICENSED
	}

}
