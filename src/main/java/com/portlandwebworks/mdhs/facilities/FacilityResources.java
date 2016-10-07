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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
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
	public Response findFacilities(@QueryParam("city") String city, @QueryParam("withinDistance") Integer withinDistance) throws JsonProcessingException {
		List<FacilityResult> facilities = repo.findFacilities(new FacilityQuery(city, withinDistance), new Sort(Sort.Direction.ASC, "city"));

		return Response.ok(om.writeValueAsString(facilities)).build();
	}

}
