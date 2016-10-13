package com.portlandwebworks.mdhs;

import com.portlandwebworks.mdhs.bootstrap.FacilityCsvParser;
import com.portlandwebworks.mdhs.bootstrap.DataLoader;
import com.portlandwebworks.mdhs.facilities.FacilityResources;
import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author nick
 */
@SpringBootApplication()
@ApplicationPath("/api")
@EnableJpaRepositories
@EnableTransactionManagement
public class MdhsApp extends ResourceConfig {

	@Autowired
	private DataLoader dataLoader;
	
	public MdhsApp() {
		register(FacilityResources.class);
	}

	public static void main(String... args) {
		SpringApplication app = new SpringApplication(MdhsApp.class);
		app.run(args);
	}
	
	@PostConstruct
	public void init(){
		dataLoader.importBaseFacilities();
		dataLoader.loadZipData();
	}
}
