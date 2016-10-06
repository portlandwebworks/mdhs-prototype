package com.portlandwebworks.mdhs;

import com.portlandwebworks.mdhs.bootstrap.CsvParser;
import com.portlandwebworks.mdhs.facilities.FacilityResources;
import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author nick
 */
@SpringBootApplication()
@ApplicationPath("/api")
public class MdhsApp extends ResourceConfig {

	@Autowired
	private CsvParser csvParser;
	
	public MdhsApp() {
		register(FacilityResources.class);
	}

	public static void main(String... args) {
		SpringApplication app = new SpringApplication(MdhsApp.class);
		app.run(args);
	}
	
	@PostConstruct
	public void init(){
		csvParser.parse();
	}
}
