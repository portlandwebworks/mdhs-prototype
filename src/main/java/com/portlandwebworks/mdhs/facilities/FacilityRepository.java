package com.portlandwebworks.mdhs.facilities;

import com.portlandwebworks.mdhs.facilities.model.Facility;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.portlandwebworks.mdhs.facilities.repo.FacilityRepositoryCustom;

/**
 *
 * @author nick
 */
public interface FacilityRepository extends JpaRepository<Facility, Integer>, FacilityRepositoryCustom {

	@Query("SELECT DISTINCT f.city FROM Facility f ORDER BY f.city")
	List<String> findUniqueCities();

	@Query("SELECT DISTINCT f.county FROM Facility f ORDER BY f.county")
	List<String> findUniqueCounties();
}
