package com.portlandwebworks.mdhs.facilities;

import com.portlandwebworks.mdhs.facilities.model.Facility;
import com.portlandwebworks.mdhs.facilities.repo.FacilitiyRepositoryCustom;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author nick
 */
public interface FacilityRepository extends JpaRepository<Facility, Integer>, FacilitiyRepositoryCustom {

	@Query("SELECT DISTINCT f.city FROM Facility f ORDER BY f.city")
	List<String> findUniqueCities();

	@Query("SELECT DISTINCT f.county FROM Facility f ORDER BY f.county")
	List<String> findUniqueCounties();
}
