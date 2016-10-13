package com.portlandwebworks.mdhs.facilities;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nick
 */
@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {

	private final FacilityRepository repo;

	@Autowired
	public AddressResource(FacilityRepository repo) {
		this.repo = repo;
	}

	@GET()
	@Path("/cities")
	public Response getUniqueCities() {
		return Response.ok(repo.findUniqueCities()).build();
	}

	@GET()
	@Path("/counties")
	public Response getUniqueCounties() {
		return Response.ok(repo.findUniqueCounties()).build();
	}
	
	@GET
	@Path("/city-zips")
	public String uniqueCitiesAndZips(){
		return repo.uniqueZipCities();
	}

}
