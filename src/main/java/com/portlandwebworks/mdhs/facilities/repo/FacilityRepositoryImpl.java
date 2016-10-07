package com.portlandwebworks.mdhs.facilities.repo;

import com.portlandwebworks.mdhs.facilities.FacilityQuery;
import com.portlandwebworks.mdhs.facilities.FacilityResult;
import com.portlandwebworks.mdhs.facilities.model.Facility;
import com.portlandwebworks.mdhs.utils.ZipCodeRangeFinder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

/**
 *
 * @author nick
 */
public class FacilityRepositoryImpl implements FacilitiyRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	private final ZipCodeRangeFinder rangeFinder;

	@Autowired
	public FacilityRepositoryImpl(ZipCodeRangeFinder rangeFinder) {
		this.rangeFinder = rangeFinder;
	}

	@Override
	public List<FacilityResult> findFacilities(FacilityQuery query, Sort sort) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Facility> facilityQuery = cb.createQuery(Facility.class);
		Root<Facility> facilityRoot = facilityQuery.from(Facility.class);

		List<Predicate> preds = new ArrayList<>();

		facilityQuery = facilityQuery.where(preds.toArray(new Predicate[]{}));
		if (sort != null) {
			final List<Order> orderBys = new ArrayList<>();
			sort.forEach(order -> {
				if (order.isAscending()) {
					orderBys.add(cb.asc(facilityRoot.get(order.getProperty())));
				} else {
					orderBys.add(cb.desc(facilityRoot.get(order.getProperty())));

				}
			});
			facilityQuery = facilityQuery.orderBy(orderBys);
		}

		List<Facility> facilities = em.createQuery(facilityQuery)
				.setMaxResults(100) //temp setting, will remove once we get more UI stuff in place
				.getResultList();

		
		final Optional<String> zipForCity;
		if (query.getCity() != null) {
			zipForCity = rangeFinder.zipForKnownCity(query.getCity());
		} else {
			zipForCity = Optional.empty();
		}

		if (zipForCity.isPresent()) {
			Stream<FacilityResult> results = facilities.stream().map(f -> {
				FacilityResult result = new FacilityResult(rangeFinder.distance(zipForCity.get(), f.getZipCode()).getDistanceInMiles(), f);
				return result;
			});
			results = results.sorted((fr1, fr2) -> fr1.getDistance().compareTo(fr2.getDistance()));
			return results.collect(Collectors.toList());
		} else {
			return facilities.stream().map(f -> new FacilityResult(BigDecimal.ZERO, f)).collect(Collectors.toList());
		}
	}

	@Override
	public String uniqueZipCities() {
		List<Object[]> zipCities = em.createQuery("SELECT DISTINCT f.zipCode, f.city FROM Facility f ORDER BY f.city")
				.getResultList();
		return zipCities
				.stream()
				.map(o -> o[0] + "," + o[1])
				.collect(Collectors.joining("\n"));
	}

}
