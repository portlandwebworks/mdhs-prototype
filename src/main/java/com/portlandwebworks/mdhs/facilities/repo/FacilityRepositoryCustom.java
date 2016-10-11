package com.portlandwebworks.mdhs.facilities.repo;

import com.portlandwebworks.mdhs.facilities.FacilityQuery;
import com.portlandwebworks.mdhs.facilities.FacilityResult;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author nick
 */
public interface FacilityRepositoryCustom {

	Page<FacilityResult> findFacilities(FacilityQuery query, Pageable pageInfo);

	String uniqueZipCities();

}
