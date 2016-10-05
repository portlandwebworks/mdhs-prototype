package com.portlandwebworks.mdhs.facilities;

import com.portlandwebworks.mdhs.facilities.model.Facility;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
	
	@GET
	public Response findFacilities(){
		List<Facility> facilities = new ArrayList<>();
		facilities.add(new Facility("Test Name", "City", "County"));
		return Response.ok(facilities).build();
	}
}
