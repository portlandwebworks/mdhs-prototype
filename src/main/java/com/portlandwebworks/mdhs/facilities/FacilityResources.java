package com.portlandwebworks.mdhs.facilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portlandwebworks.mdhs.facilities.model.Facility;
import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import liquibase.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 *
 * @author nick
 */
@Component
@Path("/facilities")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FacilityResources {

	private static final int DEFAULT_PAGE_SIZE = 50;

	private final FacilityRepository repo;
	private final ObjectMapper om;
	private final AddressResource addressResource;

	@Autowired
	public FacilityResources(FacilityRepository repo, AddressResource addressResource, ObjectMapper om) {
		this.repo = repo;
		this.addressResource = addressResource;
		this.om = om;
	}

	@Path("/addresses")
	public AddressResource addresses() {
		return addressResource;
	}

	@GET
	@Transactional
	public Response findFacilities(
			@QueryParam("page") Integer page,
			@QueryParam("sortBy") String sortBy,
			@QueryParam("sortDir") Sort.Direction sortDirection,
			@QueryParam("city") String city,
			@QueryParam("licenseType") Facility.LicenseType licenseType,
			@QueryParam("genders") List<Facility.AllowedGender> genders,
			@QueryParam("ageRanges") List<Facility.AgeRange> ageRanges,
			@QueryParam("capacity") Integer capacity,
			@QueryParam("withinDistance") Integer withinDistance) throws JsonProcessingException {
		final FacilityQuery facilityQuery = new FacilityQuery(city, licenseType, genders, ageRanges, withinDistance, capacity);

		if (page == null) {
			page = 0;
		}

		final PageRequest pageRequest;
		if (sortBy != null) {
			if (sortDirection != null) {
				pageRequest = new PageRequest(page, DEFAULT_PAGE_SIZE, new Sort(sortDirection, sortBy));
			} else {
				pageRequest = new PageRequest(page, DEFAULT_PAGE_SIZE, new Sort(sortBy));
			}
		} else if (StringUtils.isEmpty(city)) {
			pageRequest = new PageRequest(page, DEFAULT_PAGE_SIZE);
		} else {
			//If we have a city param, and no sort, default to distance sort.
			pageRequest = new PageRequest(page, DEFAULT_PAGE_SIZE, new Sort(Sort.Direction.ASC, "withinDistance"));

		}
		Page<FacilityResult> facilities = repo.findFacilities(facilityQuery, pageRequest);
		return Response.ok(om.writeValueAsString(facilities.getContent())).header("Total-Pages", facilities.getTotalPages()).header("Total-Results", facilities.getTotalElements()).build();
	}
}
