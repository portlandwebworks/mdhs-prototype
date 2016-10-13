package com.portlandwebworks.mdhs.bootstrap;

import com.portlandwebworks.mdhs.facilities.FacilityRepository;
import com.portlandwebworks.mdhs.facilities.model.Facility;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nick
 */
@Component
public class DataLoader {

	private static final Logger log = LoggerFactory.getLogger(DataLoader.class);
	private final FacilityRepository repo;
	private final FacilityCsvParser parser;
	private final ZipCodeLoader zipLoader;

	@Autowired
	public DataLoader(FacilityRepository repo, FacilityCsvParser parser, ZipCodeLoader zipLoader) {
		this.repo = repo;
		this.parser = parser;
		this.zipLoader = zipLoader;
	}

	@Transactional
	public void importBaseFacilities() {
		List<Facility> facilities = parser.parse();
		repo.save(facilities);
		log.info("Saved {} facilities.", facilities.size());
	}

	public void loadZipData() {
		zipLoader.loadKnownZipCodes();
		log.info("Zip code information loaded.");
	}

}
