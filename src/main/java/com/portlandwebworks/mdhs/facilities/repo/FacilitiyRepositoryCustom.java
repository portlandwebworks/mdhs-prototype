package com.portlandwebworks.mdhs.facilities.repo;

import com.portlandwebworks.mdhs.facilities.FacilityQuery;
import com.portlandwebworks.mdhs.facilities.FacilityResult;
import java.util.List;
import org.springframework.data.domain.Sort;

/**
 *
 * @author nick
 */
public interface FacilitiyRepositoryCustom {

	List<FacilityResult> findFacilities(FacilityQuery query, Sort sort);

	String uniqueZipCities();

}
